package bg.project.foodie.model.binding;

import bg.project.foodie.model.entity.enums.CategoryNameEnum;
import org.springframework.web.multipart.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

public class RecipeBindingModel {

    private String name;
    private String cookingInstructions;
    private String shortDescription;
    private CategoryNameEnum category;
    private Integer cookingTime;
    private Integer portionsServed;
    private List<ProductBindingModel> products;
    private MultipartFile picture;

    @NotNull
    @Size(min = 3)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Size(min = 10)
    public String getCookingInstructions() {
        return cookingInstructions;
    }

    public void setCookingInstructions(String cookingInstructions) {
        this.cookingInstructions = cookingInstructions;
    }

    @NotNull
    @Size(max = 40)
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Integer getPortionsServed() {
        return portionsServed;
    }

    public void setPortionsServed(Integer portionsServed) {
        this.portionsServed = portionsServed;
    }

    @NotNull
    public CategoryNameEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryNameEnum category) {
        this.category = category;
    }

    @NotNull
    @Positive
    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    @NotNull
    @Positive
    public Integer getPortions() {
        return portionsServed;
    }

    public void setPortions(Integer portions) {
        this.portionsServed = portions;
    }

    @NotNull
    @Size(min = 1, max = 30)
    public List<ProductBindingModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBindingModel> products) {
        this.products = products;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }
}
