// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.identity;

import org.apache.xerces.xs.XSIDCDefinition;

public class KeyRef extends IdentityConstraint
{
    protected UniqueOrKey fKey;
    
    public KeyRef(final String s, final String s2, final String s3, final UniqueOrKey fKey) {
        super(s, s2, s3);
        this.fKey = fKey;
        super.type = 2;
    }
    
    public UniqueOrKey getKey() {
        return this.fKey;
    }
    
    public XSIDCDefinition getRefKey() {
        return this.fKey;
    }
}
