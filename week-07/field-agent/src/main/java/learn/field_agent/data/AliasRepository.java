package learn.field_agent.data;

import learn.field_agent.models.Alias;
import learn.field_agent.models.SecurityClearance;

import java.util.List;

public interface AliasRepository {

    Alias findById(int aliasId);

    List<Alias> findAll();

    Alias add(Alias alias);

    boolean update(Alias alias);

    boolean deleteById(int aliasId);

}
