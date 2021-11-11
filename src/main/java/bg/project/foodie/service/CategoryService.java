package bg.project.foodie.service;

import bg.project.foodie.model.entity.CategoryEntity;
import bg.project.foodie.model.entity.enums.CategoryNameEnum;

public interface CategoryService {
    void initializeCategories();

    CategoryEntity findByName(CategoryNameEnum category);
}
