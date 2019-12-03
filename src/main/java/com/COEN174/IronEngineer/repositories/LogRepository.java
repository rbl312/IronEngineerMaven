package com.COEN174.IronEngineer.repositories;

import org.springframework.data.repository.CrudRepository;
import com.COEN174.IronEngineer.entities.Log;

// Interface Name: CompetitorRepository
// Interface Description: CRUD (Create Read Update Delete) repository implementation for the Competitor class and Competitor table in the Iron Engineer database.
public interface LogRepository extends CrudRepository<Log,Integer> {

    // Function Name: findById
    // Function Parameters: email (String), the email of the competitor to be searched for
    // Expected Results: The given competitor is returned by searching the database for the competitor's email.
    // Function Description: Searches the Iron Engineer database for the provided email and returns a competitor if the email is registered in the system.
    // Notes: The implementation of this function is handled by the Spring Framework
    public Log findByLogId(Integer id);
}
