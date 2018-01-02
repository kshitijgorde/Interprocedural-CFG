import java.io.EOFException;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.io.DataInputStream;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class C50 extends InputStream
{
    int m;
    DataInputStream n;
    public static String p;
    PushbackInputStream s;
    
    public C50(final InputStream inputStream) {
        this.s = new PushbackInputStream(inputStream);
        this.n = new DataInputStream(inputStream);
        this.m = -1;
    }
    
    public String a() throws IOException {
        final StringBuffer sb = new StringBuffer();
        char c = ')';
        char char1 = (char)this.s.read();
        if (char1 == '\'') {
            c = '\'';
        }
        if (char1 == '\"') {
            c = '\"';
        }
        if (char1 == '{') {
            System.out.println("Char count? =" + this.i());
            char1 = (char)this.s.read();
            c = '}';
        }
        int n = 32;
        do {
            if ((char1 != '\\' && char1 != '\0') || n == 92) {
                sb.append(char1);
            }
            if (char1 == '\\' && n == 92) {
                n = 32;
            }
            else {
                n = char1;
            }
            if (c == '\"') {
                char1 = this.n.readChar();
            }
            else {
                char1 = (char)this.s.read();
            }
        } while (char1 != '\uffff' && (char1 != c || n == 92));
        if (sb.length() > 0 && sb.charAt(0) == c) {
            return sb.toString().substring(1);
        }
        return sb.toString();
    }
    
    public int b() throws IOException {
        final int read = this.s.read();
        final int read2 = this.s.read();
        if (read == -1 || read2 == -1) {
            throw new IOException("Error while reading 2 byte int, found EOF");
        }
        return (read2 << 8) + read;
    }
    
    static {
        C50.p = "Copyright (c) 2000 - ZoomON Inc.  All Rights Reserved";
    }
    
    public String f(final int n) throws IOException {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            final int read = this.s.read();
            if (read == -1) {
                throw new IOException("Error while reading a string of length " + n + " found EOF after " + i + " characters");
            }
            sb.append((char)read);
        }
        return sb.toString();
    }
    
    public int g() throws IOException {
        final StringBuffer sb = new StringBuffer();
        char m;
        for (m = (char)this.s.read(); Character.isSpace(m); m = (char)this.s.read()) {}
        while (m != '\uffff' && (Character.isDigit(m) || m == '-')) {
            sb.append(m);
            m = (char)this.s.read();
        }
        this.m = m;
        return Integer.parseInt(sb.toString());
    }
    
    public int h() throws IOException {
        final int read = this.s.read();
        final int read2 = this.s.read();
        if (read == -1 || read2 == -1) {
            throw new IOException("Error while reading 2 byte int, found EOF");
        }
        return (short)((read2 << 8) + read);
    }
    
    public long i() throws IOException {
        final int read = this.read();
        final int read2 = this.read();
        final int read3 = this.read();
        final int read4 = this.read();
        if (read == -1 || read2 == -1 || read3 == -1 || read4 == -1) {
            throw new EOFException();
        }
        return (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
    }
    
    public String j(final int n) throws IOException {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            char c;
            try {
                c = (char)this.s.read();
                this.s.read();
            }
            catch (IOException ex) {
                throw new IOException("Error while reading a string of length " + n + " found EOF after " + i + " characters");
            }
            sb.append(c);
        }
        return sb.toString();
    }
    
    public int read() throws IOException {
        if (this.m != -1) {
            final char c = (char)this.m;
            this.m = -1;
            return c;
        }
        return this.s.read();
    }
    
    public int k() throws IOException {
        final int read = this.read();
        final int read2 = this.read();
        final int read3 = this.read();
        final int read4 = this.read();
        if (read == -1 || read2 == -1 || read3 == -1 || read4 == -1) {
            throw new EOFException();
        }
        return (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
    }
}
