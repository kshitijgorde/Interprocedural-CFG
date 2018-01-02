// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
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

public final class ax extends Canvas implements bf, Runnable
{
    public Thread q;
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
    public int q;
    public int w;
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
    private boolean w;
    private boolean e;
    private boolean r;
    private Frame q;
    private int l;
    private int z;
    private ap q;
    private int x;
    public boolean q;
    
    public ax(final ap q) {
        this.x = 0;
        this.q = false;
        this.q = q;
        this.q = bC.w.n;
        this.q();
    }
    
    public void q() {
        this.a = -1;
        this.g = 1;
        this.q = "";
        this.w = "stop";
        this.e = "";
        this.r = "";
        this.w = false;
        this.e = false;
        this.r = false;
        this.i = 0;
        this.p = 0;
        this.x = 0;
        this.e = -1;
        this.r = -1;
        this.r = false;
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
        this.q = q("16", 10, 16);
        this.w = this.q;
        this.w = new Color(q("990000", 16, 0));
        this.t = 0;
        this.w = new Vector();
        this.e = new Vector();
        this.r = new Vector();
        this.t = new Vector();
        this.y = new Vector();
        this.u = new Vector();
        this.q = new Vector();
        this.i = new Vector();
        this.o = new Vector();
        this.e = this.size().width;
        this.r = this.size().height;
        this.q = (Frame)bq.q(this, !ap.t());
        this.r = true;
    }
    
    public final void q(final String s, final aR ar) {
        if (ar.q(1) || ar.q(2)) {
            if (this.q.a == 2 && ar.q(1)) {
                return;
            }
            if (this.q.q(61) && ar.q(2)) {
                return;
            }
        }
        final al q = this.q(s);
        this.w.addElement(q.q);
        String w = q.w;
        if (q.w == null || w.trim().equals("")) {
            w = "";
            this.q.addElement(new Integer(0));
            this.r.addElement("");
        }
        else {
            this.q.addElement(new Integer(1));
            this.r.addElement(B.q(be.w("Follow link %1"), w));
        }
        this.e.addElement(w);
        final Font font = new Font(ar.q, ar.e(), ar.w);
        this.i.addElement(font);
        this.o.addElement(this.getFontMetrics(font));
        this.t.addElement(new Color(ar.w()));
        this.y.addElement(q.q);
        this.u.addElement(new Color(ar.g));
        if (this.x < font.getSize()) {
            this.x = font.getSize();
            this.q(this.x + 4);
        }
    }
    
    public void q(final int r) {
        this.invalidate();
        this.r = r;
        this.y();
        this.q.validate();
    }
    
    private void e() {
        if (this.e != 0) {
            this.l = this.e;
        }
        if (this.r != 0) {
            this.z = this.r;
        }
        final Dimension size = this.size();
        this.e = size.width;
        this.r = size.height;
    }
    
    private void r() {
        if (this.e == 0 || this.r == 0) {
            return;
        }
        this.q = dN.q(this, this.e, this.r, "sl.r-q");
        (this.q = this.q.getGraphics()).clipRect(0, 0, this.e, this.r);
    }
    
    private void t() {
        if (this.e == 0 || this.r == 0) {
            return;
        }
        this.w = dN.q(this, this.e, this.r, "sl.r-p");
        (this.w = this.w.getGraphics()).clipRect(0, 0, this.e, this.r);
    }
    
    private void y() {
        if (this.q == null || this.e != this.l || this.r != this.z) {
            this.r();
        }
        if (this.w == null || this.e != this.l || this.r != this.z) {
            this.t();
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
        if (this.r) {
            this.paint(graphics);
        }
    }
    
    public final void paint(final Graphics graphics) {
        if (this.r) {
            graphics.drawImage(this.q, 0, 0, this);
        }
    }
    
    public final boolean mouseDrag(final Event event, final int d, final int n) {
        if (this.q) {
            return true;
        }
        if (this.r) {
            this.a = d;
            this.k = 2;
            this.u = this.f + d - this.d;
            if (this.p == 4) {
                System.out.println("Trable2");
            }
            int n2;
            if (this.w.elementAt(this.p).startsWith("img:")) {
                final Image e;
                n2 = (e = this.q.e(this.w.elementAt(this.p).substring(4), 0 != 0)).getWidth(null);
                e.getHeight(null);
            }
            else {
                n2 = this.o.elementAt(this.i).stringWidth(this.w.elementAt(this.i));
            }
            if (this.u + n2 <= 0) {
                this.u = this.u + n2 + this.j;
                this.f = this.u;
                this.d = d;
                this.q(this.i + 1, false);
                if (this.i > this.t) {
                    this.q(1, false);
                    this.u = this.e;
                    this.f = this.u;
                    this.d = d;
                }
            }
            if (this.u - this.j > 0) {
                this.q(this.i - 1, false);
                if (this.i == 0) {
                    this.q(0, false);
                    if (this.u > this.e) {
                        this.i = this.t;
                        this.u = n2 * -1;
                        this.f = this.u;
                        this.d = d;
                    }
                }
                else if (this.i < 0) {
                    this.q(0, false);
                }
                else {
                    this.u = this.u - this.j - n2;
                    this.f = this.u;
                    this.d = d;
                }
            }
            this.q(false);
        }
        return true;
    }
    
    private synchronized void q(final boolean b) {
        if (this.t < 0 || this.w == null) {
            return;
        }
        this.w.setColor(this.q);
        this.w.fillRect(0, 0, this.e, this.r);
        this.w.setColor(this.q.darker());
        this.w.drawRect(0, 0, this.e - 1, this.r - 1);
        this.o = this.u;
        this.w(this.i, true);
        this.w = false;
        boolean b2 = true;
        do {
            final boolean b3 = this.q.elementAt(this.p) == 1;
            int n;
            if (this.w.elementAt(this.p).startsWith("img:")) {
                final Image e;
                n = (e = this.q.e(this.w.elementAt(this.p).substring(4), 0 != 0)).getWidth(null);
                this.w.drawImage(e, this.o, (this.r - e.getHeight(null)) / 2, null);
            }
            else {
                this.w.setFont(this.i.elementAt(this.p));
                n = this.o.elementAt(this.p).stringWidth(this.w.elementAt(this.p));
                if (this.q) {
                    this.o -= n + this.j;
                }
                this.w.setColor(this.u.elementAt(this.p));
                this.w.fillRect(this.o, 1, n, this.r - 2);
                if (this.y == 1 && this.a >= this.o && this.a <= this.o + n && b3) {
                    this.w.setColor(this.y.elementAt(this.p));
                    this.w = true;
                    this.g = this.p;
                }
                else {
                    final Color color;
                    if ((color = this.t.elementAt(this.p)).getRGB() != Color.black.getRGB()) {
                        this.w.setColor(color);
                    }
                    else if (b3) {
                        this.w.setColor(Color.blue);
                    }
                    else {
                        this.w.setColor(Color.black);
                    }
                }
                this.w.drawString(this.w.elementAt(this.p), this.o, this.x);
                if (b3) {
                    final int n2 = this.x + 1;
                    this.w.drawLine(this.o, n2, this.o + n, n2);
                }
            }
            if (!this.q) {
                this.o = this.o + n + this.j;
            }
            if (this.o >= this.e || this.o <= 0) {
                break;
            }
            if (this.p + 1 > this.t) {
                b2 = false;
            }
            this.w(this.p + 1, true);
        } while (b2 && !b);
        if (this.w && (!this.e || this.h != this.g)) {
            this.q.setCursor(12);
            this.e = true;
        }
        if (!this.w && (this.e || this.h != this.g)) {
            this.q.setCursor(0);
            this.e = false;
        }
        this.h = this.g;
        if (this.r.equals("yes")) {
            this.w.setColor(this.w);
            this.w.drawRect(0, 0, this.e - 1, this.r - 1);
        }
        this.q.drawImage(this.w, 0, 0, null);
        this.p = this.q(this.p);
        this.repaint();
    }
    
    public final boolean mouseDown(final Event event, final int d, final int n) {
        if (this.r) {
            if (!this.w.equals("stop")) {
                this.s = 1;
            }
            this.k = 1;
            this.d = d;
            this.f = this.u;
        }
        return true;
    }
    
    public final boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.r) {
            if (!this.w.equals("stop")) {
                this.s = 0;
            }
            if (this.q.elementAt(this.h) == 0 || !this.w) {
                this.k = 0;
            }
            else {
                if (this.k == 1 || Math.abs(n - this.d) < 20) {
                    this.k = 0;
                    try {
                        if (this.e.elementAt(this.h).startsWith("http://") || this.e.elementAt(this.h).startsWith("mailto:")) {
                            this.q = new URL(this.e.elementAt(this.h));
                        }
                        else {
                            this.q = new URL(m.q().getCodeBase(), this.e.elementAt(this.h));
                        }
                    }
                    catch (Exception ex) {
                        return true;
                    }
                    m.q().getAppletContext().showDocument(this.q, this.e);
                }
                this.k = 0;
            }
        }
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int a, final int n) {
        if (this.r) {
            this.y = 1;
            this.a = a;
            if (this.w.equals("stop")) {
                this.q(false);
            }
        }
        return false;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.r) {
            if (this.w.equals("slow")) {
                this.w = this.q << 1;
            }
            if (this.w.equals("fast")) {
                this.w = this.q / 2;
            }
            if (this.w.equals("stop")) {
                this.s = 1;
            }
            this.y = 1;
            this.repaint();
        }
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.r) {
            this.w = this.q;
            if (this.w.equals("stop")) {
                this.s = 0;
            }
            this.y = 0;
            this.a = -1;
            if (this.q != null) {
                this.q.setCursor(0);
            }
            this.repaint();
        }
        return true;
    }
    
    private synchronized void q(final int i, final boolean b) {
        this.i = i;
    }
    
    private synchronized void w(final int n, final boolean b) {
        this.p = this.q(n);
    }
    
    private synchronized int q(int n) {
        if (n > this.t || n < 0) {
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
    
    public final void run() {
        while (true) {
            final int width = this.getSize().width;
            this.e = width;
            if (width != 0) {
                break;
            }
            try {
                Thread.sleep(30L);
            }
            catch (Exception ex) {}
        }
        this.e();
        this.y();
        this.u = this.e;
        if (this.q) {
            this.u = 0;
        }
        this.q(0, false);
        while (true) {
            this.t = this.w.size() - 1;
            if (this.t >= 0) {
                if (this.getSize().width != this.e) {
                    this.e();
                    this.r();
                    this.t();
                }
                if (!this.q) {
                    --this.u;
                }
                else {
                    ++this.u;
                }
                int n;
                if (this.w.elementAt(this.i).startsWith("img:")) {
                    n = this.q.e(this.w.elementAt(this.i).substring(4), false).getWidth(null);
                }
                else {
                    n = this.o.elementAt(this.i).stringWidth(this.w.elementAt(this.i));
                }
                boolean b = false;
                if (!this.q) {
                    if (this.u + n <= 0) {
                        this.u = this.u + n + this.j;
                        this.q(this.i + 1, false);
                        if (this.i > this.t) {
                            this.q(0, false);
                            this.u = this.e;
                        }
                    }
                }
                else if (this.u - n - this.j >= this.e) {
                    this.u = this.u - n - this.j;
                    b = true;
                    this.q(this.i + 1, false);
                    if (this.i > this.t) {
                        this.q(0, false);
                        this.u = 0;
                    }
                }
                this.q(b);
            }
            try {
                Thread.sleep(this.w);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    private al q(final String q) {
        final al al = new al(this, (byte)0);
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
            al.q = sval2;
            if (sval != null) {
                al.w = sval;
                al.q = new Color(9811440);
            }
        }
        else if (n == 2 && sval != null) {
            al.q = "img:" + sval;
        }
        else {
            al.q = q;
        }
        return al;
    }
    
    public final String q(final Object o) {
        if (this.w) {
            return this.r.elementAt(this.g);
        }
        return "";
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
