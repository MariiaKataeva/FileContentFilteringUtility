package com.github.mariiakataeva.filefilter;

import com.github.mariiakataeva.filefilter.progInfo.Settings;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        logger.trace("Программа запущена.");

        Settings settings = new Settings(args);
        Statistics statistics = new Statistics();

        LineHandler lineHandler = new LineHandler();
        FilesReader filesReader = new FilesReader(settings, lineHandler, statistics);
        filesReader.readData();

        statistics.printStatistics(settings.getStatisticsMode());

        logger.trace("Программа завершает работу.");
    }
}