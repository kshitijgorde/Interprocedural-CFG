import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class nInput
{
    int mx;
    int my;
    int lmx;
    int lmy;
    boolean mDown;
    
    void setMouseCoord(final int mx, final int my) {
        this.lmx = this.mx;
        this.lmy = this.my;
        this.mx = mx;
        this.my = my;
    }
    
    void setmDown(final boolean mDown) {
        this.mDown = mDown;
    }
    
    Point getMouseCoord() {
        return new Point(this.mx, this.my);
    }
    
    boolean getmDown() {
        return this.mDown;
    }
}
