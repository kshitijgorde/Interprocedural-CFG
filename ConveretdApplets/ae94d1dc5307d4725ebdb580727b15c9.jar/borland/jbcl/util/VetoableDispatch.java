// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

import java.util.EventListener;

public interface VetoableDispatch
{
    void vetoableDispatch(final EventListener p0) throws VetoException;
}
