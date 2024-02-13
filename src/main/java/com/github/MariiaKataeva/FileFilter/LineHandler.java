package com.github.MariiaKataeva.FileFilter;

import com.github.MariiaKataeva.FileFilter.DataWriting.*;
import com.github.MariiaKataeva.FileFilter.ProgInfo.Settings;

public class LineHandler {
    private final StringWriter stringWriter;
    private final IntegerWriter integerWriter;
    private final FloatWriter floatWriter;

    public LineHandler(){
        this.floatWriter = new FloatWriter();
        this.integerWriter = new IntegerWriter();
        this.stringWriter = new StringWriter();
    }

    public void handle(String str, Settings settings){
        if (str.isEmpty()){
            return;
        }
        try {
            int val = Integer.parseInt(str);
            this.integerWriter.add(val, settings);
        } catch (NumberFormatException e1) {
            try {
                float val = Float.parseFloat(str);
                this.floatWriter.add(val, settings);
            } catch (NumberFormatException e2) {
                this.stringWriter.add(str, settings);
            }
        }
    }

    public void printStatistics(Settings settings){
        this.stringWriter.printStatistics(settings.getStatisticsMode());
        this.integerWriter.printStatistics(settings.getStatisticsMode());
        this.floatWriter.printStatistics(settings.getStatisticsMode());
    }
}
