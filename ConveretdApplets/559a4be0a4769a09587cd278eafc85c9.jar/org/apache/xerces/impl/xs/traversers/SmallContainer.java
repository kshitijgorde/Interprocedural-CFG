// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

class SmallContainer extends Container
{
    String[] keys;
    
    SmallContainer(final int size) {
        this.keys = new String[size];
        super.values = new OneAttr[size];
    }
    
    void put(final String key, final OneAttr value) {
        this.keys[super.pos] = key;
        super.values[super.pos++] = value;
    }
    
    OneAttr get(final String key) {
        for (int i = 0; i < super.pos; ++i) {
            if (this.keys[i].equals(key)) {
                return super.values[i];
            }
        }
        return null;
    }
}
