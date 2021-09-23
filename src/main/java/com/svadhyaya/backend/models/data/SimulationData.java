package com.svadhyaya.backend.models.data;

import java.util.List;

public class SimulationData {

    private long simulationId;

    private List<SimulationEntryData> simulationEntries;

    public SimulationData() {
    }

    public SimulationData(long simulationId,
                          List<SimulationEntryData> simulationEntries) {
        this.simulationId = simulationId;
        this.simulationEntries = simulationEntries;
    }

    public long getSimulationId() {
        return simulationId;
    }

    public void setSimulationId(long simulationId) {
        this.simulationId = simulationId;
    }

    public List<SimulationEntryData> getSimulationEntries() {
        return simulationEntries;
    }

    public void setSimulationEntries(List<SimulationEntryData> simulationEntries) {
        this.simulationEntries = simulationEntries;
    }
}
