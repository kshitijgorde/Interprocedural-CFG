// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play;

import java.util.Vector;

class g implements Runnable
{
    final /* synthetic */ f a;
    
    private g(final f a) {
        this.a = a;
    }
    
    public void run() {
        try {
            while (this.a.a) {
                final Runnable a = this.a.b();
                if (a != null) {
                    a.run();
                }
            }
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
