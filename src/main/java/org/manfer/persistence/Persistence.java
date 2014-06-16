package org.manfer.persistence;

import java.util.List;
import org.manfer.dto.Dto;
import org.manfer.dto.PlayerDto;

/**
 * Main interface of the persistence layer.
 */
public interface Persistence {

    
    /**
     * Check connection with DB.
     * 
     * @return true is connection is established.
     */
    public boolean isConnected();    
    
    public <T extends Dto> void save(T dto);

    public <T extends Dto> void deleteAll(Class<T> collection);
    
    public <T extends Dto> void saveAll(List<T> lstDtos);
    
    public <T extends Dto> T findOne(String id, Class<T> collection);
    
    public <T extends Dto> List<T> findAll(Class<T> collection);

    public <T extends Dto> void delete(T dto, Class<T> collection);



    
    
        

}
