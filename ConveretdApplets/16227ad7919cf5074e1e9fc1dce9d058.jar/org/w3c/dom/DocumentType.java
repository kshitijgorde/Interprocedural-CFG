// 
// Decompiled by Procyon v0.5.30
// 

package org.w3c.dom;

public interface DocumentType extends Node
{
    String getName();
    
    NamedNodeMap getEntities();
    
    NamedNodeMap getNotations();
}
