// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.identity;

public interface FieldActivator
{
    void startValueScopeFor(final IdentityConstraint p0, final int p1);
    
    XPathMatcher activateField(final Field p0, final int p1);
    
    void setMayMatch(final Field p0, final Boolean p1);
    
    Boolean mayMatch(final Field p0);
    
    void endValueScopeFor(final IdentityConstraint p0, final int p1);
}
