package de.dhbw.softwareengineering.powerLift.plugins.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.dhbw.softwareengineering.powerLift.domain.entities.WorkoutHistory;
import de.dhbw.softwareengineering.powerLift.domain.repositories.WorkoutHistoryRepository;

@Repository
public class WorkoutHistorys implements WorkoutHistoryRepository {
	
	@Autowired 
	SpringDataForWorkoutHistory springDataForWorkoutHistory;

	@Override
	public Optional<WorkoutHistory> findByWorkoutHistoryId(UUID workoutHistoryId) {
		return springDataForWorkoutHistory.findById(workoutHistoryId);
	}

	@Override
	public Optional<WorkoutHistory> findByDate(LocalDate date) {
		return springDataForWorkoutHistory.findByDate(date);
	}

	@Override
	public List<WorkoutHistory> findByUserId(UUID userId) {
		return springDataForWorkoutHistory.findByUserId(userId);
	}

	@Override
	public List<WorkoutHistory> getAllWorkoutHistorys() {
		return springDataForWorkoutHistory.findAll();
	}

	@Override
	public WorkoutHistory createWorkoutHistory(WorkoutHistory workoutHistory) {
		return springDataForWorkoutHistory.save(workoutHistory);
	}

}
