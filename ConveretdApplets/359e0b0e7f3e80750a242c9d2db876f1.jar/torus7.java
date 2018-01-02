import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Button;
import java.awt.Scrollbar;
import java.awt.Label;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class torus7 extends Applet implements MouseListener, MouseMotionListener, ActionListener, AdjustmentListener, Runnable
{
    final double pi = 3.1415926;
    final double degtopi = 0.017453292222222222;
    final int div = 30;
    final int rings = 20;
    final double pi2div = 0.20943950666666666;
    final double pi2rings = 0.31415926;
    double rd;
    double distance;
    double scale;
    int prevx;
    int prevy;
    int vx;
    int vy;
    double[][] rot;
    double[][] arot;
    double[][] pts;
    boolean goFlag;
    boolean auto;
    Image image1;
    Thread thread1;
    Graphics offgraphics;
    Label label1;
    Label label2;
    Label label3;
    Scrollbar scroll1;
    Scrollbar scroll2;
    Scrollbar scroll3;
    Button button1;
    
    public torus7() {
        this.rd = 0.5;
        this.vx = 0;
        this.vy = 0;
        this.rot = new double[][] { { 1.0, 0.0, 0.0 }, { 0.0, 1.0, 0.0 }, { 0.0, 0.0, 1.0 } };
        this.arot = new double[3][3];
        this.pts = new double[1200][3];
        this.goFlag = true;
        this.auto = false;
    }
    
    public void init() {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        final double data = 45.0;
        this.distance = 30.0;
        this.scale = 2500.0;
        this.image1 = this.createImage(width, height);
        this.offgraphics = this.image1.getGraphics();
        this.setLayout(null);
        (this.button1 = new Button(" F i t ")).setSize(80, 40);
        this.button1.setLocation(width - 120, height - 45);
        this.add(this.button1);
        this.button1.addActionListener(this);
        (this.label1 = new Label("Ring\u306e\u4f4d\u7f6e")).setSize(65, 20);
        this.label1.setLocation(10, height - 45);
        this.add(this.label1);
        (this.scroll1 = new Scrollbar(0, (int)(this.rd * 100.0), 5, 0, 105)).setSize(200, 20);
        this.scroll1.setLocation(80, height - 45);
        this.add(this.scroll1);
        this.scroll1.addAdjustmentListener(this);
        (this.label2 = new Label("Ring\u306e\u89d2\u5ea6")).setSize(65, 20);
        this.label2.setLocation(10, height - 20);
        this.add(this.label2);
        (this.scroll2 = new Scrollbar(0, (int)(90.0 - data), 5, 0, 95)).setSize(200, 20);
        this.scroll2.setLocation(80, height - 20);
        this.add(this.scroll2);
        this.scroll2.addAdjustmentListener(this);
        this.setData(data);
        (this.label3 = new Label("Zoom")).setSize(35, 20);
        this.label3.setLocation(width - 37, height - 245);
        this.add(this.label3);
        (this.scroll3 = new Scrollbar(1, (int)this.distance, 5, 5, 120)).setSize(20, 225);
        this.scroll3.setLocation(width - 22, height - 225);
        this.add(this.scroll3);
        this.scroll3.addAdjustmentListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    private void setData(double n) {
        int n2 = 0;
        for (int i = 0; i <= 1; ++i) {
            final double cos = Math.cos(n * 0.017453292222222222);
            final double sin = Math.sin(n * 0.017453292222222222);
            for (int j = 0; j < 20; ++j) {
                final double cos2 = Math.cos(j * 0.31415926);
                final double sin2 = Math.sin(j * 0.31415926);
                for (int k = 0; k < 30; ++k) {
                    final double n3 = Math.cos(k * 0.20943950666666666) + this.rd;
                    final double sin3 = Math.sin(k * 0.20943950666666666);
                    final double n4 = n3;
                    final double n5 = sin3 * sin;
                    final double n6 = sin3 * cos;
                    this.pts[n2][0] = n4 * cos2 - n5 * sin2;
                    this.pts[n2][1] = n4 * sin2 + n5 * cos2;
                    this.pts[n2][2] = n6;
                    ++n2;
                }
            }
            n *= -1.0;
        }
    }
    
    public void start() {
        (this.thread1 = new Thread(this)).start();
    }
    
    public void stop() {
        this.goFlag = false;
    }
    
    public void paint(final Graphics graphics) {
        final int[] array = new int[30];
        final int[] array2 = new int[30];
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        this.offgraphics.setColor(new Color(120, 120, 120));
        this.offgraphics.fillRect(0, 0, width, height);
        this.offgraphics.setColor(new Color(255, 255, 255));
        int n = 0;
        for (int i = 0; i <= 1; ++i) {
            for (int j = 0; j < 20; ++j) {
                int n2 = 0;
                for (int k = 0; k < 30; ++k) {
                    if (this.auto) {
                        this.multiply();
                    }
                    final double n3 = this.rot[0][0] * this.pts[n][0] + this.rot[0][1] * this.pts[n][1] + this.rot[0][2] * this.pts[n][2];
                    final double n4 = this.rot[1][0] * this.pts[n][0] + this.rot[1][1] * this.pts[n][1] + this.rot[1][2] * this.pts[n][2];
                    final double n5 = this.rot[2][0] * this.pts[n][0] + this.rot[2][1] * this.pts[n][1] + this.rot[2][2] * this.pts[n][2];
                    final double n6 = n3 / (this.distance - n5);
                    final double n7 = n4 / (this.distance - n5);
                    array[n2] = (int)(this.scale * n6 + (width - 20) / 2);
                    array2[n2] = (int)(-this.scale * n7 + (height - 45) / 2);
                    ++n2;
                    ++n;
                }
                this.offgraphics.drawPolygon(array, array2, 30);
            }
        }
        graphics.drawImage(this.image1, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void run() {
        while (this.goFlag) {
            this.repaint();
            try {
                Thread.sleep(30L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.button1) {
            this.scale = this.getSize().width / 2 / (1.0 + this.rd) * (this.distance - 1.0) * 0.8;
            this.repaint();
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        if (adjustmentEvent.getAdjustable() == this.scroll1 || adjustmentEvent.getAdjustable() == this.scroll2) {
            this.rd = this.scroll1.getValue() / 100.0;
            this.setData(90 - this.scroll2.getValue());
            this.repaint();
        }
        if (adjustmentEvent.getAdjustable() == this.scroll3) {
            this.distance = this.scroll3.getValue();
            this.repaint();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.prevx = mouseEvent.getX();
        this.prevy = mouseEvent.getY();
        mouseEvent.consume();
        this.vx = 0;
        this.vy = 0;
        this.auto = false;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.vx == 0 && this.vy == 0) {
            this.auto = false;
        }
        else {
            this.auto = true;
            final double n = -this.vx;
            final double n2 = -this.vy;
            final double atan2 = Math.atan2(n, n2);
            final double n3 = -Math.sqrt(n * n + n2 * n2) / 200000.0;
            this.set_arot(Math.cos(atan2), Math.sin(atan2), Math.cos(n3), Math.sin(n3));
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        this.vx = -(x - this.prevx);
        this.vy = y - this.prevy;
        this.auto = false;
        final double n = this.vx;
        final double n2 = -this.vy;
        final double atan2 = Math.atan2(n, n2);
        final double n3 = -Math.sqrt(n * n + n2 * n2) * 2.0 * 3.1415926 / this.getSize().width;
        this.set_arot(Math.cos(atan2), Math.sin(atan2), Math.cos(n3), Math.sin(n3));
        this.multiply();
        this.repaint();
        this.vx = x - this.prevx;
        this.vy = y - this.prevy;
        this.prevx = x;
        this.prevy = y;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    void multiply() {
        final double n = this.arot[0][0] * this.rot[0][0] + this.arot[0][1] * this.rot[1][0] + this.arot[0][2] * this.rot[2][0];
        final double n2 = this.arot[0][0] * this.rot[0][1] + this.arot[0][1] * this.rot[1][1] + this.arot[0][2] * this.rot[2][1];
        final double n3 = this.arot[0][0] * this.rot[0][2] + this.arot[0][1] * this.rot[1][2] + this.arot[0][2] * this.rot[2][2];
        final double n4 = this.arot[1][0] * this.rot[0][0] + this.arot[1][1] * this.rot[1][0] + this.arot[1][2] * this.rot[2][0];
        final double n5 = this.arot[1][0] * this.rot[0][1] + this.arot[1][1] * this.rot[1][1] + this.arot[1][2] * this.rot[2][1];
        final double n6 = this.arot[1][0] * this.rot[0][2] + this.arot[1][1] * this.rot[1][2] + this.arot[1][2] * this.rot[2][2];
        final double n7 = this.arot[2][0] * this.rot[0][0] + this.arot[2][1] * this.rot[1][0] + this.arot[2][2] * this.rot[2][0];
        final double n8 = this.arot[2][0] * this.rot[0][1] + this.arot[2][1] * this.rot[1][1] + this.arot[2][2] * this.rot[2][1];
        final double n9 = this.arot[2][0] * this.rot[0][2] + this.arot[2][1] * this.rot[1][2] + this.arot[2][2] * this.rot[2][2];
        this.rot[0][0] = n;
        this.rot[0][1] = n2;
        this.rot[0][2] = n3;
        this.rot[1][0] = n4;
        this.rot[1][1] = n5;
        this.rot[1][2] = n6;
        this.rot[2][0] = n7;
        this.rot[2][1] = n8;
        this.rot[2][2] = n9;
    }
    
    void set_arot(final double n, final double n2, final double n3, final double n4) {
        this.arot[0][0] = n * n + n2 * n2 * n3;
        this.arot[0][1] = n2 * n * (1.0 - n3);
        this.arot[0][2] = n2 * n4;
        this.arot[1][0] = n2 * n * (1.0 - n3);
        this.arot[1][1] = n2 * n2 + n * n * n3;
        this.arot[1][2] = -n4 * n;
        this.arot[2][0] = -n2 * n4;
        this.arot[2][1] = n * n4;
        this.arot[2][2] = n3;
    }
}
