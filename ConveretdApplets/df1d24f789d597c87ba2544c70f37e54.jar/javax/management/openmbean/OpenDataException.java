// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.openmbean;

import java.io.Serializable;
import javax.management.JMException;

public class OpenDataException extends JMException implements Serializable
{
    private static final long serialVersionUID = 8346311255433349870L;
    
    public OpenDataException() {
    }
    
    public OpenDataException(final String message) {
        super(message);
    }
}
