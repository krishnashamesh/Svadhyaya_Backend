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

        SimulationEntryModel simulationEntry = new SimulationEntryModel();
        simulationEntry.setSimulation(simulation);

        RoundResultDetailsModel roundResultDetailsModel =
                new RoundResultDetailsModel();

        QuestionParametersModel questionParameters = new QuestionParametersModel();
        questionParameters.setSimulationType(simulationType);
        //TODO set questions here
        simulationEntry.setQuestionParameters(questionParameters);
        simulationEntry.setRoundResultDetails(roundResultDetailsModel);
        simulationEntry.setRoundId(1);
        simulationEntry.setIterationId(1);


        questionParametersRepository.saveAndFlush(questionParameters);
        roundResultDetailsRepository.saveAndFlush(roundResultDetailsModel);
        simulationRepository.saveAndFlush(simulation);
        simulationEntryRepository.saveAndFlush(simulationEntry);

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
}
