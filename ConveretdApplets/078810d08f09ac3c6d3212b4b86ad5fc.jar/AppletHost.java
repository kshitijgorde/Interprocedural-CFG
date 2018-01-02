import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class AppletHost
{
    public static void main(final String[] array) {
        final Frame frame = new Frame("Mines");
        frame.setSize(640, 350);
        final Mines mines = new Mines();
        frame.add(mines, "Center");
        mines.init();
        mines.start();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }
}
