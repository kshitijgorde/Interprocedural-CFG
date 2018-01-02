import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Line
{
    public KeyPoynt p1;
    public KeyPoynt p2;
    public Color color;
    public boolean extend;
    public int style;
    
    public Line(final KeyPoynt p2, final KeyPoynt p3) {
        this.color = Color.black;
        this.extend = false;
        this.style = 0;
        this.p1 = p2;
        this.p2 = p3;
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public void setStyle(final int style) {
        this.style = style;
    }
    
    public void setStart(final KeyPoynt p) {
        this.p1 = p;
    }
    
    public void setEnd(final KeyPoynt p) {
        this.p2 = p;
    }
    
    public void moveStartTo(final double n, final double n2) {
        this.p1.moveTo(n, n2);
    }
    
    public void moveEndTo(final double n, final double n2) {
        this.p2.moveTo(n, n2);
    }
}
