// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.util.ByteListImpl;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.util.Base64;
import org.apache.xerces.impl.dv.ValidationContext;

public class Base64BinaryDV extends TypeValidator
{
    public short getAllowedFacets() {
        return 2079;
    }
    
    public Object getActualValue(final String s, final ValidationContext validationContext) throws InvalidDatatypeValueException {
        final byte[] decode = Base64.decode(s);
        if (decode == null) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { s, "base64Binary" });
        }
        return new XBase64(decode);
    }
    
    public int getDataLength(final Object o) {
        return ((XBase64)o).getLength();
    }
    
    private static final class XBase64 extends ByteListImpl
    {
        public XBase64(final byte[] array) {
            super(array);
        }
        
        public synchronized String toString() {
            if (super.canonical == null) {
                super.canonical = Base64.encode(super.data);
            }
            return super.canonical;
        }
        
        public boolean equals(final Object o) {
            if (!(o instanceof XBase64)) {
                return false;
            }
            final byte[] data = ((XBase64)o).data;
            final int length = super.data.length;
            if (length != data.length) {
                return false;
            }
            for (int i = 0; i < length; ++i) {
                if (super.data[i] != data[i]) {
                    return false;
                }
            }
            return true;
        }
    }
}
