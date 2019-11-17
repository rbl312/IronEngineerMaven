package com.COEN174.IronEngineer.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.print.attribute.standard.PrintQuality;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Team")
public class Team {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "team_id")
    private Integer teamId;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "size")
    private Integer size;

    @Column(name = "approved")
    private Integer approved;

    @OneToMany(mappedBy = "teamIdFK")
    private Set<Competitor> competitors;

    public Team(String teamName, Competitor competitor) {
        this.teamName = teamName;
        this.size=0;
        this.approved=0;
        this.competitors = new HashSet<>();
        this.competitors.add(competitor);
    }

    public Team(){}

    public boolean addTeamMember(Competitor competitor){
        if(competitors == null) {
            this.competitors = new HashSet<>();
        }

        else if(competitors.size() >=3){
            return false;
        }

        this.competitors.add(competitor);
        return true;
    }

    public boolean removeTeamMember(Competitor competitor){
        if(this.competitors == null || !this.competitors.contains(competitor)){
            return false;
        }
        else{
            this.competitors.remove(competitor);
            this.size--;
            return true;
        }
    }

    public Set<Competitor> getTeamMembers(){
        return this.competitors;
    }

    // TODO
    // Make this method generic so it can be reused for running and swimming
    public float getDistanceBiked(){
        if(competitors == null)
            return 0;
        int totalDistanceBiked = 0;
        for (Competitor competitor : competitors) {
            totalDistanceBiked += competitor.getDistanceBiked();
        }
        return totalDistanceBiked;
    }
    public float getDistanceRan(){
        if(competitors == null)
            return 0;
        int totalDistanceRan = 0;
        for (Competitor competitor: competitors) {
            totalDistanceRan += competitor.getDistanceRan();
        }
        return totalDistanceRan;
    }
    public float getDistanceSwam(){
        if(competitors == null)
            return 0;
        int totalDistanceSwam = 0;
        for (Competitor competitor : competitors) {
            totalDistanceSwam += competitor.getDistanceSwam();
        }
        return totalDistanceSwam;
    }
}
