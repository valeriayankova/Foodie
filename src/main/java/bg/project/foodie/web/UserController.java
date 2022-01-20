package bg.project.foodie.web;

import bg.project.foodie.model.service.UserServiceModel;
import bg.project.foodie.model.view.UserViewModel;
import bg.project.foodie.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public UserServiceModel userServiceModel() {
        return new UserServiceModel();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        UserViewModel userViewModel = userService.getViewByUsername(principal.getName());
        model.addAttribute("userViewModel", userViewModel);

        return "profile";
    }

    @PostMapping("/register")
    public String registerPost(@Valid UserServiceModel userServiceModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() ||
                !userServiceModel.getPassword().equals(userServiceModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterService", userServiceModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        boolean isSuccessful = userService.registerUser(userServiceModel);

        if (!isSuccessful) {
            redirectAttributes.addFlashAttribute("usernameTaken", true);
            return "redirect:register";
        }
        return "redirect:/";
    }
}
