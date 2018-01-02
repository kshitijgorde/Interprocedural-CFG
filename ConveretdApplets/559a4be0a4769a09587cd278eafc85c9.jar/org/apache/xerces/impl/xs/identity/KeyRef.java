// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.identity;

import org.apache.xerces.impl.xs.psvi.XSIDConstraintDefinition;

public class KeyRef extends IdentityConstraint
{
    protected UniqueOrKey fKey;
    
    public KeyRef(final String namespace, final String identityConstraintName, final String elemName, final UniqueOrKey key) {
        super(namespace, identityConstraintName, elemName);
        this.fKey = key;
        super.type = 2;
    }
    
    public UniqueOrKey getKey() {
        return this.fKey;
    }
    
    public XSIDConstraintDefinition getRefKey() {
        return this.fKey;
    }
}
