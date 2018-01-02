import java.awt.Point;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class goto
{
    public static final int LEFT = 0;
    public static final int xma = 1;
    public static final int RIGHT = 2;
    public static final int yma = 3;
    public static final int zma = 4;
    protected int jma;
    protected int wma;
    protected int mma;
    protected int nma;
    protected Color xa;
    protected Color pma;
    protected Color rma;
    protected int kma;
    protected int lma;
    protected String text;
    protected Font font;
    protected boolean oma;
    protected boolean qma;
    protected FontMetrics sma;
    protected boolean tma;
    protected boolean vma;
    u uma;
    
    public goto() {
        this.tma = false;
        this.vma = false;
        this.uma = new u("\n");
        this.text = "";
        this.uma.m(this.text);
        this.mma = 0;
        this.nma = 3;
        this.xa = Color.black;
        this.pma = Color.yellow;
        this.rma = Color.black;
        this.kma = 0;
        this.lma = 0;
        this.font = new Font("SansSerif", 0, 10);
        this.oma = true;
        this.qma = true;
        this.jma = 2;
        this.wma = 0;
        this.sma = null;
    }
    
    public goto(String text) {
        this();
        if (text == null) {
            text = "";
        }
        if (!this.tma) {
            this.text = text.replace('\n', ' ');
        }
        else {
            this.text = text;
        }
        this.uma.m(text);
    }
    
    public void k(final boolean tma) {
        this.tma = tma;
    }
    
    public boolean isEmpty() {
        return this.text == null || this.text.equals("");
    }
    
    public int b(final Graphics graphics) {
        int n = 0;
        try {
            if (this.sma == null) {
                this.sma = graphics.getFontMetrics(this.font);
            }
        }
        catch (NullPointerException ex) {}
        try {
            if (this.tma) {
                for (int i = 0; i < this.uma.a(); ++i) {
                    final String b = this.uma.b(i);
                    if (b != null) {
                        n = Math.max(n, this.sma.stringWidth(b));
                    }
                }
            }
            else {
                n = this.sma.stringWidth(this.text);
            }
        }
        catch (NullPointerException ex2) {}
        n += 2;
        if (this.oma || this.qma) {
            n += 2 * this.jma;
        }
        return n;
    }
    
    public int _(final Graphics graphics) {
        try {
            if (this.sma == null) {
                this.sma = graphics.getFontMetrics(this.font);
            }
        }
        catch (NullPointerException ex) {}
        int n = 0;
        try {
            if (this.tma) {
                n = this.sma.getDescent() + this.sma.getAscent();
                if (this.uma.a() > 0) {
                    n += (this.uma.a() - 1) * this.sma.getHeight();
                }
            }
            else {
                n = this.sma.getDescent() + this.sma.getAscent();
            }
        }
        catch (NullPointerException ex2) {}
        if (this.oma || this.qma) {
            n += 2 * this.wma;
        }
        return n;
    }
    
    public void a(final Graphics graphics) {
        int kma = this.kma;
        int lma = this.lma;
        final int _ = this._(graphics);
        final int b = this.b(graphics);
        switch (this.mma) {
            case 2: {
                kma -= b;
                break;
            }
            case 1: {
                kma -= b / 2;
                break;
            }
        }
        switch (this.nma) {
            case 1: {
                lma -= _ / 2;
                break;
            }
            case 4: {
                lma -= _;
                break;
            }
        }
        if (this.oma) {
            graphics.setColor(this.pma);
            graphics.fillRect(kma, lma, b + 1, _ + 1);
        }
        if (this.qma) {
            graphics.setColor(this.rma);
            graphics.drawRect(kma, lma, b, _);
        }
        graphics.setColor(this.xa);
        if (this.sma == null) {
            this.sma = graphics.getFontMetrics(this.font);
        }
        final Font font = graphics.getFont();
        graphics.setFont(this.font);
        if (this.tma) {
            for (int i = 0; i < this.uma.a(); ++i) {
                final int n = i * (this.sma.getAscent() + this.sma.getDescent());
                final int n2 = b;
                int n3;
                if (this.qma || this.oma) {
                    n3 = n2 - (this.sma.stringWidth(this.uma.b(i)) + 2 * this.jma);
                }
                else {
                    n3 = n2 - this.sma.stringWidth(this.uma.b(i));
                }
                int n4 = n3 / 2;
                if (n4 < 0) {
                    n4 = 0;
                }
                if (this.vma) {
                    n4 = 0;
                }
                if (this.qma || this.oma) {
                    graphics.drawString(this.uma.b(i), n4 + kma + this.jma + 2, n + lma + this.wma + this.sma.getAscent());
                }
                else {
                    graphics.drawString(this.uma.b(i), n4 + kma + 2, n + lma + this.sma.getAscent());
                }
            }
        }
        else if (this.qma || this.oma) {
            graphics.drawString(this.text, kma + this.jma + 2, lma + this.wma + this.sma.getAscent());
        }
        else {
            graphics.drawString(this.text, kma + 2, lma + this.sma.getAscent());
        }
        graphics.setFont(font);
    }
    
    public void a(final Graphics graphics, final String text) {
        if (!this.tma) {
            this.text = text.replace('\n', ' ');
        }
        else {
            this.text = text;
        }
        this.uma.m(text);
        this.a(graphics);
    }
    
    public void _(final Graphics graphics, final String text, final int kma, final int lma) {
        if (!this.tma) {
            this.text = text.replace('\n', ' ');
        }
        else {
            this.text = text;
        }
        this.uma.m(text);
        this.kma = kma;
        this.lma = lma;
        this.a(graphics);
    }
    
    public void _(final Graphics graphics, final int kma, final int lma) {
        this.kma = kma;
        this.lma = lma;
        this.a(graphics);
    }
    
    public void i(final int mma) {
        this.mma = mma;
    }
    
    public void j(final int nma) {
        this.nma = nma;
    }
    
    public void f(final Color rma) {
        this.rma = rma;
    }
    
    public void setColor(final Color xa) {
        this.xa = xa;
    }
    
    public void g(final Color pma) {
        this.pma = pma;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(String text) {
        if (text == null) {
            text = "";
        }
        if (!this.tma) {
            this.text = text.replace('\n', ' ');
        }
        else {
            this.text = text;
        }
        this.uma.m(text);
        this.sma = null;
    }
    
    public void f(final int kma, final int lma) {
        this.kma = kma;
        this.lma = lma;
    }
    
    public void b(final Point point) {
        this.kma = point.x;
        this.lma = point.y;
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public void setFont(final Font font) {
        this.font = font;
    }
    
    public void i(final boolean qma) {
        this.qma = qma;
    }
    
    public void h(final boolean oma) {
        this.oma = oma;
    }
    
    public boolean g() {
        return this.qma;
    }
    
    public boolean P() {
        return this.oma;
    }
    
    public void l(final boolean vma) {
        this.vma = vma;
    }
}
