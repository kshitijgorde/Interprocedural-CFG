// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

class V implements Runnable
{
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ ap c;
    
    V(final ap c, final String a, final String b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    public void run() {
        this.c.b(this.a, this.b);
    }
}
