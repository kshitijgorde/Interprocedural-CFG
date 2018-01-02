// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Frame;

public class aA extends bc implements aj
{
    public String a(final Object o) {
        if (o == super.d) {
            return aG.a("Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
        }
        return null;
    }
    
    public aA(final Frame frame, final be be, final int n) {
        super(frame, be, n, new ae(be, false));
    }
}
