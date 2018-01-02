import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class vNES extends Applet implements Runnable
{
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    int a;
    int b;
    private J j;
    private V k;
    private b l;
    private String m;
    private Font n;
    Color c;
    private boolean o;
    
    public vNES() {
        this.m = "";
        this.c = Color.black.darker().darker();
        this.o = false;
    }
    
    public void init() {
        X.j.put("VK_SPACE", 32);
        X.j.put("VK_PAGE_UP", 33);
        X.j.put("VK_PAGE_DOWN", 34);
        X.j.put("VK_END", 35);
        X.j.put("VK_HOME", 36);
        X.j.put("VK_DELETE", 127);
        X.j.put("VK_INSERT", 155);
        X.j.put("VK_LEFT", 37);
        X.j.put("VK_UP", 38);
        X.j.put("VK_RIGHT", 39);
        X.j.put("VK_DOWN", 40);
        X.j.put("VK_0", 48);
        X.j.put("VK_1", 49);
        X.j.put("VK_2", 50);
        X.j.put("VK_3", 51);
        X.j.put("VK_4", 52);
        X.j.put("VK_5", 53);
        X.j.put("VK_6", 54);
        X.j.put("VK_7", 55);
        X.j.put("VK_8", 56);
        X.j.put("VK_9", 57);
        X.j.put("VK_A", 65);
        X.j.put("VK_B", 66);
        X.j.put("VK_C", 67);
        X.j.put("VK_D", 68);
        X.j.put("VK_E", 69);
        X.j.put("VK_F", 70);
        X.j.put("VK_G", 71);
        X.j.put("VK_H", 72);
        X.j.put("VK_I", 73);
        X.j.put("VK_J", 74);
        X.j.put("VK_K", 75);
        X.j.put("VK_L", 76);
        X.j.put("VK_M", 77);
        X.j.put("VK_N", 78);
        X.j.put("VK_O", 79);
        X.j.put("VK_P", 80);
        X.j.put("VK_Q", 81);
        X.j.put("VK_R", 82);
        X.j.put("VK_S", 83);
        X.j.put("VK_T", 84);
        X.j.put("VK_U", 85);
        X.j.put("VK_V", 86);
        X.j.put("VK_W", 87);
        X.j.put("VK_X", 88);
        X.j.put("VK_Y", 89);
        X.j.put("VK_Z", 90);
        X.j.put("VK_NUMPAD0", 96);
        X.j.put("VK_NUMPAD1", 97);
        X.j.put("VK_NUMPAD2", 98);
        X.j.put("VK_NUMPAD3", 99);
        X.j.put("VK_NUMPAD4", 100);
        X.j.put("VK_NUMPAD5", 101);
        X.j.put("VK_NUMPAD6", 102);
        X.j.put("VK_NUMPAD7", 103);
        X.j.put("VK_NUMPAD8", 104);
        X.j.put("VK_NUMPAD9", 105);
        X.j.put("VK_MULTIPLY", 106);
        X.j.put("VK_ADD", 107);
        X.j.put("VK_SUBTRACT", 109);
        X.j.put("VK_DECIMAL", 110);
        X.j.put("VK_DIVIDE", 111);
        X.j.put("VK_BACK_SPACE", 8);
        X.j.put("VK_TAB", 9);
        X.j.put("VK_ENTER", 10);
        X.j.put("VK_SHIFT", 16);
        X.j.put("VK_CONTROL", 17);
        X.j.put("VK_ALT", 18);
        X.j.put("VK_PAUSE", 19);
        X.j.put("VK_ESCAPE", 27);
        X.j.put("VK_OPEN_BRACKET", 91);
        X.j.put("VK_BACK_SLASH", 92);
        X.j.put("VK_CLOSE_BRACKET", 93);
        X.j.put("VK_SEMICOLON", 59);
        X.j.put("VK_QUOTE", 222);
        X.j.put("VK_COMMA", 44);
        X.j.put("VK_MINUS", 45);
        X.j.put("VK_PERIOD", 46);
        X.j.put("VK_SLASH", 47);
        final String parameter;
        if ((parameter = this.getParameter("rom")) == null || parameter.equals("")) {
            this.m = "vnes.nes";
        }
        else {
            this.m = parameter;
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("scale")) == null || parameter2.equals("")) {
            this.d = false;
        }
        else {
            this.d = parameter2.equals("on");
        }
        final String parameter3;
        if ((parameter3 = this.getParameter("sound")) == null || parameter3.equals("")) {
            this.f = true;
        }
        else {
            this.f = parameter3.equals("on");
        }
        this.getParameter("stereo");
        final String parameter4;
        if ((parameter4 = this.getParameter("scanlines")) == null || parameter4.equals("")) {
            this.e = false;
        }
        else {
            this.e = parameter4.equals("on");
        }
        final String parameter5;
        if ((parameter5 = this.getParameter("fps")) == null || parameter5.equals("")) {
            this.g = false;
        }
        else {
            this.g = parameter5.equals("on");
        }
        final String parameter6;
        if ((parameter6 = this.getParameter("timeemulation")) == null || parameter6.equals("")) {
            this.h = true;
        }
        else {
            this.h = parameter6.equals("on");
        }
        final String parameter7;
        if ((parameter7 = this.getParameter("showsoundbuffer")) == null || parameter7.equals("")) {
            this.i = false;
        }
        else {
            this.i = parameter7.equals("on");
        }
        final String parameter8;
        if ((parameter8 = this.getParameter("p1_up")) == null || parameter8.equals("")) {
            X.k.put("p1_up", "VK_UP");
        }
        else {
            X.k.put("p1_up", "VK_" + parameter8);
        }
        final String parameter9;
        if ((parameter9 = this.getParameter("p1_down")) == null || parameter9.equals("")) {
            X.k.put("p1_down", "VK_DOWN");
        }
        else {
            X.k.put("p1_down", "VK_" + parameter9);
        }
        final String parameter10;
        if ((parameter10 = this.getParameter("p1_left")) == null || parameter10.equals("")) {
            X.k.put("p1_left", "VK_LEFT");
        }
        else {
            X.k.put("p1_left", "VK_" + parameter10);
        }
        final String parameter11;
        if ((parameter11 = this.getParameter("p1_right")) == null || parameter11.equals("")) {
            X.k.put("p1_right", "VK_RIGHT");
        }
        else {
            X.k.put("p1_right", "VK_" + parameter11);
        }
        final String parameter12;
        if ((parameter12 = this.getParameter("p1_a")) == null || parameter12.equals("")) {
            X.k.put("p1_a", "VK_X");
        }
        else {
            X.k.put("p1_a", "VK_" + parameter12);
        }
        final String parameter13;
        if ((parameter13 = this.getParameter("p1_b")) == null || parameter13.equals("")) {
            X.k.put("p1_b", "VK_Z");
        }
        else {
            X.k.put("p1_b", "VK_" + parameter13);
        }
        final String parameter14;
        if ((parameter14 = this.getParameter("p1_start")) == null || parameter14.equals("")) {
            X.k.put("p1_start", "VK_ENTER");
        }
        else {
            X.k.put("p1_start", "VK_" + parameter14);
        }
        final String parameter15;
        if ((parameter15 = this.getParameter("p1_select")) == null || parameter15.equals("")) {
            X.k.put("p1_select", "VK_CONTROL");
        }
        else {
            X.k.put("p1_select", "VK_" + parameter15);
        }
        final String parameter16;
        if ((parameter16 = this.getParameter("p2_up")) == null || parameter16.equals("")) {
            X.k.put("p2_up", "VK_NUMPAD8");
        }
        else {
            X.k.put("p2_up", "VK_" + parameter16);
        }
        final String parameter17;
        if ((parameter17 = this.getParameter("p2_down")) == null || parameter17.equals("")) {
            X.k.put("p2_down", "VK_NUMPAD2");
        }
        else {
            X.k.put("p2_down", "VK_" + parameter17);
        }
        final String parameter18;
        if ((parameter18 = this.getParameter("p2_left")) == null || parameter18.equals("")) {
            X.k.put("p2_left", "VK_NUMPAD4");
        }
        else {
            X.k.put("p2_left", "VK_" + parameter18);
        }
        final String parameter19;
        if ((parameter19 = this.getParameter("p2_right")) == null || parameter19.equals("")) {
            X.k.put("p2_right", "VK_NUMPAD6");
        }
        else {
            X.k.put("p2_right", "VK_" + parameter19);
        }
        final String parameter20;
        if ((parameter20 = this.getParameter("p2_a")) == null || parameter20.equals("")) {
            X.k.put("p2_a", "VK_NUMPAD7");
        }
        else {
            X.k.put("p2_a", "VK_" + parameter20);
        }
        final String parameter21;
        if ((parameter21 = this.getParameter("p2_b")) == null || parameter21.equals("")) {
            X.k.put("p2_b", "VK_NUMPAD9");
        }
        else {
            X.k.put("p2_b", "VK_" + parameter21);
        }
        final String parameter22;
        if ((parameter22 = this.getParameter("p2_start")) == null || parameter22.equals("")) {
            X.k.put("p2_start", "VK_NUMPAD1");
        }
        else {
            X.k.put("p2_start", "VK_" + parameter22);
        }
        final String parameter23;
        if ((parameter23 = this.getParameter("p2_select")) == null || parameter23.equals("")) {
            X.k.put("p2_select", "VK_NUMPAD3");
        }
        else {
            X.k.put("p2_select", "VK_" + parameter23);
        }
        final String parameter24;
        if ((parameter24 = this.getParameter("romsize")) == null || parameter24.equals("")) {
            this.a = -1;
        }
        else {
            try {
                this.a = Integer.parseInt(parameter24);
            }
            catch (Exception ex) {
                this.a = -1;
            }
        }
        System.gc();
        this.j = new J(this);
        final J j;
        final J n = j = this.j;
        n.e = new b(j.b, 256, 240);
        j.e.a(j.a.c.getRGB());
        j.e.a();
        j.e.a(true);
        j.c = new F(j.b, 0);
        j.d = new F(j.b, 1);
        j.c.a[0] = X.j.get(X.k.get("p1_a"));
        j.c.a[1] = X.j.get(X.k.get("p1_b"));
        j.c.a[2] = X.j.get(X.k.get("p1_start"));
        j.c.a[3] = X.j.get(X.k.get("p1_select"));
        j.c.a[4] = X.j.get(X.k.get("p1_up"));
        j.c.a[5] = X.j.get(X.k.get("p1_down"));
        j.c.a[6] = X.j.get(X.k.get("p1_left"));
        j.c.a[7] = X.j.get(X.k.get("p1_right"));
        j.e.addKeyListener(j.c);
        j.d.a[0] = X.j.get(X.k.get("p2_a"));
        j.d.a[1] = X.j.get(X.k.get("p2_b"));
        j.d.a[2] = X.j.get(X.k.get("p2_start"));
        j.d.a[3] = X.j.get(X.k.get("p2_select"));
        j.d.a[4] = X.j.get(X.k.get("p2_up"));
        j.d.a[5] = X.j.get(X.k.get("p2_down"));
        j.d.a[6] = X.j.get(X.k.get("p2_left"));
        j.d.a[7] = X.j.get(X.k.get("p2_right"));
        j.e.addKeyListener(j.d);
        X.e = true;
        X.d = 0;
        (this.k = this.j.b).a(this.f);
        this.k.c();
    }
    
    public void start() {
        new Thread(this).start();
    }
    
    public void run() {
        this.n = new Font("Tahoma", 1, 12);
        this.o = true;
        System.out.println("vNES 2.13 © 2006-2010 Jamie Sanders");
        System.out.println("For games and updates, see www.virtualnes.com");
        System.out.println("Use of this program subject to GNU GPL, Version 3.");
        this.k.a(this.m);
        if (this.k.j.a()) {
            (this.l = this.j.e).c(this.g);
            this.setLayout(null);
            if (this.d) {
                if (this.e) {
                    this.l.b(4);
                }
                else {
                    this.l.b(3);
                }
                this.setSize(512, 480);
                this.setBounds(0, 0, 512, 480);
                this.l.setBounds(0, 0, 512, 480);
            }
            else {
                this.l.setBounds(0, 0, 256, 240);
            }
            this.setIgnoreRepaint(true);
            this.add(this.l);
            X.g = this.h;
            this.k.c.a = this.i;
            this.k.b.c();
            return;
        }
        System.out.println("vNES was unable to find (" + this.m + ").");
    }
    
    public void stop() {
        this.k.b();
        this.k.d.b();
        this.destroy();
    }
    
    public void destroy() {
        if (this.k != null && this.k.b.e()) {
            this.stop();
        }
        if (this.k != null) {
            final V k;
            if ((k = this.k).b != null) {
                k.b.f();
            }
            if (k.c != null) {
                k.c.h();
            }
            if (k.d != null) {
                final P d;
                (d = k.d).a = null;
                if (d.c != null) {
                    d.c.g();
                }
                if (d.d != null) {
                    d.d.g();
                }
                if (d.e != null) {
                    d.e.a = null;
                }
                if (d.f != null) {
                    d.f.a = null;
                }
                if (d.g != null) {
                    d.g.a = null;
                }
                d.c = null;
                d.d = null;
                d.e = null;
                d.f = null;
                d.g = null;
                d.b = null;
            }
            if (k.e != null) {
                k.e.a = null;
            }
            if (k.f != null) {
                k.f.a = null;
            }
            if (k.g != null) {
                k.g.a = null;
            }
            if (k.h != null) {
                k.h.d();
            }
            if (k.j != null) {
                k.j.h();
            }
            k.a = null;
            k.b = null;
            k.c = null;
            k.d = null;
            k.e = null;
            k.f = null;
            k.g = null;
            k.h = null;
            k.j = null;
            k.k = null;
            k.i = null;
        }
        if (this.j != null) {
            final J j;
            if ((j = this.j).e != null) {
                j.e.e();
            }
            if (j.c != null) {
                j.c.b = null;
            }
            if (j.d != null) {
                j.d.b = null;
            }
            j.b = null;
            j.a = null;
            j.c = null;
            j.d = null;
            j.e = null;
            j.f = null;
        }
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        System.runFinalization();
        System.gc();
    }
    
    public void paint(final Graphics graphics) {
        if (!this.o) {
            return;
        }
        int n;
        int n2;
        if (this.d) {
            n = 512;
            n2 = 480;
        }
        else {
            n = 256;
            n2 = 240;
        }
        graphics.setColor(this.c);
        graphics.fillRect(0, 0, n, n2);
        String s;
        if (this.b < 10) {
            s = "  ";
        }
        else if (this.b < 100) {
            s = " ";
        }
        else {
            s = "";
        }
        final String string = "vNES is Loading Game... " + s + this.b + "%";
        graphics.setFont(this.n);
        final int stringWidth = graphics.getFontMetrics(this.n).stringWidth(string);
        final int height = graphics.getFontMetrics(this.n).getHeight();
        graphics.setFont(this.n);
        graphics.setColor(Color.white);
        graphics.drawString(string, n / 2 - stringWidth / 2, n2 / 2 - height / 2);
        graphics.drawString(string, n / 2 - stringWidth / 2, n2 / 2 - height / 2);
        graphics.drawString("vNES © 2006-2009 Jamie Sanders", 12, 448);
        graphics.drawString("For games and updates, visit www.virtualnes.com", 12, 464);
    }
    
    public void update(final Graphics graphics) {
    }
}
