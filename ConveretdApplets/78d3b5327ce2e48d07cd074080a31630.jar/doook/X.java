// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Frame;

public class X extends ai implements aO
{
    public String a(final Object o) {
        if (o == super.b) {
            return ar.b("Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
        }
        return null;
    }
    
    public X(final Frame frame, final as as, final int n) {
        super(frame, as, n, new T(as, false));
    }
}
