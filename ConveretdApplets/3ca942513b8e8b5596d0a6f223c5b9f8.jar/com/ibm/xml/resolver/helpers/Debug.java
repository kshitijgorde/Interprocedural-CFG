// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.resolver.helpers;

public class Debug
{
    protected int debug;
    
    public Debug() {
        this.debug = 0;
    }
    
    public void setDebug(final int debug) {
        this.debug = debug;
    }
    
    public int getDebug() {
        return this.debug;
    }
    
    public void message(final int n, final String s) {
        if (this.debug >= n) {
            System.out.println(s);
        }
    }
    
    public void message(final int n, final String s, final String s2) {
        if (this.debug >= n) {
            System.out.println(s + ": " + s2);
        }
    }
    
    public void message(final int n, final String s, final String s2, final String s3) {
        if (this.debug >= n) {
            System.out.println(s + ": " + s2);
            System.out.println("\t" + s3);
        }
    }
}
