// 
// Decompiled by Procyon v0.5.30
// 

package lecture;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public abstract class a
{
    protected PrintStream a;
    
    protected abstract int if();
    
    protected abstract int a();
    
    public String if(final byte[] array) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        String string;
        try {
            this.a(byteArrayInputStream, byteArrayOutputStream);
            string = byteArrayOutputStream.toString("8859_1");
        }
        catch (Exception ex) {
            throw new Error("ChracterEncoder::encodeBuffer internal error");
        }
        return string;
    }
    
    public void if(final byte[] array, final OutputStream outputStream) throws IOException {
        this.a(new ByteArrayInputStream(array), outputStream);
    }
    
    public void a(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final byte[] array = new byte[this.a()];
        this.do(outputStream);
        while (true) {
            final int a = this.a(inputStream, array);
            if (a == 0) {
                break;
            }
            this.a(outputStream, a);
            for (int i = 0; i < a; i += this.if()) {
                if (i + this.if() <= a) {
                    this.a(outputStream, array, i, this.if());
                }
                else {
                    this.a(outputStream, array, i, a - i);
                }
            }
            if (a < this.a()) {
                break;
            }
            this.if(outputStream);
        }
        this.a(outputStream);
    }
    
    protected abstract void a(final OutputStream p0, final byte[] p1, final int p2, final int p3) throws IOException;
    
    public String a(final byte[] array) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        try {
            this.if(byteArrayInputStream, byteArrayOutputStream);
        }
        catch (Exception ex) {
            throw new Error("ChracterEncoder::encodeBuffer internal error");
        }
        return byteArrayOutputStream.toString();
    }
    
    public void a(final byte[] array, final OutputStream outputStream) throws IOException {
        this.if(new ByteArrayInputStream(array), outputStream);
    }
    
    public void if(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final byte[] array = new byte[this.a()];
        this.do(outputStream);
        int i;
        do {
            i = this.a(inputStream, array);
            if (i == 0) {
                break;
            }
            this.a(outputStream, i);
            for (int j = 0; j < i; j += this.if()) {
                if (j + this.if() <= i) {
                    this.a(outputStream, array, j, this.if());
                }
                else {
                    this.a(outputStream, array, j, i - j);
                }
            }
            this.if(outputStream);
        } while (i >= this.a());
        this.a(outputStream);
    }
    
    protected void do(final OutputStream outputStream) throws IOException {
        this.a = new PrintStream(outputStream);
    }
    
    protected void a(final OutputStream outputStream) throws IOException {
    }
    
    protected void a(final OutputStream outputStream, final int n) throws IOException {
    }
    
    protected void if(final OutputStream outputStream) throws IOException {
        this.a.println();
    }
    
    protected int a(final InputStream inputStream, final byte[] array) throws IOException {
        for (int i = 0; i < array.length; ++i) {
            final int read = inputStream.read();
            if (read == -1) {
                return i;
            }
            array[i] = (byte)read;
        }
        return array.length;
    }
}
