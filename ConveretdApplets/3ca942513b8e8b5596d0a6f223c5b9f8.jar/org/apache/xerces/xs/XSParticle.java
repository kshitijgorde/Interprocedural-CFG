// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xs;

public interface XSParticle extends XSObject
{
    int getMinOccurs();
    
    int getMaxOccurs();
    
    boolean getMaxOccursUnbounded();
    
    XSTerm getTerm();
}
