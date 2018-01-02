import java.awt.Cursor;
import java.awt.Event;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Color;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class WebBanner extends Applet implements Runnable
{
    String szStatusWindow;
    String Author;
    Font hfont;
    FontMetrics hfm;
    boolean bloaded;
    boolean bStatusWindow;
    Thread loopThread;
    Thread loadThread;
    Image offImage;
    Graphics offScreen;
    Vector vImageStats;
    int iImage;
    int iDelay;
    int iTotalImages;
    int iCurrentImageCnt;
    boolean bUrlActive;
    boolean bAuthor;
    Color BackgroundColor;
    
    public void init() {
        this.getGraphics();
        this.getSize();
        this.offImage = this.createImage(this.getSize().width, this.getSize().height);
        this.offScreen = this.offImage.getGraphics();
        this.hfont = new Font("TimesRoman", 2, 15);
        this.hfm = this.getFontMetrics(this.hfont);
        this.iImage = 0;
        this.iDelay = 0;
        this.iTotalImages = 0;
        this.iCurrentImageCnt = 0;
        this.bUrlActive = false;
        this.bAuthor = false;
        this.bloaded = false;
        this.bStatusWindow = false;
        this.Author = "WebBanner - Copyright(c) 2001 WebAppstogo";
        this.vImageStats = new Vector();
    }
    
    public void loadImages() {
        int iTotalImages = 0;
        Image image = null;
        this.szStatusWindow = this.getParameter("Status");
        if (this.szStatusWindow != null) {
            this.bStatusWindow = true;
        }
        final String parameter = this.getParameter("Author");
        if (parameter == null) {
            return;
        }
        if (parameter.equalsIgnoreCase(this.Author)) {
            this.bAuthor = true;
            final String parameter2 = this.getParameter("Delay");
            if (parameter2 == null) {
                this.iDelay = 5000;
            }
            else {
                this.iDelay = Integer.parseInt(parameter2);
                if (this.iDelay < 1) {
                    this.iDelay = 1;
                }
            }
            final String s = "background";
            if (this.getParameter(s) == null) {
                this.BackgroundColor = Color.black;
            }
            else {
                this.BackgroundColor = this.getColor(this.getParameter(s));
            }
            while (this.getParameter("Image".concat(String.valueOf(String.valueOf(++iTotalImages)))) != null) {}
            this.iTotalImages = iTotalImages;
            int n = 0;
            this.showStatus("Loading Images...");
            while (true) {
                ++n;
                final String parameter3 = this.getParameter("Url".concat(String.valueOf(String.valueOf(n))));
                final String parameter4 = this.getParameter("Image".concat(String.valueOf(String.valueOf(n))));
                this.iCurrentImageCnt = n - 1;
                if (parameter4 == null) {
                    break;
                }
                try {
                    image = this.getImage(new URL(this.getDocumentBase(), parameter4));
                }
                catch (MalformedURLException ex) {}
                while (image.getWidth(this) == -1) {
                    this.delay(100);
                }
                this.vImageStats.addElement(new ImageStats(image, parameter3));
            }
            this.repaint();
            if (this.bStatusWindow) {
                this.showStatus(this.szStatusWindow);
            }
            else {
                this.showStatus(this.Author);
            }
        }
    }
    
    public void start() {
        if (this.loopThread == null) {
            (this.loopThread = new Thread(this)).start();
        }
        if (!this.bloaded && this.loadThread == null) {
            (this.loadThread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.loopThread != null) {
            this.loopThread = null;
            this.vImageStats.removeAllElements();
        }
        if (this.loadThread != null) {
            this.loadThread = null;
        }
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        long currentTimeMillis = System.currentTimeMillis();
        if (!this.bloaded && Thread.currentThread() == this.loadThread) {
            this.loadImages();
            this.bloaded = true;
            this.loadThread = null;
        }
        while (Thread.currentThread() == this.loopThread) {
            if (!this.bUrlActive) {
                this.startBanner();
            }
            this.repaint();
            try {
                currentTimeMillis += 10;
                Thread.sleep(Math.max(0L, currentTimeMillis - System.currentTimeMillis()));
                continue;
            }
            catch (InterruptedException ex) {}
            break;
        }
    }
    
    Color getColor(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        try {
            return new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
        }
        catch (Exception ex) {
            return Color.black;
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.setCursor(Cursor.getPredefinedCursor(12));
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.setCursor(Cursor.getPredefinedCursor(13));
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.bloaded) {
            return true;
        }
        if (this.bUrlActive) {
            return true;
        }
        for (int i = 0; i < this.vImageStats.size(); ++i) {
            final ImageStats imageStats = this.vImageStats.elementAt(i);
            if (imageStats.IsUrlAvailable()) {
                this.getAppletContext().showDocument(imageStats.GetUrl());
                return this.bUrlActive = true;
            }
        }
        return true;
    }
    
    public void startBanner() {
        for (int i = 0; i < this.vImageStats.size(); ++i) {
            final ImageStats imageStats = this.vImageStats.elementAt(i);
            final ImageStats imageStats2 = this.vImageStats.elementAt(i);
            this.iImage = i;
            this.repaint();
            this.delay(this.iDelay);
        }
    }
    
    public void delay(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        this.offScreen.setColor(this.BackgroundColor);
        this.offScreen.fillRect(0, 0, this.getSize().width, this.getSize().height);
        if (!this.bAuthor) {
            this.offScreen.setColor(Color.red);
            this.offScreen.drawString("Fill in author value with", 0, this.getSize().height / 2);
            this.offScreen.drawString(this.Author, 0, this.getSize().height / 2 + this.hfm.getHeight());
        }
        else if (!this.bloaded) {
            this.offScreen.setColor(Color.white);
            this.offScreen.drawString(String.valueOf(String.valueOf(new StringBuffer("Loading image ").append(this.iCurrentImageCnt).append(" of ").append(this.iTotalImages - 1))), this.getSize().width / 3, this.getSize().height / 2);
        }
        else if (this.vImageStats.size() > 0) {
            final ImageStats imageStats = this.vImageStats.elementAt(this.iImage);
            this.offScreen.drawImage(imageStats.Img, imageStats.xImagePos, imageStats.yImagePos, this);
        }
        graphics.drawImage(this.offImage, 0, 0, this);
    }
}
