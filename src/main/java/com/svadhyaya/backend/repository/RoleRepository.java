package com.svadhyaya.backend.repository;

import com.svadhyaya.backend.db.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
