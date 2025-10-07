package tourism.repository;

import tourism.model.TouristAttraction;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Repository class for managing a list of tourist attractions.
 * Provides basic CRUD operations on an in-memory list of {@link TouristAttraction} objects.
 */
@Repository
public class TouristRepository {

    /**
     * In-memory list of tourist attractions initialized with predefined data.
     */
    private final ArrayList<TouristAttraction> attractions = new ArrayList<>(List.of(
            new TouristAttraction("Tivoli", "København", "Forlystelsespark i hjertet af København.", List.of("forlystelser", "familie", "kultur")),
            new TouristAttraction("Nyhavn", "København", "Farverig havnepromenade med restauranter og barer.", List.of("havn", "restauranter", "historie")),
            new TouristAttraction("Den Lille Havfrue", "København", "Berømt statue inspireret af H.C. Andersen.", List.of("statue", "kultur", "historie")),
            new TouristAttraction("ARoS", "Aarhus", "Kunstmuseum i Aarhus med regnbuepanorama.", List.of("kunst", "museum", "arkitektur")),
            new TouristAttraction("Egeskov Slot", "Kværndrup", "Renæssanceslot på Fyn omgivet af voldgrav.", List.of("slot", "historie", "have")),
            new TouristAttraction("Aalborg Zoo", "Aalborg", "Dyrepark med mere end 100 forskellige arter.", List.of("dyr", "familie", "natur")),
            new TouristAttraction("Moesgaard Museum", "Aarhus", "Museum i Aarhus med arkæologi og kulturhistorie.", List.of("museum", "historie", "arkæologi")),
            new TouristAttraction("Kronborg Slot", "Helsingør", "Renæssanceslot i Helsingør, kendt fra Shakespeares Hamlet.", List.of("slot", "kultur", "historie")),
            new TouristAttraction("Odense Zoo", "Odense", "Familievenlig zoologisk have på Fyn.", List.of("dyr", "familie", "natur")),
            new TouristAttraction("Hammershus", "Bornholm", "Nordeuropas største borgruin på Bornholm.", List.of("ruin", "historie", "arkitektur")),
            new TouristAttraction("Grenen", "Skagen", "Danmarks nordligste punkt, hvor to have mødes.", List.of("natur", "strand", "geografi")),
            new TouristAttraction("Legoland", "Billund", "Forlystelsespark i Billund bygget af LEGO-klodser.", List.of("forlystelser", "familie", "leg"))
    ));

    /**
     * Retrieves the full list of tourist attractions.
     *
     * @return a list of all {@link TouristAttraction} objects.
     */
    public ArrayList<TouristAttraction> getAllAttractions() {
        return attractions;
    }

    /**
     * Finds a tourist attraction by its exact name match.
     *
     * @param attractionName the name to look up
     * @return the {@link TouristAttraction} if found, otherwise {@code null}
     */
    public TouristAttraction findByName(String attractionName) {
        if (attractionName == null) return null;
        for (TouristAttraction attraction : attractions) {
            if (attractionName.equals(attraction.getName())) {
                return attraction;
            }
        }
        return null;
    }

    /**
     * Adds a new tourist attraction to the list.
     *
     * @param touristAttraction the {@link TouristAttraction} to add.
     * @return the added attraction if successful, otherwise {@code null}.
     */
    public TouristAttraction addOneNamedAttractionToList(TouristAttraction touristAttraction) {
        boolean isAddOpSuccess = attractions.add(touristAttraction);
        return isAddOpSuccess ? touristAttraction : null;
    }

    /**
     * Updates an existing tourist attraction at the specified index.
     *
     * @param index the index of the attraction to update.
     * @param updatedTouristAttraction the new {@link TouristAttraction} data.
     * @return the previous attraction that was replaced.
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    public TouristAttraction updateOneNamedAttraction(int index, TouristAttraction updatedTouristAttraction) {
        return attractions.set(index, updatedTouristAttraction);
    }

    /**
     * Updates an existing tourist attraction by name.
     *
     * @param updatedAttraction the new data whose name identifies the target
     * @return the previous attraction if replaced, otherwise {@code null}
     */
    public TouristAttraction updateOneNamedAttraction(TouristAttraction updatedAttraction) {
        if (updatedAttraction == null || updatedAttraction.getName() == null) return null;
        for (int i = 0; i < attractions.size(); i++) {
            TouristAttraction existing = attractions.get(i);
            if (updatedAttraction.getName().equals(existing.getName())) {
                return attractions.set(i, updatedAttraction);
            }
        }
        return null;
    }

    /**
     * Deletes a tourist attraction from the list by its name.
     *
     * @param attractionName the name of the attraction to remove.
     * @return {@code true} if an attraction was removed, {@code false} otherwise.
     */
    public boolean deleteOneNamedAttractionFromList(String attractionName) {
        return attractions.removeIf(namedAttractionToRemove ->
                namedAttractionToRemove.getName().equals(attractionName));
    }

    /**
     * Returns a distinct list of all tags across attractions.
     */
    public List<String> getDistinctTags() {
        return attractions.stream()
                .flatMap(a -> a.getTags() == null ? new ArrayList<String>().stream() : a.getTags().stream())
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Returns a distinct list of all cities across attractions.
     */
    public List<String> getDistinctCities() {
        return attractions.stream()
                .map(TouristAttraction::getCity)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }
}
