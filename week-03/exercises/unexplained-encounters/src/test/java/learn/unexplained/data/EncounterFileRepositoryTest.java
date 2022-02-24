package learn.unexplained.data;

import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EncounterFileRepositoryTest {

    static final String SEED_PATH = "./data/encounters-seed.csv";
    static final String TEST_PATH = "./data/encounters-test.csv";

    EncounterRepository repository = new EncounterFileRepository(TEST_PATH);

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_PATH);
        Path testPath = Paths.get(TEST_PATH);

        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void findAll() throws DataAccessException {
        List<Encounter> actual = repository.findAll();
        assertEquals(3, actual.size());
    }

    @Test
    void shouldAdd() throws DataAccessException {
        Encounter encounter = new Encounter();
        encounter.setType(EncounterType.UFO);
        encounter.setWhen("Jan 15, 2005");
        encounter.setDescription("moving pinpoint of light." +
                "seemed to move with me along the highway. " +
                "then suddenly reversed direction without slowing down. it just reversed.");
        encounter.setOccurrences(1);

        Encounter actual = repository.add(encounter);

        assertNotNull(actual);
        assertEquals(4, actual.getEncounterId());
    }

    @Test
    void shouldFindOneOfEachType() throws DataAccessException {
        List<Encounter> one = repository.findByType(EncounterType.UFO);
        assertEquals(1, one.size());
        List<Encounter> two = repository.findByType(EncounterType.CREATURE);
        assertEquals(1, two.size());
        List<Encounter> three = repository.findByType(EncounterType.SOUND);
        assertEquals(1, three.size());

    }

    @Test
    void shouldBeEmptyForMissingType() throws DataAccessException {
        List<Encounter> doesNotExist = repository.findByType(EncounterType.VISION);
        assertEquals(new ArrayList<>(), doesNotExist);
    }
    @Test
    void shouldUpdate() throws DataAccessException {
        Encounter encounter = repository.findAll().get(2);
        encounter.setDescription("testDescription");
        encounter.setOccurrences(2);
        encounter.setType(EncounterType.VISION);
        encounter.setWhen("0000-00-00");
        assertTrue(repository.update(encounter));

        encounter = repository.findAll().get(2);
        assertNotNull(encounter);
        assertEquals("testDescription", encounter.getDescription());
        assertEquals(2, encounter.getOccurrences());
        assertEquals(EncounterType.VISION, encounter.getType());
        assertEquals("0000-00-00", encounter.getWhen());


    }

    @Test
    void shouldBeFalseForMissingUpdate() throws DataAccessException {
    Encounter doesNotExist = new Encounter();
    doesNotExist.setEncounterId(1000);
    assertFalse(repository.update(doesNotExist));

    }
    @Test
    void shouldDelete() throws DataAccessException {
        int count = repository.findAll().size();
        assertTrue(repository.deleteById(1));
        assertEquals(count - 1, repository.findAll().size());

    }

    @Test
    void shouldBeFalseForMissingDelete() throws DataAccessException {
        int count = repository.findAll().size();
        assertFalse(repository.deleteById(1000));
    }
}