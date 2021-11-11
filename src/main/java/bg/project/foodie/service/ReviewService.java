package bg.project.foodie.service;

import bg.project.foodie.model.service.ReviewServiceModel;
import bg.project.foodie.model.view.ReviewViewModel;

import java.security.Principal;
import java.util.List;

public interface ReviewService {
    void addReview(Long id, ReviewServiceModel map, Principal principal);

    List<ReviewViewModel> findAllReviewsById(Long id);

    void deleteReview(Long id);
}
