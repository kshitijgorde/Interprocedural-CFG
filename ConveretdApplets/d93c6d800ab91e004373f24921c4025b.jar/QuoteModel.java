import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

public class QuoteModel
{
    Date date;
    int changeEighths;
    int highEighths;
    int lastPriceEighths;
    int lowEighths;
    int volume;
    
    public int getChange() {
        return this.changeEighths;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public int getHigh() {
        return this.highEighths;
    }
    
    public int getLastPrice() {
        return this.lastPriceEighths;
    }
    
    public int getLow() {
        return this.lowEighths;
    }
    
    public int getVolume() {
        return this.volume;
    }
    
    public void set(final Date date, final int highEighths, final int lowEighths, final int lastPriceEighths, final int changeEighths, final int volume) {
        this.date = date;
        this.highEighths = highEighths;
        this.lowEighths = lowEighths;
        this.lastPriceEighths = lastPriceEighths;
        this.changeEighths = changeEighths;
        this.volume = volume;
    }
}
