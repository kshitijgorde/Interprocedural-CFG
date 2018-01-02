// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.util.Hashtable;

class LCount
{
    static Hashtable lCounts;
    public int captures;
    public int bubbles;
    public int defaults;
    
    static LCount lookup(final String s) {
        LCount lCount = LCount.lCounts.get(s);
        if (lCount == null) {
            LCount.lCounts.put(s, lCount = new LCount());
        }
        return lCount;
    }
    
    static {
        LCount.lCounts = new Hashtable();
    }
}
