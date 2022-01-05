package bg.project.foodie.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class ReviewEntity {

    private Long reviewId;
    private Integer score;
    private String description;
    private UserEntity author;
    private RecipeEntity recipe;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    @Column(nullable = false)
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    @ManyToOne
    public RecipeEntity getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
    }
}
