package com.bedfordshire.helpmebackend.model;

import javax.persistence.*;

/**
 * @author Lakitha Prabudh
 */
@Entity
@Table(name = "help_type")
public class HelpTypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String uuid;
    private String name;
    private boolean valid;

    public HelpTypeModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "HelpTypeModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", valid=" + valid +
                '}';
    }
}
