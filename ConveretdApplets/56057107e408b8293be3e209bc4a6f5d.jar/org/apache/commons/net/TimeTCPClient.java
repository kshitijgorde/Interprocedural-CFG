// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.net;

import java.util.Date;
import java.io.IOException;
import java.io.DataInputStream;

public final class TimeTCPClient extends SocketClient
{
    public static final int DEFAULT_PORT = 37;
    public static final long SECONDS_1900_TO_1970 = 2208988800L;
    
    public TimeTCPClient() {
        this.setDefaultPort(37);
    }
    
    public long getTime() throws IOException {
        final DataInputStream input = new DataInputStream(this._input_);
        return input.readInt() & 0xFFFFFFFFL;
    }
    
    public Date getDate() throws IOException {
        return new Date((this.getTime() - 2208988800L) * 1000L);
    }
}
