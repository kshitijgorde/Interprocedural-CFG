// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import java.util.Iterator;
import org.jdom.filter.Filter;
import java.util.List;
import java.io.Serializable;

public interface Parent extends Cloneable, Serializable
{
    int getContentSize();
    
    int indexOf(final Content p0);
    
    List cloneContent();
    
    Content getContent(final int p0);
    
    List getContent();
    
    List getContent(final Filter p0);
    
    List removeContent();
    
    List removeContent(final Filter p0);
    
    boolean removeContent(final Content p0);
    
    Content removeContent(final int p0);
    
    Object clone();
    
    Iterator getDescendants();
    
    Iterator getDescendants(final Filter p0);
    
    Parent getParent();
    
    Document getDocument();
}
