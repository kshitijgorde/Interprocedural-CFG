import java.awt.Cursor;
import java.awt.Event;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageViewer extends Applet implements Runnable
{
    String WebAppstogo;
    String Author;
    String Registered;
    String szStatusWindow;
    Font hfont;
    FontMetrics hfm;
    Color hColor;
    boolean bTrialVersion;
    boolean bloaded;
    boolean bStatusWindow;
    Thread loopThread;
    Thread loadThread;
    Image offImage;
    Graphics offScreen;
    Vector vImageStats;
    final int EXPAND = 1;
    final int SHRINK = 2;
    final int CS_WAIT = 1;
    boolean bDrawing;
    int iImage;
    int iMode;
    int iSpeed;
    int iImageSize;
    int iTotalImages;
    int iCurrentImageCnt;
    boolean bSetHighlight;
    boolean bUrlActive;
    boolean bAuthor;
    Color BackgroundColor;
    Image j1;
    Image j2;
    Image j3;
    Image j4;
    
    public ImageViewer() {
        this.WebAppstogo = "WebAppstogo.com";
        this.Author = "ImageViewer - Copyright(c) 2001 WebAppstogo";
        this.Registered = "1LFJFGI5KDE";
        this.hfont = new Font("TimesRoman", 2, 15);
        this.hfm = this.getFontMetrics(this.hfont);
        this.bTrialVersion = true;
        this.bloaded = false;
        this.bStatusWindow = false;
        this.vImageStats = new Vector();
        this.bDrawing = false;
        this.iImage = 0;
        this.iMode = 2;
        this.iSpeed = 0;
        this.iImageSize = 0;
        this.iTotalImages = 0;
        this.iCurrentImageCnt = 0;
        this.bSetHighlight = false;
        this.bUrlActive = false;
        this.bAuthor = false;
    }
    
    public void init() {
        this.getGraphics();
        this.size();
        this.offImage = this.createImage(this.size().width, this.size().height);
        this.offScreen = this.offImage.getGraphics();
    }
    
    public void loadImages() {
        int iTotalImages = 0;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        Image image = null;
        Image image2 = null;
        this.szStatusWindow = this.getParameter("Status");
        if (this.szStatusWindow != null) {
            this.bStatusWindow = true;
        }
        final String parameter = this.getParameter("Reg");
        if (parameter != null && parameter.equalsIgnoreCase(this.Registered)) {
            this.bTrialVersion = false;
        }
        final String parameter2 = this.getParameter("Author");
        if (parameter2 == null) {
            return;
        }
        if (parameter2.equalsIgnoreCase(this.Author)) {
            this.bAuthor = true;
            while (this.getParameter("Image".concat(String.valueOf(String.valueOf(++iTotalImages)))) != null) {}
            this.iTotalImages = iTotalImages;
            int n5 = 0;
            final String parameter3 = this.getParameter("Speed");
            if (parameter3 == null) {
                this.iImageSize = 15;
            }
            else {
                this.iSpeed = Integer.parseInt(parameter3);
                if (this.iSpeed > 50) {
                    this.iSpeed = 50;
                }
                if (this.iSpeed < 1) {
                    this.iSpeed = 1;
                }
            }
            final String parameter4 = this.getParameter("ImageSize");
            if (parameter4 == null) {
                this.iImageSize = 4;
            }
            else {
                this.iImageSize = Integer.parseInt(parameter4);
                if (this.iImageSize > 100) {
                    this.iImageSize = 100;
                }
                if (this.iImageSize < 1) {
                    this.iImageSize = 1;
                }
            }
            final String s = "background";
            if (this.getParameter(s) == null) {
                this.BackgroundColor = Color.black;
            }
            else {
                this.BackgroundColor = this.getColor(this.getParameter(s));
            }
            this.showStatus("Loading Images...");
            while (true) {
                ++n5;
                final String parameter5 = this.getParameter("Url".concat(String.valueOf(String.valueOf(n5))));
                final String parameter6 = this.getParameter("Image".concat(String.valueOf(String.valueOf(n5))));
                this.iCurrentImageCnt = n5 - 1;
                if (parameter6 == null) {
                    break;
                }
                try {
                    image = this.getImage(new URL(this.getDocumentBase(), parameter6));
                }
                catch (MalformedURLException ex) {}
                while (image.getWidth(this) == -1) {}
                if (image2 != null) {
                    this.vImageStats.addElement(new ImageStats(image, n3, n4, image2.getWidth(this) / this.iImageSize, image2.getHeight(this) / this.iImageSize, parameter5));
                }
                else {
                    this.vImageStats.addElement(new ImageStats(image, n3, n4, image.getWidth(this) / this.iImageSize, image.getHeight(this) / this.iImageSize, parameter5));
                }
                image2 = image;
                n3 = image2.getWidth(this) / this.iImageSize * ++n;
                if (n3 + image2.getWidth(this) / this.iImageSize <= this.size().width) {
                    continue;
                }
                n3 = 0;
                n = 0;
                n4 = image2.getHeight(this) / this.iImageSize * ++n2;
            }
            if (this.bStatusWindow && !this.bTrialVersion) {
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
            this.loopThread.stop();
            this.loopThread = null;
        }
        if (this.loadThread != null) {
            this.loadThread.stop();
            this.loadThread = null;
        }
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        long currentTimeMillis = System.currentTimeMillis();
        if (!this.bloaded && Thread.currentThread() == this.loadThread) {
            this.loadImages();
            this.bloaded = true;
            this.loadThread.stop();
        }
        while (Thread.currentThread() == this.loopThread) {
            if (!this.bUrlActive && this.bDrawing) {
                this.bDrawing = false;
                this.startImage(this.iImage, this.iMode);
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
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.bSetHighlight = false;
        return true;
    }
    
    public boolean mouseBrowse(final int n, final int n2, final int n3, final int n4, final String s, final FontMetrics fontMetrics) {
        final int n5 = n3 + fontMetrics.stringWidth(s);
        final int n6 = n4 + (fontMetrics.getHeight() - 20);
        return n >= n3 && n2 >= n4 - fontMetrics.getHeight() && n <= n5 && n2 <= n6;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.mouseBrowse(n, n2, this.size().width - this.hfm.stringWidth(this.WebAppstogo) - 5, this.size().height - (this.hfm.getHeight() - 15), this.WebAppstogo, this.hfm)) {
            this.bSetHighlight = true;
        }
        else {
            this.bSetHighlight = false;
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.bloaded) {
            return true;
        }
        if (this.bUrlActive) {
            return true;
        }
        if (this.bSetHighlight && this.bTrialVersion) {
            try {
                this.getAppletContext().showDocument(new URL("http://www.webappstogo.com"));
            }
            catch (MalformedURLException ex) {}
            return this.bUrlActive = true;
        }
        if (this.bDrawing) {
            return true;
        }
        int i = 0;
        while (i < this.vImageStats.size()) {
            final ImageStats imageStats = this.vImageStats.elementAt(i);
            if (imageStats.State == 1) {
                if (imageStats.IsUrlAvailable()) {
                    this.getAppletContext().showDocument(imageStats.GetUrl());
                    return this.bUrlActive = true;
                }
                final ImageStats imageStats2 = imageStats;
                final int n3 = 2;
                this.iMode = n3;
                imageStats2.State = n3;
                this.bDrawing = true;
                this.iImage = i;
                return true;
            }
            else {
                if (imageStats.CheckBrowse(n, n2)) {
                    this.bDrawing = true;
                    this.iImage = i;
                    final ImageStats imageStats3 = imageStats;
                    final boolean b = true;
                    this.iMode = (b ? 1 : 0);
                    imageStats3.State = (b ? 1 : 0);
                }
                ++i;
            }
        }
        return true;
    }
    
    public void startImage(final int n, final int n2) {
        switch (n2) {
            case 1: {
                this.scroll(n, 1, 0, 0);
                break;
            }
            case 2: {
                this.scroll(n, 2, 0, 0);
                break;
            }
        }
    }
    
    public void scroll(final int n, final int n2, final int n3, final int n4) {
        final ImageStats imageStats = this.vImageStats.elementAt(n);
        final int width = this.size().width;
        final int height = this.size().height;
        switch (n2) {
            case 1: {
                while (imageStats.xImageSize < width || imageStats.yImageSize < height || imageStats.xImagePos != 0 || imageStats.yImagePos != 0) {
                    if (imageStats.xImagePos > 0) {
                        final ImageStats imageStats2 = imageStats;
                        imageStats2.xImagePos -= this.iSpeed * 2;
                    }
                    else {
                        imageStats.xImagePos = 0;
                    }
                    if (imageStats.yImagePos > 0) {
                        final ImageStats imageStats3 = imageStats;
                        imageStats3.yImagePos -= this.iSpeed * 2;
                    }
                    else {
                        imageStats.yImagePos = 0;
                    }
                    if (imageStats.xImageSize < width) {
                        final ImageStats imageStats4 = imageStats;
                        imageStats4.xImageSize += this.iSpeed * 2;
                    }
                    else {
                        imageStats.xImageSize = width;
                    }
                    if (imageStats.yImageSize < height) {
                        final ImageStats imageStats5 = imageStats;
                        imageStats5.yImageSize += this.iSpeed * 2;
                    }
                    else {
                        imageStats.yImageSize = height;
                    }
                    this.repaint();
                    this.pause(5);
                }
                break;
            }
            case 2: {
                while (imageStats.xImageSize > imageStats.xImageSizeOrg || imageStats.yImageSize > imageStats.yImageSizeOrg || imageStats.xImagePos != imageStats.xImagePosOrg || imageStats.yImagePos != imageStats.yImagePosOrg) {
                    if (imageStats.xImagePos < imageStats.xImagePosOrg) {
                        final ImageStats imageStats6 = imageStats;
                        imageStats6.xImagePos += this.iSpeed * 2;
                    }
                    else {
                        imageStats.xImagePos = imageStats.xImagePosOrg;
                    }
                    if (imageStats.yImagePos < imageStats.yImagePosOrg) {
                        final ImageStats imageStats7 = imageStats;
                        imageStats7.yImagePos += this.iSpeed * 2;
                    }
                    else {
                        imageStats.yImagePos = imageStats.yImagePosOrg;
                    }
                    if (imageStats.xImageSize > imageStats.xImageSizeOrg) {
                        final ImageStats imageStats8 = imageStats;
                        imageStats8.xImageSize -= this.iSpeed * 2;
                    }
                    else {
                        imageStats.xImageSize = imageStats.xImageSizeOrg;
                    }
                    if (imageStats.yImageSize > imageStats.yImageSizeOrg) {
                        final ImageStats imageStats9 = imageStats;
                        imageStats9.yImageSize -= this.iSpeed * 2;
                    }
                    else {
                        imageStats.yImageSize = imageStats.yImageSizeOrg;
                    }
                    this.repaint();
                    this.pause(5);
                }
                break;
            }
        }
    }
    
    public void pause(final int n) {
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
        this.offScreen.fillRect(0, 0, this.size().width, this.size().height);
        if (!this.bAuthor) {
            this.offScreen.setColor(Color.red);
            this.offScreen.drawString("Fill in author value with", 0, this.size().height / 2);
            this.offScreen.drawString(this.Author, 0, this.size().height / 2 + this.hfm.getHeight());
        }
        else if (!this.bloaded) {
            this.offScreen.setColor(Color.white);
            this.offScreen.drawString(String.valueOf(String.valueOf(new StringBuffer("Loading image ").append(this.iCurrentImageCnt).append(" of ").append(this.iTotalImages - 1))), this.size().width / 3, this.size().height / 2);
        }
        else {
            for (int i = 0; i < this.vImageStats.size(); ++i) {
                final ImageStats imageStats = this.vImageStats.elementAt(i);
                this.offScreen.drawImage(imageStats.Img, imageStats.xImagePos, imageStats.yImagePos, imageStats.xImageSize, imageStats.yImageSize, this);
            }
            final ImageStats imageStats2 = this.vImageStats.elementAt(this.iImage);
            this.offScreen.drawImage(imageStats2.Img, imageStats2.xImagePos, imageStats2.yImagePos, imageStats2.xImageSize, imageStats2.yImageSize, this);
            if (this.bTrialVersion) {
                if (this.bSetHighlight) {
                    this.setCursor(Cursor.getPredefinedCursor(12));
                    this.offScreen.setColor(Color.blue);
                }
                else {
                    this.setCursor(Cursor.getPredefinedCursor(0));
                    this.offScreen.setColor(Color.white);
                }
                final int n = this.size().width - this.hfm.stringWidth(this.WebAppstogo) - 5;
                final int n2 = this.size().height - (this.hfm.getHeight() - 10);
                this.offScreen.setFont(this.hfont);
                this.offScreen.drawString(this.WebAppstogo, n, n2);
            }
        }
        graphics.drawImage(this.offImage, 0, 0, this);
    }
}
