// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.openmbean;

import java.io.Serializable;

public class InvalidKeyException extends IllegalArgumentException implements Serializable
{
    private static final long serialVersionUID = 4224269443946322062L;
    
    public InvalidKeyException() {
    }
    
    public InvalidKeyException(final String message) {
        super(message);
    }
}
