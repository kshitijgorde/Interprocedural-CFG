// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import org.eclipse.swt.SWT;

abstract class JPEGVariableSizeSegment extends JPEGSegment
{
    public JPEGVariableSizeSegment(final byte[] array) {
        super(array);
    }
    
    public JPEGVariableSizeSegment(final LEDataInputStream leDataInputStream) {
        try {
            final byte[] reference = new byte[4];
            leDataInputStream.read(reference);
            this.reference = reference;
            final byte[] reference2 = new byte[this.getSegmentLength() + 2];
            reference2[0] = reference[0];
            reference2[1] = reference[1];
            reference2[2] = reference[2];
            reference2[3] = reference[3];
            leDataInputStream.read(reference2, 4, reference2.length - 4);
            this.reference = reference2;
        }
        catch (Exception ex) {
            SWT.error(39, ex);
        }
    }
}
