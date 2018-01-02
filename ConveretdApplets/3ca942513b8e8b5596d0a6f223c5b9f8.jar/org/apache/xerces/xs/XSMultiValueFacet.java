// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xs;

public interface XSMultiValueFacet extends XSObject
{
    short getFacetKind();
    
    StringList getLexicalFacetValues();
    
    XSObjectList getAnnotations();
}
