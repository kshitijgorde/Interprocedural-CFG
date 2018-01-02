// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

final class dl implements Runnable
{
    final /* synthetic */ bt a;
    private final /* synthetic */ double b;
    
    dl(final bt a, final double b) {
        this.a = a;
        this.b = b;
    }
    
    public void run() {
        bt.c(this.a).i(this.b);
    }
}
