import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Component;
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

public class PoincareDemo extends Applet implements MouseListener, KeyListener, ActionListener, Runnable
{
    Thread thIntegrate;
    int w;
    int h;
    int w2;
    int h2;
    int w4;
    int delay;
    int Mx;
    int My;
    int x1;
    int x2;
    int y1;
    int y2;
    double Q1_0;
    double P1_0;
    double Q2_0;
    double P2_0;
    double Q1_1;
    double P1_1;
    double Q2_1;
    double P2_1;
    double Q12;
    double Q22;
    double Pmax;
    double E;
    double ds;
    double ds2;
    double ds8;
    double t;
    Image buffImage;
    Graphics buffGraphics;
    boolean bRun;
    boolean bClear;
    boolean bClearLeft;
    Label lbDs;
    Label lbDelay;
    TextField tfDs;
    TextField tfDelay;
    Button btRun;
    Button btClear;
    
    public PoincareDemo() {
        this.delay = 0;
        this.Mx = 30;
        this.My = 25;
        this.Q1_0 = 0.6;
        this.P1_0 = 0.0;
        this.Pmax = 5.0;
        this.ds = 0.001;
        this.bRun = false;
        this.bClear = true;
        this.bClearLeft = true;
    }
    
    public void init() {
        this.w = this.getSize().width;
        this.h = this.getSize().height;
        this.w2 = this.w / 2;
        this.h2 = this.h / 2;
        this.w4 = this.w / 4;
        this.buffImage = this.createImage(this.w, this.h);
        this.buffGraphics = this.buffImage.getGraphics();
        final String parameter = this.getParameter("Delay");
        if (parameter != null) {
            this.delay = Integer.parseInt(parameter);
        }
        final String parameter2 = this.getParameter("coord");
        if (parameter2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter2);
            this.Q1_0 = Double.valueOf(stringTokenizer.nextToken());
            this.P1_0 = Double.valueOf(stringTokenizer.nextToken());
        }
        this.E = -1.0;
        this.P2_0 = -4.0;
        this.Q1_1 = this.Q1_0;
        this.P1_1 = this.P1_0;
        this.Q2_1 = this.Q2_0;
        this.P2_1 = this.P2_0;
        this.Q12 = this.Q1_1 * this.Q1_1;
        this.Q22 = this.Q2_1 * this.Q2_1;
        this.ds2 = this.ds * 2.0;
        this.ds8 = this.ds / 8.0;
        this.x1 = (int)(this.Mx * this.Q1_1);
        this.y1 = (int)(this.My * this.P1_1);
        this.x2 = 0;
        this.y2 = (int)(this.My * this.P2_1);
        this.add(this.lbDs = new Label("ds", 2));
        this.add(this.tfDs = new TextField("" + this.ds, 5));
        this.add(this.lbDelay = new Label("Delay", 2));
        this.add(this.tfDelay = new TextField("" + this.delay, 3));
        this.tfDs.addKeyListener(this);
        this.tfDelay.addKeyListener(this);
        (this.btRun = new Button("Run ")).addActionListener(this);
        this.add(this.btRun);
        (this.btClear = new Button("Clear")).addActionListener(this);
        this.add(this.btClear);
        this.addMouseListener(this);
    }
    
    public void destroy() {
        this.removeMouseListener(this);
    }
    
    public void run() {
        while (Thread.currentThread() == this.thIntegrate) {
            for (int i = 0; i < 3; ++i) {
                this.Q1_0 = this.Q1_1;
                this.Q2_0 = this.Q2_1;
                this.P1_0 = this.P1_1;
                this.P2_0 = this.P2_1;
                final double n = this.Q1_0 + this.P1_0 * this.Q22 * this.ds8;
                final double n2 = this.Q2_0 + this.P2_0 * this.Q12 * this.ds8;
                this.Q12 = n * n;
                this.Q22 = n2 * n2;
                final double n3 = this.Q12 + this.Q22;
                final double n4 = n3 * n3;
                this.P1_1 = this.P1_0 + n * (2.0 - this.P2_0 * this.P2_0 / 8.0 - this.Q22 * (1.0 + this.Q22 / n4)) * this.ds2;
                this.P2_1 = this.P2_0 + n2 * (2.0 - this.P1_0 * this.P1_0 / 8.0 - this.Q12 * (1.0 + this.Q12 / n4)) * this.ds2;
                this.Q1_1 = n + this.P1_1 * this.Q22 * this.ds8;
                this.Q2_1 = n2 + this.P2_1 * this.Q12 * this.ds8;
                this.Q12 = this.Q1_1 * this.Q1_1;
                this.Q22 = this.Q2_1 * this.Q2_1;
                if (Math.abs(this.P1_1) > this.Pmax || Math.abs(this.P2_1) > this.Pmax) {
                    this.bRun = false;
                    this.btRun.setLabel("Run ");
                    return;
                }
            }
            if (this.Q2_1 > 0.0) {
                final double n5 = 0.0;
                this.Q2_0 = n5;
                this.Q2_1 = n5;
                final double n6 = -4.0;
                this.P2_0 = n6;
                this.P2_1 = n6;
                this.bClearLeft = true;
            }
            this.repaint();
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void stop() {
        this.thIntegrate = null;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int n = mouseEvent.getX() - this.w4;
        final int n2 = this.h2 - mouseEvent.getY();
        if (n > 0) {
            final double n3 = n / this.Mx;
            this.Q1_0 = n3;
            this.Q1_1 = n3;
            final double n4 = n2 / this.My;
            this.P1_0 = n4;
            this.P1_1 = n4;
            this.x1 = (int)(this.Mx * this.Q1_1);
            this.y1 = (int)(this.My * this.P1_1);
            this.x2 = 0;
            this.y2 = -this.My * 4;
            this.E = -1.0;
            final double n5 = 0.0;
            this.Q2_1 = n5;
            this.Q2_0 = n5;
            final double n6 = -4.0;
            this.P2_1 = n6;
            this.P2_0 = n6;
            this.Q12 = this.Q1_1 * this.Q1_1;
            this.Q22 = 0.0;
            this.bClearLeft = true;
            this.bRun = true;
            this.btRun.setLabel("Stop");
            (this.thIntegrate = new Thread(this)).start();
        }
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Clear")) {
            this.bClear = true;
            this.repaint();
        }
        else if (this.bRun) {
            this.bRun = false;
            this.btRun.setLabel("Run ");
            this.thIntegrate = null;
        }
        else {
            this.bRun = true;
            this.btRun.setLabel("Stop");
            (this.thIntegrate = new Thread(this)).start();
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            try {
                this.ds = Double.valueOf(this.tfDs.getText());
                this.ds2 = this.ds * 2.0;
                this.ds8 = this.ds / 8.0;
                this.delay = Integer.parseInt(this.tfDelay.getText());
            }
            catch (NumberFormatException ex) {}
        }
        keyEvent.consume();
    }
    
    public void paint(final Graphics graphics) {
        if (this.bClear) {
            this.buffGraphics.setColor(Color.white);
            this.buffGraphics.fillRect(0, 0, this.w, this.h);
            this.buffGraphics.setColor(Color.lightGray);
            this.buffGraphics.drawLine(this.w4, 0, this.w4, this.h);
            this.buffGraphics.drawLine(0, this.h2, this.w2, this.h2);
            this.bClear = false;
        }
        this.buffGraphics.setColor(Color.blue);
        final int x1 = (int)(this.Mx * Math.abs(this.Q1_1));
        int y1 = (int)(this.My * this.P1_1);
        if (this.Q1_1 < 0.0) {
            y1 = -y1;
        }
        if (x1 < this.w4) {
            this.buffGraphics.drawLine(this.w4 + this.x1, this.h2 - this.y1, this.w4 + x1, this.h2 - y1);
        }
        if (this.bClearLeft) {
            this.buffGraphics.setColor(Color.white);
            this.buffGraphics.fillRect(0, 0, this.w2, this.h);
            this.buffGraphics.setColor(Color.lightGray);
            this.buffGraphics.drawLine(this.w4, 0, this.w4, this.h);
            this.buffGraphics.drawLine(0, this.h2, this.w2, this.h2);
            this.buffGraphics.setColor(Color.black);
            this.buffGraphics.drawLine(this.w2 + x1, this.h2 - y1, this.w2 + x1, this.h2 - y1);
            this.bClearLeft = false;
        }
        this.y1 = y1;
        this.buffGraphics.setColor(Color.red);
        final int x2 = (int)(this.Mx * Math.abs(this.Q2_1));
        int y2 = (int)(this.My * this.P2_1);
        if (this.Q2_1 > 0.0) {
            y2 = -y2;
        }
        this.buffGraphics.drawLine(this.w4 - this.x2, this.h2 - this.y2, this.w4 - x2, this.h2 - y2);
        this.x1 = x1;
        this.x2 = x2;
        this.y2 = y2;
        graphics.drawImage(this.buffImage, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
