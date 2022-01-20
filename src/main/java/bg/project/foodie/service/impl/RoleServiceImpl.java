package bg.project.foodie.service.impl;

import bg.project.foodie.model.entity.RoleEntity;
import bg.project.foodie.model.entity.enums.RoleNameEnum;
import bg.project.foodie.repository.RoleRepository;
import bg.project.foodie.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initializeRoles() {
        if (roleRepository.count() > 0) {
            return;
        }

        Arrays.stream(RoleNameEnum.values())
                .forEach(r -> {
                    RoleEntity role = new RoleEntity();
                    role.setName(r);
                    roleRepository.save(role);
                });
    }

    @Override
    public RoleEntity findByRoleName(RoleNameEnum roleName) {
        return roleRepository.findByName(roleName);
    }
}
