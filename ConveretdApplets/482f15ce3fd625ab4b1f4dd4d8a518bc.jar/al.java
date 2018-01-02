import java.io.InputStream;
import java.io.IOException;
import java.net.SocketException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.io.FileNotFoundException;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.NoRouteToHostException;
import java.security.AccessControlException;
import java.net.HttpURLConnection;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public final class al
{
    private URL a;
    public String a;
    public int a;
    private int b;
    public String b;
    private byte[] a;
    private boolean a;
    private aE a;
    
    public al(final aE a) {
        this.a = "";
        this.a = 0;
        this.b = 0;
        this.a = false;
        this.a = a;
        this.b = 0;
        this.b = "";
        this.a();
        this.a = false;
    }
    
    public final void a() {
        this.a = "";
        this.a = 0;
    }
    
    public final boolean a(final URL a) {
        if (this.a && this.a != null && this.a.a != null) {
            this.a.a.a(0.0f);
        }
        this.a = a;
        this.a();
        try {
            final HttpURLConnection httpURLConnection;
            (httpURLConnection = (HttpURLConnection)this.a.openConnection()).setUseCaches(false);
            httpURLConnection.connect();
            this.b = httpURLConnection.getContentLength();
            if (this.b < 0) {
                this.b = 9999999;
            }
            else if (this.b == 0) {
                this.a = "The Nintendo ROM file is currently not available.";
                this.a = 0;
                return false;
            }
            final InputStream inputStream = httpURLConnection.getInputStream();
            ((aE)(Object)(this.a = new byte[this.b])).a(false);
            int n = 0;
            int i = 0;
            while (i < this.b) {
                final int read = inputStream.read();
                ++n;
                if (read == -1) {
                    inputStream.close();
                    if (this.b == 9999999) {
                        this.b = new String(this.a, 0, n - 1);
                        return true;
                    }
                    return false;
                }
                else {
                    this.a[i] = (byte)read;
                    final int n2 = i;
                    if (this.a && this.a != null && this.a.a != null) {
                        this.a.a.a(n2 / this.b);
                    }
                    ++i;
                }
            }
            inputStream.close();
            this.b = new String(this.a, 0, this.b);
            if (this.a && this.a != null && this.a.a != null) {
                this.a.a.a(1.0f);
            }
            return true;
        }
        catch (AccessControlException ex2) {
            this.a = "No Access from Applet to Internet Site";
            this.a = 7;
            return false;
        }
        catch (NoRouteToHostException ex3) {
            this.a = "Proxy settings are invalid, proxy server/port is incorrect.";
            this.a = 3;
            return false;
        }
        catch (ProtocolException ex4) {
            this.a = "Proxy settings are invalid, username/password incorrect.";
            this.a = 4;
            return false;
        }
        catch (ConnectException ex5) {
            this.a = "Could not establish connection to the Internet";
            this.a = 7;
            return false;
        }
        catch (FileNotFoundException ex6) {
            this.a = "The Nintendo game could not be located";
            this.a = 6;
            return false;
        }
        catch (UnknownHostException ex7) {
            this.a = "There is currently no Internet connection";
            this.a = 7;
            return false;
        }
        catch (MalformedURLException ex8) {
            this.a = "URL has Invalid Protocol";
            this.a = 8;
            return false;
        }
        catch (SocketException ex9) {
            this.a = "There is currently no Internet connection";
            this.a = 7;
            return false;
        }
        catch (IOException ex) {
            this.a = ex.toString();
            this.a = 10;
            return false;
        }
        catch (IllegalArgumentException ex10) {
            this.a = "Can't connect to NESCafe server from this host";
            this.a = 8;
            return false;
        }
        catch (Exception ex11) {
            this.a = "A connection error occurred";
            this.a = 10;
            return false;
        }
    }
}
