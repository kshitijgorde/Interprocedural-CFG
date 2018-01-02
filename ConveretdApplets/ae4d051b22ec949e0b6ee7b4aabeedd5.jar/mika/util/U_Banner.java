// 
// Decompiled by Procyon v0.5.30
// 

package mika.util;

import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import mika.system.S_Debug;
import java.applet.Applet;
import java.awt.Color;
import java.util.Vector;
import java.awt.Panel;

public class U_Banner extends Panel implements Runnable
{
    private String m_strLinkDescription;
    private String m_strLink;
    private Vector m_vecImage;
    private Vector m_vecDelay;
    private int m_iImageToShow;
    private Color m_clrBackground;
    private Thread m_BannerThread;
    private boolean m_bRunning;
    private Applet m_applet;
    
    public U_Banner() {
        this.m_strLinkDescription = "";
        this.m_strLink = "";
        this.m_vecImage = new Vector();
        this.m_vecDelay = new Vector();
        this.m_iImageToShow = 0;
        this.m_clrBackground = Color.black;
        this.m_BannerThread = null;
        this.m_bRunning = false;
        this.m_applet = null;
    }
    
    public void init() {
        try {
            (this.m_BannerThread = new Thread(this, "Banner thread")).setPriority(1);
        }
        catch (Exception ex) {
            S_Debug.print(ex);
        }
    }
    
    public void start() {
        if (this.m_BannerThread != null) {
            this.m_BannerThread.start();
            this.m_bRunning = true;
        }
    }
    
    public void stop() {
        if (this.m_BannerThread != null) {
            this.m_BannerThread.stop();
            this.m_bRunning = false;
        }
    }
    
    public void setApplet(final Applet applet) {
        this.m_applet = applet;
    }
    
    public void addImage(final Image image, final float n) {
        this.m_vecImage.addElement(image);
        this.m_vecDelay.addElement(new Float(n));
    }
    
    public void setLink(final String strLink, final String strLinkDescription) {
        this.m_strLink = strLink;
        this.m_strLinkDescription = strLinkDescription;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final Image image = this.m_vecImage.elementAt(this.m_iImageToShow);
        final int width = this.size().width;
        final int height = this.size().height;
        final int n = (width - image.getWidth(this)) / 2;
        final int n2 = (height - image.getHeight(this)) / 2;
        if (n != 0) {
            graphics.setColor(this.m_clrBackground);
            graphics.fillRect(0, 0, n, height);
            graphics.fillRect(width - n, 0, n, height);
        }
        if (n2 != 0) {
            graphics.setColor(this.m_clrBackground);
            graphics.fillRect(0, 0, width, n2);
            graphics.fillRect(0, height - n2, width, n2);
        }
        graphics.drawImage(image, n, n2, this);
    }
    
    public void run() {
        try {
            if (this.m_vecDelay.size() == 0) {
                S_Debug.print("[U_Banner::run] No images set. Thread not started.");
                return;
            }
            while (true) {
                Thread.sleep((int)(this.m_vecDelay.elementAt(this.m_iImageToShow) * 1000.0f));
                this.m_iImageToShow = ++this.m_iImageToShow % this.m_vecImage.size();
                this.repaint();
            }
        }
        catch (Exception ex) {
            S_Debug.print(ex);
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        try {
            if (this.m_strLink != null && this.m_applet != null) {
                this.m_applet.getAppletContext().showDocument(new URL(this.m_strLink));
            }
            return true;
        }
        catch (Exception ex) {
            S_Debug.print(ex);
            return true;
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.m_strLinkDescription != null && this.m_applet != null) {
            this.m_applet.showStatus(this.m_strLinkDescription);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.m_applet != null) {
            this.m_applet.showStatus("");
        }
        return true;
    }
}
