package com.github.MariiaKataeva.FileFilter.DataWriting;

import com.github.MariiaKataeva.FileFilter.ProgInfo.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FloatWriter {
    private int itemsCounter;
    private float minVal;
    private float maxVal;
    private float sumVal;
    private float avgVal;
    private BufferedWriter bw;
    public void add(float val, Settings settings) {
        if (this.itemsCounter == 0){
            try {
                boolean isAddingMode = settings.getFileWritingMode() == FileWritingMode.ADD;
                this.bw = new BufferedWriter(new FileWriter(settings.getFloatsFilePath(), isAddingMode));
            } catch (IOException e) {
                System.err.println("Ошибка при создании FloatsWriter: " + e.getMessage());
            }
        }
        try {
            this.bw.write(Float.toString(val));
            this.bw.newLine();
            this.bw.flush();
            this.statisticsUpdate(val);
        } catch (IOException e){
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    private void statisticsUpdate(float val){
        if (itemsCounter == 0){
            this.maxVal = val;
            this.minVal = val;
        } else {
            if (val > maxVal){
                this.maxVal = val;
            }
            if (val < minVal){
                this.minVal = val;
            }
        }
        this.sumVal += val;
        this.itemsCounter++;
        this.avgVal = sumVal / itemsCounter;
    }

    public void printStatistics(StatisticsMode mode){
        if (mode == StatisticsMode.FULL_STATISTICS){
            System.out.println("FOR FLOATS:");
            System.out.println("\tcounter = " + itemsCounter);
            if (itemsCounter == 0){
                return;
            }
            System.out.println("\tmax value = " + maxVal);
            System.out.println("\tmin val = " + minVal);
            System.out.println("\tsum = " + sumVal);
            System.out.println("\tavg value = " + avgVal);
        } else if (mode == StatisticsMode.SHORT_STATISTICS){
            System.out.println("FOR FLOATS:");
            System.out.println("\tcounter = " + itemsCounter);
        }
    }
}
