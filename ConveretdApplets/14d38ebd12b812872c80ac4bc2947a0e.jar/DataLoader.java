import java.net.URLConnection;
import java.util.Vector;
import java.io.DataInputStream;
import java.io.IOException;
import java.awt.Font;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class DataLoader implements Runnable
{
    public static final String VERSION = "1.3";
    private static final int DELAY = 500;
    private Thread thread;
    private URL url;
    private String[] textData;
    private boolean dataReady;
    private boolean errorOccurred;
    private boolean shouldRefresh;
    private boolean shouldWrap;
    private boolean isAlive;
    private Font startingFont;
    private int width;
    
    public DataLoader(final URL url, final Font font, final int n) {
        this(url, font, n, true);
    }
    
    public DataLoader(final URL url, final Font startingFont, final int width, final boolean shouldWrap) {
        this.dataReady = false;
        this.errorOccurred = false;
        this.shouldRefresh = true;
        this.isAlive = false;
        this.url = url;
        this.dataReady = false;
        this.shouldRefresh = true;
        this.shouldWrap = shouldWrap;
        this.errorOccurred = false;
        this.startingFont = startingFont;
        this.width = width;
        this.isAlive = true;
        (this.thread = new Thread(this)).start();
    }
    
    public void refresh() {
        this.shouldRefresh = true;
    }
    
    public void run() {
        while (this.isAlive) {
            while (!this.shouldRefresh) {
                try {
                    Thread.sleep(500L);
                    if (!this.isAlive) {
                        return;
                    }
                    continue;
                }
                catch (InterruptedException ex) {
                    this.isAlive = false;
                    return;
                }
            }
            URLConnection openConnection;
            try {
                openConnection = this.url.openConnection();
            }
            catch (IOException ex2) {
                this.errorOccurred = true;
                return;
            }
            DataInputStream dataInputStream;
            try {
                dataInputStream = new DataInputStream(openConnection.getInputStream());
            }
            catch (IOException ex3) {
                this.errorOccurred = true;
                return;
            }
            final Vector vector = new Vector<String>();
            while (true) {
                String line;
                try {
                    line = dataInputStream.readLine();
                }
                catch (IOException ex4) {
                    this.errorOccurred = true;
                    return;
                }
                if (line == null) {
                    break;
                }
                vector.addElement(String.valueOf(line) + "\n");
            }
            this.textData = new String[vector.size()];
            for (int i = 0; i < vector.size(); ++i) {
                this.textData[i] = new String(vector.elementAt(i));
            }
            try {
                dataInputStream.close();
            }
            catch (IOException ex5) {
                System.err.println("IOException closing DataInputStream on text data.");
            }
            if (this.shouldWrap) {
                final String[] wrapForWidth = new LineWrapManager(this.textData, this.startingFont).wrapForWidth(this.width);
                System.arraycopy(wrapForWidth, 0, this.textData = new String[wrapForWidth.length], 0, wrapForWidth.length);
            }
            for (int j = 0; j < this.textData.length; ++j) {
                this.textData[j] = this.textData[j].trim();
            }
            this.dataReady = true;
            this.shouldRefresh = false;
        }
    }
    
    public void stop() {
        this.isAlive = false;
    }
    
    public void start() {
        if (this.isAlive) {
            return;
        }
        this.isAlive = true;
        (this.thread = new Thread(this)).start();
    }
    
    public boolean dataReady() {
        return this.dataReady;
    }
    
    public boolean errorOccurred() {
        return this.errorOccurred;
    }
    
    public String[] getData() {
        if (!this.dataReady() || this.errorOccurred()) {
            return null;
        }
        return this.textData;
    }
}
