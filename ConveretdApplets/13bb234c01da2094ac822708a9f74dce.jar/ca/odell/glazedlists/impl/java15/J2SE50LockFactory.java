// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.java15;

import ca.odell.glazedlists.util.concurrent.ReadWriteLock;
import ca.odell.glazedlists.util.concurrent.LockFactory;

public class J2SE50LockFactory implements LockFactory
{
    public ReadWriteLock a() {
        return new J2SE50ReadWriteLock();
    }
}
