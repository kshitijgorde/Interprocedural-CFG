// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

final class dk implements Runnable
{
    final /* synthetic */ bt a;
    private final /* synthetic */ double b;
    
    dk(final bt a, final double b) {
        this.a = a;
        this.b = b;
    }
    
    public void run() {
        bt.c(this.a).i(this.b);
    }
}
