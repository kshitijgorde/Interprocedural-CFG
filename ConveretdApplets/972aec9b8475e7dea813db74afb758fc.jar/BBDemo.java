import java.awt.Container;
import javax.swing.JFrame;
import java.awt.Component;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BBDemo extends JApplet
{
    public BBDemo() {
        this.add(new BBPanel());
    }
    
    public static void main(final String[] args) {
        final JFrame win = new JFrame("Bouncing Ball Demo");
        win.setDefaultCloseOperation(3);
        win.setContentPane(new BBPanel());
        win.pack();
        win.setVisible(true);
        System.out.println(win.getSize());
    }
}
