package com.github.MariiaKataeva.filefilter;

import com.github.MariiaKataeva.filefilter.progInfo.Settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FilesReader {
    private Settings settings;
    private LineHandler handler;
    private Statistics statistics;


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
        File file = new File(filePath);
        if (!file.exists()){
            System.out.println("No such file: " + filePath);
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                this.handler.handle(line, this.settings, this.statistics);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
