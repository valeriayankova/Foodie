package bg.project.foodie.service.impl;

import bg.project.foodie.model.entity.CategoryEntity;
import bg.project.foodie.model.entity.enums.CategoryNameEnum;
import bg.project.foodie.repository.CategoryRepository;
import bg.project.foodie.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initializeCategories() {
        if (categoryRepository.count() > 0) {
            return;
        }

        Arrays.stream(CategoryNameEnum.values())
                .forEach(cne -> {
                    CategoryEntity category = new CategoryEntity();
                    category.setName(cne);
                    categoryRepository.save(category);
                });
    }

    @Override
    public CategoryEntity findByName(CategoryNameEnum category) {
        return categoryRepository.findByName(category).orElse(null);
    }
}
