// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import org.w3c.dom.Node;

public interface NodeEx extends Node, XmlWritable
{
    String getInheritedAttribute(final String p0);
    
    String getLanguage();
    
    int getIndexOf(final Node p0);
    
    void setReadonly(final boolean p0);
    
    boolean isReadonly();
}
