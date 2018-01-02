// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

final class dl implements Runnable
{
    final /* synthetic */ bu a;
    private final /* synthetic */ double b;
    
    dl(final bu a, final double b) {
        this.a = a;
        this.b = b;
    }
    
    public void run() {
        bu.c(this.a).i(this.b);
    }
}
