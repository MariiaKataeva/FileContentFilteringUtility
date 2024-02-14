package com.github.MariiaKataeva.filefilter;

import com.github.MariiaKataeva.filefilter.progInfo.Settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FilesReader {
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
            System.err.println("Ошибка: Передан нулевой путь к файлу." + e);
            return;
        } catch (SecurityException e) {
            System.err.println("Ошибка безопасности: Недостаточно прав доступа для создания файла." + e);
            return;
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: Некорректный путь к файлу." + e);
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                this.handler.handle(line, this.settings, this.statistics);
            }
        } catch (IOException e) {
            System.err.println("Ошибка: Не удалось прочитать файл." + e);
        }
    }
}
