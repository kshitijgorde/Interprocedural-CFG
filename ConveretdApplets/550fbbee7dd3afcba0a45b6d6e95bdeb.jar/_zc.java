import java.awt.Event;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class _zc extends Canvas
{
    public PixScreen p;
    public int d;
    public String a;
    public Color n;
    public Color v;
    public Color i;
    private int l;
    
    public final void d(final Graphics graphics) {
        if (this.p == null) {
            return;
        }
        final Color color = new Color(-1);
        graphics.setColor(color);
        graphics.drawString(this.p.p(10), 10, 20);
        graphics.drawString(this.p.p(11), 10, 36);
        graphics.drawString(this.p.p(12), 10, 52);
        graphics.drawString(this.p.p(13), 10, 68);
        graphics.setColor(new Color(-11184811));
        graphics.fillRect(10, 87, 42, 20);
        graphics.setColor(color);
        graphics.drawString(this.p.p(14), 20, 102);
        String string = "";
        String s = null;
        switch (this.p.nf & 0xFFF) {
            case 1000: {
                s = this.p.p(20);
                break;
            }
            case 1001: {
                s = this.p.p(21);
                break;
            }
            case 1002: {
                s = this.p.p(22);
                break;
            }
            case 1003: {
                s = this.p.p(23);
                break;
            }
            case 1004: {
                s = this.p.p(24);
                string = this.p.ng - this.p.az / 86400000L + this.p.p(33);
                break;
            }
            default: {
                s = this.p.p(25);
                break;
            }
        }
        if ((this.p.nf & 0x1000) != 0x0) {
            s = this.p.p(40) + s;
        }
        String s2;
        if (this.p.nc == null) {
            s2 = this.p.p(26);
        }
        else {
            s2 = this.p.p(27) + this.p.nc;
        }
        graphics.drawString(s2, 10, 130);
        graphics.drawString(this.p.p(28) + s + this.p.p(29) + string + this.p.p(30) + this.p.ne, 10, 146);
        graphics.drawString(this.p.p(32), 10, 162);
    }
    
    public _zc(final PixScreen p2, final int d) {
        this.a = "";
        this.n = new Color(6710886);
        this.v = new Color(16777215);
        this.i = new Color(8947848);
        this.l = 4;
        this.p = p2;
        this.d = d;
        if (d == 1) {
            this.setBackground(new Color(68));
        }
    }
    
    public final void paint(final Graphics graphics) {
        switch (this.d) {
            case 1: {
                this.d(graphics);
            }
            case 2: {
                this.p(graphics);
            }
            default: {}
        }
    }
    
    public final int d() {
        return this.getGraphics().getFontMetrics().getHeight() + this.l;
    }
    
    public final int p() {
        return this.getGraphics().getFontMetrics().stringWidth(this.a) + this.l;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        this.p.np.setVisible(false);
        this.p.np.dispose();
        return true;
    }
    
    public final void p(final Graphics graphics) {
        this.resize(this.p(), this.d());
        this.setBackground(this.i);
        graphics.setColor(this.n);
        graphics.drawRect(0, 0, this.p() - 1, this.d() - 1);
        graphics.setColor(this.v);
        graphics.drawString(this.a, this.l / 2, this.d() - this.l / 2 - 2);
    }
}
