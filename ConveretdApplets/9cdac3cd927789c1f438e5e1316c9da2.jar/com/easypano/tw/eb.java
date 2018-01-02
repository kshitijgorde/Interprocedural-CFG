// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

final class eb extends Thread
{
    final /* synthetic */ bx a;
    
    eb(final bx a) {
        this.a = a;
    }
    
    public void run() {
        final boolean q = g.q;
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
