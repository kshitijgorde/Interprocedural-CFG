import java.util.Random;
import java.net.URL;
import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

public class MktModel
{
    static boolean debug;
    String symbolName;
    Date startDate;
    int intervalType;
    int maxQuotes;
    int nrQuotes;
    QuoteModel[] dailyQuotes;
    String cgiServer;
    static int HOUR_INTERVAL;
    static int DAY_INTERVAL;
    
    public MktModel(final String s, final Date startDate, final int maxQuotes, final int intervalType) {
        this.cgiServer = "http://" + s + "/cgi/quotes.cgi";
        this.symbolName = null;
        this.startDate = startDate;
        this.nrQuotes = 0;
        this.intervalType = intervalType;
        this.maxQuotes = maxQuotes;
        this.dailyQuotes = new QuoteModel[this.maxQuotes];
    }
    
    public void addQuote(final QuoteModel quoteModel) throws Exception {
        if (this.nrQuotes == this.maxQuotes) {
            throw new Exception("quote overflow");
        }
        this.dailyQuotes[this.nrQuotes++] = quoteModel;
    }
    
    public int countQuotes() {
        return this.nrQuotes;
    }
    
    private void downloadQuotes(final Date date) throws Exception {
        if (this.intervalType != MktModel.DAY_INTERVAL) {
            throw new Exception("MktModel: invalid interval");
        }
        final URL url = new URL(this.cgiServer + ("?symbol=" + this.symbolName + "&start=" + Pretty.date(date) + "&days=" + this.maxQuotes));
        if (MktModel.debug) {
            System.out.println("MktView.downloadQuotes(): feedUrl=" + url.toString());
        }
    }
    
    private void fabricateQuotes(final Date date) throws Exception {
        final Random random = new Random(5L);
        long time = date.getTime();
        if (MktModel.debug) {
            System.out.println("MktView.fabricateQuotes(): msec=" + time + " (START)");
        }
        int n = (int)(random.nextDouble() * 50.0 * 8.0);
        for (int i = 0; i < this.maxQuotes; ++i) {
            final QuoteModel quoteModel = new QuoteModel();
            final Date date2 = new Date(time);
            int n2 = (int)(random.nextDouble() * 7.0);
            if (n2 > 5) {
                n2 = -(n2 - 5);
            }
            final int n3 = n * n2 / 100;
            int n4 = n + n3;
            if (n4 < 1) {
                n4 = 0;
            }
            quoteModel.set(date2, n4 + (int)(2.0 + random.nextDouble() * 9.0), n4 - (int)(2.0 + random.nextDouble() * 18.0), n4, n3, 100 * (int)(random.nextDouble() * 10000.0));
            time += 86400000L;
            this.addQuote(quoteModel);
            n = n4;
        }
    }
    
    public String getName() {
        if (this.symbolName == null) {
            return "";
        }
        return this.symbolName;
    }
    
    public boolean isEmpty() {
        return this.nrQuotes == 0;
    }
    
    public QuoteModel getQuote(final int n) throws Exception {
        if (n < 0 || n > this.nrQuotes) {
            throw new Exception("MktModel: invalid quoteNr");
        }
        return this.dailyQuotes[n];
    }
    
    public int maxPrice() {
        int max = 0;
        for (int i = 0; i < this.nrQuotes; ++i) {
            try {
                max = Math.max(max, this.getQuote(i).getHigh());
            }
            catch (Exception ex) {
                throw new InternalError();
            }
        }
        return max;
    }
    
    public int maxVolume() {
        int max = 0;
        for (int i = 0; i < this.nrQuotes; ++i) {
            try {
                max = Math.max(max, this.getQuote(i).getVolume());
            }
            catch (Exception ex) {
                throw new InternalError();
            }
        }
        return max;
    }
    
    public int minPrice() {
        int min = 0;
        for (int i = 0; i < this.nrQuotes; ++i) {
            try {
                min = Math.min(min, this.getQuote(i).getHigh());
            }
            catch (Exception ex) {
                throw new InternalError();
            }
        }
        return min;
    }
    
    public void setSymbol(final String symbolName) {
        if (this.symbolName == null || !this.symbolName.equals(symbolName)) {
            this.symbolName = symbolName;
            this.nrQuotes = 0;
            try {
                if (this.symbolName.equals("FAKE")) {
                    this.fabricateQuotes(this.startDate);
                    return;
                }
                this.downloadQuotes(this.startDate);
            }
            catch (Exception ex) {
                this.nrQuotes = 0;
            }
        }
    }
    
    static {
        MktModel.HOUR_INTERVAL = 1;
        MktModel.DAY_INTERVAL = 2;
    }
}
