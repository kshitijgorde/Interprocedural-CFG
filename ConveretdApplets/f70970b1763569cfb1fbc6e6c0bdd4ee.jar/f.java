import java.io.IOException;
import java.util.Properties;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public final class f extends e
{
    private String b;
    private int c;
    private Socket d;
    private InputStream e;
    private OutputStream f;
    
    public f(final Properties properties) {
        super(properties);
        this.b = null;
        this.c = 0;
        this.d = null;
        this.e = null;
        this.f = null;
        try {
            this.b = this.a.getProperty("host", "127.0.0.1");
            this.c = Integer.parseInt(this.a.getProperty("tunnelPort", "80"));
        }
        catch (Exception ex) {
            System.out.println("DataService(): " + ex);
        }
    }
    
    public final boolean b(final int n) throws IOException {
        final byte[] array = new byte[256];
        int n2 = 0;
        boolean b = false;
        this.b();
        try {
            InputStream inputStream = null;
            OutputStream outputStream = null;
            final Socket a;
            if ((a = new n(this.b, this.c).a(n)) != null) {
                a.setTcpNoDelay(true);
                inputStream = a.getInputStream();
                outputStream = a.getOutputStream();
            }
            if (inputStream != null && outputStream != null) {
                outputStream.write("GET /dt?ver=1 HTTP/1.0\r\n\r\n".getBytes());
                outputStream.flush();
                int read;
                do {
                    read = inputStream.read(array, 0, "HTTP/1.0 200 OK\r\n\r\n".length() - n2);
                } while ((n2 += read) < "HTTP/1.0 200 OK\r\n\r\n".length() && read > 0);
                if (read < 0 || n2 < "HTTP/1.0 200 OK\r\n\r\n".length()) {
                    b = true;
                }
                else if ("HTTP/1.0 200 OK\r\n\r\n".equals(new String(array, 0, n2))) {
                    final int read2 = inputStream.read();
                    if (inputStream.read() == 10 && read2 >= 48 && read2 <= 57) {
                        switch (read2) {
                            case 48: {
                                break;
                            }
                            case 49: {
                                System.out.println("DataTunnelService.doConnect(): DataTunnel down!!!");
                                break;
                            }
                            case 50: {
                                b = true;
                                break;
                            }
                            case 51: {
                                System.out.println("DataTunnelService.doConnect(): DataTunnel error!!!");
                                break;
                            }
                            default: {
                                b = true;
                                break;
                            }
                        }
                    }
                    else {
                        b = true;
                    }
                }
                else {
                    b = true;
                }
                if (b) {
                    System.out.println("DataTunnelService.doConnect(): Bad Protocol!!!");
                    return false;
                }
                this.d = a;
                this.e = inputStream;
                this.f = outputStream;
            }
        }
        catch (Exception ex) {
            throw new IOException("DataTunnelService.doConnect() failed: " + ex);
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
            }
        }
        catch (Exception ex) {}
        try {
            if (this.e != null) {
                this.e.close();
            }
        }
        catch (Exception ex2) {}
        try {
            if (this.f != null) {
                this.f.close();
            }
        }
        catch (Exception ex3) {}
    }
}
