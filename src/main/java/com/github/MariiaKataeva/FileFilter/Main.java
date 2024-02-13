package com.github.MariiaKataeva.FileFilter;

import com.github.MariiaKataeva.FileFilter.ProgInfo.Settings;

public class Main {
    public static void main(String[] args) {

        Settings settings = new Settings(args);

        LineHandler lineHandler = new LineHandler(settings);
        FilesReader filesReader = new FilesReader(settings, lineHandler);
        filesReader.readData();
        lineHandler.printStatistics(settings);
    }
}