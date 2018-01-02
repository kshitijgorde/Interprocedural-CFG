// 
// Decompiled by Procyon v0.5.30
// 

package org.a.d.c;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectInput;

public abstract class c extends b implements a, ObjectInput, ObjectOutput
{
    private static String z;
    
    public c() {
    }
    
    public c(final ThreadGroup threadGroup, final String s) {
        super(threadGroup, s);
    }
    
    public abstract void close() throws IOException;
    
    public abstract int available() throws IOException;
    
    public abstract int read(final byte[] p0) throws IOException;
    
    public abstract void write(final byte[] p0) throws IOException;
    
    public abstract boolean readBoolean();
    
    public abstract short readShort();
    
    public abstract void writeObject(final Object p0) throws IOException;
    
    public void flush() throws IOException {
        throw new IOException(c.z);
    }
    
    public int read() throws IOException {
        throw new IOException(c.z);
    }
    
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        throw new IOException(c.z);
    }
    
    public byte readByte() throws IOException {
        throw new IOException(c.z);
    }
    
    public char readChar() throws IOException {
        throw new IOException(c.z);
    }
    
    public double readDouble() throws IOException {
        throw new IOException(c.z);
    }
    
    public float readFloat() throws IOException {
        throw new IOException(c.z);
    }
    
    public void readFully(final byte[] array) throws IOException {
        throw new IOException(c.z);
    }
    
    public void readFully(final byte[] array, final int n, final int n2) throws IOException {
        throw new IOException(c.z);
    }
    
    public int readInt() throws IOException {
        throw new IOException(c.z);
    }
    
    public String readLine() throws IOException {
        throw new IOException(c.z);
    }
    
    public long readLong() throws IOException {
        throw new IOException(c.z);
    }
    
    public Object readObject() throws ClassNotFoundException, IOException {
        throw new IOException(c.z);
    }
    
    public String readUTF() throws IOException {
        throw new IOException(c.z);
    }
    
    public int readUnsignedByte() throws IOException {
        throw new IOException(c.z);
    }
    
    public int readUnsignedShort() throws IOException {
        throw new IOException(c.z);
    }
    
    public long skip(final long n) throws IOException {
        throw new IOException(c.z);
    }
    
    public int skipBytes(final int n) throws IOException {
        throw new IOException(c.z);
    }
    
    public void write(final int n) throws IOException {
        throw new IOException(c.z);
    }
    
    public void write(final byte[] array, final int n, final int n2) throws IOException {
        throw new IOException(c.z);
    }
    
    public void writeBoolean(final boolean b) throws IOException {
        throw new IOException(c.z);
    }
    
    public void writeByte(final int n) throws IOException {
        throw new IOException(c.z);
    }
    
    public void writeBytes(final String s) throws IOException {
        throw new IOException(c.z);
    }
    
    public void writeChar(final int n) throws IOException {
        throw new IOException(c.z);
    }
    
    public void writeChars(final String s) throws IOException {
        throw new IOException(c.z);
    }
    
    public void writeDouble(final double n) throws IOException {
        throw new IOException(c.z);
    }
    
    public void writeFloat(final float n) throws IOException {
        throw new IOException(c.z);
    }
    
    public void writeInt(final int n) throws IOException {
        throw new IOException(c.z);
    }
    
    public void writeLong(final long n) throws IOException {
        throw new IOException(c.z);
    }
    
    public void writeShort(final int n) throws IOException {
        throw new IOException(c.z);
    }
    
    public void writeUTF(final String s) throws IOException {
        throw new IOException(c.z);
    }
    
    static {
        final char[] charArray = ")T\u0006\u0019\t\u0010Y\u001b\u0002F\u0019\u001c\u0006\u0019\t\u0013S\u001bJZ\bL\u001f\u0005[\tY\u000bD".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '}';
                    break;
                }
                case 1: {
                    c2 = '<';
                    break;
                }
                case 2: {
                    c2 = 'o';
                    break;
                }
                case 3: {
                    c2 = 'j';
                    break;
                }
                default: {
                    c2 = ')';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        c.z = new String(charArray).intern();
    }
}
