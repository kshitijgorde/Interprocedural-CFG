// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jmdns;

import java.util.logging.Level;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Hashtable;
import java.util.logging.Logger;

final class DNSOutgoing
{
    private static Logger logger;
    int id;
    int flags;
    private boolean multicast;
    private int numQuestions;
    private int numAnswers;
    private int numAuthorities;
    private int numAdditionals;
    private Hashtable names;
    byte[] data;
    int off;
    int len;
    private LinkedList authorativeAnswers;
    
    DNSOutgoing(final int flags) {
        this(flags, true);
    }
    
    DNSOutgoing(final int flags, final boolean multicast) {
        this.authorativeAnswers = new LinkedList();
        this.flags = flags;
        this.multicast = multicast;
        this.names = new Hashtable();
        this.data = new byte[1460];
        this.off = 12;
    }
    
    void addQuestion(final DNSQuestion rec) throws IOException {
        if (this.numAnswers > 0 || this.numAuthorities > 0 || this.numAdditionals > 0) {
            throw new IllegalStateException("Questions must be added before answers");
        }
        ++this.numQuestions;
        this.writeQuestion(rec);
    }
    
    void addAnswer(final DNSIncoming in, final DNSRecord rec) throws IOException {
        if (this.numAuthorities > 0 || this.numAdditionals > 0) {
            throw new IllegalStateException("Answers must be added before authorities and additionals");
        }
        if (!rec.suppressedBy(in)) {
            this.addAnswer(rec, 0L);
        }
    }
    
    void addAdditionalAnswer(final DNSIncoming in, final DNSRecord rec) throws IOException {
        if (this.off < 1260 && !rec.suppressedBy(in)) {
            this.writeRecord(rec, 0L);
            ++this.numAdditionals;
        }
    }
    
    void addAnswer(final DNSRecord rec, final long now) throws IOException {
        if (this.numAuthorities > 0 || this.numAdditionals > 0) {
            throw new IllegalStateException("Questions must be added before answers");
        }
        if (rec != null && (now == 0L || !rec.isExpired(now))) {
            this.writeRecord(rec, now);
            ++this.numAnswers;
        }
    }
    
    void addAuthorativeAnswer(final DNSRecord rec) throws IOException {
        if (this.numAdditionals > 0) {
            throw new IllegalStateException("Authorative answers must be added before additional answers");
        }
        this.authorativeAnswers.add(rec);
        this.writeRecord(rec, 0L);
        ++this.numAuthorities;
    }
    
    void writeByte(final int value) throws IOException {
        if (this.off >= this.data.length) {
            throw new IOException("buffer full");
        }
        this.data[this.off++] = (byte)value;
    }
    
    void writeBytes(final String str, final int off, final int len) throws IOException {
        for (int i = 0; i < len; ++i) {
            this.writeByte(str.charAt(off + i));
        }
    }
    
    void writeBytes(final byte[] data) throws IOException {
        if (data != null) {
            this.writeBytes(data, 0, data.length);
        }
    }
    
    void writeBytes(final byte[] data, final int off, final int len) throws IOException {
        for (int i = 0; i < len; ++i) {
            this.writeByte(data[off + i]);
        }
    }
    
    void writeShort(final int value) throws IOException {
        this.writeByte(value >> 8);
        this.writeByte(value);
    }
    
    void writeInt(final int value) throws IOException {
        this.writeShort(value >> 16);
        this.writeShort(value);
    }
    
    void writeUTF(final String str, final int off, final int len) throws IOException {
        int utflen = 0;
        for (int i = 0; i < len; ++i) {
            final int ch = str.charAt(off + i);
            if (ch >= 1 && ch <= 127) {
                ++utflen;
            }
            else if (ch > 2047) {
                utflen += 3;
            }
            else {
                utflen += 2;
            }
        }
        this.writeByte(utflen);
        for (int i = 0; i < len; ++i) {
            final int ch = str.charAt(off + i);
            if (ch >= 1 && ch <= 127) {
                this.writeByte(ch);
            }
            else if (ch > 2047) {
                this.writeByte(0xE0 | (ch >> 12 & 0xF));
                this.writeByte(0x80 | (ch >> 6 & 0x3F));
                this.writeByte(0x80 | (ch >> 0 & 0x3F));
            }
            else {
                this.writeByte(0xC0 | (ch >> 6 & 0x1F));
                this.writeByte(0x80 | (ch >> 0 & 0x3F));
            }
        }
    }
    
    void writeName(String name) throws IOException {
        while (true) {
            int n = name.indexOf(46);
            if (n < 0) {
                n = name.length();
            }
            if (n <= 0) {
                this.writeByte(0);
                return;
            }
            final Integer offset = this.names.get(name);
            if (offset != null) {
                final int val = offset;
                if (val > this.off) {
                    DNSOutgoing.logger.log(Level.WARNING, "DNSOutgoing writeName failed val=" + val + " name=" + name);
                }
                this.writeByte(val >> 8 | 0xC0);
                this.writeByte(val);
                return;
            }
            this.names.put(name, new Integer(this.off));
            this.writeUTF(name, 0, n);
            name = name.substring(n);
            if (!name.startsWith(".")) {
                continue;
            }
            name = name.substring(1);
        }
    }
    
    void writeQuestion(final DNSQuestion question) throws IOException {
        this.writeName(question.name);
        this.writeShort(question.type);
        this.writeShort(question.clazz);
    }
    
    void writeRecord(final DNSRecord rec, final long now) throws IOException {
        final int save = this.off;
        try {
            this.writeName(rec.name);
            this.writeShort(rec.type);
            this.writeShort(rec.clazz | ((rec.unique && this.multicast) ? 32768 : 0));
            this.writeInt((now == 0L) ? rec.ttl : rec.getRemainingTTL(now));
            this.writeShort(0);
            final int start = this.off;
            rec.write(this);
            final int len = this.off - start;
            this.data[start - 2] = (byte)(len >> 8);
            this.data[start - 1] = (byte)(len & 0xFF);
        }
        catch (IOException e) {
            this.off = save;
            throw e;
        }
    }
    
    void finish() throws IOException {
        final int save = this.off;
        this.off = 0;
        this.writeShort(this.multicast ? 0 : this.id);
        this.writeShort(this.flags);
        this.writeShort(this.numQuestions);
        this.writeShort(this.numAnswers);
        this.writeShort(this.numAuthorities);
        this.writeShort(this.numAdditionals);
        this.off = save;
    }
    
    boolean isQuery() {
        return (this.flags & 0x8000) == 0x0;
    }
    
    public boolean isEmpty() {
        return this.numQuestions == 0 && this.numAuthorities == 0 && this.numAdditionals == 0 && this.numAnswers == 0;
    }
    
    @Override
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append(this.isQuery() ? "dns[query," : "dns[response,");
        buf.append(':');
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
        buf.append(",\nnames=" + this.names);
        buf.append(",\nauthorativeAnswers=" + this.authorativeAnswers);
        buf.append("]");
        return buf.toString();
    }
    
    static {
        DNSOutgoing.logger = Logger.getLogger(DNSOutgoing.class.toString());
    }
}
