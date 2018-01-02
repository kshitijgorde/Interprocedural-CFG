import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class StatusCurrencyRateInfo implements Serializable
{
    String marketStatus;
    String tradingDate;
    String closeTime;
    double USExRate;
    
    public StatusCurrencyRateInfo(String marketStatus, final double usExRate, final String tradingDate, final String closeTime) {
        if (marketStatus.equals("P")) {
            marketStatus = "Market Pre-open";
        }
        else if (marketStatus.equals("O")) {
            marketStatus = "Market Open";
        }
        else if (marketStatus.equals("H")) {
            marketStatus = "Market Halt";
        }
        else if (marketStatus.equals("R")) {
            marketStatus = "Market Resume";
        }
        else if (marketStatus.equals("C")) {
            marketStatus = "Market Closed";
        }
        this.marketStatus = marketStatus;
        this.USExRate = usExRate;
        this.tradingDate = tradingDate;
        this.closeTime = closeTime;
    }
    
    public String getMarketStatus() {
        return this.marketStatus;
    }
    
    public double getUSExchangeRate() {
        return this.USExRate;
    }
    
    public String getTradingDate() {
        return this.tradingDate;
    }
    
    public String getCloseTime() {
        return this.closeTime;
    }
}
