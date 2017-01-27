package ru.roboart.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.roboart.models.Event;
import ru.roboart.models.MapImage;

import javax.ws.rs.HeaderParam;
import java.util.List;

/**
 * Created by toktar.
 */
@RepositoryRestResource(collectionResourceRel = "events", path = "events")
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {


   // List<Event> findByTimestampGreaterThanEqual(@HeaderParam("timestamp") long timestamp);

   // List<MapImage> findById(@Param("id") String id);
}
