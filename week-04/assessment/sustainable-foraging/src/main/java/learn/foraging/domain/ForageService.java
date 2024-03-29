package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForageRepository;
import learn.foraging.data.ForagerRepository;
import learn.foraging.data.ItemRepository;
import learn.foraging.models.Category;
import learn.foraging.models.Forage;
import learn.foraging.models.Forager;
import learn.foraging.models.Item;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ForageService {

    private final ForageRepository forageRepository;
    private final ForagerRepository foragerRepository;
    private final ItemRepository itemRepository;

    public ForageService(ForageRepository forageRepository, ForagerRepository foragerRepository, ItemRepository itemRepository) {
        this.forageRepository = forageRepository;
        this.foragerRepository = foragerRepository;
        this.itemRepository = itemRepository;
    }

    public List<Forage> findByDate(LocalDate date) {

        Map<String, Forager> foragerMap = foragerRepository.findAll().stream()
                .collect(Collectors.toMap(i -> i.getId(), i -> i));
        Map<Integer, Item> itemMap = itemRepository.findAll().stream()
                .collect(Collectors.toMap(i -> i.getId(), i -> i));

        List<Forage> result = forageRepository.findByDate(date);
        for (Forage forage : result) {
            forage.setForager(foragerMap.get(forage.getForager().getId()));
            forage.setItem(itemMap.get(forage.getItem().getId()));
        }

        return result;
    }

    public void calculateKgPerItem(List<Forage> forages) {
        Map<Item, Double> kgPerItem = forages.stream()
                .collect(Collectors.groupingBy(Forage::getItem,
                        Collectors.summingDouble(h -> h.getKilograms())));

        for (Item item : kgPerItem.keySet()) {
            Optional<Forage> first = forages.stream()
                    .filter(forage -> forage.getItem().equals(item))
                    .findFirst();

            if (first.isPresent()) {
                Forage f = first.get();
                System.out.printf("%s: %.2f kg%n", f.getItem().getName(), kgPerItem.get(item));

            }
        }

    }


    public void calculateValuePerCategory(List<Forage> forages) {
        Map<Item, Double> valuePerItem = forages.stream()
                .collect(Collectors.groupingBy(Forage::getItem,
                        Collectors.summingDouble(h -> h.getValue().doubleValue())));

        BigDecimal edibleValue = new BigDecimal(0);
        BigDecimal medValue = new BigDecimal(0);


        for (Item item : valuePerItem.keySet()) {
            if (item.getCategory() == Category.EDIBLE) {
                edibleValue = edibleValue.add(new BigDecimal(valuePerItem.get(item)));
            } else if (item.getCategory() == Category.MEDICINAL) {
                medValue = medValue.add(new BigDecimal(valuePerItem.get(item)));
            }
        }

        System.out.printf("%s: $%.2f%n", Category.EDIBLE, edibleValue);
        System.out.printf("%s: $%.2f%n", Category.MEDICINAL, medValue);
        System.out.printf("%s: $0.00%n", Category.INEDIBLE);
        System.out.printf("%s: $0.00%n", Category.POISONOUS);
    }

    public Result<Forage> add(Forage forage) throws DataException {
        Result<Forage> result = validate(forage);
        if (!result.isSuccess()) {
            return result;
        }

        LocalDate date = forage.getDate();
        Result<Forage> result1 = checkForDuplicate(forage, result, date);
        if (result1 != null) return result1;

        result.setPayload(forageRepository.add(forage));

        return result;
    }

    private Result<Forage> checkForDuplicate(Forage forage, Result<Forage> result, LocalDate date) throws DataException {
        List<Forage> forages = forageRepository.findByDate(date);
        for (Forage e : forages) {
            if (Objects.equals(forage.getForager().getId(), e.getForager().getId())
                    && Objects.equals(forage.getItem().getId(), e.getItem().getId())
                    && Objects.equals(forage.getDate(), e.getDate())) {
                result.addErrorMessage("Forage already exists.");
                return result;
            }
        }
        return null;
    }

    public int generate(LocalDate start, LocalDate end, int count) throws DataException {

        if (start == null || end == null || start.isAfter(end) || count <= 0) {
            return 0;
        }

        count = Math.min(count, 500);

        ArrayList<LocalDate> dates = new ArrayList<>();
        while (!start.isAfter(end)) {
            dates.add(start);
            start = start.plusDays(1);
        }

        List<Item> items = itemRepository.findAll();
        List<Forager> foragers = foragerRepository.findAll();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            Forage forage = new Forage();
            forage.setDate(dates.get(random.nextInt(dates.size())));
            forage.setForager(foragers.get(random.nextInt(foragers.size())));
            forage.setItem(items.get(random.nextInt(items.size())));
            forage.setKilograms(random.nextDouble() * 5.0 + 0.1);
            forageRepository.add(forage);
        }

        return count;
    }

    private Result<Forage> validate(Forage forage) {

        Result<Forage> result = validateNulls(forage);
        if (!result.isSuccess()) {
            return result;
        }

        validateFields(forage, result);
        if (!result.isSuccess()) {
            return result;
        }

        validateChildrenExist(forage, result);

        return result;
    }

    private Result<Forage> validateNulls(Forage forage) {
        Result<Forage> result = new Result<>();

        if (forage == null) {
            result.addErrorMessage("Nothing to save.");
            return result;
        }

        if (forage.getDate() == null) {
            result.addErrorMessage("Forage date is required.");
        }

        if (forage.getForager() == null) {
            result.addErrorMessage("Forager is required.");
        }

        if (forage.getItem() == null) {
            result.addErrorMessage("Item is required.");
        }

        return result;
    }

    private void validateFields(Forage forage, Result<Forage> result) {
        // No future dates.
        if (forage.getDate().isAfter(LocalDate.now())) {
            result.addErrorMessage("Forage date cannot be in the future.");
        }

        if (forage.getKilograms() <= 0 || forage.getKilograms() > 250.0) {
            result.addErrorMessage("Kilograms must be a positive number less than 250.0");
        }
    }

    private void validateChildrenExist(Forage forage, Result<Forage> result) {

        if (forage.getForager().getId() == null
                || foragerRepository.findById(forage.getForager().getId()) == null) {
            result.addErrorMessage("Forager does not exist.");
        }

        if (itemRepository.findById(forage.getItem().getId()) == null) {
            result.addErrorMessage("Item does not exist.");
        }
    }
}
