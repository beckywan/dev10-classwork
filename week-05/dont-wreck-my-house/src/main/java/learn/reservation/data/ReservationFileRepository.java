package learn.reservation.data;

import learn.reservation.models.Guest;
import learn.reservation.models.Host;
import learn.reservation.models.Reservation;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReservationFileRepository implements ReservationRepository{
    private static final String HEADER = "id,start_date,end_date,guest_id,total";
    private final String directory;

    public ReservationFileRepository(String directory) {
        this.directory = directory;
    }

    private String getFilePath(String uuid) {
        return Paths.get(directory, uuid + ".csv").toString();
    }

    @Override
    public List<Reservation> findByUuid(String uuid) {
        ArrayList<Reservation> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath(uuid)))) {

            reader.readLine(); // read header

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (fields.length == 5) {
                    result.add(deserialize(fields, uuid));
                }
            }
        } catch (IOException ex) {
            // don't throw on read
        }
        return result;
    }

    @Override
    public Reservation add(Reservation reservation) throws DataException {
        List<Reservation> all = findByUuid(reservation.getUUID());
        all.add(reservation);
        writeAll(all, reservation.getUUID());
        return reservation;
    }

    @Override
    public boolean update(Reservation reservation) throws DataException {
        List<Reservation> all = findByUuid(reservation.getUUID());
        for (int i = 0; i < all.size(); i++) {
            if (Objects.equals(all.get(i).getId(), reservation.getId())) {
                all.set(i, reservation);
                writeAll(all, reservation.getUUID());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Reservation reservation) throws DataException {
        List<Reservation> all = findByUuid(reservation.getUUID());
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == reservation.getId()) {
                all.remove(i);
                writeAll(all, reservation.getUUID());
                return true;
            }
        }
        return false;
    }


    private void writeAll(List<Reservation> reservations, String id) throws DataException {
        try (PrintWriter writer = new PrintWriter(getFilePath(id))) {

            writer.println(HEADER);

            for (Reservation reservation : reservations) {
                writer.println(serialize(reservation));
            }
        } catch (FileNotFoundException ex) {
            throw new DataException(ex);
        }
    }

    private String serialize(Reservation reservation) {
        return String.format("%s,%s,%s,%s,%s",
                reservation.getId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getGuest().getId(),
                reservation.getTotal());
    }

    //id,start_date,end_date,guest_id,total

    private Reservation deserialize(String[] fields, String uuid) {
        Reservation result = new Reservation();
        result.setId(Integer.parseInt(fields[0]));
        result.setUUID(uuid);
        result.setStartDate(LocalDate.parse(fields[1]));
        result.setEndDate(LocalDate.parse(fields[2]));

        Guest guest = new Guest();
        guest.setId(Integer.parseInt(fields[3]));
        result.setGuest(guest);

        Host host = new Host();
        host.setId(uuid);
        result.setGuest(guest);

        result.setTotal(new BigDecimal(fields[4]));

        return result;
    }
}
