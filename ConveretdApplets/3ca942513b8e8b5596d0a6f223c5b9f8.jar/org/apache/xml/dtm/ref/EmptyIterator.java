// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm.ref;

import org.apache.xml.dtm.DTMAxisIterator;

public final class EmptyIterator implements DTMAxisIterator
{
    private static final EmptyIterator INSTANCE;
    
    public static DTMAxisIterator getInstance() {
        return EmptyIterator.INSTANCE;
    }
    
    public final int next() {
        return -1;
    }
    
    public final DTMAxisIterator reset() {
        return this;
    }
    
    public final int getLast() {
        return 0;
    }
    
    public final int getPosition() {
        return 1;
    }
    
    public final void setMark() {
    }
    
    public final void gotoMark() {
    }
    
    public final DTMAxisIterator setStartNode(final int node) {
        return this;
    }
    
    public final int getStartNode() {
        return -1;
    }
    
    public final boolean isReverse() {
        return false;
    }
    
    public final DTMAxisIterator cloneIterator() {
        return this;
    }
    
    public final void setRestartable(final boolean isRestartable) {
    }
    
    public final int getNodeByPosition(final int position) {
        return -1;
    }
    
    static {
        INSTANCE = new EmptyIterator();
    }
}
