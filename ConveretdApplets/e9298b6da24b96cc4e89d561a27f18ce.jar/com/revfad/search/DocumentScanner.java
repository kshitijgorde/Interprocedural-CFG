// 
// Decompiled by Procyon v0.5.30
// 

package com.revfad.search;

public interface DocumentScanner
{
    char[] buffer(final Document p0);
    
    boolean shouldContinue(final Document p0);
    
    void scan(final char[] p0, final int p1, final Document p2);
}
