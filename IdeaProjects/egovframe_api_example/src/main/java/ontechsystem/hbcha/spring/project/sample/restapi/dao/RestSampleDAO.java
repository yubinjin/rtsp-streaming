package ontechsystem.hbcha.spring.project.sample.restapi.dao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;

import java.util.HashMap;
import java.util.List;

@Repository("restSampleDAO")
public class RestSampleDAO extends EgovAbstractMapper{

    public List<?> selectSampleList(HashMap<String, Object> params){
        System.out.println("start DAO");
        List<?> tmp = selectList("selectSampleList", params);
        System.out.println("end DAO");
        return tmp;

    }
}
