// 
// Decompiled by Procyon v0.5.30
// 

package ABLjemsty;

import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import ABLwidgets.pen;
import ABLwidgets.new_font;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.Image;
import java.awt.Panel;

public class CommandStatusPanel extends Panel
{
    private Image a;
    private Image b;
    public Component c;
    private String d;
    private int e;
    private int f;
    private String g;
    private boolean h;
    public Component i;
    private int j;
    private int k;
    private char l;
    private boolean m;
    public int n;
    private Font o;
    private Color p;
    private Color q;
    private Color r;
    private Color s;
    private Font t;
    private Color u;
    private Color v;
    public String w;
    public String x;
    private String y;
    private Image[] z;
    private Image[] aa;
    private Image ab;
    private Image ac;
    
    public CommandStatusPanel() {
        this.d = "";
        this.g = "";
        this.h = true;
        this.l = '0';
        this.m = false;
        this.n = 26;
        this.p = Color.black;
        this.q = Color.lightGray;
        this.r = Color.gray;
        this.s = Color.white;
        this.u = Color.white;
        this.v = Color.black;
        this.y = "Command";
        this.z = new Image[2];
        this.aa = new Image[2];
        this.setLayout(null);
        this.setBackground(this.q);
        this.o = new_font.a("Dialog", 0, 10);
        this.t = this.o;
        this.setFont(this.o);
    }
    
    public void a(final Image image, final Image image2, final Image image3, final Image image4, final Image ab, final Image ac) {
        this.z[0] = image;
        this.z[1] = image2;
        this.aa[0] = image3;
        this.aa[1] = image4;
        this.a = this.z[0];
        this.b = this.aa[1];
        this.ab = ab;
        this.ac = ac;
    }
    
    public void a(final CommandStatusPanel commandStatusPanel) {
        if (commandStatusPanel.n > 0) {
            this.n = commandStatusPanel.n;
        }
        if (commandStatusPanel.o != null) {
            this.o = commandStatusPanel.o;
        }
        if (commandStatusPanel.p != null) {
            this.p = commandStatusPanel.p;
        }
        if (commandStatusPanel.q != null) {
            this.setBackground(this.q = commandStatusPanel.q);
        }
        if (commandStatusPanel.r != null) {
            this.r = commandStatusPanel.r;
        }
        if (commandStatusPanel.s != null) {
            this.s = commandStatusPanel.s;
        }
        if (commandStatusPanel.t != null) {
            this.t = commandStatusPanel.t;
        }
        if (commandStatusPanel.u != null) {
            this.u = commandStatusPanel.u;
        }
        if (commandStatusPanel.v != null) {
            this.v = commandStatusPanel.v;
        }
        if (commandStatusPanel.w != null) {
            this.w = commandStatusPanel.w;
        }
        if (commandStatusPanel.x != null) {
            this.x = commandStatusPanel.x;
        }
        this.j = commandStatusPanel.j;
        this.k = commandStatusPanel.k;
        this.l = commandStatusPanel.l;
        this.m = commandStatusPanel.m;
        this.z[0] = commandStatusPanel.z[0];
        this.z[1] = commandStatusPanel.z[1];
        this.aa[0] = commandStatusPanel.aa[0];
        this.aa[1] = commandStatusPanel.aa[1];
        this.a = this.z[0];
        this.b = this.aa[1];
        this.ab = commandStatusPanel.ab;
        this.ac = commandStatusPanel.ac;
    }
    
    public void a(final int n) {
        if (n > 0) {
            this.n = n;
        }
    }
    
    public void a(final int n, final int n2, final int n3, final int j, final int k) {
        this.j = j;
        this.k = k;
        this.reshape(n, n2, n3, this.n);
    }
    
    public void a(final char l, final char c) {
        this.l = l;
        this.m = (c == '1');
    }
    
    public void a(final boolean b) {
        final Image a = this.a;
        this.a = this.z[b];
        if (this.a != a) {
            this.repaint();
        }
    }
    
    public void b(final boolean b) {
        final Image b2 = this.b;
        this.b = this.aa[b];
        if (this.b != b2) {
            this.repaint();
        }
    }
    
    public void a(final pen pen) {
        if (pen == null) {
            return;
        }
        this.o = pen.d;
        this.p = pen.b;
        this.setBackground(this.q = pen.c);
    }
    
    public void b(final pen pen) {
        if (pen == null) {
            return;
        }
        this.t = pen.d;
        this.v = pen.b;
        this.u = pen.c;
    }
    
    public void c(final pen pen) {
        if (pen == null) {
            return;
        }
        this.r = pen.b;
        this.s = pen.c;
    }
    
    public void c(final boolean h) {
        if (h == this.h) {
            return;
        }
        this.h = h;
        if (this.c != null) {
            if (h) {
                this.c.show();
            }
            else {
                this.c.hide();
            }
        }
        this.repaint();
    }
    
    public void a(String g) {
        if (g == null) {
            g = "";
        }
        g.trim();
        if (g.length() == 0) {
            g = this.g;
        }
        this.d = g;
    }
    
    public void b(String s) {
        if (s == null) {
            s = "";
        }
        s.trim();
        this.g = s;
        this.d = s;
    }
    
    public void a(final Component c) {
        if (this.c != null) {
            this.remove(this.c);
        }
        if ((this.c = c) != null) {
            c.hide();
            c.setFont(this.t);
            c.setBackground(this.u);
            c.setForeground(this.v);
            this.add(c);
        }
    }
    
    public void c(final String y) {
        if (y == null) {
            return;
        }
        this.y = y;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if ((event.modifiers & 0x4) != 0x0) {
            if (n > this.e && n < this.f) {
                if (this.d.length() > 0 && this.d != this.g) {
                    this.a((String)null);
                    this.repaint();
                }
                else if (this.d == this.g && this.g.length() > 0 && this.i != null) {
                    event.arg = "show";
                    this.i.handleEvent(event);
                }
            }
            return true;
        }
        return super.mouseUp(event, n, n2);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.h) {
            return;
        }
        final int width = this.size().width;
        final int n = (this.ab == null) ? 0 : this.ab.getWidth(null);
        final int n2 = (this.ac == null) ? 0 : this.ac.getWidth(null);
        final int n3 = width - n - n2;
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.o);
        final int height = fontMetrics.getHeight();
        final int n4 = fontMetrics.getLeading() + fontMetrics.getAscent();
        final int n5 = graphics.getFontMetrics(this.t).getHeight() + 6;
        final int n6 = (this.n - 16) / 2;
        final int n7 = (this.n - height) / 2 + n4;
        final int n8 = (this.n - n5) / 2;
        final int n9 = 4;
        graphics.setFont(this.o);
        final int n10 = 0;
        if (this.ab != null) {
            graphics.drawImage(this.ab, n10, 0, this.q, this);
        }
        final int n11 = n10 + n + (n9 + 2);
        final int n12 = n6;
        if (this.a != null) {
            graphics.drawImage(this.a, n11, n12, this.q, this);
        }
        final int n13 = n11 + (16 + n9);
        if (this.b != null) {
            graphics.drawImage(this.b, n13, n12, this.q, this);
        }
        int n14 = n13 + (16 + n9);
        graphics.setColor(this.r);
        graphics.drawLine(n14, 0, n14, this.n - 2);
        ++n14;
        graphics.setColor(this.s);
        graphics.drawLine(n14, 0, n14, this.n - 2);
        ++n14;
        int j;
        if (this.m && this.c == null) {
            j = n14 + 2 * n9;
            if (this.j > j) {
                j = this.j;
            }
        }
        else {
            final int n15 = n14 + (n3 - n14) / 2;
            int n16 = n14 + 2 * n9;
            if (this.c == null) {
                graphics.setColor(this.p);
                if (this.x != null) {
                    graphics.drawString(this.x, n16, n7);
                }
            }
            else {
                if (this.w == null) {
                    this.w = this.y;
                }
                final int stringWidth = fontMetrics.stringWidth(this.w);
                if (n15 - n16 > stringWidth * 4 + 2 * n9) {
                    graphics.setColor(this.p);
                    if (this.c != null) {
                        graphics.drawString(this.w, n16, n7);
                    }
                    n16 = n16 + stringWidth + n9;
                }
                final int n17 = n15 - n16 - 2 * n9;
                if (n17 > 0) {
                    this.c.reshape(n16, n8, n17, n5);
                }
                if (n17 > 20) {
                    this.c.show();
                }
                else {
                    this.c.hide();
                }
            }
            int n18 = n15;
            graphics.setColor(this.r);
            graphics.drawLine(n18, 0, n18, this.n - 2);
            ++n18;
            graphics.setColor(this.s);
            graphics.drawLine(n18, 0, n18, this.n - 2);
            j = ++n18 + 2 * n9;
        }
        final int stringWidth2 = fontMetrics.stringWidth(this.d);
        int n19 = 0;
        switch (this.l) {
            case '1': {
                n19 = this.k - stringWidth2;
                break;
            }
            case '2': {
                n19 = (this.k - stringWidth2) / 2;
                break;
            }
        }
        if (n19 < 0) {
            n19 = 0;
        }
        final int e = j + n19;
        graphics.setColor(this.p);
        graphics.drawString(this.d, e, n7);
        this.e = e;
        final int f = width - n2;
        this.f = f;
        if (this.ac != null) {
            graphics.drawImage(this.ac, f, 0, this.q, this);
        }
    }
}
