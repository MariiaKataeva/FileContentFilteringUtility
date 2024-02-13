package com.github.MariiaKataeva.FileFilter;

public class Main {
    public static void main(String[] args) {

        String[] fileNames = args;
        //todo: сделать нормальную обработку аргументов - парсер
        LineHandler itemsHandler = new LineHandler();
        FilesReader filesReader = new FilesReader(fileNames, itemsHandler);
        filesReader.readData();
    }
}