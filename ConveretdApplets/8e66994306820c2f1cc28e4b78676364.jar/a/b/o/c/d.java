// 
// Decompiled by Procyon v0.5.30
// 

package a.b.o.c;

import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import a.b.b.b;
import a.b.b.c;
import java.awt.Color;
import java.awt.Font;
import java.util.Vector;
import java.awt.Cursor;
import java.awt.Image;
import a.b.o.a.b.e;
import java.io.Serializable;
import java.awt.event.MouseListener;
import java.awt.Component;

public class d extends Component implements h, MouseListener, Serializable
{
    private a.b.o.a.b.e a;
    private int b;
    private Image c;
    private Cursor d;
    private Vector e;
    private Vector f;
    private Vector g;
    private Vector h;
    private int i;
    private static String z;
    
    public d() {
        this(null, null, null);
    }
    
    public d(final String s) {
        this(s, null, null);
    }
    
    public d(final String s, final Font font, final Color color) {
        this(new a.b.o.a.b.e(s, font, color));
    }
    
    public d(final a.b.o.a.b.e e) {
        this.e = new Vector();
        this.f = new Vector();
        this.g = new Vector();
        this.h = new Vector();
        this.a(e);
        this.a((Cursor)null);
        this.b(0);
        this.addMouseListener(this);
    }
    
    public void a(final a.b.o.a.b.e e) {
        this.a(e, true);
    }
    
    protected void a(final a.b.o.a.b.e a, final boolean b) {
        if (a != null) {
            this.a = a;
            if (this.a.b() != null) {
                super.setFont(this.a.b());
            }
            if (this.a.c() != null) {
                super.setForeground(this.a.c());
            }
        }
        else {
            this.a = new a.b.o.a.b.e();
        }
        if (b) {
            this.c();
        }
        this.c = null;
    }
    
    public void a(final c c) {
        if (c != null) {
            this.e.addElement(c);
        }
    }
    
    public void b(final c c) {
        if (c != null) {
            this.e.removeElement(c);
        }
    }
    
    public void c(final c c) {
        if (c != null) {
            this.f.addElement(c);
        }
    }
    
    public void d(final c c) {
        if (c != null) {
            this.f.removeElement(c);
        }
    }
    
    public void e(final c c) {
        if (c != null) {
            this.g.addElement(c);
        }
    }
    
    public void f(final c c) {
        if (c != null) {
            this.g.removeElement(c);
        }
    }
    
    public void a(final b b) {
        if (b != null) {
            this.h.addElement(b);
        }
    }
    
    public int getX() {
        return this.getBounds().x;
    }
    
    public int getY() {
        return this.getBounds().y;
    }
    
    public int getHeight() {
        return this.getBounds().height;
    }
    
    public int getWidth() {
        return this.getBounds().width;
    }
    
    public void paint(final Graphics graphics) {
        if (graphics != null) {
            if (this.c != null) {
                graphics.drawImage(this.c, 0, 0, null);
            }
            else {
                this.a(graphics);
            }
        }
    }
    
    public String a() {
        final String[] a = this.a.a();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < a.length; ++i) {
            sb.append(a[i]);
        }
        return sb.toString();
    }
    
    public Font getFont() {
        return this.a.b();
    }
    
    public void setFont(final Font font) {
        this.a.a(font);
        super.setFont(this.a.b());
        this.c();
        this.c = null;
    }
    
    public void a(final int n) {
        this.a.a(n);
    }
    
    public void b(final int i) {
        this.i = i;
    }
    
    public int a() {
        return this.i;
    }
    
    public Color b() {
        return this.a.c();
    }
    
    public void a(final Cursor d) {
        this.d = d;
    }
    
    private void a(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        graphics.setFont(this.a.b());
        graphics.setColor(this.a.c());
        final FontMetrics fontMetrics = this.getFontMetrics(this.a.b());
        this.b = fontMetrics.getLeading() + fontMetrics.getMaxAscent();
        final Dimension[] a = this.a.a(fontMetrics);
        final String[] a2 = this.a.a();
        int b = this.b;
        for (int i = 0; i < a2.length; ++i) {
            int n = 0;
            switch (this.a.d()) {
                case 0: {
                    n = 0;
                    break;
                }
                case 1: {
                    n = this.getWidth() - a[i].width;
                    break;
                }
                case 2: {
                    n = (this.getWidth() - a[i].width) / 2;
                    break;
                }
                default: {
                    n = 0;
                    break;
                }
            }
            graphics.drawString(a2[i], n, b);
            if (this.a.e()) {
                graphics.drawLine(n, b + 1, n + a[i].width, b + 1);
            }
            b += a[i].height;
        }
    }
    
    protected void c() {
        this.setSize(this.b(this.a));
    }
    
    protected Dimension b(final a.b.o.a.b.e e) {
        int n = 0;
        int max = 0;
        if (e != null && e.b() != null) {
            final Dimension[] b = e.b(this.getFontMetrics(e.b()));
            for (int i = 0; i < b.length; ++i) {
                n += b[i].height;
                max = Math.max(max, b[i].width);
            }
        }
        return new Dimension(max, n);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.d != null) {
            super.setCursor(this.d);
        }
        for (int i = 0; i < this.h.size(); ++i) {
            final b b = this.h.elementAt(i);
            for (int j = 0; j < this.f.size(); ++j) {
                try {
                    b.a((c)this.f.elementAt(j));
                }
                catch (NullPointerException ex) {
                    this.h.removeElementAt(i);
                    --i;
                }
            }
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.d != null) {
            super.setCursor(new Cursor(0));
        }
        for (int i = 0; i < this.h.size(); ++i) {
            final b b = this.h.elementAt(i);
            for (int j = 0; j < this.g.size(); ++j) {
                try {
                    b.a((c)this.g.elementAt(j));
                }
                catch (NullPointerException ex) {
                    this.h.removeElementAt(i);
                    --i;
                }
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        for (int i = 0; i < this.h.size(); ++i) {
            final b b = this.h.elementAt(i);
            for (int j = 0; j < this.e.size(); ++j) {
                try {
                    b.a((c)this.e.elementAt(j));
                }
                catch (NullPointerException ex) {
                    this.h.removeElementAt(i);
                    --i;
                }
            }
        }
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        try {
            final d d = (d)o;
            return this.a(this.a(), d.a()) && this.a(this.getFont(), d.getFont()) && this.a(this.b(), d.b());
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    private boolean a(final Object o, final Object o2) {
        if (o == null || o2 == null) {
            return o == o2;
        }
        return o.equals(o2);
    }
    
    public String toString() {
        return new String(a.b.o.c.d.z + this.a() + "}");
    }
    
    static {
        final char[] charArray = "#XnS6\u0018SbB\u001b\u0003\u001dmS\u0010\u000fI,\u0007".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = 'w';
                    break;
                }
                case 1: {
                    c2 = '=';
                    break;
                }
                case 2: {
                    c2 = '\u0016';
                    break;
                }
                case 3: {
                    c2 = '\'';
                    break;
                }
                default: {
                    c2 = 'u';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        d.z = new String(charArray).intern();
    }
}
