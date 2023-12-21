package ontechsystem.hbcha.spring.project.sample.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import ontechsystem.hbcha.spring.project.sample.restapi.dao.RestSampleDAO;

import java.util.HashMap;
import java.util.List;


@Service(value="restSampleService")
public class RestSampleServiceImpl extends EgovAbstractServiceImpl implements RestSampleService{
    @Autowired
    private RestSampleDAO restSampleDAO;
    public List<?> selectSampleList(HashMap<String, Object> params){
        System.out.println("serviceImpl Start");
        List<?> tmp = restSampleDAO.selectSampleList(params);
        System.out.println("serviceImpl End");
        return  tmp;
    }
}
