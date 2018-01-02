// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.output;

public class jiImageSaveFailedException extends Exception
{
    public static final int UNKNOWN = 0;
    public static final int WRITER_NO_MULTI_PAGE_SUPPORT = 1;
    private int reason;
    
    public jiImageSaveFailedException(final String s) {
        this(s, 0);
    }
    
    public jiImageSaveFailedException(final String s, final int reason) {
        super(s);
        this.reason = reason;
    }
    
    public int getReason() {
        return this.reason;
    }
}
