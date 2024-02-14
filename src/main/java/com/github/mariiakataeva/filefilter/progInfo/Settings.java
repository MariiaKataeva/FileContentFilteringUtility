package com.github.mariiakataeva.filefilter.progInfo;

import org.apache.commons.cli.*;
import org.apache.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Settings {
    private static final Logger logger = Logger.getLogger(Settings.class);
    private StatisticsMode statisticsMode;
    private FileWritingMode fileWritingMode;
    private String integersFilePath;
    private String floatsFilePath;
    private String stringsFilePath;
    private String[] inputFiles;

    public Settings(String[] args){
        this.statisticsMode = StatisticsMode.NO_STATISTICS;
        this.fileWritingMode = FileWritingMode.REWRITE;
        this.integersFilePath = "integers.txt";
        this.floatsFilePath = "floats.txt";
        this.stringsFilePath = "strings.txt";

        this.argsParsing(args);
    }

    private void argsParsing(String[] commandLineArguments) {
        Options posixOptions = defOptions();

        CommandLineParser cmdLinePosixParser = new PosixParser();
        CommandLine commandLine;
        try {
            commandLine = cmdLinePosixParser.parse(posixOptions, commandLineArguments);
        } catch (ParseException e){
            logger.warn("Ошибка при парсинге аргументов командной строки.  " + e);
            logger.info("Взяты настройки по умолчанию.");
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("utility-name", posixOptions);
            return;
        }


        if (commandLine.hasOption('p')) {
            String[] arguments = commandLine.getOptionValues('p');
            this.integersFilePath = arguments[0] + this.integersFilePath;
            this.floatsFilePath = arguments[0] + this.floatsFilePath;
            this.stringsFilePath = arguments[0] + this.stringsFilePath;
        }
        if (commandLine.hasOption('o')) {
            String[] arguments = commandLine.getOptionValues('o');
            Path directory = Paths.get(arguments[0]);
            this.integersFilePath = (directory.resolve(integersFilePath)).toString();
            this.floatsFilePath = (directory.resolve(floatsFilePath)).toString();
            this.stringsFilePath = (directory.resolve(stringsFilePath)).toString();
        }
        if (commandLine.hasOption('a')){
            commandLine.getOptionValues('a');
            this.fileWritingMode = FileWritingMode.ADD;
        }
        if (commandLine.hasOption('s')){
            commandLine.getOptionValues('s');
            this.statisticsMode = StatisticsMode.SHORT_STATISTICS;
        }
        if (commandLine.hasOption('f')){
            commandLine.getOptionValues('f');
            this.statisticsMode = StatisticsMode.FULL_STATISTICS;
        }

        this.inputFiles = commandLine.getArgs();
    }


    private Options defOptions(){
        Option option1 = new Option("o", "output_path", true, "output path");
        option1.setArgs(1);
        option1.setOptionalArg(false);

        Option option2 = new Option("p", "prefix", true, "prefix for output files");
        option2.setArgs(1);
        option2.setOptionalArg(false);

        Option option3 = new Option("a", "add", true, "adding to existing files mode");
        option3.setArgs(0);

        Option option4 = new Option("s", "short_stat", true, "short statistics mode");
        option4.setArgs(0);

        Option option5 = new Option("f", "full_stat", true, "full statistics mode");
        option5.setArgs(0);


        Options posixOptions = new Options();
        posixOptions.addOption(option1);
        posixOptions.addOption(option2);
        posixOptions.addOption(option3);
        posixOptions.addOption(option4);
        posixOptions.addOption(option5);

        return posixOptions;
    }




    public StatisticsMode getStatisticsMode() {
        return statisticsMode;
    }

    public FileWritingMode getFileWritingMode() {
        return fileWritingMode;
    }
    public String getIntegersFilePath() {
        return integersFilePath;
    }

    public String getFloatsFilePath() {
        return floatsFilePath;
    }

    public String getStringsFilePath() {
        return stringsFilePath;
    }

    public String[] getInputFiles() {
        return inputFiles;
    }
}
