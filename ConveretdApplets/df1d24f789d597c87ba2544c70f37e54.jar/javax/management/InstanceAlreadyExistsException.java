// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

public class InstanceAlreadyExistsException extends OperationsException
{
    private static final long serialVersionUID = 8893743928912733931L;
    
    public InstanceAlreadyExistsException() {
    }
    
    public InstanceAlreadyExistsException(final String message) {
        super(message);
    }
}
