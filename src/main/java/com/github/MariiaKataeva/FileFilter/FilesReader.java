package com.github.MariiaKataeva.FileFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FilesReader {
    private String[] filePaths;
    private LineHandler handler;


    public FilesReader(String[] args, LineHandler handler){
        this.handler = new LineHandler();
        this.filePaths = args;
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
