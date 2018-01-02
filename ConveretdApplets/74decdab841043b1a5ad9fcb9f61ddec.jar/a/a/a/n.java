// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.applet.Applet;

public final class n
{
    private Applet a;
    
    public n(final Applet a) {
        this.a = a;
    }
    
    public final String a(Enum enum1) {
        final n n = this;
        enum1 = enum1;
        this = n;
        final String parameter;
        if ((parameter = n.a.getParameter(enum1.name())) != null) {
            return parameter;
        }
        return null;
    }
    
    public final String b(final Enum enum1) {
        final String a;
        if ((a = this.a(enum1)) == null) {
            f.b("ERROR you must set " + enum1.name());
            throw new RuntimeException("Error you must set " + enum1.name());
        }
        return a;
    }
}
