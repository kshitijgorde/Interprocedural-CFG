import java.awt.Font;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class j extends g
{
    private int tsa;
    private int tra;
    
    public j() {
        this.tsa = 10;
        this.tra = -1;
    }
    
    public j(final String s) {
        super(s);
        this.tsa = 10;
        this.tra = -1;
    }
    
    public int b(final Graphics graphics) {
        int b = super.b(graphics);
        if (this.tra >= 0) {
            b += super.usa + 2 + this.tsa;
        }
        return b;
    }
    
    public void a(final Graphics graphics) {
        int vsa = super.vsa;
        int wsa = super.wsa;
        final int _ = this._(graphics);
        final int b = this.b(graphics);
        switch (super.xsa) {
            case 2: {
                vsa -= b;
                break;
            }
            case 1: {
                vsa -= b / 2;
                break;
            }
        }
        switch (super.ysa) {
            case 1: {
                wsa -= _ / 2;
                break;
            }
            case 4: {
                wsa -= _;
                break;
            }
        }
        if (super.zsa) {
            graphics.setColor(super.Asa);
            graphics.fillRect(vsa, wsa, b, _);
        }
        if (super.Bsa) {
            graphics.setColor(super.Csa);
            graphics.drawRect(vsa, wsa, b, _);
        }
        graphics.setColor(super.T);
        if (this.tra >= 0) {
            if (this.tra == 0) {
                graphics.drawLine(vsa + super.usa + 1, wsa + _ / 2, vsa + super.usa + 1 + this.tsa, wsa + _ / 2);
            }
            else {
                graphics.fillRect(vsa + super.usa + 1, wsa + Math.round((_ - this.tra) / 2.0f), this.tsa + 1, this.tra + 1);
            }
            vsa += this.tsa + super.usa + 2;
        }
        if (super.Dsa == null) {
            super.Dsa = graphics.getFontMetrics(super.font);
        }
        final Font font = graphics.getFont();
        graphics.setFont(super.font);
        if (super.Esa) {
            for (int i = 0; i < super.Fsa.g(); ++i) {
                final int n = i * (super.Dsa.getMaxAscent() + super.Dsa.getMaxDescent());
                final int n2 = b;
                int n3;
                if (super.Bsa || super.zsa) {
                    n3 = n2 - (super.Dsa.stringWidth(super.Fsa.a(i)) + 2 * super.usa);
                }
                else {
                    n3 = n2 - super.Dsa.stringWidth(super.Fsa.a(i));
                }
                int n4 = n3 / 2;
                if (n4 < 0) {
                    n4 = 0;
                }
                if (super.Gsa) {
                    n4 = 0;
                }
                if (super.Bsa || super.zsa) {
                    graphics.drawString(super.Fsa.a(i), n4 + vsa + super.usa + 2, n + wsa + super.Hsa + super.Dsa.getMaxAscent());
                }
                else {
                    graphics.drawString(super.Fsa.a(i), n4 + vsa + 2, n + wsa + super.Dsa.getMaxAscent());
                }
            }
        }
        else if (super.Bsa || super.zsa) {
            graphics.drawString(super.text, vsa + super.usa + 2, wsa + super.Hsa + super.Dsa.getMaxAscent());
        }
        else {
            graphics.drawString(super.text, vsa + 2, wsa + super.Dsa.getMaxAscent());
        }
        graphics.setFont(font);
    }
    
    public void a(final Graphics graphics, final String text, final int tra, final int vsa, final int wsa) {
        this.tra = tra;
        if (!super.Esa) {
            super.text = text.replace('\n', ' ');
        }
        else {
            super.text = text;
        }
        super.Fsa.l(text);
        super.vsa = vsa;
        super.wsa = wsa;
        this.a(graphics);
    }
    
    public void I(final int tra) {
        this.tra = tra;
    }
}
