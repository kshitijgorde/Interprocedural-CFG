import java.io.OutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class h extends e
{
    private boolean b;
    private l c;
    private b d;
    private InputStream e;
    private int f;
    private long g;
    private l h;
    private b i;
    private ByteArrayOutputStream j;
    private boolean k;
    
    public h(final Properties properties) {
        super(properties);
        this.b = false;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = 0;
        this.g = 1000L;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = true;
        try {
            final String property = this.a.getProperty("host", "127.0.0.1");
            final int int1 = Integer.parseInt(this.a.getProperty("proxyPort", "80"));
            this.k = Boolean.valueOf(this.a.getProperty("serverBlock", "true"));
            this.c = new l(new URL("http", property, int1, "/dp"));
            this.c.a = false;
            this.h = new l(new URL("http", property, int1, "/dp"));
            this.h.a = false;
            (this.d = new b()).a(true);
            this.d.b(3, true);
            (this.i = new b()).a(false);
            this.i.b(3, true);
            this.j = new ByteArrayOutputStream(4096);
        }
        catch (Exception ex) {
            System.err.println("DataProxyService(): " + ex);
        }
    }
    
    public final boolean b(final int n) throws IOException {
        try {
            if (this.c()) {
                if (this.d.a(1)) {
                    this.i.b = this.d.b;
                    this.i.b(1, true);
                }
                if (this.d.a(2)) {
                    this.i.c = this.d.c;
                    this.i.b(2, true);
                }
                if (this.d.a(4)) {
                    this.i.d = this.d.d;
                    this.i.b(4, true);
                }
                if (this.d.a(5)) {
                    this.i.e = this.d.e;
                    this.i.b(5, true);
                }
                if (this.d.a(6)) {
                    this.i.a(this.d.b());
                    this.i.b(6, true);
                }
                if (this.d.a(7)) {
                    this.i.b(this.d.c());
                    this.i.b(7, true);
                }
                this.b = true;
            }
            else {
                this.e = null;
            }
        }
        catch (Exception ex) {
            if (this.e != null) {
                this.e = null;
            }
            throw new IOException("DataProxyService.doConnect() failed: " + ex);
        }
        if (this.e == null) {
            this.b();
            return false;
        }
        return true;
    }
    
    private static OutputStream a(final l l, final b b) throws IOException {
        final byte[] array = new byte[128];
        final int c = b.c(array);
        l.a(c);
        final OutputStream b2;
        (b2 = l.b()).write(array, 0, c);
        return b2;
    }
    
    private static InputStream b(final l l, final b b) throws IOException {
        byte[] array;
        int read;
        int n;
        InputStream a;
        int read2;
        for (array = new byte[128], read = 0, n = 0, read2 = (a = l.a()).read(); n < read2 && (read = a.read(array, n, read2 - n)) > 0; n += read) {}
        if (read2 <= 0 || read <= 0 || b.a(array, read2) < 0) {
            a = null;
        }
        return a;
    }
    
    private boolean c() {
        try {
            if (!this.k) {
                this.d.a(8, true);
            }
            this.d.b(8, false);
            this.d.b(9, false);
            this.d.a = 0;
            a(this.c, this.d);
            this.e = b(this.c, this.d);
            this.d.a(true);
            if (this.e != null && this.d.g == 0) {
                this.f = this.d.a;
                if (this.f <= 0) {
                    this.d();
                }
            }
            else {
                this.e = null;
            }
        }
        catch (RuntimeException ex2) {
            this.f = 0;
            return true;
        }
        catch (IOException ex3) {
            this.f = 0;
            return true;
        }
        catch (Exception ex) {
            System.err.println("DataProxyService.readConnect() failed: " + ex);
            ex.printStackTrace();
            this.b();
            this.f = 0;
            return true;
        }
        final int g;
        boolean b = false;
        switch (g = this.d.g) {
            case 0: {
                b = true;
                break;
            }
            case 3:
            case 4:
            case 7: {
                b = false;
                this.b();
                break;
            }
            case 5:
            case 6:
            case 8: {
                b = false;
                this.f = 0;
                this.d.g = 0;
                break;
            }
            default: {
                b = false;
                this.b();
                break;
            }
        }
        if (!b) {
            System.err.println("DataProxyService.readConnect() failed: lastError = " + g);
        }
        return b;
    }
    
    public final int a(final byte[] array, final int n, final int n2) throws IOException {
        int n3 = 0;
        int read = -1;
        if (!this.b) {
            return -1;
        }
        do {
            if (this.f > 0) {
                try {
                    if ((read = this.e.read(array, n, n2)) > 0) {
                        this.f -= read;
                        if (this.f > 0) {
                            continue;
                        }
                        this.d();
                    }
                    else {
                        if (read >= 0) {
                            continue;
                        }
                        this.d();
                        this.f = 0;
                    }
                }
                catch (IOException ex) {
                    if (n3 != 0) {
                        this.b();
                        throw ex;
                    }
                    read = -1;
                    n3 = 1;
                }
            }
            else {
                if (this.g > 0L) {
                    this.a(this.g);
                }
                if (this.c()) {
                    if (this.f <= 512) {
                        this.g = this.d.f;
                        if (this.g >= 500L) {
                            continue;
                        }
                        this.g = 500L;
                    }
                    else {
                        this.g = 0L;
                    }
                }
                else {
                    this.b();
                }
            }
        } while (read == -1 && this.b);
        if (read == -1) {
            this.b();
        }
        return read;
    }
    
    public final void b(final byte[] array, final int n, final int n2) throws IOException {
        if (!this.b) {
            throw new IOException("Closed");
        }
        this.j.write(array, n, n2);
        if (this.j.size() >= 4096) {
            this.a();
        }
    }
    
    public final void a() throws IOException {
        int n = 0;
        if (!this.b) {
            return;
        }
        while (this.j.size() > 0) {
            try {
                this.i.a(false);
                this.i.b(3, true);
                this.i.a = this.j.size();
                this.i.b(9, true);
                this.h.a(this.j.size());
                this.j.writeTo(a(this.h, this.i));
                b(this.h, this.i);
                this.h.c();
                n = 4;
                this.j.reset();
                this.e();
            }
            catch (RuntimeException ex2) {
                if (n >= 2) {
                    return;
                }
                ++n;
                continue;
            }
            catch (IOException ex3) {
                if (n >= 2) {
                    return;
                }
                ++n;
                continue;
            }
            catch (Exception ex) {
                System.err.println("DataProxyService.flush() failed: " + ex);
                ex.printStackTrace();
                this.b();
                return;
            }
            switch (this.d.g) {
                case 0: {
                    this.j.reset();
                    continue;
                }
                case 3:
                case 4:
                case 7: {
                    this.b();
                    continue;
                }
                case 5:
                case 6:
                case 8: {
                    this.d.g = 0;
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (Exception ex4) {}
                    continue;
                }
                default: {
                    this.b();
                    continue;
                }
            }
        }
    }
    
    private void d() {
        try {
            this.c.c();
        }
        catch (Exception ex) {}
    }
    
    public final void b() {
        this.b = false;
        this.f = 0;
        try {
            this.d.a();
            this.d.a(true);
            this.d.b(3, true);
            this.d();
            this.i.a();
            this.i.a(false);
            this.i.b(3, true);
            this.j.reset();
            try {
                this.h.c();
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
        finally {
            this.e();
        }
    }
    
    private synchronized void a(final long n) {
        try {
            this.wait(n);
        }
        catch (Exception ex) {}
    }
    
    private synchronized void e() {
        try {
            this.notify();
        }
        catch (Exception ex) {}
    }
}
