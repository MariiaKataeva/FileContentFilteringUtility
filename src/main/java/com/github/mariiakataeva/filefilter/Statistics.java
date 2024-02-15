package com.github.mariiakataeva.filefilter;

import com.github.mariiakataeva.filefilter.progInfo.StatisticsMode;

//import org.apache.log4j.Logger;
import org.apache.logging.log4j.*;

public class Statistics {
    private static final Logger logger = LogManager.getLogger(Statistics.class);
    private int stringsCounter;
    private int minStrLength;
    private int maxStrLength;
    private int integersCounter;
    private int minIntVal;
    private int maxIntVal;
    private int sumIntVal;
    private float avgIntVal;
    private int floatsCounter;
    private float minFloatVal;
    private float maxFloatVal;
    private float sumFloatVal;
    private float avgFloatVal;
    public void update(int val){
        if (integersCounter == 0){
            this.maxIntVal = val;
            this.minIntVal = val;
        } else {
            if (val > maxIntVal){
                this.maxIntVal = val;
            }
            if (val < minIntVal){
                this.minIntVal = val;
            }
        }
        this.sumIntVal += val;
        this.integersCounter++;
        this.avgIntVal = (float)sumIntVal / (float)integersCounter;

        logger.debug("В статистике учтен элемент " + val);
    }
    public void update(float val){
        if (floatsCounter == 0){
            this.maxFloatVal = val;
            this.minFloatVal = val;
        } else {
            if (val > maxFloatVal){
                this.maxFloatVal = val;
            }
            if (val < minFloatVal){
                this.minFloatVal = val;
            }
        }
        this.sumFloatVal += val;
        this.floatsCounter++;
        this.avgFloatVal = sumFloatVal / floatsCounter;

        logger.debug("В статистике учтен элемент " + val);
    }
    public void update(String val){
        int strLength = val.length();
        if (stringsCounter == 0){
            this.minStrLength = strLength;
            this.maxStrLength = strLength;
        } else {
            if (strLength > maxStrLength){
                this.maxStrLength = strLength;
            }
            if (strLength < minStrLength){
                this.minStrLength = strLength;
            }
        }
        this.stringsCounter++;

        logger.debug("В статистике учтен элемент " + val);
    }

    public void printStatistics(StatisticsMode mode){
        if (mode == StatisticsMode.SHORT_STATISTICS || stringsCounter == 0){
            System.out.println("FOR STRINGS:");
            System.out.println("\tcounter = " + stringsCounter);
        } else if (mode == StatisticsMode.FULL_STATISTICS){
            System.out.println("FOR STRINGS:");
            System.out.println("\tcounter = " + stringsCounter);
            System.out.println("\tmax length = " + maxStrLength);
            System.out.println("\tmin length = " + minStrLength);
        }

        if (mode == StatisticsMode.SHORT_STATISTICS || floatsCounter == 0){
            System.out.println("FOR FLOATS:");
            System.out.println("\tcounter = " + floatsCounter);
        } else if (mode == StatisticsMode.FULL_STATISTICS){
            System.out.println("FOR FLOATS:");
            System.out.println("\tcounter = " + floatsCounter);
            System.out.println("\tmax value = " + maxFloatVal);
            System.out.println("\tmin val = " + minFloatVal);
            System.out.println("\tsum = " + sumFloatVal);
            System.out.println("\tavg value = " + avgFloatVal);
        }

        if (mode == StatisticsMode.SHORT_STATISTICS || integersCounter == 0){
            System.out.println("FOR INTEGERS:");
            System.out.println("\tcounter = " + integersCounter);
        } else if (mode == StatisticsMode.FULL_STATISTICS){
            System.out.println("FOR INTEGERS:");
            System.out.println("\tcounter = " + integersCounter);
            System.out.println("\tmax value = " + maxIntVal);
            System.out.println("\tmin val = " + minIntVal);
            System.out.println("\tsum = " + sumIntVal);
            System.out.println("\tavg value = " + avgIntVal);
        }
    }

    public int getStringsCounter(){
        return stringsCounter;
    }
    public int getFloatsCounter(){
        return floatsCounter;
    }
    public int getIntegersCounter(){
        return integersCounter;
    }
}
