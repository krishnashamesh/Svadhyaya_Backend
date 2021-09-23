package com.svadhyaya.backend.db.models;

import javax.persistence.*;

@Entity
@Table(name = "simulationEntry")
public class SimulationEntryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long simulationEntryId;

    @ManyToOne
    @JoinColumn(name = "simulationId")
    private SimulationModel simulation;

    private int roundId;

    private int iterationId;

    @OneToOne
    @JoinColumn(name = "questionId", referencedColumnName = "questionId")
    private QuestionParametersModel questionParameters;

    @OneToOne
    @JoinColumn(name = "roundResultDetailsId", referencedColumnName = "roundResultDetailsId")
    private RoundResultDetailsModel roundResultDetails;

    public SimulationEntryModel() {
    }

    public long getSimulationEntryId() {
        return simulationEntryId;
    }

    public void setSimulationEntryId(long simulationEntryId) {
        this.simulationEntryId = simulationEntryId;
    }

    public SimulationModel getSimulation() {
        return simulation;
    }

    public void setSimulation(SimulationModel simulation) {
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

    public QuestionParametersModel getQuestionParameters() {
        return questionParameters;
    }

    public void setQuestionParameters(QuestionParametersModel questionParameters) {
        this.questionParameters = questionParameters;
    }

    public RoundResultDetailsModel getRoundResultDetails() {
        return roundResultDetails;
    }

    public void setRoundResultDetails(RoundResultDetailsModel roundResultDetails) {
        this.roundResultDetails = roundResultDetails;
    }
}
