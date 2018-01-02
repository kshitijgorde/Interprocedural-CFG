// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jmdns;

import java.util.Iterator;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Logger;

abstract class DNSRecord extends DNSEntry
{
    private static Logger logger;
    int ttl;
    private long created;
    
    DNSRecord(final String name, final int type, final int clazz, final int ttl) {
        super(name, type, clazz);
        this.ttl = ttl;
        this.created = System.currentTimeMillis();
    }
    
    @Override
    public boolean equals(final Object other) {
        return other instanceof DNSRecord && this.sameAs((DNSRecord)other);
    }
    
    boolean sameAs(final DNSRecord other) {
        return super.equals(other) && this.sameValue(other);
    }
    
    abstract boolean sameValue(final DNSRecord p0);
    
    boolean sameType(final DNSRecord other) {
        return this.type == other.type;
    }
    
    abstract boolean handleQuery(final JmDNS p0, final long p1);
    
    abstract boolean handleResponse(final JmDNS p0);
    
    abstract DNSOutgoing addAnswer(final JmDNS p0, final DNSIncoming p1, final InetAddress p2, final int p3, final DNSOutgoing p4) throws IOException;
    
    boolean suppressedBy(final DNSIncoming msg) {
        try {
            int i = msg.numAnswers;
            while (i-- > 0) {
                if (this.suppressedBy(msg.answers.get(i))) {
                    return true;
                }
            }
            return false;
        }
        catch (ArrayIndexOutOfBoundsException e) {
            DNSRecord.logger.log(Level.WARNING, "suppressedBy() message " + msg + " exception ", e);
            return false;
        }
    }
    
    boolean suppressedBy(final DNSRecord other) {
        return this.sameAs(other) && other.ttl > this.ttl / 2;
    }
    
    long getExpirationTime(final int percent) {
        return this.created + percent * this.ttl * 10L;
    }
    
    int getRemainingTTL(final long now) {
        return (int)Math.max(0L, (this.getExpirationTime(100) - now) / 1000L);
    }
    
    boolean isExpired(final long now) {
        return this.getExpirationTime(100) <= now;
    }
    
    boolean isStale(final long now) {
        return this.getExpirationTime(50) <= now;
    }
    
    void resetTTL(final DNSRecord other) {
        this.created = other.created;
        this.ttl = other.ttl;
    }
    
    abstract void write(final DNSOutgoing p0) throws IOException;
    
    public String toString(final String other) {
        return this.toString("record", this.ttl + "/" + this.getRemainingTTL(System.currentTimeMillis()) + "," + other);
    }
    
    static {
        DNSRecord.logger = Logger.getLogger(DNSRecord.class.toString());
    }
    
    static class Address extends DNSRecord
    {
        private static Logger logger;
        InetAddress addr;
        
        Address(final String name, final int type, final int clazz, final int ttl, final InetAddress addr) {
            super(name, type, clazz, ttl);
            this.addr = addr;
        }
        
        Address(final String name, final int type, final int clazz, final int ttl, final byte[] rawAddress) {
            super(name, type, clazz, ttl);
            try {
                this.addr = InetAddress.getByAddress(rawAddress);
            }
            catch (UnknownHostException exception) {
                Address.logger.log(Level.WARNING, "Address() exception ", exception);
            }
        }
        
        @Override
        void write(final DNSOutgoing out) throws IOException {
            if (this.addr != null) {
                byte[] buffer = this.addr.getAddress();
                if (1 == this.type) {
                    if (!(this.addr instanceof Inet4Address)) {
                        final byte[] tempbuffer = buffer;
                        buffer = new byte[4];
                        System.arraycopy(tempbuffer, 12, buffer, 0, 4);
                    }
                }
                else if (this.addr instanceof Inet4Address) {
                    final byte[] tempbuffer = buffer;
                    buffer = new byte[16];
                    for (int i = 0; i < 16; ++i) {
                        if (i < 11) {
                            buffer[i] = tempbuffer[i - 12];
                        }
                        else {
                            buffer[i] = 0;
                        }
                    }
                }
                final int length = buffer.length;
                out.writeBytes(buffer, 0, length);
            }
        }
        
        boolean same(final DNSRecord other) {
            return this.sameName(other) && this.sameValue(other);
        }
        
        boolean sameName(final DNSRecord other) {
            return this.name.equalsIgnoreCase(((Address)other).name);
        }
        
        @Override
        boolean sameValue(final DNSRecord other) {
            return this.addr.equals(((Address)other).getAddress());
        }
        
        InetAddress getAddress() {
            return this.addr;
        }
        
        private byte[] toByteArray() {
            try {
                final ByteArrayOutputStream bout = new ByteArrayOutputStream();
                final DataOutputStream dout = new DataOutputStream(bout);
                dout.write(this.name.getBytes("UTF8"));
                dout.writeShort(this.type);
                dout.writeShort(this.clazz);
                final byte[] buffer = this.addr.getAddress();
                for (int i = 0; i < buffer.length; ++i) {
                    dout.writeByte(buffer[i]);
                }
                dout.close();
                return bout.toByteArray();
            }
            catch (IOException e) {
                throw new InternalError();
            }
        }
        
        private int lexCompare(final Address that) {
            final byte[] thisBytes = this.toByteArray();
            final byte[] thatBytes = that.toByteArray();
            for (int i = 0, n = Math.min(thisBytes.length, thatBytes.length); i < n; ++i) {
                if (thisBytes[i] > thatBytes[i]) {
                    return 1;
                }
                if (thisBytes[i] < thatBytes[i]) {
                    return -1;
                }
            }
            return thisBytes.length - thatBytes.length;
        }
        
        @Override
        boolean handleQuery(final JmDNS dns, final long expirationTime) {
            final Address dnsAddress = dns.getLocalHost().getDNSAddressRecord(this);
            if (dnsAddress != null && dnsAddress.sameType(this) && dnsAddress.sameName(this) && !dnsAddress.sameValue(this)) {
                Address.logger.finer("handleQuery() Conflicting probe detected. dns state " + dns.getState() + " lex compare " + this.lexCompare(dnsAddress));
                if (dns.getState().isProbing() && this.lexCompare(dnsAddress) >= 0) {
                    dns.getLocalHost().incrementHostName();
                    dns.getCache().clear();
                    for (final ServiceInfo info : dns.services.values()) {
                        info.revertState();
                    }
                }
                dns.revertState();
                return true;
            }
            return false;
        }
        
        @Override
        boolean handleResponse(final JmDNS dns) {
            final Address dnsAddress = dns.getLocalHost().getDNSAddressRecord(this);
            if (dnsAddress != null && dnsAddress.sameType(this) && dnsAddress.sameName(this) && !dnsAddress.sameValue(this)) {
                Address.logger.finer("handleResponse() Denial detected");
                if (dns.getState().isProbing()) {
                    dns.getLocalHost().incrementHostName();
                    dns.getCache().clear();
                    for (final ServiceInfo info : dns.services.values()) {
                        info.revertState();
                    }
                }
                dns.revertState();
                return true;
            }
            return false;
        }
        
        @Override
        DNSOutgoing addAnswer(final JmDNS dns, final DNSIncoming in, final InetAddress addr, final int port, final DNSOutgoing out) throws IOException {
            return out;
        }
        
        @Override
        public String toString() {
            return this.toString(" address '" + ((this.addr != null) ? this.addr.getHostAddress() : "null") + "'");
        }
        
        static {
            Address.logger = Logger.getLogger(Address.class.toString());
        }
    }
    
    static class Pointer extends DNSRecord
    {
        private static Logger logger;
        String alias;
        
        Pointer(final String name, final int type, final int clazz, final int ttl, final String alias) {
            super(name, type, clazz, ttl);
            this.alias = alias;
        }
        
        @Override
        void write(final DNSOutgoing out) throws IOException {
            out.writeName(this.alias);
        }
        
        @Override
        boolean sameValue(final DNSRecord other) {
            return this.alias.equals(((Pointer)other).alias);
        }
        
        @Override
        boolean handleQuery(final JmDNS dns, final long expirationTime) {
            return false;
        }
        
        @Override
        boolean handleResponse(final JmDNS dns) {
            return false;
        }
        
        String getAlias() {
            return this.alias;
        }
        
        @Override
        DNSOutgoing addAnswer(final JmDNS dns, final DNSIncoming in, final InetAddress addr, final int port, final DNSOutgoing out) throws IOException {
            return out;
        }
        
        @Override
        public String toString() {
            return this.toString(this.alias);
        }
        
        static {
            Pointer.logger = Logger.getLogger(Pointer.class.toString());
        }
    }
    
    static class Text extends DNSRecord
    {
        private static Logger logger;
        byte[] text;
        
        Text(final String name, final int type, final int clazz, final int ttl, final byte[] text) {
            super(name, type, clazz, ttl);
            this.text = text;
        }
        
        @Override
        void write(final DNSOutgoing out) throws IOException {
            out.writeBytes(this.text, 0, this.text.length);
        }
        
        @Override
        boolean sameValue(final DNSRecord other) {
            final Text txt = (Text)other;
            if (txt.text.length != this.text.length) {
                return false;
            }
            int i = this.text.length;
            while (i-- > 0) {
                if (txt.text[i] != this.text[i]) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        boolean handleQuery(final JmDNS dns, final long expirationTime) {
            return false;
        }
        
        @Override
        boolean handleResponse(final JmDNS dns) {
            return false;
        }
        
        @Override
        DNSOutgoing addAnswer(final JmDNS dns, final DNSIncoming in, final InetAddress addr, final int port, final DNSOutgoing out) throws IOException {
            return out;
        }
        
        @Override
        public String toString() {
            return this.toString((this.text.length > 10) ? (new String(this.text, 0, 7) + "...") : new String(this.text));
        }
        
        static {
            Text.logger = Logger.getLogger(Text.class.toString());
        }
    }
    
    static class Service extends DNSRecord
    {
        private static Logger logger;
        int priority;
        int weight;
        int port;
        String server;
        
        Service(final String name, final int type, final int clazz, final int ttl, final int priority, final int weight, final int port, final String server) {
            super(name, type, clazz, ttl);
            this.priority = priority;
            this.weight = weight;
            this.port = port;
            this.server = server;
        }
        
        @Override
        void write(final DNSOutgoing out) throws IOException {
            out.writeShort(this.priority);
            out.writeShort(this.weight);
            out.writeShort(this.port);
            out.writeName(this.server);
        }
        
        private byte[] toByteArray() {
            try {
                final ByteArrayOutputStream bout = new ByteArrayOutputStream();
                final DataOutputStream dout = new DataOutputStream(bout);
                dout.write(this.name.getBytes("UTF8"));
                dout.writeShort(this.type);
                dout.writeShort(this.clazz);
                dout.writeShort(this.priority);
                dout.writeShort(this.weight);
                dout.writeShort(this.port);
                dout.write(this.server.getBytes("UTF8"));
                dout.close();
                return bout.toByteArray();
            }
            catch (IOException e) {
                throw new InternalError();
            }
        }
        
        private int lexCompare(final Service that) {
            final byte[] thisBytes = this.toByteArray();
            final byte[] thatBytes = that.toByteArray();
            for (int i = 0, n = Math.min(thisBytes.length, thatBytes.length); i < n; ++i) {
                if (thisBytes[i] > thatBytes[i]) {
                    return 1;
                }
                if (thisBytes[i] < thatBytes[i]) {
                    return -1;
                }
            }
            return thisBytes.length - thatBytes.length;
        }
        
        @Override
        boolean sameValue(final DNSRecord other) {
            final Service s = (Service)other;
            return this.priority == s.priority && this.weight == s.weight && this.port == s.port && this.server.equals(s.server);
        }
        
        @Override
        boolean handleQuery(final JmDNS dns, final long expirationTime) {
            final ServiceInfo info = dns.services.get(this.name.toLowerCase());
            if (info != null && (this.port != info.port || !this.server.equalsIgnoreCase(dns.getLocalHost().getName()))) {
                Service.logger.finer("handleQuery() Conflicting probe detected");
                if (info.getState().isProbing() && this.lexCompare(new Service(info.getQualifiedName(), 33, 32769, 3600, info.priority, info.weight, info.port, dns.getLocalHost().getName())) >= 0) {
                    final String oldName = info.getQualifiedName().toLowerCase();
                    info.setName(dns.incrementName(info.getName()));
                    dns.services.remove(oldName);
                    dns.services.put(info.getQualifiedName().toLowerCase(), info);
                    Service.logger.finer("handleQuery() Lost tie break: new unique name chosen:" + info.getName());
                }
                info.revertState();
                return true;
            }
            return false;
        }
        
        @Override
        boolean handleResponse(final JmDNS dns) {
            final ServiceInfo info = dns.services.get(this.name.toLowerCase());
            if (info != null && (this.port != info.port || !this.server.equalsIgnoreCase(dns.getLocalHost().getName()))) {
                Service.logger.finer("handleResponse() Denial detected");
                if (info.getState().isProbing()) {
                    final String oldName = info.getQualifiedName().toLowerCase();
                    info.setName(dns.incrementName(info.getName()));
                    dns.services.remove(oldName);
                    dns.services.put(info.getQualifiedName().toLowerCase(), info);
                    Service.logger.finer("handleResponse() New unique name chose:" + info.getName());
                }
                info.revertState();
                return true;
            }
            return false;
        }
        
        @Override
        DNSOutgoing addAnswer(final JmDNS dns, final DNSIncoming in, final InetAddress addr, final int port, final DNSOutgoing out) throws IOException {
            final ServiceInfo info = dns.services.get(this.name.toLowerCase());
            if (info != null && this.port == info.port != this.server.equals(dns.getLocalHost().getName())) {
                return dns.addAnswer(in, addr, port, out, new Service(info.getQualifiedName(), 33, 32769, 3600, info.priority, info.weight, info.port, dns.getLocalHost().getName()));
            }
            return out;
        }
        
        @Override
        public String toString() {
            return this.toString(this.server + ":" + this.port);
        }
        
        static {
            Service.logger = Logger.getLogger(Service.class.toString());
        }
    }
}
