// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

public class ErrorResponse
{
    public static final int ABORT = 1;
    public static final int IGNORE = 2;
    public static final int RETRY = 3;
    private int response;
    
    public ErrorResponse() {
        this.response = 1;
    }
    
    public final void abort() {
        this.response = 1;
    }
    
    public final void ignore() {
        this.response = 2;
    }
    
    public final void retry() {
        this.response = 3;
    }
    
    public final int getResponse() {
        return this.response;
    }
    
    public final boolean isAbort() {
        return this.response == 1;
    }
    
    public final boolean isIgnore() {
        return this.response == 2;
    }
    
    public final boolean isRetry() {
        return this.response == 3;
    }
}
