package learn.reservation.models;

import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;

public class Reservation {

    //id,start_date,end_date,guest_id,total

    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Guest guest;
    private Host host;
    private BigDecimal total;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    private String UUID;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return id == that.id && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(guest, that.guest) && Objects.equals(host, that.host) && Objects.equals(total, that.total) && Objects.equals(UUID, that.UUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, guest, host, total, UUID);
    }

}
