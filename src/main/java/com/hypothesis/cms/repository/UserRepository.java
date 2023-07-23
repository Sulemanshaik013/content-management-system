package com.hypothesis.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hypothesis.cms.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
