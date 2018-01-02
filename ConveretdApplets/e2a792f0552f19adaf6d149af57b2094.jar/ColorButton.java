import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JToggleButton;

// 
// Decompiled by Procyon v0.5.30
// 

public class ColorButton extends JToggleButton
{
    private static final Dimension BUTTON_SIZE;
    private static final int BORDER_SIZE = 10;
    
    public ColorButton(final Color background, final String s) {
        this.setBackground(background);
        this.setPreferredSize(ColorButton.BUTTON_SIZE);
        this.setIcon(new ImageIcon(s));
        this.setPressedIcon(new ImageIcon(s));
    }
    
    public ColorButton(final Color background, final URL url) {
        this.setBackground(background);
        this.setPreferredSize(ColorButton.BUTTON_SIZE);
        this.setIcon(new ImageIcon(url));
        this.setPressedIcon(new ImageIcon(url));
    }
    
    static {
        BUTTON_SIZE = new Dimension(42, 42);
    }
}
