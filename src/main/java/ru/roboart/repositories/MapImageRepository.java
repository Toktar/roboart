package ru.roboart.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.roboart.models.Event;
import ru.roboart.models.MapImage;

import javax.ws.rs.HeaderParam;
import java.util.List;

/**
 * Created by toktar.
 */
@RepositoryRestResource(collectionResourceRel = "maps", path = "maps")
public interface MapImageRepository extends PagingAndSortingRepository<MapImage, Long> {

   // List<MapImage> findByTimestampGreaterThanEqual(@HeaderParam("timestamp") long timestamp);
    //List<MapImage> findAll();
    //List<MapImage> findById(@Param("id") String id);
}
