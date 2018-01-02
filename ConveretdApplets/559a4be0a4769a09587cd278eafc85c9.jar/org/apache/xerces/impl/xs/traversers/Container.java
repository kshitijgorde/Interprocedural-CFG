// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

abstract class Container
{
    static final int THRESHOLD = 5;
    OneAttr[] values;
    int pos;
    
    Container() {
        this.pos = 0;
    }
    
    static Container getContainer(final int size) {
        if (size > 5) {
            return new LargeContainer(size);
        }
        return new SmallContainer(size);
    }
    
    abstract void put(final String p0, final OneAttr p1);
    
    abstract OneAttr get(final String p0);
}
