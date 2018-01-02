// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;

public interface SSHPdu
{
    void writeTo(final OutputStream p0) throws IOException;
    
    void readFrom(final InputStream p0) throws IOException;
    
    SSHPdu createPdu() throws IOException;
    
    byte[] rawData();
    
    void rawSetData(final byte[] p0);
    
    int rawOffset();
    
    int rawSize();
    
    void rawAdjustSize(final int p0);
}
