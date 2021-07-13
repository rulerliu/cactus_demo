//package com.cloudwise.cactus_demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.KafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
//
//@Configuration
//public class KafkaConfig {
//    @Bean
//    public KafkaListenerContainerFactory<?> batchFactory(ConsumerFactory consumerFactory){
//        ConcurrentKafkaListenerContainerFactory<Integer,String> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory);
//        //消费者数量,最好跟topic的分区数一致
//        factory.setConcurrency(10);
//        // 被过滤的消息将被丢弃
//        factory.setAckDiscarded(true);
//        // 消息过滤策略
//        factory.setRecordFilterStrategy(consumerRecord -> {
//            if (Integer.parseInt(consumerRecord.value().toString()) % 2 == 0) {
//                return false;
//            }
//            //返回true消息则被过滤
//            return true;
//        });
//        factory.getContainerProperties().setPollTimeout(500);
//        return factory;
//    }
//
//    //kafka异常处理器，异常处理器的message.getPayload()也可以拿到各条消息的信息
//    @Bean
//    public ConsumerAwareListenerErrorHandler consumerAwareErrorHandler() {
//        return (message, exception, consumer) -> {
//            System.out.println("消费异常："+message.getPayload());
//            return null;
//        };
//    }
//}
