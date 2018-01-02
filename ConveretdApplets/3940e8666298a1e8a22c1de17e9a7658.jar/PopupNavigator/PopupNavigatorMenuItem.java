// 
// Decompiled by Procyon v0.5.30
// 

package PopupNavigator;

import java.net.URL;
import java.awt.MenuItem;

class PopupNavigatorMenuItem extends MenuItem
{
    public URL url;
    public String url_string;
    public String frame;
    public String fontName;
    public Integer fontStyle;
    public Integer fontSize;
    
    public PopupNavigatorMenuItem(final String s) {
        super(s);
        this.url = null;
        this.url_string = null;
        this.frame = null;
        this.fontName = null;
        this.fontStyle = null;
        this.fontSize = null;
    }
}
