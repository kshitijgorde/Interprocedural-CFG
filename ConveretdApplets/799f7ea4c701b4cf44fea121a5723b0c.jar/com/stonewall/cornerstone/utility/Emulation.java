// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.util.Iterator;
import org.jdom.Content;
import java.util.HashMap;
import org.jdom.Namespace;
import org.xmodel.log.Log;
import org.jdom.Element;
import java.util.concurrent.locks.ReentrantLock;
import java.nio.ByteBuffer;
import java.util.Map;

class Emulation
{
    Simulator simulator;
    final String name;
    final String timeout;
    final Map<String, String> properties;
    final ByteBuffer rdBfr;
    final ByteBuffer wrBfr;
    static final ReentrantLock lock;
    static final Map<String, Element> documents;
    static final Log log;
    static Namespace cnns;
    
    static {
        lock = new ReentrantLock();
        documents = new HashMap<String, Element>();
        log = Log.getLog("DeviceSimulator");
        Emulation.cnns = Namespace.getNamespace("cn", "http://www.stonewallnetworks.com/ns/common");
    }
    
    static Emulation newInstance(Element root) {
        Emulation result = null;
        long tm = System.currentTimeMillis();
        if (root.getAttributeValue("extends") != null) {
            root = extend(root);
        }
        final Element child = (Element)root.getContent(0);
        switch (Type.valueOf(child.getName())) {
            case mml: {
                result = new MMLEmulation(child);
                break;
            }
            case telnet: {
                result = new TelnetEmulation(child);
                break;
            }
            case xmlrpc: {
                result = new RpcEmulation(child);
                break;
            }
        }
        putDocument(result.name, root);
        tm = System.currentTimeMillis() - tm;
        Emulation.log.debug("Emulation [" + result.name + "] created: " + tm + " (ns)");
        return result;
    }
    
    static Emulation getByName(final String name) {
        Emulation.lock.lock();
        try {
            return newInstance(document(name));
        }
        finally {
            Emulation.lock.unlock();
        }
    }
    
    static Element document(final String name) {
        Emulation.lock.lock();
        try {
            final Element root = Emulation.documents.get(name);
            return (root != null) ? ((Element)root.clone()) : null;
        }
        finally {
            Emulation.lock.unlock();
        }
    }
    
    static void putDocument(final String name, final Element root) {
        Emulation.lock.lock();
        try {
            Emulation.documents.put(name, root);
        }
        finally {
            Emulation.lock.unlock();
        }
        Emulation.lock.unlock();
    }
    
    static boolean notDefined(final String emulation) {
        Emulation.lock.lock();
        try {
            return Emulation.documents.get(emulation) == null;
        }
        finally {
            Emulation.lock.unlock();
        }
    }
    
    static Element extend(final Element root) {
        final Element supr = document(root.getAttributeValue("extends"));
        if (supr == null) {
            throw new IllegalArgumentException("Extend: reference not-found");
        }
        final Element child = (Element)root.getContent(0);
        final Element suprChild = (Element)supr.getContent(0);
        if (suprChild.getName() != child.getName()) {
            throw new IllegalArgumentException("May only extend emulations of same type");
        }
        final Element result = (Element)supr.clone();
        final Element resultChild = (Element)result.getContent(0);
        for (final Object o : child.getContent()) {
            final Element e = (Element)o;
            resultChild.addContent((Content)e.clone());
        }
        for (final Object o : root.getChildren("property", Emulation.cnns)) {
            final Element e = (Element)o;
            result.addContent((Content)e.clone());
        }
        result.setAttribute("name", root.getAttributeValue("name"));
        return result;
    }
    
    Emulation(final Element root) {
        this.simulator = null;
        this.properties = new HashMap<String, String>();
        this.rdBfr = ByteBuffer.allocate(10240);
        this.wrBfr = ByteBuffer.allocate(10240);
        this.name = root.getAttributeValue("name");
        this.timeout = root.getChildText("timeout", Emulation.cnns);
        for (final Object o : root.getChildren("property", Emulation.cnns)) {
            final Element e = (Element)o;
            this.properties.put(e.getAttributeValue("name"), e.getText());
        }
    }
    
    void start(final Simulator simulator) {
        this.simulator = simulator;
        simulator.timeout = ((this.timeout == null) ? 0L : (Long.valueOf(this.timeout) * 1000L));
        final String localAddr = simulator.channel.socket().getLocalAddress().getHostAddress();
        this.properties.put("IP", localAddr);
        final String[] octets = localAddr.split("\\.");
        this.properties.put("IP\\[0\\]", octets[0]);
        this.properties.put("IP\\[1\\]", octets[1]);
        this.properties.put("IP\\[2\\]", octets[2]);
        this.properties.put("IP\\[3\\]", octets[3]);
    }
    
    void read() {
        throw new UnsupportedOperationException();
    }
    
    boolean nonblocking() {
        return true;
    }
    
    void write(final String s) {
        final EncodedString ec = new EncodedString(s);
        ec.setProperties(this.properties);
        Emulation.log.info("Replacing variables [" + this.properties + "]");
        this.write(ec.toString().getBytes());
    }
    
    void write(final Reader reader) {
        try {
            final char[] bfr = new char[this.wrBfr.capacity()];
            final PushbackReader pbr = new PushbackReader(reader, bfr.length);
            while (true) {
                int n = pbr.read(bfr);
                if (n == -1) {
                    break;
                }
                final int pos = this.lastMarkerIndex(bfr, n);
                if (pos > 0) {
                    pbr.unread(bfr, pos, n - pos);
                    n = pos;
                }
                this.write(new String(bfr, 0, n));
            }
        }
        catch (Exception e) {
            Emulation.log.error("write-failed", e);
            this.simulator.close();
        }
    }
    
    int lastMarkerIndex(final char[] bfr, final int n) {
        int result = -1;
        for (int i = 0; i < n; ++i) {
            if (bfr[i] == '$') {
                result = i;
                for (int x = i + 1; x < n; ++x) {
                    if (bfr[x] == '}') {
                        i = x;
                        result = -1;
                        break;
                    }
                }
                if (result != -1) {
                    break;
                }
            }
        }
        return result;
    }
    
    void write(final byte[] bytes) {
        Emulation.log.info("Writing [" + new String(bytes) + "]");
        try {
            int n;
            for (int i = 0, remaining = bytes.length; remaining > 0; remaining -= n, i += n) {
                this.wrBfr.clear();
                n = ((remaining > this.wrBfr.capacity()) ? this.wrBfr.capacity() : remaining);
                this.wrBfr.put(bytes, i, n);
                this.wrBfr.flip();
                n = this.simulator.getChannel().write(this.wrBfr);
            }
        }
        catch (IOException e) {
            this.simulator.close();
        }
    }
    
    enum Type
    {
        mml("mml", 0), 
        telnet("telnet", 1), 
        xmlrpc("xmlrpc", 2);
        
        private Type(final String s, final int n) {
        }
    }
}
