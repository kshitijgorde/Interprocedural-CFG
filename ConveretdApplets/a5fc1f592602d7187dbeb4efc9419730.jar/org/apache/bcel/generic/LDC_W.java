// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

import java.io.IOException;
import org.apache.bcel.util.ByteSequence;

public class LDC_W extends LDC
{
    LDC_W() {
    }
    
    public LDC_W(final int index) {
        super(index);
    }
    
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        this.setIndex(bytes.readUnsignedShort());
        super.length = 3;
    }
}
