// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.asn1;

import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

public abstract class ASN1Set extends ASN1Object
{
    protected Vector set;
    
    public static ASN1Set getInstance(final Object o) {
        if (o == null || o instanceof ASN1Set) {
            return (ASN1Set)o;
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + o.getClass().getName());
    }
    
    public static ASN1Set getInstance(final ASN1TaggedObject asn1TaggedObject, final boolean b) {
        if (b) {
            if (!asn1TaggedObject.isExplicit()) {
                throw new IllegalArgumentException("object implicit - explicit expected.");
            }
            return (ASN1Set)asn1TaggedObject.getObject();
        }
        else {
            if (asn1TaggedObject.isExplicit()) {
                return new DERSet(asn1TaggedObject.getObject());
            }
            if (asn1TaggedObject.getObject() instanceof ASN1Set) {
                return (ASN1Set)asn1TaggedObject.getObject();
            }
            final ASN1EncodableVector asn1EncodableVector = new ASN1EncodableVector();
            if (asn1TaggedObject.getObject() instanceof ASN1Sequence) {
                final Enumeration objects = ((ASN1Sequence)asn1TaggedObject.getObject()).getObjects();
                while (objects.hasMoreElements()) {
                    asn1EncodableVector.add(objects.nextElement());
                }
                return new DERSet(asn1EncodableVector, false);
            }
            throw new IllegalArgumentException("unknown object in getInstance: " + asn1TaggedObject.getClass().getName());
        }
    }
    
    public ASN1Set() {
        this.set = new Vector();
    }
    
    public Enumeration getObjects() {
        return this.set.elements();
    }
    
    public DEREncodable getObjectAt(final int n) {
        return this.set.elementAt(n);
    }
    
    public int size() {
        return this.set.size();
    }
    
    public ASN1SetParser parser() {
        return new ASN1SetParser() {
            private final int max = ASN1Set.this.size();
            private int index;
            
            public DEREncodable readObject() throws IOException {
                if (this.index == this.max) {
                    return null;
                }
                final DEREncodable object = ASN1Set.this.getObjectAt(this.index++);
                if (object instanceof ASN1Sequence) {
                    return ((ASN1Sequence)object).parser();
                }
                if (object instanceof ASN1Set) {
                    return ((ASN1Set)object).parser();
                }
                return object;
            }
            
            public DERObject getDERObject() {
                return ASN1Set.this;
            }
        };
    }
    
    public int hashCode() {
        final Enumeration objects = this.getObjects();
        int size = this.size();
        while (objects.hasMoreElements()) {
            final Object nextElement = objects.nextElement();
            size *= 17;
            if (nextElement != null) {
                size ^= nextElement.hashCode();
            }
        }
        return size;
    }
    
    boolean asn1Equals(final DERObject derObject) {
        if (!(derObject instanceof ASN1Set)) {
            return false;
        }
        final ASN1Set set = (ASN1Set)derObject;
        if (this.size() != set.size()) {
            return false;
        }
        final Enumeration objects = this.getObjects();
        final Enumeration objects2 = set.getObjects();
        while (objects.hasMoreElements()) {
            final DERObject derObject2 = objects.nextElement().getDERObject();
            final DERObject derObject3 = objects2.nextElement().getDERObject();
            if (derObject2 != derObject3) {
                if (derObject2 != null && derObject2.equals(derObject3)) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }
    
    private boolean lessThanOrEqual(final byte[] array, final byte[] array2) {
        if (array.length <= array2.length) {
            for (int i = 0; i != array.length; ++i) {
                final int n = array[i] & 0xFF;
                final int n2 = array2[i] & 0xFF;
                if (n2 > n) {
                    return true;
                }
                if (n > n2) {
                    return false;
                }
            }
            return true;
        }
        for (int j = 0; j != array2.length; ++j) {
            final int n3 = array[j] & 0xFF;
            final int n4 = array2[j] & 0xFF;
            if (n4 > n3) {
                return true;
            }
            if (n3 > n4) {
                return false;
            }
        }
        return false;
    }
    
    private byte[] getEncoded(final DEREncodable derEncodable) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final ASN1OutputStream asn1OutputStream = new ASN1OutputStream(byteArrayOutputStream);
        try {
            asn1OutputStream.writeObject(derEncodable);
        }
        catch (IOException ex) {
            throw new IllegalArgumentException("cannot encode object added to SET");
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    protected void sort() {
        if (this.set.size() > 1) {
            boolean b = true;
            int n = this.set.size() - 1;
            while (b) {
                int i = 0;
                int n2 = 0;
                byte[] encoded = this.getEncoded(this.set.elementAt(0));
                b = false;
                while (i != n) {
                    final byte[] encoded2 = this.getEncoded(this.set.elementAt(i + 1));
                    if (this.lessThanOrEqual(encoded, encoded2)) {
                        encoded = encoded2;
                    }
                    else {
                        final Object element = this.set.elementAt(i);
                        this.set.setElementAt(this.set.elementAt(i + 1), i);
                        this.set.setElementAt(element, i + 1);
                        b = true;
                        n2 = i;
                    }
                    ++i;
                }
                n = n2;
            }
        }
    }
    
    protected void addObject(final DEREncodable derEncodable) {
        this.set.addElement(derEncodable);
    }
    
    abstract void encode(final DEROutputStream p0) throws IOException;
    
    public String toString() {
        return this.set.toString();
    }
}
