// 
// Decompiled by Procyon v0.5.30
// 

package com.kaon.meson;

public class StringPair
{
    private static boolean a;
    public String b;
    public String c;
    public Object d;
    private int e;
    
    public StringPair(final String b, final String c, final int e) {
        this.b = b;
        this.c = c;
        this.e = e;
    }
    
    public static int hashCode(final String s, final String s2) {
        if (StringPair.a) {
            int n = 0;
            for (int length = s.length(), i = 0; i < length; ++i) {
                n = 31 * n + s.charAt(i);
            }
            for (int length2 = s2.length(), j = 0; j < length2; ++j) {
                n = 31 * n + s2.charAt(j);
            }
            return n;
        }
        return (s + s2).hashCode();
    }
    
    public int hashCode() {
        return this.e;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o instanceof StringPair) {
            final StringPair stringPair = (StringPair)o;
            return stringPair.e == this.e && this.b.equals(stringPair.b) && this.c.equals(stringPair.c);
        }
        if (o instanceof String) {
            final String s = (String)o;
            return this.b.length() + this.c.length() == s.length() && s.startsWith(this.b) && s.endsWith(this.c);
        }
        return (this.b + this.c).equals(o);
    }
    
    public boolean equals(final String s, final String s2) {
        return this.b.equals(s) && this.c.equals(s2);
    }
    
    public String toString() {
        return this.b + this.c;
    }
    
    public String getFirst() {
        return this.b;
    }
    
    public String getLast() {
        return this.c;
    }
    
    static {
        StringPair.a = true;
        if (StringPair.a) {
            final String s = "Dont' use fast hashCode() if hashCode() algorithm ";
            final String s2 = "of the underlying JVM is different";
            StringPair.a = ((s + s2).hashCode() == hashCode(s, s2));
        }
    }
}
