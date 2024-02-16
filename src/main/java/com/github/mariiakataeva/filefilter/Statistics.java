package com.github.mariiakataeva.filefilter;

import com.github.mariiakataeva.filefilter.progInfo.StatisticsMode;

import org.apache.logging.log4j.*;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Statistics {
    private static final Logger logger = LogManager.getLogger(Statistics.class);
    private int stringsCounter;
    private int minStrLength;
    private int maxStrLength;
    private int integersCounter;
    private BigInteger minIntVal;
    private BigInteger maxIntVal;
    private BigInteger sumIntVal;
    private BigDecimal avgIntVal;
    private int floatsCounter;
    private BigDecimal minFloatVal;
    private BigDecimal maxFloatVal;
    private BigDecimal sumFloatVal;
    private BigDecimal avgFloatVal;
    public Statistics(){
        this.sumIntVal = new BigInteger("0");
        this.sumFloatVal = new BigDecimal("0.0");
    }
    public void update(BigInteger val){
        if (integersCounter == 0){
            this.maxIntVal = val;
            this.minIntVal = val;
        } else {
            if (val.compareTo(maxIntVal) > 0){
                this.maxIntVal = val;
            }
            if (val.compareTo(minIntVal) < 0){
                this.minIntVal = val;
            }
        }
        this.sumIntVal = sumIntVal.add(val);
        this.integersCounter++;
        this.avgIntVal = new BigDecimal(sumIntVal).divide(new BigDecimal(integersCounter), RoundingMode.HALF_UP);

        logger.debug("В статистике учтен элемент " + val);
    }
    public void update(BigDecimal val){
        if (floatsCounter == 0){
            this.maxFloatVal = val;
            this.minFloatVal = val;
        } else {
            if (val.compareTo(maxFloatVal) > 0){
                this.maxFloatVal = val;
            }
            if (val.compareTo(minFloatVal) < 0){
                this.minFloatVal = val;
            }
        }
        this.sumFloatVal = sumFloatVal.add(val);
        this.floatsCounter++;
        this.avgFloatVal = sumFloatVal.divide(new BigDecimal(floatsCounter), RoundingMode.HALF_UP);

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
