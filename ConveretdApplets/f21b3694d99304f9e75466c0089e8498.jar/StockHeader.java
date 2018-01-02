import java.util.Vector;
import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

public class StockHeader
{
    String symbol;
    String splits;
    StockDetail[] data;
    Date oldDate;
    Date newDate;
    double minimum;
    double maximum;
    int minimumOpenint;
    int maximumOpenint;
    int minimumVolume;
    int maximumVolume;
    int duration;
    int stockvalue;
    int tablevalue;
    String stockexchange;
    String tablestatus;
    boolean barSoFar;
    boolean future;
    boolean option;
    StockHeader cash;
    boolean showCash;
    String query;
    Vector vectorData;
    String liveQuote;
    String livePrice;
    String liveChange;
    
    StockHeader() {
        this.minimum = Double.MAX_VALUE;
        this.maximum = Double.MIN_VALUE;
        this.minimumOpenint = Integer.MAX_VALUE;
        this.maximumOpenint = Integer.MIN_VALUE;
        this.minimumVolume = Integer.MAX_VALUE;
        this.maximumVolume = Integer.MIN_VALUE;
        this.barSoFar = false;
        this.future = false;
        this.option = false;
        this.showCash = true;
        this.vectorData = new Vector(200);
    }
    
    public void addDetail(final StockDetail stockdetail) {
        if (stockdetail != null) {
            if (this.isOption()) {
                stockdetail.factor = 10000.0;
            }
            if (this.vectorData != null) {
                this.vectorData.addElement(stockdetail);
            }
        }
    }
    
    public void convertArray() {
        System.out.println("Converting vector to array");
        this.data = new StockDetail[this.vectorData.size()];
        for (int i = 0; i < this.vectorData.size(); ++i) {
            this.data[i] = this.vectorData.elementAt(i);
        }
        this.vectorData.removeAllElements();
        this.vectorData = null;
        for (int j = 0; j < this.count() && this.dataAt(j) != null; ++j) {
            if (this.dataAt(j).getLow() < this.minimum) {
                this.minimum = this.dataAt(j).getLow();
            }
            if (this.dataAt(j).getVolume() < this.minimumVolume) {
                this.minimumVolume = this.dataAt(j).getVolume();
            }
            if (this.dataAt(j).getHigh() > this.maximum) {
                this.maximum = this.dataAt(j).getHigh();
            }
            if (this.dataAt(j).getVolume() > this.maximumVolume) {
                this.maximumVolume = this.dataAt(j).getVolume();
            }
            if (this.dataAt(j).getOpenint() > this.maximumOpenint) {
                this.maximumOpenint = this.dataAt(j).getOpenint();
            }
            if (this.dataAt(j).getOpenint() < this.minimumOpenint) {
                this.minimumOpenint = this.dataAt(j).getOpenint();
            }
        }
        System.out.println(String.valueOf(String.valueOf("total of ").concat(String.valueOf(this.count()))).concat(String.valueOf(" days of data loaded")));
    }
    
    public int count() {
        if (this.data != null) {
            return this.data.length;
        }
        if (this.vectorData != null) {
            return this.vectorData.size();
        }
        return 0;
    }
    
    public StockDetail dataAt(int i) {
        if (i < 0) {
            i = 0;
        }
        if (this.vectorData != null) {
            return this.vectorData.elementAt(i);
        }
        try {
            if (i < this.data.length && i >= 0) {
                final StockDetail stockdetail2;
                final StockDetail stockdetail = stockdetail2 = this.data[i];
                return stockdetail2;
            }
            final StockDetail stockdetail4;
            final StockDetail stockdetail3 = stockdetail4 = null;
            return stockdetail4;
        }
        catch (ArrayIndexOutOfBoundsException arrayindexoutofboundsexception) {
            System.out.println(String.valueOf("out of bounds: ").concat(String.valueOf(i)));
            return null;
        }
    }
    
    public StockDetail firstData() {
        return this.dataAt(0);
    }
    
    public StockHeader getCash() {
        return this.cash;
    }
    
    public boolean isFuture() {
        return this.symbol.indexOf("1600") > 0 || this.future;
    }
    
    public boolean isOption() {
        return this.symbol.indexOf("|") > 0 || this.option;
    }
    
    public StockDetail lastData() {
        return this.dataAt(this.count() - 1);
    }
    
    public void setCash(final StockHeader stockheader) {
        this.cash = stockheader;
    }
    
    public void setDuration(final int i) {
        this.duration = i;
    }
    
    public void setFuture(final boolean flag) {
        this.future = flag;
    }
    
    public void setOption(final boolean flag) {
        this.option = flag;
    }
    
    public void setShowCash(final boolean flag) {
        this.showCash = flag;
    }
    
    public void setStockExchg(final int i) {
        this.stockvalue = i;
    }
    
    public void setSymbol(final String s) {
        this.symbol = s;
    }
    
    public void setTableSelect(final int i) {
        this.tablevalue = i;
    }
    
    public boolean showCash() {
        return this.showCash;
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf("StockHeader ").concat(String.valueOf(this.symbol))).concat(String.valueOf(" "))).concat(String.valueOf(this.count()))).concat(String.valueOf(" days"));
    }
}
