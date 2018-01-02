// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

final class dj implements Runnable
{
    final /* synthetic */ bt a;
    private final /* synthetic */ double b;
    private final /* synthetic */ double c;
    private final /* synthetic */ double d;
    
    dj(final bt a, final double b, final double c, final double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public void run() {
        bt.c(this.a).c(this.b);
        bt.c(this.a).i(this.c);
        bt.c(this.a).f(this.d);
    }
}
