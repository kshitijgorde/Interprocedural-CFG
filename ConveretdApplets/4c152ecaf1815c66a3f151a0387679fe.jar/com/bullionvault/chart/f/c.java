// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.f;

import java.util.Date;

public final class c
{
    public float a;
    public float b;
    public float c;
    public boolean d;
    public Date e;
    public String f;
    
    public c() {
        this.d = true;
    }
    
    public final String toString() {
        return this.e + " = HLC(" + this.a + "," + this.b + "," + this.c + ")";
    }
}
