// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.util.ByteListImpl;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.util.HexBin;
import org.apache.xerces.impl.dv.ValidationContext;

public class HexBinaryDV extends TypeValidator
{
    public short getAllowedFacets() {
        return 2079;
    }
    
    public Object getActualValue(final String s, final ValidationContext validationContext) throws InvalidDatatypeValueException {
        final byte[] decode = HexBin.decode(s);
        if (decode == null) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[] { s, "hexBinary" });
        }
        return new XHex(decode);
    }
    
    public int getDataLength(final Object o) {
        return ((XHex)o).getLength();
    }
    
    private static final class XHex extends ByteListImpl
    {
        public XHex(final byte[] array) {
            super(array);
        }
        
        public synchronized String toString() {
            if (super.canonical == null) {
                super.canonical = HexBin.encode(super.data);
            }
            return super.canonical;
        }
        
        public boolean equals(final Object o) {
            if (!(o instanceof XHex)) {
                return false;
            }
            final byte[] data = ((XHex)o).data;
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
