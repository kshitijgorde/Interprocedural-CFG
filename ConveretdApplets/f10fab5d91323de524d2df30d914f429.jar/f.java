import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.util.Vector;
import java.awt.event.ComponentListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class f extends Canvas implements ComponentListener
{
    CHAT a;
    int c;
    int a;
    int b;
    int d;
    boolean c;
    boolean a;
    Vector a;
    Vector b;
    boolean b;
    String a;
    int e;
    
    public f(final CHAT a) {
        this.d = 0;
        this.c = false;
        this.b = false;
        this.a = a;
        this.a = a.h;
        j.a = a.createImage(1, 1).getGraphics();
        this.a = new Vector();
        this.b = new Vector();
        this.addComponentListener(this);
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.a(graphics, 0);
    }
    
    synchronized void a(final Graphics graphics, final int n) {
        if (n != 0) {
            this.a((j)null, n);
            return;
        }
        int t = this.a.t;
        if (t < 20) {
            t = 20;
        }
        final int visibleAmount = this.b / t;
        if (this.c) {
            this.c = false;
            this.a.a[0].setValue(this.c);
        }
        int n2;
        if (visibleAmount < this.c) {
            this.a.a[0].setVisibleAmount(visibleAmount);
            this.a.a[0].setEnabled(true);
            n2 = this.c - visibleAmount - this.a.a[0].getValue();
            if (n2 < 0) {
                n2 = 0;
            }
        }
        else {
            this.a.a[0].setEnabled(false);
            n2 = 0;
        }
        int n3 = 8;
        int n4 = this.b - t / 3;
        graphics.clearRect(0, n4, this.a, t);
        while (0 < n4 && n2 < this.c) {
            graphics.clearRect(0, n4 - t, this.a, t);
            final j j = this.b.elementAt(n2);
            graphics.setColor(j.b);
            graphics.setFont(j.b);
            final int a = j.a;
            boolean b;
            if (b = (a > 0)) {
                n3 = 25;
                b = (a < this.d);
            }
            Label_0545: {
                if (this.a) {
                    graphics.drawString(j.c, this.a - j.c, n4);
                    if (!j.a) {
                        n4 -= t;
                        break Label_0545;
                    }
                    if (b) {
                        graphics.drawImage(this.a.a.a[j.a], this.a - 21, n4 - 16, null);
                    }
                    graphics.drawString(j.a, this.a - j.d, n4);
                    graphics.setColor(j.a);
                    graphics.setFont(j.a);
                    graphics.drawString(j.b, this.a - j.b, n4);
                }
                else {
                    graphics.drawString(j.c, n3, n4);
                    if (!j.a) {
                        n4 -= t;
                        break Label_0545;
                    }
                    if (b) {
                        graphics.drawImage(this.a.a.a[j.a], 3, n4 - 16, null);
                    }
                    graphics.drawString(j.a, j.b, n4);
                    graphics.setColor(j.a);
                    graphics.setFont(j.a);
                    graphics.drawString(j.b, j.c, n4);
                }
                n4 -= t;
            }
            ++n2;
            n3 = 8;
        }
        graphics.clearRect(0, 0, this.a, n4);
    }
    
    public void a(final Font font, final Color color, final Font font2, final Color color2, final String s, final String s2, final String s3, final int n) {
        final j j = new j(font, color, font2, color2, s, s2, s3, n);
        this.a(j, 0);
        if (j.d < this.a) {
            this.a(j, 1);
        }
        else {
            int n2 = 1;
            int n3 = this.a - j.b;
            String s4 = "";
            final StringTokenizer stringTokenizer = new StringTokenizer(s3, " ", true);
            final boolean h = this.a.h;
            final Vector vector = new Vector<String>();
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (h) {
                    vector.addElement(nextToken);
                }
                else {
                    vector.insertElementAt(nextToken, 0);
                }
            }
            for (int i = vector.size() - 1; i >= 0; --i) {
                String substring = vector.elementAt(i);
                final int stringWidth = j.a.stringWidth(substring);
                if (n3 > stringWidth) {
                    n3 -= stringWidth;
                    if (h) {
                        s4 = substring.concat(s4);
                    }
                    else {
                        s4 = s4.concat(substring);
                    }
                }
                else {
                    if (this.a - 30 < stringWidth && n3 > 0 && !h) {
                        final int a = this.a(substring, j.a, n3);
                        s4 = s4.concat(substring.substring(0, a));
                        substring = substring.substring(a);
                    }
                    if (n2 != 0) {
                        this.a(new j(font, color, font2, color2, s, s2, s4, n), 1);
                        n2 = 0;
                    }
                    else {
                        this.a(new j(font, color, "  ".concat(s4)), 1);
                    }
                    if ((n3 = this.a - 30 - j.a.stringWidth(substring)) < 0 && !h) {
                        s4 = this.a(substring, j.a, font, color, 1);
                        n3 = this.a - 30 - j.a.stringWidth(s4);
                    }
                    else {
                        s4 = substring;
                    }
                }
            }
            if (!s4.trim().equals("")) {
                this.a(new j(font, color, "  ".concat(s4)), 1);
            }
        }
    }
    
    int a(final String s, final FontMetrics fontMetrics, final int n) {
        int n2;
        for (n2 = n * s.length() / fontMetrics.stringWidth(s); fontMetrics.stringWidth(s.substring(0, n2)) >= n; --n2) {}
        return n2;
    }
    
    String a(final String s, final FontMetrics fontMetrics, final Font font, final Color color, final int n) {
        int n2 = this.a - 30;
        final int n3;
        if (n2 < (n3 = fontMetrics.getMaxAdvance() << 2)) {
            n2 = n3;
            if (n2 < 50) {
                n2 = 50;
            }
        }
        String substring;
        int n4;
        for (substring = s; fontMetrics.stringWidth(substring) >= n2; substring = substring.substring(n4)) {
            for (n4 = n2 * substring.length() / fontMetrics.stringWidth(substring); fontMetrics.stringWidth(substring.substring(0, n4)) >= n2; --n4) {}
            final String substring2 = substring.substring(0, n4);
            if (n == 1) {
                this.a(new j(font, color, "  ".concat(substring2)), 1);
            }
            else if (n == 0) {
                this.b.insertElementAt(new j(font, color, "  ".concat(substring2)), 0);
            }
        }
        return substring;
    }
    
    synchronized void a(j j, final int n) {
        final boolean a = d.a;
        int b = n;
        int n2 = n;
        int n3 = n;
        int n4 = n;
        if (!a) {
            if (n == 0) {
                this.a.insertElementAt(j, 0);
                f f = this;
                if (!a) {
                    if (this.a.size() > 60) {
                        this.a.setSize(60);
                    }
                    f = this;
                }
                f.b = false;
                if (!a) {
                    return;
                }
            }
            b = n;
            n2 = n;
            n3 = n;
            n4 = n;
        }
        int n8;
        int n7;
        int n6;
        final int n5 = n6 = (n7 = (n8 = 1));
        if (!a) {
            Label_0134: {
                if (n4 == n5) {
                    final int n9 = n3 = (n2 = (b = (this.b ? 1 : 0)));
                    if (a) {
                        break Label_0134;
                    }
                    if (n9 == 0) {
                        this.b.insertElementAt(j, 0);
                        this.c = this.b.size();
                        this.a.a[0].setMaximum(this.c);
                        this.a.d();
                        this.repaint();
                        if (!a) {
                            return;
                        }
                    }
                }
                b = n;
                n2 = n;
                n3 = n;
            }
            n7 = (n6 = (n8 = 2));
        }
        if (!a) {
            if (n3 == n6) {
                this.b = true;
                this.a = this.getSize().width;
                this.c = 0;
                this.b.removeAllElements();
                final int h = this.a.h ? 1 : 0;
                int size = this.a.size();
                while (true) {
                    while (true) {
                        Label_0745: {
                            if (!a) {
                                break Label_0745;
                            }
                            j = (j)this.a.elementAt(size - 1);
                            final int d = j.d;
                            Label_0742: {
                                if (a || d >= this.a) {
                                    int n10 = this.a - j.b;
                                    String s = "";
                                    final StringTokenizer stringTokenizer = new StringTokenizer(j.a, " ", true);
                                    final Vector<String> vector = new Vector<String>();
                                    while (true) {
                                        Label_0286: {
                                            if (a) {
                                                break Label_0286;
                                            }
                                        Label_0364_Outer:
                                            while (!stringTokenizer.hasMoreTokens()) {
                                                int n11 = vector.size() - 1;
                                                if (!a && !a) {
                                                    String trim = null;
                                                    while (true) {
                                                        while (true) {
                                                            Label_0689: {
                                                                if (!a) {
                                                                    break Label_0689;
                                                                }
                                                                final String s2 = vector.elementAt(n11);
                                                                String substring = trim;
                                                                final int stringWidth = j.a.stringWidth(substring);
                                                                final int n12 = n10;
                                                                final int n13 = stringWidth;
                                                                Label_0686: {
                                                                    final int n15;
                                                                    Label_0509: {
                                                                        if (!a) {
                                                                            if (n12 > n13) {
                                                                                n10 -= stringWidth;
                                                                                if (!a) {
                                                                                    if (h != 0) {
                                                                                        s = substring.concat(s);
                                                                                        if (!a) {
                                                                                            break Label_0686;
                                                                                        }
                                                                                    }
                                                                                    s = s.concat(substring);
                                                                                }
                                                                                if (!a) {
                                                                                    break Label_0686;
                                                                                }
                                                                            }
                                                                            final int n14 = this.a - 30;
                                                                            if (a) {
                                                                                break Label_0509;
                                                                            }
                                                                        }
                                                                        if (n12 < n13) {
                                                                            int n16;
                                                                            n15 = (n16 = n10);
                                                                            if (!a) {
                                                                                if (n15 > 0) {
                                                                                    final boolean b2;
                                                                                    final int n17 = (b2 = ((n16 = h) != 0)) ? 1 : 0;
                                                                                    if (!a) {
                                                                                        if (n17 == 0) {
                                                                                            final int a2 = this.a(substring, j.a, n10);
                                                                                            s = s.concat(substring.substring(0, a2));
                                                                                            substring = substring.substring(a2);
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                    Label_0600: {
                                                                        Label_0570: {
                                                                            if (!a) {
                                                                                if (n15 == 0) {
                                                                                    break Label_0570;
                                                                                }
                                                                                this.b.insertElementAt(new j(j.b, j.b, j.a, j.a, j.c, j.b, s, j.a), 0);
                                                                            }
                                                                            if (!a) {
                                                                                break Label_0600;
                                                                            }
                                                                        }
                                                                        this.b.insertElementAt(new j(j.b, j.b, "  ".concat(s)), 0);
                                                                    }
                                                                    int n20;
                                                                    int n19;
                                                                    final int n18 = n10 = (n19 = (n20 = this.a - 30 - j.a.stringWidth(substring)));
                                                                    Label_0682: {
                                                                        if (!a) {
                                                                            if (n18 >= 0) {
                                                                                break Label_0682;
                                                                            }
                                                                            n20 = (n19 = h);
                                                                        }
                                                                        if (!a) {
                                                                            if (n19 != 0) {
                                                                                break Label_0682;
                                                                            }
                                                                            s = this.a(substring, j.a, j.b, j.b, 0);
                                                                            n20 = this.a - 30 - j.a.stringWidth(s);
                                                                        }
                                                                        n10 = n20;
                                                                        if (!a) {
                                                                            break Label_0686;
                                                                        }
                                                                    }
                                                                    s = substring;
                                                                }
                                                                --n11;
                                                            }
                                                            if (n11 >= 0) {
                                                                continue Label_0364_Outer;
                                                            }
                                                            break;
                                                        }
                                                        trim = s.trim();
                                                        if (a) {
                                                            continue;
                                                        }
                                                        break;
                                                    }
                                                    if (!trim.equals("")) {
                                                        this.b.insertElementAt(new j(j.b, j.b, "  ".concat(s)), 0);
                                                    }
                                                    break Label_0742;
                                                }
                                            }
                                        }
                                        final String nextToken = stringTokenizer.nextToken();
                                        Label_0314: {
                                            if (!a) {
                                                if (h == 0) {
                                                    break Label_0314;
                                                }
                                                vector.addElement(nextToken);
                                            }
                                            if (!a) {
                                                continue;
                                            }
                                        }
                                        vector.insertElementAt(nextToken, 0);
                                        continue;
                                    }
                                }
                                this.b.insertElementAt(j, 0);
                                if (a) {
                                    goto Label_0238;
                                }
                            }
                            --size;
                        }
                        if (size > 0) {
                            continue;
                        }
                        break;
                    }
                    this.c = this.b.size();
                    this.a.a[0].setMaximum(this.c);
                    if (a) {
                        continue;
                    }
                    break;
                }
                if (!a) {
                    return;
                }
            }
            b = n;
            n2 = n;
            n8 = (n7 = 3);
        }
        if (!a) {
            if (n2 == n7) {
                final int size2 = this.a.size();
                int n21 = 0;
                while (true) {
                    Label_0841: {
                        if (!a) {
                            break Label_0841;
                        }
                        j = (j)this.a.elementAt(n21);
                        j.a(this.a, this.e);
                        ++n21;
                    }
                    if (n21 >= size2) {
                        return;
                    }
                    continue;
                }
            }
            else {
                b = n;
                n8 = 4;
            }
        }
        if (b == n8) {
            this.a.removeAllElements();
        }
    }
    
    public void a(final String a, final int e) {
        this.a = a;
        this.e = e;
        this.a((Graphics)null, 3);
        this.a((Graphics)null, 2);
        this.repaint();
    }
    
    public void a() {
        this.a((Graphics)null, 4);
        this.b = this.getSize().height;
        this.a((Graphics)null, 2);
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.b = this.getSize().height;
        this.a((Graphics)null, 2);
    }
}
