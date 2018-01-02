// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.asn1;

import java.io.IOException;
import org.bouncycastle.util.io.Streams;
import java.io.InputStream;

public class BEROctetStringParser implements ASN1OctetStringParser
{
    private ASN1StreamParser _parser;
    
    BEROctetStringParser(final ASN1StreamParser parser) {
        this._parser = parser;
    }
    
    protected BEROctetStringParser(final ASN1ObjectParser asn1ObjectParser) {
        this._parser = asn1ObjectParser._aIn;
    }
    
    public InputStream getOctetStream() {
        return new ConstructedOctetStream(this._parser);
    }
    
    public DERObject getDERObject() {
        try {
            return new BERConstructedOctetString(Streams.readAll(this.getOctetStream()));
        }
        catch (IOException ex) {
            throw new IllegalStateException("IOException converting stream to byte array: " + ex.getMessage());
        }
    }
}
