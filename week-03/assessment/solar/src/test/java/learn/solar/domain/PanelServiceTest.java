package learn.solar.domain;

import learn.solar.data.DataException;
import learn.solar.data.PanelRepositoryTestDouble;
import learn.solar.models.Material;
import learn.solar.models.Panel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PanelServiceTest {

    PanelService service = new PanelService(new PanelRepositoryTestDouble());


    @Test
    void shouldNotAddNull() throws DataException {
        PanelResult expected = makeResult("panel cannot be null");
        PanelResult actual = service.add(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddEmptySection() throws DataException {
        Panel panel = new Panel(0,"" , 2, 2, 2000, Material.CADMIUM_TELLURIDE, "yes");
        PanelResult expected = makeResult("Section is required");
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullSection() throws DataException {
        Panel panel = new Panel(0,null , 2, 2, 2000, Material.CADMIUM_TELLURIDE, "yes");
        PanelResult expected = makeResult("Section is required");
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddFutureYear() throws DataException {
        Panel panel = new Panel(0,"ocean" , 2, 2, 9999, Material.CADMIUM_TELLURIDE, "yes");
        PanelResult expected = makeResult("Year installed must be in the past");
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNotNullMaterial() throws DataException {
        Panel panel = new Panel(0,"ocean" , 2, 2, 2000, null, "yes");
        PanelResult expected = makeResult("Correct material type is required");
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }


    @Test
    void shouldNotAddDuplicate() throws DataException {
        Panel panel = new Panel(1,"farm",1,1,2000,Material.AMORPHOUS_SILICON,"yes");
        PanelResult expected = makeResult("Space is occupied with a panel already.");
        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() throws DataException {
        Panel panel = new Panel(0,"ocean",2,3,2000,Material.AMORPHOUS_SILICON,"yes");
        PanelResult expected = new PanelResult();
        expected.setPayload(panel);

        PanelResult actual = service.add(panel);
        assertEquals(expected, actual);
    }

    private PanelResult makeResult(String message) {
        PanelResult result = new PanelResult();
        result.addErrorMessage(message);
        return result;
    }

    @Test
    void shouldFindByType() throws DataException {
        List<Panel> actual = service.findBySection("city");
        assertEquals(1, actual.size());
    }

    @Test
    void shouldUpdate() throws DataException {
        Panel panelToUpdate = new Panel(1,"test",1,1,2000,Material.AMORPHOUS_SILICON,"yes");
        PanelResult expected = new PanelResult();
        expected.setPayload(panelToUpdate);

        PanelResult actual = service.update(panelToUpdate);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNull() throws DataException {
        PanelResult expected = makeResult("panel cannot be null");
        PanelResult actual = service.update(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteById() throws DataException {
        PanelResult expected = new PanelResult();
        PanelResult actual = service.deleteById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotDeleteById() throws DataException {
        PanelResult expected = makeResult("Panel ID 99 was not found");
        PanelResult actual = service.deleteById(99);
        assertEquals(expected, actual);
    }
}