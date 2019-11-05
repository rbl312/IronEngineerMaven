package com.COEN174.IronEngineer.repositories;

import org.springframework.data.repository.CrudRepository;
import com.COEN174.IronEngineer.entities.Team;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends CrudRepository<Team,Integer>{
    public Optional<Team> findByTeamName(String teamName);
    public Optional<Team> findByTeamId(Integer id);
}
