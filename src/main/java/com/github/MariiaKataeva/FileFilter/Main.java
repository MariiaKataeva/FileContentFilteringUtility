package com.github.MariiaKataeva.FileFilter;

public class Main {
    public static void main(String[] args) {

        String[] fileNames = args;
        //todo: сделать нормальную обработку аргументов - парсер
        LineHandler lineHandler = new LineHandler();
        FilesReader filesReader = new FilesReader(fileNames, lineHandler);
        filesReader.readData();
        lineHandler.printStatistics();
    }
}