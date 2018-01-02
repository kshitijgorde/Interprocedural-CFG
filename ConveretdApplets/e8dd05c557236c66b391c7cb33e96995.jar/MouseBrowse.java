// 
// Decompiled by Procyon v0.5.30
// 

public class MouseBrowse
{
    int x1;
    int y1;
    int x2;
    int y2;
    int lockBar;
    boolean color;
    
    MouseBrowse(final int x1, final int y1, final int x2, final int y2) {
        this.lockBar = -1;
        this.color = true;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    boolean CheckBrowse(final int n, final int n2) {
        return n >= this.x1 && n <= this.x2 && n2 >= this.y1 && n2 <= this.y2;
    }
}
