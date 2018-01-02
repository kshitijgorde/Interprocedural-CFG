import java.awt.image.ImageObserver;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.util.StringTokenizer;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class QuadMapO extends Applet implements MouseListener, KeyListener, ActionListener
{
    int w;
    int h;
    int w2;
    int h2;
    int it;
    int rand;
    double Xo;
    double Yo;
    double K;
    double Pi2;
    double Xc;
    double Yc;
    double dX;
    double Mx;
    double My;
    Image buffImage;
    Graphics buffGraphics;
    boolean bClear;
    Label lbK;
    Label lbIt;
    TextField tfK;
    TextField tfXY;
    TextField tfIt;
    TextField tfRand;
    Button btClear;
    Button btRand;
    
    public QuadMapO() {
        this.it = 3000;
        this.rand = 50;
        this.Xo = 3.0;
        this.Yo = -4.0;
        this.K = 0.3;
        this.Pi2 = 6.283185307179586;
        this.Xc = 3.141592653589793;
        this.Yc = 0.0;
        this.dX = this.Pi2;
        this.bClear = true;
    }
    
    public void init() {
        double n = this.Pi2;
        double doubleValue = 4.0;
        this.w = this.getSize().width;
        this.h = this.getSize().height;
        this.w2 = this.w / 2;
        this.h2 = this.h / 2;
        this.buffImage = this.createImage(this.w, this.h);
        this.buffGraphics = this.buffImage.getGraphics();
        final String parameter = this.getParameter("K");
        if (parameter != null) {
            this.K = Double.valueOf(parameter);
        }
        final String parameter2 = this.getParameter("it");
        if (parameter2 != null) {
            this.it = Integer.parseInt(parameter2);
        }
        final String parameter3 = this.getParameter("rand");
        if (parameter3 != null) {
            this.rand = Integer.parseInt(parameter3);
        }
        final String parameter4 = this.getParameter("coord");
        if (parameter4 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter4);
            this.Xc = Double.valueOf(stringTokenizer.nextToken());
            this.Yc = Double.valueOf(stringTokenizer.nextToken());
            n = Double.valueOf(stringTokenizer.nextToken());
            doubleValue = Double.valueOf(stringTokenizer.nextToken());
        }
        this.Mx = n / this.w;
        this.My = doubleValue / this.h;
        this.setLayout(new FlowLayout(0, 0, 0));
        this.add(this.tfXY = new TextField("" + (float)this.Xc + " " + (float)this.Yc + "; " + (float)n + " " + (float)doubleValue, 30));
        this.add(this.lbK = new Label("K", 2));
        this.add(this.tfK = new TextField("" + this.K, 5));
        this.tfK.addKeyListener(this);
        this.add(this.lbIt = new Label("it", 2));
        this.add(this.tfIt = new TextField("" + this.it, 5));
        this.tfK.addKeyListener(this);
        (this.btClear = new Button("Clear")).addActionListener(this);
        this.add(this.btClear);
        (this.btRand = new Button("Rand")).addActionListener(this);
        this.add(this.btRand);
        this.add(this.tfRand = new TextField("" + this.rand, 3));
        this.tfRand.addKeyListener(this);
        this.addMouseListener(this);
        this.clear();
        this.random();
    }
    
    public void destroy() {
        this.removeMouseListener(this);
    }
    
    public void random() {
        this.buffGraphics.setColor(Color.blue);
        for (int i = 0; i < this.rand; ++i) {
            this.Xo = this.Xc + this.Mx * this.w * (Math.random() - 0.5);
            this.Yo = this.Yc + this.My * this.h * (Math.random() - 0.5);
            this.iterate();
        }
    }
    
    public void iterate() {
        double xo = this.Xo;
        for (int i = 0; i < this.it; ++i) {
            final double yo = xo;
            xo = 2.0 * (this.K - xo * xo) - this.Yo;
            final int n = this.w2 + (int)((xo - this.Xc) / this.Mx);
            final int n2 = this.h2 - (int)((yo - this.Yc) / this.My);
            this.buffGraphics.drawLine(n, n2, n, n2);
            this.Yo = yo;
        }
        this.repaint();
    }
    
    public void Orbits(final int n) {
        this.buffGraphics.setColor(Color.red);
        double xo = this.Xo;
        int n2 = this.w2 + (int)((this.Xo - this.Xc) / this.Mx);
        int n3 = this.h2 - (int)((this.Yo - this.Yc) / this.My);
        for (int i = 0; i < n; ++i) {
            final double yo = xo;
            xo = 2.0 * (this.K - xo * xo) - this.Yo;
            final int n4 = this.w2 + (int)((xo - this.Xc) / this.Mx);
            final int n5 = this.h2 - (int)((yo - this.Yc) / this.My);
            this.buffGraphics.drawLine(n2, n3, n4, n5);
            this.buffGraphics.fillRect(n2, n3, 3, 3);
            n2 = n4;
            n3 = n5;
            this.Yo = yo;
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int n = mouseEvent.getX() - this.w2;
        final int n2 = this.h2 - mouseEvent.getY();
        this.Xo = n * this.Mx + this.Xc;
        this.Yo = n2 * this.My + this.Yc;
        if (mouseEvent.isAltDown()) {
            this.Xc = this.Xo;
            this.Yc = this.Yo;
            this.My /= 2.0;
            if (!mouseEvent.isShiftDown()) {
                this.Mx /= 2.0;
            }
            this.tfXY.setText("" + (float)this.Xc + " " + (float)this.Yc + "; " + (float)(this.w * this.Mx) + " " + (float)(this.h * this.My));
            this.clear();
            this.random();
            return;
        }
        if (mouseEvent.isControlDown()) {
            this.Xc = this.Xo;
            this.Yc = this.Yo;
            this.My *= 2.0;
            if (!mouseEvent.isShiftDown()) {
                this.Mx *= 2.0;
            }
            this.tfXY.setText("" + (float)this.Xc + " " + (float)this.Yc + "; " + (float)(this.w * this.Mx) + " " + (float)(this.h * this.My));
            this.clear();
            this.random();
            return;
        }
        if (mouseEvent.isShiftDown()) {
            this.Orbits(1);
            this.repaint();
        }
        else {
            this.showStatus("X=" + (float)this.Xo + "  Y=" + (float)this.Yo);
            this.buffGraphics.setColor(Color.black);
            this.iterate();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Clear")) {
            this.clear();
            this.repaint();
        }
        else {
            this.random();
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            try {
                final double k = this.K;
                this.K = Double.valueOf(this.tfK.getText());
                this.it = Integer.parseInt(this.tfIt.getText());
                this.rand = Integer.parseInt(this.tfRand.getText());
                if (k != this.K) {
                    this.clear();
                    this.random();
                }
            }
            catch (NumberFormatException ex) {}
        }
        keyEvent.consume();
    }
    
    public void clear() {
        this.buffGraphics.setColor(Color.white);
        this.buffGraphics.fillRect(0, 0, this.w, this.h);
    }
    
    public void paint(final Graphics graphics) {
        this.drawLabel();
        graphics.drawImage(this.buffImage, 0, 0, this);
    }
    
    public void drawLabel() {
        int n = 0;
        final double n2 = this.w2;
        final double n3 = this.h2;
        while (true) {
            final String parameter = this.getParameter("or" + n);
            if (parameter == null) {
                break;
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter);
            this.Xo = Double.valueOf(stringTokenizer.nextToken());
            this.Yo = Double.valueOf(stringTokenizer.nextToken());
            this.Orbits(Integer.parseInt(stringTokenizer.nextToken()));
            ++n;
        }
        int n4 = 0;
        this.buffGraphics.setColor(Color.black);
        while (true) {
            final String parameter2 = this.getParameter("lb" + n4);
            if (parameter2 == null) {
                break;
            }
            final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter2);
            final double n5 = this.w2 + (Double.valueOf(stringTokenizer2.nextToken()) - this.Xc) / this.Mx;
            final double n6 = this.h2 - (Double.valueOf(stringTokenizer2.nextToken()) - this.Yc) / this.My;
            if (n5 < this.w && n5 > 0.0 && n6 < this.h && n6 > 0.0) {
                this.buffGraphics.drawString(stringTokenizer2.nextToken(), (int)n5, (int)n6);
            }
            ++n4;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
