// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.session;

import org.apache.mina.core.write.WriteRequestQueue;

public class IoSessionDataStructureFactory
{
    public IoSessionAttributeMap getAttributeMap$c34fc7f() throws Exception {
        return new IoSessionAttributeMap();
    }
    
    public WriteRequestQueue getWriteRequestQueue$709e179() throws Exception {
        return new DefaultIoSessionDataStructureFactory$DefaultWriteRequestQueue();
    }
}
