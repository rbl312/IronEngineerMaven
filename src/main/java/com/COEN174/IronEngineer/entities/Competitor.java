package com.COEN174.IronEngineer.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
//@Table(name = "competitor")
public class Competitor {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "competitor_id")
    private Integer competitorId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "team_id")
    private Integer teamIdFK;

    @Column(name = "admin")
    private Integer admin;

    /** All distance values stored in miles */

    @Column(name = "distance_ran")
    private Float distanceRan;

    @Column(name = "distance_swam")
    private Float distanceSwam;

    @Column(name = "distance_biked")
    private Float distanceBiked;

    public Competitor(String name, String email){
        this.name = name;
        this.email = email;
        this.distanceRan = 0.0f;
        this.distanceBiked = 0.0f;
        this.distanceSwam = 0.0f;
    }

    public Competitor(){}

    public float getDistanceRan(){
        if(distanceRan == null)
            return 0.0f;
        return distanceRan;
    }
    public float getDistanceBiked(){
        if(distanceBiked == null)
            return 0.0f;
        return distanceBiked;
    }
    public float getDistanceSwam(){
        if(distanceSwam == null)
            return 0.0f;
        return distanceSwam;
    }
//    public boolean isOnTeam(){
//        if()
//    }

}
