import java.applet.Applet;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class DragBitmap extends BitmapSprite
{
    protected boolean draggable;
    
    public DragBitmap(final int x, final int y, final Image i, final Applet a) {
        super(x, y, i, a);
        this.draggable = false;
    }
    
    public void translate(final int x, final int y) {
        super.locx += x;
        super.locy += y;
    }
    
    public void setCenter(final int x, final int y) {
        super.locx = x - super.width / 2;
        super.locy = y - super.height / 2;
    }
    
    public void setDraggable(final boolean b) {
        this.draggable = b;
    }
    
    public boolean isDraggable() {
        return this.draggable;
    }
}
