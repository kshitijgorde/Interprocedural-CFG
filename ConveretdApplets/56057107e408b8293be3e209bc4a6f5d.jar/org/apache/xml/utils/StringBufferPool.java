// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

public class StringBufferPool
{
    private static ObjectPool m_stringBufPool;
    static /* synthetic */ Class class$org$apache$xml$utils$FastStringBuffer;
    
    static {
        StringBufferPool.m_stringBufPool = new ObjectPool((StringBufferPool.class$org$apache$xml$utils$FastStringBuffer != null) ? StringBufferPool.class$org$apache$xml$utils$FastStringBuffer : (StringBufferPool.class$org$apache$xml$utils$FastStringBuffer = class$("org.apache.xml.utils.FastStringBuffer")));
    }
    
    static /* synthetic */ Class class$(final String class$) {
        try {
            return Class.forName(class$);
        }
        catch (ClassNotFoundException forName) {
            throw new NoClassDefFoundError(forName.getMessage());
        }
    }
    
    public static void free(final FastStringBuffer sb) {
        sb.setLength(0);
        StringBufferPool.m_stringBufPool.freeInstance(sb);
    }
    
    public static FastStringBuffer get() {
        return (FastStringBuffer)StringBufferPool.m_stringBufPool.getInstance();
    }
}
