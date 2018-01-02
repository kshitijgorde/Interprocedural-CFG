// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import com.kenai.jaffl.MemoryIO;
import com.kenai.jaffl.provider.BoundedMemoryIO;

class BoundedDirectMemoryIO extends BoundedMemoryIO
{
    BoundedDirectMemoryIO(final DirectMemoryIO parent, final long offset, final long size) {
        super(parent, offset, size);
    }
    
    public long address() {
        return this.getDelegatedMemoryIO().address();
    }
}
