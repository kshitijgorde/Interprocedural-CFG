// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.connection;

public class ConnectionException extends Exception
{
    private static final long serialVersionUID = 1L;
    
    public ConnectionException(final Throwable t) {
        super((t == null) ? null : t.toString());
    }
    
    public ConnectionException(final String s) {
        super(s);
    }
}
