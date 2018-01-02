import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SnowApplet extends Applet implements Runnable
{
    int appletsize_x;
    int appletsize_y;
    int radius;
    int x_pos;
    int x_speed;
    int y_speed;
    int y_pos;
    int mouse_x;
    int mouse_y;
    private Image dbImage;
    private Graphics dbg;
    private Image backImage;
    private Image snowImage;
    private Image wallImage;
    private Font f;
    int snowFlakes;
    int flakesDefault;
    int inScreen;
    int maxRad;
    int minRad;
    String paramImageFile;
    String paramBackground;
    String paramFlakes;
    String paramMinRad;
    String paramMaxRad;
    int[] flakesX;
    int[] flakesY;
    int[] flakesSize;
    int[] flakesSpd;
    
    public SnowApplet() {
        this.radius = 20;
        this.x_pos = 20;
        this.x_speed = 0;
        this.y_speed = 0;
        this.y_pos = this.radius;
        this.mouse_x = 0;
        this.mouse_y = 0;
        this.snowFlakes = 999;
        this.flakesDefault = 50;
        this.inScreen = 0;
        this.maxRad = 10;
        this.minRad = 1;
        this.paramFlakes = "0";
        this.flakesX = new int[this.snowFlakes];
        this.flakesY = new int[this.snowFlakes];
        this.flakesSize = new int[this.snowFlakes];
        this.flakesSpd = new int[this.snowFlakes];
    }
    
    public void init() {
        this.appletsize_x = this.getSize().width;
        this.appletsize_y = this.getSize().height;
        this.paramImageFile = this.getParameter("Image");
        this.paramBackground = this.getParameter("Background");
        this.paramFlakes = this.getParameter("Flakes");
        this.paramMinRad = this.getParameter("MinRad");
        this.paramMaxRad = this.getParameter("MaxRad");
        if (this.paramImageFile != null) {
            this.snowImage = this.getImage(this.getCodeBase(), this.paramImageFile);
        }
        if (this.paramBackground != null) {
            this.backImage = this.getImage(this.getCodeBase(), this.paramBackground);
        }
        if (this.paramFlakes != null) {
            this.snowFlakes = Integer.parseInt(this.paramFlakes);
        }
        else {
            this.snowFlakes = this.flakesDefault;
        }
        if (this.paramMaxRad != null) {
            this.maxRad = Integer.parseInt(this.paramMaxRad);
        }
        if (this.paramMaxRad != null) {
            this.minRad = Integer.parseInt(this.paramMinRad);
        }
    }
    
    public void start() {
        new Thread(this).start();
        System.out.println("Applet Started - Winter Snow Applet by Christopher Ridgeon - chris@planetlara.com");
        this.setFlakeStart(-1);
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2, final Graphics graphics) {
        this.inScreen = 1;
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2, final Graphics graphics) {
        this.inScreen = 1;
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2, final Graphics graphics) {
        this.inScreen = 0;
        return true;
    }
    
    public void stop() {
    }
    
    public void destroy() {
    }
    
    public void update(final Graphics graphics) {
        if (this.dbImage == null) {
            this.dbImage = this.createImage(this.getSize().width, this.getSize().height);
            this.dbg = this.dbImage.getGraphics();
        }
        this.dbg.setColor(this.getBackground());
        this.dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.dbg.setColor(this.getForeground());
        this.paint(this.dbg);
        graphics.drawImage(this.dbImage, 0, 0, this);
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        while (true) {
            this.repaint();
            try {
                Thread.sleep(20L);
                this.changePos();
            }
            catch (InterruptedException ex) {}
            Thread.currentThread().setPriority(10);
        }
    }
    
    public void setFlakeStart(final int n) {
        if (n == -1) {
            for (int i = 0; i < this.snowFlakes; ++i) {
                this.flakesX[i] = (int)(Math.random() * this.appletsize_x + 1.0);
                this.flakesY[i] = (int)(Math.random() * -400.0 - 1.0);
                this.flakesSize[i] = (int)(Math.random() * this.maxRad + (2 + this.minRad));
                this.flakesSpd[i] = (int)(Math.random() * 5.0 + 1.0);
            }
        }
        else {
            this.flakesX[n] = (int)(Math.random() * this.appletsize_x + 1.0);
            this.flakesY[n] = (int)(Math.random() * -400.0 - 1.0);
            this.flakesSize[n] = (int)(Math.random() * this.maxRad + (2 + this.minRad));
            this.flakesSpd[n] = (int)(Math.random() * 5.0 + 1.0);
        }
    }
    
    public void changePos() {
        for (int i = 0; i < this.snowFlakes; ++i) {
            if ((int)(Math.random() * 2.0) == 1) {
                this.flakesX[i] += 2;
            }
            else {
                this.flakesX[i] -= 2;
            }
            if (this.flakesY[i] > this.appletsize_y) {
                this.setFlakeStart(i);
            }
            this.flakesY[i] += this.flakesSpd[i];
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.paramBackground != null) {
            this.setBackground(Color.black);
            graphics.drawImage(this.backImage, 0, 0, this);
        }
        graphics.setColor(Color.white);
        graphics.setFont(this.f = new Font("Arial", 0, 10));
        graphics.drawString("(C)Chris Ridgeon - chris@planetlara.com", 0, 10);
        for (int i = 0; i < this.snowFlakes; ++i) {
            if (this.paramImageFile != null) {
                graphics.drawImage(this.snowImage, this.flakesX[i], this.flakesY[i], this);
            }
            else {
                graphics.fillOval(this.flakesX[i], this.flakesY[i], this.flakesSize[i], this.flakesSize[i]);
            }
        }
    }
}
