import java.awt.Event;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class _zc extends Canvas
{
    public _zo p;
    public _zf d;
    public PixScreen a;
    public int n;
    public String v;
    public Color i;
    public Color l;
    public Color b;
    private int c;
    
    public final void d(final Graphics graphics) {
        if (this.a == null) {
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
        String s = "";
        String string = "";
        switch (this.d.p & 0xFFF) {
            default: {
                s = this.p.p(25);
            }
            case 1011: {
                s = this.p.p(24);
                string = this.d.d - this.d.p / 86400000L + this.p.p(33);
            }
            case 1010:
            case 1973:
            case 1974: {
                if ((this.d.p & 0x1000) == 0x0 || (this.d.p & 0xFFF) != 0x7B6) {}
                String s2;
                if (this.d.d == null) {
                    s2 = this.p.p(26);
                }
                else {
                    s2 = this.p.p(27) + this.d.d;
                }
                graphics.drawString(s2, 10, 130);
                if ((this.d.p & 0xFFF) == 0x7B5 || (this.d.p & 0xFFF) == 0x3F2) {
                    graphics.drawString(s + this.p.p(35) + this.d.a, 10, 146);
                }
                else if ((this.d.p & 0xFFF) == 0x7B6) {
                    graphics.drawString(s + this.p.p(36) + this.d.a, 10, 146);
                }
                else {
                    graphics.drawString(this.p.p(28) + s + this.p.p(29) + string + this.p.p(30) + this.d.a, 10, 146);
                }
                graphics.drawString(this.p.p(32), 10, 162);
            }
        }
    }
    
    public _zc(final PixScreen a, final _zo p4, final _zf d, final int n) {
        this.v = "";
        this.i = new Color(6710886);
        this.l = new Color(16777215);
        this.b = new Color(8947848);
        this.c = 4;
        this.a = a;
        this.p = p4;
        this.d = d;
        this.n = n;
        if (n == 1) {
            this.setBackground(new Color(68));
        }
    }
    
    public final void paint(final Graphics graphics) {
        switch (this.n) {
            case 1: {
                this.d(graphics);
            }
            case 2: {
                this.p(graphics);
            }
            default: {}
        }
    }
    
    public final int p() {
        return this.getGraphics().getFontMetrics().getHeight() + this.c;
    }
    
    public final int d() {
        return this.getGraphics().getFontMetrics().stringWidth(this.v) + this.c;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        this.a.vv.setVisible(false);
        this.a.vv.dispose();
        return true;
    }
    
    public final void p(final Graphics graphics) {
        this.resize(this.d(), this.p());
        this.setBackground(this.b);
        graphics.setColor(this.i);
        graphics.drawRect(0, 0, this.d() - 1, this.p() - 1);
        graphics.setColor(this.l);
        graphics.drawString(this.v, this.c / 2, this.p() - this.c / 2 - 2);
    }
}
