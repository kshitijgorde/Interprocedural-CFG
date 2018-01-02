// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.property.loader;

import java.awt.Insets;
import jmaster.util.property.D;
import jmaster.util.property.A;

public class InsetsPropertyLoader implements A
{
    static /* synthetic */ Class class$java$awt$Insets;
    
    private int A(final int[] array, final int n) {
        return (array.length > n) ? array[n] : array[array.length - 1];
    }
    
    public Class getPropertyClass() {
        return (InsetsPropertyLoader.class$java$awt$Insets == null) ? (InsetsPropertyLoader.class$java$awt$Insets = class$("java.awt.Insets")) : InsetsPropertyLoader.class$java$awt$Insets;
    }
    
    public Object loadProperty(final String s, final D d, final String s2, final int n) {
        final int[] i = d.I(s2);
        return new Insets(this.A(i, 0), this.A(i, 1), this.A(i, 2), this.A(i, 3));
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
