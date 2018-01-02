import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Julia3D extends Applet implements MouseListener, MouseMotionListener
{
    int MaxIt;
    int n;
    int n1;
    int h;
    int w;
    int h2;
    int w2;
    int mx0;
    int my0;
    int[] xPol;
    int[] yPol;
    double fiX;
    double fiY;
    double dfi;
    double scale;
    double m20;
    double m21;
    double m22;
    double re0;
    double im0;
    double del;
    double cr;
    double ci;
    double[][][] vert;
    double[][][] vert1;
    double[][][][] Norm;
    double[][][] Norm1z;
    Image buffImage;
    Graphics buffGraphics;
    Color[] col;
    boolean painted;
    
    public Julia3D() {
        this.MaxIt = 300;
        this.n = 2;
        this.fiX = 0.2;
        this.fiY = 0.3;
        this.dfi = 0.01;
        this.scale = 0.8;
        this.re0 = -1.5;
        this.im0 = -1.5;
        this.del = 3.0;
        this.cr = 0.35;
        this.ci = 0.49;
    }
    
    public double Fun(final double n, final double n2) {
        double n3 = n2;
        double n4 = n;
        double n5 = n3 * n3;
        double n6 = n4 * n4;
        int n7 = 0;
        do {
            n3 = n4 * (n3 + n3) + this.ci;
            n4 = n6 - n5 + this.cr;
            n6 = n4 * n4;
            n5 = n3 * n3;
            ++n7;
        } while (n6 + n5 < 4.0 && n7 < this.MaxIt);
        return 40.0 * Math.log(n7) - 100.0;
    }
    
    public void init() {
        this.w = this.getSize().width;
        this.h = this.getSize().height;
        this.w2 = this.w / 2;
        this.h2 = this.h / 2;
        final String parameter = this.getParameter("N");
        if (parameter != null) {
            this.n = Integer.parseInt(parameter);
        }
        this.xPol = new int[3];
        this.yPol = new int[3];
        this.buffImage = this.createImage(this.w, this.h);
        this.buffGraphics = this.buffImage.getGraphics();
        this.col = new Color[256];
        for (int i = 0; i < 256; ++i) {
            this.col[i] = new Color(i, i, i);
        }
        final String parameter2 = this.getParameter("C");
        if (parameter2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter2, " ,");
            this.cr = Double.valueOf(stringTokenizer.nextToken());
            this.ci = Double.valueOf(stringTokenizer.nextToken());
        }
        final String parameter3 = this.getParameter("bgColor");
        if (parameter3 != null) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter3);
            this.setBackground(new Color(Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken())));
        }
        else {
            this.setBackground(new Color(255, 255, 255));
        }
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setup();
    }
    
    public void setup() {
        this.n1 = this.n - 1;
        this.vert = new double[this.n][this.n][3];
        this.vert1 = new double[this.n][this.n][2];
        final double n = this.n / 2.0;
        final double n2 = this.w / this.n1;
        final double n3 = this.del / this.n1;
        for (int i = 0; i < this.n; ++i) {
            for (int j = 0; j < this.n; ++j) {
                this.vert[i][j][0] = n2 * i - this.w2;
                this.vert[i][j][2] = n2 * j - this.w2;
                this.vert[i][j][1] = this.Fun(this.re0 + n3 * i, this.im0 + n3 * j);
            }
        }
        this.Norm = new double[this.n][this.n][2][3];
        this.Norm1z = new double[this.n][this.n][2];
        for (int k = 0; k < this.n1; ++k) {
            for (int l = 0; l < this.n1; ++l) {
                this.Norm[k][l][0][0] = this.vert[k][l][1] - this.vert[k + 1][l][1];
                this.Norm[k][l][0][1] = n2;
                this.Norm[k][l][0][2] = this.vert[k + 1][l][1] - this.vert[k + 1][l + 1][1];
                final double n4 = Math.sqrt(this.Norm[k][l][0][0] * this.Norm[k][l][0][0] + this.Norm[k][l][0][1] * this.Norm[k][l][0][1] + this.Norm[k][l][0][2] * this.Norm[k][l][0][2]) / 255.5;
                final double[] array = this.Norm[k][l][0];
                final int n5 = 0;
                array[n5] /= n4;
                final double[] array2 = this.Norm[k][l][0];
                final int n6 = 1;
                array2[n6] /= n4;
                final double[] array3 = this.Norm[k][l][0];
                final int n7 = 2;
                array3[n7] /= n4;
                this.Norm[k][l][1][0] = this.vert[k][l + 1][1] - this.vert[k + 1][l + 1][1];
                this.Norm[k][l][1][1] = n2;
                this.Norm[k][l][1][2] = this.vert[k][l][1] - this.vert[k][l + 1][1];
                final double n8 = Math.sqrt(this.Norm[k][l][1][0] * this.Norm[k][l][1][0] + this.Norm[k][l][1][1] * this.Norm[k][l][1][1] + this.Norm[k][l][1][2] * this.Norm[k][l][1][2]) / 255.5;
                final double[] array4 = this.Norm[k][l][1];
                final int n9 = 0;
                array4[n9] /= n8;
                final double[] array5 = this.Norm[k][l][1];
                final int n10 = 1;
                array5[n10] /= n8;
                final double[] array6 = this.Norm[k][l][1];
                final int n11 = 2;
                array6[n11] /= n8;
            }
        }
        this.rotate();
    }
    
    public void destroy() {
        this.removeMouseListener(this);
        this.removeMouseMotionListener(this);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.mx0 = mouseEvent.getX();
        this.my0 = mouseEvent.getY();
        if (mouseEvent.isControlDown()) {
            this.n /= 2;
            if (this.n < 4) {
                this.n = 4;
            }
            this.setup();
            this.repaint();
        }
        if (mouseEvent.isAltDown()) {
            this.n *= 2;
            this.setup();
            this.repaint();
        }
        mouseEvent.consume();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (mouseEvent.isShiftDown()) {
            this.scale *= Math.exp(-(y - this.my0) / this.w);
        }
        else {
            this.fiX += this.dfi * (y - this.my0);
        }
        this.fiY += this.dfi * (x - this.mx0);
        this.mx0 = x;
        this.my0 = y;
        this.rotate();
        this.repaint();
        mouseEvent.consume();
    }
    
    public void rotate() {
        final double cos = Math.cos(this.fiX);
        final double cos2 = Math.cos(this.fiY);
        final double sin = Math.sin(this.fiX);
        final double sin2 = Math.sin(this.fiY);
        final double n = this.scale * cos2;
        final double n2 = this.scale * sin2;
        final double n3 = this.scale * sin * sin2;
        final double n4 = this.scale * cos;
        final double n5 = -this.scale * sin * cos2;
        this.m20 = -cos * sin2;
        this.m21 = sin;
        this.m22 = cos * cos2;
        for (int i = 0; i < this.n; ++i) {
            for (int j = 0; j < this.n; ++j) {
                this.vert1[i][j][0] = n * this.vert[i][j][0] + n2 * this.vert[i][j][2];
                this.vert1[i][j][1] = n3 * this.vert[i][j][0] + n4 * this.vert[i][j][1] + n5 * this.vert[i][j][2];
            }
        }
        for (int k = 0; k < this.n1; ++k) {
            for (int l = 0; l < this.n1; ++l) {
                for (int n6 = 0; n6 < 2; ++n6) {
                    this.Norm1z[k][l][n6] = this.m20 * this.Norm[k][l][n6][0] + this.m21 * this.Norm[k][l][n6][1] + this.m22 * this.Norm[k][l][n6][2];
                }
            }
        }
        this.painted = false;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        if (!this.painted) {
            this.buffGraphics.clearRect(0, 0, this.w, this.h);
            int n1 = 0;
            int n2 = this.n1;
            int n3 = 1;
            int n4 = 0;
            int n5 = this.n1;
            int n6 = 1;
            if (this.m20 < 0.0) {
                n1 = this.n1;
                n2 = -1;
                n3 = -1;
            }
            if (this.m22 < 0.0) {
                n4 = this.n1;
                n5 = -1;
                n6 = -1;
            }
            for (int i = n1; i != n2; i += n3) {
                for (int j = n4; j != n5; j += n6) {
                    if (this.Norm1z[i][j][0] > 0.0) {
                        this.xPol[0] = this.w2 + (int)this.vert1[i][j][0];
                        this.xPol[1] = this.w2 + (int)this.vert1[i + 1][j][0];
                        this.xPol[2] = this.w2 + (int)this.vert1[i + 1][j + 1][0];
                        this.yPol[0] = this.h2 - (int)this.vert1[i][j][1];
                        this.yPol[1] = this.h2 - (int)this.vert1[i + 1][j][1];
                        this.yPol[2] = this.h2 - (int)this.vert1[i + 1][j + 1][1];
                        this.buffGraphics.setColor(this.col[(int)this.Norm1z[i][j][0]]);
                        this.buffGraphics.fillPolygon(this.xPol, this.yPol, 3);
                    }
                    if (this.Norm1z[i][j][1] > 0.0) {
                        this.xPol[0] = this.w2 + (int)this.vert1[i][j][0];
                        this.xPol[1] = this.w2 + (int)this.vert1[i][j + 1][0];
                        this.xPol[2] = this.w2 + (int)this.vert1[i + 1][j + 1][0];
                        this.yPol[0] = this.h2 - (int)this.vert1[i][j][1];
                        this.yPol[1] = this.h2 - (int)this.vert1[i][j + 1][1];
                        this.yPol[2] = this.h2 - (int)this.vert1[i + 1][j + 1][1];
                        this.buffGraphics.setColor(this.col[(int)this.Norm1z[i][j][1]]);
                        this.buffGraphics.fillPolygon(this.xPol, this.yPol, 3);
                    }
                }
            }
            this.painted = true;
        }
        graphics.drawImage(this.buffImage, 0, 0, this);
        this.showStatus("n=" + this.n);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
