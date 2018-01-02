// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.property.loader;

import java.awt.Color;
import jmaster.util.property.D;
import jmaster.util.property.A;

public class ColorPropertyLoader implements A
{
    static /* synthetic */ Class class$java$awt$Color;
    
    public Object loadProperty(final String s, final D d, final String s2, final int n) {
        return new Color(D.R(s));
    }
    
    public Class getPropertyClass() {
        return (ColorPropertyLoader.class$java$awt$Color == null) ? (ColorPropertyLoader.class$java$awt$Color = class$("java.awt.Color")) : ColorPropertyLoader.class$java$awt$Color;
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
