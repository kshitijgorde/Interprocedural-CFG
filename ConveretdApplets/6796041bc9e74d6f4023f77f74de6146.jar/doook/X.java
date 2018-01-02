// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Frame;

public class X extends aj implements aB
{
    public String a(final Object o) {
        if (o == super.b) {
            return ao.e("Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
        }
        return null;
    }
    
    public boolean c() {
        return false;
    }
    
    public X(final Frame frame, final t t, final int n) {
        super(frame, t, n, new W(t, false));
    }
}
