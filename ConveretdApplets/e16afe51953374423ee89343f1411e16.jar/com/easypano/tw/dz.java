// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

final class dz extends Thread
{
    final /* synthetic */ bw a;
    
    dz(final bw a) {
        this.a = a;
    }
    
    public void run() {
        final boolean q = h.q;
        final int size = this.a.a.size();
        int n = 0;
        while (true) {
            while (true) {
                Label_0051: {
                    if (!q) {
                        break Label_0051;
                    }
                    try {
                        ((ct)this.a.a.elementAt(n)).a();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    ++n;
                }
                if (n < size) {
                    continue;
                }
                break;
            }
            if (!q) {
                return;
            }
            continue;
        }
    }
}
