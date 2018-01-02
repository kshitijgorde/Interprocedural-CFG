import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.applet.Applet;
import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

public class StockDetail
{
    public static long startTime;
    public Date date;
    private int open;
    private int high;
    private int low;
    private int close;
    private int volume;
    private int openint;
    public int x1;
    public int x2;
    double macd;
    double factor;
    Applet parent;
    String browser;
    
    StockDetail(final int i, final int j, final int k, final int l, final int i1, final Date date1) {
        this.openint = -1;
        this.x1 = -1;
        this.x2 = -1;
        this.factor = 100000.0;
        this.open = i;
        this.high = j;
        this.low = k;
        this.close = l;
        this.volume = i1;
        this.date = date1;
    }
    
    StockDetail(final String s, final String s1) {
        final StringTokenizer stringtokenizer = new StringTokenizer(s, "|");
        final SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
        int i = 0;
        this.openint = -1;
        this.x1 = -1;
        this.x2 = -1;
        this.factor = 100000.0;
        this.open = 0;
        this.high = 0;
        this.low = 0;
        this.close = 0;
        this.volume = 0;
        i = stringtokenizer.countTokens();
        if (i >= 1) {
            try {
                this.date = new Date(simpledateformat.parse(stringtokenizer.nextToken().trim()).getTime() + 1L);
            }
            catch (Exception exception) {
                System.out.println("****Date conversion exception");
                System.out.println("StockDetail(String)");
                System.out.println(s);
                System.out.println("****END Date conversion exception");
            }
        }
        if (i >= 2) {
            this.open = Integer.parseInt(stringtokenizer.nextToken().trim(), 10) * 1000;
        }
        if (i >= 3) {
            this.high = Integer.parseInt(stringtokenizer.nextToken().trim(), 10) * 1000;
        }
        if (i >= 4) {
            this.low = Integer.parseInt(stringtokenizer.nextToken().trim(), 10) * 1000;
        }
        if (i >= 5) {
            this.close = Integer.parseInt(stringtokenizer.nextToken().trim(), 10) * 1000;
        }
        if (i >= 6) {
            this.volume = Integer.parseInt(stringtokenizer.nextToken().trim(), 10);
        }
    }
    
    public double getClose() {
        return this.close / this.factor;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public double getHigh() {
        return this.high / this.factor;
    }
    
    public double getLow() {
        return this.low / this.factor;
    }
    
    public double getOpen() {
        return this.open / this.factor;
    }
    
    public int getOpenint() {
        return this.openint;
    }
    
    public int getVolume() {
        return this.volume;
    }
    
    public boolean isFuture() {
        return this.getOpenint() >= 0;
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.date).concat(String.valueOf(","))).concat(String.valueOf(this.open))).concat(String.valueOf(","))).concat(String.valueOf(this.high))).concat(String.valueOf(","))).concat(String.valueOf(this.low))).concat(String.valueOf(","))).concat(String.valueOf(this.close))).concat(String.valueOf(","))).concat(String.valueOf(this.volume));
    }
    
    static {
        StockDetail.startTime = new Date("1/1/1950").getTime();
    }
}
