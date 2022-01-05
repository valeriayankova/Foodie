package bg.project.foodie.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class RecipeEntity extends BaseEntity {

    private String name;
    private String shortDescription;
    private String cookingInstructions;
    private CategoryEntity category;
    private Integer cookingTime;
    private Integer portions;
    private String pictureUrl;
    private String picturePublicId;
    private UserEntity author;
    private Set<ProductEntity> products;
    private Set<ReviewEntity> reviews;

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Column(columnDefinition = "TEXT", nullable = false)
    public String getCookingInstructions() {
        return cookingInstructions;
    }

    public void setCookingInstructions(String cookingInstructions) {
        this.cookingInstructions = cookingInstructions;
    }

    @Column(nullable = false)
    public Integer getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    @Column(nullable = false)
    public Integer getPortions() {
        return portions;
    }

    public void setPortions(Integer portionsServed) {
        this.portions = portionsServed;
    }

    @Column
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Column
    public String getPicturePublicId() {
        return picturePublicId;
    }

    public void setPicturePublicId(String picturePublicId) {
        this.picturePublicId = picturePublicId;
    }

    @ManyToOne
    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    @ManyToOne
    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductEntity> products) {
        this.products = products;
    }

    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<ReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(Set<ReviewEntity> reviews) {
        this.reviews = reviews;
    }
}
