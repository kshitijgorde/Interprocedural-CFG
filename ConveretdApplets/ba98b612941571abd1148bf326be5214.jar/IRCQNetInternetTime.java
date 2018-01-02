import java.awt.Font;
import java.util.Date;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class IRCQNetInternetTime extends Canvas implements ImageObserver, Runnable
{
    private static final int TIME_TICK = 86400;
    private static final long ONE_HOUR = 3600000L;
    private static final int DELAY = 1500;
    private static final int w = 8;
    private String url;
    private IRCQNet theApp;
    private Image digits;
    private Image[] digit;
    private Image at;
    private Image bufferImg;
    private Image banner;
    private Graphics bufferGfx;
    private Thread myThread;
    private boolean isImagesCreated;
    private long dayMillis;
    private long endMillis;
    
    public IRCQNetInternetTime(final IRCQNet theApp) {
        this.url = "http://www.swatch.com/internettime/";
        this.digit = new Image[10];
        this.isImagesCreated = false;
        this.dayMillis = -1L;
        this.resize(300, 26);
        this.theApp = theApp;
        try {
            theApp.getToolkit().prepareImage(this.banner = this.theApp.getImage(theApp.getCodeBase(), "icons/swatch_logo.gif"), 120, 20, this);
            theApp.getToolkit().prepareImage(this.at = theApp.getImage(theApp.getCodeBase(), "icons/iTime/@.gif"), 15, 14, this);
            theApp.getToolkit().prepareImage(this.digit[0] = theApp.getImage(theApp.getCodeBase(), "icons/iTime/0.gif"), 10, 13, this);
            theApp.getToolkit().prepareImage(this.digit[1] = theApp.getImage(theApp.getCodeBase(), "icons/iTime/1.gif"), 10, 13, this);
            theApp.getToolkit().prepareImage(this.digit[2] = theApp.getImage(theApp.getCodeBase(), "icons/iTime/2.gif"), 10, 13, this);
            theApp.getToolkit().prepareImage(this.digit[3] = theApp.getImage(theApp.getCodeBase(), "icons/iTime/3.gif"), 10, 13, this);
            theApp.getToolkit().prepareImage(this.digit[4] = theApp.getImage(theApp.getCodeBase(), "icons/iTime/4.gif"), 10, 13, this);
            theApp.getToolkit().prepareImage(this.digit[5] = theApp.getImage(theApp.getCodeBase(), "icons/iTime/5.gif"), 10, 13, this);
            theApp.getToolkit().prepareImage(this.digit[6] = theApp.getImage(theApp.getCodeBase(), "icons/iTime/6.gif"), 10, 13, this);
            theApp.getToolkit().prepareImage(this.digit[7] = theApp.getImage(theApp.getCodeBase(), "icons/iTime/7.gif"), 10, 13, this);
            theApp.getToolkit().prepareImage(this.digit[8] = theApp.getImage(theApp.getCodeBase(), "icons/iTime/8.gif"), 10, 13, this);
            theApp.getToolkit().prepareImage(this.digit[9] = theApp.getImage(theApp.getCodeBase(), "icons/iTime/9.gif"), 10, 13, this);
        }
        catch (NullPointerException ex) {}
    }
    
    public void CreateImages() {
        this.bufferImg = this.createImage(300, 26);
        (this.bufferGfx = this.bufferImg.getGraphics()).setColor(Color.black);
        this.bufferGfx.fillRect(120, 3, 58, 20);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.url != null && n < 180) {
            try {
                this.theApp.getAppletContext().showDocument(new URL(this.url), "_blank");
                return true;
            }
            catch (MalformedURLException ex) {
                return false;
            }
        }
        return false;
    }
    
    private long currentDayMillis() {
        long currentTimeMillis;
        if (this.dayMillis == -1L) {
            currentTimeMillis = System.currentTimeMillis();
        }
        else {
            currentTimeMillis = this.dayMillis + (System.currentTimeMillis() - this.endMillis);
        }
        return (currentTimeMillis + 3600000L) % 86400000L;
    }
    
    public void start() {
        if (this.myThread == null) {
            (this.myThread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.myThread.stop();
        this.myThread = null;
    }
    
    public void run() {
        while (this.myThread != null) {
            this.repaint();
            try {
                Thread.sleep(1500L);
            }
            catch (InterruptedException ex) {
                this.myThread = null;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (!this.isVisible()) {
            return;
        }
        if (!this.isImagesCreated) {
            this.isImagesCreated = true;
            this.CreateImages();
        }
        this.bufferGfx.drawImage(this.banner, 0, 3, this);
        this.bufferGfx.drawImage(this.at, 125, 6, this);
        int n = (int)(this.currentDayMillis() / 86400L);
        if (n >= 1000) {
            n = 0;
        }
        final int n2 = n / 100;
        final int n3 = (n - n2 * 100) / 10;
        final int n4 = n - n2 * 100 - n3 * 10;
        this.bufferGfx.drawImage(this.digit[n2], 142, 6, this);
        this.bufferGfx.drawImage(this.digit[n3], 150, 6, this);
        this.bufferGfx.drawImage(this.digit[n4], 158, 6, this);
        this.bufferGfx.setColor(IRCQNetColors.controlColor);
        this.bufferGfx.fillRect(180, 0, 300, 26);
        this.bufferGfx.setColor(Color.black);
        final Date date = new Date(System.currentTimeMillis());
        String s = new String("" + date.getMinutes());
        if (s.length() == 1) {
            s = new String("0" + date.getMinutes());
        }
        this.bufferGfx.setFont(new Font("TimesRoman", 0, 14));
        this.bufferGfx.drawString("Local Time: " + date.getHours() + ":" + s, 185, 18);
        graphics.drawImage(this.bufferImg, 0, 0, this);
    }
}
