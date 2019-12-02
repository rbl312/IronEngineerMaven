package com.COEN174.IronEngineer.repositories;

import org.springframework.data.repository.CrudRepository;
import com.COEN174.IronEngineer.entities.Team;

import java.util.List;

// Interface Name: TeamRepository
// Interface Description: CRUD (Create Read Update Delete) repository implementation for the Team class and Team table in the Iron Engineer database.
public interface TeamRepository extends CrudRepository<Team,Integer>{
    // Function Name: findByTeamName
    // Function Parameters: teamName (String), the team name of the team to be searched for
    // Expected Results: The given team is returned by searching the database for the team's team name.
    // Function Description: Searches the Iron Engineer database for the provided team name and returns a competitor if the team name is registered to a team in the system.
    // Notes: The implementation of this function is handled by the Spring Framework
    public Team findByTeamName(String teamName);

    // Function Name: findByTeamId
    // Function Parameters: id (Integer), the key of the team to be searched for
    // Expected Results: The given team is returned by searching the database for the team's primary key.
    // Function Description: Searches the Iron Engineer database for the provided id number and returns a team if the id is registered to a team in the system.
    // Notes: The implementation of this function is handled by the Spring Framework
    public Team findByTeamId(Integer id);
}
