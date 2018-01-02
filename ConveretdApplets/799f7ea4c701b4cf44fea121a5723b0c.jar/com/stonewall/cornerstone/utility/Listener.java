// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.nio.channels.Selector;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.io.IOException;
import org.xmodel.log.Log;
import java.nio.channels.ServerSocketChannel;

class Listener
{
    String emulation;
    final ServerSocketChannel socket;
    static final Log log;
    static final int backlog = 100;
    
    static {
        log = Log.getLog("DeviceSimulator");
    }
    
    Listener(final int port) throws IOException {
        this(null, port);
    }
    
    Listener(final String addr, final int port) throws IOException {
        this.emulation = null;
        Listener.log.info("Bind: " + addr + "/" + port);
        this.socket = ServerSocketChannel.open();
        this.socket.socket().bind(this.getAddr(addr, port), 100);
        this.socket.configureBlocking(false);
    }
    
    void setEmulation(final String name) {
        this.emulation = name;
    }
    
    String getEmulation() {
        return this.emulation;
    }
    
    InetSocketAddress getAddr(final String addr, final int port) {
        if (addr == null) {
            return new InetSocketAddress(port);
        }
        return new InetSocketAddress(addr, port);
    }
    
    void register(final Selector s) throws Exception {
        final String m = "Binding: " + this.socket.socket().getLocalSocketAddress() + " to emulation: " + this.emulation;
        Listener.log.info(m);
        this.socket.register(s, 16);
    }
}
