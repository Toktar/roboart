package ru.roboart.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.roboart.models.Category;
import ru.roboart.models.Event;

/**
 * Created by toktar.
 */
@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {


   // List<MapImage> findById(@Param("id") String id);
}
