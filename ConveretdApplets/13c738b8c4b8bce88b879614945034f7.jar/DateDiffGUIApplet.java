import java.awt.Container;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DateDiffGUIApplet extends JApplet
{
    static JFrame frame;
    
    public void init() {
        (DateDiffGUIApplet.frame = new JFrame("Date Difference")).addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                DateDiffGUIApplet.frame.setVisible(false);
            }
        });
        DateDiffGUIApplet.frame.setContentPane(new DateDiffGUI());
        DateDiffGUIApplet.frame.pack();
        DateDiffGUIApplet.frame.setVisible(true);
        DateDiffGUIApplet.frame.setSize(350, 250);
    }
}
