import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class Flipbook extends Panel implements Runnable
{
    protected Studio whocalls;
    protected Artist artist;
    Image nextpage;
    Graphics drawspace;
    Color background;
    Dimension pagesize;
    
    Flipbook(final Studio whocalls, final Artist artist, final int maxheight, final int maxwidth) {
        this.background = Color.white;
        this.whocalls = whocalls;
        this.artist = artist;
        artist.maxheight = maxheight;
        artist.maxwidth = maxwidth;
    }
    
    public void paint(final Graphics graphics) {
        this.artist.compose(graphics);
    }
    
    public void run() {
        while (true) {
            try {
                Thread.currentThread();
                Thread.sleep(50L);
                synchronized (this) {
                    while (this.whocalls.flipsuspended) {
                        this.wait();
                    }
                }
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
    }
    
    public void update(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this.nextpage == null || size.width != this.pagesize.width || size.height != this.pagesize.height) {
            this.nextpage = this.createImage(size.width, size.height);
            this.pagesize = size;
            this.drawspace = this.nextpage.getGraphics();
        }
        this.drawspace.setColor(this.background);
        this.drawspace.fillRect(0, 0, size.width, size.height);
        this.paint(this.drawspace);
        graphics.drawImage(this.nextpage, 0, 0, this);
    }
    
    class Mouser extends MouseAdapter
    {
        public synchronized void mouseClicked(final MouseEvent mouseEvent) {
            mouseEvent.consume();
            System.out.println("Mouse click");
            if (!(Flipbook.this.whocalls.flipsuspended ^= true)) {
                System.out.println("Resume!");
                synchronized (Flipbook.this.whocalls.theFlipbookthread) {
                    System.out.println("Synch! " + Flipbook.this.whocalls.theFlipbookthread);
                    Flipbook.this.whocalls.theFlipbookthread.notify();
                }
                // monitorexit(this.this$0.whocalls.theFlipbookthread)
            }
        }
    }
}
