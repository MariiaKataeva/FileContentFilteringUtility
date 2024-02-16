package com.github.mariiakataeva.filefilter;

import com.github.mariiakataeva.filefilter.progInfo.Settings;

import org.apache.logging.log4j.*;
import java.math.BigInteger;
import java.math.BigDecimal;

public class LineHandler {
    private static final Logger logger = LogManager.getLogger(LineHandler.class);
    private final DataWriter dataWriter;

    public LineHandler(){
        this.dataWriter = new DataWriter();

        logger.info("Создан объект класса LineHandler.");
    }

    public void handle(String str, Settings settings, Statistics stat){
        if (str.isEmpty()){
            return;
        }
        try {
            BigInteger val = new BigInteger(str);
            this.dataWriter.add(val, settings, stat);
        } catch (NumberFormatException e1) {
            try {
                BigDecimal val = new BigDecimal(str);
                this.dataWriter.add(val, settings, stat);
            } catch (NumberFormatException e2) {
                this.dataWriter.add(str, settings, stat);
            }
        }
    }
}
