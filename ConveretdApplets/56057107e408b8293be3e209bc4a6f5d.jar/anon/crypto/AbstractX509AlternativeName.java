// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import org.bouncycastle.asn1.DEREncodableVector;
import java.io.OutputStream;
import org.bouncycastle.asn1.DEROutputStream;
import java.io.ByteArrayOutputStream;
import org.bouncycastle.asn1.DEREncodable;
import java.net.URL;
import org.bouncycastle.asn1.ASN1EncodableVector;
import java.util.StringTokenizer;
import anon.infoservice.ListenerInterface;
import java.util.Enumeration;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERTaggedObject;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1InputStream;
import java.io.ByteArrayInputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import anon.util.Util;
import java.util.Vector;

public abstract class AbstractX509AlternativeName extends AbstractX509Extension
{
    public static final Integer TAG_OTHER;
    public static final Integer TAG_EMAIL;
    public static final Integer TAG_DNS;
    public static final Integer TAG_URL;
    public static final Integer TAG_IP;
    public static final String OTHER_NAME = "otherName";
    public static final String RFC_822_NAME = "rfc822Name";
    public static final String DNS_NAME = "dNSName";
    public static final String X400_ADDRESS = "x400Address";
    public static final String DIRECTORY_NAME = "directoryName";
    public static final String EDI_PARTY_NAME = "ediPartyName";
    public static final String UNIFORM_RESOURCE_IDENTIFIER = "uniformResourceIdentifier";
    public static final String IP_ADDRESS = "iPAddress";
    public static final String REGISTERED_ID = "registeredID";
    private Vector m_values;
    private Vector m_tags;
    
    public AbstractX509AlternativeName(final String s, final String s2, final Integer n) {
        this(s, Util.toVector(s2), Util.toVector(n));
    }
    
    public AbstractX509AlternativeName(final String s, final boolean b, final String s2, final Integer n) {
        this(s, b, Util.toVector(s2), Util.toVector(n));
    }
    
    public AbstractX509AlternativeName(final String s, final Vector vector, final Vector vector2) {
        this(s, false, vector, vector2);
    }
    
    public AbstractX509AlternativeName(final String s, final boolean b, final Vector vector, final Vector vector2) {
        super(s, b, createValue(vector, vector2));
        this.m_values = (Vector)vector.clone();
        this.m_tags = (Vector)vector2.clone();
    }
    
    public AbstractX509AlternativeName(final DERSequence derSequence) {
        super(derSequence);
        this.m_values = new Vector();
        this.m_tags = new Vector();
        ASN1Sequence asn1Sequence;
        try {
            asn1Sequence = (ASN1Sequence)new ASN1InputStream(new ByteArrayInputStream(this.getDEROctets())).readObject();
        }
        catch (IOException ex) {
            throw new RuntimeException("Could not read object from DER sequence!");
        }
        final Enumeration objects = asn1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            final DERTaggedObject derTaggedObject = objects.nextElement();
            final Integer n = new Integer(derTaggedObject.getTagNo());
            final byte[] octets = ((DEROctetString)derTaggedObject.getObject()).getOctets();
            if (n.equals(AbstractX509AlternativeName.TAG_IP)) {
                String s = "";
                for (int i = 0; i < octets.length; ++i) {
                    s += (0xFF & octets[i]);
                    if (i + 1 < octets.length) {
                        s += ".";
                    }
                }
                this.m_values.addElement(s);
            }
            else {
                this.m_values.addElement(new String(octets));
            }
            this.m_tags.addElement(n);
        }
    }
    
    public static boolean isValidIP(final String s) {
        return ListenerInterface.isValidIP(s);
    }
    
    public static boolean isValidEMail(final String s) {
        if (s == null) {
            return false;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        if (!stringTokenizer.hasMoreElements()) {
            return false;
        }
        while (stringTokenizer.hasMoreElements()) {
            final String trim = stringTokenizer.nextToken().trim();
            if (trim.length() == 0) {
                return false;
            }
            final int lastIndex = trim.lastIndexOf(46);
            final int length = trim.length();
            final int index = trim.indexOf(64);
            if (length == 0 || index == -1 || lastIndex == -1 || index == 0 || lastIndex < index) {
                return false;
            }
            if (lastIndex + 2 >= length) {
                return false;
            }
        }
        return true;
    }
    
    public Vector getValues() {
        return (Vector)this.m_values.clone();
    }
    
    public Vector getTags() {
        return (Vector)this.m_tags.clone();
    }
    
    private static byte[] createValue(Vector vector, Vector vector2) {
        byte[] bytes = null;
        final ASN1EncodableVector asn1EncodableVector = new ASN1EncodableVector();
        if (vector != null && vector.size() != 0) {
            if (vector2 == null || vector.size() != vector2.size()) {
                throw new IllegalArgumentException("Tags have an invalid size!");
            }
            final Vector<Integer> vector3 = new Vector<Integer>();
            final Vector<String> vector4 = new Vector<String>();
            for (int i = 0; i < vector.size(); ++i) {
                if (!(vector.elementAt(i) instanceof String)) {
                    throw new IllegalArgumentException("Values must be Strings!");
                }
                final StringTokenizer stringTokenizer = new StringTokenizer(vector.elementAt(i), ",");
                while (stringTokenizer.hasMoreTokens()) {
                    vector3.addElement(vector2.elementAt(i));
                    vector4.addElement(stringTokenizer.nextToken().trim());
                }
            }
            vector2 = vector3;
            vector = vector4;
            for (int j = 0; j < vector.size(); ++j) {
                final String s = vector.elementAt(j);
                if (s != null) {
                    if (s.length() != 0) {
                        if (vector2.elementAt(j) == null || !(vector2.elementAt(j) instanceof Integer)) {
                            throw new IllegalArgumentException("Unsupported tag: " + vector2.elementAt(j));
                        }
                        final Integer n = vector2.elementAt(j);
                        Label_0452: {
                            if (n.equals(AbstractX509AlternativeName.TAG_IP)) {
                                if (!isValidIP(s)) {
                                    throw new IllegalArgumentException("Invalid IP address: " + s);
                                }
                            }
                            else if (n.equals(AbstractX509AlternativeName.TAG_EMAIL)) {
                                if (!isValidEMail(s)) {
                                    throw new IllegalArgumentException("Invalid email address: " + s);
                                }
                            }
                            else {
                                if (n.equals(AbstractX509AlternativeName.TAG_URL)) {
                                    try {
                                        new URL(s);
                                        break Label_0452;
                                    }
                                    catch (Exception ex) {
                                        throw new IllegalArgumentException(ex.getMessage());
                                    }
                                }
                                if (!n.equals(AbstractX509AlternativeName.TAG_DNS)) {
                                    if (!n.equals(AbstractX509AlternativeName.TAG_OTHER)) {
                                        throw new IllegalArgumentException("Unsupported tag: " + n);
                                    }
                                }
                            }
                        }
                        if (bytes == null) {
                            bytes = s.getBytes();
                        }
                        asn1EncodableVector.add(new DERTaggedObject(n, new DEROctetString(bytes)));
                        bytes = null;
                    }
                }
            }
        }
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new DEROutputStream(byteArrayOutputStream).writeObject(new DERSequence(asn1EncodableVector));
        }
        catch (IOException ex2) {
            throw new RuntimeException("Error while writing object to byte array.");
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    public static String getTagAsString(final int n) {
        switch (n) {
            case 0: {
                return "otherName";
            }
            case 1: {
                return "rfc822Name";
            }
            case 2: {
                return "dNSName";
            }
            case 3: {
                return "x400Address";
            }
            case 4: {
                return "directoryName";
            }
            case 5: {
                return "ediPartyName";
            }
            case 6: {
                return "uniformResourceIdentifier";
            }
            case 7: {
                return "iPAddress";
            }
            case 8: {
                return "registeredID";
            }
            default: {
                return null;
            }
        }
    }
    
    static {
        TAG_OTHER = new Integer(0);
        TAG_EMAIL = new Integer(1);
        TAG_DNS = new Integer(2);
        TAG_URL = new Integer(6);
        TAG_IP = new Integer(7);
    }
}
