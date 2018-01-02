// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Frame;

public final class bD extends aI implements aB
{
    public final String q(final Object o) {
        if (o == super.q.q) {
            return al.q("Type your message here, then hit ENTER or click \"Send\" to send it to all users in the current room.");
        }
        return null;
    }
    
    public final boolean q() {
        return super.q.o && bU.q;
    }
    
    public final void w() {
        bU.q(super.q, super.q);
    }
    
    public bD(final Frame frame, final co co, final l l) {
        super(co, l, new bY(co, false));
    }
}
