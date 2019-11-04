package com.COEN174.IronEngineer.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "competitor")
public class Competitor {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private String email;

    private Integer teamId;

    /** All distance values stored in miles */

    private Integer distanceRan;

    private Integer distanceSwam;

    private Integer distanceBiked;

    public boolean isOnTeam(){
        return teamId != null;
    }

}
