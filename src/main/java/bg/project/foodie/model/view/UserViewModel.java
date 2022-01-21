package bg.project.foodie.model.view;

import bg.project.foodie.model.entity.*;

import java.time.LocalDate;
import java.util.*;

public class UserViewModel {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate created;
    private int countOfRecipes;
    private int countOfReviews;
    private Set<RoleEntity> roles;
    boolean isAdmin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public int getCountOfRecipes() {
        return countOfRecipes;
    }

    public void setCountOfRecipes(int countOfRecipes) {
        this.countOfRecipes = countOfRecipes;
    }

    public int getCountOfReviews() {
        return countOfReviews;
    }

    public void setCountOfReviews(int countOfReviews) {
        this.countOfReviews = countOfReviews;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
