package com.svadhyaya.backend.service;

import com.svadhyaya.backend.db.models.Role;
import com.svadhyaya.backend.db.models.enums.RolesEnum;
import com.svadhyaya.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultRoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RolesEnum[] createRoles() {
        RolesEnum[] rolesEnums = RolesEnum.values();
        for (RolesEnum roleEnum :
                rolesEnums) {
            Role role = new Role(Long.valueOf(roleEnum.hashCode()), roleEnum.name());
            roleRepository.save(role);
        }
        return rolesEnums;
    }

    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
