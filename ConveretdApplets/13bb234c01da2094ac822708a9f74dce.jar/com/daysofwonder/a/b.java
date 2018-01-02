// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.a;

public class b
{
    private static final String[] b;
    public static final b a;
    private int c;
    private String d;
    
    public b(final int c, final String d) {
        this.c = 0;
        this.c = c;
        this.d = d;
    }
    
    public b(final int n) {
        this(n, null);
    }
    
    public b() {
        this(0);
    }
    
    public int a() {
        return this.c;
    }
    
    public String b() {
        return this.d;
    }
    
    public String toString() {
        return com.daysofwonder.a.b.b[this.c] + ((this.d != null) ? (": " + this.d) : "");
    }
    
    static {
        b = new String[] { "status.unknown", "status.disconnected", "status.lobby", "status.playing", "status.open", "status.playing_other", "status.browsing", "status.lurking" };
        a = new b();
    }
}
