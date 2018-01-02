import java.applet.Applet;
import javax.swing.ImageIcon;
import java.awt.event.WindowListener;
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
    ButtonsPanel buttonsPanel;
    ColorPanel colorPanel;
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
        this.drawPanel = new DrawPanel(this.builderPanel);
        contentPane.add((Component)(this.buttonsPanel = new ButtonsPanel(this.drawPanel)), "West");
        this.builderPanel.setButtons(this.buttonsPanel);
        contentPane.add((Component)(this.colorPanel = new ColorPanel(this.drawPanel)), "South");
        this.builderPanel.setColor(this.colorPanel);
        contentPane.add((Component)this.drawPanel, "Center");
    }
    
    public void destroy() {
        this.remove((Component)this.drawPanel);
        this.remove((Component)this.buttonsPanel);
        this.remove((Component)this.colorPanel);
    }
    
    public static void main(final String[] array) {
        final JFrame frame = new JFrame();
        frame.addWindowListener((WindowListener)new PaintApp$1());
        final PaintApp paintApp = new PaintApp();
        paintApp.isApplet = false;
        paintApp.init();
        ((Applet)paintApp).start();
        final Container contentPane = frame.getContentPane();
        frame.setTitle("Paint Frame");
        frame.setIconImage(new ImageIcon("images/picIcon.gif").getImage());
        contentPane.add("Center", (Component)paintApp);
        frame.setSize(675, 500);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
