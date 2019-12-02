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
// Class Name: Team
// Class Description: Used to represent a team within the Iron Engineer system.
// Class Attributes: teamId (Integer) used to identify the team within the Iron Engineer database, teamName (String) to store the team's name, approved (boolean) to check that the team has been approved by an administrator,
// and competitors (Set<Competitor>) used to store the competitors that are apart of the team.
// Class Methods: addTeamMember and removeTeamMember, used to add and removes competitors from a team.
public class Team {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "team_id")
    private Integer teamId;

    @Column(name = "team_name")
    private String teamName;


    @Column(name = "approved")
    private Integer approved;

    @OneToMany(mappedBy = "teamIdFK")
    private Set<Competitor> competitors;

    public Team(String teamName, Competitor competitor) {
        this.teamName = teamName;
        this.approved=0;
        this.competitors = new HashSet<>();
        this.competitors.add(competitor);
    }

    public Team(){}

    // Function Name: addTeamMember
    // Function Parameters: competitor (Competitor), the given competitor to add to the team.
    // Expected Result: The competitor is added to the team, with the database being modified to reflect these changes.
    // Description: The competitor is added to the team.
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

    // Function Name: removeTeamMember
    // Function Parameters: competitor (Competitor), the given competitor to remove from the team.
    // Expected Result: The competitor is removed from the team, with the database being modified to reflect these changes.
    // Description: The competitor is removed from the team.
    public boolean removeTeamMember(Competitor competitor){
        if(this.competitors == null || !this.competitors.contains(competitor)){
            return false;
        }
        else{
            this.competitors.remove(competitor);
//            this.size--;
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
            return 0.0f;
        float totalDistanceBiked = 0;
        for (Competitor competitor : competitors) {
            totalDistanceBiked += competitor.getDistanceBiked();
        }
        return totalDistanceBiked;
    }
    public float getDistanceRan(){
        if(competitors == null)
            return 0.0f;
        float totalDistanceRan = 0;
        for (Competitor competitor: competitors) {
            totalDistanceRan += competitor.getDistanceRan();
        }
        return totalDistanceRan;
    }
    public float getDistanceSwam(){
        if(competitors == null)
            return 0.0f;
        float totalDistanceSwam = 0;
        for (Competitor competitor : competitors) {
            totalDistanceSwam += competitor.getDistanceSwam();
        }
        return totalDistanceSwam;
    }
}
