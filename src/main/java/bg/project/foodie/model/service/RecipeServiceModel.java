package bg.project.foodie.model.service;

import bg.project.foodie.model.entity.enums.CategoryNameEnum;
import org.springframework.web.multipart.*;

import javax.validation.constraints.*;
import java.util.List;

public class RecipeServiceModel {
    private Long id;
    private String name;
    private String cookingInstructions;
    private String shortDescription;
    private CategoryNameEnum category;
    private Integer cookingTime;
    private Integer portions;
    private List<ProductServiceModel> products;
    private MultipartFile picture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return portions;
    }

    public void setPortions(Integer portions) {
        this.portions = portions;
    }

    @NotNull
    @Size(min = 1, max = 30)
    public List<ProductServiceModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductServiceModel> products) {
        this.products = products;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }


}
