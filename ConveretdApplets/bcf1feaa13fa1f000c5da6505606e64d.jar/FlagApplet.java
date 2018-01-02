import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class FlagApplet extends Applet implements Runnable, MouseListener
{
    private AppletAnimation \u00c8;
    private Image \u00c3;
    private Thread \u00c6;
    private int \u00c5;
    private int \u00c9;
    private int \u00ca;
    private int \u00cb;
    private boolean \u00cc;
    private String \u00cd;
    private String \u00ce;
    
    public String getAppletInfo() {
        return "Flag Animator Applet. version " + this.\u00cd + ". Copyright © 1999 Dan MacFarlane.";
    }
    
    public void init() {
        boolean b = true;
        final String parameter = this.getParameter("copyright");
        if (parameter == null) {
            b = false;
        }
        else if (!parameter.equalsIgnoreCase("(c) 1999 Dan MacFarlane")) {
            b = false;
        }
        final String parameter2 = this.getParameter("home");
        if (parameter2 == null) {
            b = false;
        }
        else if (!parameter2.equalsIgnoreCase("http://www.dancity.com/")) {
            b = false;
        }
        if (b) {
            this.showStatus("Loading image...");
            URL url;
            try {
                url = new URL(this.getParameter("image"));
            }
            catch (MalformedURLException ex2) {
                url = null;
            }
            if (url == null) {
                try {
                    url = new URL(this.getDocumentBase(), this.getParameter("image"));
                }
                catch (MalformedURLException ex3) {
                    url = null;
                }
            }
            this.\u00c3 = this.getImage(url);
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(this.\u00c3, 0);
            try {
                mediaTracker.waitForID(0);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            Color background = Color.black;
            if (this.getParameter("background") != null) {
                background = Color.decode(this.getParameter("background"));
            }
            this.setBackground(background);
            this.repaint();
            this.\u00c6.start();
            this.showStatus("Generating animation frames...");
            if (this.getParameter("frames") != null) {
                final int intValue = new Integer(this.getParameter("frames"));
                if (intValue > 0) {
                    this.\u00c9 = intValue;
                }
            }
            this.\u00c8.generateFrames(this.\u00c3, this.\u00c9, background);
            this.\u00ca = this.getSize().width / 2 - this.\u00c8.width / 2;
            this.\u00cb = this.getSize().height / 2 - this.\u00c8.height / 2;
            this.\u00ce = this.getParameter("href");
            this.addMouseListener(this);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.\u00cc || this.\u00c8.status.equals("100%")) {
            this.repaint();
            return;
        }
        final Dimension size = this.getSize();
        if (size.width >= 150) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, size.height / 2 - 35, size.width, 60);
            graphics.setColor(Color.white);
            final String string = "FlagApplet " + this.\u00cd;
            graphics.drawString(string, size.width / 2 - graphics.getFontMetrics().stringWidth(string) / 2, size.height / 2 - 20);
            final String s = "www.dancity.com";
            graphics.drawString(s, size.width / 2 - graphics.getFontMetrics().stringWidth(s) / 2, size.height / 2 + 20);
            final String string2 = "Generating Animation..." + this.\u00c8.status;
            graphics.drawString(string2, size.width / 2 - graphics.getFontMetrics().stringWidth(string2) / 2, size.height / 2);
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.\u00cc) {
            graphics.drawImage(this.\u00c8.getFrame(this.\u00c5), this.\u00ca, this.\u00cb, this);
            return;
        }
        final Dimension size = this.getSize();
        if (size.width >= 150) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, size.height / 2 - 15, size.width, 20);
            graphics.setColor(Color.white);
            final String string = "Generating Animation..." + this.\u00c8.status;
            graphics.drawString(string, size.width / 2 - graphics.getFontMetrics().stringWidth(string) / 2, size.height / 2);
        }
        if (this.\u00c8.status.equals("100%")) {
            this.\u00cc = true;
            graphics.setColor(this.\u00c8.background);
            graphics.fillRect(0, 0, size.width, size.height);
        }
    }
    
    public void run() {
        this.\u00c5 = 0;
        try {
            while (true) {
                ++this.\u00c5;
                if (this.\u00c5 >= this.\u00c9) {
                    this.\u00c5 = 0;
                }
                this.repaint();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
        }
        catch (Exception ex2) {
            System.err.println("Error occured during animation.");
        }
    }
    
    public void start() {
        if (this.\u00c6 == null) {
            (this.\u00c6 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        try {
            if (this.\u00c6 != null) {
                this.\u00c6.stop();
            }
        }
        catch (Exception ex) {}
        this.\u00c6 = null;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) == 0x10 && this.\u00ce != null) {
            try {
                URL url;
                if (this.\u00ce.startsWith("http://") || this.\u00ce.startsWith("mailto:")) {
                    url = new URL(this.\u00ce);
                }
                else {
                    url = new URL(String.valueOf(this.getDocumentBase()) + "/../" + this.\u00ce);
                }
                final String parameter = this.getParameter("target");
                if (parameter != null) {
                    this.getAppletContext().showDocument(url, parameter);
                    return;
                }
                this.getAppletContext().showDocument(url);
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.\u00ce != null) {
            this.setCursor(Cursor.getPredefinedCursor(12));
            this.showStatus(this.\u00ce);
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.\u00ce != null) {
            this.setCursor(Cursor.getPredefinedCursor(0));
            this.showStatus("");
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public FlagApplet() {
        this.\u00c8 = new AppletAnimation();
        this.\u00c6 = new Thread(this);
        this.\u00c9 = 8;
        this.\u00cc = false;
        this.\u00cd = "v1.04";
    }
}
