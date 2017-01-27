package ru.roboart.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.roboart.models.MapImage;
import ru.roboart.models.Organizer;

/**
 * Created by toktar.
 */
@RepositoryRestResource(collectionResourceRel = "maps", path = "maps")
public interface OrganizerRepository extends PagingAndSortingRepository<Organizer, Long> {


   // List<MapImage> findById(@Param("id") String id);
}
