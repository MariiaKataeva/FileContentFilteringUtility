package com.github.MariiaKataeva.FileFilter;

import com.github.MariiaKataeva.FileFilter.ProgInfo.Settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FilesReader {
    private String[] filePaths;
    private LineHandler handler;


    public FilesReader(Settings settings, LineHandler handler){
        this.handler = handler;
        this.filePaths = settings.getInputFiles();
    }

    public void readData(){
        for (String s : this.filePaths){
            this.readFile(s);
        }
    }

    private void readFile(String filePath){
        File file = new File(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                this.handler.handle(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
