import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import java.awt.Component;
import javax.swing.UIManager;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PolySolver extends JApplet
{
    public void init() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {}
        this.getContentPane().add(new MainPanel(this));
    }
    
    public static void main(final String[] array) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {}
        final JFrame frame = new JFrame("PolySolver");
        frame.setDefaultCloseOperation(3);
        frame.getContentPane().add(new MainPanel((JApplet)null));
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        frame.pack();
        frame.setVisible(true);
    }
    
    public String getAppletInfo() {
        return "PolySolver, by Jaap Scherphuis";
    }
}
