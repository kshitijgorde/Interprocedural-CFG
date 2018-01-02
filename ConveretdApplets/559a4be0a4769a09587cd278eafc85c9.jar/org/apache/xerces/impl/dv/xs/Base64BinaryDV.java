// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.util.Base64;
import org.apache.xerces.impl.dv.ValidationContext;

public class Base64BinaryDV extends TypeValidator
{
    public short getAllowedFacets() {
        return 126;
    }
    
    public Object getActualValue(final String content, final ValidationContext context) throws InvalidDatatypeValueException {
        final byte[] decoded = Base64.decode(content);
        if (decoded == null) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { content, "base64Binary" });
        }
        return new XBase64(decoded);
    }
    
    public int getDataLength(final Object value) {
        return ((XBase64)value).length();
    }
    
    private static final class XBase64
    {
        final byte[] data;
        private String canonical;
        
        public XBase64(final byte[] data) {
            this.data = data;
        }
        
        public synchronized String toString() {
            if (this.canonical == null) {
                this.canonical = Base64.encode(this.data);
            }
            return this.canonical;
        }
        
        public int length() {
            return this.data.length;
        }
        
        public boolean equals(final Object obj) {
            if (!(obj instanceof XBase64)) {
                return false;
            }
            final byte[] odata = ((XBase64)obj).data;
            final int len = this.data.length;
            if (len != odata.length) {
                return false;
            }
            for (int i = 0; i < len; ++i) {
                if (this.data[i] != odata[i]) {
                    return false;
                }
            }
            return true;
        }
    }
}
