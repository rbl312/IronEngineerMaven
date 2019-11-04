package com.COEN174.IronEngineer.repositories;

import org.springframework.data.repository.CrudRepository;
import com.COEN174.IronEngineer.entities.Team;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team,Integer>{
    public List<Team> findByName(String name);
}
