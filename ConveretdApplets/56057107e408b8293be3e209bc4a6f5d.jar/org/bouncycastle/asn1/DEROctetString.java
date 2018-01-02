// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.asn1;

import java.io.IOException;

public class DEROctetString extends ASN1OctetString
{
    public DEROctetString(final byte[] array) {
        super(array);
    }
    
    public DEROctetString(final DEREncodable derEncodable) {
        super(derEncodable);
    }
    
    void encode(final DEROutputStream derOutputStream) throws IOException {
        derOutputStream.writeEncoded(4, super.string);
    }
}
