// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm;

public interface DTMAxisIterator extends Cloneable
{
    public static final int END = -1;
    
    int next();
    
    DTMAxisIterator reset();
    
    int getLast();
    
    int getPosition();
    
    void setMark();
    
    void gotoMark();
    
    DTMAxisIterator setStartNode(final int p0);
    
    int getStartNode();
    
    boolean isReverse();
    
    DTMAxisIterator cloneIterator();
    
    void setRestartable(final boolean p0);
    
    int getNodeByPosition(final int p0);
}
