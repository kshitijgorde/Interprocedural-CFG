// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

final class dm implements Runnable
{
    final /* synthetic */ bu a;
    private final /* synthetic */ double b;
    
    dm(final bu a, final double b) {
        this.a = a;
        this.b = b;
    }
    
    public void run() {
        bu.c(this.a).f(this.b);
    }
}
