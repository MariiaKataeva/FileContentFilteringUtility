package com.github.MariiaKataeva.filefilter;

import com.github.MariiaKataeva.filefilter.progInfo.Settings;

public class Main {
    public static void main(String[] args) {

        Settings settings = new Settings(args);

        LineHandler lineHandler = new LineHandler();
        FilesReader filesReader = new FilesReader(settings, lineHandler);
        filesReader.readData();
        lineHandler.printStatistics(settings);
    }
}