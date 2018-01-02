// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.asn1.sec;

import org.bouncycastle.asn1.DERObject;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DEREncodableVector;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DEREncodable;
import org.bouncycastle.asn1.DERInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Encodable;

public class ECPrivateKeyStructure extends ASN1Encodable
{
    private ASN1Sequence seq;
    
    public ECPrivateKeyStructure(final ASN1Sequence seq) {
        this.seq = seq;
    }
    
    public ECPrivateKeyStructure(final BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray[0] == 0) {
            final byte[] array = new byte[byteArray.length - 1];
            System.arraycopy(byteArray, 1, array, 0, array.length);
            byteArray = array;
        }
        final ASN1EncodableVector asn1EncodableVector = new ASN1EncodableVector();
        asn1EncodableVector.add(new DERInteger(1));
        asn1EncodableVector.add(new DEROctetString(byteArray));
        this.seq = new DERSequence(asn1EncodableVector);
    }
    
    public BigInteger getKey() {
        return new BigInteger(1, ((ASN1OctetString)this.seq.getObjectAt(1)).getOctets());
    }
    
    public DERBitString getPublicKey() {
        return (DERBitString)this.getObjectInTag(1);
    }
    
    public ASN1Object getParameters() {
        return this.getObjectInTag(0);
    }
    
    private ASN1Object getObjectInTag(final int n) {
        final Enumeration objects = this.seq.getObjects();
        while (objects.hasMoreElements()) {
            final DEREncodable derEncodable = objects.nextElement();
            if (derEncodable instanceof ASN1TaggedObject) {
                final ASN1TaggedObject asn1TaggedObject = (ASN1TaggedObject)derEncodable;
                if (asn1TaggedObject.getTagNo() == n) {
                    return (ASN1Object)asn1TaggedObject.getObject().getDERObject();
                }
                continue;
            }
        }
        return null;
    }
    
    public DERObject toASN1Object() {
        return this.seq;
    }
}
