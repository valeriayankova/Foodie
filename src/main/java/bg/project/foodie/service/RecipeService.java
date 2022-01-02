package bg.project.foodie.service;

import bg.project.foodie.model.entity.RecipeEntity;
import bg.project.foodie.model.service.RecipeServiceModel;
import bg.project.foodie.model.view.RecipeViewModel;

import java.io.*;
import java.security.Principal;
import java.util.List;

public interface RecipeService {
    void addRecipe(RecipeServiceModel recipeServiceModel, Principal principal) throws IOException;

    List<RecipeViewModel> getAllRecipeViewModels();

    RecipeViewModel getRecipeViewById(Long id);

    RecipeEntity findById(Long id);
}
