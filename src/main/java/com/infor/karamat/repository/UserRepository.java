package com.infor.karamat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infor.karamat.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
