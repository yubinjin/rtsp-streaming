package ontechsystem.hbcha.spring.project.sample.restapi.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface RestSampleService {
    public List<?> selectSampleList(HashMap<String, Object> params) throws SQLException;
}
