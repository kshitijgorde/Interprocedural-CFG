// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.asn1;

import java.io.IOException;

public class DERNumericString extends ASN1Object implements DERString
{
    String string;
    
    public static DERNumericString getInstance(final Object o) {
        if (o == null || o instanceof DERNumericString) {
            return (DERNumericString)o;
        }
        if (o instanceof ASN1OctetString) {
            return new DERNumericString(((ASN1OctetString)o).getOctets());
        }
        if (o instanceof ASN1TaggedObject) {
            return getInstance(((ASN1TaggedObject)o).getObject());
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + o.getClass().getName());
    }
    
    public static DERNumericString getInstance(final ASN1TaggedObject asn1TaggedObject, final boolean b) {
        return getInstance(asn1TaggedObject.getObject());
    }
    
    public DERNumericString(final byte[] array) {
        final char[] array2 = new char[array.length];
        for (int i = 0; i != array2.length; ++i) {
            array2[i] = (char)(array[i] & 0xFF);
        }
        this.string = new String(array2);
    }
    
    public DERNumericString(final String s) {
        this(s, false);
    }
    
    public DERNumericString(final String string, final boolean b) {
        if (b && !isNumericString(string)) {
            throw new IllegalArgumentException("string contains illegal characters");
        }
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
        derOutputStream.writeEncoded(18, this.getOctets());
    }
    
    public int hashCode() {
        return this.getString().hashCode();
    }
    
    boolean asn1Equals(final DERObject derObject) {
        return derObject instanceof DERNumericString && this.getString().equals(((DERNumericString)derObject).getString());
    }
    
    public static boolean isNumericString(final String s) {
        for (int i = s.length() - 1; i >= 0; --i) {
            final char char1 = s.charAt(i);
            if (char1 > '\u007f') {
                return false;
            }
            if (('0' > char1 || char1 > '9') && char1 != ' ') {
                return false;
            }
        }
        return true;
    }
}
