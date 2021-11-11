package bg.project.foodie.model.binding;

import javax.validation.constraints.NotNull;

public class ReviewBindingModel {
    private Integer score;
    private String description;
    private Integer recipeId;

    @NotNull
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    @NotNull
    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }
}
