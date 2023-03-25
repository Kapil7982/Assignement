package com.paypal.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paypal.models.Sprint;
import com.paypal.models.Task;
import com.paypal.models.TaskStatus;

public interface SprintRepository extends JpaRepository<Sprint, Long>{

	
}
