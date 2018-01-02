// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends.action;

import edu.berkeley.guir.prefuse.util.FontLib;
import ytdfriends.FriendsLib;
import edu.berkeley.guir.prefuse.VisualItem;
import java.awt.Font;
import edu.berkeley.guir.prefuse.action.assignment.FontFunction;

public class FriendsFontFunction extends FontFunction
{
    private int m_fontSize;
    private String m_fontFamily;
    private Font m_focusFont;
    
    public FriendsFontFunction() {
        this(11);
    }
    
    public FriendsFontFunction(final int size) {
        this.m_fontFamily = "SansSerif";
        this.setFontSize(size);
    }
    
    public Font getFont(final VisualItem item) {
        if (item.isHighlighted() || item.getDOI() == 0.0) {
            final int hvalue = FriendsLib.getHighlightValue(item);
            if (hvalue == 0 || hvalue == 1) {
                return this.m_focusFont;
            }
        }
        return this.defaultFont;
    }
    
    public void setFontSize(final int size) {
        this.m_fontSize = size;
        this.setDefaultFont(FontLib.getFont(this.m_fontFamily, 0, this.m_fontSize));
        this.m_focusFont = FontLib.getFont(this.m_fontFamily, 0, this.m_fontSize);
    }
}
