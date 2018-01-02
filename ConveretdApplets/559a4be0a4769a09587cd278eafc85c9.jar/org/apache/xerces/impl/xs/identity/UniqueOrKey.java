// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.identity;

public class UniqueOrKey extends IdentityConstraint
{
    public UniqueOrKey(final String namespace, final String identityConstraintName, final String elemName, final short type) {
        super(namespace, identityConstraintName, elemName);
        super.type = type;
    }
}
