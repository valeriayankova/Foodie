package bg.project.foodie.web;

import bg.project.foodie.model.binding.ProductBindingModel;
import bg.project.foodie.model.binding.RecipeBindingModel;
import bg.project.foodie.model.binding.ReviewBindingModel;
import bg.project.foodie.model.service.RecipeServiceModel;
import bg.project.foodie.model.service.ReviewServiceModel;
import bg.project.foodie.model.view.RecipeViewModel;
import bg.project.foodie.model.view.ReviewViewModel;
import bg.project.foodie.service.RecipeService;
import bg.project.foodie.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;
    private final ModelMapper modelMapper;
    private final ReviewService reviewService;


    public RecipeController(RecipeService recipeService, ModelMapper modelMapper, ReviewService reviewService) {
        this.recipeService = recipeService;
        this.modelMapper = modelMapper;
        this.reviewService = reviewService;
    }

    @ModelAttribute
    public ReviewBindingModel reviewBindingModel() {
        return new ReviewBindingModel();
    }


    @GetMapping("/all")
    public String recipes(Model model) {

        List<RecipeViewModel> recipeViewModels = recipeService.getAllRecipeViewModels();
        model.addAttribute("recipes", recipeViewModels);

        return "recipes-all";
    }

    @GetMapping("/add")
    public String addRecipe(Model model) {
        RecipeBindingModel recipeBindingModel = new RecipeBindingModel();
        ProductBindingModel productBindingModel = new ProductBindingModel();
        recipeBindingModel.setProducts(List.of(productBindingModel));
        model.addAttribute("recipeBindingModel", recipeBindingModel);
        return "recipe-add";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        RecipeViewModel recipeViewModel = recipeService.getRecipeViewById(id);
        List<ReviewViewModel> reviews = reviewService.findAllReviewsById(id);

        model.addAttribute("reviews", reviews);
        model.addAttribute("recipeViewModel", recipeViewModel);

        return "recipe-details";
    }

    @PostMapping("/add")
    public String addRecipePost(@Valid RecipeBindingModel recipeBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Principal principal) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeBindingModel", recipeBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.recipeBindingModel", bindingResult);

            return "redirect:/recipes/all";
        }

        RecipeServiceModel recipeServiceModel = modelMapper.map(recipeBindingModel, RecipeServiceModel.class);
        recipeService.addRecipe(recipeServiceModel, principal);


        //TODO: implement business logic; also in the thymeleaf template i have to find a way to get a list from the user input
        return "redirect:/recipes/all";
    }

    @PostMapping("/details/{id}/review/add")
    public String addReviewPost(@PathVariable Long id,
                                @Valid ReviewBindingModel reviewBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("reviewBindingModel", reviewBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.reviewBindingModel", bindingResult);

            return "redirect:/recipes/details/{id}";
        }

        reviewService.addReview(id, modelMapper.map(reviewBindingModel, ReviewServiceModel.class), principal);

        return "redirect:/recipes/details/{id}";
    }

    @DeleteMapping("/details/{recipeId}/reviews/delete/{id}")
    public String deleteReview(@PathVariable Long recipeId, @PathVariable Long id) {
        reviewService.deleteReview(id);
        return "redirect:/recipes/details/{recipeId}";
    }





}
