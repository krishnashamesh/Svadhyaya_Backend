package com.svadhyaya.backend.db.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "simulation")
public class SimulationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long simulationId;

    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private UserModel user;

    private String simulationType;

    @OneToMany(targetEntity = SimulationEntryModel.class, mappedBy = "simulation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SimulationEntryModel> simulationEntries = new ArrayList<>();

    public SimulationModel() {
    }

    public SimulationModel(UserModel user, String simulationType) {
        this.user = user;
        this.simulationType = simulationType;
    }

    public List<SimulationEntryModel> getSimulationEntries() {
        return simulationEntries;
    }

    public void setSimulationEntries(List<SimulationEntryModel> simulationEntries) {
        this.simulationEntries = simulationEntries;
    }

    public long getSimulationId() {
        return simulationId;
    }

    public void setSimulationId(long simulationId) {
        this.simulationId = simulationId;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getSimulationType() {
        return simulationType;
    }

    public void setSimulationType(String simulationType) {
        this.simulationType = simulationType;
    }
}
