// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.asn1;

import java.io.IOException;

public abstract class ASN1Null extends ASN1Object
{
    public int hashCode() {
        return -1;
    }
    
    boolean asn1Equals(final DERObject derObject) {
        return derObject instanceof ASN1Null;
    }
    
    abstract void encode(final DEROutputStream p0) throws IOException;
    
    public String toString() {
        return "NULL";
    }
}
