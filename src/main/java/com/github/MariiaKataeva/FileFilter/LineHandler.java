package com.github.MariiaKataeva.FileFilter;

import com.github.MariiaKataeva.FileFilter.DataWriting.*;
import com.github.MariiaKataeva.FileFilter.ProgInfo.Settings;

public class LineHandler {
    private final StringWriter stringWriter;
    private final IntegerWriter integerWriter;
    private final FloatWriter floatWriter;

    public LineHandler(Settings settings){
        this.floatWriter = new FloatWriter(settings);
        this.integerWriter = new IntegerWriter(settings);
        this.stringWriter = new StringWriter(settings);
    }

    public void handle(String str){
        if (str.isEmpty()){
            return;
        }
        try {
            Integer.parseInt(str);
            this.integerWriter.add(str);
        } catch (NumberFormatException e1) {
            try {
                Float.parseFloat(str);
                this.floatWriter.add(str);
            } catch (NumberFormatException e2) {
                this.stringWriter.add(str);
            }
        }
    }

    public void printStatistics(Settings settings){//todo: учесть, какая подробность нужна
        System.out.println("stringCounter = " + this.stringWriter.getCounter());
        System.out.println("floatCounter = " + this.floatWriter.getCounter());
        System.out.println("integerCounter = " + this.integerWriter.getCounter());
    }
}
