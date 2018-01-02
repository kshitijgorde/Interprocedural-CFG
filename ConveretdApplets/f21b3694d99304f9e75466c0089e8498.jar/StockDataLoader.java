import java.io.DataInputStream;
import java.net.URLConnection;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class StockDataLoader implements Runnable
{
    public static final int DATA_ERROR = 20010;
    private Thread thread;
    Applet applet;
    Chart chart;
    String script;
    boolean done;
    String symbol;
    boolean liveOnly;
    
    StockDataLoader(final Chart chart1, final String s, final String s1) {
        this(chart1, s, s, false);
    }
    
    StockDataLoader(final Chart chart1, final String s, final String s1, final boolean flag) {
        this.done = false;
        this.chart = chart1;
        this.script = s;
        this.symbol = s1;
        this.liveOnly = flag;
        this.applet = chart1.parent;
        (this.thread = new Thread(this, "The Chart Data Loader")).start();
    }
    
    public void run() {
        try {
            final String s = "";
            final byte[] abyte0 = new byte[26];
            try {
                if (!this.liveOnly) {
                    final URL url = new URL(this.applet.getCodeBase(), this.script);
                    System.out.println(String.valueOf("url ").concat(String.valueOf(url)));
                    final URLConnection urlconnection = url.openConnection();
                    URLConnection.setDefaultRequestProperty("CONTENT_TYPE", urlconnection.getContentType());
                    final DataInputStream datainputstream = new DataInputStream(urlconnection.getInputStream());
                    String s2;
                    while ((s2 = datainputstream.readLine()) != null && !s2.trim().equals("")) {
                        this.chart.gotData(s2, 1);
                    }
                    datainputstream.close();
                }
            }
            catch (Exception exception1) {
                System.out.println(exception1);
                System.out.println("****Data loader exception");
                System.out.println(String.valueOf(exception1.toString()));
                System.out.println(String.valueOf(String.valueOf("Inline=[").concat(String.valueOf(new String(abyte0)))).concat(String.valueOf("]")));
                exception1.printStackTrace();
                System.out.println("****End Data loader exception");
            }
        }
        catch (Exception exception2) {
            if (!this.done) {
                this.done = true;
                this.chart.doneLoading();
            }
        }
        if (!this.done) {
            this.done = true;
            this.chart.doneLoading();
        }
    }
    
    public void stop() {
        this.thread.stop();
        this.thread = null;
    }
}
