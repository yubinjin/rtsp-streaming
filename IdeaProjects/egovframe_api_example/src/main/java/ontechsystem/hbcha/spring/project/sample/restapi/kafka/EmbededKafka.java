package ontechsystem.hbcha.spring.project.sample.restapi.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class EmbededKafka {
    //https://akageun.github.io/2020/05/02/java-kafka-client-example.html[참고]
    private HashMap<String,?> VALUE = new HashMap<String,Object>() ;
    private Properties pub_props = new Properties();
    private String PUB_TOPIC_NAME ;

    public void setConnectionKafkaPUB(String BOOTSTRAP_SERVERS, String TOPIC_NAME, HashMap<String,?> VALUE){
//        this.pub_props.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
        this.pub_props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        this.pub_props.put(ProducerConfig.ACKS_CONFIG, "all");
        this.pub_props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        this.pub_props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        this.VALUE = VALUE;
        this.PUB_TOPIC_NAME = TOPIC_NAME;
    }

    public HashMap<String,Object> getConnectionKafkaPUB(){
        HashMap<String,Object> returnDATA = new HashMap<>();
        returnDATA.put("TOPIC",this.PUB_TOPIC_NAME);
        returnDATA.put("PROPS",this.pub_props);
        returnDATA.put("VALUE",this.VALUE);
        return  returnDATA;
    }

    public HashMap<String,String> sendKafkaData() {
        HashMap<String,String> returnData = new HashMap<>();
        KafkaProducer<String,String> producer = new KafkaProducer<>(this.pub_props);
        try{
            producer.send(new ProducerRecord<String,String>(this.PUB_TOPIC_NAME, this.VALUE.toString()));
            returnData.put("result","success");
        }catch (Exception e){
            returnData.put("result","fail");
            returnData.put("Excpet", e.toString());
        }

        return returnData;
    }


}
