// 
// Decompiled by Procyon v0.5.30
// 

package courseware.jfcdep;

import java.awt.Color;
import java.awt.Button;

public class MatchedButton extends Button
{
    public static Color background;
    public static Color foreground;
    
    public MatchedButton() {
        this("");
    }
    
    public MatchedButton(final String s) {
        super(s);
        this.setForeground(MatchedButton.foreground);
        this.setBackground(MatchedButton.background);
    }
    
    public void updateColor() {
        super.setForeground(MatchedButton.foreground);
        super.setBackground(MatchedButton.background);
    }
    
    public void setForeground(final Color foreground) {
        super.setForeground(MatchedButton.foreground = foreground);
    }
    
    public void setBackground(final Color background) {
        super.setBackground(MatchedButton.background = background);
    }
    
    static {
        MatchedButton.background = new Color(204, 204, 204);
        MatchedButton.foreground = new Color(0, 0, 0);
    }
}
