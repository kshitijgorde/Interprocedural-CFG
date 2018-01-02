import java.io.IOException;
import java.util.Properties;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public final class k extends e
{
    private String b;
    private int c;
    private Socket d;
    private InputStream e;
    private OutputStream f;
    
    public k(final Properties properties) {
        super(properties);
        this.b = null;
        this.c = 0;
        this.d = null;
        this.e = null;
        this.f = null;
        try {
            this.b = this.a.getProperty("host", "127.0.0.1");
            this.c = Integer.parseInt(this.a.getProperty("port", "1580"));
        }
        catch (Exception ex) {
            System.out.println("DataService(): " + ex);
        }
    }
    
    public final boolean b(final int n) throws IOException {
        this.b();
        try {
            final Socket a;
            if ((a = new n(this.b, this.c).a(n)) != null) {
                a.setTcpNoDelay(true);
                final InputStream inputStream = a.getInputStream();
                final OutputStream outputStream = a.getOutputStream();
                if (inputStream != null && outputStream != null) {
                    this.d = a;
                    this.e = inputStream;
                    this.f = outputStream;
                }
            }
        }
        catch (Exception ex) {
            throw new IOException("DataService.doConnect() failed: " + ex);
        }
        return this.d != null;
    }
    
    public final int a(final byte[] array, final int n, final int n2) throws IOException {
        return this.e.read(array, n, n2);
    }
    
    public final void b(final byte[] array, final int n, final int n2) throws IOException {
        this.f.write(array, n, n2);
    }
    
    public final void a() throws IOException {
        this.f.flush();
    }
    
    public final void b() {
        try {
            if (this.d != null) {
                this.d.close();
                this.d = null;
            }
        }
        catch (Exception ex) {}
        try {
            if (this.e != null) {
                this.e.close();
                this.e = null;
            }
        }
        catch (Exception ex2) {}
        try {
            if (this.f != null) {
                this.f.close();
                this.f = null;
            }
        }
        catch (Exception ex3) {}
    }
}
