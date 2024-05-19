package de.dhbw.softwareengineering.powerLift.domain.repositories;


public interface BridgeRepository {
	
    boolean existsById(String id);
    long count();
    void deleteById(String id);
    void deleteAll();
    
    //TODO: save, findById, findAll, findById, count, deleteById, delete, deleteAll
    
}
