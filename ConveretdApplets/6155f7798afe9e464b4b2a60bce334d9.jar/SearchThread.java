import java.net.URLConnection;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.util.Date;
import java.io.IOException;
import java.net.URL;
import java.io.DataInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class SearchThread extends Thread
{
    private static final String[] months;
    private DataInputStream dis;
    private SiteSearcher parent;
    private boolean searching;
    private boolean running;
    private URL url;
    private boolean noHorribleError;
    private int index;
    private String[] searchstrings;
    private boolean addedinfo;
    
    public void searchFor(final String[] searchstrings) {
        this.stopSearch();
        if (!this.noHorribleError) {
            return;
        }
        this.searchstrings = searchstrings;
        this.searching = true;
        if (!this.running) {
            this.running = true;
            this.start();
        }
    }
    
    public void setErrored() {
        this.noHorribleError = false;
    }
    
    public boolean isErrored() {
        return !this.noHorribleError;
    }
    
    public void stopSearch() {
        this.searching = false;
        try {
            if (this.dis != null) {
                this.dis.close();
            }
            this.dis = null;
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public void endLife() {
        this.running = false;
    }
    
    public void run() {
        while (this.running && this.noHorribleError) {
            if (this.searching) {
                this.dosearch();
                this.searching = false;
            }
            Thread.yield();
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    protected void dosearch() {
        try {
            final URLConnection openConnection = this.url.openConnection();
            if (!this.addedinfo) {
                this.addedinfo = true;
                final Date date = new Date(openConnection.getLastModified());
                this.parent.addInfo(this.index, new String(openConnection.getContentLength() / 1024 + "k  " + SearchThread.months[date.getMonth()] + " " + date.getDate() + " " + date.getYear()));
            }
            this.dis = new DataInputStream(new BufferedInputStream(openConnection.getInputStream()));
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex);
            this.stopSearch();
            this.noHorribleError = false;
            this.parent.foundNoMatch(this.index);
            return;
        }
        catch (IOException ex2) {
            System.out.println(ex2);
            this.stopSearch();
            this.parent.foundNoMatch(this.index);
            return;
        }
        catch (SecurityException ex3) {
            System.out.println(ex3);
            this.noHorribleError = false;
            this.stopSearch();
            this.parent.foundNoMatch(this.index);
            return;
        }
        while (true) {
            Label_0340: {
                String line;
                try {
                    if (!this.searching || (line = this.dis.readLine()) == null) {
                        break Label_0340;
                    }
                }
                catch (IOException ex4) {
                    System.out.println(ex4);
                    this.parent.foundNoMatch(this.index);
                    this.stopSearch();
                    return;
                }
                final String lowerCase = line.toLowerCase();
                for (int i = 0; i < this.searchstrings.length; ++i) {
                    if (lowerCase.indexOf(this.searchstrings[i]) != -1) {
                        if (this.searching) {
                            this.parent.foundMatch(this.index);
                        }
                        this.stopSearch();
                        return;
                    }
                }
                continue;
            }
            this.parent.foundNoMatch(this.index);
            this.stopSearch();
        }
    }
    
    public SearchThread(final SiteSearcher parent, final URL url, final int index) {
        super("SearchThread: " + index);
        this.dis = null;
        this.running = false;
        this.addedinfo = false;
        this.searching = false;
        this.parent = parent;
        this.url = url;
        this.noHorribleError = true;
        this.index = index;
    }
    
    static {
        months = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
    }
}
