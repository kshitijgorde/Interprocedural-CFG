import java.awt.event.WindowListener;
import java.awt.Frame;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;

// 
// Decompiled by Procyon v0.5.30
// 

public class Showicosa extends Studio
{
    public void init() {
        super.theArtist = new Polyguy();
        this.setLayout(new BorderLayout(5, 5));
        this.add("Center", super.flipbook = new Flipbook((Object)this, super.theArtist, 290, 290));
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("Rotating Icosa");
        final Showicosa showicosa = new Showicosa();
        frame.addWindowListener(new WL());
        frame.add("Center", showicosa);
        frame.setSize(300, 300);
        showicosa.init();
        showicosa.start();
        frame.setVisible(true);
    }
}
