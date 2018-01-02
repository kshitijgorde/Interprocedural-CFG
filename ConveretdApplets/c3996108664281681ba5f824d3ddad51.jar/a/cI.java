// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.DataOutput;
import java.io.DataInput;

public final class cI extends cJ
{
    private long w;
    
    public cI(final DataInput dataInput) {
        this.w = dataInput.readLong();
        super.q(dataInput);
    }
    
    public cI(final cJ cj) {
        super(cj);
    }
    
    public final void q(final DataOutput dataOutput) {
        dataOutput.writeLong(System.currentTimeMillis());
        super.q(dataOutput);
    }
    
    public final String toString() {
        return super.toString() + "\ntime=" + this.w;
    }
}
