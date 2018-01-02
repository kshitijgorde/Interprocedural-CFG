// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.asn1;

import java.io.IOException;

public class DERT61String extends ASN1Object implements DERString
{
    String string;
    
    public static DERT61String getInstance(final Object o) {
        if (o == null || o instanceof DERT61String) {
            return (DERT61String)o;
        }
        if (o instanceof ASN1OctetString) {
            return new DERT61String(((ASN1OctetString)o).getOctets());
        }
        if (o instanceof ASN1TaggedObject) {
            return getInstance(((ASN1TaggedObject)o).getObject());
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + o.getClass().getName());
    }
    
    public static DERT61String getInstance(final ASN1TaggedObject asn1TaggedObject, final boolean b) {
        return getInstance(asn1TaggedObject.getObject());
    }
    
    public DERT61String(final byte[] array) {
        final char[] array2 = new char[array.length];
        for (int i = 0; i != array2.length; ++i) {
            array2[i] = (char)(array[i] & 0xFF);
        }
        this.string = new String(array2);
    }
    
    public DERT61String(final String string) {
        this.string = string;
    }
    
    public String getString() {
        return this.string;
    }
    
    public String toString() {
        return this.string;
    }
    
    void encode(final DEROutputStream derOutputStream) throws IOException {
        derOutputStream.writeEncoded(20, this.getOctets());
    }
    
    public byte[] getOctets() {
        final char[] charArray = this.string.toCharArray();
        final byte[] array = new byte[charArray.length];
        for (int i = 0; i != charArray.length; ++i) {
            array[i] = (byte)charArray[i];
        }
        return array;
    }
    
    boolean asn1Equals(final DERObject derObject) {
        return derObject instanceof DERT61String && this.getString().equals(((DERT61String)derObject).getString());
    }
    
    public int hashCode() {
        return this.getString().hashCode();
    }
}
