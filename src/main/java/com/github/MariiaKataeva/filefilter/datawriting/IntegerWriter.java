package com.github.MariiaKataeva.filefilter.datawriting;

import com.github.MariiaKataeva.filefilter.Statistics;
import com.github.MariiaKataeva.filefilter.progInfo.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class IntegerWriter {
    private BufferedWriter bw;

    public void add(int val, Settings settings, Statistics stat) {
        if (stat.getIntegersCounter() == 0){
            try {
                boolean isAddingMode = settings.getFileWritingMode() == FileWritingMode.ADD;
                this.bw = new BufferedWriter(new FileWriter(settings.getIntegersFilePath(), isAddingMode));
            } catch (IOException e) {
                System.err.println("Ошибка при создании IntegerWriter: " + e.getMessage());
            }
        }
        try {
            this.bw.write(Integer.toString(val));
            this.bw.newLine();
            this.bw.flush();
            stat.update(val);
        } catch (IOException e){
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
