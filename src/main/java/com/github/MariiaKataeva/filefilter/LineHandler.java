package com.github.MariiaKataeva.filefilter;

import com.github.MariiaKataeva.filefilter.datawriting.*;
import com.github.MariiaKataeva.filefilter.progInfo.Settings;

public class LineHandler {
    private final StringWriter stringWriter;
    private final IntegerWriter integerWriter;
    private final FloatWriter floatWriter;

    public LineHandler(){
        this.floatWriter = new FloatWriter();
        this.integerWriter = new IntegerWriter();
        this.stringWriter = new StringWriter();
    }

    public void handle(String str, Settings settings, Statistics stat){
        if (str.isEmpty()){
            return;
        }
        try {
            int val = Integer.parseInt(str);
            this.integerWriter.add(val, settings, stat);
        } catch (NumberFormatException e1) {
            try {
                float val = Float.parseFloat(str);
                this.floatWriter.add(val, settings, stat);
            } catch (NumberFormatException e2) {
                this.stringWriter.add(str, settings, stat);
            }
        }
    }
}
