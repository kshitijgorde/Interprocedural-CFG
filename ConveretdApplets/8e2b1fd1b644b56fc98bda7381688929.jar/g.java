import java.awt.Point;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class g
{
    public static final int LEFT = 0;
    public static final int Zsa = 1;
    public static final int RIGHT = 2;
    public static final int Lra = 3;
    public static final int Mra = 4;
    protected int usa;
    protected int Hsa;
    protected int xsa;
    protected int ysa;
    protected Color T;
    protected Color Asa;
    protected Color Csa;
    protected int vsa;
    protected int wsa;
    protected String text;
    protected Font font;
    protected boolean zsa;
    protected boolean Bsa;
    protected FontMetrics Dsa;
    protected boolean Esa;
    protected boolean Gsa;
    try Fsa;
    
    public g() {
        this.Esa = false;
        this.Gsa = false;
        this.Fsa = new try("\n");
        this.text = "";
        this.Fsa.l(this.text);
        this.xsa = 0;
        this.ysa = 3;
        this.T = Color.black;
        this.Asa = Color.yellow;
        this.Csa = Color.black;
        this.vsa = 0;
        this.wsa = 0;
        this.font = new Font("SansSerif", 0, 10);
        this.zsa = true;
        this.Bsa = true;
        this.usa = 2;
        this.Hsa = 0;
        this.Dsa = null;
    }
    
    public g(String text) {
        this();
        if (text == null) {
            text = "";
        }
        if (!this.Esa) {
            this.text = text.replace('\n', ' ');
        }
        else {
            this.text = text;
        }
        this.Fsa.l(text);
    }
    
    public void J(final boolean esa) {
        this.Esa = esa;
    }
    
    public boolean isEmpty() {
        return this.text == null || this.text.equals("");
    }
    
    public int b(final Graphics graphics) {
        int n = 0;
        try {
            if (this.Dsa == null) {
                this.Dsa = graphics.getFontMetrics(this.font);
            }
        }
        catch (NullPointerException ex) {}
        try {
            if (this.Esa) {
                for (int i = 0; i < this.Fsa.g(); ++i) {
                    final String a = this.Fsa.a(i);
                    if (a != null) {
                        n = Math.max(n, this.Dsa.stringWidth(a));
                    }
                }
            }
            else {
                n = this.Dsa.stringWidth(this.text);
            }
        }
        catch (NullPointerException ex2) {}
        n += 2;
        if (this.zsa || this.Bsa) {
            n += 2 * this.usa;
        }
        return n;
    }
    
    public int _(final Graphics graphics) {
        try {
            if (this.Dsa == null) {
                this.Dsa = graphics.getFontMetrics(this.font);
            }
        }
        catch (NullPointerException ex) {}
        int n = 0;
        try {
            if (this.Esa) {
                n = this.Dsa.getDescent() + this.Dsa.getAscent();
                if (this.Fsa.g() > 0) {
                    n += (this.Fsa.g() - 1) * this.Dsa.getHeight();
                }
            }
            else {
                n = this.Dsa.getDescent() + this.Dsa.getAscent();
            }
        }
        catch (NullPointerException ex2) {}
        if (this.zsa || this.Bsa) {
            n += 2 * this.Hsa;
        }
        return n;
    }
    
    public void a(final Graphics graphics) {
        int vsa = this.vsa;
        int wsa = this.wsa;
        final int _ = this._(graphics);
        final int b = this.b(graphics);
        switch (this.xsa) {
            case 2: {
                vsa -= b;
                break;
            }
            case 1: {
                vsa -= b / 2;
                break;
            }
        }
        switch (this.ysa) {
            case 1: {
                wsa -= _ / 2;
                break;
            }
            case 4: {
                wsa -= _;
                break;
            }
        }
        if (this.zsa) {
            graphics.setColor(this.Asa);
            graphics.fillRect(vsa, wsa, b + 1, _ + 1);
        }
        if (this.Bsa) {
            graphics.setColor(this.Csa);
            graphics.drawRect(vsa, wsa, b, _);
        }
        graphics.setColor(this.T);
        if (this.Dsa == null) {
            this.Dsa = graphics.getFontMetrics(this.font);
        }
        final Font font = graphics.getFont();
        graphics.setFont(this.font);
        if (this.Esa) {
            for (int i = 0; i < this.Fsa.g(); ++i) {
                final int n = i * (this.Dsa.getAscent() + this.Dsa.getDescent());
                final int n2 = b;
                int n3;
                if (this.Bsa || this.zsa) {
                    n3 = n2 - (this.Dsa.stringWidth(this.Fsa.a(i)) + 2 * this.usa);
                }
                else {
                    n3 = n2 - this.Dsa.stringWidth(this.Fsa.a(i));
                }
                int n4 = n3 / 2;
                if (n4 < 0) {
                    n4 = 0;
                }
                if (this.Gsa) {
                    n4 = 0;
                }
                if (this.Bsa || this.zsa) {
                    graphics.drawString(this.Fsa.a(i), n4 + vsa + this.usa + 2, n + wsa + this.Hsa + this.Dsa.getAscent());
                }
                else {
                    graphics.drawString(this.Fsa.a(i), n4 + vsa + 2, n + wsa + this.Dsa.getAscent());
                }
            }
        }
        else if (this.Bsa || this.zsa) {
            graphics.drawString(this.text, vsa + this.usa + 2, wsa + this.Hsa + this.Dsa.getAscent());
        }
        else {
            graphics.drawString(this.text, vsa + 2, wsa + this.Dsa.getAscent());
        }
        graphics.setFont(font);
    }
    
    public void _(final Graphics graphics, final String text) {
        if (!this.Esa) {
            this.text = text.replace('\n', ' ');
        }
        else {
            this.text = text;
        }
        this.Fsa.l(text);
        this.a(graphics);
    }
    
    public void b(final Graphics graphics, final String text, final int vsa, final int wsa) {
        if (!this.Esa) {
            this.text = text.replace('\n', ' ');
        }
        else {
            this.text = text;
        }
        this.Fsa.l(text);
        this.vsa = vsa;
        this.wsa = wsa;
        this.a(graphics);
    }
    
    public void a(final Graphics graphics, final int vsa, final int wsa) {
        this.vsa = vsa;
        this.wsa = wsa;
        this.a(graphics);
    }
    
    public void K(final int xsa) {
        this.xsa = xsa;
    }
    
    public void L(final int ysa) {
        this.ysa = ysa;
    }
    
    public void e(final Color csa) {
        this.Csa = csa;
    }
    
    public void setColor(final Color t) {
        this.T = t;
    }
    
    public void a(final Color asa) {
        this.Asa = asa;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(String text) {
        if (text == null) {
            text = "";
        }
        if (!this.Esa) {
            this.text = text.replace('\n', ' ');
        }
        else {
            this.text = text;
        }
        this.Fsa.l(text);
        this.Dsa = null;
    }
    
    public void n(final int vsa, final int wsa) {
        this.vsa = vsa;
        this.wsa = wsa;
    }
    
    public void b(final Point point) {
        this.vsa = point.x;
        this.wsa = point.y;
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public void setFont(final Font font) {
        this.font = font;
    }
    
    public void f(final boolean bsa) {
        this.Bsa = bsa;
    }
    
    public void e(final boolean zsa) {
        this.zsa = zsa;
    }
    
    public boolean i() {
        return this.Bsa;
    }
    
    public boolean M() {
        return this.zsa;
    }
    
    public void K(final boolean gsa) {
        this.Gsa = gsa;
    }
}
