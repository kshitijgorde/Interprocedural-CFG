// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

import com.daysofwonder.util.j;
import java.io.UTFDataFormatException;
import java.io.EOFException;
import com.daysofwonder.util.t;
import java.io.IOException;
import java.io.DataOutput;
import java.io.DataInput;

public class p implements DataInput, DataOutput
{
    private static final int a;
    private Runnable b;
    protected static final IOException d;
    protected static final IllegalStateException e;
    private static J c;
    protected boolean f;
    protected int g;
    protected int h;
    protected int i;
    protected int j;
    protected w k;
    private byte[] l;
    private boolean m;
    
    private static void b(final String s) {
        t.a("Message", s);
    }
    
    public static w a(final int n, final int n2) {
        if (p.a > 2) {
            b("getPacket(): s" + n + " hs " + n2);
        }
        if (p.c != null) {
            return p.c.a(n, n2);
        }
        return new n(n, n2);
    }
    
    public static w s() {
        if (p.a > 2) {
            b("getPacket(): no size");
        }
        if (p.c != null) {
            return p.c.a();
        }
        return new n();
    }
    
    protected p(final int g, final int h) {
        this.k = null;
        this.l = null;
        if (p.a > 2) {
            b("ct(): " + g + " ds " + h);
        }
        this.k = a(h, 8);
        if (this.k == null) {
            this.k = new n(h, 8);
        }
        this.h = h;
        this.i = 8;
        this.f = false;
        this.g = g;
    }
    
    protected p() {
        this.k = null;
        this.l = null;
        if (p.a > 2) {
            b("ct(): no size ");
        }
        this.k = s();
        if (this.k == null) {
            this.k = new n(4096);
        }
        this.h = 4096;
        this.i = 0;
        this.f = true;
    }
    
    public void a(final w k) {
        if (p.a > 2) {
            b("setBackEnd(): from packet " + k);
        }
        this.k = k;
    }
    
    public w t() {
        if (p.a > 2) {
            b("getBackEnd(): from packet " + this.k);
        }
        return this.k;
    }
    
    private void a() {
        if (this.k == null) {
            throw new IllegalStateException("BackEnd not allocated");
        }
    }
    
    public void u() {
        this.k.f();
    }
    
    public void write(final int n) {
        this.a();
        this.k.a((byte)n);
    }
    
    public void write(final byte[] array) {
        this.a();
        this.k.a(array);
    }
    
    public void write(final byte[] array, final int n, final int n2) {
        this.a();
        this.k.a(array, n, n2);
    }
    
    public void writeBoolean(final boolean b) {
        this.a();
        this.k.a((byte)(b ? 1 : 0));
    }
    
    public void writeByte(final int n) {
        this.a();
        this.k.a((byte)(n & 0xFF));
    }
    
    public void writeShort(final int n) {
        this.a();
        this.k.a((short)(n & 0xFFFF));
    }
    
    public void writeChar(final int n) {
        this.k.a((short)(n & 0xFFFF));
    }
    
    public void writeInt(final int n) {
        this.a();
        this.k.b(n);
    }
    
    public void writeLong(final long n) {
        this.a();
        this.k.a(n);
    }
    
    public void writeFloat(final float n) {
        this.a();
        this.writeInt(Float.floatToIntBits(n));
    }
    
    public void writeDouble(final double n) {
        this.a();
        this.writeLong(Double.doubleToLongBits(n));
    }
    
    public void writeBytes(final String s) {
        this.a();
        this.k.a(s.getBytes());
    }
    
    public void writeChars(final String s) {
        this.a();
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            this.k.a((byte)(char1 >>> 8 & '\u00ff'));
            this.k.a((byte)(char1 >>> 0 & '\u00ff'));
        }
    }
    
    public void writeUTF(final String s) {
        this.a();
        final int length = s.length();
        int n = 0;
        final char[] array = new char[length];
        s.getChars(0, length, array, 0);
        for (final char c : array) {
            if (c >= '\u0001' && c <= '\u007f') {
                ++n;
            }
            else if (c > '\u07ff') {
                n += 3;
            }
            else {
                n += 2;
            }
        }
        if (n > 65535) {
            throw new RuntimeException("UTF too lengthy");
        }
        this.k.a((byte)(n >>> 8 & 0xFF));
        this.k.a((byte)(n >>> 0 & 0xFF));
        for (final char c2 : array) {
            if (c2 >= '\u0001' && c2 <= '\u007f') {
                this.k.a((byte)c2);
            }
            else if (c2 > '\u07ff') {
                this.k.a((byte)('\u00e0' | (c2 >> 12 & '\u000f')));
                this.k.a((byte)('\u0080' | (c2 >> 6 & '?')));
                this.k.a((byte)('\u0080' | (c2 >> 0 & '?')));
            }
            else {
                this.k.a((byte)('\u00c0' | (c2 >> 6 & '\u001f')));
                this.k.a((byte)('\u0080' | (c2 >> 0 & '?')));
            }
        }
    }
    
    public static int a(final String s) {
        if (s == null) {
            return 0;
        }
        final int length = s.length();
        int n = 0;
        final char[] array = new char[length];
        s.getChars(0, length, array, 0);
        for (final char c : array) {
            if (c >= '\u0001' && c <= '\u007f') {
                ++n;
            }
            else if (c > '\u07ff') {
                n += 3;
            }
            else {
                n += 2;
            }
        }
        return n + 2;
    }
    
    public void readFully(final byte[] array) {
        this.a();
        this.k.b(array, 0, array.length);
    }
    
    public byte[] v() {
        return this.k.e();
    }
    
    public void readFully(final byte[] array, final int n, final int n2) {
        this.a();
        this.k.b(array, n, n2);
    }
    
    public int skipBytes(final int n) {
        this.a();
        this.k.c(n);
        return n;
    }
    
    public boolean readBoolean() {
        this.a();
        final int d = this.k.d();
        if (d < 0) {
            throw new EOFException();
        }
        return d != 0;
    }
    
    public byte readByte() {
        this.a();
        return (byte)this.k.d();
    }
    
    public int readUnsignedByte() {
        return this.k.d();
    }
    
    public short readShort() {
        this.a();
        return this.k.c();
    }
    
    public int readUnsignedShort() {
        this.a();
        final int d = this.k.d();
        final int d2 = this.k.d();
        if ((d | d2) < 0) {
            throw new EOFException();
        }
        return (d << 8) + (d2 << 0);
    }
    
    public char readChar() {
        this.a();
        return (char)this.readUnsignedShort();
    }
    
    public int readInt() {
        this.a();
        return this.k.b();
    }
    
    public long readLong() {
        this.a();
        return this.k.a();
    }
    
    public float readFloat() {
        this.a();
        return Float.intBitsToFloat(this.k.b());
    }
    
    public double readDouble() {
        this.a();
        return Double.longBitsToDouble(this.k.a());
    }
    
    public String readLine() {
        throw new IOException("TBResquest.readLine() Not Implemented.");
    }
    
    public String readUTF() {
        this.a();
        final int unsignedShort = this.readUnsignedShort();
        final char[] array = new char[unsignedShort];
        final byte[] array2 = new byte[unsignedShort];
        int i = 0;
        int n = 0;
        this.readFully(array2, 0, unsignedShort);
        while (i < unsignedShort) {
            final int n2 = array2[i] & 0xFF;
            switch (n2 >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7: {
                    ++i;
                    array[n++] = (char)n2;
                    continue;
                }
                case 12:
                case 13: {
                    i += 2;
                    if (i > unsignedShort) {
                        throw new UTFDataFormatException();
                    }
                    final byte b = array2[i - 1];
                    if ((b & 0xC0) != 0x80) {
                        throw new UTFDataFormatException();
                    }
                    array[n++] = (char)((n2 & 0x1F) << 6 | (b & 0x3F));
                    continue;
                }
                case 14: {
                    i += 3;
                    if (i > unsignedShort) {
                        throw new UTFDataFormatException();
                    }
                    final byte b2 = array2[i - 2];
                    final byte b3 = array2[i - 1];
                    if ((b2 & 0xC0) != 0x80 || (b3 & 0xC0) != 0x80) {
                        throw new UTFDataFormatException();
                    }
                    array[n++] = (char)((n2 & 0xF) << 12 | (b2 & 0x3F) << 6 | (b3 & 0x3F) << 0);
                    continue;
                }
                default: {
                    throw new UTFDataFormatException();
                }
            }
        }
        return new String(array, 0, n);
    }
    
    public int a(final Object o) {
        final int a;
        if ((a = this.k.a(null, o)) == -2) {
            this.g = this.readInt();
        }
        return a;
    }
    
    public synchronized int b(final Object o) {
        if (p.a > 2) {
            b("writeToStream(): begin");
        }
        if (this.l == null) {
            this.l = new byte[4];
            com.daysofwonder.util.w.a(this.g, this.l);
            this.k.b(0, this.j);
            this.k.b(this.l);
            if (p.a > 2) {
                b("writeToStream(): Assembling Packet header");
            }
        }
        return this.k.a(o);
    }
    
    public void a(final int n, final Object o) {
        if (!this.m) {
            this.k.a(n, o);
        }
        else {
            this.k.d(n);
        }
    }
    
    public void i(final int n) {
        this.a(n, null);
    }
    
    public int w() {
        if (!this.m && this.k != null) {
            return this.k.g();
        }
        if (this.b != null) {
            try {
                this.b.run();
            }
            catch (Exception ex) {}
        }
        return -1;
    }
    
    public void x() {
        this.m = true;
    }
    
    static {
        a = j.a("Message");
        d = new IOException("Write is only permitted");
        e = new IllegalStateException("BackEnd not allocated before use");
    }
}
