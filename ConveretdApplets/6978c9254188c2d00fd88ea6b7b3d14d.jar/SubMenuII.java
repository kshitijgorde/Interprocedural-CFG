import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class SubMenuII
{
    Component parent;
    int w;
    int h;
    int x;
    int y;
    public static final int nfStub = 0;
    public static final int nfSub = 1;
    private int ncount;
    private int nind;
    
    public SubMenuII(final Component parent, final int x, final int y, final int w, final int h, final int ncount) {
        this.ncount = 2;
        this.nind = 0;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.ncount = ncount;
        this.parent = parent;
    }
    
    public void SetType(final int nind) {
        this.nind = nind;
    }
    
    public synchronized void paint(final Graphics graphics) {
        final Color background = this.parent.getBackground();
        final Color darker = background.darker();
        final Color brighter = background.brighter();
        Color color = null;
        Color color2 = null;
        switch (this.nind) {
            case 0: {
                color = darker;
                color2 = brighter;
                break;
            }
            default: {
                color = brighter;
                color2 = darker;
                break;
            }
        }
        graphics.setColor(color);
        for (int i = 0; i < this.ncount; ++i) {
            graphics.drawLine(this.x + i, this.y + i, this.x + this.w - i - 1, this.y + i);
            graphics.drawLine(this.x + i, this.y + i, this.x + i, this.y + this.h - 1);
        }
        graphics.setColor(color2);
        for (int j = 0; j < this.ncount; ++j) {
            graphics.drawLine(this.x + j, this.y + this.h - j - 1, this.x + this.w - j, this.y + this.h - j - 1);
            graphics.drawLine(this.x + this.w - j - 1, this.y + j, this.x + this.w - j - 1, this.y + this.h - j);
        }
    }
}
