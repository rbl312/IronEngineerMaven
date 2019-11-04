package com.COEN174.IronEngineer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.COEN174.IronEngineer.entities.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

}
