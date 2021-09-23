package com.svadhyaya.backend.db.models;

import javax.persistence.*;

@Entity
@Table(name = "questionParameters")
public class QuestionParametersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long questionId;

    private String simulationType;

    private Double wholeSalePrice;
    private Double retailPrice;
    private Double salvagePrice;
    private Double meanQuantity;
    private Double varianceQuantity;
    private Double costPerUnit;

    private Double actualDemand;

    //TODO Figure out how to map this
    //@OneToMany
    //private List<HistoricalSpotPricesModel> historicalSpotPrices;

    private Double unitOptionsPrice;
    private Double unitOptionsExercisePrice;

    private Double revenueSharingPercentage;

    public QuestionParametersModel() {
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getSimulationType() {
        return simulationType;
    }

    public void setSimulationType(String simulationType) {
        this.simulationType = simulationType;
    }

    public Double getWholeSalePrice() {
        return wholeSalePrice;
    }

    public void setWholeSalePrice(Double wholeSalePrice) {
        this.wholeSalePrice = wholeSalePrice;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Double getSalvagePrice() {
        return salvagePrice;
    }

    public void setSalvagePrice(Double salvagePrice) {
        this.salvagePrice = salvagePrice;
    }

    public Double getMeanQuantity() {
        return meanQuantity;
    }

    public void setMeanQuantity(Double meanQuantity) {
        this.meanQuantity = meanQuantity;
    }

    public Double getVarianceQuantity() {
        return varianceQuantity;
    }

    public void setVarianceQuantity(Double varianceQuantity) {
        this.varianceQuantity = varianceQuantity;
    }

    public Double getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(Double costPerUnit) {
        this.costPerUnit = costPerUnit;
    }

    public Double getUnitOptionsPrice() {
        return unitOptionsPrice;
    }

    public void setUnitOptionsPrice(Double unitOptionsPrice) {
        this.unitOptionsPrice = unitOptionsPrice;
    }

    public Double getUnitOptionsExercisePrice() {
        return unitOptionsExercisePrice;
    }

    public void setUnitOptionsExercisePrice(Double unitOptionsExercisePrice) {
        this.unitOptionsExercisePrice = unitOptionsExercisePrice;
    }

    public Double getRevenueSharingPercentage() {
        return revenueSharingPercentage;
    }

    public void setRevenueSharingPercentage(Double revenueSharingPercentage) {
        this.revenueSharingPercentage = revenueSharingPercentage;
    }
}
