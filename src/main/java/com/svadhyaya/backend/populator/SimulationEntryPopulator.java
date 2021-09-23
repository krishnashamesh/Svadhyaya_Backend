package com.svadhyaya.backend.populator;

import com.svadhyaya.backend.db.models.SimulationEntryModel;
import com.svadhyaya.backend.models.data.QuestionParametersData;
import com.svadhyaya.backend.models.data.RoundResultDetailsData;
import com.svadhyaya.backend.models.data.SimulationEntryData;
import com.svadhyaya.backend.populator.template.Populator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulationEntryPopulator implements Populator<SimulationEntryModel,
        SimulationEntryData> {

    @Autowired
    private QuestionParametersPopulator questionParametersPopulator;

    @Autowired
    private RoundResultDetailsPopulator roundResultDetailsPopulator;

    @Override
    public void populate(SimulationEntryModel simulationEntryModel, SimulationEntryData simulationEntryData) {
        simulationEntryData.setSimulationEntryId(simulationEntryModel.getSimulationEntryId());

        RoundResultDetailsData roundResultDetailsData =
                new RoundResultDetailsData();

        QuestionParametersData questionParametersData =
                new QuestionParametersData();

        questionParametersPopulator.populate(simulationEntryModel.getQuestionParameters(), questionParametersData);
        roundResultDetailsPopulator.populate(simulationEntryModel.getRoundResultDetails(), roundResultDetailsData);

        simulationEntryData.setQuestionParameters(questionParametersData);
        simulationEntryData.setRoundResultDetails(roundResultDetailsData);

        simulationEntryData.setIterationId(simulationEntryModel.getIterationId());
        simulationEntryData.setRoundId(simulationEntryModel.getRoundId());
    }
}
