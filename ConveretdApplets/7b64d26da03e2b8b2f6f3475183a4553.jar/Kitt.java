import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Kitt extends Applet implements Runnable
{
    private Thread th;
    Image im;
    Graphics g2;
    Color kleur;
    int[] kl;
    int[] pos;
    int dir;
    int var;
    
    public Kitt() {
        this.pos = new int[15];
        this.dir = 0;
        this.var = 1;
    }
    
    public void destroy() {
        this.g2.dispose();
    }
    
    public void init() {
        this.im = this.createImage(this.getSize().width, this.getSize().height);
        this.g2 = this.im.getGraphics();
        this.setBackground(Color.black);
        this.kleur = new Color(20, 0, 0);
        for (int i = 0; i < 15; ++i) {
            this.pos[i] = 0;
        }
        this.kl = new int[15];
        for (int j = 0; j < 15; ++j) {
            this.kl[j] = 0;
        }
        this.kl[this.pos[0]] = 225;
    }
    
    public void paint(final Graphics graphics) {
        this.g2.setColor(Color.red);
        this.g2.drawRect(10, 10, 300, 20);
        for (int i = 0; i < 15; ++i) {
            this.kleur = new Color(this.kl[i], 0, 0);
            this.g2.setColor(this.kleur);
            this.g2.fillOval(15 + i * 20, 15, 10, 10);
        }
        graphics.drawImage(this.im, 0, 0, this);
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            this.repaint();
            final int[] pos = this.pos;
            final int n = 0;
            pos[n] += this.var;
            if (this.pos[0] == 14) {
                this.var = -1;
            }
            if (this.pos[0] == 0) {
                this.var = 1;
            }
            final int[] array = new int[15];
            for (int i = 0; i < 15; ++i) {
                array[i] = this.pos[i];
            }
            for (int j = 1; j < 15; ++j) {
                array[j] = this.pos[j - 1];
            }
            for (int k = 1; k < 15; ++k) {
                this.pos[k] = array[k];
            }
            for (int l = 0; l < 15; ++l) {
                this.kl[this.pos[14 - l]] = l * 15;
            }
        }
    }
    
    public void start() {
        if (this.th == null) {
            (this.th = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.th != null) {
            this.th.interrupt();
        }
    }
    
    public void update(final Graphics graphics) {
        this.g2.setColor(this.getBackground());
        this.g2.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.paint(graphics);
    }
}
