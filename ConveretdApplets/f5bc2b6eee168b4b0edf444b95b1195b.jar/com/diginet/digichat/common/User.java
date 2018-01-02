// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.common;

public class User extends DigiItem
{
    public int a;
    public int b;
    public boolean c;
    public boolean d;
    public String e;
    public String f;
    
    public final String toString() {
        return super.toString() + "ip=" + this.e;
    }
    
    public User(final int n, final String s) {
        super(n, s);
        this.a = -999;
        this.b = -999;
        this.c = false;
        this.d = false;
    }
}
