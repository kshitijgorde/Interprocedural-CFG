// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.InputStream;

public class DEROctetStringParser implements ASN1OctetStringParser
{
    private DefiniteLengthInputStream stream;
    
    DEROctetStringParser(final DefiniteLengthInputStream stream) {
        this.stream = stream;
    }
    
    public InputStream getOctetStream() {
        return this.stream;
    }
    
    public DERObject getDERObject() {
        try {
            return new DEROctetString(this.stream.toByteArray());
        }
        catch (IOException ex) {
            throw new IllegalStateException("IOException converting stream to byte array: " + ex.getMessage());
        }
    }
}
