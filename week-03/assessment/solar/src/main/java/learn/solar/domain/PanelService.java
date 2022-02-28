package learn.solar.domain;

import learn.solar.data.DataException;
import learn.solar.data.PanelRepository;
import learn.solar.models.Material;
import learn.solar.models.Panel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PanelService {
    private final PanelRepository repository;

    public PanelService(PanelRepository repository) {
        this.repository = repository;
    }

    public List<Panel> findAll() throws DataException {
        return repository.findAll();
    }


    public PanelResult add(Panel panel) throws DataException {
        PanelResult result = validate(panel);
        if (!result.isSuccess()) {
            return result;
        }

        // check for duplicate
        PanelResult result1 = checkForDuplicate(panel, result);
        if (result1 != null) return result1;

        panel = repository.add(panel);
        result.setPayload(panel);
        return result;
    }


    private PanelResult checkForDuplicate(Panel panel, PanelResult result) throws DataException {
        List<Panel> panels = repository.findAll();
        for (Panel e : panels) {
            if (Objects.equals(panel.getSection(), e.getSection())
                    && Objects.equals(panel.getRow(), e.getRow())
                    && Objects.equals(panel.getColumn(), e.getColumn())) {
                result.addErrorMessage("Space is occupied with a panel already.");
                return result;
            }
        }
        return null;
    }


    public List<Panel> findBySection(String section) throws DataException {
        return repository.findBySection(section);
    }


    public PanelResult update(Panel panel) throws DataException {

        PanelResult result = validate(panel);
        if (!result.isSuccess()) {
            return result;
        }

        if (panel.getId() <= 0) {
            result.addErrorMessage("Panel ID must be greater than 0");
        }

        if (result.isSuccess()) {
            if (repository.update(panel)) {
                result.setPayload(panel);
            } else {
                String message = String.format(
                        "Panel ID %s was not found", panel.getId());
                result.addErrorMessage(message);
            }
        }

        return result;
    }


    public PanelResult deleteById(int panelId) throws DataException {
        PanelResult result = new PanelResult();

        if(!repository.deleteById(panelId)) {
            String message = String.format(
                    "Panel ID %s was not found", panelId);
            result.addErrorMessage(message);
        } else {
            System.out.println("Success!");
        }
        return result;
    }


    private PanelResult validate(Panel panel) {

        PanelResult result = new PanelResult();
        if (panel == null) {
            result.addErrorMessage("panel cannot be null");
            return result;
        }

        if (panel.getSection() == null || panel.getSection().trim().length() == 0) {
            result.addErrorMessage("Section is required");
        }

        if (panel.getMaterial() == null) {
            result.addErrorMessage("Correct material type is required");
        }

        int materials = 0;
        for (Material m : Material.values()) {
            if (panel.getMaterial() == m) {
                materials++;
            }
        }
        if (materials == 0) {
            result.addErrorMessage("Correct material type is required");
        }

        if (!(panel.getTracking().equalsIgnoreCase("yes") ||
                panel.getTracking().equalsIgnoreCase("no"))) {
                result.addErrorMessage("Panel tracking information is required");
        }

        if (panel.getRow() <= 0 || panel.getRow() > 250) {
            result.addErrorMessage("Row must be less than or equal to 250");
        }

        if (panel.getColumn() <= 0 || panel.getColumn() > 250) {
            result.addErrorMessage("Column must be less than or equal to 250");
        }

        if (panel.getInstallationYear() <= 0 || panel.getInstallationYear() > 2022) {
            result.addErrorMessage("Year installed must be in the past");
        }

        return result;
    }
}
