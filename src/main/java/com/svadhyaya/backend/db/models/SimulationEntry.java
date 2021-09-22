package com.svadhyaya.backend.db.models;

import javax.persistence.*;

@Entity
@Table(name = "simulationEntry")
public class SimulationEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long simulationEntryId;

    @ManyToOne
    @JoinColumn(name = "simulationId")
    private Simulation simulation;

    private int roundId;

    private int iterationId;

    @OneToOne
    @JoinColumn(name = "questionId", referencedColumnName = "questionId")
    private QuestionParameters questionParameters;

    @OneToOne
    @JoinColumn(name = "roundResultDetailsId", referencedColumnName = "roundResultDetailsId")
    private RoundResultDetails roundResultDetails;

    public SimulationEntry() {
    }

    public long getSimulationEntryId() {
        return simulationEntryId;
    }

    public void setSimulationEntryId(long simulationEntryId) {
        this.simulationEntryId = simulationEntryId;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getIterationId() {
        return iterationId;
    }

    public void setIterationId(int iterationId) {
        this.iterationId = iterationId;
    }

    public QuestionParameters getQuestionParameters() {
        return questionParameters;
    }

    public void setQuestionParameters(QuestionParameters questionParameters) {
        this.questionParameters = questionParameters;
    }
}
