// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

public abstract class NAbstractDisposable implements NDisposable
{
    private boolean isDisposed;
    
    public NAbstractDisposable() {
        this.isDisposed = false;
    }
    
    protected void check() {
        if (this.isDisposed) {
            throw new RuntimeException("Allocated resources were released previously");
        }
    }
    
    protected abstract void dispose(final boolean p0);
    
    protected final boolean isDisposed() {
        return this.isDisposed;
    }
    
    protected boolean isOwned() {
        return false;
    }
    
    public void dispose() {
        if (!this.isDisposed && !this.isOwned()) {
            this.dispose(true);
            this.isDisposed = true;
        }
    }
}
