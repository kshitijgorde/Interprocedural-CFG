// 
// Decompiled by Procyon v0.5.30
// 

package anon.crypto;

import java.util.Enumeration;
import java.util.StringTokenizer;
import org.bouncycastle.asn1.DERObjectIdentifier;
import java.util.Hashtable;
import org.bouncycastle.asn1.x509.X509Name;
import java.util.Vector;

public final class X509DistinguishedName
{
    public static final String IDENTIFIER_CN;
    public static final String IDENTIFIER_C;
    public static final String IDENTIFIER_ST;
    public static final String IDENTIFIER_L;
    public static final String IDENTIFIER_O;
    public static final String IDENTIFIER_OU;
    public static final String IDENTIFIER_E;
    public static final String IDENTIFIER_EmailAddress;
    public static final String IDENTIFIER_SURNAME;
    public static final String IDENTIFIER_GIVENNAME;
    public static final String LABEL_COMMON_NAME = "CN";
    public static final String LABEL_COUNTRY = "C";
    public static final String LABEL_STATE_OR_PROVINCE = "ST";
    public static final String LABEL_LOCALITY = "L";
    public static final String LABEL_ORGANISATION = "O";
    public static final String LABEL_ORGANISATIONAL_UNIT = "OU";
    public static final String LABEL_EMAIL = "E";
    public static final String LABEL_EMAIL_ADDRESS = "EmailAddress";
    public static final String LABEL_SURNAME = "SURNAME";
    public static final String LABEL_GIVENNAME = "GIVENNAME";
    private static Vector m_sortedIdentifiers;
    private X509Name m_bcX509Name;
    
    public X509DistinguishedName(final String s) {
        this.m_bcX509Name = new X509Name(s);
    }
    
    public X509DistinguishedName(final Hashtable hashtable) throws IllegalCharacterException {
        if (hashtable == null) {
            throw new IllegalArgumentException("Attributes must not be null!");
        }
        final Enumeration<Object> keys = hashtable.keys();
        final Vector<Object> vector = new Vector<Object>();
        final Vector vector2 = new Vector<String>();
        while (keys.hasMoreElements()) {
            Object nextElement = keys.nextElement();
            if (hashtable.get(nextElement) == null) {
                continue;
            }
            final String string = hashtable.get(nextElement).toString();
            if (string.trim().length() == 0) {
                continue;
            }
            if (!(nextElement instanceof DERObjectIdentifier)) {
                nextElement = new DERObjectIdentifier(nextElement.toString());
            }
            if (nextElement.equals(X509Name.E) || nextElement.equals(X509Name.EmailAddress) || nextElement.equals(X509Name.OU)) {
                final StringTokenizer stringTokenizer = new StringTokenizer(string, ",");
                while (stringTokenizer.hasMoreTokens()) {
                    vector.addElement(nextElement);
                    vector2.addElement(stringTokenizer.nextToken().trim());
                }
            }
            else {
                vector.addElement(nextElement);
                vector2.addElement(string.trim());
            }
        }
        if (vector2.size() == 0) {
            throw new IllegalArgumentException("Attributes are empty!");
        }
        this.m_bcX509Name = new X509Name(vector, vector2);
    }
    
    public X509DistinguishedName(final X509Name bcX509Name) {
        this.m_bcX509Name = bcX509Name;
    }
    
    public static String getAttributeNameFromAttributeIdentifier(final String s) {
        if (s == null) {
            return null;
        }
        if (s.equals(X509DistinguishedName.IDENTIFIER_CN)) {
            return "CN";
        }
        if (s.equals(X509DistinguishedName.IDENTIFIER_C)) {
            return "C";
        }
        if (s.equals(X509DistinguishedName.IDENTIFIER_ST)) {
            return "ST";
        }
        if (s.equals(X509DistinguishedName.IDENTIFIER_L)) {
            return "L";
        }
        if (s.equals(X509DistinguishedName.IDENTIFIER_O)) {
            return "O";
        }
        if (s.equals(X509DistinguishedName.IDENTIFIER_OU)) {
            return "OU";
        }
        if (s.equals(X509DistinguishedName.IDENTIFIER_E)) {
            return "E";
        }
        if (s.equals(X509DistinguishedName.IDENTIFIER_EmailAddress)) {
            return "EmailAddress";
        }
        if (s.equals(X509DistinguishedName.IDENTIFIER_SURNAME)) {
            return "SURNAME";
        }
        if (s.equals(X509DistinguishedName.IDENTIFIER_GIVENNAME)) {
            return "GIVENNAME";
        }
        return s;
    }
    
    public String getCommonName() {
        return this.getAttributeValue(X509DistinguishedName.IDENTIFIER_CN);
    }
    
    public String getSurname() {
        return this.getAttributeValue(X509DistinguishedName.IDENTIFIER_SURNAME);
    }
    
    public String getGivenName() {
        return this.getAttributeValue(X509DistinguishedName.IDENTIFIER_GIVENNAME);
    }
    
    public String getCountryCode() {
        return this.getAttributeValue(X509DistinguishedName.IDENTIFIER_C);
    }
    
    public String getStateOrProvince() {
        return this.getAttributeValue(X509DistinguishedName.IDENTIFIER_ST);
    }
    
    public String getLocalityName() {
        return this.getAttributeValue(X509DistinguishedName.IDENTIFIER_L);
    }
    
    public String getOrganisation() {
        return this.getAttributeValue(X509DistinguishedName.IDENTIFIER_O);
    }
    
    public String getOrganisationalUnit() {
        return this.getAttributeValue(X509DistinguishedName.IDENTIFIER_OU);
    }
    
    public String getE_EmailAddress() {
        return this.getAttributeValue(X509DistinguishedName.IDENTIFIER_E);
    }
    
    public String getEmailAddress() {
        return this.getAttributeValue(X509DistinguishedName.IDENTIFIER_EmailAddress);
    }
    
    public String getAttributeValue(final String s) {
        if (s == null || s.trim().length() == 0) {
            return null;
        }
        final DERObjectIdentifier derObjectIdentifier = new DERObjectIdentifier(s);
        final Vector values = this.m_bcX509Name.getValues();
        final Vector oiDs = this.m_bcX509Name.getOIDs();
        final int index = oiDs.indexOf(derObjectIdentifier);
        if (index < 0) {
            return null;
        }
        String s2 = values.elementAt(index);
        if (s2 != null) {
            for (int i = index + 1; i < oiDs.size(); ++i) {
                if (oiDs.elementAt(i).equals(derObjectIdentifier)) {
                    String s3 = s2.trim();
                    if (s3.length() > 0) {
                        s3 += ", ";
                    }
                    s2 = s3 + values.elementAt(i);
                }
            }
            s2 = s2.trim();
        }
        return s2;
    }
    
    public Vector getAttributeIdentifiers() {
        final Vector<String> vector = new Vector<String>();
        final Vector oiDs = this.m_bcX509Name.getOIDs();
        final Enumeration sortedIdentifiers = getSortedIdentifiers();
        while (sortedIdentifiers.hasMoreElements()) {
            final int index;
            if ((index = oiDs.indexOf(sortedIdentifiers.nextElement())) >= 0) {
                vector.addElement(oiDs.elementAt(index).getId());
                oiDs.removeElementAt(index);
            }
        }
        for (int i = 0; i < oiDs.size(); ++i) {
            vector.addElement(oiDs.elementAt(i).getId());
        }
        return vector;
    }
    
    public Vector getAttributeValues() {
        final Vector oiDs = this.m_bcX509Name.getOIDs();
        final Vector values = this.m_bcX509Name.getValues();
        final Vector<Object> vector = new Vector<Object>();
        final Enumeration sortedIdentifiers = getSortedIdentifiers();
        while (sortedIdentifiers.hasMoreElements()) {
            final int index;
            if ((index = oiDs.indexOf(sortedIdentifiers.nextElement())) >= 0) {
                vector.addElement(values.elementAt(index));
                oiDs.removeElementAt(index);
                values.removeElementAt(index);
            }
        }
        for (int i = 0; i < values.size(); ++i) {
            vector.addElement(values.elementAt(i));
        }
        return vector;
    }
    
    public Hashtable getDistinguishedName() {
        final Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
        final Vector attributeIdentifiers = this.getAttributeIdentifiers();
        final Vector attributeValues = this.getAttributeValues();
        for (int i = 0; i < attributeIdentifiers.size(); ++i) {
            hashtable.put(attributeIdentifiers.elementAt(i), attributeValues.elementAt(i));
        }
        return hashtable;
    }
    
    public int hashCode() {
        return this.m_bcX509Name.hashCode();
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof X509DistinguishedName) {
            return this.m_bcX509Name.equals(((X509DistinguishedName)o).m_bcX509Name);
        }
        return o instanceof X509Name && this.m_bcX509Name.equals(o);
    }
    
    public String toString() {
        final Vector attributeIdentifiers = this.getAttributeIdentifiers();
        final Vector attributeValues = this.getAttributeValues();
        String s = "";
        for (int i = 0; i < attributeIdentifiers.size(); ++i) {
            s = s + getAttributeNameFromAttributeIdentifier(attributeIdentifiers.elementAt(i)) + "=" + attributeValues.elementAt(i);
            if (i + 1 < attributeIdentifiers.size()) {
                s += ", ";
            }
        }
        return s;
    }
    
    X509Name getX509Name() {
        return this.m_bcX509Name;
    }
    
    private static Enumeration getSortedIdentifiers() {
        if (X509DistinguishedName.m_sortedIdentifiers == null) {
            (X509DistinguishedName.m_sortedIdentifiers = new Vector()).addElement(X509Name.CN);
            X509DistinguishedName.m_sortedIdentifiers.addElement(X509Name.SURNAME);
            X509DistinguishedName.m_sortedIdentifiers.addElement(X509Name.GIVENNAME);
            X509DistinguishedName.m_sortedIdentifiers.addElement(X509Name.O);
            X509DistinguishedName.m_sortedIdentifiers.addElement(X509Name.OU);
            X509DistinguishedName.m_sortedIdentifiers.addElement(X509Name.L);
            X509DistinguishedName.m_sortedIdentifiers.addElement(X509Name.ST);
            X509DistinguishedName.m_sortedIdentifiers.addElement(X509Name.C);
            X509DistinguishedName.m_sortedIdentifiers.addElement(X509Name.E);
            X509DistinguishedName.m_sortedIdentifiers.addElement(X509Name.EmailAddress);
        }
        return X509DistinguishedName.m_sortedIdentifiers.elements();
    }
    
    static {
        IDENTIFIER_CN = X509Name.CN.getId();
        IDENTIFIER_C = X509Name.C.getId();
        IDENTIFIER_ST = X509Name.ST.getId();
        IDENTIFIER_L = X509Name.L.getId();
        IDENTIFIER_O = X509Name.O.getId();
        IDENTIFIER_OU = X509Name.OU.getId();
        IDENTIFIER_E = X509Name.E.getId();
        IDENTIFIER_EmailAddress = X509Name.EmailAddress.getId();
        IDENTIFIER_SURNAME = X509Name.SURNAME.getId();
        IDENTIFIER_GIVENNAME = X509Name.GIVENNAME.getId();
    }
    
    public class IllegalCharacterException extends IllegalArgumentException
    {
        private char m_character;
        private String m_attribute;
        
        private IllegalCharacterException(final DERObjectIdentifier derObjectIdentifier, final char character) {
            super("'" + character + "' characters are not allowed!");
            this.m_attribute = X509DistinguishedName.getAttributeNameFromAttributeIdentifier(derObjectIdentifier.getId());
            this.m_character = character;
        }
        
        public char getCharacter() {
            return this.m_character;
        }
        
        public String getAttribute() {
            return this.m_attribute;
        }
    }
}
