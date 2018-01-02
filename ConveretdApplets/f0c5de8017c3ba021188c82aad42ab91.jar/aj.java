import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class aj implements ak
{
    public static boolean a;
    private int b;
    private int c;
    private byte[] d;
    private InputStream e;
    
    public aj(final InputStream inputStream) {
        this.d = new byte[4000];
        this.a(inputStream);
    }
    
    private static int a(final byte[] array, final int n, final int n2) {
        if (n2 < 2) {
            return -1;
        }
        final int n3 = n + n2;
        int i = n;
        int n4 = 0;
        while (i < n3 && array[i] <= 13) {
            if (array[i] != 13 && array[i] != 10) {
                break;
            }
            ++i;
        }
        while (i < n3) {
            if (n4 == 0) {
                if (array[i] <= 13) {
                    if (array[i] == 13) {
                        n4 = 1;
                    }
                    else if (array[i] == 10) {
                        n4 = 2;
                    }
                }
            }
            else if (n4 == 1) {
                if (array[i] == 10) {
                    n4 = 3;
                }
                else {
                    if (array[i] == 13) {
                        return i + 1;
                    }
                    n4 = 0;
                }
            }
            else if (n4 == 3) {
                if (array[i] == 13) {
                    n4 = 4;
                }
                else {
                    n4 = 0;
                }
            }
            else if (n4 == 4) {
                if (array[i] == 10) {
                    return i + 1;
                }
                n4 = 0;
            }
            else if (n4 == 2) {
                if (array[i] == 10) {
                    return i + 1;
                }
                if (array[i] == 13) {
                    n4 = 5;
                }
                else {
                    n4 = 0;
                }
            }
            else if (n4 == 5) {
                if (array[i] == 10) {
                    n4 = 6;
                }
                else {
                    n4 = 0;
                }
            }
            else {
                if (array[i] == 13) {
                    return i + 1;
                }
                n4 = 0;
            }
            ++i;
        }
        return -1;
    }
    
    private static int b(final byte[] array, final int n, final int n2) {
        if (n2 < 2) {
            return -1;
        }
        int i = n + n2 - 1;
        int n3 = 0;
        while (i >= n) {
            if (n3 == 0) {
                if (array[i] == 13) {
                    n3 = 1;
                }
                else {
                    if (array[i] != 10) {
                        return -1;
                    }
                    n3 = 2;
                }
            }
            else if (n3 == 1) {
                if (array[i] == 13) {
                    return i + 2;
                }
                if (array[i] != 10) {
                    return -1;
                }
                n3 = 3;
            }
            else if (n3 == 2) {
                if (array[i] == 10) {
                    return i + 2;
                }
                if (array[i] != 13) {
                    return -1;
                }
                n3 = 4;
            }
            else if (n3 == 3) {
                if (array[i] != 13) {
                    return -1;
                }
                n3 = 5;
            }
            else if (n3 == 4) {
                if (array[i] != 10) {
                    return -1;
                }
                n3 = 6;
            }
            else if (n3 == 5) {
                if (array[i] == 10) {
                    return i + 4;
                }
                return -1;
            }
            else if (n3 == 6) {
                if (array[i] == 13) {
                    return i + 4;
                }
                return -1;
            }
            --i;
        }
        return -1;
    }
    
    public final String a() throws al {
        if (n.e()) {
            n.a(this.b < this.d.length, "readHeader: buf_current >= buf.length");
        }
        final int b = this.b;
        if (aj.a) {
            while ((this.c = a(this.d, 0, this.b)) < 0) {
                this.b += this.a(this.d, this.b, this.d.length - this.b - 1, this.e);
                if (this.b == this.d.length) {
                    throw new al("Header too long");
                }
            }
        }
        else {
            while ((this.c = b(this.d, 0, this.b)) < 0) {
                this.b += this.a(this.d, this.b, 1, this.e);
                if (this.b == this.d.length) {
                    throw new al("Header too long");
                }
            }
        }
        if (n.e()) {
            n.a(this.c >= 2 && this.c <= this.b && this.b <= this.d.length, "readHeader: marks are out of allowed range");
        }
        if (ak.a.l()) {
            ak.a.j("readHeader() buf_current=" + this.b + " start=" + b + " @" + this.e.hashCode() + " buf_header_end:" + this.c);
        }
        if (ak.a.m()) {
            ak.a.k(" buf:\n" + new String(this.d, 0, this.c));
        }
        return new String(this.d, 0, this.c);
    }
    
    public final byte[] a(final int n) throws al {
        final byte[] array = new byte[n];
        if (ak.a.l()) {
            ak.a.j("readBody() contentLength=" + n + " into=" + array.length + " length");
        }
        int n2 = 0;
        if (this.c < this.b) {
            n2 = this.b - this.c;
            if (n2 > n) {
                n2 = n;
                System.arraycopy(this.d, this.c, array, 0, n2);
                final int b = this.b - (this.c + n2);
                if (n.e()) {
                    n.a(b > 0, "need_to_shift <= 0");
                }
                System.arraycopy(this.d, this.b - b, this.d, 0, b);
                this.b = b;
            }
            else {
                System.arraycopy(this.d, this.c, array, 0, n2);
                this.b = 0;
            }
        }
        else {
            this.b = 0;
        }
        this.c = -1;
        return this.a(n2, n, array);
    }
    
    public final byte[] b() throws al {
        byte[] array = new byte[4000];
        int n = 0;
        if (this.c < this.b) {
            for (n = this.b - this.c; array.length < n; array = new byte[2 * array.length]) {}
            System.arraycopy(this.d, this.c, array, 0, n);
            this.b = 0;
        }
        else {
            this.b = 0;
        }
        this.c = -1;
        return this.a(array, n);
    }
    
    public final byte[] a(int i, final int n, final byte[] array) throws al {
        while (i < n) {
            i += this.a(array, i, n - i, this.e);
        }
        return array;
    }
    
    private int a(final byte[] array, final int n, final int n2, final InputStream inputStream) throws al {
        if (n.e()) {
            n.a(array.length >= n + n2 && n2 > 0 && n >= 0, "do_read: marks are out of allowed range.");
        }
        int read;
        try {
            read = inputStream.read(array, n, n2);
            if (ak.a.l()) {
                ak.a.j("do_read() start=" + n + " length=" + n2 + " have_read=" + read);
            }
            if (ak.a.l()) {
                ak.a.j("current read: " + new String(array, 0, read));
            }
        }
        catch (InterruptedIOException ex) {
            throw new al("ReadTimeout", ex);
        }
        catch (IOException ex2) {
            throw new al("CommonIOError", ex2);
        }
        if (read < 0) {
            throw new al("ServerSideClose");
        }
        if (read == 0) {
            throw new al("ZeroBytesRead");
        }
        return read;
    }
    
    private byte[] a(byte[] array, int n) throws al {
        byte[] array3;
        try {
            while (true) {
                final int read = this.e.read(array, n, array.length - n);
                if (read < 0) {
                    break;
                }
                if (read == array.length - n) {
                    final byte[] array2 = new byte[2 * array.length];
                    System.arraycopy(array, 0, array2, 0, array.length);
                    array = array2;
                }
                n += read;
            }
            array3 = new byte[n];
            System.arraycopy(array, 0, array3, 0, n);
        }
        catch (InterruptedIOException ex) {
            throw new al("ReadTimeout", ex);
        }
        catch (IOException ex2) {
            throw new al("CommonIOError", ex2);
        }
        return array3;
    }
    
    public final void c() {
        this.b = 0;
        this.c = 0;
        this.d = new byte[4000];
    }
    
    public final void d() {
        if (this.e != null) {
            try {
                this.e.close();
            }
            catch (IOException ex) {}
        }
    }
    
    public final void a(final InputStream e) {
        if (this.e != null) {
            try {
                this.e.close();
            }
            catch (Exception ex) {
                if (ak.a.k()) {
                    ak.a.g("Could not close previous input stream in HttpResponseReader ", ex);
                }
            }
        }
        this.e = e;
        this.c();
    }
    
    static {
        aj.a = true;
        if (eh.a()) {
            aj.a = false;
        }
    }
}
