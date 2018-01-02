import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowListener;
import java.awt.Frame;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Studio extends Applet
{
    Flipbook theFlipbook;
    Artist theArtist;
    boolean flipsuspended;
    
    public void init() {
        this.theArtist = new Artist();
        this.setLayout(new BorderLayout(5, 5));
        this.add("Center", this.theFlipbook = new Flipbook(this, this.theArtist, 200, 200));
        this.theFlipbook.addMouseListener(new Mouser());
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("My Window");
        final Studio studio = new Studio();
        frame.addWindowListener(new WL());
        frame.add("Center", studio);
        frame.setSize(300, 300);
        studio.init();
        studio.start();
        frame.setVisible(true);
    }
    
    public void start() {
        new Thread(this.theFlipbook, "Flipbookthread").start();
    }
    
    class Mouser extends MouseAdapter
    {
        public synchronized void mouseClicked(final MouseEvent mouseEvent) {
            mouseEvent.consume();
            if (!(Studio.this.flipsuspended ^= true)) {
                synchronized (Studio.this.theFlipbook) {
                    Studio.this.theFlipbook.notify();
                }
                // monitorexit(this.this$0.theFlipbook)
            }
        }
    }
    
    static class WL extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            System.exit(0);
        }
    }
}
