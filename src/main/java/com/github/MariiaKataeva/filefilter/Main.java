package com.github.MariiaKataeva.filefilter;

import com.github.MariiaKataeva.filefilter.progInfo.Settings;

public class Main {
    public static void main(String[] args) {

        Settings settings = new Settings(args);
        Statistics statistics = new Statistics();

        LineHandler lineHandler = new LineHandler();
        FilesReader filesReader = new FilesReader(settings, lineHandler, statistics);
        filesReader.readData();

        statistics.printStatistics(settings.getStatisticsMode());
    }
}