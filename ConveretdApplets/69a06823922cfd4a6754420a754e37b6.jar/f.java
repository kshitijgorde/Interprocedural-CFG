import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class f extends d
{
    String[] d;
    Font e;
    Font f;
    protected int g;
    protected int h;
    protected Color i;
    protected Color j;
    int k;
    protected int l;
    private boolean m;
    
    public f(final String s, final int n, final int n2, final int g) {
        super(n, n2);
        this.f = null;
        this.g = 0;
        this.h = 0;
        this.j = Color.white;
        this.k = 0;
        this.l = 20;
        this.m = false;
        (this.d = new String[1])[0] = s;
        this.g = g;
        this.h = 0;
        this.i = Color.black;
        this.j = Color.white;
        this.l = 20;
    }
    
    public void a(final String s) {
        this.a(0, s, true);
    }
    
    public void a(final Font f) {
        this.f = f;
    }
    
    public void a(final int n, final String s, final boolean b) {
        this.d[n] = s;
        if (b) {
            this.a();
        }
    }
    
    public void a() {
        final int c = d.c;
        if (this.b == null) {
            return;
        }
        if (this.j == null) {
            this.j = Color.white;
        }
        this.b.setColor(this.j);
        this.b.fillRect(0, 0, this.getSize().width, this.getSize().height);
        if (this.m) {
            this.b.setColor(Color.red);
            this.b.drawRect(1, 1, this.getSize().width - 4, this.getSize().height - 4);
        }
        final FontMetrics a = this.a();
        this.b.setColor(this.i);
        Label_0455: {
            Label_0338: {
                if (this.l == 20) {
                    int h = 0;
                    Label_0205: {
                        if (this.h == 2) {
                            h = (this.getSize().height + a.getHeight()) / 2;
                            if (c == 0) {
                                break Label_0205;
                            }
                        }
                        if (this.h == 1) {
                            h = this.getSize().height - 2;
                            if (c == 0) {
                                break Label_0205;
                            }
                        }
                        if (this.h == 0) {
                            h = a.getHeight() + 2;
                            if (c == 0) {
                                break Label_0205;
                            }
                        }
                        h = this.h;
                    }
                    int i = 0;
                    while (i < this.d.length) {
                        int g = 0;
                        Label_0305: {
                            if (this.g == 0) {
                                g = 1;
                                if (c == 0) {
                                    break Label_0305;
                                }
                            }
                            if (this.g == 2) {
                                g = (this.getSize().width - a.stringWidth(this.d[i])) / 2;
                                if (c == 0) {
                                    break Label_0305;
                                }
                            }
                            if (this.g == 1) {
                                g = this.getSize().width - a.stringWidth(this.d[i]);
                                if (c == 0) {
                                    break Label_0305;
                                }
                            }
                            g = this.g;
                        }
                        this.b.drawString(this.d[i], g, h);
                        h += a.getHeight() + 0;
                        ++i;
                        if (c != 0) {
                            break Label_0338;
                        }
                    }
                    break Label_0455;
                }
            }
            final String s = this.d[0];
            final int n = (int)Math.floor(this.getSize().height / s.length());
            int j = 0;
            while (j < s.length()) {
                final String string = "" + s.charAt(j);
                this.b.drawString(string, (this.getSize().width - a.stringWidth(string)) / 2, (n + a.getHeight()) / 2 + j * n);
                ++j;
                if (c != 0) {
                    break;
                }
            }
        }
        this.repaint();
    }
    
    protected FontMetrics a() {
        final int c = d.c;
        this.e = this.getFont();
        if (this.f != null) {
            this.e = this.f;
        }
        this.b.setFont(this.e);
        FontMetrics fontMetrics = this.b.getFontMetrics();
        if (this.f == null && this.l == 20) {
            int i = 0;
            while (i < this.d.length) {
                while (fontMetrics.stringWidth("" + this.d[i]) > this.getSize().width - 2) {
                    final Font font = this.b.getFont();
                    if (font.getSize() == 0 && c == 0) {
                        break;
                    }
                    final Font font2 = new Font(font.getName(), font.getStyle(), font.getSize() - 1);
                    this.b.setFont(font2);
                    fontMetrics = this.b.getFontMetrics();
                    this.e = font2;
                    if (c != 0) {
                        break;
                    }
                }
                ++i;
                if (c != 0) {
                    break;
                }
            }
        }
        return fontMetrics;
    }
}
