// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

final class k implements Runnable
{
    private final l a;
    private final int b;
    
    k(final l a, final int b) {
        this.a = a;
        this.b = b;
    }
    
    public final void run() {
        l.a(this.a).c(this.b);
    }
}
