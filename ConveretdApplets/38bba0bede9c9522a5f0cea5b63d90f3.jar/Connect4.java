import java.awt.Event;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Connect4 extends Applet
{
    private a a;
    private int b;
    private int c;
    private int[] d;
    private int e;
    private int[] f;
    private int g;
    private boolean h;
    private int i;
    private int j;
    private boolean k;
    private int l;
    private Font m;
    private StringBuffer n;
    private Graphics o;
    private int p;
    
    public void init() {
        this.a = new a(this);
        this.d[0] = a.b(0, 0);
        this.f[0] = 0;
        this.c = 4;
        this.h = false;
        this.g = 0;
        this.j = -1;
        this.i = -1;
        this.k = true;
        this.n = new StringBuffer("Start your game!");
        if (this.o == null) {
            this.o = this.getGraphics();
        }
        this.p = 0;
    }
    
    private boolean a(final int n) {
        return this.p == n;
    }
    
    public final void a(final String s) {
        final Dimension size;
        final int n = ((size = this.size()).width - 10) / 7;
        final int l = size.height / 8;
        this.o.setColor(Color.white);
        this.o.fillRect(5, l * 7 + 1, n * 7, l - 5 - 1);
        if (this.l != l) {
            this.l = l;
            this.m = new Font("Dialog", 0, l * 5 / 10);
        }
        this.o.setFont(this.m);
        this.n = new StringBuffer(s);
        this.o.setColor(Color.black);
        this.o.drawString(this.n.toString(), 15, l * 7 + l * 5 / 8);
        for (int n2 = 4; n2 < 9 && n2 <= this.c; ++n2) {
            this.o.setColor(Color.green);
            this.o.fillRect(n * 7 - 5 - 20, l * 8 - 5 - (n2 - 3 << 2) - 1, 22, 3);
        }
    }
    
    private void a(final int n, final int n2) {
        final Dimension size;
        final int n3 = ((size = this.size()).width - 10) / 7;
        final int n4 = size.height / 8;
        final int n5 = 5 + n3 * n + n3 / 8;
        final int n6 = size.height - (n2 + 2) * n4 + n4 / 8;
        final int n7 = n3 * 3 / 4;
        final int n8 = n4 * 3 / 4;
        Color color = null;
        switch (this.a.c(n2, n)) {
            case 1: {
                color = Color.green;
                break;
            }
            case -1: {
                color = Color.red;
                break;
            }
            default: {
                color = Color.white;
                break;
            }
        }
        this.o.setColor(color);
        this.o.fillOval(n5, n6, n7, n8);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size;
        final int n = ((size = this.size()).width - 10) / 7;
        final int n2 = size.height / 8;
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, n * 7 + 10 - 1, (n2 << 3) - 1);
        graphics.setColor(Color.blue.darker().darker());
        graphics.fillRect(5, n2, n * 7, n2 * 6);
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 7; ++j) {
                this.a(j, i);
            }
        }
        this.a(this.n.toString());
        graphics.setColor(Color.gray);
        for (int k = 0; k < 5; ++k) {
            graphics.drawLine(k, k, k, (n2 << 3) - k - 1);
            graphics.drawLine(k + 1, k, n * 7 + 10 - 1, k);
        }
        graphics.setColor(Color.darkGray);
        for (int l = 0; l < 5; ++l) {
            graphics.drawLine(n * 7 - l + 10 - 1, l + 1, n * 7 - l + 10 - 1, (n2 << 3) - l - 1);
            graphics.drawLine(l, (n2 << 3) - l - 1, n * 7 + 10 - 1, (n2 << 3) - l - 1);
        }
    }
    
    private int b(final int n, final int n2) {
        final int a = this.a.a(n, n2);
        this.a(n2, this.a.b(a));
        if (n == -1) {
            this.i = -1;
        }
        else {
            this.j = -1;
        }
        this.c(n2, n);
        return a;
    }
    
    public boolean mouseUp(final Event event, final int n, int p3) {
        if (this.a(2)) {
            this.init();
            this.repaint();
            return true;
        }
        p3 = 1;
        this.p = p3;
        Label_0391: {
            final int n2;
            if (this.a.a(this.d[0], this.f) == 0 && (n2 = (n - 5) * 7 / this.size().width) >= 0 && n2 <= 6 && this.a.a(n2)) {
                this.b = this.b(-1, n2);
                switch (this.e = this.a.a(this.b, this.f)) {
                    case -1: {
                        this.a("It ends in a tie! (click to restart)");
                        p3 = 2;
                        this.p = p3;
                        break;
                    }
                    case 1: {
                        this.a("You won! (click to restart)");
                        p3 = 2;
                        this.p = p3;
                        break;
                    }
                    case 0: {
                        this.e = this.a.a(1, this.d, this.c);
                        this.b = this.b(1, this.d[0]);
                        Label_0294: {
                            if (this.a.a() < 6000) {
                                if (++this.g != 2) {
                                    break Label_0294;
                                }
                                ++this.c;
                            }
                            else if (this.a.a() > 40000) {
                                --this.c;
                                this.g = 0;
                                this.h = true;
                                break Label_0294;
                            }
                            this.g = 0;
                        }
                        switch (this.e) {
                            case -1: {
                                this.a("Your move!");
                                break Label_0391;
                            }
                            case 1: {
                                if (this.a.a(this.b, this.f) != 0) {
                                    this.a("I won! (click to restart)");
                                    p3 = 2;
                                    this.p = p3;
                                    break Label_0391;
                                }
                                if (!this.h) {
                                    this.a("I'm going to win!");
                                    break Label_0391;
                                }
                                break;
                            }
                        }
                        this.a("Your move!");
                        break;
                    }
                }
            }
        }
        if (this.a(1)) {
            p3 = 0;
            this.p = p3;
        }
        return true;
    }
    
    private static void a(final Graphics graphics, final int n, final int n2, final int n3, final Color color, final boolean b) {
        final int[] array = new int[8];
        final int[] array2 = new int[8];
        graphics.setColor(color);
        array[0] = 5 + n3 * n + n / 2;
        array2[0] = n2 * 3 / 4;
        array[1] = array[0] + n / 5;
        array2[1] = array2[0] - n2 / 4;
        array[2] = array[0] + n / 10;
        array2[2] = array2[1];
        array[3] = array[2];
        array2[3] = array2[0] - n2 / 2;
        array[4] = array[0] - n / 10;
        array2[4] = array2[3];
        array[5] = array[4];
        array2[5] = array2[1];
        array[6] = array[0] - n / 5;
        array2[6] = array2[1];
        array[7] = array[0];
        array2[7] = array2[0];
        if (b) {
            graphics.fillPolygon(array, array2, 8);
            return;
        }
        graphics.drawPolygon(array, array2, 8);
    }
    
    private void c(final int n, final int n2) {
        if (n >= 0 && n < 7) {
            final boolean a = this.a.a(n);
            final Dimension size;
            final int n3 = ((size = this.size()).width - 10) / 7;
            final int n4 = size.height / 8;
            this.o.setColor(Color.white);
            this.o.fillRect(5, 5, n3 * 7, n4 - 5);
            if (n2 == -1) {
                this.j = n;
                this.k = a;
            }
            else if (n2 == 1) {
                this.i = n;
            }
            else {
                this.j = -1;
                this.i = -1;
            }
            if (this.j != -1) {
                Color color;
                if (this.k) {
                    color = Color.red;
                }
                else {
                    color = Color.gray;
                }
                a(this.o, n3, n4, this.j, color, true);
            }
            if (this.i != -1) {
                a(this.o, n3, n4, this.i, Color.green, this.i != this.j);
            }
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        final int n3;
        if (this.a(0) && (n3 = (n - 5) * 7 / this.size().width) != this.j) {
            this.c(n3, -1);
        }
        return true;
    }
    
    public String getAppletInfo() {
        return "Connect4 - Java (beta) Applet by Sven Wiebus, Dec. 1995";
    }
    
    public Connect4() {
        this.d = new int[1];
        this.f = new int[1];
        this.h = false;
        this.i = -1;
        this.j = -1;
        this.k = false;
        this.p = -1;
    }
}
