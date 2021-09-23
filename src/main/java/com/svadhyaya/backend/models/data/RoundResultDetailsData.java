package com.svadhyaya.backend.models.data;

import java.math.BigDecimal;

public class RoundResultDetailsData {

    private long roundResultDetailsId;

    private BigDecimal actualDemand;

    private BigDecimal orderedQuantity;
    private BigDecimal revenue;
    private BigDecimal cost;
    private BigDecimal profitOrLoss;

    private BigDecimal idealOrderedQuantity;
    private BigDecimal idealRevenue;
    private BigDecimal idealCost;
    private BigDecimal idealProfitOrLoss;

    public long getRoundResultDetailsId() {
        return roundResultDetailsId;
    }

    public void setRoundResultDetailsId(long roundResultDetailsId) {
        this.roundResultDetailsId = roundResultDetailsId;
    }

    public BigDecimal getActualDemand() {
        return actualDemand;
    }

    public void setActualDemand(BigDecimal actualDemand) {
        this.actualDemand = actualDemand;
    }

    public BigDecimal getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(BigDecimal orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getProfitOrLoss() {
        return profitOrLoss;
    }

    public void setProfitOrLoss(BigDecimal profitOrLoss) {
        this.profitOrLoss = profitOrLoss;
    }

    public BigDecimal getIdealOrderedQuantity() {
        return idealOrderedQuantity;
    }

    public void setIdealOrderedQuantity(BigDecimal idealOrderedQuantity) {
        this.idealOrderedQuantity = idealOrderedQuantity;
    }

    public BigDecimal getIdealRevenue() {
        return idealRevenue;
    }

    public void setIdealRevenue(BigDecimal idealRevenue) {
        this.idealRevenue = idealRevenue;
    }

    public BigDecimal getIdealCost() {
        return idealCost;
    }

    public void setIdealCost(BigDecimal idealCost) {
        this.idealCost = idealCost;
    }

    public BigDecimal getIdealProfitOrLoss() {
        return idealProfitOrLoss;
    }

    public void setIdealProfitOrLoss(BigDecimal idealProfitOrLoss) {
        this.idealProfitOrLoss = idealProfitOrLoss;
    }
}
