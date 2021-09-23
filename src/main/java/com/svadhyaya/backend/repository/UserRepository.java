package com.svadhyaya.backend.repository;

import com.svadhyaya.backend.db.models.UserModel;
import com.svadhyaya.backend.repository.template.SvadhyayaRepository;

public interface UserRepository extends SvadhyayaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}
