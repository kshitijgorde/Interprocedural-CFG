// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

class AllocatedDirectMemoryIO extends DirectMemoryIO
{
    private volatile boolean allocated;
    
    public AllocatedDirectMemoryIO(final int size, final boolean clear) {
        super(AllocatedDirectMemoryIO.IO.allocateMemory(size, clear));
        this.allocated = true;
        if (this.address == 0L) {
            throw new OutOfMemoryError("Failed to allocate " + size + " bytes");
        }
    }
    
    protected void finalize() throws Throwable {
        try {
            if (this.allocated) {
                AllocatedDirectMemoryIO.IO.freeMemory(this.address);
                this.allocated = false;
            }
        }
        finally {
            super.finalize();
        }
    }
}
