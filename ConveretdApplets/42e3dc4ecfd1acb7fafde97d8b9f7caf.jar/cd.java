import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.Image;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Component;
import au.com.rocketdog.project.awc.applet.images.ImageRes;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class cd extends JDialog implements WindowListener, Runnable, MouseListener
{
    public bh a;
    public bg b;
    public b9 c;
    
    public cd(final bh a) {
        super(Main.h());
        this.a = a;
        this.setTitle(a.r());
        final BorderLayout layout = new BorderLayout();
        layout.setHgap(1);
        this.getContentPane().setLayout(layout);
        this.setBackground(Color.white);
        this.b = new bg(ImageRes.b, 240, 180);
        this.addWindowListener(this);
        this.getContentPane().add(this.b);
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.setSize(240, 180);
        this.setLocation(screenSize.width / 2 - this.getSize().width / 2, screenSize.height / 2 - this.getSize().height / 2);
        this.c = new b9(a.a(), "awcimlive", ImageRes.c);
        this.getContentPane().add(this.c, "South");
        a.a(true);
        a.p();
        this.b.addMouseListener(this);
        this.pack();
        this.setResizable(false);
        new Thread(this).start();
    }
    
    public void run() {
        try {
            final Image image = Toolkit.getDefaultToolkit().getImage(new URL(this.a.b()));
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForAll();
            this.b.a(image);
            this.repaint();
        }
        catch (Exception ex) {}
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.a.a(false);
        this.a.p();
        this.dispose();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.c.a();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
}
