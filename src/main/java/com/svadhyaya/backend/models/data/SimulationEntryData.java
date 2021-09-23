package com.svadhyaya.backend.models.data;

public class SimulationEntryData {

    private long simulationEntryId;
    private int roundId;
    private int iterationId;
    private QuestionParametersData questionParameters;
    private RoundResultDetailsData roundResultDetails;

    public long getSimulationEntryId() {
        return simulationEntryId;
    }

    public void setSimulationEntryId(long simulationEntryId) {
        this.simulationEntryId = simulationEntryId;
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

    public QuestionParametersData getQuestionParameters() {
        return questionParameters;
    }

    public void setQuestionParameters(QuestionParametersData questionParameters) {
        this.questionParameters = questionParameters;
    }

    public RoundResultDetailsData getRoundResultDetails() {
        return roundResultDetails;
    }

    public void setRoundResultDetails(RoundResultDetailsData roundResultDetails) {
        this.roundResultDetails = roundResultDetails;
    }
}
