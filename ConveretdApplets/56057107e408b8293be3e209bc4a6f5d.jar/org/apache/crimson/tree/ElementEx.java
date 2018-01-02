// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import org.w3c.dom.Element;

public interface ElementEx extends Element, NodeEx
{
    String getIdAttributeName();
    
    Object getUserObject();
    
    void setUserObject(final Object p0);
    
    void trimToSize();
}
