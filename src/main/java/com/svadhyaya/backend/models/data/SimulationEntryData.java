package com.svadhyaya.backend.models.data;

import java.util.List;

public class SimulationEntryData {

    private long simulationEntryId;
    private int roundId;
    private QuestionParametersData questionParameters;
    private List<RoundResultDetailsData> roundResultDetailsDataList;

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


    public QuestionParametersData getQuestionParameters() {
        return questionParameters;
    }

    public void setQuestionParameters(QuestionParametersData questionParameters) {
        this.questionParameters = questionParameters;
    }

    public List<RoundResultDetailsData> getRoundResultDetailsDataList() {
        return roundResultDetailsDataList;
    }

    public void setRoundResultDetailsDataList(List<RoundResultDetailsData> roundResultDetailsDataList) {
        this.roundResultDetailsDataList = roundResultDetailsDataList;
    }
}
