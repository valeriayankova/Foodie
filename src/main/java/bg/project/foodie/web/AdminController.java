package bg.project.foodie.web;

import bg.project.foodie.model.view.AdminPanelUserViewModel;
import bg.project.foodie.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/panel")
    public String adminPanel() {
        return "admin-panel";
    }

    @GetMapping("/reports")
    public String reports() {
        return "admin-reports";
    }

    @GetMapping("/users")
    public String users(Model model) {
        List<AdminPanelUserViewModel> adminPanelUserViewModels = userService.findAllUsers();
        model.addAttribute("users", adminPanelUserViewModels);

        return "admin-users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);

        return "redirect:/admin/users";
    }

    @PatchMapping("/users/{id}")
    public String makeAdmin(@PathVariable Long id) {
        userService.makeUserAdmin(id);

        return "redirect:/admin/users";
    }
}
