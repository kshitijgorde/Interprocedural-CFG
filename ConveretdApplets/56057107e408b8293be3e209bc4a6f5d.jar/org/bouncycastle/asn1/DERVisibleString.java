// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.asn1;

import java.io.IOException;

public class DERVisibleString extends ASN1Object implements DERString
{
    String string;
    
    public static DERVisibleString getInstance(final Object o) {
        if (o == null || o instanceof DERVisibleString) {
            return (DERVisibleString)o;
        }
        if (o instanceof ASN1OctetString) {
            return new DERVisibleString(((ASN1OctetString)o).getOctets());
        }
        if (o instanceof ASN1TaggedObject) {
            return getInstance(((ASN1TaggedObject)o).getObject());
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + o.getClass().getName());
    }
    
    public static DERVisibleString getInstance(final ASN1TaggedObject asn1TaggedObject, final boolean b) {
        return getInstance(asn1TaggedObject.getObject());
    }
    
    public DERVisibleString(final byte[] array) {
        final char[] array2 = new char[array.length];
        for (int i = 0; i != array2.length; ++i) {
            array2[i] = (char)(array[i] & 0xFF);
        }
        this.string = new String(array2);
    }
    
    public DERVisibleString(final String string) {
        this.string = string;
    }
    
    public String getString() {
        return this.string;
    }
    
    public String toString() {
        return this.string;
    }
    
    public byte[] getOctets() {
        final char[] charArray = this.string.toCharArray();
        final byte[] array = new byte[charArray.length];
        for (int i = 0; i != charArray.length; ++i) {
            array[i] = (byte)charArray[i];
        }
        return array;
    }
    
    void encode(final DEROutputStream derOutputStream) throws IOException {
        derOutputStream.writeEncoded(26, this.getOctets());
    }
    
    boolean asn1Equals(final DERObject derObject) {
        return derObject instanceof DERVisibleString && this.getString().equals(((DERVisibleString)derObject).getString());
    }
    
    public int hashCode() {
        return this.getString().hashCode();
    }
}
