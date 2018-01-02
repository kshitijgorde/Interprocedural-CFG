// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.openmbean;

import java.io.Serializable;

public class InvalidOpenTypeException extends IllegalArgumentException implements Serializable
{
    private static final long serialVersionUID = -2837312755412327534L;
    
    public InvalidOpenTypeException() {
    }
    
    public InvalidOpenTypeException(final String message) {
        super(message);
    }
}
