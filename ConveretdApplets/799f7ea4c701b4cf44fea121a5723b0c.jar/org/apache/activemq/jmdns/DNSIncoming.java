// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jmdns;

import java.util.Collection;
import java.util.Iterator;
import java.io.IOException;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.net.DatagramPacket;
import java.util.logging.Logger;

final class DNSIncoming
{
    private static Logger logger;
    private DatagramPacket packet;
    private int off;
    private int len;
    private byte[] data;
    int id;
    private int flags;
    private int numQuestions;
    int numAnswers;
    private int numAuthorities;
    private int numAdditionals;
    private long receivedTime;
    List questions;
    List answers;
    
    DNSIncoming(final DatagramPacket packet) throws IOException {
        this.packet = packet;
        this.data = packet.getData();
        this.len = packet.getLength();
        this.off = packet.getOffset();
        this.questions = Collections.EMPTY_LIST;
        this.answers = Collections.EMPTY_LIST;
        this.receivedTime = System.currentTimeMillis();
        try {
            this.id = this.readUnsignedShort();
            this.flags = this.readUnsignedShort();
            this.numQuestions = this.readUnsignedShort();
            this.numAnswers = this.readUnsignedShort();
            this.numAuthorities = this.readUnsignedShort();
            this.numAdditionals = this.readUnsignedShort();
            if (this.numQuestions > 0) {
                this.questions = Collections.synchronizedList(new ArrayList<Object>(this.numQuestions));
                for (int i = 0; i < this.numQuestions; ++i) {
                    final DNSQuestion question = new DNSQuestion(this.readName(), this.readUnsignedShort(), this.readUnsignedShort());
                    this.questions.add(question);
                }
            }
            final int n = this.numAnswers + this.numAuthorities + this.numAdditionals;
            if (n > 0) {
                this.answers = Collections.synchronizedList(new ArrayList<Object>(n));
                for (int j = 0; j < n; ++j) {
                    final String domain = this.readName();
                    final int type = this.readUnsignedShort();
                    final int clazz = this.readUnsignedShort();
                    final int ttl = this.readInt();
                    final int len = this.readUnsignedShort();
                    final int end = this.off + len;
                    DNSRecord rec = null;
                    switch (type) {
                        case 1:
                        case 28: {
                            rec = new DNSRecord.Address(domain, type, clazz, ttl, this.readBytes(this.off, len));
                            break;
                        }
                        case 5:
                        case 12: {
                            rec = new DNSRecord.Pointer(domain, type, clazz, ttl, this.readName());
                            break;
                        }
                        case 16: {
                            rec = new DNSRecord.Text(domain, type, clazz, ttl, this.readBytes(this.off, len));
                            break;
                        }
                        case 33: {
                            rec = new DNSRecord.Service(domain, type, clazz, ttl, this.readUnsignedShort(), this.readUnsignedShort(), this.readUnsignedShort(), this.readName());
                            break;
                        }
                        case 13: {
                            break;
                        }
                        default: {
                            DNSIncoming.logger.finer("DNSIncoming() unknown type:" + type);
                            break;
                        }
                    }
                    if (rec != null) {
                        this.answers.add(rec);
                    }
                    else if (this.answers.size() < this.numAnswers) {
                        --this.numAnswers;
                    }
                    else if (this.answers.size() < this.numAnswers + this.numAuthorities) {
                        --this.numAuthorities;
                    }
                    else if (this.answers.size() < this.numAnswers + this.numAuthorities + this.numAdditionals) {
                        --this.numAdditionals;
                    }
                    this.off = end;
                }
            }
        }
        catch (IOException e) {
            DNSIncoming.logger.log(Level.WARNING, "DNSIncoming() dump " + this.print(true) + "\n exception ", e);
            throw e;
        }
    }
    
    boolean isQuery() {
        return (this.flags & 0x8000) == 0x0;
    }
    
    boolean isTruncated() {
        return (this.flags & 0x200) != 0x0;
    }
    
    boolean isResponse() {
        return (this.flags & 0x8000) == 0x8000;
    }
    
    private int get(final int off) throws IOException {
        if (off < 0 || off >= this.len) {
            throw new IOException("parser error: offset=" + off);
        }
        return this.data[off] & 0xFF;
    }
    
    private int readUnsignedShort() throws IOException {
        return (this.get(this.off++) << 8) + this.get(this.off++);
    }
    
    private int readInt() throws IOException {
        return (this.readUnsignedShort() << 16) + this.readUnsignedShort();
    }
    
    private byte[] readBytes(final int off, final int len) throws IOException {
        final byte[] bytes = new byte[len];
        System.arraycopy(this.data, off, bytes, 0, len);
        return bytes;
    }
    
    private void readUTF(final StringBuffer buf, int off, final int len) throws IOException {
        final int end = off + len;
        while (off < end) {
            int ch = this.get(off++);
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
                    ch = ((ch & 0x1F) << 6 | (this.get(off++) & 0x3F));
                    break;
                }
                case 14: {
                    ch = ((ch & 0xF) << 12 | (this.get(off++) & 0x3F) << 6 | (this.get(off++) & 0x3F));
                    break;
                }
                default: {
                    ch = ((ch & 0x3F) << 4 | (this.get(off++) & 0xF));
                    break;
                }
            }
            buf.append((char)ch);
        }
    }
    
    private String readName() throws IOException {
        final StringBuffer buf = new StringBuffer();
        int off = this.off;
        int next = -1;
        int first = off;
        while (true) {
            final int len = this.get(off++);
            if (len == 0) {
                this.off = ((next >= 0) ? next : off);
                return buf.toString();
            }
            switch (len & 0xC0) {
                case 0: {
                    this.readUTF(buf, off, len);
                    off += len;
                    buf.append('.');
                    continue;
                }
                case 192: {
                    if (next < 0) {
                        next = off + 1;
                    }
                    off = ((len & 0x3F) << 8 | this.get(off++));
                    if (off >= first) {
                        throw new IOException("bad domain name: possible circular name detected");
                    }
                    first = off;
                    continue;
                }
                default: {
                    throw new IOException("bad domain name: '" + (Object)buf + "' at " + off);
                }
            }
        }
    }
    
    String print(final boolean dump) {
        final StringBuffer buf = new StringBuffer();
        buf.append(this.toString() + "\n");
        final Iterator iterator = this.questions.iterator();
        while (iterator.hasNext()) {
            buf.append("    ques:" + iterator.next() + "\n");
        }
        int count = 0;
        final Iterator iterator2 = this.answers.iterator();
        while (iterator2.hasNext()) {
            if (count < this.numAnswers) {
                buf.append("    answ:");
            }
            else if (count < this.numAnswers + this.numAuthorities) {
                buf.append("    auth:");
            }
            else {
                buf.append("    addi:");
            }
            buf.append(iterator2.next() + "\n");
            ++count;
        }
        if (dump) {
            for (int off = 0, len = this.packet.getLength(); off < len; off += 32) {
                final int n = Math.min(32, len - off);
                if (off < 10) {
                    buf.append(' ');
                }
                if (off < 100) {
                    buf.append(' ');
                }
                buf.append(off);
                buf.append(':');
                for (int i = 0; i < n; ++i) {
                    if (i % 8 == 0) {
                        buf.append(' ');
                    }
                    buf.append(Integer.toHexString((this.data[off + i] & 0xF0) >> 4));
                    buf.append(Integer.toHexString((this.data[off + i] & 0xF) >> 0));
                }
                buf.append("\n");
                buf.append("    ");
                for (int i = 0; i < n; ++i) {
                    if (i % 8 == 0) {
                        buf.append(' ');
                    }
                    buf.append(' ');
                    final int ch = this.data[off + i] & 0xFF;
                    buf.append((ch > 32 && ch < 127) ? ((char)ch) : '.');
                }
                buf.append("\n");
                if (off + 32 >= 256) {
                    buf.append("....\n");
                    break;
                }
            }
        }
        return buf.toString();
    }
    
    @Override
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append(this.isQuery() ? "dns[query," : "dns[response,");
        if (this.packet.getAddress() != null) {
            buf.append(this.packet.getAddress().getHostAddress());
        }
        buf.append(':');
        buf.append(this.packet.getPort());
        buf.append(",len=");
        buf.append(this.packet.getLength());
        buf.append(",id=0x");
        buf.append(Integer.toHexString(this.id));
        if (this.flags != 0) {
            buf.append(",flags=0x");
            buf.append(Integer.toHexString(this.flags));
            if ((this.flags & 0x8000) != 0x0) {
                buf.append(":r");
            }
            if ((this.flags & 0x400) != 0x0) {
                buf.append(":aa");
            }
            if ((this.flags & 0x200) != 0x0) {
                buf.append(":tc");
            }
        }
        if (this.numQuestions > 0) {
            buf.append(",questions=");
            buf.append(this.numQuestions);
        }
        if (this.numAnswers > 0) {
            buf.append(",answers=");
            buf.append(this.numAnswers);
        }
        if (this.numAuthorities > 0) {
            buf.append(",authorities=");
            buf.append(this.numAuthorities);
        }
        if (this.numAdditionals > 0) {
            buf.append(",additionals=");
            buf.append(this.numAdditionals);
        }
        buf.append("]");
        return buf.toString();
    }
    
    void append(final DNSIncoming that) {
        if (this.isQuery() && this.isTruncated() && that.isQuery()) {
            this.questions.addAll(that.questions);
            this.numQuestions += that.numQuestions;
            if (Collections.EMPTY_LIST.equals(this.answers)) {
                this.answers = Collections.synchronizedList(new ArrayList<Object>());
            }
            if (that.numAnswers > 0) {
                this.answers.addAll(this.numAnswers, that.answers.subList(0, that.numAnswers));
                this.numAnswers += that.numAnswers;
            }
            if (that.numAuthorities > 0) {
                this.answers.addAll(this.numAnswers + this.numAuthorities, that.answers.subList(that.numAnswers, that.numAnswers + that.numAuthorities));
                this.numAuthorities += that.numAuthorities;
            }
            if (that.numAdditionals > 0) {
                this.answers.addAll(that.answers.subList(that.numAnswers + that.numAuthorities, that.numAnswers + that.numAuthorities + that.numAdditionals));
                this.numAdditionals += that.numAdditionals;
            }
            return;
        }
        throw new IllegalArgumentException();
    }
    
    int elapseSinceArrival() {
        return (int)(System.currentTimeMillis() - this.receivedTime);
    }
    
    static {
        DNSIncoming.logger = Logger.getLogger(DNSIncoming.class.toString());
    }
}
