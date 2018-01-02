// 
// Decompiled by Procyon v0.5.30
// 

class TTRuntimeError extends Exception
{
    public String errMsg;
    
    public TTRuntimeError(final String errMsg) {
        this.errMsg = errMsg;
    }
}
