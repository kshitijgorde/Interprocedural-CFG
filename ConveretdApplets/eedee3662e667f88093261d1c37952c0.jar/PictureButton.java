import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Color;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PictureButton extends Applet implements Runnable
{
    private Thread loader;
    private Thread timer;
    private boolean allLoaded;
    private String linkUrl;
    private String linkTarget;
    private String linkApplet;
    private String linkMessage;
    private Polygon linkArea;
    private int linkDelay;
    private Point imagePosition;
    private Image normalImage;
    private Image mouseOverImage;
    private Image mouseDownImage;
    private Image currentImage;
    private Image buffer;
    private Image backgroundImage;
    private AudioClip mouseDownSound;
    private boolean isMouseOver;
    private boolean isMouseDown;
    private boolean isMouseCapture;
    private boolean isStatusMessage;
    
    public PictureButton() {
        this.allLoaded = false;
        this.isMouseOver = false;
        this.isMouseDown = false;
        this.isMouseCapture = false;
        this.isStatusMessage = false;
        System.out.println(this.getAppletInfo());
    }
    
    public String getAppletInfo() {
        return "PictureButton, Version 1.0" + System.getProperty("line.separator") + "Copyright (c) 1999, 2000 by R\u00fcdiger Appel, All Rights Reserved" + System.getProperty("line.separator") + "http://www.3quarks.com";
    }
    
    public void init() {
        this.linkUrl = this.getParameter("LinkUrl");
        this.linkTarget = this.getParameter("LinkTarget");
        this.linkApplet = this.getParameter("LinkApplet");
        this.linkMessage = this.getParameter("LinkMessage");
        this.linkDelay = this.getIntegerParameter("LinkDelay", 100);
        this.linkArea = this.getPolygonParameter("LinkArea");
        this.imagePosition = this.getPointParameter("ImagePosition");
        final Color colorParameter = this.getColorParameter("BackgroundColor");
        if (colorParameter != null) {
            this.setBackground(colorParameter);
        }
    }
    
    private int getIntegerParameter(final String s, final int n) {
        try {
            return Integer.parseInt(this.getParameter(s).trim());
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    private Polygon getPolygonParameter(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter(s), ",");
            final Polygon polygon = new Polygon();
            while (stringTokenizer.hasMoreTokens()) {
                polygon.addPoint(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
            }
            return polygon;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private Point getPointParameter(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter(s), ",");
            if (stringTokenizer.countTokens() == 2) {
                return new Point(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    private Color getColorParameter(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter(s), ",");
            switch (stringTokenizer.countTokens()) {
                case 1: {
                    final String trim = stringTokenizer.nextToken().trim();
                    if (trim.length() == 7 && trim.charAt(0) == '#') {
                        return new Color(Integer.parseInt(trim.substring(1, 3), 16), Integer.parseInt(trim.substring(3, 5), 16), Integer.parseInt(trim.substring(5, 7), 16));
                    }
                    return new Color(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
                }
                case 3: {
                    return new Color(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.size().width;
        final int height = this.size().height;
        if (this.buffer == null) {
            this.buffer = this.createImage(width, height);
        }
        final Graphics graphics2 = this.buffer.getGraphics();
        graphics2.clipRect(0, 0, width, height);
        graphics2.setColor(this.getBackground());
        graphics2.fillRect(0, 0, width, height);
        if (this.backgroundImage != null) {
            final int width2 = this.backgroundImage.getWidth(this);
            final int height2 = this.backgroundImage.getHeight(this);
            for (int i = 0; i < width; i += width2) {
                for (int j = 0; j < height; j += height2) {
                    graphics2.drawImage(this.backgroundImage, i, j, this);
                }
            }
        }
        this.currentImage = this.getButtonImage();
        if (this.currentImage != null) {
            if (this.imagePosition != null) {
                graphics2.drawImage(this.currentImage, this.imagePosition.x, this.imagePosition.y, this);
            }
            else {
                graphics2.drawImage(this.currentImage, (width - this.currentImage.getWidth(this)) / 2, (height - this.currentImage.getHeight(this)) / 2, this);
            }
        }
        graphics.drawImage(this.buffer, 0, 0, this);
    }
    
    private Image getButtonImage() {
        Image image;
        if (this.isMouseOver) {
            if (this.isMouseDown) {
                image = this.mouseDownImage;
            }
            else {
                image = this.mouseOverImage;
            }
        }
        else {
            image = this.normalImage;
        }
        if (image != null) {
            return image;
        }
        if (this.isMouseOver && this.mouseOverImage != null) {
            return this.mouseOverImage;
        }
        return this.normalImage;
    }
    
    private void updateButton(final boolean isMouseOver, final boolean isMouseDown, final boolean isMouseCapture) {
        if (isMouseDown && !this.isMouseDown && this.mouseDownSound != null) {
            this.mouseDownSound.play();
        }
        this.isMouseOver = isMouseOver;
        this.isMouseDown = isMouseDown;
        this.isMouseCapture = isMouseCapture;
        if (this.currentImage != this.getButtonImage()) {
            this.repaint();
        }
        if (this.isStatusMessage != (this.isMouseOver || this.isMouseDown)) {
            this.isStatusMessage = (this.isMouseOver || this.isMouseDown);
            if (this.isStatusMessage) {
                if (this.linkMessage != null) {
                    this.showStatus(this.linkMessage);
                    return;
                }
                if (this.linkUrl != null) {
                    this.showStatus(this.linkUrl);
                }
            }
            else {
                this.showStatus("Done");
            }
        }
    }
    
    private boolean mouseHit(final int n, final int n2) {
        return n >= 0 && n < this.size().width && n2 >= 0 && n2 < this.size().height && (this.linkArea == null || this.linkArea.inside(n, n2));
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.updateButton(this.mouseHit(n, n2), false, false);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.updateButton(false, false, false);
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.updateButton(this.mouseHit(n, n2), false, this.isMouseCapture);
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.isMouseCapture) {
            this.updateButton(this.mouseHit(n, n2), this.mouseHit(n, n2), true);
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.mouseHit(n, n2)) {
            this.updateButton(true, true, true);
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        final boolean b = this.isMouseCapture && this.mouseHit(n, n2);
        this.updateButton(this.mouseHit(n, n2), false, false);
        if (b && this.timer == null) {
            (this.timer = new Thread(this)).start();
        }
        return true;
    }
    
    public void start() {
        if (!this.allLoaded) {
            (this.loader = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.loader != null) {
            this.loader.stop();
            this.loader = null;
        }
        if (this.timer != null) {
            this.timer.stop();
            this.timer = null;
        }
    }
    
    public void run() {
        if (Thread.currentThread() == this.loader) {
            final MediaTracker mediaTracker = new MediaTracker(this);
            this.backgroundImage = this.loadImage(mediaTracker, this.getParameter("BackgroundImage"), 1);
            if (this.backgroundImage != null) {
                this.repaint();
            }
            this.normalImage = this.loadImage(mediaTracker, this.getParameter("NormalImage"), 2);
            if (this.normalImage != null) {
                this.repaint();
            }
            this.mouseOverImage = this.loadImage(mediaTracker, this.getParameter("MouseOverImage"), 3);
            if (this.normalImage == null && this.mouseOverImage != null) {
                this.repaint();
            }
            this.mouseDownImage = this.loadImage(mediaTracker, this.getParameter("MouseDownImage"), 4);
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("MouseDownSound"), ",");
                this.mouseDownSound = this.getAudioClip(this.getCodeBase(), stringTokenizer.nextToken().trim());
                if (stringTokenizer.nextToken().trim().equalsIgnoreCase("safety") && (System.getProperty("os.name").equalsIgnoreCase("Windows 95") || System.getProperty("os.name").equalsIgnoreCase("Windows 98"))) {
                    this.mouseDownSound = null;
                }
            }
            catch (Exception ex) {}
            this.allLoaded = true;
        }
        if (Thread.currentThread() == this.timer) {
            try {
                Thread.sleep(this.linkDelay);
            }
            catch (InterruptedException ex2) {}
            this.activateLink();
        }
    }
    
    private Image loadImage(final MediaTracker mediaTracker, final String s, final int n) {
        if (s != null) {
            final Image image = this.getImage(this.getCodeBase(), s);
            mediaTracker.addImage(image, n);
            try {
                mediaTracker.waitForID(n);
                if (!mediaTracker.isErrorID(n)) {
                    return image;
                }
            }
            catch (InterruptedException ex) {}
        }
        return null;
    }
    
    private void activateLink() {
        this.timer = null;
        if (this.linkApplet != null) {
            final Applet applet = this.getAppletContext().getApplet(this.linkApplet);
            if (applet != null) {
                applet.handleEvent(new Event(this, 1001, this.getParameter("Name")));
            }
        }
        if (this.linkUrl != null) {
            try {
                final URL url = new URL(this.getCodeBase(), this.linkUrl);
                if (this.linkTarget == null) {
                    this.getAppletContext().showDocument(url);
                    return;
                }
                this.getAppletContext().showDocument(url, this.linkTarget);
            }
            catch (MalformedURLException ex) {}
        }
    }
}
