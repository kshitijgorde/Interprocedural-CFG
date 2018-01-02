import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JToggleButton;

// 
// Decompiled by Procyon v0.5.30
// 

public class DrawActionButton extends JToggleButton
{
    private static final Dimension BUTTON_SIZE;
    private static final Color myBlue;
    
    public DrawActionButton(final String s) {
        this.setBackground(DrawActionButton.myBlue);
        this.setIcon(new ImageIcon(s));
        this.setPreferredSize(DrawActionButton.BUTTON_SIZE);
    }
    
    public DrawActionButton(final URL url) {
        this.setBackground(DrawActionButton.myBlue);
        this.setIcon(new ImageIcon(url));
        this.setPreferredSize(DrawActionButton.BUTTON_SIZE);
    }
    
    static {
        BUTTON_SIZE = new Dimension(50, 50);
        myBlue = new Color(192, 192, 255);
    }
}
