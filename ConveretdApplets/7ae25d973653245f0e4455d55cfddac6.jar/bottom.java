import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class bottom extends Applet implements Runnable, MouseListener
{
    int i;
    int j;
    int k;
    int l;
    int x;
    int y;
    int grayCount;
    int currMes;
    int newMes;
    final String[] messages;
    Image background;
    Graphics bg;
    long nextTime;
    MediaTracker mt;
    Thread runner;
    Color[] grays;
    Font f;
    FontMetrics fm;
    cluster clusterApplet;
    
    public void init() {
        this.mt = new MediaTracker(this);
        final InputStream resourceAsStream = this.getClass().getResourceAsStream("row4b.gif");
        try {
            final byte[] array = new byte[resourceAsStream.available()];
            resourceAsStream.read(array);
            this.background = Toolkit.getDefaultToolkit().createImage(array);
        }
        catch (IOException ex) {
            this.background = this.createImage(176, 21);
        }
        this.mt.addImage(this.background, 0);
        try {
            this.mt.waitForID(0);
        }
        catch (InterruptedException ex2) {}
        this.i = 0;
        while (this.i < 5) {
            this.grays[this.i] = new Color((this.i + 1) * 51, (this.i + 1) * 51, (this.i + 1) * 51);
            ++this.i;
        }
        this.f = new Font("SansSerif", 1, 12);
        this.fm = this.getFontMetrics(this.f);
        this.setFont(this.f);
        this.addMouseListener(this);
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.runner != null && this.runner.isAlive()) {
            this.runner.stop();
        }
        this.runner = null;
    }
    
    public void run() {
        while (this.runner != null) {
            try {
                Thread.sleep(Math.max(25L, this.nextTime - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {}
            this.nextTime = System.currentTimeMillis() + 80L;
            if (this.newMes >= 0) {
                if (this.grayCount > 0) {
                    --this.grayCount;
                }
                else {
                    this.currMes = this.newMes;
                    this.newMes = -1;
                }
                this.repaint();
            }
            else {
                if (this.grayCount >= 4 || this.currMes < 0) {
                    continue;
                }
                ++this.grayCount;
                this.repaint();
            }
        }
    }
    
    public void setNewMessage(final int newMes) {
        this.newMes = newMes;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.x = mouseEvent.getX();
        this.y = mouseEvent.getY();
        if ((this.x - 12) * (this.x - 12) + (this.y - 11 * this.y - 11) < 122) {
            if (this.clusterApplet == null) {
                this.clusterApplet = (cluster)this.getAppletContext().getApplet("cluster");
            }
            this.clusterApplet.reset();
            this.clusterApplet.repaint();
        }
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.background, 0, 0, this);
        if (this.currMes >= 0) {
            graphics.setColor(this.grays[this.grayCount]);
            graphics.drawString(this.messages[this.currMes], 100 - this.fm.stringWidth(this.messages[this.currMes]) / 2, 17);
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public bottom() {
        this.currMes = -1;
        this.newMes = -1;
        this.messages = new String[] { "Your turn", "Computer's turn", "You win!", "Computer wins", "Tied game" };
        this.grays = new Color[5];
    }
}
