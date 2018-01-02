import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.Toolkit;
import java.awt.Frame;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class TankGame extends WindowAdapter
{
    private TankApplet applet;
    
    TankGame() {
        final Frame frame = new Frame("TankGame");
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int)(screenSize.width * 0.75), (int)(screenSize.height * 0.75));
        frame.setLocation((int)(screenSize.width * 0.125), (int)(screenSize.height * 0.09375));
        this.applet = new TankApplet();
        this.applet.local = true;
        frame.addWindowListener(this);
        frame.add(this.applet);
        this.applet.init();
        frame.show();
        this.applet.start();
    }
    
    public static void main(final String[] array) {
        new TankGame();
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.applet.stop();
        System.exit(0);
    }
}
