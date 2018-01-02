import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import java.awt.Toolkit;

// 
// Decompiled by Procyon v0.5.30
// 

public class Main
{
    public static void main(final String[] args) {
        final Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        final UI_TuringMachine applet = new UI_TuringMachine(false);
        final JFrame frame = new JFrame();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                final JFrame f = (JFrame)e.getSource();
                f.setVisible(false);
                f.dispose();
                System.exit(0);
            }
        });
        frame.setTitle("Turing Machine");
        frame.setResizable(false);
        frame.getContentPane().add(applet);
        applet.init();
        applet.start();
        frame.setBounds((int)(screenDim.getWidth() - 800.0) / 2, (int)(screenDim.getHeight() - 600.0) / 2, 800, 600);
        frame.setVisible(true);
    }
}
