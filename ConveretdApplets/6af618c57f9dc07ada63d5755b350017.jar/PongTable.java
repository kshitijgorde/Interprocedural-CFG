import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class PongTable extends Canvas implements Runnable, MouseMotionListener, MouseListener
{
    int Xsize;
    int Ysize;
    int Xball;
    int Yball;
    int Xbat;
    int Ybat;
    int XbatC;
    int YbatC;
    int difficulty;
    double[] direction;
    Thread thisthread;
    int[] Xbatdir;
    int[] Ybatdir;
    boolean Centred;
    Image ball;
    Image paddle1;
    Image paddle2;
    Image back;
    Image toplay;
    
    PongTable(final int xsize, final int ysize, final Image ball, final Image paddle1, final Image paddle2, final Image back, final Image toplay, final int difficulty) {
        this.direction = new double[2];
        this.Xbatdir = new int[2];
        this.Ybatdir = new int[2];
        this.Centred = true;
        Toolkit.getDefaultToolkit();
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.setSize(xsize, ysize);
        this.Xsize = xsize;
        this.Ysize = ysize;
        this.startpositions();
        this.Xball = this.Xsize / 2;
        this.Yball = this.Ysize / 2;
        this.direction[0] = 0.0;
        this.direction[1] = 0.0;
        this.ball = ball;
        this.paddle1 = paddle1;
        this.paddle2 = paddle2;
        this.back = back;
        this.toplay = toplay;
        this.difficulty = difficulty;
        (this.thisthread = new Thread(this)).start();
    }
    
    void startpositions() {
        this.Xball = 20;
        this.Yball = 20;
        this.Xbat = 20;
        this.Ybat = 40;
        this.XbatC = this.Xsize - 20;
        this.YbatC = 40;
        this.direction[0] = 4.0;
        this.direction[1] = 4.0;
    }
    
    public void update(final Graphics graphics) {
        final Dimension size = this.getSize();
        final Image image = this.createImage(size.width, size.height);
        final Graphics graphics2 = image.getGraphics();
        graphics2.setColor(this.getBackground());
        graphics2.fillRect(0, 0, size.width, size.height);
        graphics2.setColor(this.getForeground());
        this.paint(graphics2);
        graphics.drawImage(image, 0, 0, this);
        image.flush();
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.back, 5, 5, this.Xsize - 10, this.Ysize - 10, this);
        graphics.drawImage(this.paddle1, this.XbatC - 2, this.YbatC - 10, this);
        graphics.drawImage(this.paddle2, this.Xbat - 2, this.Ybat - 10, this);
        graphics.drawImage(this.ball, this.Xball - 5, this.Yball - 5, this);
        if (this.Centred) {
            graphics.drawImage(this.toplay, 10, 10, this);
        }
    }
    
    public void run() {
        double n = this.Xball;
        double n2 = this.Yball;
        while (true) {
            for (int i = 0; i < 4; ++i) {
                n += this.direction[0] / 4.0;
                n2 += this.direction[1] / 4.0;
                if (n < 10.0 || n > this.Xsize - 10) {
                    n = this.Xsize / 2;
                    n2 = this.Ysize / 2;
                    this.direction[0] = 0.0;
                    this.direction[1] = 0.0;
                    this.Centred = true;
                }
                if (n2 < 10.0 || n2 > this.Ysize - 10) {
                    this.direction[1] *= -1.0;
                }
                if (n > 18.0 && n < 27.0 && Math.abs(n2 - this.Ybat) <= 10.0) {
                    this.direction[0] = -1.0 * this.direction[0] + (this.Xbatdir[0] - this.Xbatdir[1]) / 8;
                    this.direction[1] += (this.Ybatdir[0] - this.Ybatdir[1]) / 8;
                    n = 28.0;
                }
                if (n < this.Xsize - 15 && n > this.Xsize - 22 && Math.abs(n2 - this.YbatC) <= 10.0) {
                    this.direction[0] = -1.0 * this.direction[0] + -1.0 * Math.random();
                    this.direction[1] += this.direction[0] / 4.0 * Math.sin(Math.random() * 2.0 * 3.141592653589793);
                    n = this.Xsize - 23;
                }
            }
            this.Xball = (int)n;
            this.Yball = (int)n2;
            if (this.Xball > this.Xsize / 2 && this.Yball - this.YbatC != 0) {
                this.YbatC += (this.Yball - this.YbatC) / Math.abs(this.Yball - this.YbatC) * this.difficulty;
            }
            this.repaint();
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex) {
                System.out.println("Error on sleep");
            }
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (y > 15 && y < this.Ysize - 10) {
            this.Ybat = y;
            this.Xbatdir[1] = this.Xbatdir[0];
            this.Ybatdir[1] = this.Ybatdir[0];
            this.Xbatdir[0] = x;
            this.Ybatdir[0] = y;
        }
        this.repaint();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.Centred) {
            this.startpositions();
            this.Centred = false;
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
}
