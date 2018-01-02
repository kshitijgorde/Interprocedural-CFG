import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JackhammerDuke extends Applet implements Runnable
{
    int seqslot;
    int nimgs;
    Image imgs;
    String dir;
    Thread kicker;
    int pause;
    double x;
    int imgsWidth;
    int imgsHeight;
    private int[] sequence;
    boolean erase;
    
    public JackhammerDuke() {
        this.seqslot = 0;
        this.nimgs = 4;
        this.kicker = null;
        this.imgsWidth = 328;
        this.imgsHeight = 90;
        this.sequence = new int[] { 2, 1, 0, 1, 0, 1, 0, 1, 2, 1, 0, 2, 0, 1, 0, 2, 3 };
    }
    
    public void paint(final Graphics graphics) {
        final int n = this.sequence[this.seqslot];
        if (this.imgs != null && n < this.nimgs) {
            final int n2 = this.imgsWidth / this.nimgs;
            this.x = Math.max(0.0, Math.min(this.size().width - n2, this.x + Math.random() * 4.0 - 2.0));
            graphics.clipRect((int)this.x, 0, n2, this.imgsHeight);
            graphics.drawImage(this.imgs, (int)this.x - n * n2, 0, this);
            this.erase = (n == 3);
        }
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        this.imgs = this.getImage(this.getCodeBase(), "images/jack.gif");
        if (this.imgs != null) {
            this.x = (this.size().width - this.imgsWidth / this.nimgs) / 2;
            while (this.size().width > 0 && this.size().height > 0 && this.kicker != null) {
                if (this.seqslot == 0) {
                    this.play(this.getCodeBase(), "audio/jackhammer-short.au");
                }
                this.repaint();
                try {
                    Thread.sleep((this.seqslot == this.sequence.length - 1) ? 500 : 100);
                }
                catch (InterruptedException ex) {}
                this.seqslot = (this.seqslot + 1) % this.sequence.length;
            }
        }
    }
    
    public void start() {
        if (this.kicker == null) {
            (this.kicker = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.kicker = null;
    }
    
    public void update(final Graphics graphics) {
        if (this.erase || this.sequence[this.seqslot] == 3) {
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, this.size().width, this.size().height);
            this.erase = false;
        }
        this.paint(graphics);
    }
}
