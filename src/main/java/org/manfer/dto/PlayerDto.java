package org.manfer.dto;

import java.util.Objects;
import org.springframework.data.annotation.Id;

/**
 * This is a simple POJO object to be persisted.
 */
public class PlayerDto implements Dto {

    @Id
    private String id;
   
    private String name;
    private String surname;
    private int scored_Goals;
    private int faults_Committed;
    private int faults_Received;

    
    public PlayerDto(String name, String surname){
        this.name = name;
        this.surname = surname;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getScored_Goals() {
        return scored_Goals;
    }

    public void setScored_Goals(int scored_Goals) {
        this.scored_Goals = scored_Goals;
    }

    public int getFaults_Committed() {
        return faults_Committed;
    }

    public void setFaults_Committed(int faults_Committed) {
        this.faults_Committed = faults_Committed;
    }

    public int getFaults_Received() {
        return faults_Received;
    }

    public void setFaults_Received(int faults_Received) {
        this.faults_Received = faults_Received;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlayerDto other = (PlayerDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PlayerDto{" + "id=" + id + ", name=" + name + ", surname=" + surname
                + ", scored_Goals=" + scored_Goals + ", faults_Committed=" + faults_Committed
                + ", faults_Received=" + faults_Received + '}';
    }

}
