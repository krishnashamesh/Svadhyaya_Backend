package com.svadhyaya.backend.populator;

import com.svadhyaya.backend.db.models.SimulationEntryModel;
import com.svadhyaya.backend.db.models.SimulationModel;
import com.svadhyaya.backend.models.data.SimulationData;
import com.svadhyaya.backend.models.data.SimulationEntryData;
import com.svadhyaya.backend.populator.template.Populator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimulationPopulator implements Populator<SimulationModel, SimulationData> {

    @Autowired
    private SimulationEntryPopulator simulationEntryPopulator;

    @Override
    public void populate(SimulationModel simulationModel, SimulationData simulationData) {

        simulationData.setSimulationId(simulationModel.getSimulationId());

        List<SimulationEntryModel> simulationEntries = simulationModel.getSimulationEntries();
        if (!CollectionUtils.isEmpty(simulationEntries)) {

            for (SimulationEntryModel simulationEntryModel : simulationEntries) {
                SimulationEntryData simulationEntryData =
                        new SimulationEntryData();
                simulationEntryPopulator.populate(simulationEntryModel, simulationEntryData);
                List<SimulationEntryData> simulationEntryDataList =
                        CollectionUtils.isEmpty(simulationData.getSimulationEntries()) ? new ArrayList<>() :
                                new ArrayList<>(simulationData.getSimulationEntries());
                simulationEntryDataList.add(simulationEntryData);
                simulationData.setSimulationEntries(simulationEntryDataList);
            }
        }
    }
}
