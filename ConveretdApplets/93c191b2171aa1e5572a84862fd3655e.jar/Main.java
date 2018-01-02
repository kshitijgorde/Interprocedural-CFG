import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.util.Random;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Main
{
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final Color WINDOW_COLOR;
    private static final int _seed;
    public static final Random RANDOM;
    public static final boolean DEBUG = false;
    
    public static void main(final String[] args) {
        final Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        final UI_SortingAlgorithms applet = new UI_SortingAlgorithms(false);
        final JFrame frame = new JFrame();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                final JFrame f = (JFrame)e.getSource();
                f.setVisible(false);
                f.dispose();
                System.exit(0);
            }
        });
        frame.setTitle("Sorting Algorithms");
        frame.getContentPane().add(applet);
        applet.init();
        applet.start();
        frame.setBounds((int)(screenDim.getWidth() - 800.0) / 2, (int)(screenDim.getHeight() - 600.0) / 2, 800, 600);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    static {
        WINDOW_COLOR = new Color(123, 123, 123);
        _seed = new Random().nextInt();
        RANDOM = new Random(Main._seed);
    }
}
