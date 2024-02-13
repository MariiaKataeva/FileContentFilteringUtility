package com.github.MariiaKataeva.FileFilter;

import com.github.MariiaKataeva.FileFilter.DataWriting.*;

public class LineHandler {
    private final StringWriter stringWriter;
    private final IntegerWriter integerWriter;
    private final FloatWriter floatWriter;

    public LineHandler(){
        this.floatWriter = new FloatWriter();
        this.integerWriter = new IntegerWriter();
        this.stringWriter = new StringWriter();
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

    public void printStatistics(){
        System.out.println("stringCounter = " + this.stringWriter.getCounter());
        System.out.println("floatCounter = " + this.floatWriter.getCounter());
        System.out.println("integerCounter = " + this.integerWriter.getCounter());
    }
}
