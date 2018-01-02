// 
// Decompiled by Procyon v0.5.30
// 

package dessin;

import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.Cursor;
import java.util.Enumeration;
import java.net.URLConnection;
import java.awt.Font;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
import java.awt.Label;
import java.awt.Point;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.Canvas;

class j extends Canvas implements KeyListener, MouseListener, MouseMotionListener
{
    boolean void;
    int[] C;
    Graphics null;
    Image i;
    Color n;
    Point g;
    int c;
    int b;
    private int for;
    private int if;
    private int s;
    private int r;
    float p;
    private boolean z;
    boolean u;
    v v;
    m byte;
    Label m;
    a t;
    a new;
    String a;
    private boolean l;
    private boolean F;
    private boolean I;
    private boolean D;
    private boolean else;
    private boolean K;
    private boolean H;
    private boolean long;
    private boolean try;
    private boolean h;
    private boolean q;
    boolean E;
    boolean goto;
    private boolean e;
    private boolean B;
    private int char;
    int case;
    String o;
    private int A;
    String d;
    private int G;
    Principale int;
    boolean do;
    boolean f;
    boolean J;
    private boolean w;
    String k;
    String j;
    private int x;
    private int y;
    
    j(final Principale int1, final boolean l, final String k, final String j, final String d) {
        this.I = false;
        this.h = false;
        this.q = false;
        this.long = false;
        this.K = false;
        this.D = false;
        this.try = false;
        this.else = false;
        this.e = false;
        this.B = false;
        this.F = false;
        this.H = false;
        this.C = new int[] { 0, 180 };
        this.goto = false;
        this.E = false;
        this.A = 0;
        this.char = 0;
        this.w = false;
        this.G = -1;
        this.u = false;
        this.f = false;
        this.J = false;
        this.case = 0;
        this.g = new Point(0, 0);
        this.do = false;
        this.n = Color.black;
        this.p = 1.0f;
        this.z = false;
        this.i = null;
        this.null = null;
        this.l = l;
        this.int = int1;
        this.k = k;
        this.j = j;
        this.d = d;
        this.v = new v(this, this.l);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.requestFocus();
    }
    
    public void a(final String o) {
        this.o = o;
        this.E = true;
        this.byte = new o(o, this.n, this.d);
    }
    
    public void a(final Label m, final boolean u) {
        this.u = u;
        this.m = new Label();
        this.m = m;
        this.byte = new k(this.m, u);
    }
    
    public void do(final int n, final int n2) {
        final Graphics graphics = this.getGraphics();
        graphics.setXORMode(this.v.do());
        this.v.a(graphics, true);
        this.v.a(this.for, this.if, this.for + n, this.if + n2);
        this.v.a(graphics, true);
        this.v.a(this.byte);
        graphics.setPaintMode();
    }
    
    public void do() {
        if (this.v == null) {
            return;
        }
        this.v.goto();
    }
    
    public void byte() {
        this.v.e();
    }
    
    public void for(final String s) {
        final Vector<String> vector = new Vector<String>();
        System.getProperty("line.separator");
        URL url;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {
            System.out.println("Error: " + ex.toString());
            return;
        }
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            openConnection.setDoInput(true);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.equals("")) {
                    vector.addElement(new String(line));
                }
            }
            bufferedReader.close();
        }
        catch (IOException ex2) {
            System.out.println("Error: " + ex2.toString());
        }
        this.v.e();
        if (vector.isEmpty()) {
            return;
        }
        final Enumeration<String> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final String s2 = elements.nextElement();
            final int index = s2.indexOf(59);
            final String substring = s2.substring(0, index);
            String s3 = s2.substring(index + 1);
            if (substring.equals("zonetexte")) {
                s3.replace('#', '\n');
            }
            else if (substring.equals("texte")) {
                final boolean b = true;
                int n = 0;
                int int1 = 0;
                int int2 = 0;
                int int3 = 0;
                int int4 = 0;
                int int5 = 0;
                String s4 = "";
                String text = "";
                while (b) {
                    final int index2 = s3.indexOf(59);
                    if (index2 == -1) {
                        break;
                    }
                    final String substring2 = s3.substring(0, index2);
                    s3 = s3.substring(index2 + 1);
                    if (n == 0) {
                        int1 = this.int(substring2);
                    }
                    else if (n == 1) {
                        int2 = this.int(substring2);
                    }
                    else if (n == 2) {
                        int3 = this.int(substring2);
                    }
                    else if (n == 3) {
                        s4 = substring2;
                    }
                    else if (n == 4) {
                        int4 = this.int(substring2);
                    }
                    else if (n == 5) {
                        int5 = this.int(substring2);
                    }
                    else if (n == 6) {
                        text = substring2;
                    }
                    ++n;
                }
                final Label label = new Label();
                label.setText(text);
                label.setFont(new Font(s4, int5, int4));
                label.setForeground(new Color(int3));
                (this.byte = new k(label, false)).a(0, 0, int1, int2);
                this.v.if(this.byte);
                this.byte.a = false;
            }
            else if (substring.equals("textelink")) {
                final boolean b2 = true;
                int n2 = 0;
                int int6 = 0;
                int int7 = 0;
                int int8 = 0;
                int int9 = 0;
                int int10 = 0;
                String s5 = "";
                String text2 = "";
                while (b2) {
                    final int index3 = s3.indexOf(59);
                    if (index3 == -1) {
                        break;
                    }
                    final String substring3 = s3.substring(0, index3);
                    s3 = s3.substring(index3 + 1);
                    if (n2 == 0) {
                        int6 = this.int(substring3);
                    }
                    else if (n2 == 1) {
                        int7 = this.int(substring3);
                    }
                    else if (n2 == 2) {
                        int8 = this.int(substring3);
                    }
                    else if (n2 == 3) {
                        s5 = substring3;
                    }
                    else if (n2 == 4) {
                        int9 = this.int(substring3);
                    }
                    else if (n2 == 5) {
                        int10 = this.int(substring3);
                    }
                    else if (n2 == 6) {
                        text2 = substring3;
                    }
                    ++n2;
                }
                final Label label2 = new Label();
                label2.setText(text2);
                label2.setFont(new Font(s5, int10, int9));
                label2.setForeground(new Color(int8));
                (this.byte = new k(label2, true)).a(0, 0, int6, int7);
                this.v.if(this.byte);
                this.byte.a = false;
            }
            else {
                final int[] do1 = this.do(s3);
                final Color color = new Color(do1[4]);
                if (substring.equals("ligne")) {
                    this.byte = new r(do1[0], do1[1], do1[2], do1[3], color, this.p);
                    this.v.if(this.byte);
                    this.byte.a = false;
                }
                else if (substring.equals("arc")) {
                    this.byte = new n(do1[0], do1[1], do1[2], do1[3], color, this.p, new int[] { do1[6], do1[7] });
                    this.v.if(this.byte);
                    this.byte.a = false;
                }
                else if (substring.equals("oval")) {
                    if (do1[6] == 1) {
                        this.byte = new g(do1[0], do1[1], do1[2], do1[3], color, this.p, false);
                    }
                    else if (do1[6] == 0) {
                        this.byte = new g(do1[0], do1[1], do1[2], do1[3], color, this.p, true);
                    }
                    this.v.if(this.byte);
                    this.byte.a = false;
                }
                else if (substring.equals("pin")) {
                    this.byte = new l(do1[0], do1[1], color, this.p);
                    this.v.if(this.byte);
                    this.byte.a = false;
                }
                else if (substring.equals("rectangle")) {
                    if (do1[6] == 1) {
                        this.byte = new b(do1[0], do1[1], do1[2], do1[3], color, this.p, false);
                    }
                    else if (do1[6] == 0) {
                        this.byte = new b(do1[0], do1[1], do1[2], do1[3], color, this.p, true);
                    }
                    this.v.if(this.byte);
                    this.byte.a = false;
                }
                else {
                    if (!substring.equals("triangle")) {
                        continue;
                    }
                    final int[] array = { do1[0], do1[2], do1[6] };
                    final int[] array2 = { do1[1], do1[3], do1[7] };
                    if (do1[8] == 1) {
                        this.byte = new f(array, array2, color, this.p, false);
                    }
                    else if (do1[8] == 0) {
                        this.byte = new f(array, array2, color, this.p, true);
                    }
                    this.v.if(this.byte);
                    this.byte.a = false;
                }
            }
        }
        this.repaint();
    }
    
    public void if(final int n) {
        this.v.a(this.k, this.j, n);
    }
    
    public void a(final boolean z) {
        this.z = z;
        this.repaint();
    }
    
    public void a(final Color n) {
        this.n = n;
    }
    
    public void a(final int n) {
        this.p = n;
    }
    
    private void if(final int n, final int n2) {
        if (this.h) {
            final m c = this.v.c();
            if (c != null) {
                if (c.a(n, n2) == 1) {
                    this.G = 1;
                    this.setCursor(Cursor.getPredefinedCursor(13));
                }
                if (c.a(n, n2) == 2 || c.a(n, n2) == 3) {
                    this.G = c.a(n, n2);
                    this.setCursor(Cursor.getPredefinedCursor(11));
                }
                if (c.a(n, n2) == 4) {
                    this.G = c.a(n, n2);
                    this.setCursor(Cursor.getPredefinedCursor(8));
                }
                if (c.a(n, n2) == 5) {
                    this.G = c.a(n, n2);
                    this.setCursor(Cursor.getPredefinedCursor(7));
                }
                if (c.a(n, n2) == 6) {
                    this.G = c.a(n, n2);
                    this.setCursor(Cursor.getPredefinedCursor(11));
                }
                if (c.a(n, n2) == 7) {
                    this.G = c.a(n, n2);
                    this.setCursor(Cursor.getPredefinedCursor(5));
                }
                if (c.a(n, n2) == 8) {
                    this.G = c.a(n, n2);
                    this.setCursor(Cursor.getPredefinedCursor(9));
                }
                if (c.a(n, n2) == 9) {
                    this.G = c.a(n, n2);
                    this.setCursor(Cursor.getPredefinedCursor(4));
                }
                if (c.a(n, n2) == 10) {
                    this.G = c.a(n, n2);
                    this.setCursor(Cursor.getPredefinedCursor(10));
                }
                if (c.a(n, n2) == 11) {
                    this.G = c.a(n, n2);
                    this.setCursor(Cursor.getPredefinedCursor(6));
                }
                if (c.a(n, n2) == -1) {
                    this.G = -1;
                    this.setCursor(Cursor.getPredefinedCursor(0));
                }
            }
            else {
                this.G = -1;
                this.setCursor(Cursor.getPredefinedCursor(0));
            }
        }
        else if (this.I || this.long || this.D || this.try || this.else || this.F || this.e || this.B) {
            this.setCursor(Cursor.getPredefinedCursor(1));
        }
        else if (this.E || this.K) {
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
        else if (this.goto) {
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
        else {
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
    }
    
    public int a() {
        return this.v.char();
    }
    
    public String int() {
        return "http://www.abcelectronique.com/";
    }
    
    Component for() {
        return this;
    }
    
    public void try() {
        if (this.v == null) {
            return;
        }
        this.v.case();
    }
    
    public boolean case() {
        return this.do;
    }
    
    public void new() {
        try {
            if (this.v.b()) {
                this.repaint();
            }
        }
        catch (NullPointerException ex) {}
    }
    
    public boolean char() {
        return this.I;
    }
    
    public boolean if() {
        this.getGraphics();
        return this.v.do(this.x, this.y) != null;
    }
    
    private int int(final String s) {
        return Integer.valueOf(s);
    }
    
    public void a(final int a, final int char1) {
        this.A = a;
        this.char = char1;
        this.repaint();
    }
    
    public void if(final String a) {
        this.a = a;
        this.repaint();
    }
    
    public int[] do(String substring) {
        final int[] array = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        final boolean b = true;
        int n = 0;
        while (b) {
            final int index = substring.indexOf(59);
            if (index == -1) {
                break;
            }
            final String substring2 = substring.substring(0, index);
            substring = substring.substring(index + 1);
            array[n] = this.int(substring2);
            ++n;
        }
        return array;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        keyEvent.getKeyChar();
        final int n = 1;
        if (this.h) {
            if (keyCode == 16) {
                this.w = true;
            }
            if (this.v.void() > 0) {
                if (keyCode == 38) {
                    this.do(0, -n);
                }
                if (keyCode == 40) {
                    this.do(0, n);
                }
                if (keyCode == 37) {
                    this.do(-n, 0);
                }
                if (keyCode == 39) {
                    this.do(n, 0);
                }
                if (keyEvent.getKeyCode() == 127) {
                    this.new();
                }
            }
        }
        this.repaint();
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        if (this.h && keyCode == 16) {
            this.w = false;
            goto Label_0025;
        }
        this.repaint();
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void a(final boolean[] array) {
        if (this.v != null) {
            this.v.new(this.getGraphics());
        }
        this.h = array[0];
        this.I = array[1];
        this.H = array[2];
        this.F = array[3];
        this.goto = array[4];
        this.long = array[5];
        this.try = array[6];
        this.D = array[7];
        this.else = array[8];
        this.e = array[9];
        this.B = array[10];
        this.K = array[11];
        if (this.h) {
            this.J = false;
            this.q = true;
        }
        this.E = false;
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x4) == 0x4) {
            if (this.I && this.f) {
                this.f = false;
                this.do = true;
            }
            else {
                this.do = false;
            }
            if (this.K) {
                this.K = false;
            }
            if (this.goto) {
                this.goto = false;
            }
            if (this.E) {
                this.E = false;
            }
        }
        this.if(this.x, this.y);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.x = mouseEvent.getX();
        this.y = mouseEvent.getY();
        final Graphics graphics = this.getGraphics();
        if ((mouseEvent.getModifiers() & 0x10) == 0x10) {
            graphics.setColor(Color.white);
            if (this.h) {
                if (this.q) {
                    graphics.setXORMode(Color.gray);
                    this.t.a(graphics);
                    this.t.a(this.x, this.y);
                    this.t.a(graphics);
                }
                else {
                    if (this.G == 1) {
                        graphics.setXORMode(this.v.do());
                        this.v.a(graphics, true);
                        this.v.a(this.for, this.if, this.x, this.y);
                        this.v.a(graphics, true);
                        this.v.a(this.byte);
                    }
                    if (this.G == 2 || this.G == 3) {
                        graphics.setXORMode(Color.gray);
                        this.v.a(graphics, true);
                        this.v.a(this.x, this.y, this.G);
                        this.v.a(graphics, true);
                        this.v.a(this.byte);
                    }
                    if (this.G == 4 || this.G == 5 || this.G == 6 || this.G == 7 || this.G == 8 || this.G == 9 || this.G == 10 || this.G == 11) {
                        graphics.setXORMode(Color.gray);
                        this.v.a(graphics, true);
                        this.v.a(this.x, this.y, this.G);
                        this.v.a(graphics, true);
                    }
                }
                this.for = this.x;
                this.if = this.y;
            }
            if (this.I) {
                this.f = false;
                graphics.setXORMode(Color.gray);
                this.byte.a(graphics, true);
                this.byte.if(this.x, this.y);
                this.byte.a(graphics, true);
                this.g.x = this.x;
                this.g.y = this.y;
            }
            if (this.H) {
                graphics.setColor(this.n);
                (this.byte = new t(this.c, this.b, this.x, this.y, this.n, this.p)).a(graphics, true);
                this.v.if(this.byte);
                this.c = this.x;
                this.b = this.y;
            }
            if (this.long || this.D || this.try || this.else || this.e || this.B) {
                graphics.setXORMode(Color.gray);
                this.byte.a(graphics, true);
                this.byte.if(this.x, this.y);
                this.byte.a(graphics, true);
            }
            if (this.F) {
                graphics.setXORMode(Color.gray);
                this.byte.a(graphics, true);
                this.byte.if(this.x, this.y);
                this.byte.a(graphics, true);
            }
            graphics.setPaintMode();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.x = mouseEvent.getX();
        this.y = mouseEvent.getY();
        final Graphics graphics = this.getGraphics();
        this.g.x = this.x;
        this.g.y = this.y;
        this.if(this.x, this.y);
        if (this.I) {
            if (this.f) {
                if (Math.abs(this.x - this.s) >= Math.abs(this.y - this.r)) {
                    this.y = this.r;
                }
                else {
                    this.x = this.s;
                }
                this.g.x = this.x;
                this.g.y = this.y;
                graphics.setXORMode(Color.gray);
                this.byte.a(graphics, true);
                this.byte.if(this.x, this.y);
                this.byte.a(graphics, true);
                graphics.setPaintMode();
            }
        }
        else if (this.h) {
            if (!this.J) {
                final m do1 = this.v.do(this.x, this.y);
                if (do1 == null) {
                    this.v.new(graphics);
                }
                else {
                    if (!do1.a) {
                        do1.a(graphics);
                    }
                    if (this.v.void() < 2) {
                        this.v.for(graphics);
                    }
                    do1.a = true;
                }
                this.v.a(graphics, true);
            }
        }
        this.int.a(this.x, this.y);
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.x = mouseEvent.getX();
        this.y = mouseEvent.getY();
        this.s = this.x;
        this.r = this.y;
        final Graphics graphics = this.getGraphics();
        if ((mouseEvent.getModifiers() & 0x10) == 0x10) {
            graphics.setColor(Color.white);
            if (this.h) {
                final m do1 = this.v.do(this.x, this.y);
                this.t = new a(this.x, this.y);
                if (do1 == null) {
                    this.J = false;
                    this.v.new(graphics);
                    this.q = true;
                    this.t.a(this.x, this.y);
                    this.t.a(graphics);
                }
                else {
                    this.J = true;
                    this.q = false;
                    if (!do1.a) {
                        do1.a(graphics);
                    }
                    if (!this.w && this.v.void() < 2) {
                        this.v.for(graphics);
                        goto Label_0200;
                    }
                    do1.a = true;
                }
                this.v.a(graphics, true);
                this.for = this.x;
                this.if = this.y;
            }
            if (this.I) {
                this.f = true;
                graphics.setXORMode(Color.gray);
                (this.byte = new r(this.g.x, this.g.y, this.n, this.p)).if(this.g.x, this.g.y);
                this.byte.a(graphics, true);
                this.v.if(this.byte);
                this.s = this.g.x;
                this.r = this.g.y;
            }
            if (this.long || this.try) {
                graphics.setXORMode(Color.gray);
                if (this.long) {
                    this.byte = new b(this.x, this.y, this.n, this.p, false);
                }
                if (this.try) {
                    this.byte = new b(this.x, this.y, this.n, this.p, true);
                }
                this.byte.if(this.x, this.y);
                this.byte.a(graphics, true);
                this.v.if(this.byte);
            }
            if (this.D || this.else) {
                graphics.setXORMode(Color.gray);
                if (!this.else) {
                    this.byte = new g(this.x, this.y, this.n, this.p, false);
                }
                if (this.else) {
                    this.byte = new g(this.x, this.y, this.n, this.p, true);
                }
                this.byte.if(this.x, this.y);
                this.byte.a(graphics, true);
                this.v.if(this.byte);
            }
            if (this.e || this.B) {
                graphics.setXORMode(Color.gray);
                if (!this.B) {
                    this.byte = new f(this.x, this.y, this.n, this.p, false);
                }
                if (this.B) {
                    this.byte = new f(this.x, this.y, this.n, this.p, true);
                }
                this.byte.if(this.x, this.y);
                this.byte.a(graphics, true);
                this.v.if(this.byte);
            }
            if (this.F) {
                graphics.setXORMode(Color.gray);
                (this.byte = new n(this.x, this.y, this.n, this.p, this.C)).if(this.x, this.y);
                this.byte.a(graphics, true);
                this.v.if(this.byte);
            }
            if (this.K) {
                graphics.setXORMode(Color.gray);
                (this.byte = new l(this.x, this.y, this.n, this.p)).if(this.x, this.y);
                this.byte.a(graphics, true);
                this.v.if(this.byte);
            }
            if (this.H) {
                graphics.setColor(this.n);
                (this.byte = new t(this.x, this.y, this.x, this.y, this.n, this.p)).a(graphics, true);
                this.v.if(this.byte);
                this.c = this.x;
                this.b = this.y;
            }
            if (this.goto) {
                this.v.if(this.byte);
                this.byte.a(graphics, this.x, this.y);
                this.byte.a = false;
                (this.byte = new k(this.m, this.u)).a(graphics, this.x, this.y);
                this.byte.a = false;
            }
            if (this.E) {
                this.v.if(this.byte);
                this.byte.if(graphics, this.x, this.y);
                this.byte.a = false;
                (this.byte = new o(this.o, this.n, this.d)).a(graphics, this.x, this.y);
                this.byte.a = false;
            }
            this.repaint();
            graphics.setPaintMode();
        }
        if ((mouseEvent.getModifiers() & 0x4) == 0x4 && this.h) {
            final m do2 = this.v.do(this.x, this.y);
            Label_1248: {
                if (do2 != null) {
                    if (!do2.a) {
                        do2.a(graphics);
                        this.v.a(graphics);
                    }
                    do2.a = true;
                    this.for = this.x;
                    this.if = this.y;
                    graphics.setPaintMode();
                    this.repaint();
                    break Label_1248;
                }
                break Label_1248;
            }
            goto Label_1249;
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.x = mouseEvent.getX();
        this.y = mouseEvent.getY();
        if ((mouseEvent.getModifiers() & 0x10) == 0x10) {
            if (!this.h && this.I) {
                this.byte.if(this.x, this.y);
                this.byte.a = false;
                goto Label_0066;
            }
            if (this.q) {
                this.t.a(this.x, this.y);
                final Vector vector = new Vector(4);
                this.v.a(this.t.a());
                if (this.v.void() > 0) {
                    this.J = true;
                }
                else {
                    this.J = false;
                }
            }
            if (this.long || this.D || this.try || this.else || this.F || this.e || this.B) {
                this.byte.a();
            }
            this.repaint();
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.i == null) {
            this.i = this.createImage(this.getSize().width, this.getSize().height);
        }
        (this.null = this.i.getGraphics()).setColor(Color.white);
        this.null.clearRect(0, 0, this.getSize().width, this.getSize().height);
        this.null.setColor(Color.black);
        this.null.drawRect(1, 1, this.getSize().width - 2, this.getSize().height - 2);
        if (this.v != null) {
            this.null.setColor(Color.black);
            this.v.a(this.null, false);
            if (!this.H) {
                this.v.a(this.null);
            }
            this.null.setPaintMode();
            if (this.E) {
                this.byte.if(this.null, this.x, this.y);
            }
            if (this.goto) {
                this.null.setColor(this.m.getForeground());
                this.null.setFont(this.m.getFont());
                String s = this.m.getText();
                if (this.u) {
                    final String text = this.m.getText();
                    s = text.substring(0, text.indexOf("###"));
                }
                this.null.drawString(s, this.x, this.y);
            }
            if (this.K) {
                this.null.setColor(Color.blue);
                this.null.fillRect(this.x, this.y, 6, 6);
            }
        }
        else {
            this.null.setColor(Color.black);
        }
        if (this.z) {
            this.null.setColor(Color.white);
            this.null.fillRect(2, 2, 533, 330);
            this.null.setColor(Color.black);
            this.null.setFont(new Font("SansSerif", 0, 12));
            this.null.drawString("Saving in progress, please wait...", 100, 100);
            this.null.drawRect(100, 115, 200, 15);
            this.null.setColor(Color.green);
            if (this.char != 0) {
                this.null.fillRect(101, 116, 200 * (this.A + 1) / this.char - 1, 14);
            }
        }
        graphics.drawImage(this.i, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
