// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play;

class b
{
    int a;
    int b;
    String c;
    String d;
    int e;
    
    public b(final int a, final String c, final String d) {
        this.a = a;
        int n = d.length() * 70 / 1000;
        if (n < 5) {
            n = 5;
        }
        this.b = a + n;
        this.c = c;
        this.d = d;
    }
}
