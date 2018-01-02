// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;

public class BERTaggedObject extends DERTaggedObject
{
    public BERTaggedObject(final int n, final DEREncodable derEncodable) {
        super(n, derEncodable);
    }
    
    public BERTaggedObject(final boolean b, final int n, final DEREncodable derEncodable) {
        super(b, n, derEncodable);
    }
    
    public BERTaggedObject(final int n) {
        super(false, n, new BERSequence());
    }
    
    void encode(final DEROutputStream derOutputStream) throws IOException {
        if (derOutputStream instanceof ASN1OutputStream || derOutputStream instanceof BEROutputStream) {
            derOutputStream.writeTag(160, super.tagNo);
            derOutputStream.write(128);
            if (!super.empty) {
                if (!super.explicit) {
                    Enumeration enumeration;
                    if (super.obj instanceof ASN1OctetString) {
                        if (super.obj instanceof BERConstructedOctetString) {
                            enumeration = ((BERConstructedOctetString)super.obj).getObjects();
                        }
                        else {
                            enumeration = new BERConstructedOctetString(((ASN1OctetString)super.obj).getOctets()).getObjects();
                        }
                    }
                    else if (super.obj instanceof ASN1Sequence) {
                        enumeration = ((ASN1Sequence)super.obj).getObjects();
                    }
                    else {
                        if (!(super.obj instanceof ASN1Set)) {
                            throw new RuntimeException("not implemented: " + super.obj.getClass().getName());
                        }
                        enumeration = ((ASN1Set)super.obj).getObjects();
                    }
                    while (enumeration.hasMoreElements()) {
                        derOutputStream.writeObject(enumeration.nextElement());
                    }
                }
                else {
                    derOutputStream.writeObject(super.obj);
                }
            }
            derOutputStream.write(0);
            derOutputStream.write(0);
        }
        else {
            super.encode(derOutputStream);
        }
    }
}
