package com.svadhyaya.backend.db.models;

import javax.persistence.*;

@Entity
@Table(name = "roundResultDetails")
public class RoundResultDetailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roundResultDetailsId;

    private String entryOwner;

    private Double orderedQuantity;

    private Double revenue;
    private Double cost;
    private Double profitOrLoss;

    public RoundResultDetailsModel() {
    }

    public long getRoundResultDetailsId() {
        return roundResultDetailsId;
    }

    public void setRoundResultDetailsId(long roundResultDetailsId) {
        this.roundResultDetailsId = roundResultDetailsId;
    }

    public String getEntryOwner() {
        return entryOwner;
    }

    public void setEntryOwner(String entryOwner) {
        this.entryOwner = entryOwner;
    }

    public Double getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(Double orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getProfitOrLoss() {
        return profitOrLoss;
    }

    public void setProfitOrLoss(Double profitOrLoss) {
        this.profitOrLoss = profitOrLoss;
    }
}
