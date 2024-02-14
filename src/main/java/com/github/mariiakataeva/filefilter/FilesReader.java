package com.github.mariiakataeva.filefilter;

import com.github.mariiakataeva.filefilter.progInfo.Settings;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FilesReader {
    private static final Logger logger = Logger.getLogger(FilesReader.class);
    private final Settings settings;
    private final LineHandler handler;
    private final Statistics statistics;


    public FilesReader(Settings settings, LineHandler handler, Statistics stat){
        this.handler = handler;
        this.settings = settings;
        this.statistics = stat;
    }

    public void readData(){
        if (this.settings.getInputFiles() == null){
            return;
        }
        for (String s : this.settings.getInputFiles()){
            this.readFile(s);
        }
    }

    private void readFile(String filePath){
        File file;
        try {
            file = new File(filePath);
        } catch (NullPointerException e) {
            logger.error("Ошибка: Передан нулевой путь к файлу:" + filePath + ".  " + e);
            return;
        } catch (SecurityException e) {
            logger.error("Ошибка безопасности: Недостаточно прав доступа для создания файла " + filePath + ".  " + e);
            return;
        } catch (IllegalArgumentException e) {
            logger.error("Ошибка: Некорректный путь к файлу: " + filePath + ".  " + e);
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                this.handler.handle(line, this.settings, this.statistics);
            }
        } catch (IOException e) {
            logger.error("Ошибка: Не удалось прочитать файл " + filePath + ".  " + e);
        }
    }
}
