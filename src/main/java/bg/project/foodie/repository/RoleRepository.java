package bg.project.foodie.repository;

import bg.project.foodie.model.entity.RoleEntity;
import bg.project.foodie.model.entity.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(RoleNameEnum roleName);
}
