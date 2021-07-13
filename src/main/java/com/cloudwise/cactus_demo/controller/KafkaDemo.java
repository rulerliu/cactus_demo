//package com.cloudwise.cactus_demo.controller;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///**
// * kafka相关的例子，仅供参考
// */
//@RestController
//public class KafkaDemo {
//    @Autowired
//    private KafkaTemplate<String, Object> kafkaTemplate;
//
//    // 简单的发送消息
//    @GetMapping("/kafka/normal/{message}")
//    public void sendMessage1(@PathVariable("message") String normalMessage) {
//        kafkaTemplate.send("topic1", normalMessage);//往topic1中发送消息
//    }
//
//    //带有回调函数的发送消息
//    @GetMapping("/kafka/callbackOne/{message}")
//    public void sendMessage2(@PathVariable("message") String callbackMessage) {
//        kafkaTemplate.send("topic1", callbackMessage).addCallback(success -> {
//            // 消息发送到的topic
//            String topic = success.getRecordMetadata().topic();
//            // 消息发送到的分区
//            int partition = success.getRecordMetadata().partition();
//            // 消息在分区内的offset
//            long offset = success.getRecordMetadata().offset();
//            System.out.println("发送消息成功:" + topic + "-" + partition + "-" + offset);
//        }, failure -> {
//            System.out.println("发送消息失败:" + failure.getMessage());
//        });
//    }
//
//    // 单条消息的消费监听  没有配置spring.kafka.listener.type=batch这个属性
//    @KafkaListener(topics = {"topic1"})
//    public void onMessage1(ConsumerRecord<?, ?> record){
//        // 消费的哪个topic、partition的消息,打印出消息内容
//        System.out.println("简单消费："+record.topic()+"-"+record.partition()+"-"+record.value());
//    }
//
//    // 批量消息的消费监听  配置spring.kafka.listener.type=batch这个属性
//    @KafkaListener(id = "consumer2",groupId = "felix-group", topics = "topic1")
//    public void onMessage3(List<ConsumerRecord<?, ?>> records) {
//        System.out.println(">>>批量消费一次，records.size()="+records.size());
//        for (ConsumerRecord<?, ?> record : records) {
//            System.out.println(record.value());
//        }
//    }
//
//    // 将这个异常处理器的BeanName放到@KafkaListener注解的errorHandler属性里面
//    @KafkaListener(topics = "topic1",errorHandler="consumerAwareErrorHandler")
//    public void onMessage5(List<ConsumerRecord<?, ?>> records) throws Exception {
//        System.out.println("批量消费一次...");
//        throw new Exception("批量消费-模拟异常");
//    }
//
//    /**
//     * 消息转发 从topic1接收到的消息经过处理后转发到topic2
//     * PS：被注解方法的return值即转发的消息内容
//     **/
//    @KafkaListener(topics = {"topic1"})
//    @SendTo("topic2")
//    public String onMessage7(ConsumerRecord<?, ?> record) {
//        return record.value()+"-forward message";
//    }
//}
