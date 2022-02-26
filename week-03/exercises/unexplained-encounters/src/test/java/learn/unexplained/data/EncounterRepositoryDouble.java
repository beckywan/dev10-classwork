package learn.unexplained.data;

import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;

import java.util.ArrayList;
import java.util.List;

public class EncounterRepositoryDouble implements EncounterRepository {

    private List<Encounter> encounters = new ArrayList<>(List.of(
            new Encounter(1, EncounterType.UFO, "1/1/2015", "test description #1", 1),
            new Encounter(2, EncounterType.CREATURE, "2/1/2015", "test description #2", 1),
            new Encounter(3, EncounterType.SOUND, "3/1/2015", "test description #3", 1)
    ));

    @Override
    public List<Encounter> findAll() throws DataAccessException {
        return encounters;
    }

    @Override
    public boolean update(Encounter encounter) throws DataAccessException {
        return encounter.getEncounterId() == 1;
    }

    @Override
    public List<Encounter> findByType(EncounterType type) throws DataAccessException {
        List<Encounter> result = new ArrayList<>();
        for (Encounter encounter : encounters) {
            if(encounter.getType() == type) {
                result.add(encounter);
            }
        }
        return result;
    }

    @Override
    public Encounter add(Encounter encounter) throws DataAccessException {
        return encounter;
    }

    @Override
    public boolean deleteById(int encounterId) throws DataAccessException {
        return encounterId == 1;
    }
}
