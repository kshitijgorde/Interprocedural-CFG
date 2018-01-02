// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Enumeration;

final class Y implements Runnable
{
    private final aD a;
    
    Y(final aD a) {
        this.a = a;
    }
    
    public final void run() {
        synchronized (aD.a(this.a)) {
            final Enumeration<ai> elements = aD.a(this.a).elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().r();
            }
        }
    }
}
