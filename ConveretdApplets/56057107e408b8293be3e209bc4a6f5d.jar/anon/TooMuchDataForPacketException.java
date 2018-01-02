// 
// Decompiled by Procyon v0.5.30
// 

package anon;

import java.io.IOException;

public class TooMuchDataForPacketException extends IOException
{
    private int m_bytesSent;
    
    public TooMuchDataForPacketException(final int bytesSent) {
        super("ToMuchDataForPacketException: Supplied data doesn't fit in one single packet.");
        this.m_bytesSent = bytesSent;
    }
    
    public int getBytesSent() {
        return this.m_bytesSent;
    }
}
