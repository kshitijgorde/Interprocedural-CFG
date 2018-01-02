// 
// Decompiled by Procyon v0.5.30
// 

package speedometer.A;

import java.util.Iterator;
import java.net.MalformedURLException;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import javax.net.ssl.SSLSocketFactory;
import java.net.Socket;
import java.net.URL;
import java.io.File;
import java.util.Map;

public class E implements A
{
    public static final String A = "\r\n";
    public static final int G = 34;
    public static final String B = "------------";
    public static final char[] F;
    public static final String C = "--";
    public static final String H = "HTTP/1.0 200 OK";
    public static final String E = "HTTP/1.1 200 OK";
    boolean D;
    
    static {
        F = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'w', 'v', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'W', 'V', 'X', 'Y', 'Z' };
    }
    
    public E() {
        this.D = false;
    }
    
    public boolean C() {
        return this.D;
    }
    
    public void A(final boolean d) {
        this.D = d;
    }
    
    public boolean A(final String s, final Map map, final String s2, final File file, String name, final A a, final StringBuffer sb, final StringBuffer sb2, final StringBuffer sb3) throws MalformedURLException {
        if (name == null) {
            name = file.getName();
        }
        boolean b = false;
        final URL url = new URL(s);
        OutputStream outputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        InputStream inputStream = null;
        InputStream inputStream2 = null;
        InputStream inputStream3 = null;
        FilterInputStream filterInputStream = null;
        Label_1000: {
            try {
                Socket socket = new Socket(url.getHost(), (url.getPort() == -1) ? 80 : url.getPort());
                if ("https".equalsIgnoreCase(url.getProtocol())) {
                    socket = SSLSocketFactory.getDefault().createSocket(url.getHost(), (url.getPort() == -1) ? 443 : url.getPort());
                }
                bufferedOutputStream = new BufferedOutputStream(outputStream = socket.getOutputStream());
                inputStream2 = new BufferedInputStream(inputStream = socket.getInputStream());
                final String b2 = this.B();
                final String a2 = this.A(map, b2);
                final String a3 = this.A(b2, s2, name);
                final String s3 = "\r\n";
                final String string = String.valueOf(b2) + "--" + "\r\n";
                final int n = a2.getBytes().length + a3.getBytes().length + (int)file.length() + s3.getBytes().length + string.getBytes().length;
                final String a4 = this.A(url, b2, n);
                final long n2 = n + a4.length();
                final long n3 = 0L;
                if (a != null) {
                    a.A(n2);
                }
                final byte[] bytes = a4.getBytes();
                outputStream.write(bytes);
                final long n4 = n3 + bytes.length;
                this.A(a, n4, n2);
                if (!this.D) {
                    bufferedOutputStream.flush();
                    final byte[] bytes2 = a2.getBytes();
                    outputStream.write(bytes2);
                    final long n5 = n4 + bytes2.length;
                    this.A(a, n5, n2);
                    if (!this.D) {
                        final byte[] bytes3 = a3.getBytes();
                        outputStream.write(bytes3);
                        long n6 = n5 + bytes3.length;
                        this.A(a, n6, n2);
                        if (!this.D) {
                            final byte[] array = new byte[1024];
                            filterInputStream = new BufferedInputStream(inputStream3 = new FileInputStream(file));
                            int read;
                            while ((read = filterInputStream.read(array)) != -1) {
                                bufferedOutputStream.write(array, 0, read);
                                bufferedOutputStream.flush();
                                n6 += read;
                                this.A(a, n6, n2);
                                if (this.D) {
                                    return false;
                                }
                            }
                            final byte[] bytes4 = (String.valueOf(s3) + string).getBytes();
                            bufferedOutputStream.write(bytes4);
                            this.A(a, n6 + bytes4.length, n2);
                            bufferedOutputStream.flush();
                            if (!this.D) {
                                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream2));
                                String s4 = null;
                                if (sb != null) {
                                    sb.delete(0, sb.length());
                                }
                                if (sb2 != null) {
                                    sb2.delete(0, sb2.length());
                                }
                                int n7 = 0;
                                String line;
                                while ((line = bufferedReader.readLine()) != null) {
                                    if (s4 == null) {
                                        s4 = line;
                                        if (sb3 != null) {
                                            sb3.append(s4);
                                        }
                                    }
                                    if (n7 == 0) {
                                        n7 = ((line.length() == 0) ? 1 : 0);
                                        if (sb == null) {
                                            continue;
                                        }
                                        if (sb.length() > 0) {
                                            sb.append("\r\n");
                                        }
                                        sb.append(line);
                                    }
                                    else {
                                        if (sb2 == null) {
                                            continue;
                                        }
                                        if (sb2.length() > 0) {
                                            sb2.append("\r\n");
                                        }
                                        sb2.append(line);
                                    }
                                }
                                bufferedReader.close();
                                if ("HTTP/1.0 200 OK".equals(s4) || "HTTP/1.1 200 OK".equals(s4)) {
                                    if (a != null) {
                                        a.A();
                                    }
                                    b = true;
                                    break Label_1000;
                                }
                                if (a != null) {
                                    a.A("Server returned:" + s4);
                                }
                                break Label_1000;
                            }
                        }
                    }
                }
                return false;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                if (a != null) {
                    a.A("Error occured:" + ex);
                }
            }
            finally {
                this.A(inputStream2);
                this.A(inputStream);
                this.A(bufferedOutputStream);
                this.A(outputStream);
                this.A(filterInputStream);
                this.A(inputStream3);
            }
        }
        this.A(inputStream2);
        this.A(inputStream);
        this.A(bufferedOutputStream);
        this.A(outputStream);
        this.A(filterInputStream);
        this.A(inputStream3);
        return b;
    }
    
    public boolean A(final String s, final Map map, final String s2, final long n, final String s3, final A a, final StringBuffer sb, final StringBuffer sb2, final StringBuffer sb3) throws MalformedURLException {
        boolean b = false;
        final URL url = new URL(s);
        OutputStream outputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        InputStream inputStream = null;
        InputStream inputStream2 = null;
        Label_0984: {
            try {
                Socket socket = new Socket(url.getHost(), (url.getPort() == -1) ? 80 : url.getPort());
                if ("https".equalsIgnoreCase(url.getProtocol())) {
                    socket = SSLSocketFactory.getDefault().createSocket(url.getHost(), (url.getPort() == -1) ? 443 : url.getPort());
                }
                bufferedOutputStream = new BufferedOutputStream(outputStream = socket.getOutputStream());
                inputStream2 = new BufferedInputStream(inputStream = socket.getInputStream());
                final String b2 = this.B();
                final String a2 = this.A(map, b2);
                final String a3 = this.A(b2, s2, s3);
                final String s4 = "\r\n";
                final String string = String.valueOf(b2) + "--" + "\r\n";
                final int n2 = a2.getBytes().length + a3.getBytes().length + (int)n + s4.getBytes().length + string.getBytes().length;
                final String a4 = this.A(url, b2, n2);
                final long n3 = n2 + a4.length();
                final long n4 = 0L;
                if (a != null) {
                    a.A(n3);
                }
                final byte[] bytes = a4.getBytes();
                outputStream.write(bytes);
                final long n5 = n4 + bytes.length;
                this.A(a, n5, n3);
                if (!this.D) {
                    bufferedOutputStream.flush();
                    final byte[] bytes2 = a2.getBytes();
                    outputStream.write(bytes2);
                    final long n6 = n5 + bytes2.length;
                    this.A(a, n6, n3);
                    if (!this.D) {
                        final byte[] bytes3 = a3.getBytes();
                        outputStream.write(bytes3);
                        long n7 = n6 + bytes3.length;
                        this.A(a, n7, n3);
                        if (!this.D) {
                            final byte[] array = new byte[1024];
                            long n8 = 0L;
                            while (n7 < n) {
                                for (int i = 0; i < array.length; ++i) {
                                    array[i] = (byte)(Math.random() * 127.0);
                                }
                                final long n9 = (array.length > n - n8) ? (n - n8) : array.length;
                                bufferedOutputStream.write(array, 0, (int)n9);
                                bufferedOutputStream.flush();
                                n7 += n9;
                                n8 += n9;
                                this.A(a, n7, n3);
                                if (this.D) {
                                    return false;
                                }
                            }
                            final byte[] bytes4 = (String.valueOf(s4) + string).getBytes();
                            bufferedOutputStream.write(bytes4);
                            this.A(a, n7 + bytes4.length, n3);
                            bufferedOutputStream.flush();
                            if (!this.D) {
                                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream2));
                                String s5 = null;
                                if (sb != null) {
                                    sb.delete(0, sb.length());
                                }
                                if (sb2 != null) {
                                    sb2.delete(0, sb2.length());
                                }
                                int n10 = 0;
                                String line;
                                while ((line = bufferedReader.readLine()) != null) {
                                    if (s5 == null) {
                                        s5 = line;
                                        if (sb3 != null) {
                                            sb3.append(s5);
                                        }
                                    }
                                    if (n10 == 0) {
                                        n10 = ((line.length() == 0) ? 1 : 0);
                                        if (sb == null) {
                                            continue;
                                        }
                                        if (sb.length() > 0) {
                                            sb.append("\r\n");
                                        }
                                        sb.append(line);
                                    }
                                    else {
                                        if (sb2 == null) {
                                            continue;
                                        }
                                        if (sb2.length() > 0) {
                                            sb2.append("\r\n");
                                        }
                                        sb2.append(line);
                                    }
                                }
                                bufferedReader.close();
                                if ("HTTP/1.0 200 OK".equals(s5) || "HTTP/1.1 200 OK".equals(s5)) {
                                    if (a != null) {
                                        a.A();
                                    }
                                    b = true;
                                    break Label_0984;
                                }
                                if (a != null) {
                                    a.A("Server returned:" + s5);
                                }
                                break Label_0984;
                            }
                        }
                    }
                }
                return false;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                if (a != null) {
                    a.A("Error occured:" + ex);
                }
            }
            finally {
                this.A(inputStream2);
                this.A(inputStream);
                this.A(bufferedOutputStream);
                this.A(outputStream);
            }
        }
        this.A(inputStream2);
        this.A(inputStream);
        this.A(bufferedOutputStream);
        this.A(outputStream);
        return b;
    }
    
    public int A(final String s, final Map map, final String s2, final File file, final String s3) throws MalformedURLException {
        final URL url = new URL(s);
        final String b = this.B();
        final int n = this.A(map, b).getBytes().length + this.A(b, s2, s3).getBytes().length + (int)file.length() + "\r\n".getBytes().length + (String.valueOf(b) + "--" + "\r\n").getBytes().length;
        return this.A(url, b, n).getBytes().length + n;
    }
    
    public void A(final long n) {
        this.B("uploadStarted, bytesTotal=" + n);
    }
    
    public void A(final long n, final long n2) {
        this.B("progressChanged, bytesTransferred=" + n + ", bytesTotal=" + n2);
    }
    
    public void A() {
        this.B("uploadFinished");
    }
    
    public void A(final String s) {
        this.B("uploadFailed, reason=" + s);
    }
    
    protected String A(final URL url, final String s, final int n) {
        return "POST " + ((url.getPath() == null || url.getPath().trim().length() == 0) ? "/" : url.getPath()) + " HTTP/1.0" + "\r\n" + "Host: " + url.getHost() + ":" + ((url.getPort() == -1) ? 80 : url.getPort()) + "\r\n" + "User-Agent: Java Image Uploader 1.0" + "\r\n" + "Content-Length: " + n + "\r\n" + "Content-Type: multipart/form-data; boundary=" + s.substring(2) + "\r\n" + "\r\n";
    }
    
    protected String A(final Map map, final String s) {
        final StringBuffer sb = new StringBuffer("");
        if (map != null) {
            for (final String s2 : map.keySet()) {
                final String s3 = map.get(s2);
                if (s3 != null) {
                    sb.append(String.valueOf(s) + "\r\n" + "Content-Disposition: form-data; name=\"" + s2 + "\"" + "\r\n" + "\r\n" + s3 + "\r\n");
                }
            }
        }
        return sb.toString();
    }
    
    protected String A(final String s, final String s2, final String s3) {
        return String.valueOf(s) + "\r\n" + "Content-Disposition: form-data; name=\"" + s2 + "\"; filename=\"" + s3 + "\"" + "\r\n" + "Content-Type: text/comma-separated-values" + "\r\n" + "\r\n";
    }
    
    protected String B() {
        final StringBuffer sb = new StringBuffer("------------");
        while (sb.length() < 34) {
            sb.append(speedometer.A.E.F[(int)(Math.random() * speedometer.A.E.F.length)]);
        }
        return sb.toString();
    }
    
    protected void A(final InputStream inputStream) {
        try {
            inputStream.close();
        }
        catch (Exception ex) {}
    }
    
    protected void A(final OutputStream outputStream) {
        try {
            outputStream.close();
        }
        catch (Exception ex) {}
    }
    
    protected void A(final A a, final long n, final long n2) {
        if (a != null) {
            a.A(n, n2);
        }
    }
    
    protected void B(final String s) {
    }
}
