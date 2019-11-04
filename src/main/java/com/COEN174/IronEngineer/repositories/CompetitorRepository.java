package com.COEN174.IronEngineer.repositories;

import org.springframework.data.repository.CrudRepository;
import com.COEN174.IronEngineer.entities.Competitor;

import java.util.List;

public interface CompetitorRepository extends CrudRepository<Competitor,Integer> {
    public List<Competitor> findByTeamTeamId(Integer teamId);
    public Competitor findByEmail(String email);
}
