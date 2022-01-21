package bg.project.foodie.service.impl;

import bg.project.foodie.cloudinary.*;
import bg.project.foodie.model.entity.*;
import bg.project.foodie.model.service.*;
import bg.project.foodie.model.view.*;
import bg.project.foodie.repository.ProductRepository;
import bg.project.foodie.repository.RecipeRepository;
import bg.project.foodie.service.CategoryService;
import bg.project.foodie.service.RecipeService;
import bg.project.foodie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.*;
import java.io.*;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CategoryService categoryService;
    private final CloudinaryService cloudinaryService;

    public RecipeServiceImpl(RecipeRepository recipeRepository, ModelMapper modelMapper, UserService userService, CategoryService categoryService, ProductRepository productRepository, CloudinaryService cloudinaryService) {
        this.recipeRepository = recipeRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.categoryService = categoryService;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void addRecipe(RecipeServiceModel recipeServiceModel, Principal principal) throws IOException {
        RecipeEntity recipe = modelMapper.map(recipeServiceModel, RecipeEntity.class);

        Set<ProductEntity> products = recipeServiceModel.getProducts().stream()
                .map(p -> modelMapper.map(p, ProductEntity.class))
                .collect(Collectors.toSet());

        if (!recipeServiceModel.getPicture().isEmpty()) {
            CloudinaryImage upload = cloudinaryService.upload(recipeServiceModel.getPicture());
            recipe.setPicturePublicId(upload.getPublicId());
            recipe.setPictureUrl(upload.getUrl());
        }

        recipe.setProducts(products);
        recipe.setCategory(categoryService.findByName(recipeServiceModel.getCategory()));
        recipe.setAuthor(userService.findUserByUsername(principal.getName()));

        recipeRepository.save(recipe);
    }

    @Override
    public List<RecipeViewModel> getAllRecipeViewModels() {

        return recipeRepository.findAll().stream()
                .map(r -> {
                    RecipeViewModel view = modelMapper.map(r, RecipeViewModel.class);
                    List<ProductServiceModel> products = r.getProducts().stream()
                            .map(p -> modelMapper.map(p, ProductServiceModel.class))
                            .collect(Collectors.toList());
                    view.setProducts(products);
                    return view;
                })
                .collect(Collectors.toList());

    }

    @Override
    public RecipeViewModel getRecipeViewById(Long id) {
        return recipeRepository.findById(id)
                .map(r -> {
                    RecipeViewModel view = modelMapper.map(r, RecipeViewModel.class);
                    view.setCategory(r.getCategory().getName());
                    return view;
                })
                .orElse(null);
    }

    @Override
    public RecipeEntity findById(Long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateRecipe(RecipeServiceModel serviceModel, Principal principal) {
        RecipeEntity updatedRecipe = modelMapper.map(serviceModel, RecipeEntity.class);
        updatedRecipe.setCategory(categoryService.findByName(serviceModel.getCategory()));
        RecipeEntity recipe = recipeRepository.findById(updatedRecipe.getId()).orElse(null);
        assert recipe != null;
        if (!recipe.getAuthor().getUsername().equals(principal.getName())) {
            return false;
        }
        updateEntity(updatedRecipe, recipe);
        recipeRepository.save(recipe);
        return true;
    }

    private void updateEntity(RecipeEntity updatedRecipe, RecipeEntity recipe) {
        recipe.setName(updatedRecipe.getName())
                .setProducts(updatedRecipe.getProducts())
                .setShortDescription(updatedRecipe.getShortDescription())
                .setCategory(updatedRecipe.getCategory())
                .setPortions(updatedRecipe.getPortions())
                .setCookingTime(updatedRecipe.getCookingTime())
                .setCookingInstructions(updatedRecipe.getCookingInstructions());
    }

    @Override
    public void deleteById(Long id) {
        RecipeEntity recipe = recipeRepository.findById(id).orElse(null);
        if (recipe != null) {
            String publicId = recipe.getPicturePublicId();

            if (publicId != null) {
                cloudinaryService.delete(publicId);
            }
            recipeRepository.delete(recipe);
        }
    }

    @Override
    public List<RecipeViewModel> getAllRecipesByCategory(String c) {
        return recipeRepository.findAllByCategory(c).stream()
                .map(r -> modelMapper.map(r, RecipeViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public RecipeViewModel findHighestRatedRecipe() {
        RecipeEntity recipe = recipeRepository.findRecipeByHighestRating();
        return modelMapper.map(recipe, RecipeViewModel.class);
    }
}
