// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

public class MalformedObjectNameException extends OperationsException
{
    private static final long serialVersionUID = -572689714442915824L;
    
    public MalformedObjectNameException() {
    }
    
    public MalformedObjectNameException(final String message) {
        super(message);
    }
}
