package com.COEN174.IronEngineer.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Entity
@Getter
@Setter
//@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private Integer size;

    @ElementCollection
    private List<Integer> memberIds;

    public Team(Integer id, String name, Integer size, List<Integer> memberIds) {
        this.id = id;
        this.name = name;
        this.size = 0;
        this.memberIds = memberIds;
    }

    public Team(){}

    // TODO
    // Make this method generic so it can be reused for running and swimming
    public int getDistanceBiked(){
        if(memberIds == null)
            return 0;
        int totalDistanceBiked = 0;
        for (Integer memberId : memberIds) {
            //get Competitor
            //add their total to the list
        }
        return totalDistanceBiked;
    }
    public int getDistanceRan(){
        if(memberIds == null)
            return 0;
        int totalDistanceRan = 0;
        for (Integer memberId : memberIds) {
            //get Competitor
            //add their total to the list
        }
        return totalDistanceRan;
    }
    public int getDistanceSwam(){
        if(memberIds == null)
            return 0;
        int totalDistanceSwam = 0;
        for (Integer memberId : memberIds) {
            //get Competitor
            //add their total to the list
        }
        return totalDistanceSwam;
    }
}
