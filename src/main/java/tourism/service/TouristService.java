package tourism.service;
import org.springframework.stereotype.Service;
import tourism.model.TouristAttraction;
import tourism.repository.TouristRepository;

import java.util.List;

/**
 * Service class that provides business logic for managing tourist attractions.
 * Acts as an intermediary between the controller layer and the {@link TouristRepository}.
 */
@Service
public class TouristService {

    /**
     * Repository for accessing and modifying tourist attraction data.
     */
    private final TouristRepository repository;

    /**
     * Constructs a new {@code TouristService} with the given repository.
     *
     * @param repository the {@link TouristRepository} to use for data access
     */
    public TouristService(TouristRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all tourist attractions.
     *
     * @return a list of all {@link TouristAttraction} objects
     */
    public List<TouristAttraction> getAttractions() {
        return repository.getAllAttractions();
    }

    /**
     * Finds a tourist attraction by its name.
     *
     * @param attractionName the name of the attraction to find
     * @return the matching {@link TouristAttraction}, or {@code null} if not found
     */
    public TouristAttraction getOneNamedAttraction(String attractionName) {
        return repository.findByName(attractionName);
    }

    /**
     * Retrieves a list of all tags associated with the tourist attractions.
     *
     * @return a list of tags from all attractions
     */
    public List<String> getTags() {
        return repository.getDistinctTags();
    }

    /**
     * Retrieves a list of all cities where the tourist attractions are located.
     *
     * @return a list of city names from all attractions
     */
    public List<String> getCities() {
        return repository.getDistinctCities();
    }

    /**
     * Adds a new tourist attraction to the repository.
     *
     * @param attraction the {@link TouristAttraction} to add
     * @return the added attraction if successful, otherwise {@code null}
     */
    public TouristAttraction addNamedAttraction(TouristAttraction attraction) {
        return repository.addOneNamedAttractionToList(attraction);
    }

    /**
     * Updates an existing tourist attraction by matching its name.
     *
     * @param updatedTouristAttraction the updated {@link TouristAttraction} object
     * @return the previous attraction that was replaced, or {@code null} if no match was found
     */
    public TouristAttraction updateAttraction(TouristAttraction updatedTouristAttraction) {
        return repository.updateOneNamedAttraction(updatedTouristAttraction);
    }

    /**
     * Deletes a tourist attraction by its name.
     *
     * @param attractionName the name of the attraction to delete
     * @return {@code true} if the attraction was successfully deleted, {@code false} otherwise
     */
    public boolean deleteAttraction(String attractionName) {
        return repository.deleteOneNamedAttractionFromList(attractionName);
    }
}
