// 
// Decompiled by Procyon v0.5.30
// 

package anon.transport.connection.util;

import java.io.IOException;
import java.io.OutputStream;

public class ClosedOutputStream extends OutputStream
{
    private final boolean m_multibleClose;
    
    public static OutputStream getNotCloseable() {
        return Holder.neverCloseable;
    }
    
    public static OutputStream getMultibleCloseable() {
        return Holder.multibleCloseable;
    }
    
    private ClosedOutputStream() {
        this.m_multibleClose = false;
    }
    
    private ClosedOutputStream(final boolean multibleClose) {
        this.m_multibleClose = multibleClose;
    }
    
    public void write(final int n) throws IOException {
        throw new IOException("OutputStream is closed");
    }
    
    public void close() throws IOException {
        if (this.m_multibleClose) {
            return;
        }
        throw new IOException("InputStream allready closed");
    }
    
    private static class Holder
    {
        private static OutputStream neverCloseable;
        private static OutputStream multibleCloseable;
        
        static {
            Holder.neverCloseable = new ClosedOutputStream(false, null);
            Holder.multibleCloseable = new ClosedOutputStream(true, null);
        }
    }
}
