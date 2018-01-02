// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import java.util.Hashtable;

class LargeContainer extends Container
{
    Hashtable items;
    
    LargeContainer(final int size) {
        this.items = new Hashtable(size * 2 + 1);
        super.values = new OneAttr[size];
    }
    
    void put(final String key, final OneAttr value) {
        this.items.put(key, value);
        super.values[super.pos++] = value;
    }
    
    OneAttr get(final String key) {
        final OneAttr ret = this.items.get(key);
        return ret;
    }
}
