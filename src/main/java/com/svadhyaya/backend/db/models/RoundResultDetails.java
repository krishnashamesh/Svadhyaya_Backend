package com.svadhyaya.backend.db.models;

import javax.persistence.*;

@Entity
@Table(name = "roundResultDetails")
public class RoundResultDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roundResultDetailsId;

    private String entryOwner;

    private Double orderedQuantity;

    private Double revenue;
    private Double cost;
    private Double profitOrLoss;

    public RoundResultDetails() {
    }
}
