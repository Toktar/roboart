package ru.roboart.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.roboart.models.Gift;
import ru.roboart.models.Organizer;

/**
 * Created by toktar.
 */
@RepositoryRestResource(collectionResourceRel = "gifts", path = "gifts")
public interface GiftRepository extends PagingAndSortingRepository<Gift, Long> {


   // List<MapImage> findById(@Param("id") String id);
}
