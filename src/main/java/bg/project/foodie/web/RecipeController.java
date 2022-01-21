package bg.project.foodie.web;

import bg.project.foodie.model.service.ProductServiceModel;
import bg.project.foodie.model.service.RecipeServiceModel;
import bg.project.foodie.model.service.ReviewServiceModel;
import bg.project.foodie.model.view.*;
import bg.project.foodie.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.*;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final ModelMapper modelMapper;
    private final ReviewService reviewService;
    private final UserService userService;

    public RecipeController(RecipeService recipeService, ModelMapper modelMapper, ReviewService reviewService, UserService userService) {
        this.recipeService = recipeService;
        this.modelMapper = modelMapper;
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @ModelAttribute
    public ReviewServiceModel reviewBindingModel() {
        return new ReviewServiceModel();
    }



    @GetMapping(value = {"", "/{category}"})
    public String recipes(@PathVariable(required = false) String category, Model model) {
        if (category != null) {
            List<RecipeViewModel> recipes = recipeService.getAllRecipesByCategory(category.toUpperCase());
            model.addAttribute("categoryRecipes", recipes);
        } else {
            List<RecipeViewModel> recipeViewModels = recipeService.getAllRecipeViewModels();
            model.addAttribute("recipes", recipeViewModels);
        }

        return "recipes-all";
    }

    @GetMapping("/add")
    public String addRecipe(Model model) {
        RecipeServiceModel recipeServiceModel = new RecipeServiceModel();
        ProductServiceModel productServiceModel = new ProductServiceModel();
        recipeServiceModel.setProducts(List.of(productServiceModel));
        model.addAttribute("recipeServiceModel", recipeServiceModel);
        return "recipe-add";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model, Principal principal) {
        RecipeViewModel recipeViewModel = recipeService.getRecipeViewById(id);
        List<ReviewViewModel> reviews = reviewService.findAllReviewsById(id);
        UserViewModel author = recipeViewModel.getAuthor();
        boolean isAuthor = principal.getName().equals(author.getUsername());
        boolean isAdmin = userService.isCurrentUserAdmin(principal);

        model.addAttribute("reviews", reviews);
        model.addAttribute("recipeViewModel", recipeViewModel);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isAuthor", isAuthor);

        return "recipe-details";
    }

    @PostMapping("/add")
    public String addRecipePost(@Valid RecipeServiceModel recipeServiceModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Principal principal) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeServiceModel", recipeServiceModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.recipeServiceModel", bindingResult);

            return "redirect:/recipes";
        }
        recipeService.addRecipe(recipeServiceModel, principal);

        return "redirect:/recipes";
    }

    @PostMapping("/details/{id}/review/add")
    public String addReviewPost(@PathVariable Long id,
                                @Valid ReviewServiceModel reviewServiceModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("reviewServiceModel", reviewServiceModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.reviewServiceModel", bindingResult);

            return "redirect:/recipes/details/{id}";
        }

        reviewService.addReview(id, reviewServiceModel, principal);
        return "redirect:/recipes/details/{id}";
    }

    @DeleteMapping("/details/{recipeId}/reviews/delete/{id}")
    public String deleteReview(@PathVariable Long recipeId, @PathVariable Long id) {
        reviewService.deleteReview(id);
        return "redirect:/recipes/details/{recipeId}";
    }

    @GetMapping("/update/{id}")
    public String updateRecipe(@PathVariable Long id, Model model) {
        RecipeViewModel recipe = recipeService.getRecipeViewById(id);
        RecipeServiceModel recipeUpdate = modelMapper.map(recipe, RecipeServiceModel.class);
        List<ProductServiceModel> recipeProducts = recipeUpdate.getProducts();
        model.addAttribute("recipeUpdate", recipeUpdate);
        return "recipe-update";
    }

    @PatchMapping("/update/{id}")
    public String updateRecipePatch(@PathVariable Long id,
                                    @Valid RecipeServiceModel recipeServiceModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes, Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipe", recipeServiceModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.recipe", bindingResult);

            return "redirect:update/{id}";
        }

        recipeServiceModel.setId(id);
        boolean isUpdated = recipeService.updateRecipe(recipeServiceModel, principal);
        if (!isUpdated) {
            return "redirect:/update/{id}";
        }
        return "redirect:/recipes/details/{id}";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteById(id);
        return "redirect:/recipes";
    }

    @GetMapping("/details/favorite")
    public String getHighestRatedRecipe() {
        RecipeViewModel recipe = recipeService.findHighestRatedRecipe();
        return "redirect:/recipes/details/" + recipe.getId();
    }
}
