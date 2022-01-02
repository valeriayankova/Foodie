package bg.project.foodie.model.service;

import bg.project.foodie.model.binding.ProductBindingModel;
import bg.project.foodie.model.entity.enums.CategoryNameEnum;
import org.springframework.web.multipart.*;

import java.util.List;

public class RecipeServiceModel {

    private Long id;
    private String name;
    private String imageUrl;
    private String cookingInstructions;
    private String shortDescription;
    private CategoryNameEnum category;
    private Integer cookingTime;
    private Integer portions;
    private List<ProductBindingModel> products;
    private MultipartFile picture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCookingInstructions() {
        return cookingInstructions;
    }

    public void setCookingInstructions(String cookingInstructions) {
        this.cookingInstructions = cookingInstructions;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryNameEnum category) {
        this.category = category;
    }

    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    public Integer getPortions() {
        return portions;
    }

    public void setPortions(Integer portions) {
        this.portions = portions;
    }

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
