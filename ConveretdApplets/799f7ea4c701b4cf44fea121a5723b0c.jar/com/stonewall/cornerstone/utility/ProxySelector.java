// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.Collections;
import java.net.Proxy;
import java.util.List;
import java.net.URI;

public class ProxySelector extends java.net.ProxySelector
{
    @Override
    public List<Proxy> select(final URI uri) {
        return Collections.singletonList(Proxy.NO_PROXY);
    }
    
    @Override
    public void connectFailed(final URI uri, final SocketAddress sa, final IOException ex) {
    }
}
