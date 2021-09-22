package com.svadhyaya.backend.db.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "simulation")
public class Simulation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long simulationId;

    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    private String simulationType;

    @OneToMany(targetEntity = SimulationEntry.class, mappedBy = "simulation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SimulationEntry> simulationEntries = new ArrayList<>();

    public Simulation() {
    }

    public Simulation(User user, String simulationType) {
        this.user = user;
        this.simulationType = simulationType;
    }

    public long getSimulationId() {
        return simulationId;
    }

    public void setSimulationId(long simulationId) {
        this.simulationId = simulationId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSimulationType() {
        return simulationType;
    }

    public void setSimulationType(String simulationType) {
        this.simulationType = simulationType;
    }
}
