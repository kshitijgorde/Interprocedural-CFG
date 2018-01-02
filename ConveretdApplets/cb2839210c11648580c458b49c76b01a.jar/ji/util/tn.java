// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import ji.io.h;
import java.awt.Component;

class tn implements Runnable
{
    private final /* synthetic */ Component a;
    
    tn(final Component a) {
        this.a = a;
    }
    
    public void run() {
        if (i.c(102)) {
            h.d(null, "Requesting focus on ".concat(String.valueOf(String.valueOf(this.a))));
        }
        this.a.requestFocus();
    }
}
