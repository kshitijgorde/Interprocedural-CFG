import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import netscape.javascript.JSObject;
import java.util.Vector;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import a.a.b;
import a.a.a;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Ticker extends Applet implements Runnable, MouseListener, MouseMotionListener, w, a
{
    private String a;
    private String goto;
    private String int;
    private String c;
    private static final int else = 1200;
    public static final int m = 10;
    public static final int try = 20;
    public static final int i = 30;
    private String l;
    private t for;
    private o if;
    private b long;
    private boolean byte;
    private String g;
    private boolean h;
    private boolean k;
    private boolean b;
    private e char;
    private int f;
    private Cursor e;
    private Cursor case;
    private Cursor null;
    private int j;
    private boolean n;
    private boolean new;
    private int do;
    private byte[] d;
    private String void;
    
    public synchronized void init() {
        final Color color = new Color(0);
        final Color color2 = new Color(16777215);
        if (!this.c.equals(new String(this.d))) {
            return;
        }
        System.out.println(this.a);
        final String parameter = this.getParameter("Author");
        if (parameter == null || !parameter.replace('i', 'I').toUpperCase().equals(this.goto.replace('i', 'I').toUpperCase())) {
            return;
        }
        final String parameter2 = this.getParameter("LoadingMessage");
        final Color a = h.a(this.getParameter("LoadingBGColor"), color);
        final Color a2 = h.a(this.getParameter("LoadingTextColor"), color2);
        final String parameter3 = this.getParameter("ContextCursor");
        if (parameter3 != null && parameter3.trim().length() > 0) {
            final String upperCase = parameter3.trim().replace('i', 'I').toUpperCase();
            if (upperCase.equals("NO") || upperCase.equals("OFF") || upperCase.equals("FALSE")) {
                this.do = 30;
            }
            else if (upperCase.equals("ALL")) {
                this.do = 10;
            }
            else {
                this.do = 20;
            }
        }
        (this.if = new o()).a(this);
        this.if.a(parameter2, a, a2);
        this.setLayout(new BorderLayout());
        this.if.addMouseListener(this);
        this.if.addMouseMotionListener(this);
        this.add("Center", this.if);
        this.validate();
        this.if.try();
        if (this.do == 10) {
            this.if(this.f);
        }
        this.a(this.getParameter("ProjectFile"));
    }
    
    public synchronized void start() {
        if (this.if != null) {
            this.if.a(false);
        }
    }
    
    public synchronized void stop() {
        if (this.if != null) {
            this.if.a(true);
        }
    }
    
    public synchronized void destroy() {
        this.byte = true;
        if (this.if != null) {
            this.if.new();
            this.if.a((w)null);
        }
    }
    
    public final synchronized void evalCommand(final String s, final String s2) {
        final int[] array = { 0 };
        if (this.if != null && s2 != null) {
            final String[] array2 = { "=" + s2 };
            final s a = this.if.a(s);
            if (a != null) {
                this.if.a(a, h.a(array2, array, (Vector)null));
            }
        }
    }
    
    private void if(final String s) {
        if (s != null && s.trim().length() > 0) {
            new n(JSObject.getWindow((Applet)this), s).start();
        }
    }
    
    public Component try() {
        return this;
    }
    
    public synchronized void a(final String l) {
        if (l != null) {
            this.l = l;
            new Thread(this).start();
        }
    }
    
    public void run() {
        final String[] array = { null };
        t a = null;
        array[0] = h.a(this.getCodeBase(), this.l);
        if (array[0] != null) {
            a = h.a(array);
            if (array[0] == null) {
                System.out.println("project evaluation error");
            }
        }
        if (a == null || this.byte) {
            return;
        }
        this.for = a;
        this.n = a.goto;
        if (this.j != 300) {
            this.remove(this.long);
            this.j = 300;
        }
        (this.long = new b(0, a.null)).int(a.long);
        this.long.a(a.void);
        this.long.a(a.do, a.new, a.if, a.int, a.char, a.case, a.a);
        if (a.byte != null && (a.byte.equals("LEFT") || a.byte.equals("RIGHT"))) {
            if (a.byte.equals("LEFT")) {
                this.add("West", this.long);
                this.j = 100;
                this.validate();
            }
            else if (a.byte.equals("RIGHT")) {
                this.add("East", this.long);
                this.j = 200;
                this.validate();
            }
        }
        this.if.a(a.b);
        final s[] for1 = a.for;
        if (for1 != null) {
            for (int i = 0; i < for1.length; ++i) {
                if (this.byte) {
                    return;
                }
                if (for1[i].void != null) {
                    array[0] = h.a(this.getCodeBase(), for1[i].void);
                    if (array[0] == null) {
                        System.out.println("error reading file: " + for1[i].void);
                    }
                    else {
                        h.a(this.getCodeBase(), array, for1[i], this);
                        if (array[0] == null) {
                            System.out.println("error parsing file: " + for1[i].void);
                        }
                    }
                }
            }
            array[0] = null;
            this.if.a(for1);
            if (a.else >= 0) {
                this.if.int(for1[a.else].for);
            }
        }
        this.for.for = null;
        if (this.long != null) {
            this.long.a(this);
        }
        final e char1 = this.if.char();
        if (char1 != null && this.n) {
            this.a(char1);
        }
        this.new = a.try;
        this.if.do(this.k);
    }
    
    public void new() {
        this.new = true;
    }
    
    public void do() {
        this.new = false;
    }
    
    public void a(final int n) {
        if (this.long != null && this.j != n) {
            if (this.j != 300) {
                this.remove(this.long);
            }
            if (n == 100) {
                this.add("West", this.long);
                this.j = 100;
            }
            else if (n == 200) {
                this.add("East", this.long);
                this.j = 200;
            }
            this.validate();
        }
    }
    
    public void for() {
        if (this.long != null && this.j != 300) {
            this.remove(this.long);
            this.j = 300;
            this.validate();
        }
    }
    
    public void int() {
        if (!this.n) {
            final e char1 = this.if.char();
            this.n = true;
            this.a(char1);
        }
    }
    
    public void byte() {
        if (this.n) {
            this.n = false;
            this.a((e)null);
        }
    }
    
    private void if(final int f) {
        this.f = f;
        if (this.do != 30) {
            if (f == 12) {
                this.setCursor(this.case);
                return;
            }
            if (f == 3) {
                if (this.do == 10) {
                    this.setCursor(this.null);
                }
            }
            else {
                this.setCursor(this.e);
            }
        }
    }
    
    public void a(final int n, final int n2) {
        if (this.f != n2) {
            this.if(n2);
        }
        if (n >= 0 && this.long != null && this.n) {
            this.long.if((long)n);
        }
        this.case();
    }
    
    public void a(final e e) {
        final int a = this.if.a(e);
        final int for1 = this.if.for(e);
        final int if1 = this.if.if(e);
        final Dimension do1 = this.if.do(e);
        if (e != null && this.long != null && do1 != null && this.n) {
            if (if1 == 20) {
                this.long.a((long)(for1 + do1.height - 1));
            }
            else {
                this.long.a((long)for1);
            }
            this.long.try(do1.height);
            this.long.if((long)a);
            return;
        }
        if (this.long != null) {
            this.long.a(0L);
            this.long.try(0);
            this.long.if(0L);
        }
    }
    
    public void if() {
        if (this.n) {
            this.h = true;
            this.if.a(this.if.char(), true, 0);
        }
    }
    
    public void a() {
        if (this.n) {
            this.h = false;
            this.if.a(this.if.char(), false, 1200);
        }
    }
    
    public void a(final b b, final long n) {
        this.if.a(this.if.char(), (int)n);
    }
    
    private void case() {
        if (this.g.equals(this.if.byte)) {
            return;
        }
        if (this.if.byte != null) {
            this.showStatus(this.if.byte);
            this.g = this.if.byte;
            return;
        }
        this.showStatus("");
        this.g = "";
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        final e char1 = this.char;
        if (char1 != null && this.new) {
            final Dimension do1 = this.if.do(char1);
            if (do1 == null) {
                return;
            }
            final int if1 = this.if.if(char1);
            final Point else1 = this.if.else();
            final int for1 = this.if.for(char1);
            int n = this.if.a(char1) + (else1.y - point.y);
            if (if1 == 20) {
                if (n >= for1) {
                    n = for1 - 1;
                }
            }
            else if (n > for1 - do1.height) {
                n = for1 - do1.height;
            }
            if (n < 0) {
                n = 0;
            }
            this.if.a(char1, n);
        }
        this.if.a(point.x, point.y);
        this.if.a();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        this.if.a(point.x, point.y);
        this.if.a();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        this.b = true;
        this.if.if(true);
        this.if.a(point.x, point.y);
        if (!this.k) {
            this.if.for();
        }
        this.if.a();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.b = false;
        this.if.if(false);
        if (!this.k) {
            this.if.goto();
        }
        this.if.a();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        this.k = true;
        this.char = this.if.a(point.x, point.y, this.new);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.if.byte != null && this.if.int != null && this.if.byte.trim().length() > 0 && this.if.int.trim().length() > 0) {
            final AppletContext appletContext = this.getAppletContext();
            try {
                appletContext.showDocument(new URL(this.getCodeBase(), this.if.byte), this.if.int);
            }
            catch (MalformedURLException ex) {}
        }
        this.if(this.if.m);
        this.k = false;
        this.if.a(this.char, false, 1200);
        if (this.if.char != null && this.if.i != null) {
            this.if.a(this.if.char, this.if.i);
        }
        this.if.if(this.char, 1200);
        if (!this.b) {
            this.if.goto();
        }
    }
    
    public Ticker() {
        this.a = "JET - Java Extended Ticker v2.0 - (c) exsys GbR Emden - www.java.exsys.net";
        this.goto = "JET - Java Extended Ticker - (c) exsys GbR Emden - www.java.exsys.net";
        this.int = "by Raul Molino Garcia";
        this.c = "(c) exsys GbR - software@exsys.net";
        this.byte = false;
        this.g = "";
        this.h = false;
        this.k = false;
        this.b = false;
        this.f = 3;
        this.e = Cursor.getPredefinedCursor(0);
        this.case = Cursor.getPredefinedCursor(12);
        this.null = Cursor.getPredefinedCursor(3);
        this.j = 300;
        this.n = true;
        this.new = true;
        this.do = 20;
        this.d = new byte[] { 40, 99, 41, 32, 101, 120, 115, 121, 115, 32, 71, 98, 82, 32, 45, 32, 115, 111, 102, 116, 119, 97, 114, 101, 64, 101, 120, 115, 121, 115, 46, 110, 101, 116 };
        this.void = "unregistered";
    }
}
