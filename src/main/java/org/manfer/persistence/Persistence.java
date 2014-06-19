package org.manfer.persistence;

import java.util.List;
import org.manfer.dto.Dto;

/**
 * Main interface of the persistence layer.
 * This is the interface to be used from the application level in order to perform any 
 * action with the underlying DB.
 * 
 * @author marcandreuf
 */
public interface Persistence {

    
    /**
     * Check connection with DB.
     * 
     * @return true if connection is established.
     */
    public boolean isConnected();    
    
    
    /**
     * Stores the given DTO object to DB.
     *  
     * @param <T> object type to be persisted.
     * @param dto object to be persisted.
     */
    public <T extends Dto> void save(T dto);

    /**
     * Removes all objects of the given collection type.
     * 
     * @param collection Class type of the object collection.
     */
    public <T extends Dto> void deleteAll(Class<T> collection);
    
    /**
     * Stores all objects of a given list in the collection of objects with same type.
     * 
     * @param <T> object type to be persisted.
     * @param lstDtos list of objects to be persisted.
     */
    public <T extends Dto> void saveAll(List<T> lstDtos);
    
    /**
     * Load one object by the given id from the given collection type.
     * 
     * @param id Id of the object to be loaded.
     * @param collection Class type of the object.
     * @return The object with the given Id from the collection type.
     */
    public <T extends Dto> T findOne(String id, Class<T> collection);
    
    
    /**
     * Load all objects of a given type.
     * 
     * @param collection Class type of the object.
     * @return A list of objects of the given type.
     */
    public <T extends Dto> List<T> findAll(Class<T> collection);

    /**
     * Removes the given object from the collection.
     * 
     * @param dto Object to be deleted.
     * @param collection Class type of the object.
     */
    public <T extends Dto> void delete(T dto, Class<T> collection);

    
    /**
     * Loads an object by its name. 
     * 
     * @param name
     * @param collection Class type of the object.
     * @return The object with the given name from the collection type.
     */
    public <T extends Dto> T findByName(String name, Class<T> collection);

}
