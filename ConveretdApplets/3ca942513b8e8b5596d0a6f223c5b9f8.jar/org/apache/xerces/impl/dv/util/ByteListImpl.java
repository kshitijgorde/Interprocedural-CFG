// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.dv.util;

import org.apache.xerces.xs.XSException;
import org.apache.xerces.xs.datatypes.ByteList;

public class ByteListImpl implements ByteList
{
    protected final byte[] data;
    protected String canonical;
    
    public ByteListImpl(final byte[] data) {
        this.data = data;
    }
    
    public int getLength() {
        return this.data.length;
    }
    
    public boolean contains(final byte b) {
        for (int i = 0; i < this.data.length; ++i) {
            if (this.data[i] == b) {
                return true;
            }
        }
        return false;
    }
    
    public byte item(final int n) throws XSException {
        if (n < 0 || n > this.data.length - 1) {
            throw new XSException((short)2, null);
        }
        return this.data[n];
    }
}
