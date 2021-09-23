package com.svadhyaya.backend.repository;

import com.svadhyaya.backend.db.models.RoleModel;
import com.svadhyaya.backend.repository.template.SvadhyayaRepository;

public interface RoleRepository extends SvadhyayaRepository<RoleModel, Long> {
    RoleModel findByName(String name);
}
