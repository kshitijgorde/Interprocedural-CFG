// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.psvi;

public interface XSAttributeGroupDefinition extends XSObject
{
    XSObjectList getAttributeUses();
    
    XSWildcard getAttributeWildcard();
    
    XSAnnotation getAnnotation();
}
