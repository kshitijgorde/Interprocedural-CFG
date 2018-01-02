// 
// Decompiled by Procyon v0.5.30
// 

public class Indicator
{
    private int type;
    private int value;
    public static final int MOVING_AVERAGE = 1;
    public static final int ROC = 2;
    public static final int RSI = 3;
    public static final int MACD = 4;
    public static final int EMA = 5;
    public static final int BOLLINGER = 6;
    public static final int OBV = 7;
    public static final int ACCUMULATION_DISTRIBUTION = 8;
    public static final int CCI = 9;
    public static final int STOCHAST = 10;
    public static final int MOMENTUM = 11;
    public double BollingerFact;
    private double firstEma;
    private int emaDays1;
    private int emaDays2;
    private int trigger;
    private double ema1;
    private double ema2;
    
    Indicator(final int type) {
        this.firstEma = 0.0;
        this.ema1 = 0.0;
        this.ema2 = 0.0;
        this.type = type;
    }
    
    Indicator(final int type, final int value) {
        this.firstEma = 0.0;
        this.ema1 = 0.0;
        this.ema2 = 0.0;
        this.type = type;
        this.value = value;
    }
    
    Indicator(final int type, final int value, final double bollingerFact) {
        this.firstEma = 0.0;
        this.ema1 = 0.0;
        this.ema2 = 0.0;
        this.type = type;
        this.value = value;
        this.BollingerFact = bollingerFact;
    }
    
    Indicator(final int type, final int emaDays1, final int emaDays2, final int trigger) {
        this.firstEma = 0.0;
        this.ema1 = 0.0;
        this.ema2 = 0.0;
        this.type = type;
        this.emaDays1 = emaDays1;
        this.emaDays2 = emaDays2;
        this.trigger = trigger;
    }
    
    public double getBollingerFact() {
        return this.BollingerFact;
    }
    
    public int getData() {
        return this.value;
    }
    
    public double getEma() {
        return this.firstEma;
    }
    
    public double getEma1() {
        return this.ema1;
    }
    
    public double getEma2() {
        return this.ema2;
    }
    
    public int getEmaDays1() {
        return this.emaDays1;
    }
    
    public int getEmaDays2() {
        return this.emaDays2;
    }
    
    public double getMacdEma() {
        return this.firstEma;
    }
    
    public int getTrigger() {
        return this.trigger;
    }
    
    public int getType() {
        return this.type;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public void setEma(final double firstEma) {
        this.firstEma = firstEma;
    }
    
    public void setEma1(final double ema1) {
        this.ema1 = ema1;
    }
    
    public void setEma2(final double ema2) {
        this.ema2 = ema2;
    }
    
    public void setMacdEma(final double firstEma) {
        this.firstEma = firstEma;
    }
}
