// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.net.SocketAddress;
import java.io.IOException;
import org.xmodel.log.Log;
import java.nio.channels.SocketChannel;

class Simulator extends Thread
{
    boolean run;
    Emulation emulation;
    long timeout;
    long lastRead;
    final DeviceSimulator container;
    final SocketChannel channel;
    static final Log log;
    
    static {
        log = Log.getLog("DeviceSimulator");
    }
    
    Simulator(final DeviceSimulator container, final SocketChannel channel) {
        super(link(channel));
        this.run = true;
        this.timeout = 0L;
        this.lastRead = System.currentTimeMillis();
        this.container = container;
        this.channel = channel;
    }
    
    void start(final Emulation e) throws IOException {
        this.emulation = e;
        this.start();
    }
    
    @Override
    public void run() {
        this.emulation.start(this);
        while (this.run) {
            this.read();
        }
    }
    
    void read() {
        this.lastRead = System.currentTimeMillis();
        Simulator.log.debug("Data ready on: " + this.link());
        this.emulation.read();
    }
    
    SocketChannel getChannel() {
        return this.channel;
    }
    
    void close() {
        try {
            Simulator.log.info("closed:" + this.link());
            this.container.closed(this);
            this.channel.close();
            this.run = false;
        }
        catch (Exception ex) {}
    }
    
    String link() {
        return link(this.channel);
    }
    
    static String link(final SocketChannel c) {
        final SocketAddress addr = c.socket().getLocalSocketAddress();
        return "[" + addr + "]";
    }
}
