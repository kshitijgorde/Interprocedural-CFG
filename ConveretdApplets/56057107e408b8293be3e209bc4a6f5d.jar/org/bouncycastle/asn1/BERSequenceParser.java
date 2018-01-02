// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.asn1;

import java.io.IOException;

public class BERSequenceParser implements ASN1SequenceParser
{
    private ASN1StreamParser _parser;
    
    BERSequenceParser(final ASN1StreamParser parser) {
        this._parser = parser;
    }
    
    public DEREncodable readObject() throws IOException {
        return this._parser.readObject();
    }
    
    public DERObject getDERObject() {
        try {
            return new BERSequence(this._parser.readVector());
        }
        catch (IOException ex) {
            throw new IllegalStateException(ex.getMessage());
        }
    }
}
