// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Container;
import java.awt.Color;
import java.awt.Frame;
import java.applet.AppletContext;
import java.awt.Canvas;

public class vad extends Canvas
{
    private AppletContext a;
    private String[] b;
    private String c;
    private String d;
    private String e;
    private String f;
    private boolean g;
    public boolean h;
    private Frame i;
    public Color j;
    private int k;
    private int l;
    private int m;
    private int n;
    private boolean[] o;
    private String[] p;
    private int[] q;
    private int[] r;
    
    public vad(final AppletContext a, final String s) {
        this.f = "%0";
        this.g = false;
        this.h = false;
        this.j = Color.blue;
        this.k = 5;
        this.a = a;
        this.b = this.b(s);
        this.c = "";
        this.a();
    }
    
    public void addNotify() {
        super.addNotify();
        Container container;
        for (container = this.getParent(); !(container instanceof Frame) && container != null; container = container.getParent()) {}
        if (container instanceof Frame) {
            this.i = (Frame)container;
        }
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        if (this.h) {
            final String string = (font.isBold() ? "bold" : "") + (font.isItalic() ? "italic" : "");
            this.a(font.getName() + ((string.length() > 0) ? ("-" + string + "-") : "-") + font.getSize());
        }
    }
    
    public Dimension minimumSize() {
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        return new Dimension(fontMetrics.stringWidth(this.c) + 4, fontMetrics.getHeight() + 4);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public synchronized void a(final String c, final String d, final String e, final String f, final boolean g) {
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.k = 5;
        this.a();
        this.repaint();
    }
    
    public void a(final String s, final String s2) {
        this.a("", s, s2, "%0", false);
    }
    
    public void a(final String s, final String s2, final boolean b) {
        this.a(s, null, null, s2, b);
    }
    
    public void a(final String s) {
        this.a(s, null, null, "%0", false);
    }
    
    private String[] b(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    private void a() {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.c, " <>[]{}(),", true);
        final int countTokens = stringTokenizer.countTokens();
        this.o = new boolean[countTokens];
        this.p = new String[countTokens];
        this.q = new int[countTokens];
        this.r = new int[countTokens];
        for (int i = 0; i < countTokens; ++i) {
            this.p[i] = stringTokenizer.nextToken();
            for (int n = 0; n < this.b.length && !this.o[i]; ++n) {
                this.o[i] = (!this.g && this.p[i].startsWith(this.b[n]));
            }
        }
    }
    
    private synchronized String a(final int n) {
        if (this.d != null && this.e != null && this.e.length() > 0 && n >= this.m && n <= this.n) {
            return this.e;
        }
        for (int i = 0; i < this.p.length; ++i) {
            if (this.o[i] && n >= this.q[i] && n <= this.r[i]) {
                return this.p[i];
            }
        }
        return null;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (event.id == 403) {
            if (n == 1004 || n == 1006) {
                this.k -= 30;
                this.repaint();
                return true;
            }
            if (n == 1005 || n == 1007) {
                this.k += 30;
                this.repaint();
                return true;
            }
        }
        return false;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        return this.mouseMove(event, n, n2);
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        final String a = this.a(n);
        if (a != null) {
            if (this.i != null) {
                this.i.setCursor(12);
            }
            this.a.showStatus(a);
        }
        else {
            if (this.i != null) {
                this.i.setCursor(10);
            }
            this.a.showStatus("");
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.i != null) {
            this.i.setCursor(0);
        }
        this.a.showStatus("");
        return true;
    }
    
    public boolean mouseDown(final Event event, final int l, final int n) {
        this.l = l;
        this.requestFocus();
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int l, final int n) {
        this.k += ((this.l == -1) ? 0 : (l - this.l));
        this.l = l;
        this.repaint();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.l = -1;
        final String a = this.a(n);
        if (a != null) {
            try {
                final URL url = new URL(van.a(this.f, a));
                final String protocol = url.getProtocol();
                if (protocol.equals("mailto") || protocol.equals("news")) {
                    this.a.showDocument(url, "_self");
                }
                else {
                    this.a.showDocument(url, "_blank");
                }
            }
            catch (MalformedURLException ex) {}
        }
        return true;
    }
    
    public synchronized void paint(final Graphics graphics) {
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final int n = fontMetrics.getAscent() + 2;
        final int n2 = n + fontMetrics.getDescent();
        int k = this.k;
        if (this.d != null) {
            this.m = k;
            final int n3 = k + fontMetrics.stringWidth(this.d);
            this.n = n3;
            if (this.e != null && this.e.length() > 0) {
                graphics.setColor(this.j);
                graphics.drawLine(this.m, n2, this.n, n2);
            }
            else {
                graphics.setColor(this.getForeground());
            }
            graphics.drawString(this.d, this.m, n);
            k = n3 + fontMetrics.stringWidth(" ");
        }
        for (int i = 0; i < this.p.length; ++i) {
            this.q[i] = k;
            k += fontMetrics.stringWidth(this.p[i]);
            this.r[i] = k;
            if (this.o[i]) {
                graphics.setColor(this.j);
                graphics.drawLine(this.q[i], n2, this.r[i], n2);
            }
            else {
                graphics.setColor(this.getForeground());
            }
            graphics.drawString(this.p[i], this.q[i], n);
        }
    }
}
