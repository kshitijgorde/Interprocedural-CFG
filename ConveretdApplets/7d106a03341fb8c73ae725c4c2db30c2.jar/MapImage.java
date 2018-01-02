import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dialog;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class MapImage extends Canvas
{
    Image i;
    Image stickpinImage;
    int w;
    int h;
    boolean stickpin;
    int pinX;
    int pinY;
    Dialog dialog;
    MapProjection mp;
    
    public MapImage(final Dialog dialog, final Image[] array, final int w, final int h, final MapProjection mp) {
        this.stickpin = false;
        this.i = array[0];
        this.stickpinImage = array[1];
        this.w = w;
        this.h = h;
        this.resize(this.w, this.h);
        this.dialog = dialog;
        this.mp = mp;
    }
    
    void doPin(final Point point) {
        this.stickpin = true;
        this.pinX = point.x;
        this.pinY = point.y;
    }
    
    public void paint(final Graphics graphics) {
        if (this.i != null) {
            graphics.drawImage(this.i, 0, 0, this);
        }
        if (this.stickpin && this.stickpinImage != null) {
            graphics.drawImage(this.stickpinImage, this.pinX, this.pinY, this);
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.postEvent(new Event(this.getParent(), 0, new Point(n, n2)));
        return true;
    }
}
