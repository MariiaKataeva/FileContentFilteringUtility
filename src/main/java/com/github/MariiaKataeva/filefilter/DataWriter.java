package com.github.MariiaKataeva.filefilter;

import com.github.MariiaKataeva.filefilter.progInfo.FileWritingMode;
import com.github.MariiaKataeva.filefilter.progInfo.Settings;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataWriter {
    private BufferedWriter integerBW;
    private BufferedWriter floatBW;
    private BufferedWriter stringBW;

    public void add(int val, Settings settings, Statistics stat) {
        if (stat.getIntegersCounter() == 0){
            try {
                boolean isAddingMode = settings.getFileWritingMode() == FileWritingMode.ADD;
                this.integerBW = new BufferedWriter(new FileWriter(settings.getIntegersFilePath(), isAddingMode));
            } catch (IOException e) {
                System.err.println("Ошибка при создании IntegerWriter: " + e.getMessage());
                System.out.println("Не записан элемент: " + val);
                return;
            }
        }
        try {
            this.integerBW.write(Integer.toString(val));
            this.integerBW.newLine();
            this.integerBW.flush();
            stat.update(val);
        } catch (IOException e){
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
            System.out.println("Не записан элемент: " + val);
        }
    }

    public void add(float val, Settings settings, Statistics stat) {
        if (stat.getFloatsCounter() == 0){
            try {
                boolean isAddingMode = settings.getFileWritingMode() == FileWritingMode.ADD;
                this.floatBW = new BufferedWriter(new FileWriter(settings.getFloatsFilePath(), isAddingMode));
            } catch (IOException e) {
                System.err.println("Ошибка при создании FloatsWriter: " + e.getMessage());
                System.out.println("Не записан элемент: " + val);
                return;
            }
        }
        try {
            this.floatBW.write(Float.toString(val));
            this.floatBW.newLine();
            this.floatBW.flush();
            stat.update(val);
        } catch (IOException e){
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
            System.out.println("Не записан элемент: " + val);
        }
    }

    public void add(String val, Settings settings, Statistics stat) {
        if (stat.getStringsCounter() == 0){
            try {
                boolean isAddingMode = settings.getFileWritingMode() == FileWritingMode.ADD;
                this.stringBW = new BufferedWriter(new FileWriter(settings.getStringsFilePath(), isAddingMode));
            } catch (IOException e) {
                System.err.println("Ошибка при создании StringWriter: " + e.getMessage());
                System.out.println("Не записан элемент: " + val);
                return;
            }
        }
        try {
            this.stringBW.write(val);
            this.stringBW.newLine();
            this.stringBW.flush();
            stat.update(val);
        } catch (IOException e){
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
            System.out.println("Не записан элемент: " + val);
        }
    }
}
