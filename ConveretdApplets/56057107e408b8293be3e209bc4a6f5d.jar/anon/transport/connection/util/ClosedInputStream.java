// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.connection.util;

import java.io.IOException;
import java.io.InputStream;

public class ClosedInputStream extends InputStream
{
    private final boolean m_multibleClose;
    
    public static InputStream getNotCloseable() {
        return Holder.neverCloseable;
    }
    
    public static InputStream getMultibleCloseable() {
        return Holder.multibleCloseable;
    }
    
    private ClosedInputStream() {
        this.m_multibleClose = false;
    }
    
    private ClosedInputStream(final boolean multibleClose) {
        this.m_multibleClose = multibleClose;
    }
    
    public int read() throws IOException {
        throw new IOException("InputStream is closed");
    }
    
    public void close() throws IOException {
        if (this.m_multibleClose) {
            return;
        }
        throw new IOException("InputStream already closed");
    }
    
    private static class Holder
    {
        private static InputStream neverCloseable;
        private static InputStream multibleCloseable;
        
        static {
            Holder.neverCloseable = new ClosedInputStream(false, null);
            Holder.multibleCloseable = new ClosedInputStream(true, null);
        }
    }
}
