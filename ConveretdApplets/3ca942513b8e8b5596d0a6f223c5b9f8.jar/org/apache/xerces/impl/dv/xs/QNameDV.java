// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.xs.datatypes.XSQName;
import org.apache.xerces.xni.QName;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.impl.dv.ValidationContext;

public class QNameDV extends TypeValidator
{
    private static final String EMPTY_STRING;
    
    public short getAllowedFacets() {
        return 2079;
    }
    
    public Object getActualValue(final String s, final ValidationContext validationContext) throws InvalidDatatypeValueException {
        final int index = s.indexOf(":");
        String s2;
        String substring;
        if (index > 0) {
            s2 = validationContext.getSymbol(s.substring(0, index));
            substring = s.substring(index + 1);
        }
        else {
            s2 = QNameDV.EMPTY_STRING;
            substring = s;
        }
        if (s2.length() > 0 && !XMLChar.isValidNCName(s2)) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { s, "QName" });
        }
        if (!XMLChar.isValidNCName(substring)) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { s, "QName" });
        }
        final String uri = validationContext.getURI(s2);
        if (s2.length() > 0 && uri == null) {
            throw new InvalidDatatypeValueException("UndeclaredPrefix", new Object[] { s, s2 });
        }
        return new XQName(s2, validationContext.getSymbol(substring), validationContext.getSymbol(s), uri);
    }
    
    public int getDataLength(final Object o) {
        return ((XQName)o).rawname.length();
    }
    
    static {
        EMPTY_STRING = "".intern();
    }
    
    private static final class XQName extends QName implements XSQName
    {
        public XQName(final String s, final String s2, final String s3, final String s4) {
            this.setValues(s, s2, s3, s4);
        }
        
        public boolean equals(final Object o) {
            if (o instanceof QName) {
                final QName qName = (QName)o;
                return super.uri == qName.uri && super.localpart == qName.localpart;
            }
            return false;
        }
        
        public synchronized String toString() {
            return super.rawname;
        }
        
        public javax.xml.namespace.QName getJAXPQName() {
            return new javax.xml.namespace.QName(super.uri, super.localpart, super.prefix);
        }
        
        public QName getXNIQName() {
            return this;
        }
    }
}
