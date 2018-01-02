// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.asn1;

import java.io.IOException;

public class DERSetParser implements ASN1SetParser
{
    private ASN1StreamParser _parser;
    
    DERSetParser(final ASN1StreamParser parser) {
        this._parser = parser;
    }
    
    public DEREncodable readObject() throws IOException {
        return this._parser.readObject();
    }
    
    public DERObject getDERObject() {
        try {
            return new DERSet(this._parser.readVector(), false);
        }
        catch (IOException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }
}
