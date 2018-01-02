// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom3;

import org.w3c.dom.Node;

public interface UserDataHandler
{
    public static final short NODE_CLONED = 1;
    public static final short NODE_IMPORTED = 2;
    public static final short NODE_DELETED = 3;
    public static final short NODE_RENAMED = 4;
    
    void handle(final short p0, final String p1, final Object p2, final Node p3, final Node p4);
}
