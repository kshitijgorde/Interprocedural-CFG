// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

final class dj implements Runnable
{
    final /* synthetic */ bt a;
    private final /* synthetic */ double b;
    
    dj(final bt a, final double b) {
        this.a = a;
        this.b = b;
    }
    
    public void run() {
        bt.c(this.a).c(this.b);
    }
}
