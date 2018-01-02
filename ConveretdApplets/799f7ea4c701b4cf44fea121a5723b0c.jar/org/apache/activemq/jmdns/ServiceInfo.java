// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jmdns;

import java.util.Vector;
import java.util.Enumeration;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.net.InetAddress;
import java.util.Hashtable;
import java.util.TimerTask;
import java.util.logging.Logger;

public class ServiceInfo implements DNSListener
{
    private static Logger logger;
    public static final byte[] NO_VALUE;
    JmDNS dns;
    private DNSState state;
    TimerTask task;
    String type;
    private String name;
    String server;
    int port;
    int weight;
    int priority;
    byte[] text;
    Hashtable props;
    InetAddress addr;
    
    public ServiceInfo(final String type, final String name, final int port, final String text) {
        this(type, name, port, 0, 0, text);
    }
    
    public ServiceInfo(final String type, final String name, final int port, final int weight, final int priority, final String text) {
        this(type, name, port, weight, priority, (byte[])null);
        try {
            final ByteArrayOutputStream out = new ByteArrayOutputStream(text.length());
            this.writeUTF(out, text);
            this.text = out.toByteArray();
        }
        catch (IOException e) {
            throw new RuntimeException("unexpected exception: " + e);
        }
    }
    
    public ServiceInfo(final String type, final String name, final int port, final int weight, final int priority, final Hashtable props) {
        this(type, name, port, weight, priority, new byte[0]);
        if (props != null) {
            try {
                final ByteArrayOutputStream out = new ByteArrayOutputStream(256);
                final Enumeration e = props.keys();
                while (e.hasMoreElements()) {
                    final String key = e.nextElement();
                    final Object val = props.get(key);
                    final ByteArrayOutputStream out2 = new ByteArrayOutputStream(100);
                    this.writeUTF(out2, key);
                    if (val instanceof String) {
                        out2.write(61);
                        this.writeUTF(out2, (String)val);
                    }
                    else if (val instanceof byte[]) {
                        out2.write(61);
                        final byte[] bval = (byte[])val;
                        out2.write(bval, 0, bval.length);
                    }
                    else if (val != ServiceInfo.NO_VALUE) {
                        throw new IllegalArgumentException("invalid property value: " + val);
                    }
                    final byte[] data = out2.toByteArray();
                    out.write(data.length);
                    out.write(data, 0, data.length);
                }
                this.text = out.toByteArray();
            }
            catch (IOException e2) {
                throw new RuntimeException("unexpected exception: " + e2);
            }
        }
    }
    
    public ServiceInfo(final String type, final String name, final int port, final int weight, final int priority, final byte[] text) {
        this.state = DNSState.PROBING_1;
        this.type = type;
        this.name = name;
        this.port = port;
        this.weight = weight;
        this.priority = priority;
        this.text = text;
    }
    
    ServiceInfo(final String type, final String name) {
        this.state = DNSState.PROBING_1;
        if (!type.endsWith(".")) {
            throw new IllegalArgumentException("type must be fully qualified DNS name ending in '.': " + type);
        }
        this.type = type;
        this.name = name;
    }
    
    ServiceInfo(final ServiceInfo info) {
        this.state = DNSState.PROBING_1;
        if (info != null) {
            this.type = info.type;
            this.name = info.name;
            this.port = info.port;
            this.weight = info.weight;
            this.priority = info.priority;
            this.text = info.text;
        }
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getName() {
        return this.name;
    }
    
    void setName(final String name) {
        this.name = name;
    }
    
    public String getQualifiedName() {
        return this.name + "." + this.type;
    }
    
    public String getServer() {
        return this.server;
    }
    
    public String getHostAddress() {
        return (this.addr != null) ? this.addr.getHostAddress() : "";
    }
    
    public InetAddress getAddress() {
        return this.addr;
    }
    
    public InetAddress getInetAddress() {
        return this.addr;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    public int getWeight() {
        return this.weight;
    }
    
    public byte[] getTextBytes() {
        return this.text;
    }
    
    public String getTextString() {
        if (this.text == null || this.text.length == 0 || (this.text.length == 1 && this.text[0] == 0)) {
            return null;
        }
        return this.readUTF(this.text, 0, this.text.length);
    }
    
    public String getURL() {
        return this.getURL("http");
    }
    
    public String getURL(final String protocol) {
        String url = protocol + "://" + this.getAddress() + ":" + this.getPort();
        final String path = this.getPropertyString("path");
        if (path != null) {
            if (path.indexOf("://") >= 0) {
                url = path;
            }
            else {
                url += (path.startsWith("/") ? path : ("/" + path));
            }
        }
        return url;
    }
    
    public synchronized byte[] getPropertyBytes(final String name) {
        return this.getProperties().get(name);
    }
    
    public synchronized String getPropertyString(final String name) {
        final byte[] data = this.getProperties().get(name);
        if (data == null) {
            return null;
        }
        if (data == ServiceInfo.NO_VALUE) {
            return "true";
        }
        return this.readUTF(data, 0, data.length);
    }
    
    public Enumeration getPropertyNames() {
        final Hashtable props = this.getProperties();
        return (props != null) ? props.keys() : new Vector().elements();
    }
    
    void writeUTF(final OutputStream out, final String str) throws IOException {
        for (int i = 0, len = str.length(); i < len; ++i) {
            final int c = str.charAt(i);
            if (c >= 1 && c <= 127) {
                out.write(c);
            }
            else if (c > 2047) {
                out.write(0xE0 | (c >> 12 & 0xF));
                out.write(0x80 | (c >> 6 & 0x3F));
                out.write(0x80 | (c >> 0 & 0x3F));
            }
            else {
                out.write(0xC0 | (c >> 6 & 0x1F));
                out.write(0x80 | (c >> 0 & 0x3F));
            }
        }
    }
    
    String readUTF(final byte[] data, int off, final int len) {
        final StringBuffer buf = new StringBuffer();
        final int end = off + len;
        while (off < end) {
            int ch = data[off++] & 0xFF;
            switch (ch >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7: {
                    break;
                }
                case 12:
                case 13: {
                    if (off >= len) {
                        return null;
                    }
                    ch = ((ch & 0x1F) << 6 | (data[off++] & 0x3F));
                    break;
                }
                case 14: {
                    if (off + 2 >= len) {
                        return null;
                    }
                    ch = ((ch & 0xF) << 12 | (data[off++] & 0x3F) << 6 | (data[off++] & 0x3F));
                    break;
                }
                default: {
                    if (off + 1 >= len) {
                        return null;
                    }
                    ch = ((ch & 0x3F) << 4 | (data[off++] & 0xF));
                    break;
                }
            }
            buf.append((char)ch);
        }
        return buf.toString();
    }
    
    synchronized Hashtable getProperties() {
        if (this.props == null && this.text != null) {
            final Hashtable props = new Hashtable();
            int off = 0;
            while (off < this.text.length) {
                final int len = this.text[off++] & 0xFF;
                if (len == 0 || off + len > this.text.length) {
                    props.clear();
                    break;
                }
                int i;
                for (i = 0; i < len && this.text[off + i] != 61; ++i) {}
                final String name = this.readUTF(this.text, off, i);
                if (name == null) {
                    props.clear();
                    break;
                }
                if (i == len) {
                    props.put(name, ServiceInfo.NO_VALUE);
                }
                else {
                    final byte[] value = new byte[len - ++i];
                    System.arraycopy(this.text, off + i, value, 0, len - i);
                    props.put(name, value);
                    off += len;
                }
            }
            this.props = props;
        }
        return this.props;
    }
    
    @Override
    public void updateRecord(final JmDNS jmdns, final long now, final DNSRecord rec) {
        if (rec != null && !rec.isExpired(now)) {
            switch (rec.type) {
                case 1:
                case 28: {
                    if (rec.name.equals(this.server)) {
                        this.addr = ((DNSRecord.Address)rec).getAddress();
                        break;
                    }
                    break;
                }
                case 33: {
                    if (rec.name.equals(this.getQualifiedName())) {
                        final DNSRecord.Service srv = (DNSRecord.Service)rec;
                        this.server = srv.server;
                        this.port = srv.port;
                        this.weight = srv.weight;
                        this.priority = srv.priority;
                        this.addr = null;
                        this.updateRecord(jmdns, now, (DNSRecord)jmdns.getCache().get(this.server, 1, 1));
                        break;
                    }
                    break;
                }
                case 16: {
                    if (rec.name.equals(this.getQualifiedName())) {
                        final DNSRecord.Text txt = (DNSRecord.Text)rec;
                        this.text = txt.text;
                        break;
                    }
                    break;
                }
            }
            if (this.hasData() && this.dns != null) {
                this.dns.handleServiceResolved(this);
                this.dns = null;
            }
            synchronized (this) {
                this.notifyAll();
            }
        }
    }
    
    boolean hasData() {
        return this.server != null && this.addr != null && this.text != null;
    }
    
    synchronized void advanceState() {
        this.state = this.state.advance();
        this.notifyAll();
    }
    
    synchronized void revertState() {
        this.state = this.state.revert();
        this.notifyAll();
    }
    
    synchronized void cancel() {
        this.state = DNSState.CANCELED;
        this.notifyAll();
    }
    
    DNSState getState() {
        return this.state;
    }
    
    @Override
    public int hashCode() {
        return this.getQualifiedName().hashCode();
    }
    
    @Override
    public boolean equals(final Object obj) {
        return obj instanceof ServiceInfo && this.getQualifiedName().equals(((ServiceInfo)obj).getQualifiedName());
    }
    
    public String getNiceTextString() {
        final StringBuffer buf = new StringBuffer();
        for (int i = 0, len = this.text.length; i < len; ++i) {
            if (i >= 20) {
                buf.append("...");
                break;
            }
            final int ch = this.text[i] & 0xFF;
            if (ch < 32 || ch > 127) {
                buf.append("\\0");
                buf.append(Integer.toString(ch, 8));
            }
            else {
                buf.append((char)ch);
            }
        }
        return buf.toString();
    }
    
    @Override
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("service[");
        buf.append(this.getQualifiedName());
        buf.append(',');
        buf.append(this.getAddress());
        buf.append(':');
        buf.append(this.port);
        buf.append(',');
        buf.append(this.getNiceTextString());
        buf.append(']');
        return buf.toString();
    }
    
    static {
        ServiceInfo.logger = Logger.getLogger(ServiceInfo.class.toString());
        NO_VALUE = new byte[0];
    }
}
