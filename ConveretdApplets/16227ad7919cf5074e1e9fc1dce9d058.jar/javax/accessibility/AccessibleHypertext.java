// 
// Decompiled by Procyon v0.5.30
// 

package javax.accessibility;

public interface AccessibleHypertext extends AccessibleText
{
    AccessibleHyperlink getLink(final int p0);
    
    int getLinkCount();
    
    int getLinkIndex(final int p0);
}
