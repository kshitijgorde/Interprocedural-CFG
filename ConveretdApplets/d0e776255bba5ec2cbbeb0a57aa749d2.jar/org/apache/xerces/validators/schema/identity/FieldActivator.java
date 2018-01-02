// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.schema.identity;

public interface FieldActivator
{
    void startValueScopeFor(final IdentityConstraint p0) throws Exception;
    
    XPathMatcher activateField(final Field p0) throws Exception;
    
    void endValueScopeFor(final IdentityConstraint p0) throws Exception;
}
