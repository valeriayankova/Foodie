package bg.project.foodie.init;

import bg.project.foodie.service.CategoryService;
import bg.project.foodie.service.RoleService;
import bg.project.foodie.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final CategoryService categoryService;

    public DBInit(UserService userService, RoleService roleService, CategoryService categoryService) {
        this.userService = userService;
        this.roleService = roleService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        roleService.initializeRoles();
        userService.initializeAdmin();
        categoryService.initializeCategories();
    }
}
