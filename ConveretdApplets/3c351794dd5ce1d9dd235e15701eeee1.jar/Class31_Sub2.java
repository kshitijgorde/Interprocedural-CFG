import java.io.OutputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Collection;
import java.net.URISyntaxException;
import java.net.URI;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.SocketAddress;
import java.net.URL;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.Proxy;
import java.net.ProxySelector;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Class31_Sub2 extends Class31
{
    private ProxySelector aProxySelector3587;
    static Class aClass3588;
    static Class aClass3589;
    
    private final Socket method310(final int n, final Proxy proxy) throws IOException {
        Socket socket2;
        try {
            if (proxy.type() == Proxy.Type.DIRECT) {
                return this.method308((byte)(-53));
            }
            final SocketAddress address = proxy.address();
            if (!(address instanceof InetSocketAddress)) {
                return null;
            }
            final InetSocketAddress inetSocketAddress = (InetSocketAddress)address;
            if (proxy.type() == Proxy.Type.HTTP) {
                String string = null;
                try {
                    final Class<?> forName = Class.forName("sun.net.www.protocol.http.AuthenticationInfo");
                    final Method declaredMethod = forName.getDeclaredMethod("getProxyAuth", (Class31_Sub2.aClass3588 == null) ? (Class31_Sub2.aClass3588 = method312("java.lang.String")) : Class31_Sub2.aClass3588, Integer.TYPE);
                    declaredMethod.setAccessible(true);
                    final Object invoke = declaredMethod.invoke(null, inetSocketAddress.getHostName(), new Integer(inetSocketAddress.getPort()));
                    if (invoke != null) {
                        final Method declaredMethod2 = forName.getDeclaredMethod("supportsPreemptiveAuthorization", (Class<?>[])new Class[0]);
                        declaredMethod2.setAccessible(true);
                        if (declaredMethod2.invoke(invoke, new Object[0])) {
                            final Method declaredMethod3 = forName.getDeclaredMethod("getHeaderName", (Class<?>[])new Class[0]);
                            declaredMethod3.setAccessible(true);
                            final Method declaredMethod4 = forName.getDeclaredMethod("getHeaderValue", (Class31_Sub2.aClass3589 == null) ? (Class31_Sub2.aClass3589 = method312("java.net.URL")) : Class31_Sub2.aClass3589, (Class31_Sub2.aClass3588 == null) ? (Class31_Sub2.aClass3588 = method312("java.lang.String")) : Class31_Sub2.aClass3588);
                            declaredMethod4.setAccessible(true);
                            string = (String)declaredMethod3.invoke(invoke, new Object[0]) + ": " + (String)declaredMethod4.invoke(invoke, new URL("https://" + this.aString299 + "/"), "https");
                        }
                    }
                }
                catch (Exception ex2) {}
                return this.method311(inetSocketAddress.getHostName(), inetSocketAddress.getPort(), string);
            }
            if (proxy.type() == Proxy.Type.SOCKS) {
                final Socket socket = new Socket(proxy);
                socket.connect(new InetSocketAddress(this.aString299, this.anInt302));
                return socket;
            }
            if (n != -6562) {
                this.aProxySelector3587 = null;
            }
            socket2 = null;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return socket2;
    }
    
    @Override
    final Socket method305(final int n) throws IOException {
        Socket method311;
        try {
            if (n != -2) {
                return null;
            }
            if (!Boolean.parseBoolean(System.getProperty("java.net.useSystemProxies"))) {
                System.setProperty("java.net.useSystemProxies", "true");
            }
            final boolean b = 0xFFFFFE44 == ~this.anInt302;
            List<Proxy> select;
            List<Proxy> select2;
            try {
                select = this.aProxySelector3587.select(new URI((b ? "https" : "http") + "://" + this.aString299));
                select2 = this.aProxySelector3587.select(new URI((b ? "http" : "https") + "://" + this.aString299));
            }
            catch (URISyntaxException ex2) {
                return this.method308((byte)(-53));
            }
            select.addAll(select2);
            final Object[] array = select.toArray();
            IOException_Sub1 ioException_Sub1 = null;
            final Object[] array2 = array;
            for (int n2 = 0; ~array2.length < ~n2; ++n2) {
                final Proxy proxy = (Proxy)array2[n2];
                Socket socket;
                try {
                    final Socket method310 = this.method310(-6562, proxy);
                    if (null == method310) {
                        continue;
                    }
                    socket = method310;
                }
                catch (IOException_Sub1 ioException_Sub2) {
                    ioException_Sub1 = ioException_Sub2;
                    continue;
                }
                catch (IOException ex3) {
                    continue;
                }
                return socket;
            }
            if (null != ioException_Sub1) {
                throw ioException_Sub1;
            }
            method311 = this.method308((byte)(-53));
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return method311;
    }
    
    private final Socket method311(final String s, final int n, final String s2) throws IOException {
        Socket socket2;
        try {
            final Socket socket = new Socket(s, n);
            socket.setSoTimeout(10000);
            final OutputStream outputStream = socket.getOutputStream();
            if (null == s2) {
                outputStream.write(("CONNECT " + this.aString299 + ":" + this.anInt302 + " HTTP/1.0\n\n").getBytes(Charset.forName("ISO-8859-1")));
            }
            else {
                outputStream.write(("CONNECT " + this.aString299 + ":" + this.anInt302 + " HTTP/1.0\n" + s2 + "\n\n").getBytes(Charset.forName("ISO-8859-1")));
            }
            outputStream.flush();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            final String line = bufferedReader.readLine();
            if (line != null) {
                if (line.startsWith("HTTP/1.0 200") || line.startsWith("HTTP/1.1 200")) {
                    return socket;
                }
                if (line.startsWith("HTTP/1.0 407") || line.startsWith("HTTP/1.1 407")) {
                    int n2 = 0;
                    final String s3 = "proxy-authenticate: ";
                    for (String s4 = bufferedReader.readLine(); s4 != null && n2 < 50; ++n2, s4 = bufferedReader.readLine()) {
                        if (s4.toLowerCase().startsWith(s3)) {
                            String s5 = s4.substring(s3.length()).trim();
                            final int index = s5.indexOf(32);
                            if (-1 != index) {
                                s5 = s5.substring(0, index);
                            }
                            throw new IOException_Sub1(s5);
                        }
                    }
                    throw new IOException_Sub1("");
                }
            }
            outputStream.close();
            bufferedReader.close();
            socket.close();
            socket2 = null;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return socket2;
    }
    
    public Class31_Sub2() {
        try {
            this.aProxySelector3587 = ProxySelector.getDefault();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static Class method312(final String s) {
        Class<?> forName;
        try {
            forName = Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
        return forName;
    }
}
