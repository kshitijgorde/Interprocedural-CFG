// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.connection;

public class RequestException extends ConnectionException
{
    public static final int Reason_UNKNOWN = 1;
    public static final int Reason_SERVER_BUSY = 2;
    public static final int Reason_MISSING_CREDENTIALS = 3;
    public static final int Reason_OTHER = 4;
    private static final long serialVersionUID = 1L;
    private int m_reason;
    
    public RequestException(final Throwable t, final int reason) {
        super(t);
        this.m_reason = reason;
    }
    
    public RequestException(final Throwable t) {
        super(t);
        this.m_reason = 1;
    }
    
    public RequestException(final String s, final int reason) {
        super(s);
        this.m_reason = reason;
    }
    
    public RequestException(final String s) {
        super(s);
        this.m_reason = 1;
    }
    
    public int getReason() {
        return this.m_reason;
    }
}
