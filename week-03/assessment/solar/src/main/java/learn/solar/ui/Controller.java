package learn.solar.ui;

import learn.solar.data.DataException;
import learn.solar.domain.PanelResult;
import learn.solar.domain.PanelService;
import learn.solar.models.Panel;

import java.util.List;

public class Controller {
    private final PanelService service;
    private final View view;

    public Controller(PanelService service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        view.printHeader("Welcome To Solar Panel Farm");

        try {
            runMenuLoop();
        } catch (DataException ex) {
            view.printHeader("CRITICAL ERROR:" + ex.getMessage());
        }

        view.printHeader("Goodbye");
    }

    private void runMenuLoop() throws DataException {
        MenuOption option;
        do {
            option = view.displayMenuGetOption();
            switch (option) {
                case ADD:
                    addPanel();
                    break;
                case DISPLAY_BY_SECTION:
                    displayBySection();
                    break;
                case UPDATE:
                    updatePanel();
                    break;
                case DElETE:
                    deletePanel();
                    break;
            }
        } while (option != MenuOption.EXIT);
    }
    

    private void displayBySection() throws DataException {
        view.printHeader(MenuOption.DISPLAY_BY_SECTION.getMessage());
        List<Panel> all = service.findAll();
        String section = view.readSection(all);
        List<Panel> panels = service.findBySection(section);
        view.printBySection(panels);
    }


    private void addPanel() throws DataException {
        Panel panel = view.makePanel();
        PanelResult result = service.add(panel);
        view.printResult(result);
    }

    private void deletePanel() throws DataException {
        view.printHeader(MenuOption.DElETE.getMessage());

        List<Panel> all = service.findAll();
        String section = view.readSection(all);
        List<Panel> panels = service.findBySection(section);

        int id = view.deletePanel(panels);
        if(id == 0) {
            return;
        }

        PanelResult result = service.deleteById(id);
        view.printResult(result);
    }

    private void updatePanel() throws DataException {
        view.printHeader(MenuOption.UPDATE.getMessage());

        List<Panel> all = service.findAll();
        String section = view.readSection(all);
        List<Panel> panels = service.findBySection(section);

        Panel panel = view.updatePanel(panels);
        if(panel == null) {
            return;
        }

        PanelResult result = service.update(panel);
        view.printResult(result);
    }
}
