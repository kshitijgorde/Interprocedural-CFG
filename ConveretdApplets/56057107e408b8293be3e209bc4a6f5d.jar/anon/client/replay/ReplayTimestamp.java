// 
// Decompiled by Procyon v0.5.30
// 

package anon.client.replay;

import java.io.IOException;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;

public class ReplayTimestamp
{
    private static final long SECONDS_PER_INTERVAL = 600L;
    private String m_mixId;
    private long m_replayReferenceTime;
    
    public ReplayTimestamp(final String mixId, final int n, final int n2) {
        this.m_mixId = mixId;
        this.m_replayReferenceTime = System.currentTimeMillis() - (n * 600L + n2) * 1000L;
    }
    
    public String getMixId() {
        return this.m_mixId;
    }
    
    public byte[] getCurrentTimestamp() {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeShort((int)((System.currentTimeMillis() - this.m_replayReferenceTime) / 600000L));
            dataOutputStream.flush();
        }
        catch (IOException ex) {}
        return byteArrayOutputStream.toByteArray();
    }
}
