package bg.project.foodie.service;

import bg.project.foodie.model.entity.UserEntity;
import bg.project.foodie.model.service.UserServiceModel;
import bg.project.foodie.model.view.AdminPanelUserViewModel;
import bg.project.foodie.model.view.UserViewModel;

import java.util.List;

public interface UserService {

    void initializeAdmin();

    boolean registerUser(UserServiceModel userServiceModel);

    List<AdminPanelUserViewModel> findAllUsers();

    void deleteUserById(Long id);

    void makeUserAdmin(Long id);

    UserViewModel findUserById(Long id);

    UserEntity findUserByUsername(String name);

    UserViewModel getViewByUsername(String name);
}
