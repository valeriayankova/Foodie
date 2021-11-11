package bg.project.foodie.service;

import bg.project.foodie.model.entity.RoleEntity;
import bg.project.foodie.model.entity.enums.RoleNameEnum;

public interface RoleService {
    void initializeRoles();

    RoleEntity findByRoleName(RoleNameEnum admin);
}
