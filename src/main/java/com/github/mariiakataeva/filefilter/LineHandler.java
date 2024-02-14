package com.github.mariiakataeva.filefilter;

import com.github.mariiakataeva.filefilter.progInfo.Settings;

public class LineHandler {
    private final DataWriter dataWriter;

    public LineHandler(){
        this.dataWriter = new DataWriter();
    }

    public void handle(String str, Settings settings, Statistics stat){
        if (str.isEmpty()){
            return;
        }
        try {
            int val = Integer.parseInt(str);
            this.dataWriter.add(val, settings, stat);
        } catch (NumberFormatException e1) {
            try {
                float val = Float.parseFloat(str);
                this.dataWriter.add(val, settings, stat);
            } catch (NumberFormatException e2) {
                this.dataWriter.add(str, settings, stat);
            }
        }
    }
}
