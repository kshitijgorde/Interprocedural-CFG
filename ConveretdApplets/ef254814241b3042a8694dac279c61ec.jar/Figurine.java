import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class Figurine extends Canvas implements Runnable
{
    private Thread animation;
    private Image figurine;
    private Image background;
    private Image buffer;
    private boolean runOut;
    private int frame;
    private int frames;
    private int delay;
    
    public Figurine(final Image figurine, final int frames, final int delay) {
        this.runOut = false;
        this.figurine = figurine;
        this.frames = frames;
        this.delay = delay;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.size().width;
        final int height = this.size().height;
        if (this.buffer == null) {
            this.buffer = this.createImage(width, height);
        }
        if (this.background == null) {
            this.background = this.createImage(width, height);
            final Graphics graphics2 = this.background.getGraphics();
            if (((Figurines)this.getParent()).getBackgroundImage() != null) {
                graphics2.drawImage(((Figurines)this.getParent()).getBackgroundImage(), -this.location().x, -this.location().y, this);
                while ((this.checkImage(this.background, this) & 0x20) != 0x20) {
                    try {
                        Thread.currentThread();
                        Thread.sleep(50L);
                    }
                    catch (InterruptedException ex) {}
                }
            }
            else {
                graphics2.setColor(this.getParent().getBackground());
                graphics2.fillRect(0, 0, width, height);
            }
        }
        final int n = width;
        int n2;
        if (this.frame < this.frames) {
            n2 = n * this.frame;
        }
        else {
            n2 = n * (2 * (this.frames - 1) - this.frame);
        }
        final Graphics graphics3 = this.buffer.getGraphics();
        graphics3.drawImage(this.background, 0, 0, this);
        graphics3.drawImage(this.figurine, -n2, 0, this);
        graphics.drawImage(this.buffer, 0, 0, this);
    }
    
    public void run() {
        while (!this.runOut || this.frame != 0) {
            try {
                this.frame = (this.frame + 1) % (2 * (this.frames - 1));
                this.repaint();
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void stop() {
        if (this.animation != null) {
            this.animation.stop();
            this.animation = null;
            this.frame = 0;
        }
    }
    
    public void toggle() {
        if (this.animation != null && this.animation.isAlive()) {
            this.runOut = !this.runOut;
            return;
        }
        this.start();
    }
    
    public void start() {
        this.stop();
        this.runOut = false;
        this.frame = 0;
        (this.animation = new Thread(this)).start();
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (n >= 0 && n < this.size().width && n2 >= 0 && n2 < this.size().height) {
            ((Figurines)this.getParent()).stopDemo();
            this.toggle();
        }
        return true;
    }
}
