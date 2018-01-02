// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.d;

public class e
{
    String b;
    String c;
    
    public e() {
        this.b = "";
        this.c = "";
    }
    
    public String toString() {
        if (this.b.equals("")) {
            return this.c;
        }
        return this.b + "=" + '\"' + this.c + '\"';
    }
    
    public String a(final int n) {
        return this.toString();
    }
}
