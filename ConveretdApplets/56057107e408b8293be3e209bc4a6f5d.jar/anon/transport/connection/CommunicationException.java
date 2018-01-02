// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.connection;

public class CommunicationException extends ConnectionException
{
    private static final long serialVersionUID = 1L;
    
    public CommunicationException(final String s) {
        super(s);
    }
    
    public CommunicationException(final Throwable t) {
        super(t);
    }
}
