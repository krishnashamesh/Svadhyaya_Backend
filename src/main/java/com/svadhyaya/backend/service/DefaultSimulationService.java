package com.svadhyaya.backend.service;

import com.svadhyaya.backend.db.models.*;
import com.svadhyaya.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultSimulationService {

    @Autowired
    private DefaultUserDetailsService userDetailsService;

    @Autowired
    private DefaultQuestionParametersService questionParametersService;

    @Autowired
    private SimulationRepository simulationRepository;

    @Autowired
    private SimulationEntryRepository simulationEntryRepository;

    @Autowired
    private QuestionParametersRepository questionParametersRepository;

    @Autowired
    private RoundResultDetailsRepository roundResultDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    public SimulationModel createSimulation(String username, String simulationType) {

        UserModel user = userDetailsService.getUserFromRepoWithName(username);

        SimulationModel simulation = new SimulationModel(user, simulationType);

        simulationRepository.saveAndFlush(simulation);
        simulationRepository.refresh(simulation);

        return simulation;
    }

    public SimulationModel getLatestSimulationForUser(String username, String simulationType) {
        UserModel user = userRepository.findByUsername(username);

        List<SimulationModel> simulationList = simulationRepository.findByUserOrderBySimulationIdDesc(user);
        if (!CollectionUtils.isEmpty(simulationList)) {
            return simulationList.get(0);
        } else {
            return createSimulation(username, simulationType);
        }
    }

    public SimulationModel getSimulationModelById(String simulationId) {
        return simulationRepository.findById(Long.valueOf(simulationId)).get();
    }

    public SimulationModel createNewRound(SimulationModel simulation) {

        SimulationEntryModel simulationEntry = new SimulationEntryModel();
        simulationEntry.setSimulation(simulation);

        QuestionParametersModel questionParameters = new QuestionParametersModel();
        questionParameters.setSimulationType(simulation.getSimulationType());
        simulationEntry.setQuestionParameters(questionParameters);
        simulationEntry.setRoundResultDetailsModels(new ArrayList<RoundResultDetailsModel>());

        List<SimulationEntryModel> simulationEntries = simulationEntry.getSimulation().getSimulationEntries();
        if (CollectionUtils.isEmpty(simulationEntries) || (simulationEntries.get(simulationEntries.size() - 1).getRoundId() == 0)) {
            simulationEntry.setRoundId(1);
        } else {
            simulationEntry.setRoundId((simulationEntries.get(simulationEntries.size() - 1).getRoundId()) + 1);
        }
        questionParameters =
                questionParametersService.setQuestionParameters(questionParameters);

        questionParametersRepository.saveAndFlush(questionParameters);
        simulationRepository.saveAndFlush(simulation);
        simulationEntryRepository.saveAndFlush(simulationEntry);

        simulationRepository.refresh(simulation);
        return simulation;
    }
}
