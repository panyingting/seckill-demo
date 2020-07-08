package reinty.study.seckill.core.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reinty.study.seckill.autoconfig.kafka.ProducerConfiguration;

import java.util.Properties;

@Service
public class MyKafkaProducer implements InitializingBean {


    private Producer<String, String> producer;

    @Autowired
    private ProducerConfiguration producerConfiguration;

    public void sendMsg(String topic, String msg){
        producer.send(new ProducerRecord<>(topic, msg));
    }

    @Override
    public void afterPropertiesSet(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", producerConfiguration.getBootstrapServers());
        properties.put("acks", producerConfiguration.getAcks());
        properties.put("retries", producerConfiguration.getRetries());
        properties.put("batch.size", producerConfiguration.getBatchSize());
        properties.put("linger.ms", producerConfiguration.getLingerMs());
        properties.put("buffer.memory", producerConfiguration.getBufferMemory());
        properties.put("key.serializer", producerConfiguration.getSerializer());
        properties.put("value.serializer", producerConfiguration.getSerializer());
        try {
            producer = new KafkaProducer<>(properties);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            producer.close();
        }
    }
}
