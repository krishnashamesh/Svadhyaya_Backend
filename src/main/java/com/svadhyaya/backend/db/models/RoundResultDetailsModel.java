package com.svadhyaya.backend.db.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "roundResultDetails")
public class RoundResultDetailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public RoundResultDetailsModel() {
        orderedQuantity = revenue = cost = profitOrLoss = actualDemand = idealCost
                = idealOrderedQuantity = idealRevenue = idealProfitOrLoss =
                BigDecimal.ZERO;
    }

    public long getRoundResultDetailsId() {
        return roundResultDetailsId;
    }

    public void setRoundResultDetailsId(long roundResultDetailsId) {
        this.roundResultDetailsId = roundResultDetailsId;
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

    public BigDecimal getActualDemand() {
        return actualDemand;
    }

    public void setActualDemand(BigDecimal actualDemand) {
        this.actualDemand = actualDemand;
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
