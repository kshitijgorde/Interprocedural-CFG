// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

class SmallContainer extends Container
{
    String[] keys;
    
    SmallContainer(final int n) {
        this.keys = new String[n];
        super.values = new OneAttr[n];
    }
    
    void put(final String s, final OneAttr oneAttr) {
        this.keys[super.pos] = s;
        super.values[super.pos++] = oneAttr;
    }
    
    OneAttr get(final String s) {
        for (int i = 0; i < super.pos; ++i) {
            if (this.keys[i].equals(s)) {
                return super.values[i];
            }
        }
        return null;
    }
}
