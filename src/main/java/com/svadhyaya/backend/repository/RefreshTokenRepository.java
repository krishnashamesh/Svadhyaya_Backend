package com.svadhyaya.backend.repository;

import com.svadhyaya.backend.db.models.RefreshTokenModel;
import com.svadhyaya.backend.repository.template.SvadhyayaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends SvadhyayaRepository<RefreshTokenModel, Long> {

    Optional<RefreshTokenModel> findById(Long id);

    Optional<RefreshTokenModel> findByToken(String token);
}
