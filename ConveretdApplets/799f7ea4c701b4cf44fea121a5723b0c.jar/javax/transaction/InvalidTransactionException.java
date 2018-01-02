// 
// Decompiled by Procyon v0.5.30
// 

package javax.transaction;

import java.rmi.RemoteException;

public class InvalidTransactionException extends RemoteException
{
    public InvalidTransactionException() {
    }
    
    public InvalidTransactionException(final String message) {
        super(message);
    }
}
