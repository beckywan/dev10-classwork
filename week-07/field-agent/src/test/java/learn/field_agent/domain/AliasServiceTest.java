package learn.field_agent.domain;

import learn.field_agent.data.AliasRepository;
import learn.field_agent.models.Alias;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)

public class AliasServiceTest {

        @Autowired
        AliasService service;

        @MockBean
        AliasRepository repository;

        @Test
        void shouldNotAddWhenInvalid() {
            Alias alias = makeAlias();
            alias.setName("   ");

            Result<Alias> actual = service.add(alias);
            assertEquals(ResultType.INVALID, actual.getType());

            alias = makeAlias();
            alias.setName(null);
            actual = service.add(alias);
            assertEquals(ResultType.INVALID, actual.getType());

            alias = makeAlias();
            alias.setName("\t");
            actual = service.add(alias);
            assertEquals(ResultType.INVALID, actual.getType());
        }

        @Test
        void shouldAdd() {
            Alias alias = makeAlias();
            Alias mockOut = makeAlias();
            mockOut.setAliasId(1);

            when(repository.add(alias)).thenReturn(mockOut);

            Result<Alias> actual = service.add(alias);
            assertEquals(ResultType.SUCCESS, actual.getType());
            assertEquals(mockOut, actual.getPayload());
        }

        @Test
        void shouldNotUpdateWhenInvalid() {
            Alias alias = makeAlias();
            Result<Alias> actual = service.update(alias);
            assertEquals(ResultType.INVALID, actual.getType());

            alias = makeAlias();
            alias.setAliasId(1);
            alias.setName("");
            actual = service.update(alias);
            assertEquals(ResultType.INVALID, actual.getType());

            alias = makeAlias();
            alias.setAliasId(1);
            alias.setName(null);
            actual = service.update(alias);
            assertEquals(ResultType.INVALID, actual.getType());
        }

        @Test
        void shouldUpdate() {
            Alias alias = makeAlias();
            alias.setAliasId(1);

            when(repository.update(alias)).thenReturn(true);

            Result<Alias> actual = service.update(alias);
            assertEquals(ResultType.SUCCESS, actual.getType());
        }

    @Test
    void shouldNotDeleteInvalid() {
        assertFalse(service.deleteById(999));
    }

        Alias makeAlias() {
            Alias alias = new Alias();
            alias.setName("Test");
            return alias;
        }
    }
