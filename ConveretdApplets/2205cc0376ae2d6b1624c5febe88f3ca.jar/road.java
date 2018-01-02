import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class road extends Thread
{
    int i;
    public static int j;
    Graphics g;
    Image car2;
    ImageObserver io;
    public static int flag;
    boolean msg;
    
    road(final Graphics g, final Image car2, final ImageObserver io) {
        this.msg = true;
        this.g = g;
        this.io = io;
        this.car2 = car2;
    }
    
    public void run() {
        this.drawRoad(this.g);
    }
    
    public void drawRoad(final Graphics graphics) {
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
        while (road.j <= 1000) {
            this.i = -1000;
            while (this.i <= 479) {
                graphics.setColor(Color.black);
                graphics.fillRect(320, this.i + road.j, 10, this.i + road.j + 50);
                graphics.setColor(Color.white);
                graphics.fillRect(320, this.i + road.j + 10, 10, this.i + road.j + 60);
                this.i += 60;
            }
            graphics.clearRect(235, road.j - 10, 60, 60);
            graphics.drawImage(this.car2, 235, road.j, this.io);
            graphics.clearRect(355, -150 + (road.j - 10), 60, 60);
            graphics.drawImage(this.car2, 355, -150 + road.j, this.io);
            graphics.clearRect(235, -300 + (road.j - 10), 60, 60);
            graphics.drawImage(this.car2, 235, -300 + road.j, this.io);
            graphics.clearRect(355, -450 + (road.j - 10), 60, 60);
            graphics.drawImage(this.car2, 355, -450 + road.j, this.io);
            Label_0518: {
                if ((Car.position != 235 || road.j < 250 || road.j > 360) && (Car.position != 355 || road.j < 400 || road.j > 510) && (Car.position != 235 || road.j < 550 || road.j > 660)) {
                    if (Car.position != 355 || road.j < 700 || road.j > 810) {
                        break Label_0518;
                    }
                }
                try {
                    Thread.sleep(2000L);
                    Car.kill = true;
                }
                catch (InterruptedException ex2) {}
            }
            if (road.j >= 360 && (road.j - 360) % 150 == 0) {
                if (road.flag == 1) {
                    --Car.points;
                    road.flag = 0;
                }
                ++Car.points;
                graphics.setColor(Color.yellow);
                graphics.fillRect(550, 5, 637, 25);
                graphics.setColor(Color.blue);
                graphics.setFont(new Font("TimesRoman", 1, 20));
                graphics.drawString("Score :" + Car.points, 557, 22);
            }
            try {
                Thread.sleep(Car.delay);
            }
            catch (InterruptedException ex3) {}
            road.j += 10;
        }
    }
}
