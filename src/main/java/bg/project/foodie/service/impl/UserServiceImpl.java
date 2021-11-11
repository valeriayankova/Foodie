package bg.project.foodie.service.impl;

import bg.project.foodie.model.entity.RoleEntity;
import bg.project.foodie.model.entity.UserEntity;
import bg.project.foodie.model.entity.enums.RoleNameEnum;
import bg.project.foodie.model.service.UserServiceModel;
import bg.project.foodie.model.view.AdminPanelUserViewModel;
import bg.project.foodie.model.view.UserViewModel;
import bg.project.foodie.repository.UserRepository;
import bg.project.foodie.service.RoleService;
import bg.project.foodie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final SecurityUserServiceImpl securityUserService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder, ModelMapper modelMapper, SecurityUserServiceImpl securityUserService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.securityUserService = securityUserService;
    }


    @Override
    public void initializeAdmin() {
        if (userRepository.count() > 0) {
            return;
        }

        UserEntity admin = new UserEntity();
        admin.setUsername("admin")
                .setPassword(passwordEncoder.encode("1234567890"))
                .setEmail("admin@admin")
                .setFirstName("admin")
                .setLastName("adminov");

        Set<RoleEntity> roles = Set.of(roleService.findByRoleName(RoleNameEnum.ADMIN),
                roleService.findByRoleName(RoleNameEnum.USER));
        admin.setRoles(roles);

        userRepository.save(admin);
    }

    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {

        if (userRepository.existsByUsername(userServiceModel.getUsername())) {
            return false;
        }

        UserEntity newUser = modelMapper.map(userServiceModel, UserEntity.class);
        newUser.setRoles(Set.of(roleService.findByRoleName(RoleNameEnum.USER)));
        newUser.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        userRepository.save(newUser);

        UserDetails principal = securityUserService.loadUserByUsername(newUser.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);


        return true;
    }

    @Override
    public List<AdminPanelUserViewModel> findAllUsers() {
        return userRepository.findAll().stream()
                .map(u -> {
                    AdminPanelUserViewModel view = modelMapper.map(u, AdminPanelUserViewModel.class);
                    if (u.getRoles().stream().anyMatch(r -> r.getName().equals(RoleNameEnum.ADMIN))) {
                        view.setAdmin(true);
                    }

                    return view;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Long id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        if (user == null || user.getRoles().stream().anyMatch(r -> r.getName().equals(RoleNameEnum.ADMIN))) {
            return;
        }
        userRepository.delete(user);
    }

    @Override
    public void makeUserAdmin(Long id) {
        userRepository.findById(id)
                .ifPresent(user -> {
                    user.getRoles().add(roleService.findByRoleName(RoleNameEnum.ADMIN));
                    userRepository.save(user);
                });

    }

    @Override
    public UserViewModel findUserById(Long id) {
        return userRepository
                .findById(id)
                .map(u -> modelMapper.map(u, UserViewModel.class))
                .orElse(null);
    }

    @Override
    public UserEntity findUserByUsername(String name) {
        return userRepository.findByUsername(name).orElse(null);
    }

}
