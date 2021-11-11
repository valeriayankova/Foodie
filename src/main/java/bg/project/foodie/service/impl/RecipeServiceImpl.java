package bg.project.foodie.service.impl;

import bg.project.foodie.model.entity.ProductEntity;
import bg.project.foodie.model.entity.RecipeEntity;
import bg.project.foodie.model.service.RecipeServiceModel;
import bg.project.foodie.model.view.RecipeViewModel;
import bg.project.foodie.repository.ProductRepository;
import bg.project.foodie.repository.RecipeRepository;
import bg.project.foodie.service.CategoryService;
import bg.project.foodie.service.RecipeService;
import bg.project.foodie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository, ModelMapper modelMapper, UserService userService, CategoryService categoryService, ProductRepository productRepository) {
        this.recipeRepository = recipeRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.categoryService = categoryService;
        this.productRepository = productRepository;
    }

    @Override
    public void addRecipe(RecipeServiceModel recipeServiceModel, Principal principal) {
        RecipeEntity recipe = modelMapper.map(recipeServiceModel, RecipeEntity.class);

        Set<ProductEntity> products = recipeServiceModel.getProducts().stream()
                .map(productBindingModel -> {
                    ProductEntity product = modelMapper.map(productBindingModel, ProductEntity.class);
                    product.setRecipe(recipe);

                    return product;
                })
                .collect(Collectors.toSet());

        recipe.setProducts(products);
        recipe.setCategory(categoryService.findByName(recipeServiceModel.getCategory()));
        recipe.setAuthor(userService.findUserByUsername(principal.getName()));

        recipeRepository.save(recipe);

    }

    @Override
    public List<RecipeViewModel> getAllRecipeViewModels() {

        return recipeRepository.findAll().stream()
                .map(r -> modelMapper.map(r, RecipeViewModel.class))
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
}
