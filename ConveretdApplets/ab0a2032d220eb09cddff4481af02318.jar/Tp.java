import java.awt.Point;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Tp
{
    public static final int LEFT = 0;
    public static final int sDb = 1;
    public static final int RIGHT = 2;
    public static final int t = 3;
    public static final int u = 4;
    protected int da;
    protected int qa;
    protected int ga;
    protected int ha;
    protected Color Ba;
    protected Color ja;
    protected Color la;
    protected int ea;
    protected int fa;
    protected String text;
    protected Font font;
    protected boolean ia;
    protected boolean ka;
    protected FontMetrics ma;
    protected boolean na;
    protected boolean pa;
    d oa;
    
    public Tp() {
        this.na = false;
        this.pa = false;
        this.oa = new d("\n");
        this.text = "";
        this.oa.e(this.text);
        this.ga = 0;
        this.ha = 3;
        this.Ba = Color.black;
        this.ja = Color.yellow;
        this.la = Color.black;
        this.ea = 0;
        this.fa = 0;
        this.font = new Font("SansSerif", 0, 10);
        this.ia = true;
        this.ka = true;
        this.da = 2;
        this.qa = 0;
        this.ma = null;
    }
    
    public Tp(final String text) {
        this();
        if (!this.na) {
            this.text = text.replace('\n', ' ');
        }
        else {
            this.text = text;
        }
        this.oa.e(text);
    }
    
    public void c(final boolean na) {
        this.na = na;
    }
    
    public boolean isEmpty() {
        return this.text == null || this.text.equals("");
    }
    
    public int _(final Graphics graphics) {
        int n = 0;
        try {
            if (this.ma == null) {
                this.ma = graphics.getFontMetrics(this.font);
            }
        }
        catch (NullPointerException ex) {}
        try {
            if (this.na) {
                for (int i = 0; i < this.oa.z(); ++i) {
                    final String _ = this.oa._(i);
                    if (_ != null) {
                        n = Math.max(n, this.ma.stringWidth(_));
                    }
                }
            }
            else {
                n = this.ma.stringWidth(this.text);
            }
        }
        catch (NullPointerException ex2) {}
        n += 2;
        if (this.ia || this.ka) {
            n += 2 * this.da;
        }
        return n;
    }
    
    public int a(final Graphics graphics) {
        try {
            if (this.ma == null) {
                this.ma = graphics.getFontMetrics(this.font);
            }
        }
        catch (NullPointerException ex) {}
        int n = 0;
        try {
            if (this.na) {
                n = this.ma.getDescent() + this.ma.getAscent();
                if (this.oa.z() > 0) {
                    n += (this.oa.z() - 1) * this.ma.getHeight();
                }
            }
            else {
                n = this.ma.getDescent() + this.ma.getAscent();
            }
        }
        catch (NullPointerException ex2) {}
        if (this.ia || this.ka) {
            n += 2 * this.qa;
        }
        return n;
    }
    
    public void b(final Graphics graphics) {
        int ea = this.ea;
        int fa = this.fa;
        final int a = this.a(graphics);
        final int _ = this._(graphics);
        switch (this.ga) {
            case 2: {
                ea -= _;
                break;
            }
            case 1: {
                ea -= _ / 2;
                break;
            }
        }
        switch (this.ha) {
            case 1: {
                fa -= a / 2;
                break;
            }
            case 4: {
                fa -= a;
                break;
            }
        }
        if (this.ia) {
            graphics.setColor(this.ja);
            graphics.fillRect(ea, fa, _ + 1, a + 1);
        }
        if (this.ka) {
            graphics.setColor(this.la);
            graphics.drawRect(ea, fa, _, a);
        }
        graphics.setColor(this.Ba);
        if (this.ma == null) {
            this.ma = graphics.getFontMetrics(this.font);
        }
        final Font font = graphics.getFont();
        graphics.setFont(this.font);
        if (this.na) {
            for (int i = 0; i < this.oa.z(); ++i) {
                final int n = i * (this.ma.getAscent() + this.ma.getDescent());
                final int n2 = _;
                int n3;
                if (this.ka || this.ia) {
                    n3 = n2 - (this.ma.stringWidth(this.oa._(i)) + 2 * this.da);
                }
                else {
                    n3 = n2 - this.ma.stringWidth(this.oa._(i));
                }
                int n4 = n3 / 2;
                if (n4 < 0) {
                    n4 = 0;
                }
                if (this.pa) {
                    n4 = 0;
                }
                if (this.ka || this.ia) {
                    graphics.drawString(this.oa._(i), n4 + ea + this.da + 2, n + fa + this.qa + this.ma.getAscent());
                }
                else {
                    graphics.drawString(this.oa._(i), n4 + ea + 2, n + fa + this.ma.getAscent());
                }
            }
        }
        else if (this.ka || this.ia) {
            graphics.drawString(this.text, ea + this.da + 2, fa + this.qa + this.ma.getAscent());
        }
        else {
            graphics.drawString(this.text, ea + 2, fa + this.ma.getAscent());
        }
        graphics.setFont(font);
    }
    
    public void b(final Graphics graphics, final String text) {
        if (!this.na) {
            this.text = text.replace('\n', ' ');
        }
        else {
            this.text = text;
        }
        this.oa.e(text);
        this.b(graphics);
    }
    
    public void b(final Graphics graphics, final String text, final int ea, final int fa) {
        if (!this.na) {
            this.text = text.replace('\n', ' ');
        }
        else {
            this.text = text;
        }
        this.oa.e(text);
        this.ea = ea;
        this.fa = fa;
        this.b(graphics);
    }
    
    public void _(final Graphics graphics, final int ea, final int fa) {
        this.ea = ea;
        this.fa = fa;
        this.b(graphics);
    }
    
    public void G(final int ga) {
        this.ga = ga;
    }
    
    public void F(final int ha) {
        this.ha = ha;
    }
    
    public void a(final Color la) {
        this.la = la;
    }
    
    public void setColor(final Color ba) {
        this.Ba = ba;
    }
    
    public void b(final Color ja) {
        this.ja = ja;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(final String text) {
        if (!this.na) {
            this.text = text.replace('\n', ' ');
        }
        else {
            this.text = text;
        }
        this.oa.e(text);
        this.ma = null;
    }
    
    public void d(final int ea, final int fa) {
        this.ea = ea;
        this.fa = fa;
    }
    
    public void a(final Point point) {
        this.ea = point.x;
        this.fa = point.y;
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public void setFont(final Font font) {
        this.font = font;
    }
    
    public void d(final boolean ka) {
        this.ka = ka;
    }
    
    public void e(final boolean ia) {
        this.ia = ia;
    }
    
    public boolean a() {
        return this.ka;
    }
    
    public boolean b() {
        return this.ia;
    }
    
    public void f(final boolean pa) {
        this.pa = pa;
    }
}
