// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ASN1StreamParser
{
    private InputStream _in;
    private int _limit;
    
    public ASN1StreamParser(final InputStream inputStream) {
        this(inputStream, Integer.MAX_VALUE);
    }
    
    public ASN1StreamParser(final InputStream in, final int limit) {
        this._in = in;
        this._limit = limit;
    }
    
    public ASN1StreamParser(final byte[] array) {
        this(new ByteArrayInputStream(array), array.length);
    }
    
    public DEREncodable readObject() throws IOException {
        final int read = this._in.read();
        if (read == -1) {
            return null;
        }
        this.set00Check(false);
        final int tagNumber = ASN1InputStream.readTagNumber(this._in, read);
        final boolean b = (read & 0x20) != 0x0;
        final int length = ASN1InputStream.readLength(this._in, this._limit);
        if (length < 0) {
            if (!b) {
                throw new IOException("indefinite length primitive encoding encountered");
            }
            final IndefiniteLengthInputStream indefiniteLengthInputStream = new IndefiniteLengthInputStream(this._in);
            if ((read & 0x80) != 0x0) {
                return new BERTaggedObjectParser(read, tagNumber, indefiniteLengthInputStream);
            }
            final ASN1StreamParser asn1StreamParser = new ASN1StreamParser(indefiniteLengthInputStream);
            switch (tagNumber) {
                case 4: {
                    return new BEROctetStringParser(asn1StreamParser);
                }
                case 16: {
                    return new BERSequenceParser(asn1StreamParser);
                }
                case 17: {
                    return new BERSetParser(asn1StreamParser);
                }
                default: {
                    throw new IOException("unknown BER object encountered");
                }
            }
        }
        else {
            final DefiniteLengthInputStream definiteLengthInputStream = new DefiniteLengthInputStream(this._in, length);
            if ((read & 0x40) != 0x0) {
                return new DERApplicationSpecific(b, tagNumber, definiteLengthInputStream.toByteArray());
            }
            if ((read & 0x80) != 0x0) {
                return new BERTaggedObjectParser(read, tagNumber, definiteLengthInputStream);
            }
            if (b) {
                switch (tagNumber) {
                    case 4: {
                        return new BEROctetStringParser(new ASN1StreamParser(definiteLengthInputStream));
                    }
                    case 16: {
                        return new DERSequenceParser(new ASN1StreamParser(definiteLengthInputStream));
                    }
                    case 17: {
                        return new DERSetParser(new ASN1StreamParser(definiteLengthInputStream));
                    }
                    default: {
                        return new DERUnknownTag(true, tagNumber, definiteLengthInputStream.toByteArray());
                    }
                }
            }
            else {
                switch (tagNumber) {
                    case 4: {
                        return new DEROctetStringParser(definiteLengthInputStream);
                    }
                    default: {
                        return ASN1InputStream.createPrimitiveDERObject(tagNumber, definiteLengthInputStream.toByteArray());
                    }
                }
            }
        }
    }
    
    private void set00Check(final boolean eofOn00) {
        if (this._in instanceof IndefiniteLengthInputStream) {
            ((IndefiniteLengthInputStream)this._in).setEofOn00(eofOn00);
        }
    }
    
    ASN1EncodableVector readVector() throws IOException {
        final ASN1EncodableVector asn1EncodableVector = new ASN1EncodableVector();
        DEREncodable object;
        while ((object = this.readObject()) != null) {
            asn1EncodableVector.add(object.getDERObject());
        }
        return asn1EncodableVector;
    }
}
