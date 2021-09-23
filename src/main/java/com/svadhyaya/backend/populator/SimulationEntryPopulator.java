package com.svadhyaya.backend.populator;

import com.svadhyaya.backend.db.models.RoundResultDetailsModel;
import com.svadhyaya.backend.db.models.SimulationEntryModel;
import com.svadhyaya.backend.models.data.QuestionParametersData;
import com.svadhyaya.backend.models.data.RoundResultDetailsData;
import com.svadhyaya.backend.models.data.SimulationEntryData;
import com.svadhyaya.backend.populator.template.Populator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        simulationEntryData.setRoundId(simulationEntryModel.getRoundId());

        QuestionParametersData questionParametersData =
                new QuestionParametersData();

        questionParametersPopulator.populate(simulationEntryModel.getQuestionParameters(), questionParametersData);

        List<RoundResultDetailsData> roundResultDetailsDataList =
                new ArrayList<>();
        for (RoundResultDetailsModel roundResultDetails :
                simulationEntryModel.getRoundResultDetailsModels()) {
            RoundResultDetailsData roundResultDetailsData =
                    new RoundResultDetailsData();
            roundResultDetailsPopulator.populate(roundResultDetails, roundResultDetailsData);
            roundResultDetailsDataList.add(roundResultDetailsData);
        }
        simulationEntryData.setQuestionParameters(questionParametersData);
        simulationEntryData.setRoundResultDetailsDataList(roundResultDetailsDataList);

    }
}
