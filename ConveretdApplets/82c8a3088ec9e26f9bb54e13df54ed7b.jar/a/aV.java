// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;

public final class aV
{
    Vector q;
    int q;
    private static int w;
    
    public aV() {
        this.q = new Vector();
    }
    
    public final void q(final Object o) {
        ++aV.w;
        this.q.addElement(o);
    }
    
    public final Object q() {
        ++this.q;
        return this.q.elementAt(this.q - 1);
    }
    
    public final String toString() {
        return "MenuSet attributes\nSize=" + aV.w;
    }
}