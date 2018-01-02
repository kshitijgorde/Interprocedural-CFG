// 
// Decompiled by Procyon v0.5.30
// 

package forward.client;

public class ClientForwardException extends Exception
{
    public static final int ERROR_CONNECTION_ERROR = 1;
    public static final int ERROR_PROTOCOL_ERROR = 2;
    public static final int ERROR_VERSION_ERROR = 3;
    public static final int ERROR_UNKNOWN_ERROR = 255;
    private int m_errorCode;
    
    public ClientForwardException(final int errorCode, final String s) {
        super(s);
        this.m_errorCode = errorCode;
    }
    
    public int getErrorCode() {
        return this.m_errorCode;
    }
}
