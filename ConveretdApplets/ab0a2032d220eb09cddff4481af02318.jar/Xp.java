import java.awt.Font;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class Xp extends Tp
{
    private int ca;
    private int c;
    
    public Xp() {
        this.ca = 10;
        this.c = -1;
    }
    
    public Xp(final String s) {
        super(s);
        this.ca = 10;
        this.c = -1;
    }
    
    public int _(final Graphics graphics) {
        int _ = super._(graphics);
        if (this.c >= 0) {
            _ += super.da + 2 + this.ca;
        }
        return _;
    }
    
    public void b(final Graphics graphics) {
        int ea = super.ea;
        int fa = super.fa;
        final int a = this.a(graphics);
        final int _ = this._(graphics);
        switch (super.ga) {
            case 2: {
                ea -= _;
                break;
            }
            case 1: {
                ea -= _ / 2;
                break;
            }
        }
        switch (super.ha) {
            case 1: {
                fa -= a / 2;
                break;
            }
            case 4: {
                fa -= a;
                break;
            }
        }
        if (super.ia) {
            graphics.setColor(super.ja);
            graphics.fillRect(ea, fa, _, a);
        }
        if (super.ka) {
            graphics.setColor(super.la);
            graphics.drawRect(ea, fa, _, a);
        }
        graphics.setColor(super.Ba);
        if (this.c >= 0) {
            if (this.c == 0) {
                graphics.drawLine(ea + super.da + 1, fa + a / 2, ea + super.da + 1 + this.ca, fa + a / 2);
            }
            else {
                graphics.fillRect(ea + super.da + 1, fa + Math.round((a - this.c) / 2.0f), this.ca + 1, this.c + 1);
            }
            ea += this.ca + super.da + 2;
        }
        if (super.ma == null) {
            super.ma = graphics.getFontMetrics(super.font);
        }
        final Font font = graphics.getFont();
        graphics.setFont(super.font);
        if (super.na) {
            for (int i = 0; i < super.oa.z(); ++i) {
                final int n = i * (super.ma.getMaxAscent() + super.ma.getMaxDescent());
                final int n2 = _;
                int n3;
                if (super.ka || super.ia) {
                    n3 = n2 - (super.ma.stringWidth(super.oa._(i)) + 2 * super.da);
                }
                else {
                    n3 = n2 - super.ma.stringWidth(super.oa._(i));
                }
                int n4 = n3 / 2;
                if (n4 < 0) {
                    n4 = 0;
                }
                if (super.pa) {
                    n4 = 0;
                }
                if (super.ka || super.ia) {
                    graphics.drawString(super.oa._(i), n4 + ea + super.da + 2, n + fa + super.qa + super.ma.getMaxAscent());
                }
                else {
                    graphics.drawString(super.oa._(i), n4 + ea + 2, n + fa + super.ma.getMaxAscent());
                }
            }
        }
        else if (super.ka || super.ia) {
            graphics.drawString(super.text, ea + super.da + 2, fa + super.qa + super.ma.getMaxAscent());
        }
        else {
            graphics.drawString(super.text, ea + 2, fa + super.ma.getMaxAscent());
        }
        graphics.setFont(font);
    }
    
    public void _(final Graphics graphics, final String text, final int c, final int ea, final int fa) {
        this.c = c;
        if (!super.na) {
            super.text = text.replace('\n', ' ');
        }
        else {
            super.text = text;
        }
        super.oa.e(text);
        super.ea = ea;
        super.fa = fa;
        this.b(graphics);
    }
    
    public void k(final int c) {
        this.c = c;
    }
}
