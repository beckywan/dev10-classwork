package learn.solar.data;

import learn.solar.models.Material;
import learn.solar.models.Panel;

import java.util.ArrayList;
import java.util.List;


public class PanelRepositoryTestDouble implements PanelRepository {

    private List<Panel> panels = new ArrayList<> (List.of(
            new Panel(1,"farm",1,1,2000, Material.AMORPHOUS_SILICON,"yes"),
            new Panel(2,"farm",1,2,2002,Material.AMORPHOUS_SILICON,"no"),
            new Panel(3,"city",1,1,2010,Material.CADMIUM_TELLURIDE,"yes")
    ));

    @Override
    public List<Panel> findAll() throws DataException {
        return panels;
    }

    @Override
    public boolean update(Panel panel) throws DataException {
        return panel.getId() == 1;
    }

    @Override
    public List<Panel> findBySection(String section) throws DataException {
        List<Panel> result = new ArrayList<>();
        for (Panel panel : panels) {
            if(panel.getSection() == section) {
                result.add(panel);
            }
        }
        return result;
    }

    @Override
    public Panel add(Panel panel) throws DataException {
        return panel;
    }

    @Override
    public boolean deleteById(int panelId) throws DataException {
        return panelId == 1;
    }
}
