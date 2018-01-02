import javax.swing.ImageIcon;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.Component;
import java.net.URL;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PaintApp extends JApplet
{
    PanelMediator builderPanel;
    DrawPanel drawPanel;
    public boolean isApplet;
    
    public PaintApp() {
        this.isApplet = true;
    }
    
    public void init() {
        final Container contentPane = this.getContentPane();
        this.builderPanel = new PanelMediator();
        if (this.isApplet) {
            this.builderPanel.URL_STRING = this.getCodeBase();
        }
        else {
            try {
                this.builderPanel.URL_STRING = new URL("file://");
            }
            catch (Exception ex) {}
        }
        contentPane.add(this.drawPanel = new DrawPanel(this.builderPanel), "Center");
    }
    
    public void destroy() {
        this.remove(this.drawPanel);
    }
    
    public static void main(final String[] array) {
        final JFrame frame = new JFrame();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        final PaintApp paintApp = new PaintApp();
        paintApp.isApplet = false;
        paintApp.init();
        paintApp.start();
        final Container contentPane = frame.getContentPane();
        frame.setTitle("Paint Frame");
        frame.setIconImage(new ImageIcon("images/picIcon.gif").getImage());
        contentPane.add("Center", paintApp);
        frame.setSize(550, 500);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
