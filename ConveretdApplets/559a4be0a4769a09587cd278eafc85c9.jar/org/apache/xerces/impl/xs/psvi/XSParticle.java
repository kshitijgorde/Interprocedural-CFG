// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.psvi;

public interface XSParticle extends XSObject
{
    int getMinOccurs();
    
    boolean getIsMaxOccursUnbounded();
    
    int getMaxOccurs();
    
    XSTerm getTerm();
}
