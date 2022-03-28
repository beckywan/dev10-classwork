package learn.field_agent.data;

import learn.field_agent.models.Agency;
import learn.field_agent.models.Agent;
import learn.field_agent.models.Location;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SecurityClearanceJdbcTemplateRepositoryTest {


    @Autowired
    SecurityClearanceJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindById() {
        SecurityClearance secret = new SecurityClearance(1, "Secret");
        SecurityClearance topSecret = new SecurityClearance(2, "Top Secret");

        SecurityClearance actual = repository.findById(1);
        assertEquals(secret, actual);

        actual = repository.findById(2);
        assertEquals(topSecret, actual);

        //should not find missing
        actual = repository.findById(3);
        assertEquals(null, actual);
    }

    @Test
    void shouldFindAll() {
        List<SecurityClearance> actual = repository.findAll();
        assertEquals(2, actual.size());
    }

    @Test
    void shouldAddSecurityClearance() {
        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setName("Test");
        SecurityClearance actual = repository.add(securityClearance);
        assertNotNull(actual);
        assertEquals(3, actual.getSecurityClearanceId());
    }

    @Test
    void shouldUpdateSecurityClearance() {

        SecurityClearance securityClearance = new SecurityClearance();
        securityClearance.setName("Test");
        securityClearance.setSecurityClearanceId(2);
        assertTrue(repository.update(securityClearance));
    }

    @Test
    void shouldDeleteSecurityClearance() {
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(2));
    }

}