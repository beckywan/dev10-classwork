package learn.reservation.data;

import learn.reservation.models.Host;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.junit.jupiter.api.Assertions.*;

class HostFileRepositoryTest {

    HostFileRepository repository = new HostFileRepository("./dont-wreck-my-house-data/hosts.csv");

    @Test
    void shouldFindAll() {
        assertEquals(1000, repository.findAll().size());
    }

    //id,last_name,email,phone,address,city,state,postal_code,standard_rate,weekend_rate

    @Test
    void shouldFindHost() {
        Host host = repository.findByEmail("eyearnes0@sfgate.com");
        assertNotNull(host);
        assertEquals("3edda6bc-ab95-49a8-8962-d50b53f84b15", host.getId());
        assertEquals("Yearnes", host.getLastName());
        assertEquals("(806) 1783815", host.getPhone());
        assertEquals("3 Nova Trail", host.getAddress());
        assertEquals("Amarillo", host.getCity());
        assertEquals("TX", host.getState());
        assertEquals(79182, host.getPostalCode());
        assertEquals(new BigDecimal(340), host.getStandardRate());
        assertEquals(new BigDecimal(425), host.getWeekendRate());

    }

    @Test
    void shouldNotFindHost() {
        Host host = repository.findByEmail("TEST@EMAIL.com");
        assertNull(host);
    }
}