import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.util.StringTokenizer;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SoundButton extends Applet implements Runnable
{
    private Thread loader;
    private boolean allLoaded;
    private Applet receiver;
    private String onMessage;
    private String offMessage;
    private Image buttonImage;
    private boolean isMouseOver;
    private boolean isSoundEnabled;
    
    public SoundButton() {
        this.allLoaded = false;
        this.isMouseOver = false;
        this.isSoundEnabled = false;
        System.out.println(this.getAppletInfo());
    }
    
    public String getAppletInfo() {
        return "SoundButton, Version 1.0" + System.getProperty("line.separator") + "Copyright (c) 1999 by R\u00fcdiger Appel, All Rights Reserved" + System.getProperty("line.separator") + "See also: http://www.3quarks.com";
    }
    
    public void init() {
        this.onMessage = this.getParameter("onMessage");
        this.offMessage = this.getParameter("offMessage");
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("BackgroundColor"), ",");
            if (stringTokenizer.countTokens() == 1) {
                final String trim = stringTokenizer.nextToken().trim();
                this.setBackground(new Color(Integer.parseInt(trim.substring(1, 3), 16), Integer.parseInt(trim.substring(3, 5), 16), Integer.parseInt(trim.substring(5, 7), 16)));
                return;
            }
            this.setBackground(new Color(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim())));
        }
        catch (Exception ex) {}
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.buttonImage != null) {
            graphics.drawImage(this.buttonImage, -((this.isSoundEnabled ? (2 * this.size().width) : 0) + (this.isMouseOver ? 0 : this.size().width)), 0, this);
        }
    }
    
    private void updateStatus() {
        String s;
        if (this.isMouseOver) {
            if (this.isSoundEnabled) {
                s = this.offMessage;
            }
            else {
                s = this.onMessage;
            }
        }
        else {
            s = " ";
        }
        if (s != null) {
            this.showStatus(s);
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.isMouseOver = true;
        this.repaint();
        this.updateStatus();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.isMouseOver = false;
        this.repaint();
        this.updateStatus();
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (n >= 0 && n < this.size().width && n2 >= 0 && n2 < this.size().height) {
            this.isSoundEnabled = !this.isSoundEnabled;
            try {
                ((TickerLine)this.receiver).enableSound(this.isSoundEnabled);
                this.isSoundEnabled = ((TickerLine)this.receiver).isSoundEnabled();
            }
            catch (Exception ex) {}
            this.repaint();
            this.updateStatus();
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
    }
    
    public void run() {
        try {
            final MediaTracker mediaTracker = new MediaTracker(this);
            final Image image = this.getImage(this.getCodeBase(), this.getParameter("ButtonImage"));
            mediaTracker.addImage(image, 1);
            mediaTracker.waitForID(1);
            if (!mediaTracker.isErrorID(1)) {
                this.buttonImage = image;
            }
        }
        catch (Exception ex) {}
        try {
            this.receiver = this.getAppletContext().getApplet(this.getParameter("Receiver"));
        }
        catch (Exception ex2) {}
        this.allLoaded = true;
        this.repaint();
    }
}
