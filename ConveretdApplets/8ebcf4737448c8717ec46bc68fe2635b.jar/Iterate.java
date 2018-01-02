import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.util.StringTokenizer;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Iterate extends Applet implements MouseMotionListener, KeyListener
{
    Image buffImage;
    Graphics buffGraphics;
    int maxIt;
    int N;
    int numIt;
    int w;
    int h;
    int h2;
    int iXo;
    int iYo;
    int lbSize;
    int[] xFun;
    int[] yFun;
    int[] xGist;
    int[] yGist;
    int[] xIt;
    int[] yIt;
    double C;
    double maxIYI;
    double Xo;
    double Yo;
    double delX;
    double Step;
    Label lbIt;
    Label lbN;
    Label lbC;
    TextField tfIt;
    TextField tfN;
    TextField tfC;
    
    public Iterate() {
        this.maxIt = 10;
        this.N = 1;
        this.lbSize = 30;
        this.C = -0.5;
        this.maxIYI = 2.0;
        this.Xo = 0.0;
        this.Yo = 0.0;
        this.delX = 4.0;
    }
    
    public void init() {
        this.h = Integer.parseInt(this.getParameter("height")) - this.lbSize;
        this.h2 = this.h / 2;
        this.w = Integer.parseInt(this.getParameter("width"));
        final String parameter = this.getParameter("XYoDel");
        if (parameter != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter);
            this.Xo = Double.valueOf(stringTokenizer.nextToken());
            this.Yo = Double.valueOf(stringTokenizer.nextToken());
            this.delX = Double.valueOf(stringTokenizer.nextToken());
        }
        final String parameter2 = this.getParameter("MaxIt");
        if (parameter2 != null) {
            this.maxIt = Integer.parseInt(parameter2);
        }
        final String parameter3 = this.getParameter("N");
        if (parameter3 != null) {
            this.N = Integer.parseInt(parameter3);
        }
        final String parameter4 = this.getParameter("C");
        if (parameter4 != null) {
            this.C = Double.valueOf(parameter4);
        }
        this.xFun = new int[this.h];
        for (int i = this.h - 1; i >= 0; --i) {
            this.xFun[i] = i;
        }
        this.yFun = new int[this.h];
        this.buffImage = this.createImage(this.w, this.h);
        this.buffGraphics = this.buffImage.getGraphics();
        this.add(this.lbC = new Label("C"));
        this.add(this.tfC = new TextField("" + this.C, 9));
        this.add(this.lbN = new Label("N"));
        this.add(this.tfN = new TextField("" + this.N, 3));
        this.add(this.lbIt = new Label("It"));
        this.add(this.tfIt = new TextField("" + this.maxIt, 3));
        this.tfC.addKeyListener(this);
        this.tfN.addKeyListener(this);
        this.tfIt.addKeyListener(this);
        this.addMouseMotionListener(this);
        this.Step = this.delX / this.h;
        this.iXo = this.h2 - (int)(this.Xo / this.Step);
        this.iYo = this.h2 + (int)(this.Yo / this.Step);
        this.draw();
    }
    
    public void destroy() {
        this.removeMouseMotionListener(this);
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            try {
                this.N = Integer.parseInt(this.tfN.getText());
                this.maxIt = Integer.parseInt(this.tfIt.getText());
                this.C = Double.valueOf(this.tfC.getText());
            }
            catch (NumberFormatException ex) {}
            this.draw();
            this.repaint();
        }
        keyEvent.consume();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.C = this.Yo + this.Step * (this.h2 - (mouseEvent.getY() - this.lbSize));
        this.tfC.setText(Float.toString((float)this.C));
        this.draw();
        this.repaint();
        mouseEvent.consume();
    }
    
    public void draw() {
        this.xGist = new int[this.maxIt << 2];
        this.yGist = new int[this.maxIt << 2];
        this.xIt = new int[this.maxIt + 1];
        this.yIt = new int[this.maxIt + 1];
        for (int i = 0; i <= this.maxIt; ++i) {
            this.xIt[i] = this.h + (this.w - this.h - 1) * i / this.maxIt;
        }
        this.generateFun();
        this.iterations();
        this.buffGraphics.setColor(Color.white);
        this.buffGraphics.fillRect(0, 0, this.w - 1, this.h - 1);
        this.buffGraphics.setColor(Color.black);
        this.buffGraphics.drawRect(0, 0, this.w - 1, this.h - 1);
        this.buffGraphics.drawLine(this.h, 0, this.h, this.h - 1);
        this.buffGraphics.drawLine(0, this.iYo, this.w - 1, this.iYo);
        this.buffGraphics.drawLine(this.iXo, 0, this.iXo, this.h - 1);
        this.buffGraphics.setColor(Color.green);
        final int n = (int)((this.Yo - this.Xo) / this.Step);
        this.buffGraphics.drawLine(0, this.h + n, this.h, n);
        this.buffGraphics.setColor(Color.blue);
        this.buffGraphics.drawPolyline(this.xFun, this.yFun, this.h);
        this.buffGraphics.setColor(Color.red);
        this.buffGraphics.drawPolyline(this.xGist, this.yGist, this.numIt << 1);
        this.buffGraphics.drawPolyline(this.xIt, this.yIt, this.numIt + 1);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.buffImage, 0, this.lbSize, this);
        this.showStatus("It=" + this.numIt);
    }
    
    public void iterations() {
        double n = 0.0;
        int n2 = this.xGist[0] = this.h2 - (int)(this.Xo / this.Step);
        this.yGist[0] = (this.yIt[0] = this.h2 + (int)(this.Yo / this.Step));
        for (int i = 1; i <= this.maxIt; ++i) {
            double n3 = n;
            for (int j = this.N; j > 0; --j) {
                n3 = n3 * n3 + this.C;
            }
            final int n4 = i << 1;
            for (int k = this.N; k > 0; --k) {
                this.xGist[n4 - 1] = n2;
            }
            final int n5 = this.yGist[n4 - 1] = this.h2 - (int)((n3 - this.Yo) / this.Step);
            n2 = (this.xGist[n4] = this.h2 + (int)((n3 - this.Xo) / this.Step));
            this.yGist[n4] = (this.yIt[i] = n5);
            n = n3;
            this.numIt = i;
            if (Math.abs(n3) > this.maxIYI) {
                break;
            }
        }
    }
    
    public void generateFun() {
        for (int i = this.h - 1; i >= 0; --i) {
            double n = this.Xo + (i - this.h2) * this.Step;
            for (int j = this.N; j > 0; --j) {
                n = n * n + this.C;
            }
            this.yFun[i] = this.h2 - (int)((n - this.Yo) / this.Step);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
