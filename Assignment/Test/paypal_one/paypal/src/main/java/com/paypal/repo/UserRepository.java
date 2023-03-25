package com.paypal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paypal.models.Task;
import com.paypal.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

	
}
