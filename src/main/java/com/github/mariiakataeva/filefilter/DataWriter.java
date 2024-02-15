package com.github.mariiakataeva.filefilter;

import com.github.mariiakataeva.filefilter.progInfo.FileWritingMode;
import com.github.mariiakataeva.filefilter.progInfo.Settings;

//import org.apache.log4j.Logger;
import org.apache.logging.log4j.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataWriter {
    private static final Logger logger = LogManager.getLogger(DataWriter.class);
    private BufferedWriter integerBW;
    private BufferedWriter floatBW;
    private BufferedWriter stringBW;

    public void add(int val, Settings settings, Statistics stat) {
        if (stat.getIntegersCounter() == 0){
            try {
                boolean isAddingMode = settings.getFileWritingMode() == FileWritingMode.ADD;
                this.integerBW = new BufferedWriter(new FileWriter(settings.getIntegersFilePath(), isAddingMode));
            } catch (IOException e) {
                logger.error("Частичная потеря данных: не записан элемент " + val + ".  Причина - ошибка при создании IntegerWriter: " + e.getMessage());
                return;
            }
        }
        try {
            this.integerBW.write(Integer.toString(val));
            this.integerBW.newLine();
            this.integerBW.flush();
            stat.update(val);
        } catch (IOException e){
            logger.error("Частичная потеря данных: не записан элемент " + val + ".  Причина - ошибка при записи в файл: " + e.getMessage());
            return;
        }

        logger.debug("Записан элемент integer: " + val);
    }

    public void add(float val, Settings settings, Statistics stat) {
        if (stat.getFloatsCounter() == 0){
            try {
                boolean isAddingMode = settings.getFileWritingMode() == FileWritingMode.ADD;
                this.floatBW = new BufferedWriter(new FileWriter(settings.getFloatsFilePath(), isAddingMode));
            } catch (IOException e) {
                logger.error("Частичная потеря данных: не записан элемент " + val + ".  Причина - ошибка при создании FloatsWriter: " + e.getMessage());
                return;
            }
        }
        try {
            this.floatBW.write(Float.toString(val));
            this.floatBW.newLine();
            this.floatBW.flush();
            stat.update(val);
        } catch (IOException e){
            logger.error("Частичная потеря данных: не записан элемент " + val + ".  Причина - ошибка при записи в файл: " + e.getMessage());
            return;
        }

        logger.debug("Записан элемент float: " + val);
    }

    public void add(String val, Settings settings, Statistics stat) {
        if (stat.getStringsCounter() == 0){
            try {
                boolean isAddingMode = settings.getFileWritingMode() == FileWritingMode.ADD;
                this.stringBW = new BufferedWriter(new FileWriter(settings.getStringsFilePath(), isAddingMode));
            } catch (IOException e) {
                logger.error("Частичная потеря данных: не записан элемент " + val + ".  Причина - ошибка при создании StringWriter: " + e.getMessage());
                return;
            }
        }
        try {
            this.stringBW.write(val);
            this.stringBW.newLine();
            this.stringBW.flush();
            stat.update(val);
        } catch (IOException e){
            logger.error("Частичная потеря данных: не записан элемент " + val + ".  Причина - ошибка при записи в файл: " + e.getMessage());
            return;
        }

        logger.debug("Записан элемент String: " + val);
    }
}
