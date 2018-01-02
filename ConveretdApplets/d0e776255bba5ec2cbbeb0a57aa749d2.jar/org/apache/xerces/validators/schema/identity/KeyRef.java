// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.schema.identity;

public class KeyRef extends IdentityConstraint
{
    protected String fReferName;
    
    public KeyRef(final String s, final String fReferName, final String s2) {
        super(s, s2);
        this.fReferName = fReferName;
    }
    
    public short getType() {
        return 2;
    }
    
    public String getReferName() {
        return this.fReferName;
    }
}
