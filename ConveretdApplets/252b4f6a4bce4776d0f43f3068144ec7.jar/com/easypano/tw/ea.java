// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

final class ea extends Thread
{
    final /* synthetic */ bw a;
    
    ea(final bw a) {
        this.a = a;
    }
    
    public void run() {
        final int q = h.q;
        final int size = this.a.a.size();
        int n = 0;
        while (true) {
            while (true) {
                Label_0051: {
                    if (q == 0) {
                        break Label_0051;
                    }
                    try {
                        ((cs)this.a.a.elementAt(n)).a();
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
            if (q == 0) {
                return;
            }
            continue;
        }
    }
}
