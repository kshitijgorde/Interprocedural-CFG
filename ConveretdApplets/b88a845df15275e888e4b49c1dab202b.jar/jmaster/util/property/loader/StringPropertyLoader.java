// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.property.loader;

import jmaster.util.property.D;
import jmaster.util.property.A;

public class StringPropertyLoader implements A
{
    static /* synthetic */ Class class$java$lang$String;
    
    public Object loadProperty(final String s, final D d, final String s2, final int n) {
        return s;
    }
    
    public Class getPropertyClass() {
        return (StringPropertyLoader.class$java$lang$String == null) ? (StringPropertyLoader.class$java$lang$String = class$("java.lang.String")) : StringPropertyLoader.class$java$lang$String;
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
