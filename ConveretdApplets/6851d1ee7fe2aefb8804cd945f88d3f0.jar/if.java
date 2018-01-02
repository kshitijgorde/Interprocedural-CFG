import java.awt.Font;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class if extends goto
{
    private int ima;
    private int eka;
    
    public if() {
        this.ima = 10;
        this.eka = -1;
    }
    
    public if(final String s) {
        super(s);
        this.ima = 10;
        this.eka = -1;
    }
    
    public int b(final Graphics graphics) {
        int b = super.b(graphics);
        if (this.eka >= 0) {
            b += super.jma + 2 + this.ima;
        }
        return b;
    }
    
    public void a(final Graphics graphics) {
        int kma = super.kma;
        int lma = super.lma;
        final int _ = this._(graphics);
        final int b = this.b(graphics);
        switch (super.mma) {
            case 2: {
                kma -= b;
                break;
            }
            case 1: {
                kma -= b / 2;
                break;
            }
        }
        switch (super.nma) {
            case 1: {
                lma -= _ / 2;
                break;
            }
            case 4: {
                lma -= _;
                break;
            }
        }
        if (super.oma) {
            graphics.setColor(super.pma);
            graphics.fillRect(kma, lma, b, _);
        }
        if (super.qma) {
            graphics.setColor(super.rma);
            graphics.drawRect(kma, lma, b, _);
        }
        graphics.setColor(super.xa);
        if (this.eka >= 0) {
            if (this.eka == 0) {
                graphics.drawLine(kma + super.jma + 1, lma + _ / 2, kma + super.jma + 1 + this.ima, lma + _ / 2);
            }
            else {
                graphics.fillRect(kma + super.jma + 1, lma + Math.round((_ - this.eka) / 2.0f), this.ima + 1, this.eka + 1);
            }
            kma += this.ima + super.jma + 2;
        }
        if (super.sma == null) {
            super.sma = graphics.getFontMetrics(super.font);
        }
        final Font font = graphics.getFont();
        graphics.setFont(super.font);
        if (super.tma) {
            for (int i = 0; i < super.uma.a(); ++i) {
                final int n = i * (super.sma.getMaxAscent() + super.sma.getMaxDescent());
                final int n2 = b;
                int n3;
                if (super.qma || super.oma) {
                    n3 = n2 - (super.sma.stringWidth(super.uma.b(i)) + 2 * super.jma);
                }
                else {
                    n3 = n2 - super.sma.stringWidth(super.uma.b(i));
                }
                int n4 = n3 / 2;
                if (n4 < 0) {
                    n4 = 0;
                }
                if (super.vma) {
                    n4 = 0;
                }
                if (super.qma || super.oma) {
                    graphics.drawString(super.uma.b(i), n4 + kma + super.jma + 2, n + lma + super.wma + super.sma.getMaxAscent());
                }
                else {
                    graphics.drawString(super.uma.b(i), n4 + kma + 2, n + lma + super.sma.getMaxAscent());
                }
            }
        }
        else if (super.qma || super.oma) {
            graphics.drawString(super.text, kma + super.jma + 2, lma + super.wma + super.sma.getMaxAscent());
        }
        else {
            graphics.drawString(super.text, kma + 2, lma + super.sma.getMaxAscent());
        }
        graphics.setFont(font);
    }
    
    public void _(final Graphics graphics, final String text, final int eka, final int kma, final int lma) {
        this.eka = eka;
        if (!super.tma) {
            super.text = text.replace('\n', ' ');
        }
        else {
            super.text = text;
        }
        super.uma.m(text);
        super.kma = kma;
        super.lma = lma;
        this.a(graphics);
    }
    
    public void h(final int eka) {
        this.eka = eka;
    }
}
