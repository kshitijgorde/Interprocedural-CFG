// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.openmbean;

import java.io.Serializable;

public class KeyAlreadyExistsException extends IllegalArgumentException implements Serializable
{
    private static final long serialVersionUID = 1845183636745282866L;
    
    public KeyAlreadyExistsException() {
    }
    
    public KeyAlreadyExistsException(final String message) {
        super(message);
    }
}
