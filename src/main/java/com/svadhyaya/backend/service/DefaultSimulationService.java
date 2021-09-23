package com.svadhyaya.backend.service;

import com.svadhyaya.backend.db.models.*;
import com.svadhyaya.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    public SimulationModel setParametersForNextQuestion(SimulationModel simulation) {

        List<SimulationEntryModel> simulationEntryModels = simulation.getSimulationEntries();
        SimulationEntryModel simulationEntry = new SimulationEntryModel();
        simulationEntry.setSimulation(simulation);

        RoundResultDetailsModel roundResultDetailsModel =
                new RoundResultDetailsModel();
        roundResultDetailsRepository.saveAndFlush(roundResultDetailsModel);

        QuestionParametersModel questionParameters = new QuestionParametersModel();
        questionParameters.setSimulationType(simulation.getSimulationType());
        //TODO set questions here
        simulationEntry.setQuestionParameters(questionParameters);
        simulationEntry.setRoundResultDetails(roundResultDetailsModel);
        List<SimulationEntryModel> simulationEntries = simulationEntry.getSimulation().getSimulationEntries();
        if (CollectionUtils.isEmpty(simulationEntries) || (simulationEntries.get(simulationEntries.size() - 1).getRoundId() == 0)) {
            simulationEntry.setRoundId(1);
            simulationEntry.setIterationId(1);
        } else {
            simulationEntry.setRoundId(simulationEntry.getRoundId());
            simulationEntry.setIterationId(simulationEntries.get(simulationEntries.size() - 1).getIterationId() + 1);
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
