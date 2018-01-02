// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.event.MouseEvent;
import com.daysofwonder.b.a;

public class av implements Runnable
{
    final /* synthetic */ ap a;
    
    public av(final ap a) {
        this.a = a;
    }
    
    public void run() {
        int n = 0;
        int n2 = 0;
        while (this.a.H()) {
            this.a.aN.d();
            synchronized (this.a) {
                if (n2++ % 10 == 0) {
                    this.a.a(null, n++);
                }
                if (this.a.aa != null || this.a.an == null || !this.a.an.c_() || this.a.aK++ <= this.a.aO) {
                    continue;
                }
                final z q = this.a.Q();
                if (this.a.aL) {
                    this.a.am.a(q, null, this.a.ab, this.a.ad, this.a.ad, 0, this.a.an.a(this.a.ad, this.a.ak, this.a.aj, this.a.al));
                    this.a.aL = false;
                }
                if (this.a.aM) {
                    this.a.am.a(q, null, this.a.ab, this.a.ad, this.a.ad, 0, this.a.an.a(this.a.ad, this.a.ak, this.a.aj, this.a.al));
                    this.a.aM = false;
                }
                this.a.an.a(q, this.a.ak, this.a.al, this.a.aj, this.a.ad, 0, false, false);
                if (!this.a.aM) {
                    this.a.am.a(q, null, this.a.ab, this.a.ac, this.a.ad, 1, this.a.an.a(this.a.ad, this.a.ak, this.a.aj, this.a.al));
                    this.a.aM = true;
                }
                this.a.a(q);
                q.c();
                this.a.aO = 3;
            }
        }
    }
}
