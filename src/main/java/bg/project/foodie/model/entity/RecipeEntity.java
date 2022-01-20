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

    public RecipeEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Column(nullable = false)
    public String getShortDescription() {
        return shortDescription;
    }

    public RecipeEntity setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    @Column(columnDefinition = "TEXT", nullable = false)
    public String getCookingInstructions() {
        return cookingInstructions;
    }

    public RecipeEntity setCookingInstructions(String cookingInstructions) {
        this.cookingInstructions = cookingInstructions;
        return this;
    }

    @Column(nullable = false)
    public Integer getCookingTime() {
        return cookingTime;
    }

    public RecipeEntity setCookingTime(Integer cookingTime) {
        this.cookingTime = cookingTime;
        return this;
    }

    @Column(nullable = false)
    public Integer getPortions() {
        return portions;
    }

    public RecipeEntity setPortions(Integer portionsServed) {
        this.portions = portionsServed;
        return this;
    }

    @Column
    public String getPictureUrl() {
        return pictureUrl;
    }

    public RecipeEntity setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    @Column
    public String getPicturePublicId() {
        return picturePublicId;
    }

    public RecipeEntity setPicturePublicId(String picturePublicId) {
        this.picturePublicId = picturePublicId;
        return this;
    }

    @ManyToOne
    public CategoryEntity getCategory() {
        return category;
    }

    public RecipeEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    @ManyToOne
    public UserEntity getAuthor() {
        return author;
    }

    public RecipeEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn
    public Set<ProductEntity> getProducts() {
        return products;
    }

    public RecipeEntity setProducts(Set<ProductEntity> products) {
        this.products = products;
        return this;
    }

    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<ReviewEntity> getReviews() {
        return reviews;
    }

    public RecipeEntity setReviews(Set<ReviewEntity> reviews) {
        this.reviews = reviews;
        return this;
    }
}
