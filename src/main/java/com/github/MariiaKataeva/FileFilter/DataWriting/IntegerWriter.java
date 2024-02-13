package com.github.MariiaKataeva.FileFilter.DataWriting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class IntegerWriter {
    private int itemsCounter;
    private BufferedWriter bw;
    public IntegerWriter(){
        try {
            this.bw = new BufferedWriter(new FileWriter("outputIntegers.txt"));
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
            System.out.println(this.itemsCounter);//---------------------------------------
        } catch (IOException e){
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    public int getCounter(){
        System.out.println("!!!!!!!!!!!" + this.itemsCounter);//---------------------------------------
        return this.itemsCounter;
    }
}
