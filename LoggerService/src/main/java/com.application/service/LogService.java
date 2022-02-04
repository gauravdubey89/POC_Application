package com.application.service;

import com.application.constants.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class LogService {

    @KafkaListener(topics = "logs", groupId = "foo")
    public void listenGroupFoo(String message) throws IOException {
        log.info("Received Message in group foo: {}", message);
        try {
            StringBuilder currentWorkingDir = new StringBuilder(System.getProperty("user.dir"));
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat(Constant.LOGFILENAMEDATEFORMATE);
            String strDate = formatter.format(date);
            String fileName = currentWorkingDir + "/" + Constant.DIRECTORY + "/" + Constant.DIRECTORY + "-" + strDate + ".txt";
            String directoryName = String.valueOf(currentWorkingDir.append("/" + Constant.DIRECTORY + "/"));
            File file = new File(String.valueOf(fileName));
            File directory = new File(String.valueOf(directoryName));
            if (!directory.exists()) {
                directory.mkdir();
                if (!file.exists()) {
                    file.getParentFile().mkdir();
                    file.createNewFile();
                }
            }
            writeLogsFile(file, message);
        }catch (Exception exception){
            log.error("Exception :{}", exception);
        }
    }

    public void writeLogsFile(File file, String message) throws IOException {
        BufferedWriter bw = null;
        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
        bw = new BufferedWriter(fw);
        bw.write(new SimpleDateFormat(Constant.LOGGENERATIONDATEFORMATE).format(new Date()) + "  " + message);
        bw.newLine();
        bw.close();
    }

}
