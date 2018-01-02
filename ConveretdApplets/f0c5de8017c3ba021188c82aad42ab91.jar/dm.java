import java.net.UnknownHostException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Vector;
import java.net.URLConnection;

// 
// Decompiled by Procyon v0.5.30
// 

public class dm implements ak
{
    public final void a(final URLConnection urlConnection, final Vector vector, final bf bf, final bb bb, final j j, final int n) {
        InputStream a = null;
        int a2 = 1;
        try {
            a = this.a(urlConnection);
            bf.a(new dq(a));
            a2 = new dr(n).a(new do(urlConnection), new aj(a), vector, bf, bb, j);
        }
        catch (Exception ex) {
            if (ak.a.l()) {
                ak.a.j("URLMsgReader handling error for " + bf);
            }
            dj.a(bf, j, ex, 0);
            if (ex instanceof ct) {
                a2 = 1;
            }
        }
        finally {
            if (a2 == 1 && urlConnection != null && urlConnection instanceof HttpURLConnection) {
                ((HttpURLConnection)urlConnection).disconnect();
                if (ak.a.i()) {
                    ak.a.g("disconnected " + urlConnection.getURL() + "@" + urlConnection.hashCode());
                }
            }
            if (a2 == 0 && a != null) {
                try {
                    a.close();
                }
                catch (Exception ex2) {
                    if (ak.a.k()) {
                        ak.a.g("could not close input stream ", ex2);
                    }
                }
            }
        }
    }
    
    public final void a(final URLConnection urlConnection, final x x, final bf bf, final bb bb, final j j, final int n) {
        final Vector<x> vector = new Vector<x>();
        vector.addElement(x);
        this.a(urlConnection, vector, bf, bb, j, n);
    }
    
    public final InputStream a(final URLConnection urlConnection) throws am {
        try {
            final long currentTimeMillis = System.currentTimeMillis();
            if (ak.a.j()) {
                ak.a.h("getting input stream from " + urlConnection.getURL());
            }
            final InputStream inputStream = urlConnection.getInputStream();
            if (ak.a.j()) {
                ak.a.h("got input stream from " + urlConnection.getURL() + " after " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
            }
            return inputStream;
        }
        catch (Exception ex) {
            if (ex instanceof NoRouteToHostException) {
                throw new dl("no route to host", ex);
            }
            if (ex instanceof ConnectException) {
                throw new dl("cannot connect", ex);
            }
            if (ex instanceof UnknownHostException) {
                throw new dn("unknown host", ex);
            }
            if (ak.a.f()) {
                ak.a.a("could not get InputStream. ", ex);
            }
            final String headerField = urlConnection.getHeaderField(0);
            String s = "could not get InputStream due to " + ex.getMessage();
            int f = -1;
            if (headerField != null) {
                try {
                    final do do1 = new do(urlConnection);
                    f = do1.f();
                    s = do1.g();
                }
                catch (Exception ex2) {
                    if (ak.a.g()) {
                        ak.a.b("cannot parse status line " + headerField, ex2);
                    }
                }
            }
            throw new ct(s, f, urlConnection.getURL().toString());
        }
    }
}
