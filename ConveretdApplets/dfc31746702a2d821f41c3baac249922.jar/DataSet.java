import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class DataSet
{
    public String Name;
    public String IRISCode;
    public Data[] chartData;
    public Hashtable splitData;
    public Vector trendLine;
    public Vector indicator;
    private Vector v_chartData;
    public int dataType;
    public static final int UNKNOWN = 0;
    public static final int XY = 1;
    public static final int OHLCV = 5;
    private Parameters param;
    private int open;
    private int high;
    private int low;
    private int close;
    private int volume;
    private int count;
    private Date date;
    
    DataSet(final String s) {
        this.param = new Parameters();
        this.Name = "";
        this.IRISCode = "";
        this.dataType = 0;
        if (this.chartData != null) {
            this.chartData = null;
        }
        if (this.v_chartData != null) {
            this.v_chartData.removeAllElements();
        }
        else {
            this.v_chartData = new Vector(250, 50);
        }
        if (this.trendLine != null) {
            this.trendLine.removeAllElements();
        }
        else {
            this.trendLine = new Vector(5, 2);
        }
        if (this.indicator != null) {
            this.indicator.removeAllElements();
        }
        else {
            this.indicator = new Vector(5, 2);
        }
        if (this.splitData != null) {
            this.splitData.clear();
        }
        else {
            this.splitData = new Hashtable();
        }
        System.gc();
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(Parameters.applet.getCodeBase(), String.valueOf(Parameters.dataFile) + s).openConnection().getInputStream()));
            int n = 0;
            bufferedReader.readLine();
            while (true) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (!this.ParseOHLC(line)) {
                        String line2;
                        while ((line2 = bufferedReader.readLine()) != null) {
                            this.ParseSpecial(line2);
                        }
                        bufferedReader.close();
                        System.gc();
                        this.chartData = new Data[this.v_chartData.size()];
                        this.v_chartData.copyInto(this.chartData);
                        this.v_chartData.removeAllElements();
                        this.v_chartData = null;
                        System.gc();
                        return;
                    }
                    if (this.dataType == 0 && this.count == 5) {
                        this.dataType = 5;
                    }
                    this.v_chartData.addElement(new Data(this.date, this.open, this.high, this.low, this.close, this.volume));
                    ++n;
                }
                continue;
            }
        }
        catch (Exception ex) {}
    }
    
    public boolean ParseOHLC(final String s) {
        final String trim = s.trim();
        this.open = -1;
        this.high = -1;
        this.low = -1;
        this.close = -1;
        this.volume = -1;
        this.count = -1;
        final StringTokenizer stringTokenizer = new StringTokenizer(trim, "|");
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        final int countTokens = stringTokenizer.countTokens();
        if (countTokens < 2) {
            return false;
        }
        if (countTokens >= 1) {
            try {
                this.date = new Date(simpleDateFormat.parse(stringTokenizer.nextToken().trim()).getTime() + 1L);
            }
            catch (Exception ex) {
                return false;
            }
        }
        this.count = 0;
        if (countTokens >= 2) {
            this.open = Integer.parseInt(stringTokenizer.nextToken().trim(), 10);
            ++this.count;
        }
        if (countTokens >= 3) {
            this.high = Integer.parseInt(stringTokenizer.nextToken().trim(), 10);
            ++this.count;
        }
        if (countTokens >= 4) {
            this.low = Integer.parseInt(stringTokenizer.nextToken().trim(), 10);
            ++this.count;
        }
        if (countTokens >= 5) {
            this.close = Integer.parseInt(stringTokenizer.nextToken().trim(), 10);
            ++this.count;
        }
        if (countTokens >= 6) {
            this.volume = Integer.parseInt(stringTokenizer.nextToken().trim(), 10);
            ++this.count;
        }
        return true;
    }
    
    private boolean ParseSpecial(final String s) {
        final String trim = s.trim();
        final String upperCase = trim.trim().toUpperCase();
        if (upperCase.startsWith("IRISCODE:")) {
            this.IRISCode = trim.substring(9).trim();
        }
        if (upperCase.startsWith("NAME:")) {
            this.Name = trim.substring(5).trim();
        }
        if (upperCase.startsWith("ERROR:")) {
            this.IRISCode = null;
            this.Name = null;
            this.chartData = null;
            return false;
        }
        if (upperCase.startsWith("SPLIT:")) {
            final StringTokenizer stringTokenizer = new StringTokenizer(trim.substring(6).trim(), "|");
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            if (stringTokenizer.countTokens() > 1) {
                try {
                    this.date = new Date(simpleDateFormat.parse(stringTokenizer.nextToken().trim()).getTime() + 1L);
                    this.splitData.put(this.date, stringTokenizer.nextToken().trim());
                }
                catch (Exception ex) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void addAccumulation() {
        boolean b = false;
        final Indicator indicator = new Indicator(8);
        for (int i = 0; i < this.indicator.size(); ++i) {
            final int type = this.indicator.elementAt(i).getType();
            if (type == 3 || type == 2 || type == 4 || type == 7 || type == 8 || type == 9 || type == 10 || type == 11) {
                this.indicator.setElementAt(indicator, i);
                b = true;
            }
        }
        if (!b) {
            this.indicator.addElement(indicator);
        }
    }
    
    protected void addIndicators(final int n, final int n2) {
        boolean b = false;
        if (n2 < 0) {
            return;
        }
        final Indicator indicator = new Indicator(n, n2);
        for (int i = 0; i < this.indicator.size(); ++i) {
            final int type = this.indicator.elementAt(i).getType();
            if ((type == 3 || type == 2 || type == 4 || type == 7 || type == 8 || type == 9 || type == 10 || type == 11) && (Plotter.indicate == 2 || Plotter.indicate == 3 || Plotter.indicate == 4 || Plotter.indicate == 7 || Plotter.indicate == 8 || Plotter.indicate == 9 || Plotter.indicate == 10 || Plotter.indicate == 11)) {
                this.indicator.setElementAt(indicator, i);
                b = true;
            }
        }
        if (!b) {
            this.indicator.addElement(indicator);
        }
    }
    
    protected void addIndicators(final int n, final String s, final String s2) {
        boolean b = false;
        final Indicator indicator = new Indicator(n, Integer.parseInt(s.trim(), 10), new Double(s2.trim()));
        for (int i = 0; i < this.indicator.size(); ++i) {
            if (((Indicator)this.indicator.elementAt(i)).getType() == 6) {
                this.indicator.setElementAt(indicator, i);
                b = true;
            }
        }
        if (!b) {
            this.indicator.addElement(indicator);
        }
    }
    
    protected void addIndicators(final int n, final String s, final String s2, final String s3) {
        boolean b = false;
        final Indicator indicator = new Indicator(n, Integer.parseInt(s.trim(), 10), Integer.parseInt(s2.trim(), 10), Integer.parseInt(s3.trim(), 10));
        for (int i = 0; i < this.indicator.size(); ++i) {
            final int type = this.indicator.elementAt(i).getType();
            if ((type == 3 || type == 2 || type == 4 || type == 7 || type == 8 || type == 9 || type == 10 || type == 11) && (Plotter.indicate == 2 || Plotter.indicate == 3 || Plotter.indicate == 4 || Plotter.indicate == 7 || Plotter.indicate == 8 || Plotter.indicate == 9 || Plotter.indicate == 10 || Plotter.indicate == 11)) {
                this.indicator.setElementAt(indicator, i);
                b = true;
            }
        }
        if (!b) {
            this.indicator.addElement(indicator);
        }
    }
    
    protected void addTrendLine(final int[] array) {
        this.trendLine.addElement(array);
    }
    
    public void removeAllIndicators() {
        this.indicator.removeAllElements();
    }
    
    public void removeAllTrendLine() {
        this.trendLine.removeAllElements();
    }
    
    public void removeLastIndicators() {
        if (this.indicator.size() > 0) {
            this.indicator.removeElementAt(this.indicator.size() - 1);
        }
    }
    
    public void removeLastTrendLine() {
        if (this.trendLine.size() > 0) {
            this.trendLine.removeElementAt(this.trendLine.size() - 1);
        }
    }
    
    class Data
    {
        private Date date;
        private Object data;
        
        Data(final Date date, final int n, final int n2, final int n3, final int n4, final int n5) {
            final int[] data = { n, n2, n3, n4, n5 };
            this.date = date;
            this.data = data;
        }
        
        Data(final Date date, final Object data) {
            this.date = date;
            this.data = data;
        }
        
        public Object getData() {
            return this.data;
        }
        
        public Object getData(final Date date) {
            if (this.date.equals(date)) {
                return this.data;
            }
            return null;
        }
        
        public Date getDate() {
            return this.date;
        }
        
        public int getInt(final int n) {
            return ((int[])this.data)[n];
        }
    }
}
