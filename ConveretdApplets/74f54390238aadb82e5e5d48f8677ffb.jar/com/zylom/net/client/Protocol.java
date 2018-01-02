// 
// Decompiled by Procyon v0.5.30
// 

package com.zylom.net.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;

public abstract class Protocol
{
    abstract void open(final Connection p0, final Location p1, final Socket p2, final InputStream p3, final OutputStream p4) throws IOException;
    
    abstract void close(final Connection p0);
    
    abstract void sendData(final Connection p0, final PDU p1) throws IOException;
}
