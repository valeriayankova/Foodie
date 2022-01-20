package bg.project.foodie.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    private String productName;
    private Double quantity;
    private String measurement;
//    private RecipeEntity recipe;

    @Column(nullable = false)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String name) {
        this.productName = name;
    }

    @Column(nullable = false)
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Column
    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

//    @ManyToOne(cascade = CascadeType.ALL)
//    public RecipeEntity getRecipe() {
//        return recipe;
//    }
//
//    public void setRecipe(RecipeEntity recipe) {
//        this.recipe = recipe;
//    }
}
