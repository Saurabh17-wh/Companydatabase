package com.info.company.dao;

import com.info.company.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User , Integer>
{
    public User findById(int id);
}
