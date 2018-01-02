// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.data;

import org.apache.activemq.kaha.StoreLocation;

public interface RedoListener
{
    void onRedoItem(final StoreLocation p0, final Object p1) throws Exception;
}
