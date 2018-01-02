import java.awt.event.MouseEvent;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class g extends Canvas implements MouseListener, MouseMotionListener, Runnable
{
    CHAT a;
    int b;
    Image[] a;
    Image b;
    Image a;
    boolean a;
    boolean b;
    Graphics a;
    int e;
    int d;
    Thread a;
    int a;
    int c;
    
    public g(final CHAT a) {
        this.b = 0;
        this.a = false;
        this.e = 0;
        this.a = a;
        if (a.h) {
            this.b = true;
        }
        this.setSize(0, 24);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        (this.a = new Thread(this, "-")).start();
    }
    
    public void run() {
        try {
            int n = 0;
            while (!this.a.r) {
                Thread.sleep(250L);
                if (n++ >= 40) {
                    break;
                }
            }
            this.a();
            this.repaint();
            final String s = this.a.e[4];
            if (s != null) {
                this.a.b = this.a.getImage(this.a.getCodeBase(), s);
                final MediaTracker mediaTracker = new MediaTracker(this.a);
                mediaTracker.addImage(this.a.b, 0);
                mediaTracker.waitForID(0);
                this.a.a.repaint();
            }
        }
        catch (Exception ex) {}
    }
    
    public void a() {
        final boolean a = d.a;
        final MediaTracker mediaTracker = new MediaTracker(this.a);
        this.a = this.a.getImage(this.a.getCodeBase(), this.a.e[3]);
        Label_0083: {
            if (a) {
                break Label_0083;
            }
            if (this.a == null) {
                this.a = this.a.createImage(1, 1);
            }
            mediaTracker.addImage(this.a, 0);
            try {
                mediaTracker.waitForID(0);
            }
            catch (Exception ex) {}
        }
        final int width = this.a.getWidth(this.a);
        int height = this.a.getHeight(this.a);
        final int n = 17;
        final int n3;
        int n2 = n3 = width / n;
        g g = null;
        Label_0237: {
            if (!a) {
                if (n3 > 64) {
                    n2 = 64;
                }
                g = this;
                if (a) {
                    break Label_0237;
                }
                final boolean b = this.b;
            }
            if (n3 != 0) {
                final int b2 = n2;
                this.e = b2;
                this.a = b2;
                this.b = b2;
            }
            this.d = n2 + 1;
            this.setSize(this.c = 21 * this.d + 9, 24);
            this.a = new Image[this.d];
            this.b = this.a.createImage(21 * this.d, 21);
            this.a = this.b.getGraphics();
            g = this;
        }
        Graphics graphics = g.b.getGraphics();
        Color color = new Color(this.a.b[36]);
        int n4 = 0;
        while (true) {
            while (true) {
                Label_0345: {
                    if (!a) {
                        break Label_0345;
                    }
                    this.a[n4] = this.a.createImage(17, 17);
                    Label_0342: {
                        if (a) {
                            break Label_0342;
                        }
                        final int n5;
                        if (n5 == 1) {
                            color = new Color(16645630);
                        }
                        graphics = this.a[n4].getGraphics();
                        graphics.setColor(color);
                        graphics.fillRect(0, 0, 17, 17);
                    }
                    ++n4;
                }
                if (n4 < n2 + 1) {
                    continue;
                }
                break;
            }
            graphics.finalize();
            int n8;
            int n7;
            int n6;
            final int n5 = n6 = (n7 = (n8 = n2));
            if (!a) {
                if (!a) {
                    if (n5 <= 0) {
                        return;
                    }
                    n7 = (n6 = (n8 = height));
                }
                if (!a) {
                    if (n6 <= 0) {
                        return;
                    }
                    n8 = (n7 = height);
                }
                final int n9 = n;
                if (!a) {
                    if (n7 > n9) {
                        height = n;
                    }
                    this.a = true;
                    n8 = width;
                }
                final int[] array = new int[n8 * n9];
                final PixelGrabber pixelGrabber = new PixelGrabber(this.a, 0, 0, width, height, array, 0, width);
                try {
                    pixelGrabber.grabPixels();
                }
                catch (InterruptedException ex2) {}
                this.a.flush();
                this.a = null;
                int n10 = 0;
            Label_0501_Outer:
                while (true) {
                    Label_0546: {
                        if (!a) {
                            break Label_0546;
                        }
                        final int[] array2 = new int[n * height];
                        int n11 = 0;
                        while (true) {
                            while (true) {
                                Label_0504: {
                                    if (!a) {
                                        break Label_0504;
                                    }
                                    System.arraycopy(array, n10 * n + n11 * width, array2, n11 * n, n);
                                    ++n11;
                                }
                                if (n11 < height) {
                                    continue Label_0501_Outer;
                                }
                                break;
                            }
                            this.a[n10 + 1] = this.createImage(new MemoryImageSource(n, height, array2, 0, n));
                            if (a) {
                                continue;
                            }
                            break;
                        }
                        ++n10;
                    }
                    if (n10 < n2) {
                        continue;
                    }
                    break;
                }
                this.a.a.d = this.d;
                this.a.a.repaint();
                return;
            }
            continue;
        }
    }
    
    public String a() {
        int b = this.b;
        if (this.b) {
            b = this.d - this.b - 1;
        }
        if (b > 9) {
            return String.valueOf((char)(b + 48));
        }
        return String.valueOf(b);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.a) {
            return;
        }
        this.a.setColor(this.a.a[36]);
        this.a.fillRect(0, 0, this.c, 21);
        for (int i = 0; i < this.d; ++i) {
            if (this.b == i) {
                this.a.draw3DRect(i * 21, 0, 20, 20, false);
            }
            else if (this.a == i && this.b != i) {
                this.a.draw3DRect(i * 21, 0, 20, 20, true);
            }
        }
        for (int j = 0; j < this.d; ++j) {
            int n;
            if (this.b) {
                n = this.d - j - 1;
            }
            else {
                n = j;
            }
            if (this.b == j) {
                this.a.drawImage(this.a[n], 3 + j * 21, 3, null);
            }
            else {
                this.a.drawImage(this.a[n], 2 + j * 21, 2, null);
            }
        }
        graphics.drawImage(this.b, 9, 2, null);
    }
    
    public void b() {
        this.b = this.e;
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.a != -1) {
            this.b = this.a;
            this.repaint();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.a.h();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.a = -1;
        this.repaint();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int y = mouseEvent.getY();
        final int x = mouseEvent.getX();
        if (y > 3 && y < 21 && x > 10 && x < this.c) {
            final int a = (x - 9) / 21;
            if (this.a != a) {
                this.a = a;
                this.repaint();
            }
        }
        else if (this.a != -1) {
            this.a = -1;
            this.repaint();
        }
    }
}
