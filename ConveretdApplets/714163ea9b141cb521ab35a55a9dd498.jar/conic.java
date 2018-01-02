import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class conic extends Applet implements MouseListener, MouseMotionListener
{
    final double pi = 3.1415926;
    final int div = 15;
    final int nest = 2;
    double scale;
    double offx;
    double offy;
    double xmax;
    double ymax;
    point[] pt;
    double[][] mat;
    int near;
    int keep;
    Image image1;
    Graphics offg;
    
    public conic() {
        this.scale = 50.0;
        this.pt = new point[5];
        this.mat = new double[5][6];
    }
    
    public void init() {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        this.offx = width / 2.0;
        this.offy = height / 2.0;
        this.xmax = width / 2.0 / this.scale;
        this.ymax = height / 2.0 / this.scale;
        for (int i = 0; i < 5; ++i) {
            this.pt[i] = new point(this.xmax / 2.0 * Math.cos(1.25663704 * i), this.ymax / 2.0 * Math.sin(1.25663704 * i));
        }
        this.near = -1;
        this.keep = -1;
        this.image1 = this.createImage(width, height);
        this.offg = this.image1.getGraphics();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        this.offg.setColor(new Color(100, 100, 100));
        this.offg.fillRect(0, 0, width, height);
        this.setMat();
        this.offg.setColor(new Color(255, 255, 255));
        this.draw_Curv(this.offg, -this.xmax, -this.ymax, this.xmax, this.ymax, 2, 0);
        this.offg.setColor(new Color(255, 0, 0));
        for (int i = 0; i < 5; ++i) {
            this.offg.drawOval((int)(this.scale * this.pt[i].x + this.offx) - 1, (int)(-this.scale * this.pt[i].y + this.offy) - 1, 3, 3);
        }
        if (this.near >= 0) {
            this.offg.drawRect((int)(this.scale * this.pt[this.near].x + this.offx) - 7, (int)(-this.scale * this.pt[this.near].y + this.offy) - 7, 15, 15);
        }
        graphics.drawImage(this.image1, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void setMat() {
        for (int i = 0; i < 5; ++i) {
            this.mat[i][0] = this.pt[i].x * this.pt[i].x;
            this.mat[i][1] = this.pt[i].x * this.pt[i].y;
            this.mat[i][2] = this.pt[i].y * this.pt[i].y;
            this.mat[i][3] = this.pt[i].x;
            this.mat[i][4] = this.pt[i].y;
            this.mat[i][5] = -1.0;
        }
        this.haki(this.mat);
    }
    
    private double func(final double n, final double n2) {
        return this.mat[0][5] * n * n + this.mat[1][5] * n * n2 + this.mat[2][5] * n2 * n2 + this.mat[3][5] * n + this.mat[4][5] * n2 + 1.0;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.near = -1;
        this.keep = -1;
        this.repaint();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.keep >= 0) {
            final int width = this.getSize().width;
            final int height = this.getSize().height;
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            if (0 < x && x < width && 0 < y && y < height) {
                this.pt[this.keep].x = x / this.scale - this.xmax;
                this.pt[this.keep].y = -(y - this.offy) / this.scale;
                this.repaint();
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.near >= 0) {
            this.keep = this.near;
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final point point = new point(mouseEvent.getX() / this.scale - this.xmax, -(mouseEvent.getY() - this.offy) / this.scale);
        for (int i = 0; i < 5; ++i) {
            if (this.distance(point, this.pt[i]) < this.xmax / 30.0) {
                this.near = i;
                this.repaint();
                break;
            }
            if (this.near >= 0) {
                this.near = -1;
                this.repaint();
            }
        }
    }
    
    double distance(final point point, final point point2) {
        final double n = point.x - point2.x;
        final double n2 = point.y - point2.y;
        return Math.sqrt(n * n + n2 * n2);
    }
    
    private void draw_Curv(final Graphics graphics, final double n, final double n2, final double n3, final double n4, final int n5, int n6) {
        final double[][] array = new double[16][16];
        if (n5 == 0) {
            switch (n6) {
                case 1:
                case 14: {
                    this.dline1(graphics, n, n2, n3, n4);
                    break;
                }
                case 2:
                case 13: {
                    this.dline1(graphics, n3, n2, n, n4);
                    break;
                }
                case 4:
                case 11: {
                    this.dline1(graphics, n3, n4, n, n2);
                    break;
                }
                case 7:
                case 8: {
                    this.dline1(graphics, n, n4, n3, n2);
                    break;
                }
                case 3:
                case 12: {
                    this.dline21(graphics, n, n2, n3, n4);
                    break;
                }
                case 6:
                case 9: {
                    this.dline22(graphics, n3, n2, n, n4);
                    break;
                }
                case 5:
                case 10: {
                    this.dline3(graphics, n, n2, n3, n4);
                    break;
                }
            }
        }
        else {
            for (int i = 0; i <= 15; ++i) {
                final double n7 = n + (n3 - n) / 15.0 * i;
                for (int j = 0; j <= 15; ++j) {
                    array[i][j] = this.func(n7, n2 + (n4 - n2) / 15.0 * j);
                }
            }
            for (int k = 0; k < 15; ++k) {
                final double n8 = n + (n3 - n) / 15.0 * k;
                for (int l = 0; l < 15; ++l) {
                    final double n9 = n2 + (n4 - n2) / 15.0 * l;
                    n6 = 0;
                    if (array[k][l] >= 0.0) {
                        ++n6;
                    }
                    if (array[k + 1][l] >= 0.0) {
                        n6 += 2;
                    }
                    if (array[k + 1][l + 1] >= 0.0) {
                        n6 += 4;
                    }
                    if (array[k][l + 1] >= 0.0) {
                        n6 += 8;
                    }
                    if (0 < n6 && n6 < 15) {
                        this.draw_Curv(graphics, n8, n9, n8 + (n3 - n) / 15.0, n9 + (n4 - n2) / 15.0, n5 - 1, n6);
                    }
                }
            }
        }
    }
    
    private void dline(final Graphics graphics, final double n, final double n2, final double n3, final double n4) {
        graphics.drawLine((int)(this.scale * n + this.offx), (int)(-this.scale * n2 + this.offy), (int)(this.scale * n3 + this.offx), (int)(-this.scale * n4 + this.offy));
    }
    
    private void dline1(final Graphics graphics, final double n, final double n2, final double n3, final double n4) {
        final double func = this.func(n, n2);
        final double func2 = this.func(n3, n2);
        final double func3 = this.func(n, n4);
        this.dline(graphics, (func * n3 - func2 * n) / (func - func2), n2, n, (func * n4 - func3 * n2) / (func - func3));
    }
    
    private void dline21(final Graphics graphics, final double n, final double n2, final double n3, final double n4) {
        final double func = this.func(n, n2);
        final double func2 = this.func(n3, n2);
        final double func3 = this.func(n3, n4);
        final double func4 = this.func(n, n4);
        this.dline(graphics, n, (func * n4 - func4 * n2) / (func - func4), n3, (func2 * n4 - func3 * n2) / (func2 - func3));
    }
    
    private void dline22(final Graphics graphics, final double n, final double n2, final double n3, final double n4) {
        final double func = this.func(n, n2);
        final double func2 = this.func(n3, n2);
        final double func3 = this.func(n3, n4);
        final double func4 = this.func(n, n4);
        this.dline(graphics, (func2 * n - func * n3) / (func2 - func), n2, (func3 * n - func4 * n3) / (func3 - func4), n4);
    }
    
    private void dline3(final Graphics graphics, final double n, final double n2, final double n3, final double n4) {
        final double func = this.func(n, n2);
        final double func2 = this.func(n3, n2);
        final double func3 = this.func(n3, n4);
        final double func4 = this.func(n, n4);
        final double n5 = (func * n3 - func2 * n) / (func - func2);
        final double n6 = (func * n4 - func4 * n2) / (func - func4);
        final double n7 = (func3 * n - func4 * n3) / (func3 - func4);
        this.dline(graphics, n, n6, n3, (func3 * n2 - func2 * n4) / (func3 - func2));
        this.dline(graphics, n5, n2, n7, n4);
    }
    
    public void haki(final double[][] array) {
        final int length = array.length;
        final int length2 = array[0].length;
        for (int i = 0; i < length; ++i) {
            int n;
            for (n = i; array[n][i] == 0.0 && n < length - 2; ++n) {}
            this.swap(array, i, n);
            final double n2 = array[i][i];
            for (int j = 0; j < length2; ++j) {
                final double[] array2 = array[i];
                final int n3 = j;
                array2[n3] /= n2;
            }
            for (int k = 0; k < length; ++k) {
                if (k != i) {
                    final double n4 = array[k][i];
                    for (int l = i; l < length2; ++l) {
                        final double[] array3 = array[k];
                        final int n5 = l;
                        array3[n5] -= n4 * array[i][l];
                    }
                }
            }
        }
    }
    
    private void swap(final double[][] array, final int n, final int n2) {
        for (int i = 0; i < array[0].length; ++i) {
            final double n3 = array[n][i];
            array[n][i] = array[n2][i];
            array[n2][i] = n3;
        }
    }
    
    public class point
    {
        double x;
        double y;
        
        point() {
        }
        
        point(final double x, final double y) {
            this.x = x;
            this.y = y;
        }
    }
}
