import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class MarketActivityInfo implements Serializable
{
    int totalTrades;
    int advances;
    int declines;
    int noChange;
    double totalValue;
    double totalSharesTraded;
    
    public MarketActivityInfo(final int totalTrades, final double totalSharesTraded, final double totalValue, final int advances, final int declines, final int noChange) {
        this.totalTrades = totalTrades;
        this.totalSharesTraded = totalSharesTraded;
        this.totalValue = totalValue;
        this.advances = advances;
        this.declines = declines;
        this.noChange = noChange;
    }
    
    public int getTotalTrades() {
        return this.totalTrades;
    }
    
    public double getTotalSharesTraded() {
        return this.totalSharesTraded;
    }
    
    public double getTotalValue() {
        return this.totalValue;
    }
    
    public int getAdvances() {
        return this.advances;
    }
    
    public int getDeclines() {
        return this.declines;
    }
    
    public int getNoChange() {
        return this.noChange;
    }
}
