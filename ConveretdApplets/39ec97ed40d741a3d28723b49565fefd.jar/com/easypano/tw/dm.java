// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.util.Vector;

final class dm
{
    Vector a;
    int b;
    final /* synthetic */ bt c;
    
    dm(final bt c) {
        this.c = c;
        this.a = new Vector();
    }
    
    public void a(final Runnable runnable) {
        this.a.addElement(runnable);
    }
    
    public void a() {
        final boolean q = g.q;
        this.b = this.a.size();
        int n = 0;
        while (true) {
            while (true) {
                Label_0048: {
                    if (!q) {
                        break Label_0048;
                    }
                    this.a.elementAt(0).run();
                    this.a.removeElementAt(0);
                    ++n;
                }
                if (n < this.b) {
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
    
    public int b() {
        return this.a.size();
    }
}
