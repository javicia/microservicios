package com.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.service.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{

}
