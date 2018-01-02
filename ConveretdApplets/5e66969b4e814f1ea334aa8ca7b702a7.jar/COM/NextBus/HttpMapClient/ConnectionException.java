// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.HttpMapClient;

public class ConnectionException extends Exception
{
    private static final long serialVersionUID = 2776274932950149440L;
    private int _errorCode;
    
    public ConnectionException(final int errorCode, final String s) {
        super(s);
        this._errorCode = errorCode;
    }
    
    public final int a() {
        return this._errorCode;
    }
}
