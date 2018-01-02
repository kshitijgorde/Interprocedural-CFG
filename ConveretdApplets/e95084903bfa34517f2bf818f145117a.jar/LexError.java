// 
// Decompiled by Procyon v0.5.30
// 

class LexError extends Exception
{
    public String errMsg;
    
    public LexError(final String errMsg) {
        this.errMsg = errMsg;
    }
    
    public String toString() {
        return this.errMsg;
    }
}
