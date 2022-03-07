package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForagerRepository;
import learn.foraging.models.Forage;
import learn.foraging.models.Forager;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ForagerService {

    private final ForagerRepository repository;

    public ForagerService(ForagerRepository repository) {
        this.repository = repository;
    }

    public List<Forager> findByState(String stateAbbr) {
        return repository.findByState(stateAbbr);
    }

    public List<Forager> findByLastName(String prefix) {
        return repository.findAll().stream()
                .filter(i -> i.getLastName().startsWith(prefix))
                .collect(Collectors.toList());
    }

    public Result<Forager> add(Forager forager) throws DataException {
        Result<Forager> result = validate(forager);

        Result<Forager> result1 = checkForDuplicate(forager, result);
        if (result1 != null) return result1;

        if (!result.isSuccess()) {
            return result;
        }

        result.setPayload(repository.add(forager));

        return result;
    }

    private Result<Forager> checkForDuplicate(Forager forager, Result<Forager> result) throws DataException {
        List<Forager> foragers = repository.findAll();
        for (Forager e : foragers) {
            if (Objects.equals(forager.getFirstName(), e.getFirstName())
                    && Objects.equals(forager.getLastName(), e.getLastName())
                    && Objects.equals(forager.getState(), e.getState())) {
                result.addErrorMessage("Forager already exists.");
                return result;
            }
        }
        return null;
    }

    private Result<Forager> validate(Forager forager) {
        Result<Forager> result = new Result<>();

        if (forager == null) {
            result.addErrorMessage("Nothing to save.");
            return result;
        }

        if (forager.getFirstName() == null) {
            result.addErrorMessage("Forager first name is required.");
        }

        if (forager.getLastName() == null) {
            result.addErrorMessage("Forager last name is required.");
        }

        if (forager.getState() == null) {
            result.addErrorMessage("State is required.");
        } else if (forager.getState().length() != 2) {
            result.addErrorMessage("State abbreviation is required.");
        }

        return result;
    }
}
