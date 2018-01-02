// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.connection;

public class UnsuportedCommandException extends ConnectionException
{
    private static final long serialVersionUID = 1L;
    
    public UnsuportedCommandException(final Throwable t) {
        super(t);
    }
    
    public UnsuportedCommandException(final String s) {
        super(s);
    }
}
