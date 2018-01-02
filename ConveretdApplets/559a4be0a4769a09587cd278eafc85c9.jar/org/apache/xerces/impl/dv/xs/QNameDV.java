// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.xni.QName;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.impl.dv.ValidationContext;

public class QNameDV extends TypeValidator
{
    private static final String EMPTY_STRING;
    
    public short getAllowedFacets() {
        return 126;
    }
    
    public Object getActualValue(final String content, final ValidationContext context) throws InvalidDatatypeValueException {
        final int colonptr = content.indexOf(":");
        String prefix;
        String localpart;
        if (colonptr > 0) {
            prefix = context.getSymbol(content.substring(0, colonptr));
            localpart = content.substring(colonptr + 1);
        }
        else {
            prefix = QNameDV.EMPTY_STRING;
            localpart = content;
        }
        if (prefix.length() > 0 && !XMLChar.isValidNCName(prefix)) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { content, "QName" });
        }
        if (!XMLChar.isValidNCName(localpart)) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { content, "QName" });
        }
        final String uri = context.getURI(prefix);
        if (prefix.length() > 0 && uri == null) {
            throw new InvalidDatatypeValueException("UndeclaredPrefix", new Object[] { content, prefix });
        }
        return new XQName(prefix, context.getSymbol(localpart), context.getSymbol(content), uri);
    }
    
    public int getDataLength(final Object value) {
        return ((XQName)value).rawname.length();
    }
    
    static {
        EMPTY_STRING = "".intern();
    }
    
    private static final class XQName extends QName
    {
        private String canonical;
        
        public XQName(final String prefix, final String localpart, final String rawname, final String uri) {
            this.setValues(prefix, localpart, rawname, uri);
        }
        
        public boolean equals(final Object object) {
            if (object instanceof QName) {
                final QName qname = (QName)object;
                return super.uri == qname.uri && super.localpart == qname.localpart;
            }
            return false;
        }
        
        public synchronized String toString() {
            if (this.canonical == null) {
                if (super.prefix == null) {
                    this.canonical = super.localpart;
                }
                else {
                    this.canonical = super.prefix + ':' + super.localpart;
                }
            }
            return this.canonical;
        }
    }
}
