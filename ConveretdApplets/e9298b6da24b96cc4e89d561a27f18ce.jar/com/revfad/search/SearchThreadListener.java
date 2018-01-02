// 
// Decompiled by Procyon v0.5.30
// 

package com.revfad.search;

public interface SearchThreadListener
{
    void searching(final SearchThread p0, final Document p1);
    
    void foundMatch(final SearchThread p0, final Match p1);
    
    void finishedSearching(final SearchThread p0, final Document p1);
    
    void foundLink(final SearchThread p0, final Document p1, final String p2, final String p3);
}
