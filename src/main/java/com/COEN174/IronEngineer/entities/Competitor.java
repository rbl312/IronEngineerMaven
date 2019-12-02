package com.COEN174.IronEngineer.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
// Class Name: Competitor
// Class Description: Competitor is the class used to store user information in the Iron Engineer system. All user information is stored within competitor objects including distances completed.
// Class Attributes: competitorId (Integer) used to identify competitors in the Iron Engineer system, name (String) to store the competitors name, email (String) to store competitor's email,
// teamIdFk (Integer) to store the identifying key for the team that a competitor is apart of, isAdmin (boolean) to used to differentiate between regular and administrative users.
// All completed distances are store using Floats: distanceRan, distanceSwam, distanceBiked
// Class Notes: This class corresponds to the Competitor table in the Iron Engineer database.
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

    @Column(name = "is_admin")
    private Boolean isAdmin;

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
        this.isAdmin = false;
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

    public void addDistanceSwam(float add){
        if(add + distanceSwam < 0)
            distanceSwam = 0.0f;
        else
            distanceSwam += add;
    }

    public void addDistanceBiked(float add){
        if(add + distanceBiked < 0)
            distanceBiked = 0.0f;
        else
            distanceBiked += add;
    }

    public void addDistanceRan(float add){
        if(add + distanceRan < 0)
            distanceRan = 0.0f;
        else
            distanceRan += add;
    }
}
