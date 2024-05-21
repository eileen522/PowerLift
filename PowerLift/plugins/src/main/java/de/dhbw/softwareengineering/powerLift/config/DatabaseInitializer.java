package de.dhbw.softwareengineering.powerLift.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.dhbw.softwareengineering.powerLift.domain.entities.Exercise;
import de.dhbw.softwareengineering.powerLift.domain.entities.TrainingSet;
import de.dhbw.softwareengineering.powerLift.domain.entities.User;
import de.dhbw.softwareengineering.powerLift.domain.entities.Workout;
import de.dhbw.softwareengineering.powerLift.domain.entities.WorkoutHistory;
import de.dhbw.softwareengineering.powerLift.domain.entities.WorkoutPlan;
import de.dhbw.softwareengineering.powerLift.domain.repositories.ExerciseRepository;
import de.dhbw.softwareengineering.powerLift.domain.repositories.TrainingSetRepository;
import de.dhbw.softwareengineering.powerLift.domain.repositories.UserRepository;
import de.dhbw.softwareengineering.powerLift.domain.repositories.WorkoutHistoryRepository;
import de.dhbw.softwareengineering.powerLift.domain.repositories.WorkoutPlanRepository;
import de.dhbw.softwareengineering.powerLift.domain.repositories.WorkoutRepository;
import de.dhbw.softwareengineering.powerLift.domain.values.RPE;
import jakarta.annotation.PostConstruct;

@Component
public class DatabaseInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private TrainingSetRepository trainingSetRepository;

    @Autowired
    private WorkoutHistoryRepository workoutHistoryRepository;

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

    @PostConstruct
    public void init() {
        
        // Create users
    	User user1 = new User.Builder()
    	        .withUsername("johnDoe")
    	        .withEmail("john@example.com")
    	        .withPassword("password")
    	        .build();

    	User user2 = new User.Builder()
    	        .withUsername("janeDoe")
    	        .withEmail("jane@example.com")
    	        .withPassword("password1234")
    	        .build();

    	User user3 = new User.Builder()
    	        .withUsername("eileen")
    	        .withEmail("eileen@dhbw.com")
    	        .withPassword("password5678")
    	        .build();
        
        user1 = userRepository.createUser(user1);
        user2 = userRepository.createUser(user2);
        user3 = userRepository.createUser(user3);
        
        // Create exercises
        Exercise squat = new Exercise("Squat", "A primary lower body exercise", "Legs");
        Exercise bench = new Exercise("Bench Press", "A primary upper body exercise", "Chest");
        Exercise deadlift = new Exercise("Deadlift", "A full body exercise", "Full Body");
        Exercise pullUp = new Exercise("Pull-Up", "An upper body exercise", "Back");
        Exercise overheadPress = new Exercise("Overhead Press", "A shoulder exercise", "Shoulders");

        exerciseRepository.createExercise(squat);
        exerciseRepository.createExercise(bench);
        exerciseRepository.createExercise(deadlift);
        exerciseRepository.createExercise(pullUp);
        exerciseRepository.createExercise(overheadPress);
        exerciseRepository.createExercise(new Exercise("Bicep Curl", "An upper arm exercise", "Arms"));
        exerciseRepository.createExercise(new Exercise("Tricep Extension", "A lower arm exercise", "Arms"));
        exerciseRepository.createExercise(new Exercise("Leg Press", "A leg exercise", "Legs"));
        exerciseRepository.createExercise(new Exercise("Calf Raise", "A lower leg exercise", "Legs"));
        exerciseRepository.createExercise(new Exercise("Lat Pulldown", "A back exercise", "Back"));
        
        // Create training sets
        TrainingSet squatSet1 = new TrainingSet(squat, 5, 5, new RPE( 8.0));
        TrainingSet benchPressSet1 = new TrainingSet(bench, 3, 8, new RPE( 7.5));
        TrainingSet deadliftSet1 = new TrainingSet(deadlift, 4, 2, new RPE( 9.0));
        TrainingSet pullUpSet1 = new TrainingSet(pullUp, 4, 10, new RPE( 7.0));
        TrainingSet overheadPressSet1 = new TrainingSet(overheadPress, 3, 6, new RPE( 8.5));
        
        TrainingSet squatSet2 = new TrainingSet(squat, 4, 6, new RPE( 7.5));
        TrainingSet benchPressSet2 = new TrainingSet(bench, 5, 5, new RPE( 8.0));
        TrainingSet deadliftSet2 = new TrainingSet(deadlift, 3, 3, new RPE( 9.5));
        TrainingSet pullUpSet2 = new TrainingSet(pullUp, 3, 8, new RPE( 7.5));
        TrainingSet overheadPressSet2 = new TrainingSet(overheadPress, 4, 4, new RPE( 8.0));
        
        trainingSetRepository.createTrainingSet(squatSet1);
        trainingSetRepository.createTrainingSet(benchPressSet1);
        trainingSetRepository.createTrainingSet(deadliftSet1);
        trainingSetRepository.createTrainingSet(pullUpSet1);
        trainingSetRepository.createTrainingSet(overheadPressSet1);
        
        trainingSetRepository.createTrainingSet(squatSet2);
        trainingSetRepository.createTrainingSet(benchPressSet2);
        trainingSetRepository.createTrainingSet(deadliftSet2);
        trainingSetRepository.createTrainingSet(pullUpSet2);
        trainingSetRepository.createTrainingSet(overheadPressSet2);
        
        // Create workouts
        Workout workout1 = new Workout("SBD Workout", user1, Arrays.asList(squatSet1, benchPressSet1, deadliftSet1));
        Workout workout2 = new Workout("Upper Body Workout", user2, Arrays.asList(pullUpSet1, overheadPressSet1, benchPressSet2));
        Workout workout3 = new Workout("Lower Body Workout", user3, Arrays.asList(squatSet2, deadliftSet2));
        
        workoutRepository.createWorkout(workout1);
        workoutRepository.createWorkout(workout2);
        workoutRepository.createWorkout(workout3);
        
        // Create workout history
        WorkoutHistory workoutHistory1 = new WorkoutHistory(workout1, user1, LocalDate.now().minusDays(1));
        WorkoutHistory workoutHistory2 = new WorkoutHistory(workout1, user1, LocalDate.now());
        WorkoutHistory workoutHistory3 = new WorkoutHistory(workout2, user2, LocalDate.now().minusDays(2));
        WorkoutHistory workoutHistory4 = new WorkoutHistory(workout3, user3, LocalDate.now().minusDays(3));
        
        workoutHistoryRepository.createWorkoutHistory(workoutHistory1);
        workoutHistoryRepository.createWorkoutHistory(workoutHistory2);
        workoutHistoryRepository.createWorkoutHistory(workoutHistory3);
        workoutHistoryRepository.createWorkoutHistory(workoutHistory4);
        
        // Create workout plans
        WorkoutPlan workoutPlan1 = new WorkoutPlan("Strength Plan", "This is a Plan for people who want to get stronger", user1, Arrays.asList(workout1, workout3));
        WorkoutPlan workoutPlan2 = new WorkoutPlan("Hypertrophy Plan", "This is a Plan for people who want to build muscle", user2, Arrays.asList(workout2));
        
        workoutPlanRepository.createWorkoutPlan(workoutPlan1);
        workoutPlanRepository.createWorkoutPlan(workoutPlan2);
    }
}
