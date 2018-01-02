import java.awt.Event;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class tovcanvas extends Canvas
{
    int tovpcount;
    int tovlcount;
    int tovtcount;
    float[][] tovp;
    int[][] tovl;
    int[][] tovt;
    float xmid;
    float ymid;
    float zmid;
    float xmin;
    float xmax;
    float ymin;
    float ymax;
    float zmin;
    float zmax;
    float dmax;
    float scale;
    float x2;
    float y2;
    float z2;
    float xx1;
    float yy1;
    float zz1;
    float xx2;
    float yy2;
    float zz2;
    float[] xx;
    float[] yy;
    float[] zz;
    int axis;
    float xoff;
    float yoff;
    double m11;
    double m12;
    double m13;
    double m21;
    double m22;
    double m23;
    double m31;
    double m32;
    double m33;
    double angle;
    int direct;
    int displaymode;
    int c;
    float vamount;
    float vx;
    float vy;
    float vz;
    float v;
    int[] px;
    int[] py;
    boolean sketch;
    Image pic;
    Image oi;
    Graphics og;
    int width;
    int height;
    int R;
    int G;
    int B;
    int[] data;
    static float[][] ff;
    int good;
    int faceR;
    int faceG;
    int faceB;
    int wfR;
    int wfG;
    int wfB;
    int bgR;
    int bgG;
    int bgB;
    boolean copyright;
    int mousex;
    int mousey;
    boolean rotateObj;
    int closedSolid;
    boolean registered;
    
    public tovcanvas(final int width, final int height) {
        this.tovpcount = 0;
        this.tovlcount = 0;
        this.tovtcount = 0;
        this.dmax = 0.0f;
        this.scale = 0.7f;
        this.x2 = 0.0f;
        this.y2 = 0.0f;
        this.z2 = 0.0f;
        this.xx1 = 0.0f;
        this.yy1 = 0.0f;
        this.zz1 = 0.0f;
        this.xx2 = 0.0f;
        this.yy2 = 0.0f;
        this.zz2 = 0.0f;
        this.xx = new float[3];
        this.yy = new float[3];
        this.zz = new float[3];
        this.axis = 0;
        this.m11 = 1.0;
        this.m12 = 0.0;
        this.m13 = 0.0;
        this.m21 = 0.0;
        this.m22 = 1.0;
        this.m23 = 0.0;
        this.m31 = 0.0;
        this.m32 = 0.0;
        this.m33 = 1.0;
        this.angle = 0.17453277777777776;
        this.direct = 1;
        this.displaymode = 0;
        this.c = 0;
        this.vamount = 0.0f;
        this.vx = 0.0f;
        this.vy = 0.0f;
        this.vz = 0.0f;
        this.v = 0.0f;
        this.px = new int[4];
        this.py = new int[4];
        this.sketch = false;
        this.pic = null;
        this.oi = null;
        this.R = 0;
        this.G = 0;
        this.B = 0;
        this.good = 0;
        this.faceR = 255;
        this.faceG = 255;
        this.faceB = 255;
        this.wfR = 0;
        this.wfG = 0;
        this.wfB = 0;
        this.bgR = 0;
        this.bgG = 0;
        this.bgB = 0;
        this.copyright = false;
        this.rotateObj = true;
        this.closedSolid = 1;
        this.registered = false;
        this.width = width;
        this.height = height;
        this.resize(this.width, this.height);
        this.sketch = true;
    }
    
    public tovcanvas(final Image pic, final int width, final int height) {
        this.tovpcount = 0;
        this.tovlcount = 0;
        this.tovtcount = 0;
        this.dmax = 0.0f;
        this.scale = 0.7f;
        this.x2 = 0.0f;
        this.y2 = 0.0f;
        this.z2 = 0.0f;
        this.xx1 = 0.0f;
        this.yy1 = 0.0f;
        this.zz1 = 0.0f;
        this.xx2 = 0.0f;
        this.yy2 = 0.0f;
        this.zz2 = 0.0f;
        this.xx = new float[3];
        this.yy = new float[3];
        this.zz = new float[3];
        this.axis = 0;
        this.m11 = 1.0;
        this.m12 = 0.0;
        this.m13 = 0.0;
        this.m21 = 0.0;
        this.m22 = 1.0;
        this.m23 = 0.0;
        this.m31 = 0.0;
        this.m32 = 0.0;
        this.m33 = 1.0;
        this.angle = 0.17453277777777776;
        this.direct = 1;
        this.displaymode = 0;
        this.c = 0;
        this.vamount = 0.0f;
        this.vx = 0.0f;
        this.vy = 0.0f;
        this.vz = 0.0f;
        this.v = 0.0f;
        this.px = new int[4];
        this.py = new int[4];
        this.sketch = false;
        this.pic = null;
        this.oi = null;
        this.R = 0;
        this.G = 0;
        this.B = 0;
        this.good = 0;
        this.faceR = 255;
        this.faceG = 255;
        this.faceB = 255;
        this.wfR = 0;
        this.wfG = 0;
        this.wfB = 0;
        this.bgR = 0;
        this.bgG = 0;
        this.bgB = 0;
        this.copyright = false;
        this.rotateObj = true;
        this.closedSolid = 1;
        this.registered = false;
        this.width = width;
        this.height = height;
        this.pic = pic;
        this.resize(this.width, this.height);
        this.data = null;
        this.data = new int[width * height];
        this.grabPixels();
        this.sketch = false;
    }
    
    public void allocateSpace() {
        tovcanvas.ff = new float[this.tovtcount][8];
    }
    
    public void resetMatrix() {
        this.m11 = 1.0;
        this.m12 = 0.0;
        this.m13 = 0.0;
        this.m21 = 0.0;
        this.m22 = 1.0;
        this.m23 = 0.0;
        this.m31 = 0.0;
        this.m32 = 0.0;
        this.m33 = 1.0;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void rotMatrix() {
        final double cos = Math.cos(this.direct * this.angle);
        final double sin = Math.sin(this.direct * this.angle);
        double m11 = this.m11;
        double m12 = this.m12;
        double m13 = this.m13;
        double m14 = this.m21;
        double m15 = this.m22;
        double m16 = this.m23;
        double m17 = this.m31;
        double m18 = this.m32;
        double m19 = this.m33;
        if (this.axis == 0) {
            m12 = this.m12 * cos + this.m13 * sin;
            m15 = this.m22 * cos + this.m23 * sin;
            m18 = this.m32 * cos + this.m33 * sin;
            m13 = this.m13 * cos - this.m12 * sin;
            m16 = this.m23 * cos - this.m22 * sin;
            m19 = this.m33 * cos - this.m32 * sin;
        }
        else if (this.axis == 1) {
            m11 = this.m11 * cos + this.m13 * sin;
            m14 = this.m21 * cos + this.m23 * sin;
            m17 = this.m31 * cos + this.m33 * sin;
            m13 = this.m13 * cos - this.m11 * sin;
            m16 = this.m23 * cos - this.m21 * sin;
            m19 = this.m33 * cos - this.m31 * sin;
        }
        else {
            m12 = this.m12 * cos + this.m11 * sin;
            m15 = this.m22 * cos + this.m21 * sin;
            m18 = this.m32 * cos + this.m31 * sin;
            m11 = this.m11 * cos - this.m12 * sin;
            m14 = this.m21 * cos - this.m22 * sin;
            m17 = this.m31 * cos - this.m32 * sin;
        }
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m21 = m14;
        this.m22 = m15;
        this.m23 = m16;
        this.m31 = m17;
        this.m32 = m18;
        this.m33 = m19;
    }
    
    private void pointXform(final float n, final float n2, final float n3) {
        this.x2 = (float)((n * this.m11 + n2 * this.m21 + n3 * this.m31) * this.scale);
        this.y2 = (float)((n * this.m12 + n2 * this.m22 + n3 * this.m32) * this.scale);
        this.z2 = (float)((n * this.m13 + n2 * this.m23 + n3 * this.m33) * this.scale);
    }
    
    public int red(final int n, final int n2) {
        return (this.data[n * this.width + n2] & 0xFF0000) >> 16;
    }
    
    public int green(final int n, final int n2) {
        return (this.data[n * this.width + n2] & 0xFF00) >> 8;
    }
    
    public int blue(final int n, final int n2) {
        return this.data[n * this.width + n2] & 0xFF;
    }
    
    public void setRGB(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.data[n * this.width + n2] = new Color(n3, n4, n5).getRGB();
    }
    
    private void grabPixels() {
        final PixelGrabber pixelGrabber = new PixelGrabber(this.pic, 0, 0, this.width, this.height, this.data, 0, this.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
    }
    
    private void buildImage() {
        this.pic = this.createImage(new MemoryImageSource(this.width, this.height, this.data, 0, this.width));
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void paint(final Graphics graphics) {
        if (this.pic != null && !this.sketch) {
            this.pic.flush();
            this.og.drawImage(this.pic, 0, 0, this);
        }
        else {
            if (this.og == null) {
                this.oi = this.createImage(this.width, this.height);
                this.og = this.oi.getGraphics();
            }
            this.og.setColor(new Color(this.bgR, this.bgG, this.bgB));
            this.og.fillRect(0, 0, this.width, this.height);
            if (this.rotateObj) {
                this.rotMatrix();
            }
            this.good = 0;
            if (this.displaymode == 1) {
                for (int i = 0; i < this.tovtcount; ++i) {
                    for (int j = 0; j < 3; ++j) {
                        this.pointXform(this.tovp[this.tovt[i][j]][0] - this.xmid, this.tovp[this.tovt[i][j]][1] - this.ymid, this.tovp[this.tovt[i][j]][2] - this.zmid);
                        this.xx[j] = this.x2;
                        this.yy[j] = this.y2;
                        this.zz[j] = this.z2;
                    }
                    if (this.visible(this.xx[0], this.yy[0], this.zz[0], this.xx[1], this.yy[1], this.zz[1], this.xx[2], this.yy[2], this.zz[2])) {
                        tovcanvas.ff[this.good][0] = this.xx[0] + this.xoff;
                        tovcanvas.ff[this.good][1] = this.yy[0] + this.yoff;
                        tovcanvas.ff[this.good][2] = this.xx[1] + this.xoff;
                        tovcanvas.ff[this.good][3] = this.yy[1] + this.yoff;
                        tovcanvas.ff[this.good][4] = this.xx[2] + this.xoff;
                        tovcanvas.ff[this.good][5] = this.yy[2] + this.yoff;
                        tovcanvas.ff[this.good][6] = -255.0f * this.vamount + 0.5f;
                        tovcanvas.ff[this.good][7] = (this.zz[0] + this.zz[1] + this.zz[2]) / 3.0f;
                        ++this.good;
                    }
                }
                this.sortByZ();
                for (int k = 0; k < this.good; ++k) {
                    this.c = (int)tovcanvas.ff[k][6];
                    if (this.c >= 0) {
                        this.px[0] = (this.px[3] = (int)tovcanvas.ff[k][0]);
                        this.py[0] = (this.py[3] = (int)tovcanvas.ff[k][1]);
                        this.px[1] = (int)tovcanvas.ff[k][2];
                        this.py[1] = (int)tovcanvas.ff[k][3];
                        this.px[2] = (int)tovcanvas.ff[k][4];
                        this.py[2] = (int)tovcanvas.ff[k][5];
                        this.og.setColor(new Color(this.faceR * this.c / 255, this.faceG * this.c / 255, this.faceB * this.c / 255));
                        this.og.fillPolygon(this.px, this.py, 4);
                    }
                }
            }
            else {
                for (int l = 0; l < this.tovtcount; ++l) {
                    for (int n = 0; n < 3; ++n) {
                        this.pointXform(this.tovp[this.tovt[l][n]][0] - this.xmid, this.tovp[this.tovt[l][n]][1] - this.ymid, this.tovp[this.tovt[l][n]][2] - this.zmid);
                        this.xx[n] = this.x2;
                        this.yy[n] = this.y2;
                        this.zz[n] = this.z2;
                    }
                    tovcanvas.ff[this.good][0] = this.xx[0] + this.xoff;
                    tovcanvas.ff[this.good][1] = this.yy[0] + this.yoff;
                    tovcanvas.ff[this.good][2] = this.xx[1] + this.xoff;
                    tovcanvas.ff[this.good][3] = this.yy[1] + this.yoff;
                    tovcanvas.ff[this.good][4] = this.xx[2] + this.xoff;
                    tovcanvas.ff[this.good][5] = this.yy[2] + this.yoff;
                    ++this.good;
                }
                for (int n2 = 0; n2 < this.good; ++n2) {
                    this.px[0] = (this.px[3] = (int)tovcanvas.ff[n2][0]);
                    this.py[0] = (this.py[3] = (int)tovcanvas.ff[n2][1]);
                    this.px[1] = (int)tovcanvas.ff[n2][2];
                    this.py[1] = (int)tovcanvas.ff[n2][3];
                    this.px[2] = (int)tovcanvas.ff[n2][4];
                    this.py[2] = (int)tovcanvas.ff[n2][5];
                    this.og.setColor(new Color(this.wfR, this.wfG, this.wfB));
                    this.og.drawPolygon(this.px, this.py, 4);
                }
            }
        }
        if (!this.registered && this.copyright) {
            this.og.setFont(new Font("Helvetica", 1, 11));
            final String s = "tov (C) 2001 The J Maker";
            final int n3 = this.width - this.og.getFontMetrics().stringWidth(s) - 3;
            final int n4 = this.height - 3;
            this.og.setColor(Color.blue);
            this.og.drawString(s, n3, n4 - 1);
            this.og.drawString(s, n3, n4 + 1);
            this.og.drawString(s, n3 - 1, n4);
            this.og.drawString(s, n3 + 1, n4);
            this.og.setColor(Color.white);
            this.og.drawString(s, n3, n4);
        }
        graphics.drawImage(this.oi, 0, 0, this);
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.copyright = true;
        this.repaint();
        return true;
    }
    
    public boolean mouseDown(final Event event, final int mousex, final int mousey) {
        this.copyright = true;
        this.mousex = mousex;
        this.mousey = mousey;
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.copyright = false;
        this.repaint();
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        this.copyright = true;
        this.rotateObj = true;
        final double angle = (n2 - this.mousey) * 90.0 * 3.14159 / (this.height * 180.0);
        final double angle2 = (n - this.mousex) * 90.0 * 3.14159 / (this.width * 180.0);
        final int axis = this.axis;
        final int direct = this.direct;
        final double angle3 = this.angle;
        this.axis = 0;
        if (angle >= 0.0) {
            this.angle = angle;
            this.direct = 1;
        }
        else {
            this.angle = -angle;
            this.direct = -1;
        }
        this.rotMatrix();
        this.axis = 1;
        if (angle2 >= 0.0) {
            this.angle = angle2;
            this.direct = 1;
        }
        else {
            this.angle = -angle2;
            this.direct = -1;
        }
        this.rotMatrix();
        this.repaint();
        this.axis = axis;
        this.angle = angle3;
        this.direct = direct;
        return true;
    }
    
    public void sortByZ() {
        if (this.good < 2) {
            return;
        }
        for (int i = 0; i < this.good - 1; ++i) {
            int n = i;
            for (int j = i + 1; j < this.good; ++j) {
                if (tovcanvas.ff[j][7] < tovcanvas.ff[n][7]) {
                    n = j;
                }
            }
            if (i != n) {
                for (int k = 0; k < 8; ++k) {
                    final float n2 = tovcanvas.ff[i][k];
                    tovcanvas.ff[i][k] = tovcanvas.ff[n][k];
                    tovcanvas.ff[n][k] = n2;
                }
            }
        }
    }
    
    public boolean visible(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        this.vx = (n8 - n2) * (n6 - n3) - (n9 - n3) * (n5 - n2);
        this.vy = (n9 - n3) * (n4 - n) - (n7 - n) * (n6 - n3);
        this.vz = (n7 - n) * (n5 - n2) - (n8 - n2) * (n4 - n);
        this.v = (float)Math.sqrt(this.vx * this.vx + this.vy * this.vy + this.vz * this.vz);
        if (this.v > 0.0f) {
            this.vamount = this.vz / this.v;
        }
        else {
            this.vamount = 0.0f;
        }
        return this.vamount <= 0.0f;
    }
}
