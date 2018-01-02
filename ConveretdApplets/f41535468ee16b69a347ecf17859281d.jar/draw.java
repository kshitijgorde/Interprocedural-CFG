import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

class draw
{
    static final int S_XSIZE = 300;
    static final int S_YSIZE = 300;
    static final double BSIZE = 50.0;
    Random rd;
    point3d[] pts;
    int[] st;
    int shade;
    Applet app;
    Image offscreen;
    Graphics off;
    
    draw(final Applet app) {
        this.rd = new Random();
        this.pts = new point3d[14];
        this.st = new int[14];
        this.app = app;
        this.offscreen = app.createImage(300, 300);
        this.off = this.offscreen.getGraphics();
    }
    
    void start() {
        this.pts[0] = new point3d(-50.0, 50.0, 50.0, this.rand(6));
        this.pts[1] = new point3d(-50.0, -50.0, 50.0, this.rand(6));
        this.pts[2] = new point3d(50.0, 50.0, 50.0, this.rand(6));
        this.pts[3] = new point3d(50.0, -50.0, 50.0, this.rand(6));
        this.pts[4] = new point3d(-50.0, 50.0, -50.0, this.rand(6));
        this.pts[5] = new point3d(-50.0, -50.0, -50.0, this.rand(6));
        this.pts[6] = new point3d(50.0, 50.0, -50.0, this.rand(6));
        this.pts[7] = new point3d(50.0, -50.0, -50.0, this.rand(6));
        this.pts[8] = new point3d(0.0, 0.0, 25.0, this.rand(6));
        this.pts[9] = new point3d(0.0, 0.0, -25.0, this.rand(6));
        this.pts[10] = new point3d(25.0, 0.0, 0.0, this.rand(6));
        this.pts[11] = new point3d(-25.0, 0.0, 0.0, this.rand(6));
        this.pts[12] = new point3d(0.0, 25.0, 0.0, this.rand(6));
        this.pts[13] = new point3d(0.0, -25.0, 0.0, this.rand(6));
    }
    
    int rand(final int n) {
        return Math.abs(this.rd.nextInt() % n);
    }
    
    void clear() {
        this.off.setColor(Color.black);
        this.off.fillRect(0, 0, 300, 300);
    }
    
    int setOffColor(final int col, final int c) {
        switch (c) {
            case 0: {
                return 0xFF000000 | col << 16;
            }
            case 1: {
                return 0xFF000000 | col << 8;
            }
            case 2: {
                return 0xFF000000 | col;
            }
            case 3: {
                return 0xFF000000 | col << 16 | col << 8;
            }
            case 4: {
                return 0xFF000000 | col << 8 | col;
            }
            case 5: {
                return 0xFF000000 | col << 16 | col;
            }
            default: {
                return 0xFF000000 | col;
            }
        }
    }
    
    void drawball() {
        if (this.shade != 0) {
            for (int i = 0; i < 14; ++i) {
                final point3d p = this.pts[this.st[i]];
                int SC;
                if ((SC = 60 + p.zp / 2) < 60) {
                    SC = 60;
                }
                int SIZE;
                if ((SIZE = 40 + p.zp / 4) < 2) {
                    SIZE = 2;
                }
                int col;
                int cr = this.setOffColor(col = SC, p.color);
                final int ad = this.setOffColor(8, p.color);
                final int max = SIZE / 2;
                int wd = SIZE;
                int wx = p.xp - max;
                int wy = p.yp - max;
                for (int j = max - 2; j > 0 && col <= 247; --j, col += 8) {
                    this.off.setColor(new Color(cr));
                    this.off.fillOval(wx, wy, wd, wd);
                    cr += ad;
                    wd -= 2;
                    ++wx;
                    ++wy;
                }
            }
            return;
        }
        for (int i = 0; i < 14; ++i) {
            final point3d p = this.pts[this.st[i]];
            int SC;
            if ((SC = 60 + p.zp / 2) < 60) {
                SC = 60;
            }
            int SIZE;
            if ((SIZE = 40 + p.zp / 4) < 2) {
                SIZE = 2;
            }
            this.off.setColor(new Color(this.setOffColor(SC + SIZE / 2, p.color)));
            this.off.fillOval(p.xp - SIZE / 2, p.yp - SIZE / 2, SIZE, SIZE);
        }
    }
}
