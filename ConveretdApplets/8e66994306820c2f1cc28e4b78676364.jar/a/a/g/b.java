// 
// Decompiled by Procyon v0.5.30
// 

package a.a.g;

import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.Graphics;
import a.b.o.c.d;
import a.b.o.a.b.e;
import java.awt.Font;
import java.net.URL;
import netscape.javascript.JSException;
import java.awt.LayoutManager;
import a.a.c.c;
import a.b.e.n;
import a.b.e.m;
import a.a.c.i;
import org.a.c.f;
import netscape.javascript.JSObject;
import java.awt.Component;
import java.awt.Image;
import a.b.o.c.a;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.applet.Applet;

public class b extends Applet implements Runnable, a.b.b.b, MouseListener
{
    private static final Cursor a;
    private static final Color b;
    private a c;
    private Image d;
    private boolean e;
    private Color f;
    private Component g;
    private JSObject h;
    private f i;
    private a.b.e.f j;
    private org.a.d.c.b k;
    private String l;
    private org.a.d.c.b m;
    private String n;
    private ThreadGroup o;
    private String p;
    private a.b.m.a q;
    private a.b.m.a r;
    private a.b.m.a s;
    private long t;
    private long u;
    private long v;
    private long w;
    private int x;
    private int y;
    private String z;
    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private static String[] G;
    
    public b() {
        this.l = a.a.g.b.G[13];
        this.n = a.a.g.b.G[10];
        this.p = a.a.g.b.G[11];
        this.t = 3200L;
        this.u = 0L;
        this.v = 5L;
        this.w = 750L;
        this.x = 5;
        this.y = 5;
        this.A = a.a.g.b.G[16];
        this.B = a.a.g.b.G[17];
        this.C = a.a.g.b.G[9];
        this.D = a.a.g.b.G[14];
        this.E = a.a.g.b.G[12];
        this.F = a.a.g.b.G[15];
    }
    
    public void init() {
        a.a.c.i.b(false);
        this.i = a.b.g.b.a().d().a(this.getClass().getName());
        this.e = false;
        this.o = new ThreadGroup(this.p);
        a.b.g.b.a().a(new m(this.o));
        final URL codeBase = this.getCodeBase();
        int port = codeBase.getPort();
        if (port == -1) {
            port = 80;
        }
        a.a.c.c.a(a.a.g.b.G[24], codeBase.getHost());
        a.a.c.c.a(a.a.g.b.G[22], new String("" + port));
        this.i.a(a.a.g.b.G[23] + codeBase.getHost() + ":" + port);
        this.a();
        this.setBackground(this.f);
        this.setLayout(null);
        try {
            this.h = JSObject.getWindow((Applet)this);
        }
        catch (JSException ex) {
            this.i.d(a.a.g.b.G[25], (Throwable)ex);
        }
        catch (NullPointerException ex2) {
            this.i.d(a.a.g.b.G[25], ex2);
        }
    }
    
    public void start() {
        this.e = false;
        this.b();
        this.repaint();
        (this.k = new org.a.d.c.b(this.o, this, this.l)).start();
        try {
            Thread.sleep(350L);
        }
        catch (InterruptedException ex) {}
        this.addMouseListener(this);
    }
    
    public synchronized void stop() {
        this.i.a(a.a.g.b.G[19]);
        final Thread[] array = new Thread[this.o.activeCount()];
        for (int enumerate = this.o.enumerate(array), i = 0; i < enumerate; ++i) {
            final Thread thread = array[i];
            try {
                if (thread instanceof org.a.d.c.b) {
                    ((org.a.d.c.b)thread).a();
                }
            }
            catch (NullPointerException ex) {}
        }
        Thread.currentThread();
        Thread.yield();
    }
    
    public void destroy() {
        final long currentTimeMillis = System.currentTimeMillis();
        while (this.o.activeCount() > 1 && currentTimeMillis - currentTimeMillis < 1000L) {
            try {
                Thread.sleep(25L);
            }
            catch (InterruptedException ex) {}
        }
        this.i.a(a.a.g.b.G[5]);
        try {
            this.o.destroy();
        }
        catch (Exception ex2) {}
    }
    
    public int getHeight() {
        return this.getBounds().height;
    }
    
    public int getWidth() {
        return this.getBounds().width;
    }
    
    public void run() {
        if (Thread.currentThread() instanceof org.a.d.c.b) {
            final org.a.d.c.b b = (org.a.d.c.b)Thread.currentThread();
            if (b.getName().equals(this.l)) {
                this.a(b);
            }
            else if (b.getName().equals(this.n)) {
                this.b(b);
            }
        }
    }
    
    public void a(final org.a.d.c.b b) {
        a.b.o.a.b.c c = null;
        if (c == null || c.a() == null) {
            final Image image = this.createImage(super.getSize().width, super.getSize().height);
            if (image != null) {
                final Graphics graphics = image.getGraphics();
                graphics.setColor(this.f);
                graphics.fillRect(0, 0, super.getSize().width, super.getSize().height);
                c = new a.b.o.a.b.c(image);
            }
        }
        if (c != null && c.a() != null) {
            (this.c = new a(c)).setBounds(0, 0, super.getSize().width, super.getSize().height);
        }
        (this.j = new a.a.c.f()).a(this);
        final a.b.h.c.a a = new a.b.h.c.a(a.a.a.a.b, this.o, this.x, this.y, this.w, this.v);
        a.a(this.j);
        this.add(this.q = new a.b.m.a(a.a.a.a.b, a));
        this.q.setBounds(0, 0, this.getWidth() / 2, this.getHeight() / 3);
        this.q.setCursor(b.a);
        this.q.addMouseListener(this);
        final e e = new e(b.G[8], new Font("", 0, 10), new Color(0, 0, 0));
        e.a(2);
        this.q.a(new d(e));
        final a.b.h.c.a a2 = new a.b.h.c.a(a.a.a.a.a, this.o, this.x, this.y, this.w, this.v);
        a2.a(this.j);
        this.add(this.r = new a.b.m.a(a.a.a.a.a, a2));
        this.r.setBounds(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight() / 3);
        this.r.setCursor(b.a);
        this.r.addMouseListener(this);
        final e e2 = new e(b.G[8], new Font("", 0, 10), new Color(0, 0, 0));
        e2.a(2);
        this.r.a(new d(e2));
        final a.b.h.c.a a3 = new a.b.h.c.a(a.a.a.a.c, this.o, this.x, this.y, this.w, this.v);
        a3.a(this.j);
        this.add(this.s = new a.b.m.a(a.a.a.a.c, a3));
        this.s.setBounds(0, super.getSize().height / 3, super.getSize().width, 2 * super.getSize().height / 3);
        this.s.setCursor(b.a);
        this.s.addMouseListener(this);
        final e e3 = new e(b.G[7], new Font("", 0, 12), new Color(0, 0, 0));
        e3.a(2);
        this.s.a(new d(e3));
        if (this.c != null) {
            this.add(this.c);
        }
        this.repaint();
        try {
            Thread.sleep(1500L);
        }
        catch (InterruptedException ex) {}
        if (!b.b()) {
            this.c();
            this.repaint();
            this.e = true;
            (this.m = new org.a.d.c.b(this.o, this, this.n)).setPriority(6);
            this.m.start();
        }
        this.requestFocus();
    }
    
    public void repaint() {
        super.repaint();
        this.u = System.currentTimeMillis();
    }
    
    public void b(final org.a.d.c.b b) {
        this.repaint();
        this.q.b();
        this.r.b();
        this.s.b();
        while (!b.b()) {
            if (System.currentTimeMillis() > this.u + this.t) {
                this.repaint();
            }
            this.q.a();
            this.r.a();
            this.s.a();
            if (!b.b()) {
                try {
                    Thread.sleep(25L);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    public void update(final Graphics graphics) {
        final Rectangle rectangle = (Rectangle)graphics.getClip();
        final Rectangle rectangle2 = new Rectangle(rectangle.getBounds().x - 8, rectangle.getBounds().y - 8, rectangle.getBounds().width + 16, rectangle.getBounds().height + 16);
        if (this.d == null) {
            try {
                this.d = this.createImage(this.getWidth(), this.getHeight());
            }
            catch (Exception ex) {
                this.i.d(a.a.g.b.G[18], ex);
            }
        }
        graphics.setClip(rectangle2);
        this.d.getGraphics().setClip(rectangle2);
        this.paint(this.d.getGraphics());
        graphics.drawImage(this.d, 0, 0, null);
    }
    
    public void a(final a.b.b.c c) {
        try {
            final a.b.b.e e = (a.b.b.e)c;
            if (e.a() == 358) {
                this.a(e);
            }
            else if (e.a() == 357) {
                this.c(e);
            }
            else if (e.a() == 359) {
                this.b(e);
            }
        }
        catch (ClassCastException ex) {}
    }
    
    public void a(final a.b.b.e e) {
        if (this.h != null) {
            try {
                final String s = (String)e.b();
                if (s != null) {
                    this.h.eval(a.a.g.b.G[1] + s + "\"");
                }
            }
            catch (Exception ex) {
                this.i.d(a.a.g.b.G[0], ex);
            }
        }
    }
    
    public void b(final a.b.b.e e) {
        if (this.h != null) {
            try {
                this.h.eval(a.a.g.b.G[20]);
            }
            catch (Exception ex) {
                this.i.d(a.a.g.b.G[21], ex);
            }
        }
    }
    
    public void c(final a.b.b.e e) {
        if (this.h != null) {
            try {
                this.h.eval(a.a.g.b.G[2] + (String)e.b() + a.a.g.b.G[3]);
            }
            catch (Exception ex) {
                this.i.d(a.a.g.b.G[4], ex);
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.z == null || this.z.length() == 0 || mouseEvent.getSource() instanceof a.b.o.c.i) {
            return;
        }
        this.c(new a.b.b.e(this, 357, System.currentTimeMillis(), this.z));
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    private void a() {
        try {
            this.f = Color.decode(this.getParameter(this.A));
        }
        catch (NumberFormatException ex) {
            this.f = a.a.g.b.b;
        }
        catch (NullPointerException ex2) {
            this.f = a.a.g.b.b;
        }
        try {
            final int intValue = Integer.valueOf(this.getParameter(this.B));
            if (intValue > 0) {
                this.v = intValue;
            }
        }
        catch (NumberFormatException ex3) {}
        catch (NullPointerException ex4) {}
        try {
            final int intValue2 = Integer.valueOf(this.getParameter(this.C));
            if (intValue2 > 0) {
                this.w = intValue2;
            }
        }
        catch (NumberFormatException ex5) {}
        catch (NullPointerException ex6) {}
        try {
            final int intValue3 = Integer.valueOf(this.getParameter(this.D));
            if (intValue3 > 0) {
                this.x = intValue3;
            }
        }
        catch (NumberFormatException ex7) {}
        catch (NullPointerException ex8) {}
        try {
            final int intValue4 = Integer.valueOf(this.getParameter(this.E));
            if (intValue4 > 0) {
                this.y = intValue4;
            }
        }
        catch (NumberFormatException ex9) {}
        catch (NullPointerException ex10) {}
        this.z = this.getParameter(this.F);
    }
    
    private void b() {
        this.setCursor(new Cursor(3));
        final e e = new e(a.a.g.b.G[6], new Font("", 1, 10), new Color(0, 0, 0));
        e.a(2);
        (this.g = new d(e)).setBounds((this.getWidth() - this.g.getBounds().width) / 2, (this.getHeight() - this.g.getBounds().height) / 2, this.g.getBounds().width, this.g.getBounds().height);
        this.add(this.g);
    }
    
    private void c() {
        this.remove(this.g);
        this.setCursor(a.a.g.b.a);
    }
    
    static {
        final String[] g = new String[26];
        final int n = 0;
        final char[] charArray = "cc5+NJp*:I\tf*9MEc:jQ@l(jX[p,8\u0007\t".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = ')';
                    break;
                }
                case 1: {
                    c2 = '\u0002';
                    break;
                }
                case 2: {
                    c2 = 'C';
                    break;
                }
                case 3: {
                    c2 = 'J';
                    break;
                }
                default: {
                    c2 = '=';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        g[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "Zv\">HZ?a".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = ')';
                    break;
                }
                case 1: {
                    c4 = '\u0002';
                    break;
                }
                case 2: {
                    c4 = 'C';
                    break;
                }
                case 3: {
                    c4 = 'J';
                    break;
                }
                default: {
                    c4 = '=';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        g[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "Fr&$\u0015\u000b".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = ')';
                    break;
                }
                case 1: {
                    c6 = '\u0002';
                    break;
                }
                case 2: {
                    c6 = 'C';
                    break;
                }
                case 3: {
                    c6 = 'J';
                    break;
                }
                default: {
                    c6 = '=';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        g[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "\u000b+".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = ')';
                    break;
                }
                case 1: {
                    c8 = '\u0002';
                    break;
                }
                case 2: {
                    c8 = 'C';
                    break;
                }
                case 3: {
                    c8 = 'J';
                    break;
                }
                default: {
                    c8 = '=';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        g[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "cc5+NJp*:I\tj7'Q\tn*$V\tg18R[8c".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = ')';
                    break;
                }
                case 1: {
                    c10 = '\u0002';
                    break;
                }
                case 2: {
                    c10 = 'C';
                    break;
                }
                case 3: {
                    c10 = 'J';
                    break;
                }
                default: {
                    c10 = '=';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        g[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "hr3&X]\"*9\u001dMm-/\u0013".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = ')';
                    break;
                }
                case 1: {
                    c12 = '\u0002';
                    break;
                }
                case 2: {
                    c12 = 'C';
                    break;
                }
                case 3: {
                    c12 = 'J';
                    break;
                }
                default: {
                    c12 = '=';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        g[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "em\".TGem\u0016S\tR//\\Zgc=\\@vm".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = ')';
                    break;
                }
                case 1: {
                    c14 = '\u0002';
                    break;
                }
                case 2: {
                    c14 = 'C';
                    break;
                }
                case 3: {
                    c14 = 'J';
                    break;
                }
                default: {
                    c14 = '=';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        g[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "em\".TGeojMEg\"9X\tu\"#I\u0007".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = ')';
                    break;
                }
                case 1: {
                    c16 = '\u0002';
                    break;
                }
                case 2: {
                    c16 = 'C';
                    break;
                }
                case 3: {
                    c16 = 'J';
                    break;
                }
                default: {
                    c16 = '=';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        g[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "em\".TGey\u0016Syn&+NL\"4+T],".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = ')';
                    break;
                }
                case 1: {
                    c18 = '\u0002';
                    break;
                }
                case 2: {
                    c18 = 'C';
                    break;
                }
                case 3: {
                    c18 = 'J';
                    break;
                }
                default: {
                    c18 = '=';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        g[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "Jj&)V]k./".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = ')';
                    break;
                }
                case 1: {
                    c20 = '\u0002';
                    break;
                }
                case 2: {
                    c20 = 'C';
                    break;
                }
                case 3: {
                    c20 = 'J';
                    break;
                }
                default: {
                    c20 = '=';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        g[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "Mk0:QH{".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = ')';
                    break;
                }
                case 1: {
                    c22 = '\u0002';
                    break;
                }
                case 2: {
                    c22 = 'C';
                    break;
                }
                case 3: {
                    c22 = 'J';
                    break;
                }
                default: {
                    c22 = '=';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        g[n31] = new String(charArray11).intern();
        final int n34 = 11;
        final char[] charArray12 = "dg'#\\np,?M".toCharArray();
        final int length8 = charArray12.length;
        for (int n35 = 0; length8 > n35; ++n35) {
            final int n36 = n35;
            final char c23 = charArray12[n36];
            char c24 = '\0';
            switch (n35 % 5) {
                case 0: {
                    c24 = ')';
                    break;
                }
                case 1: {
                    c24 = '\u0002';
                    break;
                }
                case 2: {
                    c24 = 'C';
                    break;
                }
                case 3: {
                    c24 = 'J';
                    break;
                }
                default: {
                    c24 = '=';
                    break;
                }
            }
            charArray12[n36] = (char)(c23 ^ c24);
        }
        g[n34] = new String(charArray12).intern();
        final int n37 = 12;
        final char[] charArray13 = "\\r0#GL".toCharArray();
        final int length9 = charArray13.length;
        for (int n38 = 0; length9 > n38; ++n38) {
            final int n39 = n38;
            final char c25 = charArray13[n39];
            char c26 = '\0';
            switch (n38 % 5) {
                case 0: {
                    c26 = ')';
                    break;
                }
                case 1: {
                    c26 = '\u0002';
                    break;
                }
                case 2: {
                    c26 = 'C';
                    break;
                }
                case 3: {
                    c26 = 'J';
                    break;
                }
                default: {
                    c26 = '=';
                    break;
                }
            }
            charArray13[n39] = (char)(c25 ^ c26);
        }
        g[n37] = new String(charArray13).intern();
        final int n40 = 13;
        final char[] charArray14 = "@l*>".toCharArray();
        final int length10 = charArray14.length;
        for (int n41 = 0; length10 > n41; ++n41) {
            final int n42 = n41;
            final char c27 = charArray14[n42];
            char c28 = '\0';
            switch (n41 % 5) {
                case 0: {
                    c28 = ')';
                    break;
                }
                case 1: {
                    c28 = '\u0002';
                    break;
                }
                case 2: {
                    c28 = 'C';
                    break;
                }
                case 3: {
                    c28 = 'J';
                    break;
                }
                default: {
                    c28 = '=';
                    break;
                }
            }
            charArray14[n42] = (char)(c27 ^ c28);
        }
        g[n40] = new String(charArray14).intern();
        final int n43 = 14;
        final char[] charArray15 = "]j1/NAm/.".toCharArray();
        final int length11 = charArray15.length;
        for (int n44 = 0; length11 > n44; ++n44) {
            final int n45 = n44;
            final char c29 = charArray15[n45];
            char c30 = '\0';
            switch (n44 % 5) {
                case 0: {
                    c30 = ')';
                    break;
                }
                case 1: {
                    c30 = '\u0002';
                    break;
                }
                case 2: {
                    c30 = 'C';
                    break;
                }
                case 3: {
                    c30 = 'J';
                    break;
                }
                default: {
                    c30 = '=';
                    break;
                }
            }
            charArray15[n45] = (char)(c29 ^ c30);
        }
        g[n43] = new String(charArray15).intern();
        final int n46 = 15;
        final char[] charArray16 = "Ap&,".toCharArray();
        final int length12 = charArray16.length;
        for (int n47 = 0; length12 > n47; ++n47) {
            final int n48 = n47;
            final char c31 = charArray16[n48];
            char c32 = '\0';
            switch (n47 % 5) {
                case 0: {
                    c32 = ')';
                    break;
                }
                case 1: {
                    c32 = '\u0002';
                    break;
                }
                case 2: {
                    c32 = 'C';
                    break;
                }
                case 3: {
                    c32 = 'J';
                    break;
                }
                default: {
                    c32 = '=';
                    break;
                }
            }
            charArray16[n48] = (char)(c31 ^ c32);
        }
        g[n46] = new String(charArray16).intern();
        final int n49 = 16;
        final char[] charArray17 = "Jm/%O".toCharArray();
        final int length13 = charArray17.length;
        for (int n50 = 0; length13 > n50; ++n50) {
            final int n51 = n50;
            final char c33 = charArray17[n51];
            char c34 = '\0';
            switch (n50 % 5) {
                case 0: {
                    c34 = ')';
                    break;
                }
                case 1: {
                    c34 = '\u0002';
                    break;
                }
                case 2: {
                    c34 = 'C';
                    break;
                }
                case 3: {
                    c34 = 'J';
                    break;
                }
                default: {
                    c34 = '=';
                    break;
                }
            }
            charArray17[n51] = (char)(c33 ^ c34);
        }
        g[n49] = new String(charArray17).intern();
        final int n52 = 17;
        final char[] charArray18 = "Gg;>I@o&".toCharArray();
        final int length14 = charArray18.length;
        for (int n53 = 0; length14 > n53; ++n53) {
            final int n54 = n53;
            final char c35 = charArray18[n54];
            char c36 = '\0';
            switch (n53 % 5) {
                case 0: {
                    c36 = ')';
                    break;
                }
                case 1: {
                    c36 = '\u0002';
                    break;
                }
                case 2: {
                    c36 = 'C';
                    break;
                }
                case 3: {
                    c36 = 'J';
                    break;
                }
                default: {
                    c36 = '=';
                    break;
                }
            }
            charArray18[n54] = (char)(c35 ^ c36);
        }
        g[n52] = new String(charArray18).intern();
        final int n55 = 18;
        final char[] charArray19 = "lp1%O\ta1/\\]k--\u001dFd%gNJp&/S\tk.+ZL8c".toCharArray();
        final int length15 = charArray19.length;
        for (int n56 = 0; length15 > n56; ++n56) {
            final int n57 = n56;
            final char c37 = charArray19[n57];
            char c38 = '\0';
            switch (n56 % 5) {
                case 0: {
                    c38 = ')';
                    break;
                }
                case 1: {
                    c38 = '\u0002';
                    break;
                }
                case 2: {
                    c38 = 'C';
                    break;
                }
                case 3: {
                    c38 = 'J';
                    break;
                }
                default: {
                    c38 = '=';
                    break;
                }
            }
            charArray19[n57] = (char)(c37 ^ c38);
        }
        g[n55] = new String(charArray19).intern();
        final int n58 = 19;
        final char[] charArray20 = "hr3&X]\"*9\u001dZv,:M@l$d".toCharArray();
        final int length16 = charArray20.length;
        for (int n59 = 0; length16 > n59; ++n59) {
            final int n60 = n59;
            final char c39 = charArray20[n60];
            char c40 = '\0';
            switch (n59 % 5) {
                case 0: {
                    c40 = ')';
                    break;
                }
                case 1: {
                    c40 = '\u0002';
                    break;
                }
                case 2: {
                    c40 = 'C';
                    break;
                }
                case 3: {
                    c40 = 'J';
                    break;
                }
                default: {
                    c40 = '=';
                    break;
                }
            }
            charArray20[n60] = (char)(c39 ^ c40);
        }
        g[n58] = new String(charArray20).intern();
        final int n61 = 20;
        final char[] charArray21 = "Zv\">HZ?aj\u001f".toCharArray();
        final int length17 = charArray21.length;
        for (int n62 = 0; length17 > n62; ++n62) {
            final int n63 = n62;
            final char c41 = charArray21[n63];
            char c42 = '\0';
            switch (n62 % 5) {
                case 0: {
                    c42 = ')';
                    break;
                }
                case 1: {
                    c42 = '\u0002';
                    break;
                }
                case 2: {
                    c42 = 'C';
                    break;
                }
                case 3: {
                    c42 = 'J';
                    break;
                }
                default: {
                    c42 = '=';
                    break;
                }
            }
            charArray21[n63] = (char)(c41 ^ c42);
        }
        g[n61] = new String(charArray21).intern();
        final int n64 = 21;
        final char[] charArray22 = "cc5+NJp*:I\tp&'R_gc&TGic/O[m1p\u001d".toCharArray();
        final int length18 = charArray22.length;
        for (int n65 = 0; length18 > n65; ++n65) {
            final int n66 = n65;
            final char c43 = charArray22[n66];
            char c44 = '\0';
            switch (n65 % 5) {
                case 0: {
                    c44 = ')';
                    break;
                }
                case 1: {
                    c44 = '\u0002';
                    break;
                }
                case 2: {
                    c44 = 'C';
                    break;
                }
                case 3: {
                    c44 = 'J';
                    break;
                }
                default: {
                    c44 = '=';
                    break;
                }
            }
            charArray22[n66] = (char)(c43 ^ c44);
        }
        g[n64] = new String(charArray22).intern();
        final int n67 = 22;
        final char[] charArray23 = "mk0:QH{\u0007+IH,\u0013%O]".toCharArray();
        final int length19 = charArray23.length;
        for (int n68 = 0; length19 > n68; ++n68) {
            final int n69 = n68;
            final char c45 = charArray23[n69];
            char c46 = '\0';
            switch (n68 % 5) {
                case 0: {
                    c46 = ')';
                    break;
                }
                case 1: {
                    c46 = '\u0002';
                    break;
                }
                case 2: {
                    c46 = 'C';
                    break;
                }
                case 3: {
                    c46 = 'J';
                    break;
                }
                default: {
                    c46 = '=';
                    break;
                }
            }
            charArray23[n69] = (char)(c45 ^ c46);
        }
        g[n67] = new String(charArray23).intern();
        final int n70 = 23;
        final char[] charArray24 = "{w-$TGec,OFoc)RMgn(\\Zgyj".toCharArray();
        final int length20 = charArray24.length;
        for (int n71 = 0; length20 > n71; ++n71) {
            final int n72 = n71;
            final char c47 = charArray24[n72];
            char c48 = '\0';
            switch (n71 % 5) {
                case 0: {
                    c48 = ')';
                    break;
                }
                case 1: {
                    c48 = '\u0002';
                    break;
                }
                case 2: {
                    c48 = 'C';
                    break;
                }
                case 3: {
                    c48 = 'J';
                    break;
                }
                default: {
                    c48 = '=';
                    break;
                }
            }
            charArray24[n72] = (char)(c47 ^ c48);
        }
        g[n70] = new String(charArray24).intern();
        final int n73 = 24;
        final char[] charArray25 = "mk0:QH{\u0007+IH,\u0002.Y[g09".toCharArray();
        final int length21 = charArray25.length;
        for (int n74 = 0; length21 > n74; ++n74) {
            final int n75 = n74;
            final char c49 = charArray25[n75];
            char c50 = '\0';
            switch (n74 % 5) {
                case 0: {
                    c50 = ')';
                    break;
                }
                case 1: {
                    c50 = '\u0002';
                    break;
                }
                case 2: {
                    c50 = 'C';
                    break;
                }
                case 3: {
                    c50 = 'J';
                    break;
                }
                default: {
                    c50 = '=';
                    break;
                }
            }
            charArray25[n75] = (char)(c49 ^ c50);
        }
        g[n73] = new String(charArray25).intern();
        final int n76 = 25;
        final char[] charArray26 = "cc5+NJp*:I\tk-#I\tg18R[8c".toCharArray();
        final int length22 = charArray26.length;
        for (int n77 = 0; length22 > n77; ++n77) {
            final int n78 = n77;
            final char c51 = charArray26[n78];
            char c52 = '\0';
            switch (n77 % 5) {
                case 0: {
                    c52 = ')';
                    break;
                }
                case 1: {
                    c52 = '\u0002';
                    break;
                }
                case 2: {
                    c52 = 'C';
                    break;
                }
                case 3: {
                    c52 = 'J';
                    break;
                }
                default: {
                    c52 = '=';
                    break;
                }
            }
            charArray26[n78] = (char)(c51 ^ c52);
        }
        g[n76] = new String(charArray26).intern();
        a.a.g.b.G = g;
        a = new Cursor(12);
        b = new Color(202, 202, 152);
    }
}
