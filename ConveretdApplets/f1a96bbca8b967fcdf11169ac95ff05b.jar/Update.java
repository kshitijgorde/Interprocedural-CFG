import java.applet.AppletContext;
import java.io.InputStream;
import java.io.DataInputStream;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class Update extends Thread
{
    private int updateDelay;
    private String updateFrom;
    private int confuser;
    private Ticker ticker;
    private JavaTicker a;
    private int updateMode;
    public boolean connectionup;
    
    public Update(final String updateFrom, final int updateDelay, final Ticker ticker, final JavaTicker a, final int updateMode) {
        this.updateFrom = updateFrom;
        this.updateDelay = updateDelay;
        this.ticker = ticker;
        this.a = a;
        this.updateMode = updateMode;
        this.connectionup = true;
        this.confuser = 0;
        if (!this.updateData()) {
            this.connectionup = false;
            ticker.recycle = true;
            try {
                ticker.bumpOldHeadlines();
            }
            catch (Exception ex) {}
            ticker.killAllHeadlines();
            ticker.parseData(a.defaultMessage, a.defaultLink);
        }
        else if (!this.connectionup) {
            this.connectionup = true;
            a.setRecycle();
        }
    }
    
    public boolean updateData() {
        try {
            ++this.confuser;
            final String docBase = this.a.getDocumentBase().toString().replace('?', '.').replace('&', '.').replace('=', '.').replace(' ', '+');
            String end;
            if (this.updateFrom.indexOf("?") > 0) {
                end = "&c=" + this.confuser + "&r=" + (int)(Math.random() * 2000.0) + "&url=" + docBase;
            }
            else if (this.ticker.local) {
                end = "";
            }
            else {
                end = "?c=" + this.confuser + "&r=" + (int)(Math.random() * 2000.0) + "&url=" + docBase;
            }
            final URL data = new URL(this.ticker.base, String.valueOf(this.updateFrom) + end);
            InputStream input = null;
            data.openConnection();
            input = data.openStream();
            final DataInputStream datastream = new DataInputStream(input);
            final String headlines = datastream.readLine();
            final String links = datastream.readLine();
            final String newUpdate = datastream.readLine();
            String secCheck = datastream.readLine();
            if (secCheck == null) {
                secCheck = "true";
            }
            secCheck = secCheck.trim();
            if (secCheck.equals("false")) {
                if (this.a.getDocumentBase().toString().startsWith("file:/")) {
                    this.ticker.parseData("Ticker cannot be loaded from a local file...", "");
                    return true;
                }
                final AppletContext context = this.a.getAppletContext();
                final URL u = new URL("http://www.tickertech.com/javaticker25/unauthorized/?" + (int)(Math.random() * 10000.0));
                try {
                    context.showDocument(u, "_top");
                }
                catch (Exception e) {
                    this.a.showStatus("Error linking: " + e);
                }
                return false;
            }
            else {
                input.close();
                try {
                    datastream.close();
                }
                catch (Exception ex2) {}
                if (headlines == null || links == null) {
                    return false;
                }
                this.ticker.killAllHeadlines();
                this.ticker.parseData(headlines, links);
            }
        }
        catch (Exception ex) {
            System.out.println("Connection error: " + ex.toString());
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public void run() {
        boolean done = false;
        if (this.updateDelay == 0) {
            done = true;
        }
        while (!done) {
            try {
                Thread.sleep(this.updateDelay);
            }
            catch (Exception ex) {}
            while (this.ticker.paused || this.ticker.tooManyHeadlines()) {
                try {
                    Thread.sleep(this.updateDelay);
                }
                catch (Exception ex2) {}
            }
            System.gc();
            System.runFinalization();
            if (!this.updateData()) {
                try {
                    Thread.sleep(15000L);
                }
                catch (Exception ex3) {}
                if (this.updateData()) {
                    continue;
                }
                this.connectionup = false;
                this.ticker.recycle = true;
                try {
                    this.ticker.bumpOldHeadlines();
                }
                catch (Exception ex4) {}
                this.ticker.killAllHeadlines();
                this.ticker.parseData(this.a.defaultMessage, this.a.defaultLink);
            }
            else {
                if (this.connectionup) {
                    continue;
                }
                this.connectionup = true;
                this.a.setRecycle();
            }
        }
    }
}
