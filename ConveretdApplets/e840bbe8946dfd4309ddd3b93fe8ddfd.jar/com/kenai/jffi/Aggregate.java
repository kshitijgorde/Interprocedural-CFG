// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Aggregate extends Type
{
    private final int type;
    private final int size;
    private final int align;
    private final long handle;
    private volatile boolean disposed;
    private final Foreign foreign;
    
    public Aggregate(final long handle) {
        this(Foreign.getInstance(), handle);
    }
    
    Aggregate(final Foreign foreign, final long handle) {
        this.disposed = false;
        if (handle == 0L) {
            throw new NullPointerException("Invalid ffi_type handle");
        }
        this.foreign = foreign;
        this.handle = handle;
        this.type = foreign.getTypeType(handle);
        this.size = foreign.getTypeSize(handle);
        this.align = foreign.getTypeAlign(handle);
    }
    
    final long handle() {
        return this.handle;
    }
    
    public final int type() {
        return this.type;
    }
    
    public final int size() {
        return this.size;
    }
    
    public final int alignment() {
        return this.align;
    }
    
    public final synchronized void dispose() {
        if (this.disposed) {
            throw new RuntimeException("native handle already freed");
        }
        this.disposed = true;
        this.foreign.freeAggregate(this.handle);
    }
    
    protected void finalize() throws Throwable {
        try {
            if (!this.disposed) {
                this.dispose();
            }
        }
        catch (Throwable t) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Exception when freeing FFI aggregate: %s", t.getLocalizedMessage());
        }
        finally {
            super.finalize();
        }
    }
}
