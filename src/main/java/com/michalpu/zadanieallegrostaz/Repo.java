package com.michalpu.zadanieallegrostaz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repo implements Comparable{

    private String name;
    private String updated_at;

    public Repo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public int compareTo(Object o) {
        return this.getUpdated_at().compareTo(((Repo)o).getUpdated_at());
    }

    @Override
    public String toString() {
        return "Repo{" +
                "name='" + name + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
