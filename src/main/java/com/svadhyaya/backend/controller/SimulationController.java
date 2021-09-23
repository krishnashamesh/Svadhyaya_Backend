package com.svadhyaya.backend.controller;

import com.svadhyaya.backend.db.models.SimulationModel;
import com.svadhyaya.backend.db.models.enums.SimulationTypeEnum;
import com.svadhyaya.backend.models.data.ErrorResponseData;
import com.svadhyaya.backend.models.data.SimulationData;
import com.svadhyaya.backend.populator.SimulationPopulator;
import com.svadhyaya.backend.service.DefaultSimulationService;
import com.svadhyaya.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

@RestController
public class SimulationController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private DefaultSimulationService simulationService;

    @Autowired
    private SimulationPopulator simulationPopulator;

    @PostMapping("/createSimulation")
    public ResponseEntity<?> createSimulation(HttpServletRequest request, HttpServletResponse response) {

        String username = jwtUtil.getUserNamefromRequest(request);

        SimulationModel simulation = simulationService.createSimulation(username, SimulationTypeEnum.LONG_TERM.name());

        SimulationData simulationData = new SimulationData();
        simulationPopulator.populate(simulation, simulationData);

        return ResponseEntity.ok(simulationData);
    }

    @PostMapping("/latestSimulation")
    public ResponseEntity<?> latestSimulation(HttpServletRequest request, HttpServletResponse response, String simulationType) {

        String username = jwtUtil.getUserNamefromRequest(request);
        Optional<SimulationTypeEnum> simulationTypeOpt = Arrays.stream(SimulationTypeEnum.values()).filter(simType -> simType.name().equalsIgnoreCase(simulationType)).findAny();

        if (!simulationTypeOpt.isPresent()) {
            return ResponseEntity.badRequest().body(new ErrorResponseData(simulationType + " not found"));
        }
        SimulationModel simulation = simulationService.getLatestSimulationForUser(username, simulationTypeOpt.get().name());

        SimulationData simulationData = new SimulationData();
        simulationPopulator.populate(simulation, simulationData);

        return ResponseEntity.ok(simulationData);

    }

}
