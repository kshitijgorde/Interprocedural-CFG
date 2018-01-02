// 
// Decompiled by Procyon v0.5.30
// 

package de.ts.applets;

import java.awt.event.MouseEvent;
import de.ts.b.a.i;
import java.awt.Point;
import java.awt.image.ImageObserver;
import de.ts.b.a.f;
import de.ts.b.a.d;
import de.ts.b.a.k;
import de.ts.b.a.c;
import de.ts.b.a.m;
import de.ts.b.a.g;
import de.ts.b.a.b;
import java.awt.Component;
import de.ts.b.a.h;
import de.ts.b.a.a;
import java.util.Stack;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.awt.MediaTracker;
import java.net.URL;
import java.util.Vector;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import de.ts.b.a.l;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

public class MultiBoard extends Applet implements Runnable, MouseListener, MouseMotionListener
{
    private static final int l = 0;
    private static final int null = 1;
    private static final int c = 2;
    int q;
    int width;
    int height;
    boolean p;
    boolean s;
    Image f;
    Graphics b;
    String case;
    int u;
    long byte;
    short void;
    int g;
    l j;
    Font for;
    FontMetrics try;
    String r;
    int i;
    int a;
    int w;
    int e;
    Color A;
    Color h;
    Color[] t;
    Color y;
    long d;
    Thread k;
    Vector do;
    int D;
    int[] if;
    URL goto;
    String long;
    Image o;
    int char;
    boolean else;
    int n;
    int new;
    Vector C;
    MediaTracker z;
    Vector int;
    MediaTracker x;
    int m;
    int v;
    boolean B;
    
    public MultiBoard() {
        this.q = 0;
        this.width = 0;
        this.height = 0;
        this.p = true;
        this.s = false;
        this.f = null;
        this.b = null;
        this.case = null;
        this.u = 0;
        this.byte = 0L;
        this.void = 0;
        this.g = 0;
        this.j = null;
        this.for = null;
        this.try = null;
        this.r = null;
        this.i = 0;
        this.a = 0;
        this.w = 0;
        this.e = 0;
        this.A = null;
        this.h = null;
        this.t = null;
        this.y = null;
        this.d = 0L;
        this.k = null;
        this.do = null;
        this.D = 0;
        this.if = null;
        this.goto = null;
        this.long = null;
        this.o = null;
        this.char = 0;
        this.else = false;
        this.n = 0;
        this.new = 0;
        this.C = null;
        this.z = null;
        this.int = null;
        this.x = null;
        this.m = 0;
        this.v = 0;
        this.B = false;
    }
    
    private void a(final int u) {
        this.u = u;
        this.d = 0L;
    }
    
    private void a(final int u, final long d) {
        this.u = u;
        this.d = d;
    }
    
    private void a(final long byte1) {
        this.byte = byte1;
        this.void = 0;
    }
    
    private void a(final long byte1, final short void1) {
        this.byte = byte1;
        this.void = void1;
    }
    
    private void a(final Font for1) {
        this.for = for1;
        this.try = this.getFontMetrics(this.for);
    }
    
    private void if(final Color h) {
        this.h = h;
    }
    
    private void a(final String r) {
        this.r = r;
    }
    
    private void a(final Font font, final Color color, final String s) {
        this.a(font);
        this.if(color);
        this.a(s);
    }
    
    private void a(final Color a) {
        this.A = a;
    }
    
    private void a(final URL goto1, final String long1) {
        this.goto = goto1;
        this.long = long1;
    }
    
    private void a(final Image o) {
        this.o = o;
    }
    
    private void do() {
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        this.f = this.createImage(this.width, this.height);
        this.b = this.f.getGraphics();
        this.a(4);
        this.a(new Font("TimesRoman", 1, 16), Color.black, "this is a fading text ...");
        this.a(Color.gray);
        this.if = new int[6];
        for (int i = 0; i <= 5; ++i) {
            this.if[i] = 0;
        }
    }
    
    private DataInputStream int() {
        this.case = this.getParameter("DATFILE");
        DataInputStream dataInputStream = null;
        if (this.case != null) {
            final String concat = String.valueOf(this.getCodeBase()).concat(String.valueOf(this.case));
            if (this.p && concat.indexOf("file") > 0) {
                this.b = null;
            }
            try {
                dataInputStream = new DataInputStream(new BufferedInputStream(new URL(concat).openConnection().getInputStream()));
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace(System.err);
            }
            catch (IOException ex2) {
                ex2.printStackTrace(System.err);
            }
        }
        return dataInputStream;
    }
    
    private void if() {
        final Stack stack = new Stack<Integer>();
        for (int i = 0; i < this.do.capacity(); ++i) {
            final a a = this.do.elementAt(i);
            if (a instanceof h) {
                stack.push(new Integer(((h)a).long()));
            }
            else {
                while (!stack.isEmpty()) {
                    this.if[stack.pop()] = i;
                }
            }
        }
        this.D = this.if[0];
    }
    
    private void for() {
        this.C = new Vector(10, 10);
        this.z = new MediaTracker(this);
        int n = 0;
        for (int i = 0; i < this.do.capacity(); ++i) {
            final a a = this.do.elementAt(i);
            if (a instanceof b) {
                final b b = (b)a;
                final Image image = this.getImage(this.getCodeBase(), b.void());
                this.C.addElement(image);
                this.z.addImage(image, n);
                b.a(n++);
            }
        }
        this.C.trimToSize();
    }
    
    private void new() {
    }
    
    private boolean a() {
        boolean b = false;
        while (this.D < this.do.capacity() && !b) {
            final a a = this.do.elementAt(this.D++);
            if (a instanceof g) {
                this.a(((g)a).new());
            }
            else if (a instanceof m) {
                this.if(((m)a).new());
            }
            else if (a instanceof c) {
                this.a(((c)a).if());
            }
            else if (a instanceof l) {
                final l j = (l)a;
                this.a(j.c());
                this.i = 0;
                this.a = 0;
                if (this.try != null) {
                    if (this.r != null) {
                        this.i = this.try.stringWidth(this.r);
                    }
                    this.a = this.try.getHeight();
                }
                j.a(this.i, this.a);
                final Point if1 = j.if(this.width, this.height);
                this.w = if1.x;
                this.e = if1.y + this.a;
                this.j = j;
            }
            else if (a instanceof k) {
                final k k = (k)a;
                this.a(k.e(), k.d());
            }
            else if (a instanceof d) {
                b = true;
                final d d = (d)a;
                this.a(d.do());
                this.a(d.int(), d.for());
            }
            else if (a instanceof f) {
                final f f = (f)a;
                if (!f.char()) {
                    continue;
                }
                this.D = f.else();
            }
            else if (a instanceof b) {
                final b b2 = (b)a;
                final Image image = this.C.elementAt(b2.null());
                this.a(image);
                this.char = b2.null();
                try {
                    this.z.waitForID(this.char);
                }
                catch (InterruptedException ex) {
                    ex.printStackTrace(System.err);
                }
                b2.a(image.getWidth(this), image.getHeight(this));
                final Point if2 = b2.if(this.width, this.height);
                this.n = if2.x;
                this.new = if2.y;
                this.B = b2.b();
            }
            else {
                if (a instanceof h) {
                    final h h = (h)a;
                    break;
                }
                continue;
            }
        }
        return b;
    }
    
    private void if(final int n) {
        if (this.if[n] > 0) {
            this.D = this.if[n];
            this.k.resume();
        }
    }
    
    private void try() throws InterruptedException {
        final int w = this.w;
        final int e = this.e;
        if (!this.s) {
            this.u = 4;
        }
        switch (this.u) {
            case 1: {
                this.a(this.A, this.h);
                final long n = this.byte / this.g;
                for (int i = 0; i < this.g; ++i) {
                    this.y = this.t[i];
                    this.repaint();
                    Thread.sleep(n);
                }
                break;
            }
            case 2: {
                this.a(this.h, this.A);
                final long n2 = this.byte / this.g;
                for (int j = 0; j < this.g; ++j) {
                    this.y = this.t[j];
                    this.repaint();
                    Thread.sleep(n2);
                }
                break;
            }
            case 3: {
                Thread.sleep(this.byte);
                break;
            }
            case 4: {
                this.y = this.h;
                this.repaint();
                break;
            }
            case 5: {
                this.y = this.A;
                this.repaint();
                break;
            }
            case 6: {
                this.y = this.h;
                final int width = this.width;
                final int n3 = this.i * -1;
                final long n4 = this.byte / Math.max(width - n3, 1);
                this.w = width;
                while (this.w >= n3) {
                    this.repaint();
                    Thread.sleep(n4);
                    --this.w;
                }
                this.w = w;
                break;
            }
            case 7: {
                this.y = this.h;
                final int width2 = this.width;
                final int w2 = this.w;
                final long n5 = this.byte / Math.max(width2 - w2, 1);
                this.w = width2;
                while (this.w >= w2) {
                    this.repaint();
                    Thread.sleep(n5);
                    --this.w;
                }
                this.w = w;
                break;
            }
            case 8: {
                this.y = this.h;
                final int w3 = this.w;
                final int n6 = this.i * -1;
                final long n7 = this.byte / Math.max(w3 - n6, 1);
                this.w = this.w;
                while (this.w >= n6) {
                    this.repaint();
                    Thread.sleep(n7);
                    --this.w;
                }
                this.w = w;
                break;
            }
            case 9: {
                this.y = this.h;
                final int e2 = 0;
                final int n8 = this.height + this.a;
                final long n9 = this.byte / Math.max(n8 - e2, 1);
                this.e = e2;
                while (this.e <= n8) {
                    this.repaint();
                    Thread.sleep(n9);
                    ++this.e;
                }
                this.e = e;
                break;
            }
            case 10: {
                this.y = this.h;
                final int e3 = 0;
                final int e4 = this.e;
                final long n10 = this.byte / Math.max(e4 - e3, 1);
                this.e = e3;
                while (this.e <= e4) {
                    this.repaint();
                    Thread.sleep(n10);
                    ++this.e;
                }
                this.e = e;
                break;
            }
            case 11: {
                this.y = this.h;
                final int e5 = this.e;
                final int n11 = this.height + this.a;
                final long n12 = this.byte / Math.max(n11 - e5, 1);
                this.e = e5;
                while (this.e <= n11) {
                    this.repaint();
                    Thread.sleep(n12);
                    ++this.e;
                }
                this.e = e;
                break;
            }
            case 12: {
                this.y = this.h;
                final int w4 = this.i * -1;
                final int width3 = this.width;
                final long n13 = this.byte / Math.max(width3 - w4, 1);
                this.w = w4;
                while (this.w <= width3) {
                    this.repaint();
                    Thread.sleep(n13);
                    ++this.w;
                }
                this.w = w;
                break;
            }
            case 13: {
                this.y = this.h;
                final int w5 = this.i * -1;
                final int w6 = this.w;
                final long n14 = this.byte / Math.max(w6 - w5, 1);
                this.w = w5;
                while (this.w <= w6) {
                    this.repaint();
                    Thread.sleep(n14);
                    ++this.w;
                }
                this.w = w;
                break;
            }
            case 14: {
                this.y = this.h;
                final int w7 = this.w;
                final int width4 = this.width;
                final long n15 = this.byte / Math.max(width4 - w7, 1);
                this.w = this.w;
                while (this.w <= width4) {
                    this.repaint();
                    Thread.sleep(n15);
                    ++this.w;
                }
                this.w = w;
                break;
            }
            case 15: {
                this.y = this.h;
                final int e6 = this.height + this.a;
                final int n16 = 0;
                final long n17 = this.byte / Math.max(e6 - n16, 1);
                this.e = e6;
                while (this.e >= n16) {
                    this.repaint();
                    Thread.sleep(n17);
                    --this.e;
                }
                this.e = e;
                break;
            }
            case 16: {
                this.y = this.h;
                final int e7 = this.height + this.a;
                final int e8 = this.e;
                final long n18 = this.byte / Math.max(e7 - e8, 1);
                this.e = e7;
                while (this.e >= e8) {
                    this.repaint();
                    Thread.sleep(n18);
                    --this.e;
                }
                this.e = e;
                break;
            }
            case 17: {
                this.y = this.h;
                final int e9 = this.e;
                final int n19 = 0;
                final long n20 = this.byte / Math.max(e9 - n19, 1);
                this.e = e9;
                while (this.e >= n19) {
                    this.repaint();
                    Thread.sleep(n20);
                    --this.e;
                }
                this.e = e;
                break;
            }
            case 18: {
                this.else = true;
                this.repaint();
                break;
            }
            case 19: {
                this.else = false;
                this.repaint();
                break;
            }
            case 20: {
                this.y = this.h;
                final int n21 = 0;
                final int size = this.for.getSize();
                final long n22 = this.byte / Math.max(size, 1);
                final Font for1 = this.for;
                for (int k = n21; k <= size; ++k) {
                    this.a(new Font(this.for.getName(), this.for.getStyle(), k));
                    if (this.try != null) {
                        if (this.r != null) {
                            this.i = this.try.stringWidth(this.r);
                        }
                        this.a = this.try.getHeight();
                    }
                    final Point a = this.j.a(this.width, this.height, this.i, this.a);
                    this.w = a.x;
                    this.e = a.y + this.a;
                    this.repaint();
                    Thread.sleep(n22);
                }
                this.a(for1);
                break;
            }
            case 21: {
                this.y = this.h;
                final int size2 = this.for.getSize();
                final int n23 = 0;
                final long n24 = this.byte / Math.max(size2, 1);
                final Font for2 = this.for;
                for (int l = size2; l >= n23; --l) {
                    this.a(new Font(this.for.getName(), this.for.getStyle(), l));
                    if (this.try != null) {
                        if (this.r != null) {
                            this.i = this.try.stringWidth(this.r);
                        }
                        this.a = this.try.getHeight();
                    }
                    final Point a2 = this.j.a(this.width, this.height, this.i, this.a);
                    this.w = a2.x;
                    this.e = a2.y + this.a;
                    Thread.sleep(n24);
                    this.repaint();
                }
                this.a(for2);
                break;
            }
        }
    }
    
    private void a(final Color color, final Color color2) {
        this.g = Math.max((int)(this.void * this.byte) / 1000, 1);
        this.t = new Color[this.g];
        for (int i = 0; i < this.g; ++i) {
            final float n = (i + 1) / this.g;
            this.t[i] = new Color(color.getRed() + (int)((color2.getRed() - color.getRed()) * n), color.getGreen() + (int)((color2.getGreen() - color.getGreen()) * n), color.getBlue() + (int)((color2.getBlue() - color.getBlue()) * n));
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.b.setColor(this.A);
        this.b.fillRect(0, 0, this.width, this.height);
        if (this.else && this.o != null) {
            if (!this.B) {
                this.b.drawImage(this.o, this.n, this.new, this);
            }
            else {
                this.b.drawImage(this.o, 0, 0, this.width, this.height, this);
            }
        }
        if (this.r != null) {
            this.b.setColor(this.y);
            this.b.setFont(this.for);
            this.b.drawString(this.r, this.w, this.e);
            graphics.drawImage(this.f, 0, 0, this);
        }
    }
    
    public void init() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        final String parameter = this.getParameter("KEY");
        final String parameter2 = this.getParameter("LICENSE");
        final URL codeBase = this.getCodeBase();
        if (codeBase.getProtocol().equals("file")) {
            this.s = true;
        }
        else {
            this.p = false;
            this.s = new de.ts.a.a("MB1", parameter2, codeBase.getHost(), codeBase.getFile()).a(this, parameter2, parameter);
        }
        this.do();
        if (this.q == 0) {
            final DataInputStream int1 = this.int();
            if (int1 != null) {
                this.q = 1;
                this.do = new i(int1).a();
                this.if();
                this.for();
                this.new();
            }
            else {
                this.q = 2;
            }
        }
    }
    
    public void destroy() {
        this.b.dispose();
    }
    
    public void start() {
        if (this.k == null) {
            (this.k = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.k != null) {
            this.k.stop();
            this.k = null;
        }
    }
    
    public void run() {
        switch (this.q) {
            case 1: {
                Label_0024: {
                    break Label_0024;
                    try {
                        while (true) {
                            if (this.a()) {
                                this.try();
                            }
                            else {
                                this.k.suspend();
                            }
                        }
                    }
                    catch (InterruptedException ex) {
                        ex.printStackTrace(System.err);
                    }
                }
                break;
            }
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.if(1);
        if (this.goto != null) {
            this.getAppletContext().showDocument(this.goto, this.long);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.if(2);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.if(3);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.if(4);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.if(5);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public String getAppletInfo() {
        return "MultiBoard V1.0.1  (c) 1999 by TS";
    }
}
