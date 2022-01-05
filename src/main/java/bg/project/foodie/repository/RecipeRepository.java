package bg.project.foodie.repository;

import bg.project.foodie.model.entity.RecipeEntity;
import bg.project.foodie.model.entity.enums.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    @Query(value = "SELECT * FROM recipes AS r JOIN categories AS c ON r.category_id=c.id " +
            "WHERE c.name = :category", nativeQuery = true)
    List<RecipeEntity> findAllByCategory(@Param("category") String category);

    //TODO: write query to extract the highest rated recipe!
//    @Query()
//    RecipeEntity findRecipeByHighestRating();
}
