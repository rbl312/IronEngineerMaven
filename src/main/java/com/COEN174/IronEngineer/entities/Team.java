package com.COEN174.IronEngineer.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.print.attribute.standard.PrintQuality;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Team")
public class Team {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer teamId;

    private String name;

    private Integer size;

    @ManyToOne(fetch=FetchType.LAZY,optional = true)
    @JoinColumn(name = "id",nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private Competitor competitorId;

    @ElementCollection
    private List<Integer> memberIds;

    public Team(Integer id, String name, Integer size, List<Integer> memberIds) {
        this.teamId = id;
        this.name = name;
        this.size = 0;
        this.memberIds = memberIds;
    }

    public Team(){}

    public boolean addTeamMember(Integer id){
        if(this.size >=3){
            return false;
        }

        this.memberIds.add(id);
        this.size++;
        return true;
    }
    public List<Integer> getTeamMembers(){
        return this.memberIds;
    }

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
