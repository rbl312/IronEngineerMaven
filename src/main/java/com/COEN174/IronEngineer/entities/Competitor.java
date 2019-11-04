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
    private Integer competitorId;

    private String name;

    private String email;

    /** All distance values stored in miles */

    private Float distanceRan;

    private Float distanceSwam;

    private Float distanceBiked;

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
