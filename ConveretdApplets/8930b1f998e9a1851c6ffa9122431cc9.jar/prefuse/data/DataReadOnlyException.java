// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data;

public class DataReadOnlyException extends RuntimeException
{
    public DataReadOnlyException() {
    }
    
    public DataReadOnlyException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public DataReadOnlyException(final String s) {
        super(s);
    }
    
    public DataReadOnlyException(final Throwable t) {
        super(t);
    }
}
