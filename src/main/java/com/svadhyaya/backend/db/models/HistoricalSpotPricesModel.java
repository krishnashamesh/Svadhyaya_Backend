package com.svadhyaya.backend.db.models;

import javax.persistence.*;

@Entity
@Table(name = "historicalSpotPrices")
public class HistoricalSpotPricesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long historicalSpotPricesId;
    //TODO Find a way to get Historical Spot Prices on the fly

}
