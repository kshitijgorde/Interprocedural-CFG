// 
// Decompiled by Procyon v0.5.30
// 

package b.a.e;

import java.awt.Color;
import java.awt.Font;
import javax.swing.plaf.UIResource;
import javax.swing.UIManager;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Dimension;
import b.a.d.d;
import java.util.Vector;
import javax.swing.SwingConstants;

public class o extends m implements SwingConstants
{
    protected int c;
    protected Vector d;
    protected int e;
    protected int f;
    protected int g;
    protected int h;
    protected String i;
    protected int j;
    
    public o() {
        this("", -1);
    }
    
    public o(final String s, final int n) {
        this.c = 2;
        this.d = null;
        this.e = -1;
        this.f = 1;
        this.g = -1;
        this.h = -1;
        this.j = 1;
        this.a(n);
        this.a(s);
        this.setOpaque(false);
        this.f();
    }
    
    public String e() {
        return this.i;
    }
    
    public void a(String substring) {
        if (!b.a.d.d.a(this.i, (Object)substring)) {
            this.i = substring;
            this.d = new Vector();
            while (substring != null && substring.length() != 0) {
                int n = 1;
                int n2;
                if ((n2 = substring.indexOf("\r\n")) >= 0) {
                    n = 2;
                }
                else if ((n2 = substring.indexOf(13)) < 0) {
                    n2 = substring.indexOf(10);
                }
                String substring2;
                if (n2 >= 0) {
                    substring2 = substring.substring(0, n2);
                    substring = substring.substring(n2 + n);
                }
                else {
                    substring2 = substring;
                    substring = null;
                }
                this.d.addElement(substring2);
            }
            this.repaint();
        }
    }
    
    public void a(final int h) {
        this.h = h;
    }
    
    public Dimension b() {
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return super.b();
        }
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final Dimension dimension = new Dimension(0, ((this.d == null) ? this.f : Math.max(this.d.size(), this.f)) * fontMetrics.getHeight());
        if (this.h < 0 && fontMetrics.getMaxAdvance() > 0) {
            dimension.width = fontMetrics.getMaxAdvance();
        }
        else if (this.h < 0) {
            dimension.width = super.b().width;
        }
        else {
            dimension.width = this.h;
        }
        return dimension;
    }
    
    public Dimension c() {
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return super.c();
        }
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int n = (this.d == null) ? this.f : Math.max(this.d.size(), this.f);
        int n2 = this.g;
        if (this.getParent() != null && this.getParent().getSize().width < this.g && this.getParent().getSize().width != 0) {
            n2 = this.getParent().getSize().width;
        }
        if (n2 < this.h && this.h > 0) {
            n2 = this.h;
        }
        if (n2 >= 0) {
            return new Dimension(n2, this.a(graphics, n2, -1));
        }
        return new Dimension(this.a(fontMetrics), n * fontMetrics.getHeight());
    }
    
    protected int a(final FontMetrics fontMetrics) {
        if (this.e() == null || this.d == null) {
            return 0;
        }
        int n = 0;
        for (int i = 0; i < this.d.size(); ++i) {
            final int stringWidth = fontMetrics.stringWidth(this.d.elementAt(i));
            if (n < stringWidth) {
                n = stringWidth;
            }
        }
        return n;
    }
    
    protected void a(final Graphics graphics) {
        this.a(graphics, this.getWidth(), this.getHeight());
    }
    
    protected int a(final Graphics graphics, final int n, final int n2) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        int n3 = fontMetrics.getAscent();
        int n4 = 0;
        final int height = fontMetrics.getHeight();
        int n5 = 0;
        int n6 = (n2 < 0 || this.j != 1) ? 1 : 0;
        while (true) {
        Label_0353:
            for (int n7 = 0; this.d != null && n7 < this.d.size(); ++n7) {
                String substring = this.d.elementAt(n7);
                while (substring != null) {
                    int n8;
                    String s;
                    if ((n8 = fontMetrics.stringWidth(substring)) <= n) {
                        s = substring;
                        substring = null;
                    }
                    else {
                        s = "";
                        while (substring != null && substring.length() > 0) {
                            final int index = substring.indexOf(32);
                            if (index < 0 && s.length() == 0) {
                                s = substring;
                                substring = null;
                            }
                            else {
                                if (index < 0) {
                                    break;
                                }
                                final String substring2 = substring.substring(0, index);
                                String string;
                                if (s.length() == 0) {
                                    string = substring2;
                                }
                                else {
                                    string = s + " " + substring2;
                                }
                                if (fontMetrics.stringWidth(string) > n && s.length() != 0) {
                                    break;
                                }
                                s = string;
                                substring = substring.substring(index + 1);
                            }
                        }
                        n8 = fontMetrics.stringWidth(s);
                    }
                    int n9;
                    if (this.c == 0) {
                        n9 = (n - n8) / 2;
                    }
                    else if (this.c == 4) {
                        n9 = n - n8;
                    }
                    else {
                        n9 = 0;
                    }
                    if (n6 == 0) {
                        graphics.drawString(s, n9, n3);
                    }
                    n3 += height;
                    n4 += height;
                    if (++n5 == this.e) {
                        break Label_0353;
                    }
                }
            }
            if (n2 < 0 || this.j == 1 || n6 == 0) {
                break;
            }
            n6 = 0;
            n3 = fontMetrics.getAscent();
            if (this.j == 0) {
                n3 += (n2 - n4) / 2;
            }
            else {
                if (this.j != 3) {
                    continue;
                }
                n3 += n2 - n4;
            }
        }
        if (n5 < this.f) {
            return height * this.f;
        }
        return n4;
    }
    
    public void updateUI() {
        super.updateUI();
        this.f();
    }
    
    protected void f() {
        final Font font = UIManager.getFont("Label.font");
        final Color color = UIManager.getColor("Label.background");
        final Color color2 = UIManager.getColor("Label.foreground");
        if (this.getFont() instanceof UIResource && font != null) {
            this.setFont(font);
        }
        if (this.getBackground() instanceof UIResource && color != null) {
            this.setBackground(color);
        }
        if (this.getForeground() instanceof UIResource && color2 != null) {
            this.setForeground(color2);
        }
    }
}
