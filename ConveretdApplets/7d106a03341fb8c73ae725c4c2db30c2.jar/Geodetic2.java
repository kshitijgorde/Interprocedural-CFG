import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Font;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Geodetic2 extends Applet
{
    geoWindow geoWin;
    Button button;
    Image image;
    MediaTracker tracker;
    int width;
    int height;
    
    public void init() {
        URL url;
        try {
            url = new URL(this.getCodeBase(), "geodetic2.gif");
        }
        catch (MalformedURLException ex) {
            url = null;
            this.image = null;
        }
        if (url != null) {
            this.image = this.getImage(url);
            if (this.image != null) {
                this.tracker.addImage(this.image, 0);
            }
        }
        this.setFont(new Font("Helvetica", 1, 12));
        this.add(this.button = new Button("How far away is..."));
        this.width = this.size().width;
        this.height = this.size().height;
        this.button.move((this.width - this.button.size().width) / 2, (this.width - this.button.size().width) / 2);
        this.geoWin = new geoWindow(this);
    }
    
    public void paint(final Graphics graphics) {
        final Color color = graphics.getColor();
        graphics.setColor(Color.lightGray);
        graphics.fill3DRect(0, 0, this.size().width, this.size().height, true);
        graphics.setColor(color);
        if (this.image != null) {
            try {
                this.tracker.waitForID(0);
            }
            catch (InterruptedException ex) {
                return;
            }
            graphics.drawImage(this.image, (this.width - this.image.getWidth(this)) / 2, this.button.size().height + (this.height - this.image.getHeight(this) - this.button.size().height) / 3, this);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.drawString("(C) 1996 by W.Giel", (this.width - fontMetrics.stringWidth("(C) 1996 by W.Giel")) / 2, this.height - fontMetrics.getMaxDescent() - 3);
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (o.equals("How far away is...") && this.geoWin != null) {
            if (!this.geoWin.isShowing()) {
                this.geoWin.show();
            }
            return true;
        }
        return false;
    }
    
    public Geodetic2() {
        this.tracker = new MediaTracker(this);
    }
}
