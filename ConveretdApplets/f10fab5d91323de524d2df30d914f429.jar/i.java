import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Random;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class i extends Canvas implements Runnable, MouseListener, MouseMotionListener
{
    CHAT a;
    Random a;
    String[] a;
    Image c;
    Image b;
    Image a;
    char[] a;
    Thread a;
    String b;
    int d;
    int f;
    int i;
    boolean a;
    Font b;
    Color a;
    int g;
    int a;
    int h;
    String c;
    int c;
    String a;
    String d;
    Font a;
    int o;
    int b;
    int m;
    int e;
    int j;
    int n;
    int l;
    boolean b;
    int k;
    
    public i(final CHAT a) {
        this.a = new Random();
        this.a = new String[] { "Serif", "SansSerif", "Monospaced", "SansSerif" };
        this.a = new char[52];
        this.b = "";
        this.d = -5;
        this.f = 0;
        this.i = 0;
        this.a = false;
        this.a = new Color(16777215);
        this.g = 18;
        this.a = 3;
        this.c = "Serif";
        this.c = 30;
        this.a = "Help";
        this.o = 0;
        this.b = 0;
        this.m = 0;
        this.e = 0;
        this.j = 0;
        this.n = 0;
        this.l = 0;
        this.b = true;
        this.a = a;
        (this.a = new Thread(this, "--")).start();
        this.setSize(0, a.b);
    }
    
    void b() {
        this.d = this.a.c[14];
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.a = this.a.getGraphics().getFont();
        final FontMetrics fontMetrics = this.a.getGraphics().getFontMetrics();
        this.o = fontMetrics.stringWidth(this.a);
        this.b = fontMetrics.stringWidth(this.d);
        this.n = this.o + 20 + this.b;
        this.m = fontMetrics.getHeight() + 4;
        this.l = fontMetrics.getMaxDescent() + 3;
        int n = 0;
        for (char c = 'A'; c < '['; ++c, ++n) {
            this.a[n] = c;
        }
        for (char c2 = 'a'; c2 < '{'; ++c2, ++n) {
            this.a[n] = c2;
        }
        this.g = this.a.j;
        this.a = this.a.p;
        final String m = this.a.m;
        this.a = new Color(this.a.q);
        this.b = this.a.a;
        this.c = this.a.b;
        if (m == null) {
            this.c = "";
        }
        else {
            this.c = m;
        }
        this.b = new Font(this.c, this.a, this.g);
        if (this.c < 1) {
            this.c = 1;
            this.setVisible(false);
        }
        this.a();
    }
    
    public void run() {
        final boolean a = d.a;
        try {
            this.b();
            int n = 0;
        Label_0046:
            while (true) {
                Label_0036: {
                    if (!a) {
                        break Label_0036;
                    }
                    Thread.sleep(250L);
                    if (n++ >= 40) {
                        break Label_0046;
                    }
                }
                if (!this.a.r) {
                    continue;
                }
                break;
            }
            final String s = this.a.e[6];
            Label_0196: {
                if (s != null) {
                    final MediaTracker mediaTracker = new MediaTracker(this.a);
                    mediaTracker.addImage(this.b = this.a.getImage(this.a.getCodeBase(), s), 0);
                    mediaTracker.waitForID(0);
                    i i = this;
                    i j = this;
                    if (!a) {
                        if (this.b == null) {
                            break Label_0196;
                        }
                        this.f = this.b.getWidth(this.a);
                        i = this;
                        j = this;
                    }
                    if (!a) {
                        if (j.f > 0) {
                            this.i = this.c - this.b.getHeight(this.a) >> 1;
                            this.f += this.c;
                            this.a = true;
                            if (!a) {
                                break Label_0196;
                            }
                        }
                        i = this;
                    }
                    i.f = 15;
                }
            }
            int width = 0;
            Graphics graphics;
            int n2;
            while (true) {
                while (true) {
                    Label_0222: {
                        if (!a) {
                            break Label_0222;
                        }
                        width = this.a.getSize().width;
                        Thread.sleep(200L);
                    }
                    if (width == 0) {
                        continue;
                    }
                    break;
                }
                this.a = this.a.createImage(width, this.c);
                graphics = this.a.getGraphics();
                n2 = 1000;
                if (a) {
                    continue;
                }
                break;
            }
            i k = null;
            Label_0341: {
                while (true) {
                    Label_0300: {
                        if (!a) {
                            break Label_0300;
                        }
                        Thread.sleep(n2);
                        n2 = 200;
                        ++this.d;
                        this.a(graphics, width);
                        this.repaint();
                    }
                    if (this.b != null) {
                        k = this;
                        if (a || a) {
                            break Label_0341;
                        }
                        if (this.d < this.h) {
                            continue;
                        }
                    }
                    break;
                }
                graphics.dispose();
                this.a.flush();
                k = this;
            }
            k.a = null;
        }
        catch (Exception ex) {}
    }
    
    void a(final Graphics graphics, final int n) {
        if (this.c == null) {
            return;
        }
        final boolean b = true;
        for (int i = 0; i < n; i += 200) {
            graphics.drawImage(this.c, i, 0, this);
        }
        if (this.a) {
            graphics.drawImage(this.b, 15, this.i, this);
        }
        graphics.setColor(this.a);
        graphics.setFont(this.b);
        if (this.b != null) {
            graphics.drawString(this.b, this.f, this.d);
        }
        if (!b) {
            return;
        }
        final int n2 = n - this.b - 10;
        this.e = n - this.n - 5;
        this.j = this.c - 5 - this.m;
        graphics.setColor(this.a.a[38]);
        int n3 = 0;
        if (this.k == 1) {
            if (!this.b) {}
        }
        else if (this.k == 2) {
            graphics.draw3DRect(this.e + this.o + 10, this.j, this.b + 10, this.m, this.b);
            if (!this.b) {
                n3 = 1;
            }
        }
        graphics.setFont(this.a);
        graphics.setColor(this.a.a[37]);
        graphics.drawString(this.d, n2 + n3, this.j - this.l + this.m + n3);
    }
    
    void a() {
        final int c = this.c;
        final int n = 200;
        this.c = this.a.createImage(n, c);
        final Graphics graphics = this.c.getGraphics();
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.b);
        this.h = this.c - (this.c - fontMetrics.getHeight()) / 2 - fontMetrics.getDescent() - fontMetrics.getLeading();
        for (int i = 0; i < 52; ++i) {
            final int nextInt = this.a.nextInt();
            final String s = new String(this.a, i, 1);
            final Font font = new Font(this.a[nextInt & 0x3], nextInt >> 2 & 0x3, (nextInt >> 4 & 0x1F) + 10);
            graphics.setFont(font);
            graphics.setXORMode(new Color(nextInt >> 9 & 0x1F, nextInt >> 14 & 0x1F, nextInt >> 19 & 0x1F));
            final FontMetrics fontMetrics2 = graphics.getFontMetrics(font);
            final int n2 = fontMetrics2.getHeight() >> 1;
            final int n3 = fontMetrics2.stringWidth(s) >> 1;
            graphics.drawString(s, (int)(this.a.nextFloat() * (n + n3)) - n3, (int)(this.a.nextFloat() * (c + n2)));
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.a != null) {
            graphics.drawImage(this.a, 0, 0, this);
        }
        else {
            this.a(graphics, this.getSize().width);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.k != 0) {
            this.b = false;
            this.repaint(this.e, this.j, this.e + this.n, this.j + this.m);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.k != 0) {
            this.b = true;
            this.repaint(this.e, this.j, this.e + this.n, this.j + this.m);
        }
        if (this.k == 2) {
            this.a.a.setLocation(this.a.getLocationOnScreen().x + this.a.getBounds().width >> 1, this.a.getLocationOnScreen().y + this.a.getBounds().height / 3);
            this.a.a.show();
            this.a.a.setSize(200, 135);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.b = true;
        if (this.k != 0) {
            this.k = 0;
            this.repaint(this.e, this.j, this.e + this.n, this.j + this.m);
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int y = mouseEvent.getY();
        final int x = mouseEvent.getX();
        final int k = this.k;
        if (x > this.e && x < this.e + this.n && y > this.j && y < this.j + this.m) {
            if (x < this.e + this.o + 10) {
                this.k = 1;
            }
            else {
                this.k = 2;
            }
        }
        else {
            this.k = 0;
        }
        if (k != this.k) {
            this.repaint(this.e, this.j, this.e + this.n, this.j + this.m);
        }
    }
}
