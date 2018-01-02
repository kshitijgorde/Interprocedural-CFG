// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.asn1;

import java.io.IOException;

public class DERBMPString extends ASN1Object implements DERString
{
    String string;
    
    public static DERBMPString getInstance(final Object o) {
        if (o == null || o instanceof DERBMPString) {
            return (DERBMPString)o;
        }
        if (o instanceof ASN1OctetString) {
            return new DERBMPString(((ASN1OctetString)o).getOctets());
        }
        if (o instanceof ASN1TaggedObject) {
            return getInstance(((ASN1TaggedObject)o).getObject());
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + o.getClass().getName());
    }
    
    public static DERBMPString getInstance(final ASN1TaggedObject asn1TaggedObject, final boolean b) {
        return getInstance(asn1TaggedObject.getObject());
    }
    
    public DERBMPString(final byte[] array) {
        final char[] array2 = new char[array.length / 2];
        for (int i = 0; i != array2.length; ++i) {
            array2[i] = (char)(array[2 * i] << 8 | (array[2 * i + 1] & 0xFF));
        }
        this.string = new String(array2);
    }
    
    public DERBMPString(final String string) {
        this.string = string;
    }
    
    public String getString() {
        return this.string;
    }
    
    public String toString() {
        return this.string;
    }
    
    public int hashCode() {
        return this.getString().hashCode();
    }
    
    protected boolean asn1Equals(final DERObject derObject) {
        return derObject instanceof DERBMPString && this.getString().equals(((DERBMPString)derObject).getString());
    }
    
    void encode(final DEROutputStream derOutputStream) throws IOException {
        final char[] charArray = this.string.toCharArray();
        final byte[] array = new byte[charArray.length * 2];
        for (int i = 0; i != charArray.length; ++i) {
            array[2 * i] = (byte)(charArray[i] >> 8);
            array[2 * i + 1] = (byte)charArray[i];
        }
        derOutputStream.writeEncoded(30, array);
    }
}
