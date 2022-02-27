package learn.unexplained.ui;

import learn.unexplained.data.DataAccessException;
import learn.unexplained.domain.EncounterResult;
import learn.unexplained.domain.EncounterService;
import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;

import java.util.List;

public class Controller {

    private final EncounterService service;
    private final View view;

    public Controller(EncounterService service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        view.printHeader("Welcome To Unexplained Encounters.");

        try {
            runMenuLoop();
        } catch (DataAccessException ex) {
            view.printHeader("CRITICAL ERROR:" + ex.getMessage());
        }

        view.printHeader("Goodbye");
    }

    private void runMenuLoop() throws DataAccessException {
        MenuOption option;
        do {
            option = view.displayMenuGetOption();
            switch (option) {
                case DISPLAY_ALL:
                    displayAllEncounters();
                    break;
                case ADD:
                    addEncounter();
                    break;
                case DISPLAY_BY_TYPE:
                    displayByType();
                    break;
                case UPDATE:
                    updateEncounter();
                    break;
                case DElETE:
                    deleteEncounter();
                    break;
            }
        } while (option != MenuOption.EXIT);
    }

    private void displayAllEncounters() throws DataAccessException {
        List<Encounter> encounters = service.findAll();
        view.printAllEncounters(encounters);
    }

    private void displayByType() throws DataAccessException {
        view.printHeader(MenuOption.DISPLAY_BY_TYPE.getMessage());
        EncounterType type = view.readType();
        List<Encounter> encounters = service.findByType(type);
        view.printByType(encounters);
    }


    private void addEncounter() throws DataAccessException {
        Encounter encounter = view.makeEncounter();
        EncounterResult result = service.add(encounter);
        view.printResult(result);
    }

    private void deleteEncounter() throws DataAccessException {
        view.printHeader(MenuOption.DElETE.getMessage());

        EncounterType type = view.readType();
        List<Encounter> encounters = service.findByType(type);

        int id = view.deleteEncounter(encounters);
        if(id == 0) {
            return;
        }

        EncounterResult result = service.deleteById(id);
        view.printResult(result);
    }

    private void updateEncounter() throws DataAccessException {
        view.printHeader(MenuOption.UPDATE.getMessage());

        EncounterType type = view.readType();
        List<Encounter> encounters = service.findByType(type);

        Encounter encounter = view.updateEncounter(encounters);
        if(encounter == null) {
            return;
        }

        EncounterResult result = service.update(encounter);
        view.printResult(result);
    }
}
