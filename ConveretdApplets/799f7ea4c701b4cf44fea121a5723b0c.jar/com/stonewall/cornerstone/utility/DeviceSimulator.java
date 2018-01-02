// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.net.Socket;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.channels.ServerSocketChannel;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import java.io.File;
import java.util.Iterator;
import java.nio.channels.SelectionKey;
import java.util.HashMap;
import org.xmodel.log.Log;
import java.util.concurrent.locks.ReentrantLock;
import java.nio.channels.Channel;
import java.util.Map;
import java.nio.channels.Selector;
import org.jdom.Namespace;

public class DeviceSimulator
{
    static Namespace cnns;
    final Selector selector;
    final Map<Channel, Listener> listeners;
    final Map<Channel, Simulator> simulators;
    final Timer timer;
    final ReentrantLock lock;
    static final Log log;
    
    static {
        DeviceSimulator.cnns = Namespace.getNamespace("cn", "http://www.stonewallnetworks.com/ns/common");
        log = Log.getLog(DeviceSimulator.class);
    }
    
    public static void main(final String[] args) {
        try {
            DeviceSimulator.log.info("Device Simulator 1.2.0 (d) 05-11-2006 08:57:00 EDT");
            final DeviceSimulator simulator = new DeviceSimulator();
            simulator.start();
        }
        catch (Exception e) {
            DeviceSimulator.log.error("Init", e);
        }
    }
    
    public DeviceSimulator() throws Exception {
        this.listeners = new HashMap<Channel, Listener>();
        this.simulators = new HashMap<Channel, Simulator>();
        this.timer = new Timer();
        this.lock = new ReentrantLock();
        this.selector = Selector.open();
        this.build();
    }
    
    public void start() {
        DeviceSimulator.log.info("Simulator Started");
        this.timer.start();
    Label_0015_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        this.selector.select();
                        final Iterator<SelectionKey> itr = this.selector.selectedKeys().iterator();
                        while (itr.hasNext()) {
                            final SelectionKey k = itr.next();
                            itr.remove();
                            if (!k.isValid()) {
                                continue Label_0015_Outer;
                            }
                            if (!k.isAcceptable()) {
                                continue Label_0015_Outer;
                            }
                            this.accept(k.channel());
                        }
                    }
                }
                catch (Exception e) {
                    DeviceSimulator.log.error("Start Failed", e);
                    continue Label_0015_Outer;
                }
                continue;
            }
        }
    }
    
    void build() throws Exception {
        final SAXBuilder builder = new SAXBuilder();
        final Document d = builder.build(new File(String.valueOf(this.getDir()) + "/simulator.xml"));
        final Element root = d.getRootElement();
        List list = root.getChildren("emulation", DeviceSimulator.cnns);
        for (final Object o : list) {
            Emulation.newInstance((Element)o);
        }
        list = root.getChildren("bind", DeviceSimulator.cnns);
        for (final Object o : list) {
            this.bind((Element)o);
        }
    }
    
    String getDir() throws Exception {
        final String result = System.getProperty("simulator.home");
        if (result == null) {
            throw new Exception("simulator.home not defined");
        }
        return result;
    }
    
    void bind(final Element bind) throws Exception {
        final String addr = bind.getChildText("address", DeviceSimulator.cnns);
        final String port = bind.getChildText("port", DeviceSimulator.cnns);
        final String emulation = bind.getChildText("emulation", DeviceSimulator.cnns);
        if (Emulation.notDefined(emulation)) {
            throw new Exception("Emulation: " + emulation + " not defined.");
        }
        final List<String> addrList = this.addrList(addr);
        if (!addrList.isEmpty()) {
            for (final String a : addrList) {
                this.bind(a, Integer.parseInt(port), emulation);
            }
        }
        else {
            this.bind(addr, Integer.parseInt(port), emulation);
        }
    }
    
    void bind(final List<String> addrs, final int port, final String emulation) throws Exception {
        for (final String addr : addrs) {
            this.bind(addr, port, emulation);
        }
    }
    
    void bind(final String addr, final int port, final String emulation) throws Exception {
        final Listener lnr = new Listener(addr, port);
        lnr.setEmulation(emulation);
        lnr.register(this.selector);
        this.listeners.put(lnr.socket, lnr);
    }
    
    void accept(final Channel c) throws IOException {
        final ServerSocketChannel sc = (ServerSocketChannel)c;
        final SocketChannel client = sc.accept();
        this.setSocketOptions(client);
        final SocketAddress addr = client.socket().getLocalSocketAddress();
        DeviceSimulator.log.info("Connection accepted on: " + addr);
        final Emulation emulation = this.getEmulation(c);
        final Simulator s = new Simulator(this, client);
        this.put(client, s);
        s.start(emulation);
    }
    
    void setSocketOptions(final SocketChannel c) throws IOException {
        final Socket s = c.socket();
        s.setTcpNoDelay(true);
        s.setSoLinger(false, 0);
        s.setSendBufferSize(4096);
        s.setReceiveBufferSize(4096);
    }
    
    List<String> addrList(final String addr) {
        List<String> result = Collections.emptyList();
        try {
            final String[] octets = addr.split("\\.");
            final String[] last = octets[3].split("/");
            final int lastOctet = Integer.parseInt(last[0]);
            final int limit = Integer.parseInt(last[1]);
            result = new ArrayList<String>();
            for (int i = 0; i < limit; ++i) {
                final StringBuilder sb = new StringBuilder();
                sb.append(octets[0]);
                sb.append('.');
                sb.append(octets[1]);
                sb.append('.');
                sb.append(octets[2]);
                sb.append('.');
                sb.append(lastOctet + i);
                result.add(sb.toString());
            }
        }
        catch (Exception ex) {}
        return result;
    }
    
    void read(final Channel c) {
        final Simulator s = this.get(c);
        if (s != null) {
            s.read();
        }
    }
    
    void closed(final Simulator s) {
        this.remove(s);
    }
    
    Emulation getEmulation(final Channel c) {
        final Listener lnr = this.listeners.get(c);
        return Emulation.getByName(lnr.getEmulation());
    }
    
    void put(final Channel c, final Simulator s) {
        this.lock.lock();
        try {
            this.simulators.put(c, s);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    void remove(final Simulator s) {
        this.lock.lock();
        try {
            this.simulators.remove(s.channel);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    Simulator get(final Channel c) {
        this.lock.lock();
        try {
            return this.simulators.get(c);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    void timerHasExpired() {
        final long current = System.currentTimeMillis();
        final List<Simulator> expired = new ArrayList<Simulator>();
        this.lock.lock();
        try {
            for (final Simulator s : this.simulators.values()) {
                if (s.timeout == 0L) {
                    continue;
                }
                if (current - s.lastRead <= s.timeout) {
                    continue;
                }
                expired.add(s);
            }
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        for (final Simulator s : expired) {
            DeviceSimulator.log.info("Timeout Detected on: " + s.link());
            s.close();
        }
    }
    
    class Timer extends Thread
    {
        Timer() {
            super("timer");
        }
        
        @Override
        public void run() {
            while (true) {
                try {
                    while (true) {
                        Thread.sleep(1000L);
                        DeviceSimulator.this.timerHasExpired();
                    }
                }
                catch (Exception e) {
                    DeviceSimulator.log.error(this, e);
                    continue;
                }
                break;
            }
        }
    }
}
