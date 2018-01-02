// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Point;
import java.awt.Image;
import java.awt.Canvas;

public class VideoCanvas extends Canvas
{
    Image fOffscreenImage;
    Image errImg;
    boolean fGettingFirst;
    String fErrorString;
    String fLoadingString;
    String caption;
    boolean mouse_pressed;
    Point mouseStart;
    NuApplet applet;
    int videoWidth;
    int videoHeight;
    int minCanvasWidth;
    int minCanvasHeight;
    int videoOffsetX;
    int videoOffsetY;
    int captionPosX;
    int captionPosY;
    final Color errorColor1;
    final Color errorColor2;
    final Color captionColor1;
    final Color captionColor2;
    final Color black;
    Image titlePage;
    Image lastImage;
    Image logoImage;
    int logoX;
    int logoY;
    int logoWidth;
    int logoHeight;
    boolean invert;
    boolean crosshairs;
    Font f;
    
    public VideoCanvas(final int width, final int height, final String loadString, final Image titleImage) {
        this.fOffscreenImage = null;
        this.errImg = null;
        this.fGettingFirst = true;
        this.fErrorString = "";
        this.fLoadingString = null;
        this.caption = null;
        this.mouse_pressed = false;
        this.applet = null;
        this.videoWidth = 0;
        this.videoHeight = 0;
        this.minCanvasWidth = 0;
        this.minCanvasHeight = 0;
        this.videoOffsetX = 0;
        this.videoOffsetY = 0;
        this.captionPosX = 10;
        this.captionPosY = 200;
        this.errorColor1 = new Color(0, 0, 0);
        this.errorColor2 = new Color(255, 0, 0);
        this.captionColor1 = new Color(255, 255, 255);
        this.captionColor2 = new Color(0, 0, 0);
        this.black = new Color(0, 0, 0);
        this.invert = false;
        this.crosshairs = false;
        this.f = new Font("SansSerif", 0, 12);
        this.videoWidth = width;
        this.minCanvasWidth = width;
        this.videoHeight = height;
        this.minCanvasHeight = height;
        this.titlePage = titleImage;
        this.fErrorString = "";
        this.fLoadingString = loadString;
        this.resize(width, height);
    }
    
    public VideoCanvas(final int width, final int height) {
        this.fOffscreenImage = null;
        this.errImg = null;
        this.fGettingFirst = true;
        this.fErrorString = "";
        this.fLoadingString = null;
        this.caption = null;
        this.mouse_pressed = false;
        this.applet = null;
        this.videoWidth = 0;
        this.videoHeight = 0;
        this.minCanvasWidth = 0;
        this.minCanvasHeight = 0;
        this.videoOffsetX = 0;
        this.videoOffsetY = 0;
        this.captionPosX = 10;
        this.captionPosY = 200;
        this.errorColor1 = new Color(0, 0, 0);
        this.errorColor2 = new Color(255, 0, 0);
        this.captionColor1 = new Color(255, 255, 255);
        this.captionColor2 = new Color(0, 0, 0);
        this.black = new Color(0, 0, 0);
        this.invert = false;
        this.crosshairs = false;
        this.f = new Font("SansSerif", 0, 12);
        this.videoWidth = width;
        this.minCanvasWidth = width;
        this.videoHeight = height;
        this.minCanvasHeight = height;
        this.fErrorString = "";
        this.resize(width, height);
    }
    
    void setProgressString(final String s) {
        this.fLoadingString = new String(s);
        this.repaint();
    }
    
    void clearLogo() {
        this.logoImage = null;
    }
    
    void setLogo(final Image i, String wh) {
        this.logoImage = i;
        this.logoX = 0;
        this.logoY = 0;
        if (wh == null) {
            wh = "SE";
        }
        wh = wh.toUpperCase();
        if ("OFF".equals(wh)) {
            this.logoImage = null;
        }
        if (this.logoImage != null) {
            this.logoWidth = i.getWidth(this);
            this.logoHeight = i.getHeight(this);
            if (wh != null) {
                this.logoX = this.minCanvasWidth / 2 - this.logoWidth / 2;
                this.logoY = this.minCanvasHeight / 2 - this.logoHeight / 2;
                if (wh.indexOf("N") != -1) {
                    this.logoY = 0;
                }
                if (wh.indexOf("S") != -1) {
                    this.logoY = this.minCanvasHeight - this.logoHeight;
                }
                if (wh.indexOf("E") != -1) {
                    this.logoX = this.minCanvasWidth - this.logoWidth;
                }
                if (wh.indexOf("W") != -1) {
                    this.logoX = 0;
                }
            }
        }
    }
    
    VideoCanvas(final int vWidth, final int vHeight, final int cWidth, final int cHeight, final String loadString, final Image titleImage) {
        this.fOffscreenImage = null;
        this.errImg = null;
        this.fGettingFirst = true;
        this.fErrorString = "";
        this.fLoadingString = null;
        this.caption = null;
        this.mouse_pressed = false;
        this.applet = null;
        this.videoWidth = 0;
        this.videoHeight = 0;
        this.minCanvasWidth = 0;
        this.minCanvasHeight = 0;
        this.videoOffsetX = 0;
        this.videoOffsetY = 0;
        this.captionPosX = 10;
        this.captionPosY = 200;
        this.errorColor1 = new Color(0, 0, 0);
        this.errorColor2 = new Color(255, 0, 0);
        this.captionColor1 = new Color(255, 255, 255);
        this.captionColor2 = new Color(0, 0, 0);
        this.black = new Color(0, 0, 0);
        this.invert = false;
        this.crosshairs = false;
        this.f = new Font("SansSerif", 0, 12);
        this.minCanvasWidth = cWidth;
        this.minCanvasHeight = cHeight;
        this.setVideoSize(vWidth, vHeight, false);
        this.titlePage = titleImage;
        this.fErrorString = "";
        this.fLoadingString = loadString;
        this.resize(cWidth, cHeight);
    }
    
    public void setVideoSize(final int vWidth, final int vHeight, final boolean resize) {
        this.fOffscreenImage = null;
        final boolean repaint = this.videoWidth != 0;
        this.videoWidth = vWidth;
        this.videoHeight = vHeight;
        if (resize) {
            this.videoOffsetX = 0;
            this.videoOffsetY = 0;
            this.minCanvasWidth = this.videoWidth;
            this.minCanvasHeight = this.videoHeight;
            this.resize(this.minCanvasWidth, this.minCanvasHeight);
        }
        this.fErrorString = null;
        if (repaint) {
            this.repaint();
        }
    }
    
    public synchronized void paint(final Graphics g) {
        if (g == null) {
            return;
        }
        if (this.fOffscreenImage != null) {
            final Rectangle r = this.bounds();
            g.drawImage(this.fOffscreenImage, this.videoOffsetX, this.videoOffsetY, r.width, r.height, this);
        }
        else if (this.titlePage != null) {
            g.drawImage(this.titlePage, 0, 0, this.videoWidth, this.videoHeight, this);
            if (this.applet != null) {
                this.applet.vis = true;
            }
        }
        else if (this.errImg != null) {
            g.drawImage(this.errImg, 0, 0, this.videoWidth, this.videoHeight, this);
        }
    }
    
    Image getLastImage() {
        return this.lastImage;
    }
    
    synchronized void takeImage(final Image inImage) {
        if (this.fOffscreenImage == null) {
            this.fOffscreenImage = this.createImage(this.videoWidth, this.videoHeight);
            if (this.fOffscreenImage == null) {
                try {
                    Thread.sleep(500L);
                }
                catch (InterruptedException ex) {}
                return;
            }
            this.fGettingFirst = false;
        }
        this.lastImage = inImage;
        final Graphics buffer = this.fOffscreenImage.getGraphics();
        try {
            for (int x = 0; x < 20; ++x) {
                boolean ok;
                if (!this.invert) {
                    ok = buffer.drawImage(inImage, 0, 0, this.videoWidth, this.videoHeight, this);
                }
                else {
                    ok = buffer.drawImage(inImage, 0, 0, this.videoWidth, this.videoHeight, this.videoWidth, this.videoHeight, 0, 0, null);
                }
                if (ok) {
                    break;
                }
                Thread.sleep(40L);
            }
            if (this.caption != null && this.caption != "") {
                final int width = buffer.getFontMetrics().stringWidth(this.caption);
                final int x2 = this.videoWidth - width - 10;
                final int y = 15;
                buffer.setFont(this.f);
                buffer.setColor(this.captionColor1);
                buffer.drawString(this.caption, x2, y);
                buffer.setColor(this.captionColor2);
                buffer.drawString(this.caption, x2 - 1, y - 1);
                buffer.setColor(this.black);
            }
            if (this.logoImage != null) {
                buffer.drawImage(this.logoImage, this.logoX, this.logoY, this.logoWidth, this.logoHeight, this);
            }
            if (this.crosshairs) {
                buffer.setColor(Color.blue);
                final int cx = this.videoWidth / 2;
                final int cy = this.videoHeight / 2;
                buffer.drawLine(cx - 5, cy, cx + 5, cy);
                buffer.drawLine(cx, cy - 5, cx, cy + 5);
            }
        }
        catch (Exception e) {
            Debug.report(e, "draw video frame");
        }
        catch (OutOfMemoryError om) {
            Debug.report(om, "Memory error drawing video frame");
        }
        this.paint(this.getGraphics());
    }
    
    void setErrorStr(final String inStr, final Image img) {
        this.fErrorString = inStr;
        this.errImg = img;
        this.repaint();
    }
    
    void clearError() {
        this.fErrorString = "";
        this.errImg = null;
        this.fOffscreenImage = null;
        this.repaint();
    }
    
    void setCaption(final String inStr) {
        this.caption = inStr;
    }
    
    public void addListener(final NuApplet in_applet) {
        this.applet = in_applet;
    }
    
    public void processActionEvent(final ActionEvent ae) {
    }
    
    public void mouseDown(final int x, final int y) {
        final double w = this.getWidth();
        final double h = this.getHeight();
        final double xx = x / w;
        final double yy = y / h;
        if (this.applet != null) {
            this.applet.sendMessage(1016, new Point((int)(xx * 10000.0), (int)(yy * 10000.0)));
        }
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        this.mouseDown(x, y);
        return true;
    }
    
    public int getWidth() {
        return this.bounds().width;
    }
    
    public int getHeight() {
        return this.bounds().height;
    }
}
