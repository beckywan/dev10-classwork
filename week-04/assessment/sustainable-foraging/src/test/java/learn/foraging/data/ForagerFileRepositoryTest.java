package learn.foraging.data;

import learn.foraging.domain.Result;
import learn.foraging.models.Forage;
import learn.foraging.models.Forager;
import learn.foraging.models.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ForagerFileRepositoryTest {

    static final String SEED_FILE_PATH = "./data/foragers-seed.csv";
    static final String TEST_FILE_PATH = "./data/foragers-test.csv";
    ForagerFileRepository repository = new ForagerFileRepository(TEST_FILE_PATH);

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindAll() {
        List<Forager> all = repository.findAll();
        assertEquals(3, all.size());
    }

    @Test
    void shouldAdd() throws DataException {
        Forager forager = new Forager();
        forager.setFirstName("Test");
        forager.setLastName("Tester");
        forager.setState("NY");

        Forager result = repository.add(forager);
        assertEquals(4, repository.findAll().size());
    }
}