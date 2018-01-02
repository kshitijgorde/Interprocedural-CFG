import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class KeyPoynt
{
    public double xcoord;
    public double ycoord;
    public Color color;
    public int style;
    public Function wire;
    
    public KeyPoynt(final double xcoord, final double ycoord) {
        this.xcoord = 0.0;
        this.ycoord = 0.0;
        this.color = Color.white;
        this.style = 0;
        this.wire = null;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
    }
    
    public KeyPoynt(final double xcoord, final double ycoord, final Color color) {
        this.xcoord = 0.0;
        this.ycoord = 0.0;
        this.color = Color.white;
        this.style = 0;
        this.wire = null;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.color = color;
    }
    
    public KeyPoynt(final double xcoord, final double ycoord, final Color color, final int style) {
        this.xcoord = 0.0;
        this.ycoord = 0.0;
        this.color = Color.white;
        this.style = 0;
        this.wire = null;
        this.xcoord = xcoord;
        this.ycoord = ycoord;
        this.color = color;
        this.style = style;
    }
    
    public void moveTo(final double xcoord, final double ycoord) {
        this.xcoord = xcoord;
        if (this.wire == null) {
            this.ycoord = ycoord;
        }
        else {
            this.ycoord = this.wire.giveyfor(xcoord);
        }
    }
    
    public void fixTo(final Function wire) {
        this.wire = wire;
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public void setStyle(final int style) {
        this.style = style;
    }
}
