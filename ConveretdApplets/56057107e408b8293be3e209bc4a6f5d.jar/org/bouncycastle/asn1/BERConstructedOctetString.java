// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.asn1;

import java.util.Enumeration;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.util.Vector;

public class BERConstructedOctetString extends DEROctetString
{
    private static final int MAX_LENGTH = 1000;
    private Vector octs;
    
    private static byte[] toBytes(final Vector vector) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i != vector.size(); ++i) {
            try {
                byteArrayOutputStream.write(vector.elementAt(i).getOctets());
            }
            catch (ClassCastException ex2) {
                throw new IllegalArgumentException(vector.elementAt(i).getClass().getName() + " found in input should only contain DEROctetString");
            }
            catch (IOException ex) {
                throw new IllegalArgumentException("exception converting octets " + ex.toString());
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    public BERConstructedOctetString(final byte[] array) {
        super(array);
    }
    
    public BERConstructedOctetString(final Vector octs) {
        super(toBytes(octs));
        this.octs = octs;
    }
    
    public BERConstructedOctetString(final DERObject derObject) {
        super(derObject);
    }
    
    public BERConstructedOctetString(final DEREncodable derEncodable) {
        super(derEncodable.getDERObject());
    }
    
    public byte[] getOctets() {
        return super.string;
    }
    
    public Enumeration getObjects() {
        if (this.octs == null) {
            return this.generateOcts().elements();
        }
        return this.octs.elements();
    }
    
    private Vector generateOcts() {
        int n = 0;
        int n2 = 0;
        final Vector<DEROctetString> vector = new Vector<DEROctetString>();
        while (n2 + 1 < super.string.length) {
            if (super.string[n2] == 0 && super.string[n2 + 1] == 0) {
                final byte[] array = new byte[n2 - n + 1];
                System.arraycopy(super.string, n, array, 0, array.length);
                vector.addElement(new DEROctetString(array));
                n = n2 + 1;
            }
            ++n2;
        }
        final byte[] array2 = new byte[super.string.length - n];
        System.arraycopy(super.string, n, array2, 0, array2.length);
        vector.addElement(new DEROctetString(array2));
        return vector;
    }
    
    public void encode(final DEROutputStream derOutputStream) throws IOException {
        if (derOutputStream instanceof ASN1OutputStream || derOutputStream instanceof BEROutputStream) {
            derOutputStream.write(36);
            derOutputStream.write(128);
            if (this.octs != null) {
                for (int i = 0; i != this.octs.size(); ++i) {
                    derOutputStream.writeObject(this.octs.elementAt(i));
                }
            }
            else {
                for (int j = 0; j < super.string.length; j += 1000) {
                    int length;
                    if (j + 1000 > super.string.length) {
                        length = super.string.length;
                    }
                    else {
                        length = j + 1000;
                    }
                    final byte[] array = new byte[length - j];
                    System.arraycopy(super.string, j, array, 0, array.length);
                    derOutputStream.writeObject(new DEROctetString(array));
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
