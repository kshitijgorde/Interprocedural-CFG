// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.asn1;

import java.io.IOException;

public abstract class DERObject extends ASN1Encodable implements DERTags
{
    public DERObject toASN1Object() {
        return this;
    }
    
    public abstract int hashCode();
    
    public abstract boolean equals(final Object p0);
    
    abstract void encode(final DEROutputStream p0) throws IOException;
}
