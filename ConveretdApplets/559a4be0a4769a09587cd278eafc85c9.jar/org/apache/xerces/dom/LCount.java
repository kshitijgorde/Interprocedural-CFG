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
    
    LCount() {
        this.captures = 0;
        this.bubbles = 0;
        this.defaults = 0;
    }
    
    static LCount lookup(final String evtName) {
        LCount lc = LCount.lCounts.get(evtName);
        if (lc == null) {
            LCount.lCounts.put(evtName, lc = new LCount());
        }
        return lc;
    }
    
    static {
        LCount.lCounts = new Hashtable();
    }
}
