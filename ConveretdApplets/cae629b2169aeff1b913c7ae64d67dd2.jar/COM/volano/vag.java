// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.Graphics;
import java.net.MalformedURLException;
import java.awt.Event;
import java.util.StringTokenizer;
import java.io.InputStream;
import java.util.Properties;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Container;
import java.awt.Color;
import java.awt.Frame;
import java.applet.AppletContext;
import java.awt.Canvas;

public class vag extends Canvas implements Runnable
{
    private AppletContext a;
    private String[] b;
    private String c;
    private String d;
    private String e;
    private String f;
    private boolean g;
    public boolean h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private Frame n;
    public Color o;
    private int p;
    private int q;
    private int r;
    private int s;
    private boolean[] t;
    private String[] u;
    private int[] v;
    private int[] w;
    
    public vag(final AppletContext a, final String s) {
        this.f = "%0";
        this.g = false;
        this.h = false;
        this.i = "";
        this.j = "";
        this.k = "";
        this.l = "";
        this.m = "";
        this.o = Color.blue;
        this.p = 5;
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
            this.n = (Frame)container;
        }
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        if (this.h) {
            final String string = String.valueOf(font.isBold() ? "bold" : "") + (font.isItalic() ? "italic" : "");
            this.a(String.valueOf(font.getName()) + ((string.length() > 0) ? ("-" + string + "-") : "-") + font.getSize());
        }
    }
    
    public Dimension minimumSize() {
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        return new Dimension(Math.min(fontMetrics.stringWidth(this.c) + 4, 468), fontMetrics.getHeight() + 4);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public void a(final String i, final String j, final String k, final String l, final String m) {
        this.i = i;
        this.j = j;
        this.k = k;
        this.l = l;
        this.m = m;
        new Thread(this).start();
    }
    
    public void run() {
        try {
            final InputStream openStream = new URL(this.i).openStream();
            final Properties properties = new Properties();
            properties.load(openStream);
            openStream.close();
            this.a(properties.getProperty(this.j, this.l), properties.getProperty(this.k, this.m));
        }
        catch (Exception ex) {
            this.a(ex.toString());
        }
    }
    
    public synchronized void a(final String c, final String d, final String e, final String f, final boolean g) {
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.p = 5;
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
        this.t = new boolean[countTokens];
        this.u = new String[countTokens];
        this.v = new int[countTokens];
        this.w = new int[countTokens];
        for (int i = 0; i < countTokens; ++i) {
            this.u[i] = stringTokenizer.nextToken();
            for (int n = 0; n < this.b.length && !this.t[i]; ++n) {
                this.t[i] = (!this.g && this.u[i].startsWith(this.b[n]));
            }
        }
    }
    
    private synchronized String a(final int n) {
        if (this.d != null && this.e != null && this.e.length() > 0 && n >= this.r && n <= this.s) {
            return this.e;
        }
        for (int i = 0; i < this.u.length; ++i) {
            if (this.t[i] && n >= this.v[i] && n <= this.w[i]) {
                return this.u[i];
            }
        }
        return null;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (event.id == 403) {
            if (n == 1004 || n == 1006) {
                this.p -= 30;
                this.repaint();
                return true;
            }
            if (n == 1005 || n == 1007) {
                this.p += 30;
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
            if (this.n != null) {
                this.n.setCursor(12);
            }
            this.a.showStatus(a);
        }
        else {
            if (this.n != null) {
                this.n.setCursor(10);
            }
            this.a.showStatus("");
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.n != null) {
            this.n.setCursor(0);
        }
        this.a.showStatus("");
        return true;
    }
    
    public boolean mouseDown(final Event event, final int q, final int n) {
        this.q = q;
        this.requestFocus();
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int q, final int n) {
        this.p += ((this.q == -1) ? 0 : (q - this.q));
        this.q = q;
        this.repaint();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.q = -1;
        final String a = this.a(n);
        if (a != null) {
            try {
                final URL url = new URL(vaq.a(this.f, a));
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
        int p = this.p;
        if (this.d != null) {
            this.r = p;
            final int s = p + fontMetrics.stringWidth(this.d);
            this.s = s;
            if (this.e != null && this.e.length() > 0) {
                graphics.setColor(this.o);
                graphics.drawLine(this.r, n2, this.s, n2);
            }
            else {
                graphics.setColor(this.getForeground());
            }
            graphics.drawString(this.d, this.r, n);
            p = s + fontMetrics.stringWidth(" ");
        }
        for (int i = 0; i < this.u.length; ++i) {
            this.v[i] = p;
            p += fontMetrics.stringWidth(this.u[i]);
            this.w[i] = p;
            if (this.t[i]) {
                graphics.setColor(this.o);
                graphics.drawLine(this.v[i], n2, this.w[i], n2);
            }
            else {
                graphics.setColor(this.getForeground());
            }
            graphics.drawString(this.u[i], this.v[i], n);
        }
    }
}
