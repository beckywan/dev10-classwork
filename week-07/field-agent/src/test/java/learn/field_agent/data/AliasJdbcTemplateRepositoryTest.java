package learn.field_agent.data;

import learn.field_agent.models.Alias;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AliasJdbcTemplateRepositoryTest {


    @Autowired
    AliasJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindById() {
        Alias bob = new Alias(1, "Bob", "Builder",  1);
        Alias jo = new Alias(2, "Jo", "Mama", 2);

        Alias actual = repository.findById(1);
        assertEquals(bob, actual);

        actual = repository.findById(2);
        assertEquals(jo, actual);

        //should not find missing
        actual = repository.findById(3);
        assertEquals(null, actual);
    }

    @Test
    void shouldFindAll() {
        List<Alias> actual = repository.findAll();
        assertEquals(2, actual.size());
    }

    @Test
    void shouldAddAlias() {
        Alias alias = new Alias();
        alias.setName("Test");
        alias.setPersona("Tester");
        alias.setAgentId(1);
        Alias actual = repository.add(alias);
        assertNotNull(actual);
        assertEquals(3, actual.getAliasId());
    }

    @Test
    void shouldUpdateAlias() {
        Alias alias = new Alias();
        alias.setName("Test");
        alias.setPersona("Tester");
        alias.setAliasId(1);
        assertTrue(repository.update(alias));
    }

    @Test
    void shouldDeleteAlias() {
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(2));
    }

}