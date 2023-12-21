package ontechsystem.hbcha.spring.project.sample.restapi.controller;


import ontechsystem.hbcha.spring.project.sample.restapi.kafka.KafkaConsumerHandler;

import java.util.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import ontechsystem.hbcha.spring.project.sample.restapi.kafka.EmbededKafka;
import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ontechsystem.hbcha.spring.project.sample.restapi.service.RestSampleService;
@RestController
@RequestMapping("/rest")
public class RestSampleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassName.class);
    private static final Logger logger = LoggerFactory.getLogger(RestSampleController.class);
    //    @Resource 어노테이션은 Name으로 Bean을 지정한다.(필드/메서드에만 적용 가능)
    @Resource(name = "restSampleService")
    private RestSampleService restSampleService;

    // GET 메서드로만 "/rest/example" 접근 가능
    @RequestMapping(value = "/example/{custom_value}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String,Object> selectSampleList(HttpServletRequest request, @PathVariable String custom_value,  @RequestParam HashMap<String, Object> params ) throws IOException, InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        params.put("custom_value", custom_value);
        List<?> oList;
        try{
            System.out.println("Start");
            oList = restSampleService.selectSampleList(params);
            map.put("params",params);
            map.put("resultList",oList);
            int dataCnt = oList.size();
            map.put("dataCnt",dataCnt);
            map.put("Result","success");
        }catch (SQLException e) {
            map.put("Result","fail");
        }
        EmbededKafka eKafka = new EmbededKafka();
        eKafka.setConnectionKafkaPUB("localhost:9092","test_java",map);
        System.out.println(eKafka.getConnectionKafkaPUB().toString());
        System.out.println("kafka");
        map.put("kafkaRESULT",eKafka.sendKafkaData());
        // Kafka Consumer 시작
        KafkaConsumerHandler Kafkaconsumer = new KafkaConsumerHandler("127.0.0.1:9092","my-consumer-group-0","test_java");
        new Thread(() -> Kafkaconsumer.startConsuming()).start();// 이 부분을 추가하여 Kafka Consumer를 시작합니다.
        return map;
    }

    // GET 메서드로만 "/rest/example/test1/{custom_value}" 접근 가능
    @RequestMapping(value = "/example/test1/{custom_value}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String,Object> selectSampleList2(HttpServletRequest request, @PathVariable String custom_value,  @RequestParam HashMap<String, Object> params ) throws IOException, InterruptedException {
        HashMap<String, Object> map = new HashMap<>();
        params.put("custom_value", custom_value);
        List<?> oList;
        try{
            System.out.println("Start");
            oList = restSampleService.selectSampleList(params);
            map.put("params",params);
            map.put("resultList",oList);
            int dataCnt = oList.size();
            map.put("dataCnt",dataCnt);
            map.put("Result","success");
        }catch (SQLException e) {
            map.put("Result","fail");
        }
        EmbededKafka eKafka = new EmbededKafka();
        eKafka.setConnectionKafkaPUB("localhost:9092","test1_java",map);
        System.out.println(eKafka.getConnectionKafkaPUB().toString());
        System.out.println("kafka");
        map.put("kafkaRESULT",eKafka.sendKafkaData());
        // Kafka Consumer 시작
        KafkaConsumerHandler Kafkaconsumer = new KafkaConsumerHandler("127.0.0.1:9092","my-consumer-group-0","test1_java");
        new Thread(() -> Kafkaconsumer.startConsuming()).start();// 이 부분을 추가하여 Kafka Consumer를 시작합니다.*/
        return map;
    }



}
