// 
// Decompiled by Procyon v0.5.30
// 

package javax.transaction;

import java.rmi.RemoteException;

public class TransactionRequiredException extends RemoteException
{
    public TransactionRequiredException() {
    }
    
    public TransactionRequiredException(final String message) {
        super(message);
    }
}
