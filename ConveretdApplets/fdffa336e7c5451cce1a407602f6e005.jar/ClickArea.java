import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class ClickArea extends ImageMapArea
{
    int startx;
    int starty;
    
    public void handleArg(final String s) {
        super.terminal = false;
    }
    
    public void highlight(final Graphics graphics, final boolean b) {
    }
    
    String ptstr(final int n, final int n2) {
        return "(" + n + ", " + n2 + ")";
    }
    
    public void press(final int startx, final int starty) {
        this.showStatus("Clicked at " + this.ptstr(startx, starty));
        this.startx = startx;
        this.starty = starty;
    }
    
    public void drag(final int n, final int n2) {
        this.showStatus("Rectangle from " + this.ptstr(this.startx, this.starty) + " to " + this.ptstr(n, n2) + " is " + (n - this.startx) + "x" + (n2 - this.starty));
    }
    
    public void lift(final int n, final int n2) {
        this.drag(n, n2);
    }
}
