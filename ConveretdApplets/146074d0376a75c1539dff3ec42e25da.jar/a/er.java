// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.DataOutput;
import java.io.DataInput;

public final class er extends es
{
    private long w;
    
    public er(final DataInput dataInput) {
        this.w = dataInput.readLong();
        super.q(dataInput);
    }
    
    public er(final es es) {
        super(es);
    }
    
    public final void q(final DataOutput dataOutput) {
        dataOutput.writeLong(System.currentTimeMillis());
        super.q(dataOutput);
    }
    
    public final String toString() {
        return super.toString() + "\ntime=" + this.w;
    }
}
