package com.paypal.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.paypal.models.Sprint;
import com.paypal.repo.SprintRepository;

@Service
@Transactional
public class SprintService {
	
    private final SprintRepository sprintRepo;

    public SprintService(SprintRepository sprintRepo) {
        this.sprintRepo = sprintRepo;
    }

    public List<Sprint> getAllSprints() {
        return sprintRepo.findAll();
    }

    public Optional<Sprint> getSprintById(Long id) {
        return sprintRepo.findById(id);
    }

    public void createSprint(Sprint sprint) {
        sprintRepo.save(sprint);
    }

    public void updateSprint(Sprint sprint) {
        sprintRepo.save(sprint);
    }

    public void deleteSprintById(Long id) {
        sprintRepo.deleteById(id);
    }
}
