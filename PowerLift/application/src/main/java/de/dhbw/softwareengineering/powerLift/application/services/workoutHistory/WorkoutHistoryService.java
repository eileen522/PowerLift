package de.dhbw.softwareengineering.powerLift.application.services.workoutHistory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.dhbw.softwareengineering.powerLift.domain.entities.WorkoutHistory;
import de.dhbw.softwareengineering.powerLift.domain.repositories.WorkoutHistoryRepository;

@Service
public class WorkoutHistoryService {
	
	@Autowired
	private WorkoutHistoryRepository workoutHistoryRepository;
	
	public Optional<WorkoutHistory> findByWorkoutHistoryId(UUID workoutHistoryId) {
		return workoutHistoryRepository.findByWorkoutHistoryId(workoutHistoryId);
	}
	public Optional<WorkoutHistory> findByDate(LocalDate date) {
		return workoutHistoryRepository.findByDate(date);
	}
	public List<WorkoutHistory> findByUserId(UUID userId) {
		return workoutHistoryRepository.findByUserId(userId);
	}
	public List<WorkoutHistory> getAllWorkoutHistorys() {
		return workoutHistoryRepository.getAllWorkoutHistorys();
	}
	public void createWorkoutHistory(WorkoutHistory workoutHistory) {
		
		WorkoutHistory newWorkoutHistory = new WorkoutHistory(workoutHistory.getWorkout(), workoutHistory.getUser(), workoutHistory.getDate());
		workoutHistoryRepository.createWorkoutHistory(newWorkoutHistory);
	}

}
