package learn.solar.data;

import learn.solar.models.Material;
import learn.solar.models.Panel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PanelFileRepository implements PanelRepository {
    private static final String DELIMITER = ",";
    private static final String DELIMITER_REPLACEMENT = "@@@";
    private static final String HEADER = "id,section,row,column,installationYear,material,tracking";

    private String filePath;

    public PanelFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Panel> findAll() throws DataException {

        ArrayList<Panel> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                Panel panel = deserialize(line);
                if (panel != null) {
                    result.add(panel);
                }
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {
            throw new DataException(ex.getMessage(), ex);
        }

        return result;
    }


    @Override
    public List<Panel> findBySection(String section) throws DataException {
        List<Panel> result = new ArrayList<>();
        for (Panel panel : findAll() ) {
            if (Objects.equals(panel.getSection(), section)) {
                result.add(panel);
            }
        }
        return result;
    }

    @Override
    public boolean update(Panel panel) throws DataException {
        List<Panel> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == panel.getId()) {
                all.set(i, panel);
                writeAll(all);
                return true;
            }
        }
        return false;
    }

    @Override
    public Panel add(Panel panel) throws DataException {
        List<Panel> all = findAll();
        panel.setId(getNextId(all));
        all.add(panel);
        writeAll(all);
        return panel;
    }

    @Override
    public boolean deleteById(int panelId) throws DataException {
        List<Panel> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == panelId) {
                all.remove(i);
                writeAll(all);
                return true;
            }
        }
        return false;
    }

    private int getNextId(List<Panel> allPanels) {
        int nextId = 0;
        for (Panel e : allPanels) {
            nextId = Math.max(nextId, e.getId());
        }
        return nextId + 1;
    }

    private void writeAll(List<Panel> panels) throws DataException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(HEADER);
            for (Panel e : panels) {
                writer.println(serialize(e));
            }
        } catch (IOException ex) {
            throw new DataException(ex.getMessage(), ex);
        }
    }

    private String serialize(Panel panel) {
        return String.format("%s,%s,%s,%s,%s,%s,%s",
                panel.getId(),
                panel.getSection(),
                panel.getRow(),
                panel.getColumn(),
                panel.getInstallationYear(),
                panel.getMaterial(),
                panel.getTracking());
    }

    private Panel deserialize(String line) {
        String[] fields = line.split(DELIMITER, -1);
        if (fields.length == 7) {
            Panel panel = new Panel();
            panel.setId(Integer.parseInt(fields[0]));
            panel.setSection(fields[1]);
            panel.setRow(Integer.parseInt(fields[2]));
            panel.setColumn(Integer.parseInt(fields[3]));
            panel.setInstallationYear(Integer.parseInt(fields[4]));
            panel.setMaterial(Material.valueOf(fields[5]));
            panel.setTracking(fields[6]);
            return panel;
        }
        return null;
    }

    private String clean(String value) {
        return value.replace(DELIMITER, DELIMITER_REPLACEMENT);
    }

    private String restore(String value) {
        return value.replace(DELIMITER_REPLACEMENT, DELIMITER);
    }
    
}


