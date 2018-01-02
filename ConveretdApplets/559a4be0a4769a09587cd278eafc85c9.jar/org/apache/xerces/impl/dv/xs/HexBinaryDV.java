// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.util.HexBin;
import org.apache.xerces.impl.dv.ValidationContext;

public class HexBinaryDV extends TypeValidator
{
    public short getAllowedFacets() {
        return 126;
    }
    
    public Object getActualValue(final String content, final ValidationContext context) throws InvalidDatatypeValueException {
        final byte[] decoded = HexBin.decode(content);
        if (decoded == null) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { content, "hexBinary" });
        }
        return new XHex(decoded);
    }
    
    public int getDataLength(final Object value) {
        return ((XHex)value).length();
    }
    
    private static final class XHex
    {
        final byte[] data;
        private String canonical;
        
        public XHex(final byte[] data) {
            this.data = data;
        }
        
        public synchronized String toString() {
            if (this.canonical == null) {
                this.canonical = HexBin.encode(this.data);
            }
            return this.canonical;
        }
        
        public int length() {
            return this.data.length;
        }
        
        public boolean equals(final Object obj) {
            if (!(obj instanceof XHex)) {
                return false;
            }
            final byte[] odata = ((XHex)obj).data;
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
