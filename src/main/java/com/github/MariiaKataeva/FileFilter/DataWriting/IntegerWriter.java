package com.github.MariiaKataeva.FileFilter.DataWriting;

import com.github.MariiaKataeva.FileFilter.ProgInfo.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class IntegerWriter {
    private int itemsCounter;
    private BufferedWriter bw;
    public IntegerWriter(Settings settings){
        try {
            boolean isAddingMode = settings.getFileWritingMode() == FileWritingMode.ADD;
            this.bw = new BufferedWriter(new FileWriter(settings.getIntegersFilePath(), isAddingMode));
        } catch (IOException e) {
            System.err.println("Ошибка при создании IntegerWriter: " + e.getMessage());
        }
    }

    public void add(String str) {
        try {
            this.bw.write(str);
            this.bw.newLine();
            this.bw.flush();
            this.itemsCounter++;
        } catch (IOException e){
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    public int getCounter(){
        return this.itemsCounter;
    }
}
