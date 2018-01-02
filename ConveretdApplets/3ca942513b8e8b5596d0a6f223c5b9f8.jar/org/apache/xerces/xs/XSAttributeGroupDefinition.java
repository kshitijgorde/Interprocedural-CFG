// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xs;

public interface XSAttributeGroupDefinition extends XSObject
{
    XSObjectList getAttributeUses();
    
    XSWildcard getAttributeWildcard();
    
    XSAnnotation getAnnotation();
}
