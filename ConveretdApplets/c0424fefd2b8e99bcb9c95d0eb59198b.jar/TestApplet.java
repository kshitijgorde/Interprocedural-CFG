import java.net.URL;
import java.awt.Point;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Component;
import java.awt.MediaTracker;
import javax.swing.JPanel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TestApplet extends Applet
{
    private static JPanel antPanel;
    private static JPanel defaultPanel;
    
    public void init() {
        final MediaTracker tracker = new MediaTracker(this);
        final URL cb = this.getCodeBase();
        final Image[] images = { this.getImage(cb, "img/bug1.png"), this.getImage(cb, "img/bug2.png"), this.getImage(cb, "img/bug3.png") };
        for (int i = 0; i < images.length; ++i) {
            tracker.addImage(images[i], i);
        }
        try {
            tracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        final JPanel testPanel = new JPanel(new BorderLayout());
        (TestApplet.antPanel = new JPanel(new FlowLayout(1, 10, 20))).add(new JLabel("<html>Use your own set of images<br>for creating animated cursors <br>like this ant...</html>"));
        TestApplet.antPanel.setBorder(BorderFactory.createTitledBorder("Custom Ant Cursor Area"));
        TestApplet.antPanel.setBackground(new Color(255, 255, 128));
        (TestApplet.defaultPanel = new JPanel(new FlowLayout(1, 10, 20))).add(new JLabel("<html>...or just use:<pre>CrasyCursor.create(myComponent);</pre>to get this cursor.</html>"));
        TestApplet.defaultPanel.setBorder(BorderFactory.createTitledBorder("Default CrazyCursor Area"));
        TestApplet.defaultPanel.setBackground(new Color(196, 255, 255));
        testPanel.add(TestApplet.antPanel, "Center");
        testPanel.add(TestApplet.defaultPanel, "East");
        this.setLayout(new BorderLayout());
        this.add(testPanel);
        CrazyCursor4Applets.create(TestApplet.antPanel, images, new Point(15, 0));
        CrazyCursor4Applets.create(TestApplet.defaultPanel);
    }
}
