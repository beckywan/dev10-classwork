package learn.reservation.data;

import learn.reservation.models.Host;

import java.util.List;

public interface HostRepository {

    List<Host> findAll();

    Host findByEmail(String email);

}
