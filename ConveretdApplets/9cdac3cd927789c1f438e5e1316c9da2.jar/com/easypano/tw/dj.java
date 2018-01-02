// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

final class dj implements Runnable
{
    final /* synthetic */ bu a;
    private final /* synthetic */ double b;
    private final /* synthetic */ double c;
    private final /* synthetic */ double d;
    
    dj(final bu a, final double b, final double c, final double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public void run() {
        bu.c(this.a).c(this.b);
        bu.c(this.a).i(this.c);
        bu.c(this.a).f(this.d);
    }
}
