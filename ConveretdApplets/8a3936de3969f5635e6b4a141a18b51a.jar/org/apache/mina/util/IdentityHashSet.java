// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.util;

import java.util.Map;
import java.util.IdentityHashMap;

public final class IdentityHashSet<E> extends MapBackedSet<E>
{
    private static final long serialVersionUID = 6948202189467167147L;
    
    public IdentityHashSet() {
        super(new IdentityHashMap());
    }
}
