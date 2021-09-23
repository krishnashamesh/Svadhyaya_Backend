package com.svadhyaya.backend.db.models;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "refreshtoken")
public class RefreshTokenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private UserModel user;

    public RefreshTokenModel() {
    }

    public RefreshTokenModel(UserModel user, String token, Instant expiryDate) {
        this.user = user;
        this.token = token;
        this.expiryDate = expiryDate;
    }

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }
}
