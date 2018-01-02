// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.awt.Toolkit;
import java.awt.Image;

public class AWTImageLoader implements ImageLoader
{
    public Image getImage(final String s) {
        return Toolkit.getDefaultToolkit().getImage(s);
    }
}
