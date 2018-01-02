// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.property.loader;

import javax.swing.ImageIcon;
import jmaster.util.property.D;
import jmaster.util.property.A;

public class ImagePropertyLoader implements A
{
    static /* synthetic */ Class class$java$awt$Image;
    
    public Object loadProperty(final String s, final D d, final String s2, final int n) {
        try {
            return new ImageIcon(this.getClass().getResource(s)).getImage();
        }
        catch (Exception ex) {
            throw new RuntimeException("loadProperty failed, value=" + s + ", key=" + s2);
        }
    }
    
    public Class getPropertyClass() {
        return (ImagePropertyLoader.class$java$awt$Image == null) ? (ImagePropertyLoader.class$java$awt$Image = class$("java.awt.Image")) : ImagePropertyLoader.class$java$awt$Image;
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
