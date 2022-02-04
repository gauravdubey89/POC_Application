package com.application.serviceImpl;

import com.application.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Arrays;

@Service
@Slf4j
public class LogServiceImpl implements LogService {
    String topicName = "logs";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void logTemplate(String message, String logType, Class fromClass){
        this.sendMessage(logType +" "+String.valueOf(ProcessHandle.current().pid())+" --- "+ fromClass.getPackageName()+"."+fromClass.getSimpleName()+"       :"+message);
    }

    @Override
    public void sendMessage(String message) {

            ListenableFuture<SendResult<String, String>> future =
                    kafkaTemplate.send(topicName, message);

            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    log.info("Sent message=[" +
                            "{} ] with offset=[ {} ]", message, result.getRecordMetadata().offset());
                }

                @Override
                public void onFailure(Throwable ex) {
                    log.error("Unable to send message=[ " +
                            "{} ] due to : {}", message, ex.getMessage());
                }
            });
    }
}
