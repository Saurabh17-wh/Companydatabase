package com.info.company.dao;

import com.info.company.entities.Authentication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AuthenRepo extends CrudRepository<Authentication , Long>
{
    @Query("SELECT a FROM Authentication a WHERE a.username = :username")
    public Authentication getUserByUsername(@Param("username") String username);
}
