import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class linesdemo extends Applet implements Runnable, ActionListener
{
    Button b1;
    Thread t;
    Thread t1;
    Thread t2;
    Thread t3;
    Thread color;
    int linecolor;
    int sparkles;
    Image flick;
    Graphics gflick;
    int leftx;
    int rightx;
    int y1;
    
    public linesdemo() {
        this.linecolor = 5;
        this.sparkles = 0;
        this.leftx = 200;
        this.rightx = 200;
        this.y1 = 200;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.b1) {
            this.leftx = (int)(Math.round(Math.random()) * 400L);
            this.rightx = (int)(Math.round(Math.random()) * 400L);
            this.y1 = (int)(Math.random() * 300.0);
        }
    }
    
    public void init() {
        this.flick = this.createImage(this.size().width, this.size().height);
        this.gflick = this.flick.getGraphics();
        (this.b1 = new Button("animate")).addActionListener(this);
        this.add(this.b1);
        this.t1 = new Thread(this);
        this.t2 = new Thread(this);
        this.t3 = new Thread(this);
        this.color = new Thread(this);
        this.t1.start();
        this.t2.start();
        this.t3.start();
        this.color.start();
    }
    
    public void paint(final Graphics graphics) {
        this.gflick.setColor(Color.black);
        this.gflick.fillRect(0, 0, this.size().width, this.size().height);
        this.gflick.setColor(new Color(150, 50, this.linecolor));
        for (int i = 0; i < this.size().width; i += 30) {
            this.gflick.drawLine(200, this.y1, this.rightx, i);
            this.gflick.drawLine(200, this.y1, this.leftx, i);
        }
        this.gflick.setColor(new Color(this.sparkles, this.sparkles, this.sparkles));
        for (int j = 0; j < 10; ++j) {
            this.gflick.fillRect((int)(this.getSize().width * Math.random()), (int)(this.getSize().height * Math.random()), 1, 1);
        }
        graphics.drawImage(this.flick, 0, 0, this);
    }
    
    public void run() {
        this.t = Thread.currentThread();
        if (this.t == this.t1) {
            while (true) {
                this.leftx = 200;
                while (this.leftx > 0) {
                    try {
                        Thread.sleep(20L);
                    }
                    catch (Exception ex) {}
                    this.repaint();
                    --this.leftx;
                }
            }
        }
        else if (this.t == this.t2) {
            while (true) {
                this.rightx = 200;
                while (this.rightx <= 400) {
                    try {
                        Thread.sleep(20L);
                    }
                    catch (Exception ex2) {}
                    this.repaint();
                    ++this.rightx;
                }
            }
        }
        else if (this.t == this.color) {
            while (true) {
                this.linecolor = 5;
                while (this.linecolor <= 255) {
                    if (this.linecolor == 255) {
                        this.linecolor = 255;
                        while (this.linecolor >= 5) {
                            try {
                                Thread.sleep(50L);
                            }
                            catch (Exception ex3) {}
                            this.repaint();
                            --this.linecolor;
                        }
                    }
                    try {
                        Thread.sleep(50L);
                    }
                    catch (Exception ex4) {}
                    this.repaint();
                    ++this.linecolor;
                }
            }
        }
        else {
            if (this.t != this.t3) {
                return;
            }
            while (true) {
                this.sparkles = (int)(Math.round(Math.random()) * 255L);
                try {
                    Thread.sleep(500L);
                }
                catch (Exception ex5) {}
                this.repaint();
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
