import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class advert_area extends Canvas
{
    private int cur_index;
    private groupboard parent;
    private String advert_url;
    private Image advert_image;
    private Vector adverts;
    private advert cur_advert;
    
    advert_area(final groupboard parent) {
        this.cur_index = 0;
        this.parent = parent;
        this.adverts = new Vector();
    }
    
    public void add_advert(final advert cur_advert) {
        this.adverts.addElement(cur_advert);
        if (null == this.cur_advert) {
            this.cur_advert = cur_advert;
        }
    }
    
    public void cycle_advert() {
        if (null != this.cur_advert) {
            if (this.cur_index < this.adverts.size() - 1) {
                ++this.cur_index;
            }
            else {
                this.cur_index = 0;
            }
            this.cur_advert = this.adverts.elementAt(this.cur_index);
            this.repaint();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (null != this.cur_advert && null != this.cur_advert.advert_image) {
            graphics.drawImage(this.cur_advert.advert_image, 0, 0, this);
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 504: {
                if (this.parent.new_jdk) {
                    this.setCursor(new Cursor(12));
                }
                return true;
            }
            case 505: {
                if (this.parent.new_jdk) {
                    this.setCursor(Cursor.getDefaultCursor());
                }
                return true;
            }
            case 501: {
                if (null != this.cur_advert) {
                    try {
                        this.parent.getAppletContext().showDocument(new URL(this.cur_advert.advert_url), "_blank");
                    }
                    catch (MalformedURLException ex) {
                        System.out.println("Malformed url " + this.cur_advert.advert_url);
                    }
                }
                return true;
            }
            default: {
                return false;
            }
        }
    }
}
