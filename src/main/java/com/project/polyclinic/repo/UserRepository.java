package com.project.polyclinic.repo;

import com.project.polyclinic.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
    User findById (int id);
    List<User> findAll();
    void deleteById (int id);
}
