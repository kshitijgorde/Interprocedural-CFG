// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net.stream;

import org.xmodel.net.ILink;
import java.io.IOException;

public class TcpClient extends TcpBase
{
    public A connect(final String s, final int n, final int n2, final ILink.IListener listener) throws IOException {
        return super.connect(s, n, n2, listener);
    }
    
    public boolean reconnect(final A a, final int n) throws IOException {
        return super.reconnect(a, n);
    }
}
