import java.awt.Event;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Car extends Applet implements Runnable
{
    Image img;
    Image car1;
    Image cup;
    static int position;
    static int points;
    static int delay;
    road rd;
    Thread thr;
    static int pts;
    boolean msg;
    static boolean kill;
    
    public void init() {
        final int[] array = { 15, 15, 0, 60, 45, 45 };
        final int[] array2 = { 45, 50, 58, 58, 50, 45 };
        this.setBackground(Color.black);
        this.img = this.createImage(60, 60);
        final Graphics graphics = this.img.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, 60, 60);
        graphics.setColor(Color.green);
        graphics.fillRect(12, 20, 36, 7);
        graphics.fillRect(8, 15, 4, 17);
        graphics.fillRect(48, 15, 4, 17);
        graphics.fillRect(5, 40, 50, 7);
        graphics.fillRect(0, 35, 5, 17);
        graphics.fillRect(55, 35, 5, 17);
        graphics.setColor(Color.red);
        graphics.fillRect(20, 0, 20, 15);
        graphics.fillRect(15, 15, 30, 40);
        graphics.setColor(Color.blue);
        graphics.fillRect(20, 20, 7, 10);
        graphics.fillRect(33, 20, 7, 10);
        graphics.setColor(Color.red);
        graphics.fillRect(22, 22, 3, 6);
        graphics.fillRect(35, 22, 3, 6);
        graphics.setFont(new Font("TimesRoman", 0, 7));
        graphics.setColor(Color.white);
        graphics.fillPolygon(array, array2, 6);
        graphics.setColor(Color.black);
        graphics.drawString("YAMAHA", 15, 52);
        this.car1 = this.createImage(60, 60);
        final Graphics graphics2 = this.car1.getGraphics();
        graphics2.setColor(Color.black);
        graphics2.fillRect(0, 0, 60, 60);
        graphics2.setColor(Color.green);
        graphics2.fillRect(12, 20, 36, 7);
        graphics2.fillRect(8, 15, 4, 17);
        graphics2.fillRect(48, 15, 4, 17);
        graphics2.fillRect(5, 40, 50, 7);
        graphics2.fillRect(0, 35, 5, 17);
        graphics2.fillRect(55, 35, 5, 17);
        graphics2.setColor(Color.blue);
        graphics2.fillRect(20, 0, 20, 15);
        graphics2.fillRect(15, 15, 30, 40);
        graphics2.setColor(Color.red);
        graphics2.fillRect(20, 20, 7, 10);
        graphics2.fillRect(33, 20, 7, 10);
        graphics2.setColor(Color.blue);
        graphics2.fillRect(22, 22, 3, 6);
        graphics2.fillRect(35, 22, 3, 6);
        graphics2.setFont(new Font("TimesRoman", 0, 7));
        graphics2.setColor(Color.white);
        graphics2.fillPolygon(array, array2, 6);
        graphics2.setColor(Color.black);
        graphics2.drawString("  B.M.W ", 15, 52);
        (this.thr = new Thread(this)).start();
        (this.rd = new road(this.getGraphics(), this.car1, this)).start();
        final int[] array3 = { 20, 5, 35 };
        final int[] array4 = { 150, 160, 160 };
        this.cup = this.createImage(50, 165);
        final Graphics graphics3 = this.cup.getGraphics();
        graphics3.setColor(Color.black);
        graphics3.fillRect(0, 0, 50, 165);
        graphics3.setColor(Color.red);
        graphics3.fillArc(0, 40, 40, 30, 0, 180);
        graphics3.setColor(Color.yellow);
        graphics3.fillArc(0, 15, 40, 80, 180, 180);
        graphics3.setColor(Color.red);
        graphics3.drawLine(20, 95, 20, 150);
        graphics3.fillPolygon(array3, array4, 3);
    }
    
    public void run() {
        while (Car.points <= 52) {
            if (Car.points == 52 || Car.kill) {
                this.rd.stop();
                this.repaint();
                this.thr.stop();
            }
            if (Car.points % 4 == 0) {
                road.j = 0;
                Car.pts = Car.points;
                ++Car.points;
                --Car.delay;
                if (Car.delay <= 0) {
                    Car.delay = 0;
                }
                road.flag = 1;
                this.repaint();
            }
            try {
                Thread.sleep(Car.delay);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void destroy() {
        this.thr.stop();
        this.rd.stop();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (event.id == 501) {
            if (Car.position == 235) {
                Car.position = 355;
            }
            else {
                Car.position = 235;
            }
            this.repaint();
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (!Car.kill) {
            if (this.msg) {
                graphics.setColor(Color.black);
                graphics.fillRect(0, 0, 640, 400);
                graphics.setColor(Color.yellow);
                graphics.setFont(new Font("TimesRoman", 1, 16));
                graphics.drawString("TO PLAY THE GAME CLICK ANY MOUSE BUTTON", 140, 100);
                graphics.drawString("THE CAR WILL MOVE IN OPPOSITE DIRECTION", 140, 150);
                graphics.drawString("WAIT A MINUTE......", 230, 200);
                this.msg = false;
                try {
                    Thread.sleep(3000L);
                }
                catch (Exception ex) {}
                graphics.setColor(Color.black);
                graphics.fillRect(0, 0, 640, 400);
            }
            graphics.setColor(Color.white);
            graphics.fillRect(200, 0, 10, 400);
            graphics.fillRect(440, 0, 10, 400);
            graphics.drawImage(this.img, Car.position, 300, this);
            graphics.setColor(Color.yellow);
            graphics.fillRect(550, 5, 637, 25);
            graphics.setColor(Color.blue);
            graphics.setFont(new Font("TimesRoman", 1, 20));
            graphics.drawString("Score :" + Car.pts, 557, 22);
            if (Car.points >= 52) {
                for (int i = 0; i < 3; ++i) {
                    graphics.setColor(Color.yellow);
                    graphics.drawString("Have a Cuppa Java", 240, 100);
                    graphics.drawImage(this.cup, 300, 100, this);
                    graphics.setColor(Color.yellow);
                    graphics.fillRect(550, 5, 637, 25);
                    graphics.setColor(Color.blue);
                    graphics.setFont(new Font("TimesRoman", 1, 20));
                    graphics.drawString("Score :50", 557, 22);
                    try {
                        Thread.sleep(500L);
                    }
                    catch (InterruptedException ex2) {}
                }
            }
        }
        else {
            graphics.setColor(Color.yellow);
            graphics.drawString("YOU HAVE LOST THE GAME", 250, 200);
        }
    }
    
    public Car() {
        this.msg = true;
    }
    
    static {
        Car.position = 235;
        Car.delay = 50;
        Car.pts = 50;
    }
}
