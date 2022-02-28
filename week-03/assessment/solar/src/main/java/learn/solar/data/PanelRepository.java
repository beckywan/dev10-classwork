package learn.solar.data;

import learn.solar.models.Panel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface PanelRepository {

    List<Panel> findAll() throws DataException;

    List<Panel> findBySection(String section) throws DataException;

    boolean update(Panel panel) throws DataException;

    Panel add(Panel panel) throws DataException;

    boolean deleteById(int panelId) throws DataException;
}
