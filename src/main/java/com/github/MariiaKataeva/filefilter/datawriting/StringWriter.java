package com.github.MariiaKataeva.filefilter.datawriting;

import com.github.MariiaKataeva.filefilter.progInfo.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StringWriter {
    private int itemsCounter;
    private int minLength;
    private int maxLength;
    private BufferedWriter bw;

    public void add(String val, Settings settings) {
        if (this.itemsCounter == 0){
            try {
                boolean isAddingMode = settings.getFileWritingMode() == FileWritingMode.ADD;
                this.bw = new BufferedWriter(new FileWriter(settings.getStringsFilePath(), isAddingMode));
            } catch (IOException e) {
                System.err.println("Ошибка при создании StringWriter: " + e.getMessage());
            }
        }
        try {
            this.bw.write(val);
            this.bw.newLine();
            this.bw.flush();
            this.statisticsUpdate(val);
        } catch (IOException e){
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    private void statisticsUpdate(String val){
        int strLength = val.length();
        if (itemsCounter == 0){
            this.minLength = strLength;
            this.maxLength = strLength;
        } else {
            if (strLength > maxLength){
                this.maxLength = strLength;
            }
            if (strLength < minLength){
                this.minLength = strLength;
            }
        }
        this.itemsCounter++;
    }

    public void printStatistics(StatisticsMode mode){
        if (mode == StatisticsMode.FULL_STATISTICS){
            System.out.println("FOR STRINGS:");
            System.out.println("\tcounter = " + itemsCounter);
            if (itemsCounter == 0){
                return;
            }
            System.out.println("\tmax length = " + maxLength);
            System.out.println("\tmin length = " + minLength);
        } else if (mode == StatisticsMode.SHORT_STATISTICS){
            System.out.println("FOR STRINGS:");
            System.out.println("\tcounter = " + itemsCounter);
        }
    }
}
