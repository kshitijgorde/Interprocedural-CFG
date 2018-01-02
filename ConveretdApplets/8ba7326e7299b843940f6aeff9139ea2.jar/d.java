import java.util.StringTokenizer;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.applet.Applet;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class d extends Canvas
{
    private int case;
    private int char;
    public Vector[] e;
    public Vector[] int;
    private int d;
    private int b;
    private int m;
    private int c;
    private int else;
    private int i;
    private int g;
    private int j;
    private int new;
    private int if;
    private int do;
    private Image long;
    private Graphics h;
    private Rectangle[] goto;
    private FontMetrics void;
    private Font font;
    private Color l;
    private Color for;
    private Color[] f;
    private Color[] k;
    private MediaTracker byte;
    private Applet null;
    private Image[] n;
    private String[] a;
    private int try;
    
    public d() {
        this.case = 0;
        this.e = new Vector[20];
        this.int = new Vector[20];
        this.goto = new Rectangle[6];
        this.f = new Color[2];
        this.k = new Color[3];
        this.n = new Image[5];
        this.a = new String[300];
    }
    
    public void a(final int b, final int i, final int g) {
        this.i = i;
        this.g = g;
        this.else = 1;
        this.m = 0;
        this.c = 0;
        this.if = this.e[b].size();
        this.b = b;
        this.do = (int)(this.if / this.j + 0.9);
        this.goto[5].x = this.i;
        this.goto[5].y = this.g;
        this.goto[5].width = this.for();
        this.goto[5].height = this.a();
        int height = this.goto[4].height / this.do + 1;
        if (height < 10) {
            height = 10;
        }
        this.n[3] = this.createImage(this.goto[3].width, height);
        this.goto[3].height = height;
        final Graphics graphics = this.n[3].getGraphics();
        graphics.setColor(this.k[1]);
        graphics.fillRect(0, 0, this.goto[3].width, this.goto[3].height);
        graphics.setColor(this.k[2]);
        graphics.fillRect(this.goto[3].width - 1, 0, 1, this.goto[3].height);
        graphics.fillRect(0, this.goto[3].height - 1, this.goto[3].width, 1);
        graphics.setColor(this.k[0]);
        graphics.fillRect(1, 1, this.goto[3].width - 2, this.goto[3].height - 2);
        this.byte.addImage(this.n[3], 15);
        try {
            this.byte.waitForID(15);
        }
        catch (InterruptedException ex) {
            System.out.println("Error waiting for image to load");
        }
    }
    
    public int a() {
        return this.n[4].getHeight(this);
    }
    
    public int for() {
        return this.n[4].getWidth(this);
    }
    
    public String if() {
        return this.int[this.b].elementAt(this.c);
    }
    
    public void a(final Component component, final String s, final String s2, final String s3, final String s4, final String s5) {
        final boolean b = false;
        this.goto[0] = new Rectangle();
        this.goto[1] = new Rectangle();
        this.goto[2] = new Rectangle();
        this.goto[3] = new Rectangle();
        this.goto[4] = new Rectangle();
        this.goto[5] = new Rectangle();
        this.a(s2);
        if (b) {
            return;
        }
        try {
            this.n[4] = Toolkit.getDefaultToolkit().getImage(s);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println("split txt 1");
        this.a(s3, ",");
        try {
            this.n[1] = Toolkit.getDefaultToolkit().getImage(this.a[0]);
        }
        catch (Exception ex2) {
            System.out.println(ex2);
        }
        this.goto[1].x = Integer.parseInt(this.a[1]);
        this.goto[1].y = Integer.parseInt(this.a[2]);
        this.goto[1].width = Integer.parseInt(this.a[3]);
        this.goto[1].height = Integer.parseInt(this.a[4]);
        this.f[0] = new Color(Integer.parseInt(this.a[5], 16));
        System.out.println("split txt 2");
        this.a(s4, ",");
        try {
            this.n[2] = Toolkit.getDefaultToolkit().getImage(this.a[0]);
        }
        catch (Exception ex3) {
            System.out.println(ex3);
        }
        this.goto[2].x = Integer.parseInt(this.a[1]);
        this.goto[2].y = Integer.parseInt(this.a[2]);
        this.goto[2].width = Integer.parseInt(this.a[3]);
        this.goto[2].height = Integer.parseInt(this.a[4]);
        this.f[1] = new Color(Integer.parseInt(this.a[5], 16));
        System.out.println("split txt 3");
        this.a(s5, ",");
        this.goto[3].x = Integer.parseInt(this.a[0]);
        this.goto[3].y = Integer.parseInt(this.a[1]);
        this.goto[3].width = Integer.parseInt(this.a[2]);
        this.goto[3].height = Integer.parseInt(this.a[3]);
        this.goto[4].x = Integer.parseInt(this.a[0]);
        this.goto[4].y = Integer.parseInt(this.a[1]);
        this.goto[4].width = Integer.parseInt(this.a[4]);
        this.goto[4].height = Integer.parseInt(this.a[5]);
        this.k[0] = new Color(Integer.parseInt(this.a[6], 16));
        this.k[1] = new Color(Integer.parseInt(this.a[7], 16));
        this.k[2] = new Color(Integer.parseInt(this.a[8], 16));
        System.out.println("Media Tracker");
        this.byte = new MediaTracker(this);
        for (int i = 1; i < 5; ++i) {
            if (i != 3) {
                this.byte.addImage(this.n[i], i);
            }
        }
        try {
            this.byte.waitForAll();
        }
        catch (InterruptedException ex4) {
            System.out.println("Error waiting for image to load");
        }
        final a a = new a();
        for (int j = 1; j < 5; ++j) {
            if (j != 3) {
                this.n[j] = a.a(this, this.n[j]);
            }
        }
    }
    
    public void a(final String s, final Graphics graphics) {
        int n = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        final String nextToken = stringTokenizer.nextToken();
        final String nextToken2 = stringTokenizer.nextToken();
        final int int1 = Integer.parseInt(stringTokenizer.nextToken());
        if (nextToken2.indexOf("BOLD") >= 0) {
            ++n;
        }
        if (nextToken2.indexOf("ITALIC") >= 0) {
            n += 2;
        }
        this.font = new Font(nextToken, n, int1);
        this.void = graphics.getFontMetrics(this.font);
        this.l = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
        this.for = new Color(Integer.parseInt(stringTokenizer.nextToken(), 16));
        this.j = this.goto[0].height / this.void.getHeight();
        this.new = this.goto[0].height / this.j;
    }
    
    private void a(final String s) {
        this.a(s, ",");
        this.goto[0].x = Integer.parseInt(this.a[0]);
        this.goto[0].y = Integer.parseInt(this.a[1]);
        this.goto[0].width = Integer.parseInt(this.a[2]);
        this.goto[0].height = Integer.parseInt(this.a[3]);
        this.n[0] = this.createImage(this.goto[0].width, this.goto[0].height);
    }
    
    public void do() {
        this.else = 0;
        this.m = -1;
        this.c = -1;
    }
    
    public void paint(final Graphics graphics) {
        if (this.case == 100) {
            if (this.char == 0) {
                this.d = 0;
            }
            graphics.drawImage(this.n[4], this.i, this.g, this);
            graphics.drawImage(this.n[1], this.i + this.goto[1].x, this.g + this.goto[1].y, this);
            graphics.drawImage(this.n[2], this.i + this.goto[2].x, this.g + this.goto[2].y, this);
            if (this.d == 1) {
                graphics.setColor(this.f[0]);
                graphics.drawRect(this.i + this.goto[1].x, this.g + this.goto[1].y, this.goto[1].width, this.goto[1].height);
            }
            if (this.d == 2) {
                graphics.setColor(this.f[1]);
                graphics.drawRect(this.i + this.goto[2].x, this.g + this.goto[2].y, this.goto[2].width, this.goto[2].height);
            }
            final double n = this.m;
            final double n2 = this.if - this.j;
            double n3 = 0.0;
            if (n2 > 0) {
                n3 = n * (this.goto[4].height - this.goto[3].height) / n2;
            }
            final int n4 = (int)n3;
            graphics.drawImage(this.n[3], this.i + this.goto[3].x, this.g + this.goto[4].y + n4, this);
            this.goto[3].y = this.goto[4].y + n4;
            this.h.setColor(this.for);
            this.h.fillRect(0, 0, this.goto[0].width, this.goto[0].height);
            this.h.setFont(this.font);
            for (int i = 0; i < this.j; ++i) {
                if (i + this.m < this.if) {
                    if (this.c == i + this.m) {
                        this.h.setColor(this.l);
                        this.h.fillRect(0, i * this.new, this.goto[0].width, this.new);
                        this.h.setColor(this.for);
                        this.h.drawString((String)this.e[this.b].elementAt(i + this.m), 3, (i + 1) * this.new - 2);
                    }
                    else {
                        this.h.setColor(this.l);
                        this.h.drawString((String)this.e[this.b].elementAt(i + this.m), 3, (i + 1) * this.new - 2);
                    }
                }
            }
            graphics.drawImage(this.n[0], this.i + this.goto[0].x, this.g + this.goto[0].y, this);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void a(final Graphics graphics, final int char1) {
        this.char = char1;
        this.repaint();
    }
    
    public void int(final int n) {
        switch (n) {
            case 1: {
                ++this.c;
                if (this.c - this.m == this.j) {
                    ++this.m;
                    break;
                }
                break;
            }
            case -1: {
                --this.c;
                if (this.c < this.m) {
                    --this.m;
                    break;
                }
                break;
            }
            case 2: {
                this.c += this.j;
                this.m += this.j;
                break;
            }
            case -2: {
                this.c -= this.j;
                this.m -= this.j;
                break;
            }
        }
        if (this.c < 0) {
            this.c = 0;
        }
        if (this.c >= this.if) {
            this.c = this.if - 1;
        }
        this.if(this.m);
    }
    
    public void for(final int n) {
        this.m += n;
        if (this.m > this.if - this.j) {
            this.m = this.if - this.j;
        }
        if (this.m < 0) {
            this.m = 0;
        }
    }
    
    public void do(final int c) {
        if (this.c != c) {
            this.c = c;
        }
    }
    
    public void if(final int m) {
        this.m = m;
        if (this.m > this.if - this.j) {
            this.m = this.if - this.j;
        }
        if (this.m < 0) {
            this.m = 0;
        }
    }
    
    public int a(final int n) {
        final int n2 = n - this.goto[5].y - this.goto[4].y;
        if (n2 < 0 || n2 > this.goto[4].height) {
            return 0;
        }
        this.if(n2 * this.if / this.goto[4].height);
        return 1;
    }
    
    public int do(final int n, final int n2) {
        if (!this.goto[5].inside(n, n2)) {
            return 0;
        }
        if (this.goto[3].inside(n - this.goto[5].x, n2 - this.goto[5].y)) {
            return 1;
        }
        return 0;
    }
    
    public int a(final int n, final int n2) {
        if (!this.goto[5].inside(n, n2)) {
            return 0;
        }
        final int n3 = n - this.goto[5].x;
        final int n4 = n2 - this.goto[5].y;
        this.d = 0;
        if (this.goto[1].inside(n3, n4)) {
            this.for(-1);
            return this.d = 1;
        }
        if (this.goto[2].inside(n3, n4)) {
            this.for(1);
            return this.d = 2;
        }
        return 0;
    }
    
    public int if(final int n, final int n2) {
        this.case = 1;
        if (!this.goto[5].inside(n, n2)) {
            return 0;
        }
        final int n3 = n - this.goto[5].x;
        final int n4 = n2 - this.goto[5].y;
        if (this.goto[0].inside(n3, n4)) {
            this.do(this.m + n4 / this.new);
            return 3;
        }
        if (!this.goto[3].inside(n3, n4) && this.goto[4].inside(n3, n4)) {
            if (n4 < this.goto[3].y) {
                this.for(-this.j);
            }
            else {
                this.for(this.j);
            }
            return 2;
        }
        return 1;
    }
    
    protected void a(final String s, final String s2) {
        this.try = 0;
        int index;
        for (int length = s.length(), i = 0; i < length; i = index + 1, ++this.try) {
            index = s.indexOf(s2, i);
            if (index < 0) {
                index = length;
            }
            this.a[this.try] = s.substring(i, index);
        }
    }
}
