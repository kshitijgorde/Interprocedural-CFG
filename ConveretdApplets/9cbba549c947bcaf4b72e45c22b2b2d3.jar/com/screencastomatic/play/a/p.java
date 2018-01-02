// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.a;

import javax.sound.sampled.AudioFormat;
import com.screencastomatic.play.stream.e;
import com.screencastomatic.play.stream.r;

class p implements Runnable
{
    private int b;
    final /* synthetic */ n a;
    
    private p(final n a) {
        this.a = a;
        this.b = -1;
    }
    
    public void run() {
        while (this.a.e == Thread.currentThread()) {
            try {
                final int d = this.a.j.d();
                if (this.b != d) {
                    this.a.c.a(d);
                    this.b = d;
                }
                Thread.sleep(1000L);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
