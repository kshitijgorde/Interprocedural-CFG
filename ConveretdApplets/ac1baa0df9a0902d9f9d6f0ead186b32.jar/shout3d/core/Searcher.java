// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public interface Searcher
{
    Node[] searchFirst(final Node p0);
    
    void setNode(final Node p0);
    
    void setDefName(final String p0);
    
    Node[][] searchAll(final Node p0);
    
    void setType(final String p0);
}
