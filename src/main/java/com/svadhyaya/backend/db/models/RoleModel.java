package com.svadhyaya.backend.db.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;


@Entity
@Table(name = "role")
public class RoleModel {
    @Id
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<UserModel> users;

    public RoleModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserModel> getUsers() {
        return users;
    }

    public void setUsers(Set<UserModel> users) {
        this.users = users;
    }
}
