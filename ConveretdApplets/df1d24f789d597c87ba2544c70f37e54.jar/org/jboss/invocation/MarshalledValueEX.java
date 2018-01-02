// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.invocation;

import java.io.IOException;
import org.jboss.remoting.serialization.IMarshalledValue;

public class MarshalledValueEX extends MarshalledValue implements IMarshalledValue
{
    private static final long serialVersionUID = -1527598981234110322L;
    
    public MarshalledValueEX() {
    }
    
    public MarshalledValueEX(final Object obj) throws IOException {
        super(obj);
    }
}
