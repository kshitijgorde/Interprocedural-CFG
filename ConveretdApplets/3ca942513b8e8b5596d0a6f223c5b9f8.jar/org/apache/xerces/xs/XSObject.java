// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xs;

public interface XSObject
{
    short getType();
    
    String getName();
    
    String getNamespace();
    
    XSNamespaceItem getNamespaceItem();
}
