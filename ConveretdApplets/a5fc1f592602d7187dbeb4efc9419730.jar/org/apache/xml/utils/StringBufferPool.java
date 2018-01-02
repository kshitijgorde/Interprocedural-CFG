// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

public class StringBufferPool
{
    private static ObjectPool m_stringBufPool;
    static /* synthetic */ Class class$org$apache$xml$utils$FastStringBuffer;
    
    public static synchronized FastStringBuffer get() {
        return (FastStringBuffer)StringBufferPool.m_stringBufPool.getInstance();
    }
    
    public static synchronized void free(final FastStringBuffer sb) {
        sb.setLength(0);
        StringBufferPool.m_stringBufPool.freeInstance(sb);
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        StringBufferPool.m_stringBufPool = new ObjectPool((StringBufferPool.class$org$apache$xml$utils$FastStringBuffer == null) ? (StringBufferPool.class$org$apache$xml$utils$FastStringBuffer = class$("org.apache.xml.utils.FastStringBuffer")) : StringBufferPool.class$org$apache$xml$utils$FastStringBuffer);
    }
}
