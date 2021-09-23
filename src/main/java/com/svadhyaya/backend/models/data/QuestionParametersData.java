package com.svadhyaya.backend.models.data;

import java.math.BigDecimal;

public class QuestionParametersData {

    private long questionId;
    private BigDecimal wholeSalePrice;
    private BigDecimal retailPrice;
    private BigDecimal salvagePrice;
    private BigDecimal meanQuantity;
    private BigDecimal varianceQuantity;
    private BigDecimal costPerUnit;

    private BigDecimal unitOptionsPrice;
    private BigDecimal unitOptionsExercisePrice;

    private BigDecimal revenueSharingPercentage;

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public BigDecimal getWholeSalePrice() {
        return wholeSalePrice;
    }

    public void setWholeSalePrice(BigDecimal wholeSalePrice) {
        this.wholeSalePrice = wholeSalePrice;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getSalvagePrice() {
        return salvagePrice;
    }

    public void setSalvagePrice(BigDecimal salvagePrice) {
        this.salvagePrice = salvagePrice;
    }

    public BigDecimal getMeanQuantity() {
        return meanQuantity;
    }

    public void setMeanQuantity(BigDecimal meanQuantity) {
        this.meanQuantity = meanQuantity;
    }

    public BigDecimal getVarianceQuantity() {
        return varianceQuantity;
    }

    public void setVarianceQuantity(BigDecimal varianceQuantity) {
        this.varianceQuantity = varianceQuantity;
    }

    public BigDecimal getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(BigDecimal costPerUnit) {
        this.costPerUnit = costPerUnit;
    }

    public BigDecimal getUnitOptionsPrice() {
        return unitOptionsPrice;
    }

    public void setUnitOptionsPrice(BigDecimal unitOptionsPrice) {
        this.unitOptionsPrice = unitOptionsPrice;
    }

    public BigDecimal getUnitOptionsExercisePrice() {
        return unitOptionsExercisePrice;
    }

    public void setUnitOptionsExercisePrice(BigDecimal unitOptionsExercisePrice) {
        this.unitOptionsExercisePrice = unitOptionsExercisePrice;
    }

    public BigDecimal getRevenueSharingPercentage() {
        return revenueSharingPercentage;
    }

    public void setRevenueSharingPercentage(BigDecimal revenueSharingPercentage) {
        this.revenueSharingPercentage = revenueSharingPercentage;
    }
}
