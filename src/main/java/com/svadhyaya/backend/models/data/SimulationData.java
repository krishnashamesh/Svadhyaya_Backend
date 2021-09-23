package com.svadhyaya.backend.models.data;

import java.util.List;

public class SimulationData {

    private long simulationId;

    private boolean populateAllEntries;

    private List<SimulationEntryData> simulationEntries;

    public SimulationData() {
        this.populateAllEntries = false;
    }

    public SimulationData(long simulationId,
                          List<SimulationEntryData> simulationEntries) {
        this.simulationId = simulationId;
        this.simulationEntries = simulationEntries;
        this.populateAllEntries = false;

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

    public boolean isPopulateAllEntries() {
        return populateAllEntries;
    }

    public void setPopulateAllEntries(boolean populateAllEntries) {
        this.populateAllEntries = populateAllEntries;
    }
}
