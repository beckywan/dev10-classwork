package learn.solar.data;

import learn.solar.models.Material;
import learn.solar.models.Panel;
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

class PanelFileRepositoryTest {

    static final String SEED_PATH = "./data/panels-seed.csv";
    static final String TEST_PATH = "./data/panels-test.csv";

    PanelRepository repository = new PanelFileRepository(TEST_PATH);

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_PATH);
        Path testPath = Paths.get(TEST_PATH);

        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }


    @Test
    void shouldAdd() throws DataException {
        Panel panel = new Panel();
        panel.setSection("ocean");
        panel.setRow(2);
        panel.setColumn(2);
        panel.setInstallationYear(3000);
        panel.setMaterial(Material.AMORPHOUS_SILICON);
        panel.setTracking("no");

        Panel actual = repository.add(panel);

        assertNotNull(actual);
        assertEquals(4, actual.getId());
    }

    @Test
    void shouldFindEachSection() throws DataException {
        List<Panel> findTwo = repository.findBySection("Mountain");
        assertEquals(2, findTwo.size());
        List<Panel> findOne = repository.findBySection("Desert");
        assertEquals(1, findOne.size());
    }

    @Test
    void shouldBeEmptyForMissingSection() throws DataException {
        List<Panel> doesNotExist = repository.findBySection("ocean");
        assertEquals(new ArrayList<>(), doesNotExist);
    }
    @Test
    void shouldUpdate() throws DataException {
        Panel panel = repository.findAll().get(2);
        panel.setSection("ocean");
        panel.setRow(2);
        panel.setColumn(2);
        panel.setInstallationYear(3000);
        panel.setMaterial(Material.AMORPHOUS_SILICON);
        panel.setTracking("no");

        assertTrue(repository.update(panel));

        panel = repository.findAll().get(2);
        assertNotNull(panel);
        assertEquals("ocean", panel.getSection());
        assertEquals(2, panel.getRow());
        assertEquals(2, panel.getColumn());
        assertEquals(3000, panel.getInstallationYear());
        assertEquals(Material.AMORPHOUS_SILICON, panel.getMaterial());
        assertEquals("no", panel.getTracking());

    }

    @Test
    void shouldBeFalseForMissingUpdate() throws DataException {
        Panel doesNotExist = new Panel();
        doesNotExist.setId(1000);
        assertFalse(repository.update(doesNotExist));

    }
    @Test
    void shouldDelete() throws DataException {
        int count = repository.findAll().size();
        assertTrue(repository.deleteById(1));
        assertEquals(count - 1, repository.findAll().size());

    }

    @Test
    void shouldBeFalseForMissingDelete() throws DataException {
        int count = repository.findAll().size();
        assertFalse(repository.deleteById(1000));
    }
}