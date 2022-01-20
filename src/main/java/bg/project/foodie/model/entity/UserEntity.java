package bg.project.foodie.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate created;
    private Set<RoleEntity> roles;
    private Set<RecipeEntity> recipes;
    private Set<ReviewEntity> reviews;

    @Column(nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;

        return this;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;

        return this;
    }

    @Column(nullable = false)
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;

        return this;
    }

    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Column(nullable = false)
    public LocalDate getCreated() {
        return created;
    }

    public UserEntity setCreated(LocalDate created) {
        this.created = created;

        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    @OneToMany(mappedBy = "author")
    public Set<RecipeEntity> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<RecipeEntity> recipes) {
        this.recipes = recipes;
    }

    @OneToMany(mappedBy = "author")
    public Set<ReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(Set<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    @PrePersist
    public void beforeCreate() {
        this.setCreated(LocalDate.now());
    }
}
