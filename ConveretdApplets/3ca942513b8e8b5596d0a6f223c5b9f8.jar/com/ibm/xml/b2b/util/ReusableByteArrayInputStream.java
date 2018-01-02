// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

import java.io.ByteArrayInputStream;

public final class ReusableByteArrayInputStream extends ByteArrayInputStream
{
    public ReusableByteArrayInputStream(final byte[] array, final int n, final int n2) {
        super(array, n, n2);
    }
    
    public void setBuffer(final byte[] buf, final int n, final int n2) {
        super.buf = buf;
        super.pos = n;
        super.count = n + n2;
        super.mark = n;
    }
}
