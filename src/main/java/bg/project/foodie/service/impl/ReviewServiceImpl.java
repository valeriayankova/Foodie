package bg.project.foodie.service.impl;

import bg.project.foodie.model.entity.RecipeEntity;
import bg.project.foodie.model.entity.ReviewEntity;
import bg.project.foodie.model.service.ReviewServiceModel;
import bg.project.foodie.model.view.ReviewViewModel;
import bg.project.foodie.repository.ReviewRepository;
import bg.project.foodie.service.RecipeService;
import bg.project.foodie.service.ReviewService;
import bg.project.foodie.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;
    private final RecipeService recipeService;
    private final UserService userService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ModelMapper modelMapper, RecipeService recipeService, UserService userService) {
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
        this.recipeService = recipeService;
        this.userService = userService;
    }


    @Override
    public void addReview(Long id, ReviewServiceModel reviewServiceModel, Principal principal) {
        RecipeEntity recipe = recipeService.findById(id);

        ReviewEntity review = modelMapper.map(reviewServiceModel, ReviewEntity.class);
        review.setAuthor(userService.findUserByUsername(principal.getName()));
        review.setRecipe(recipe);
        reviewRepository.save(review);

    }

    @Override
    public List<ReviewViewModel> findAllReviewsById(Long id) {
        return reviewRepository.findAll().stream()
                .filter(r -> r.getRecipe().getId().equals(id))
                .map(r -> {
                    ReviewViewModel view = modelMapper.map(r, ReviewViewModel.class);
                    view.setUsername(r.getAuthor().getUsername());

                    return view;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
