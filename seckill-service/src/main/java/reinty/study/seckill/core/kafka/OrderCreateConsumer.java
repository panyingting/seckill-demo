package reinty.study.seckill.core.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import reinty.study.seckill.autoconfig.kafka.ConsumerConfiguration;
import reinty.study.seckill.core.common.CheckUtils;
import reinty.study.seckill.core.entity.OrderEntity;
import reinty.study.seckill.core.repository.OrderRepository;
import reinty.study.seckill.core.service.GoodsService;
import reinty.study.seckill.core.service.OrderService;
import reinty.study.seckill.core.service.RedisService;
import reinty.study.seckill.core.service.StockService;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

public class OrderCreateConsumer  implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderCreateConsumer.class);

    TypeReference<Map<String, String>> MAP_REF = new TypeReference<Map<String, String>>(){};

    private static final String topic = "order-stock-process";

    @Autowired
    private ConsumerConfiguration consumerConfiguration;
    @Autowired
    private StockService stockService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private OrderRepository orderRepository;

    private void doCreateOrder(String orderNo, Long promotId, Long goodsId, Long userId){

        boolean decrementSuccess = stockService.decrement(goodsId);
        try {
            CheckUtils.checkIfTrue( !decrementSuccess, "扣减库存失败");

            OrderEntity orderEntity = new OrderEntity();

            orderEntity.setOrderNo(orderNo);
            orderEntity.setStat(2);

            // 忽略其他逻辑，优惠券， 价格分摊.....
            orderRepository.save(orderEntity);
        }catch (Exception ex){
            LOGGER.error("消费创建订单信息失败, orderNo:{}", orderNo, ex);
            orderService.setFailedOrder(orderNo);
            if(decrementSuccess){
                stockService.increment(goodsId);
                // redis库存控制
                long redisStock = redisService.incr( GoodsService.getStockKey(goodsId));
            }
        }

    }


    @Override
    public void afterPropertiesSet() {

        LOGGER.info("kafka订单消费者初始化，consumerConfiguration：{}", consumerConfiguration);
        Properties properties = new Properties();
        properties.put("bootstrap.servers", consumerConfiguration.getBootstrapServers());
        properties.put("group.id", consumerConfiguration.getGroupId());
        properties.put("enable.auto.commit", consumerConfiguration.isAutoCommit());
        properties.put("auto.commit.interval.ms", consumerConfiguration.getCommitIntervalMs());
        properties.put("auto.offset.reset", consumerConfiguration.getOffsetReset());
        properties.put("session.timeout.ms", consumerConfiguration.getSessionTimeoutMs());
        properties.put("key.deserializer", consumerConfiguration.getDeserializer());
        properties.put("value.deserializer", consumerConfiguration.getDeserializer());

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList(topic));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
                Map<String, String> map = JSON.parseObject(record.value(), MAP_REF);
                doCreateOrder( map.get("orderNo"), Long.parseLong(map.get("promotId")), Long.parseLong(map.get("goodsId")),  Long.parseLong(map.get("userId")));
            }
        }
    }
}
