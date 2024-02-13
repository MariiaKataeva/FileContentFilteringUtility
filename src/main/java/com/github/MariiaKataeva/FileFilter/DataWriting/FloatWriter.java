package com.github.MariiaKataeva.FileFilter.DataWriting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FloatWriter {
    private int itemsCounter;
    private BufferedWriter bw;
    public FloatWriter(){
        try {
            this.bw = new BufferedWriter(new FileWriter("outputFloats.txt"));
        } catch (IOException e) {
            System.err.println("Ошибка при создании FloatWriter: " + e.getMessage());
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
