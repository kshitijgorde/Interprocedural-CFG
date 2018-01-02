import java.util.Hashtable;
import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class MarketInfo2 implements Serializable
{
    Hashtable data;
    MarketActivityInfo mai;
    StatusCurrencyRateInfo scri;
    String dateTime;
    
    public MarketInfo2() {
        this.data = new Hashtable();
    }
    
    public void addSector(final String s, final Sector sector) {
        this.data.put(s, sector);
    }
    
    public Hashtable getMarketInfo() {
        return this.data;
    }
    
    public void setMarketActivityInfo(final MarketActivityInfo mai) {
        this.mai = mai;
    }
    
    public MarketActivityInfo getMarketActivityInfo() {
        return this.mai;
    }
    
    public void setStatusCurrencyInfo(final StatusCurrencyRateInfo scri) {
        this.scri = scri;
    }
    
    public StatusCurrencyRateInfo getStatusCurrencyInfo() {
        return this.scri;
    }
    
    public void clearSectors() {
        this.data.clear();
    }
    
    public void setDateTime(final String dateTime) {
        this.dateTime = dateTime;
    }
    
    public String getDateTime() {
        return this.dateTime;
    }
}
