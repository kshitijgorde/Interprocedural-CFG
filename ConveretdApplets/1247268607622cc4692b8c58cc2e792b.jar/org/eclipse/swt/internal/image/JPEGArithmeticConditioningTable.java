// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

final class JPEGArithmeticConditioningTable extends JPEGVariableSizeSegment
{
    public JPEGArithmeticConditioningTable(final LEDataInputStream leDataInputStream) {
        super(leDataInputStream);
    }
    
    public int signature() {
        return 65484;
    }
}
