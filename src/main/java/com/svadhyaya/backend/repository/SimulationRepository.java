package com.svadhyaya.backend.repository;

import com.svadhyaya.backend.db.models.SimulationModel;
import com.svadhyaya.backend.db.models.UserModel;
import com.svadhyaya.backend.repository.template.SvadhyayaRepository;

import java.util.List;

public interface SimulationRepository extends SvadhyayaRepository<SimulationModel, Long> {
    List<SimulationModel> findByUserOrderBySimulationIdDesc(UserModel user);
}
