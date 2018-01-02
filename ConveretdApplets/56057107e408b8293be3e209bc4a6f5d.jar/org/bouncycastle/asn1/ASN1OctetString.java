// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.asn1;

import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.Arrays;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.util.Enumeration;
import java.util.Vector;

public abstract class ASN1OctetString extends ASN1Object implements ASN1OctetStringParser
{
    byte[] string;
    
    public static ASN1OctetString getInstance(final ASN1TaggedObject asn1TaggedObject, final boolean b) {
        return getInstance(asn1TaggedObject.getObject());
    }
    
    public static ASN1OctetString getInstance(final Object o) {
        if (o == null || o instanceof ASN1OctetString) {
            return (ASN1OctetString)o;
        }
        if (o instanceof ASN1TaggedObject) {
            return getInstance(((ASN1TaggedObject)o).getObject());
        }
        if (o instanceof ASN1Sequence) {
            final Vector<Object> vector = new Vector<Object>();
            final Enumeration objects = ((ASN1Sequence)o).getObjects();
            while (objects.hasMoreElements()) {
                vector.addElement(objects.nextElement());
            }
            return new BERConstructedOctetString(vector);
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + o.getClass().getName());
    }
    
    public ASN1OctetString(final byte[] string) {
        if (string == null) {
            throw new NullPointerException("string cannot be null");
        }
        this.string = string;
    }
    
    public ASN1OctetString(final DEREncodable derEncodable) {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final DEROutputStream derOutputStream = new DEROutputStream(byteArrayOutputStream);
            derOutputStream.writeObject(derEncodable);
            derOutputStream.close();
            this.string = byteArrayOutputStream.toByteArray();
        }
        catch (IOException ex) {
            throw new IllegalArgumentException("Error processing object : " + ex.toString());
        }
    }
    
    public InputStream getOctetStream() {
        return new ByteArrayInputStream(this.string);
    }
    
    public ASN1OctetStringParser parser() {
        return this;
    }
    
    public byte[] getOctets() {
        return this.string;
    }
    
    public int hashCode() {
        return Arrays.hashCode(this.getOctets());
    }
    
    boolean asn1Equals(final DERObject derObject) {
        if (!(derObject instanceof ASN1OctetString)) {
            return false;
        }
        final byte[] string = ((ASN1OctetString)derObject).string;
        final byte[] string2 = this.string;
        if (string.length != string2.length) {
            return false;
        }
        for (int i = 0; i != string.length; ++i) {
            if (string[i] != string2[i]) {
                return false;
            }
        }
        return true;
    }
    
    abstract void encode(final DEROutputStream p0) throws IOException;
    
    public String toString() {
        return "#" + new String(Hex.encode(this.string));
    }
}
