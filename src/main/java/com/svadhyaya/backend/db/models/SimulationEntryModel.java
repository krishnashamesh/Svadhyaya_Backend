package com.svadhyaya.backend.db.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToOne
    @JoinColumn(name = "questionId", referencedColumnName = "questionId")
    private QuestionParametersModel questionParameters;

    @OneToMany(targetEntity = RoundResultDetailsModel.class, mappedBy =
            "simulationEntry", cascade = CascadeType.ALL, fetch =
            FetchType.LAZY)
    private List<RoundResultDetailsModel> RoundResultDetailsModels =
            new ArrayList<>();

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


    public QuestionParametersModel getQuestionParameters() {
        return questionParameters;
    }

    public void setQuestionParameters(QuestionParametersModel questionParameters) {
        this.questionParameters = questionParameters;
    }

    public List<RoundResultDetailsModel> getRoundResultDetailsModels() {
        return RoundResultDetailsModels;
    }

    public void setRoundResultDetailsModels(List<RoundResultDetailsModel> roundResultDetailsModels) {
        RoundResultDetailsModels = roundResultDetailsModels;
    }
}
