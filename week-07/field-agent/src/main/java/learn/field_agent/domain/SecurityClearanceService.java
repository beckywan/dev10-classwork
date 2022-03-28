package learn.field_agent.domain;

import learn.field_agent.data.AgencyAgentJdbcTemplateRepository;
import learn.field_agent.data.AgencyAgentRepository;
import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.SecurityClearance;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityClearanceService {

    private final SecurityClearanceRepository repository;

    public SecurityClearanceService(SecurityClearanceRepository repository) {
        this.repository = repository;
    }
    
    public SecurityClearance findById(int securityClearanceId) {
        return repository.findById(securityClearanceId);
    }

    public List<SecurityClearance> findAll() {
        return repository.findAll();
    }
    
    public Result<SecurityClearance> add(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = validate(securityClearance);
        if (!result.isSuccess()) {
            return result;
        }

        if (securityClearance.getSecurityClearanceId() != 0) {
            result.addMessage("SecurityClearanceId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        securityClearance = repository.add(securityClearance);
        result.setPayload(securityClearance);
        return result;
    }

    public Result<SecurityClearance> update(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = validate(securityClearance);
        if (!result.isSuccess()) {
            return result;
        }

        if (securityClearance.getSecurityClearanceId() <= 0) {
            result.addMessage("SecurityClearanceId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(securityClearance)) {
            String msg = String.format("SecurityClearanceId: %s, not found", securityClearance.getSecurityClearanceId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }


    private JdbcTemplate jdbcTemplate;

    public Result<SecurityClearance> deleteById(int securityClearanceId) {
        Result<SecurityClearance> result = new Result<>();

        int count = jdbcTemplate.queryForObject(
                "select count(*) from agency_agent where security_clearance_id = ?;", Integer.class, securityClearanceId);
        if (count > 0) {
            result.addMessage("Security clearance is in use and can not be deleted.", ResultType.INVALID);
            return result;
        }

        return result;
    }

    private Result<SecurityClearance> validate(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = new Result<>();

        if (securityClearance == null) {
            result.addMessage("Security clearance cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(securityClearance.getName())) {
            result.addMessage("Name is required", ResultType.INVALID);
        }

        for (SecurityClearance s : repository.findAll()) {
            if (s.getName().matches(securityClearance.getName())) {
                result.addMessage("Duplicate name is not allowed", ResultType.INVALID);
            }
        }

        return result;
    }
}
