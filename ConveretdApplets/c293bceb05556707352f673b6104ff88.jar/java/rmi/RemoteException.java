// 
// Decompiled by Procyon v0.5.30
// 

package java.rmi;

import java.io.IOException;

public class RemoteException extends IOException
{
    private static final long serialVersionUID = -5148567311918794206L;
    public Throwable detail;
    
    public RemoteException() {
    }
    
    public RemoteException(final String s) {
        super(s);
    }
    
    public RemoteException(final String s, final Throwable detail) {
        super(s);
        this.detail = detail;
    }
    
    public String getMessage() {
        if (this.detail == null) {
            return super.getMessage();
        }
        return String.valueOf(super.getMessage()) + "; nested exception is: \n\t" + this.detail.toString();
    }
}
