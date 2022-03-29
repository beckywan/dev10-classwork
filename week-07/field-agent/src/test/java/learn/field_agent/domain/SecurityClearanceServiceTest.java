package learn.field_agent.domain;

import learn.field_agent.data.LocationRepository;
import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.Location;
import learn.field_agent.models.SecurityClearance;
import org.assertj.core.util.VisibleForTesting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SecurityClearanceServiceTest {
        @Autowired
        SecurityClearanceService service;

        @MockBean
        SecurityClearanceRepository repository;

        @Test
        void shouldNotAddWhenInvalid() {
            SecurityClearance securityClearance = makeSecurityClearance();
            securityClearance.setName("   ");

            Result<SecurityClearance> actual = service.add(securityClearance);
            assertEquals(ResultType.INVALID, actual.getType());

            securityClearance = makeSecurityClearance();
            securityClearance.setName(null);
            actual = service.add(securityClearance);
            assertEquals(ResultType.INVALID, actual.getType());

            securityClearance = makeSecurityClearance();
            securityClearance.setName("\t");
            actual = service.add(securityClearance);
            assertEquals(ResultType.INVALID, actual.getType());
        }

        @Test
        void shouldAdd() {
            SecurityClearance securityClearance = makeSecurityClearance();
            SecurityClearance mockOut = makeSecurityClearance();
            mockOut.setSecurityClearanceId(1);

            when(repository.add(securityClearance)).thenReturn(mockOut);

            Result<SecurityClearance> actual = service.add(securityClearance);
            assertEquals(ResultType.SUCCESS, actual.getType());
            assertEquals(mockOut, actual.getPayload());
        }

        @Test
        void shouldNotUpdateWhenInvalid() {
            SecurityClearance securityClearance = makeSecurityClearance();
            Result<SecurityClearance> actual = service.update(securityClearance);
            assertEquals(ResultType.INVALID, actual.getType());

            securityClearance = makeSecurityClearance();
            securityClearance.setSecurityClearanceId(1);
            securityClearance.setName("");
            actual = service.update(securityClearance);
            assertEquals(ResultType.INVALID, actual.getType());

            securityClearance = makeSecurityClearance();
            securityClearance.setSecurityClearanceId(1);
            securityClearance.setName(null);
            actual = service.update(securityClearance);
            assertEquals(ResultType.INVALID, actual.getType());
        }

        @Test
        void shouldUpdate() {
            SecurityClearance securityClearance = makeSecurityClearance();
            securityClearance.setSecurityClearanceId(1);

            when(repository.update(securityClearance)).thenReturn(true);

            Result<SecurityClearance> actual = service.update(securityClearance);
            assertEquals(ResultType.SUCCESS, actual.getType());
        }

    @Test
    void shouldNotDeleteInvalid() {
        when(repository.deleteById(99)).thenReturn(false);

        Result<SecurityClearance> actual = service.deleteById(99);
        assertEquals(ResultType.NOT_FOUND, actual.getType());


        when(repository.deleteById(1)).thenReturn(false);

        actual = service.deleteById(1);
        assertEquals(ResultType.INVALID, actual.getType());

    }

        @Test
        void shouldDelete() {
            when(repository.deleteById(2)).thenReturn(true);

            Result<SecurityClearance> actual = service.deleteById(2);
            assertEquals(ResultType.SUCCESS, actual.getType());
        }



        SecurityClearance makeSecurityClearance() {
            SecurityClearance securityClearance = new SecurityClearance();
            securityClearance.setName("Test");
            return securityClearance;
        }

}
