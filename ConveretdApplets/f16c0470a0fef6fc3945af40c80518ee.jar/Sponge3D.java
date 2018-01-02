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

public class Sponge3D extends Applet implements MouseListener, MouseMotionListener
{
    int nVert;
    int nFace;
    int it;
    int h;
    int w;
    int h2;
    int w2;
    int mx0;
    int my0;
    int[] xPol;
    int[] yPol;
    int[] Zsort;
    double fiX;
    double fiY;
    double dfi;
    double scale;
    double scl;
    int[][] face;
    double[][] vert;
    double[][] vert1;
    double[][] Norm;
    double[] Norm1z;
    Image buffImage;
    Graphics buffGraphics;
    Color[] col;
    boolean painted;
    
    public Sponge3D() {
        this.fiX = 0.2;
        this.fiY = 0.3;
        this.dfi = 0.01;
    }
    
    public void init() {
        this.w = this.getSize().width;
        this.h = this.getSize().height;
        this.w2 = this.w / 2;
        this.h2 = this.h / 2;
        final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("par"));
        this.it = Integer.parseInt(stringTokenizer.nextToken());
        this.scl = Double.valueOf(stringTokenizer.nextToken());
        final StringTokenizer stringTokenizer2 = new StringTokenizer(this.getParameter("N"));
        this.nVert = Integer.parseInt(stringTokenizer2.nextToken());
        this.nFace = Integer.parseInt(stringTokenizer2.nextToken());
        this.vert = new double[this.nVert][3];
        this.vert1 = new double[this.nVert][3];
        final StringTokenizer stringTokenizer3 = new StringTokenizer(this.getParameter("Vert"), " ,");
        double n = 0.0;
        for (int i = 0; i < this.nVert; ++i) {
            this.vert[i][0] = Double.valueOf(stringTokenizer3.nextToken());
            this.vert[i][1] = Double.valueOf(stringTokenizer3.nextToken());
            this.vert[i][2] = Double.valueOf(stringTokenizer3.nextToken());
            final double n2 = this.vert[i][0] * this.vert[i][0] + this.vert[i][1] * this.vert[i][1] + this.vert[i][2] * this.vert[i][2];
            if (n < n2) {
                n = n2;
            }
        }
        this.scale = this.w2 / Math.sqrt(n);
        this.face = new int[this.nFace][];
        final int[] array = new int[30];
        this.Zsort = new int[this.nVert];
        for (int j = 0; j < this.nVert; ++j) {
            this.Zsort[j] = j;
        }
        final StringTokenizer stringTokenizer4 = new StringTokenizer(this.getParameter("Faces"));
        for (int k = 0; k < this.nFace; ++k) {
            int n3 = 0;
            Label_0412: {
                break Label_0412;
                int l;
                do {
                    ++n3;
                    final int[] array2 = array;
                    final int n4 = n3;
                    l = Integer.parseInt(stringTokenizer4.nextToken());
                    array2[n4] = l;
                } while (l != -1);
            }
            this.face[k] = new int[n3];
            for (int n5 = 0; n5 < n3; ++n5) {
                this.face[k][n5] = array[n5];
            }
        }
        this.buffImage = this.createImage(this.w, this.h);
        this.buffGraphics = this.buffImage.getGraphics();
        this.col = new Color[256];
        this.Norm = new double[this.nFace][3];
        this.Norm1z = new double[this.nFace];
        for (int n6 = 0; n6 < this.nFace; ++n6) {
            this.Norm[n6][0] = (this.vert[this.face[n6][1]][1] - this.vert[this.face[n6][0]][1]) * (this.vert[this.face[n6][2]][2] - this.vert[this.face[n6][1]][2]) - (this.vert[this.face[n6][2]][1] - this.vert[this.face[n6][1]][1]) * (this.vert[this.face[n6][1]][2] - this.vert[this.face[n6][0]][2]);
            this.Norm[n6][1] = -(this.vert[this.face[n6][1]][0] - this.vert[this.face[n6][0]][0]) * (this.vert[this.face[n6][2]][2] - this.vert[this.face[n6][1]][2]) + (this.vert[this.face[n6][2]][0] - this.vert[this.face[n6][1]][0]) * (this.vert[this.face[n6][1]][2] - this.vert[this.face[n6][0]][2]);
            this.Norm[n6][2] = (this.vert[this.face[n6][1]][0] - this.vert[this.face[n6][0]][0]) * (this.vert[this.face[n6][2]][1] - this.vert[this.face[n6][1]][1]) - (this.vert[this.face[n6][2]][0] - this.vert[this.face[n6][1]][0]) * (this.vert[this.face[n6][1]][1] - this.vert[this.face[n6][0]][1]);
            final double n7 = Math.sqrt(this.Norm[n6][0] * this.Norm[n6][0] + this.Norm[n6][1] * this.Norm[n6][1] + this.Norm[n6][2] * this.Norm[n6][2]) / 255.5;
            final double[] array3 = this.Norm[n6];
            final int n8 = 0;
            array3[n8] /= n7;
            final double[] array4 = this.Norm[n6];
            final int n9 = 1;
            array4[n9] /= n7;
            final double[] array5 = this.Norm[n6];
            final int n10 = 2;
            array5[n10] /= n7;
        }
        this.xPol = new int[30];
        this.yPol = new int[30];
        for (int n11 = 0; n11 < 256; ++n11) {
            this.col[n11] = new Color(n11, n11, n11);
        }
        final String parameter = this.getParameter("bgColor");
        if (parameter != null) {
            final StringTokenizer stringTokenizer5 = new StringTokenizer(parameter);
            this.setBackground(new Color(Integer.parseInt(stringTokenizer5.nextToken()), Integer.parseInt(stringTokenizer5.nextToken()), Integer.parseInt(stringTokenizer5.nextToken())));
        }
        else {
            this.setBackground(new Color(255, 255, 255));
        }
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
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
            --this.it;
            if (this.it < 0) {
                this.it = 0;
            }
            this.painted = false;
            this.repaint();
        }
        if (mouseEvent.isAltDown()) {
            ++this.it;
            this.painted = false;
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
        this.fiY += this.dfi * (x - this.mx0);
        this.mx0 = x;
        this.fiX += this.dfi * (y - this.my0);
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
        final double n6 = -cos * sin2;
        final double n7 = sin;
        final double n8 = cos * cos2;
        for (int i = 0; i < this.nVert; ++i) {
            this.vert1[i][0] = n * this.vert[i][0] + n2 * this.vert[i][2];
            this.vert1[i][1] = n3 * this.vert[i][0] + n4 * this.vert[i][1] + n5 * this.vert[i][2];
            this.vert1[i][2] = n6 * this.vert[i][0] + n7 * this.vert[i][1] + n8 * this.vert[i][2];
        }
        for (int j = 0; j < this.nFace; ++j) {
            this.Norm1z[j] = n6 * this.Norm[j][0] + n7 * this.Norm[j][1] + n8 * this.Norm[j][2];
        }
        int n9 = this.nVert - 1;
        while (--n9 >= 0) {
            boolean b = false;
            for (int k = 0; k <= n9; ++k) {
                final int n10 = this.Zsort[k];
                final int n11 = this.Zsort[k + 1];
                if (this.vert1[n10][2] > this.vert1[n11][2]) {
                    this.Zsort[k + 1] = n10;
                    this.Zsort[k] = n11;
                    b = true;
                }
            }
            if (!b) {
                break;
            }
        }
        this.painted = false;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        if (!this.painted) {
            this.buffGraphics.clearRect(0, 0, this.w, this.h);
            this.recursion(this.it, 0.0, 0.0, 1.0, 1.0 - 1.0 / this.scl);
            this.painted = true;
        }
        graphics.drawImage(this.buffImage, 0, 0, this);
    }
    
    public void recursion(final int n, final double n2, final double n3, final double n4, final double n5) {
        if (n == 0) {
            this.drawPolygon(n2, n3, n4);
        }
        else {
            for (int i = 0; i < this.nVert; ++i) {
                final int n6 = this.Zsort[i];
                this.recursion(n - 1, n2 + this.vert1[n6][0] * n5, n3 + this.vert1[n6][1] * n5, n4 / this.scl, n5 / this.scl);
            }
        }
    }
    
    public void drawPolygon(final double n, final double n2, final double n3) {
        for (int i = 0; i < this.nFace; ++i) {
            if (this.Norm1z[i] > 0.0) {
                for (int j = 0; j < this.face[i].length; ++j) {
                    this.xPol[j] = this.w2 + (int)(n + this.vert1[this.face[i][j]][0] * n3);
                    this.yPol[j] = this.h2 - (int)(n2 + this.vert1[this.face[i][j]][1] * n3);
                }
                this.buffGraphics.setColor(this.col[(int)this.Norm1z[i]]);
                this.buffGraphics.fillPolygon(this.xPol, this.yPol, this.face[i].length);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
