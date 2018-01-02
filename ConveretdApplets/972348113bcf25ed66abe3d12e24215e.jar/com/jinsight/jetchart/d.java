// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

class d
{
    int a;
    int b;
    Object c;
    d d;
    
    protected Object clone() {
        final d d = new d();
        d.a = this.a;
        d.b = this.b;
        d.c = this.c;
        d.d = ((this.d != null) ? ((d)this.d.clone()) : null);
        return d;
    }
}
