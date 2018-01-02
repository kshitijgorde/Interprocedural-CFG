// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.property.loader;

import javax.swing.ImageIcon;
import java.net.URL;
import jmaster.util.property.D;
import jmaster.util.property.A;

public class ImageIconPropertyLoader implements A
{
    static /* synthetic */ Class class$javax$swing$ImageIcon;
    
    public Object loadProperty(final String s, final D d, final String s2, final int n) {
        Object o = null;
        try {
            o = new ImageIcon(new URL(s));
        }
        catch (Exception ex) {}
        if (o == null) {
            o = new ImageIcon(this.getClass().getResource(s));
        }
        return o;
    }
    
    public Class getPropertyClass() {
        return (ImageIconPropertyLoader.class$javax$swing$ImageIcon == null) ? (ImageIconPropertyLoader.class$javax$swing$ImageIcon = class$("javax.swing.ImageIcon")) : ImageIconPropertyLoader.class$javax$swing$ImageIcon;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
