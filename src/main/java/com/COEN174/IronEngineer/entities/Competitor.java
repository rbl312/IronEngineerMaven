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
    private Integer id;

    private String name;

    private String email;

    private String password;

    @OneToMany(mappedBy = "competitorId")
    private Set<Team> team;

    /** All distance values stored in miles */

    private Integer distanceRan;

    private Integer distanceSwam;

    private Integer distanceBiked;

//    public boolean isOnTeam(){
//        if()
//    }
}
