// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import com.spilka.client.muc.AppletAbstract;
import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Color;
import java.util.Vector;
import java.awt.Canvas;

public final class aX extends Canvas implements cn, Runnable
{
    private Thread q;
    private int q;
    private int w;
    private int e;
    private int r;
    private int t;
    private int y;
    private int u;
    private int i;
    private int o;
    private int p;
    private int a;
    private int s;
    private int d;
    private int f;
    private int g;
    private int h;
    private int j;
    private int k;
    private String q;
    private String w;
    private String e;
    private String r;
    private Vector q;
    private Vector w;
    private Vector e;
    private Vector r;
    private Vector t;
    private Vector y;
    private Vector u;
    private Color q;
    private Color w;
    private Vector i;
    private URL q;
    private Vector o;
    private Graphics q;
    private Graphics w;
    private Image q;
    private Image w;
    private boolean q;
    private boolean w;
    private boolean e;
    private Frame q;
    private int l;
    private int z;
    private bI q;
    private int x;
    private boolean r;
    
    public aX(final bI q) {
        this.x = 0;
        this.r = false;
        this.q = q;
        final int u = be.w.u;
        final Color q2 = be.w.q;
        this.q = be.w.n;
        this.r();
    }
    
    public final void q() {
        this.r();
        this.w(0);
    }
    
    private void r() {
        this.o = -1;
        this.d = 1;
        this.q = "";
        this.w = "stop";
        this.e = "";
        this.r = "";
        this.q = false;
        this.w = false;
        this.e = false;
        this.y = 0;
        this.i = 0;
        this.x = 0;
        this.q = -1;
        this.w = -1;
        this.e = false;
        this.k = 0;
        this.r = "no";
        if (this.r == null) {
            this.r = "no";
        }
        this.e = "_blank";
        this.w = "stop";
        if (this.w == null) {
            this.w = "normal";
        }
        this.q = "10";
        if (this.q == null) {
            this.q = "0";
        }
        this.j = q(this.q, 10, 0);
        this.g = q("16", 10, 16);
        this.h = this.g;
        this.w = new Color(q("990000", 16, 0));
        this.e = 0;
        this.w = new Vector();
        this.e = new Vector();
        this.r = new Vector();
        this.t = new Vector();
        this.y = new Vector();
        this.u = new Vector();
        this.q = new Vector();
        this.i = new Vector();
        this.o = new Vector();
        this.q = this.size().width;
        this.w = this.size().height;
        this.q = (Frame)cp.q(this, !bI.a());
        this.e = true;
    }
    
    public final void q(final bl bl) {
        this.q(bl.getName(), bl);
    }
    
    public final void q(final String s, final bl bl) {
        if (!bl.e()) {
            if (this.q.b_() && bl.q()) {
                return;
            }
            if (this.q.a_() && bl.w()) {
                return;
            }
        }
        final aY q = this.q(s);
        this.w.addElement(q.q);
        String w = q.w;
        if (q.w == null || w.trim().equals("")) {
            w = "";
            this.q.addElement(new Integer(0));
            this.r.addElement("");
        }
        else {
            this.q.addElement(new Integer(1));
            this.r.addElement(cv.q(cv.q("Follow link %1"), w));
        }
        this.e.addElement(w);
        final Font font = new Font(bl.q, bl.t(), bl.y);
        this.i.addElement(font);
        this.o.addElement(this.getFontMetrics(font));
        this.t.addElement(new Color(bl.r()));
        this.y.addElement(q.q);
        this.u.addElement(new Color(bl.e()));
        if (this.x < font.getSize()) {
            this.x = font.getSize();
            this.w(this.x + 4);
        }
    }
    
    public final void q(final boolean r) {
        this.r = r;
    }
    
    private void w(final int w) {
        this.invalidate();
        this.w = w;
        this.i();
        this.q.validate();
    }
    
    private void t() {
        if (this.q != 0) {
            this.l = this.q;
        }
        if (this.w != 0) {
            this.z = this.w;
        }
        final Dimension size = this.size();
        this.q = size.width;
        this.w = size.height;
    }
    
    private void y() {
        if (this.q == 0 || this.w == 0) {
            return;
        }
        this.q = co.q(this, this.q, this.w, "sl.r-q");
        (this.q = this.q.getGraphics()).clipRect(0, 0, this.q, this.w);
    }
    
    private void u() {
        if (this.q == 0 || this.w == 0) {
            return;
        }
        this.w = co.q(this, this.q, this.w, "sl.r-p");
        (this.w = this.w.getGraphics()).clipRect(0, 0, this.q, this.w);
    }
    
    private void i() {
        if (this.q == null || this.q != this.l || this.w != this.z) {
            this.y();
        }
        if (this.w == null || this.q != this.l || this.w != this.z) {
            this.u();
        }
    }
    
    private static int q(final String s, final int n, final int n2) {
        int int1;
        try {
            int1 = Integer.parseInt(s, n);
        }
        catch (Exception ex) {
            int1 = n2;
        }
        return int1;
    }
    
    public final void update(final Graphics graphics) {
        if (this.e) {
            this.paint(graphics);
        }
    }
    
    public final void paint(final Graphics graphics) {
        if (this.e) {
            graphics.drawImage(this.q, 0, 0, this);
        }
    }
    
    public final boolean mouseDrag(final Event event, final int a, final int n) {
        if (this.r) {
            return true;
        }
        if (this.e) {
            this.o = a;
            this.k = 2;
            this.t = this.s + a - this.a;
            if (this.i == 4) {
                System.out.println("Trable2");
            }
            int n2;
            if (this.w.elementAt(this.i).startsWith("img:")) {
                final Image r;
                n2 = (r = this.q.r(this.w.elementAt(this.i).substring(4), 0 != 0)).getWidth(null);
                r.getHeight(null);
            }
            else {
                n2 = this.o.elementAt(this.y).stringWidth(this.w.elementAt(this.y));
            }
            if (this.t + n2 <= 0) {
                this.t = this.t + n2 + this.j;
                this.s = this.t;
                this.a = a;
                this.q(this.y + 1, false);
                if (this.y > this.e) {
                    this.q(1, false);
                    this.t = this.q;
                    this.s = this.t;
                    this.a = a;
                }
            }
            if (this.t - this.j > 0) {
                this.q(this.y - 1, false);
                if (this.y == 0) {
                    this.q(0, false);
                    if (this.t > this.q) {
                        this.y = this.e;
                        this.t = n2 * -1;
                        this.s = this.t;
                        this.a = a;
                    }
                }
                else if (this.y < 0) {
                    this.q(0, false);
                }
                else {
                    this.t = this.t - this.j - n2;
                    this.s = this.t;
                    this.a = a;
                }
            }
            this.w(false);
        }
        return true;
    }
    
    private synchronized void w(final boolean b) {
        if (this.e < 0 || this.w == null) {
            return;
        }
        this.w.setColor(this.q);
        this.w.fillRect(0, 0, this.q, this.w);
        this.w.setColor(this.q.darker());
        this.w.drawRect(0, 0, this.q - 1, this.w - 1);
        this.u = this.t;
        this.w(this.y, true);
        this.q = false;
        boolean b2 = true;
        do {
            final boolean b3 = this.q.elementAt(this.i) == 1;
            int n;
            if (this.w.elementAt(this.i).startsWith("img:")) {
                final Image r;
                n = (r = this.q.r(this.w.elementAt(this.i).substring(4), 0 != 0)).getWidth(null);
                this.w.drawImage(r, this.u, (this.w - r.getHeight(null)) / 2, null);
            }
            else {
                this.w.setFont(this.i.elementAt(this.i));
                n = this.o.elementAt(this.i).stringWidth(this.w.elementAt(this.i));
                if (this.r) {
                    this.u -= n + this.j;
                }
                this.w.setColor(this.u.elementAt(this.i));
                this.w.fillRect(this.u, 1, n, this.w - 2);
                if (this.r == 1 && this.o >= this.u && this.o <= this.u + n && b3) {
                    this.w.setColor(this.y.elementAt(this.i));
                    this.q = true;
                    this.d = this.i;
                }
                else {
                    final Color color;
                    if ((color = this.t.elementAt(this.i)).getRGB() != Color.black.getRGB()) {
                        this.w.setColor(color);
                    }
                    else if (b3) {
                        this.w.setColor(Color.blue);
                    }
                    else {
                        this.w.setColor(Color.black);
                    }
                }
                this.w.drawString(this.w.elementAt(this.i), this.u, this.x);
                if (b3) {
                    final int n2 = this.x + 1;
                    this.w.drawLine(this.u, n2, this.u + n, n2);
                }
            }
            if (!this.r) {
                this.u = this.u + n + this.j;
            }
            if (this.u >= this.q || this.u <= 0) {
                break;
            }
            if (this.i + 1 > this.e) {
                b2 = false;
            }
            this.w(this.i + 1, true);
        } while (b2 && !b);
        if (this.q && (!this.w || this.f != this.d)) {
            this.q.setCursor(12);
            this.w = true;
        }
        if (!this.q && (this.w || this.f != this.d)) {
            this.q.setCursor(0);
            this.w = false;
        }
        this.f = this.d;
        if (this.r.equals("yes")) {
            this.w.setColor(this.w);
            this.w.drawRect(0, 0, this.q - 1, this.w - 1);
        }
        this.q.drawImage(this.w, 0, 0, null);
        this.i = this.q(this.i);
        this.repaint();
    }
    
    public final boolean mouseDown(final Event event, final int a, final int n) {
        if (this.e) {
            if (!this.w.equals("stop")) {
                this.p = 1;
            }
            this.k = 1;
            this.a = a;
            this.s = this.t;
        }
        return true;
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.e) {
            if (!this.w.equals("stop")) {
                this.p = 0;
            }
            if (this.q.elementAt(this.f) == 0 || !this.q) {
                this.k = 0;
            }
            else {
                if (this.k == 1 || Math.abs(n - this.a) < 20) {
                    this.k = 0;
                    try {
                        if (this.e.elementAt(this.f).startsWith("http://") || this.e.elementAt(this.f).startsWith("mailto:")) {
                            this.q = new URL(this.e.elementAt(this.f));
                        }
                        else {
                            this.q = new URL(AppletAbstract.q().getCodeBase(), this.e.elementAt(this.f));
                        }
                    }
                    catch (Exception ex) {
                        return true;
                    }
                    AppletAbstract.q().getAppletContext().showDocument(this.q, this.e);
                }
                this.k = 0;
            }
        }
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int o, final int n) {
        if (this.e) {
            this.r = 1;
            this.o = o;
            if (this.w.equals("stop")) {
                this.w(false);
            }
        }
        return false;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.e) {
            if (this.w.equals("slow")) {
                this.h = this.g << 1;
            }
            if (this.w.equals("fast")) {
                this.h = this.g / 2;
            }
            if (this.w.equals("stop")) {
                this.p = 1;
            }
            this.r = 1;
            this.repaint();
        }
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.e) {
            this.h = this.g;
            if (this.w.equals("stop")) {
                this.p = 0;
            }
            this.r = 0;
            this.o = -1;
            if (this.q != null) {
                this.q.setCursor(0);
            }
            this.repaint();
        }
        return true;
    }
    
    private synchronized void q(final int y, final boolean b) {
        this.y = y;
    }
    
    private synchronized void w(final int n, final boolean b) {
        this.i = this.q(n);
    }
    
    private synchronized int q(int n) {
        if (n > this.e || n < 0) {
            n = 0;
        }
        return n;
    }
    
    public final void w() {
        if (this.q == null) {
            (this.q = new Thread(this, "Scroline")).start();
            return;
        }
        this.q.resume();
    }
    
    public final void e() {
        if (this.q != null) {
            this.q.stop();
            this.q = null;
        }
    }
    
    public final void run() {
        while (true) {
            final int width = this.getSize().width;
            this.q = width;
            if (width != 0) {
                break;
            }
            try {
                Thread.sleep(30L);
            }
            catch (Exception ex) {}
        }
        this.t();
        this.i();
        this.t = this.q;
        if (this.r) {
            this.t = 0;
        }
        this.q(0, false);
        while (true) {
            this.e = this.w.size() - 1;
            if (this.e >= 0) {
                if (this.getSize().width != this.q) {
                    this.t();
                    this.y();
                    this.u();
                }
                while (this.p == 1) {}
                if (!this.r) {
                    --this.t;
                }
                else {
                    ++this.t;
                }
                int n;
                if (this.w.elementAt(this.y).startsWith("img:")) {
                    n = this.q.r(this.w.elementAt(this.y).substring(4), false).getWidth(null);
                }
                else {
                    n = this.o.elementAt(this.y).stringWidth(this.w.elementAt(this.y));
                }
                boolean b = false;
                if (!this.r) {
                    if (this.t + n <= 0) {
                        this.t = this.t + n + this.j;
                        this.q(this.y + 1, false);
                        if (this.y > this.e) {
                            this.q(0, false);
                            this.t = this.q;
                        }
                    }
                }
                else if (this.t - n - this.j >= this.q) {
                    this.t = this.t - n - this.j;
                    b = true;
                    this.q(this.y + 1, false);
                    if (this.y > this.e) {
                        this.q(0, false);
                        this.t = 0;
                    }
                }
                this.w(b);
            }
            try {
                Thread.sleep(this.h);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    private aY q(final String q) {
        final aY ay = new aY(this);
        int n = 0;
        String sval = null;
        String sval2 = null;
        StreamTokenizer streamTokenizer = null;
        try {
            streamTokenizer = new StreamTokenizer(new StringReader(q));
        }
        catch (Exception ex) {
            System.err.println("Error [2]: " + ex);
            System.exit(1);
        }
        streamTokenizer.quoteChar(39);
        try {
            for (int n2 = streamTokenizer.nextToken(); n2 != 10 && n2 != -1; n2 = streamTokenizer.nextToken()) {
                final String sval3;
                if ((sval3 = streamTokenizer.sval) != null) {
                    if (sval3.equalsIgnoreCase("href") || sval3.equalsIgnoreCase("src")) {
                        if (streamTokenizer.nextToken() == 61) {
                            streamTokenizer.nextToken();
                            sval = streamTokenizer.sval;
                        }
                    }
                    else if (sval3.equalsIgnoreCase("target")) {
                        if (streamTokenizer.nextToken() == 61) {
                            streamTokenizer.nextToken();
                            final String sval4 = streamTokenizer.sval;
                        }
                    }
                    else if (sval3.equalsIgnoreCase("a")) {
                        n = 1;
                    }
                    else if (sval3.equalsIgnoreCase("img")) {
                        n = 2;
                    }
                    else if (sval3.equalsIgnoreCase("value") && streamTokenizer.nextToken() == 61) {
                        streamTokenizer.nextToken();
                        sval2 = streamTokenizer.sval;
                    }
                }
            }
        }
        catch (IOException ex2) {
            System.err.println("Error [3]: " + ex2);
        }
        if (n == 1 && sval2 != null) {
            ay.q = sval2;
            if (sval != null) {
                ay.w = sval;
                ay.q = new Color(9811440);
            }
        }
        else if (n == 2 && sval != null) {
            ay.q = "img:" + sval;
        }
        else {
            ay.q = q;
        }
        return ay;
    }
    
    public final String q(final Object o) {
        if (this.q) {
            return this.r.elementAt(this.d);
        }
        return "";
    }
    
    public final void q(int g) {
        if (g == 0) {
            g = 20;
        }
        this.g = g;
        this.h = this.g;
    }
    
    public final Dimension size() {
        final int width = super.size().width;
        int n = this.x + 4;
        if (this.x == 0) {
            n = 0;
        }
        return new Dimension(width, n);
    }
    
    public final Dimension preferredSize() {
        return this.size();
    }
    
    public final Dimension minimumSize() {
        return this.size();
    }
}
