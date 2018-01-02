// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.http;

import java.util.Iterator;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.HashMap;
import java.io.OutputStream;
import jmaster.jumploader.model.impl.C.C;
import java.util.Map;
import java.net.URL;
import jmaster.util.log.A;

public abstract class B
{
    public static final String EOL = "\r\n";
    public static final int BOUNDARY_SIZE = 34;
    public static final String BOUNDARY_PREFIX = "------------";
    public static final char[] BOUDARY_VALID_CHARS;
    public static final String BOUNDARY_SUFFIX = "--";
    public static final String DEFAULT_FILE_CONTENT_TYPE = "application/unknown";
    public static final String DEFAULT_USER_AGENT = "JumpLoader";
    public static final int BUFFER_LENGTH = 8192;
    protected A L;
    protected boolean G;
    protected URL I;
    protected Map M;
    protected Map F;
    protected D[] J;
    protected jmaster.util.http.A H;
    protected StringBuffer E;
    protected StringBuffer Q;
    protected C R;
    protected Long B;
    protected String D;
    protected OutputStream O;
    protected String P;
    protected long N;
    protected int C;
    protected String S;
    protected boolean T;
    protected boolean K;
    protected Exception A;
    
    public B() {
        this.L = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.G = false;
        this.P = this.D();
        this.N = 0L;
        this.S = "UTF-8";
    }
    
    public boolean isCancel() {
        return this.G;
    }
    
    public void setCancel(final boolean g) {
        this.G = g;
    }
    
    public D[] getFiles() {
        return this.J;
    }
    
    public void setFiles(final D[] j) {
        this.J = j;
    }
    
    public C getResponseHeaderFields() {
        return this.R;
    }
    
    public void setResponseHeaderFields(final C r) {
        this.R = r;
    }
    
    public jmaster.util.http.A getListener() {
        return this.H;
    }
    
    public void setListener(final jmaster.util.http.A h) {
        this.H = h;
    }
    
    public A getLog() {
        return this.L;
    }
    
    public void setLog(final A l) {
        this.L = l;
    }
    
    public OutputStream getOut() {
        return this.O;
    }
    
    public void setOut(final OutputStream o) {
        this.O = o;
    }
    
    public Map getRequestParameters() {
        return this.F;
    }
    
    public void setRequestParameters(final Map f) {
        this.F = f;
    }
    
    public Map getRequestProperties() {
        return this.M;
    }
    
    public void setRequestProperties(final Map m) {
        this.M = m;
    }
    
    public Long getTransferRateMax() {
        return this.B;
    }
    
    public void setTransferRateMax(final Long b) {
        this.B = b;
    }
    
    public URL getUrl() {
        return this.I;
    }
    
    public void setUrl(final URL i) {
        this.I = i;
    }
    
    public String getHttpCode() {
        return this.D;
    }
    
    public void setHttpCode(final String d) {
        this.D = d;
    }
    
    public String getBoundary() {
        return this.P;
    }
    
    public void setBoundary(final String p) {
        this.P = p;
    }
    
    public int getBytesTransferred() {
        return this.C;
    }
    
    public void setBytesTransferred(final int c) {
        this.C = c;
    }
    
    public long getRequestContentLength() {
        return this.N;
    }
    
    public void setRequestContentLength(final long n) {
        this.N = n;
    }
    
    public StringBuffer getRequestHead() {
        return this.E;
    }
    
    public void setRequestHead(final StringBuffer e) {
        this.E = e;
    }
    
    public StringBuffer getResponseContent() {
        return this.Q;
    }
    
    public void setResponseContent(final StringBuffer q) {
        this.Q = q;
    }
    
    public String getRequestEncoding() {
        return this.S;
    }
    
    public void setRequestEncoding(final String s) {
        this.S = s;
    }
    
    public boolean isUploadOk() {
        return this.K;
    }
    
    public Exception getUploadError() {
        return this.A;
    }
    
    public boolean upload() {
        this.T = true;
        this.G = false;
        this.K = false;
        this.A = null;
        final int n = (this.J == null) ? 0 : this.J.length;
        InputStream inputStream = null;
        try {
            if (this.L.B()) {
                this.L.D("[---------------- New request ----------------]");
                this.L.D("url=" + this.I);
            }
            final String a = this.A();
            this.N += a.getBytes(this.S).length;
            final String[] array = new String[n];
            for (int i = 0; i < n; ++i) {
                array[i] = this.A(this.P, this.J[i]);
                this.N += array[i].getBytes(this.S).length + this.J[i].B() + "\r\n".getBytes(this.S).length;
            }
            final String string = this.P + "--" + "\r\n";
            this.N += string.getBytes(this.S).length;
            this.C = 0;
            if (this.H != null) {
                this.H.A(this, this.N);
            }
            if (this.M == null) {
                this.M = new HashMap();
            }
            this.M.put("Host", this.I.getHost() + ((this.I.getPort() == -1) ? "" : (":" + this.I.getPort())));
            this.M.put("Content-Type", "multipart/form-data; boundary=" + this.P.substring(2));
            this.M.put("Content-Length", "" + this.N);
            this.B();
            if (this.L.B()) {
                this.L.D("Form data:\r\n" + a);
            }
            this.O.write(a.getBytes(this.S));
            this.C += a.getBytes(this.S).length;
            if (this.H != null) {
                this.H.A(this, this.C, this.N);
            }
            if (this.G) {
                return false;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            for (int j = 0; j < n; ++j) {
                final D d = this.J[j];
                final byte[] bytes = array[j].getBytes(this.S);
                this.O.write(bytes);
                this.C += bytes.length;
                if (this.H != null) {
                    this.H.A(this, this.C, this.N);
                }
                if (this.G) {
                    return false;
                }
                final byte[] array2 = new byte[8192];
                inputStream = new BufferedInputStream(new FileInputStream(d.F()));
                if (d.A() > 0L) {
                    inputStream.skip(d.A());
                }
                long b = d.B();
                int read;
                while (b > 0L && (read = inputStream.read(array2, 0, (int)Math.min(array2.length, b))) != -1) {
                    this.O.write(array2, 0, read);
                    this.C += read;
                    b -= read;
                    if (this.H != null) {
                        this.H.A(this, this.C, this.N);
                    }
                    if (this.G) {
                        return false;
                    }
                    this.O.flush();
                    if (this.B == null) {
                        continue;
                    }
                    final long currentTimeMillis2 = System.currentTimeMillis();
                    final long n2 = currentTimeMillis2 - currentTimeMillis;
                    if (n2 <= 0L || this.C * 1000 / n2 <= this.B) {
                        continue;
                    }
                    final long n3 = currentTimeMillis + this.C * 1000 / this.B - currentTimeMillis2;
                    if (n3 <= 0L) {
                        continue;
                    }
                    Thread.sleep(n3);
                }
                this.O.flush();
                final byte[] bytes2 = "\r\n".getBytes(this.S);
                this.O.write(bytes2);
                this.C += bytes2.length;
                if (this.H != null) {
                    this.H.A(this, this.C, this.N);
                }
                this.O.flush();
                if (this.G) {
                    return false;
                }
            }
            final byte[] bytes3 = string.getBytes(this.S);
            this.O.write(bytes3);
            this.C += bytes3.length;
            if (this.H != null) {
                this.H.A(this, this.C, this.N);
            }
            this.O.flush();
            if (this.G) {
                return false;
            }
            this.E();
            if (!"200".equals(this.D)) {
                throw new jmaster.util.http.C(this.D);
            }
            if (this.G) {
                return false;
            }
            if (this.H != null) {
                this.H.A(this);
            }
            this.K = true;
        }
        catch (Throwable ex) {
            if (!(ex instanceof Exception)) {
                ex = new Exception(ex);
            }
            this.A = ex;
            if (this.H != null) {
                this.H.A(this, ex);
            }
            this.T = false;
            jmaster.util.F.A.A(inputStream);
            try {
                this.C();
            }
            catch (Exception ex2) {
                this.L.E("Failed to destroy output", ex2);
            }
        }
        finally {
            this.T = false;
            jmaster.util.F.A.A(inputStream);
            try {
                this.C();
            }
            catch (Exception ex3) {
                this.L.E("Failed to destroy output", ex3);
            }
        }
        return this.K;
    }
    
    protected abstract void E() throws Exception;
    
    protected abstract void B() throws Exception;
    
    protected abstract void C() throws Exception;
    
    protected String A() {
        final StringBuffer sb = new StringBuffer("");
        if (this.F != null) {
            for (final String s : this.F.keySet()) {
                String string = null;
                final Object value = this.F.get(s);
                if (value != null) {
                    string = "" + value;
                }
                if (string != null) {
                    sb.append(this.P + "\r\n" + "Content-Disposition: form-data; name=\"" + s + "\"" + "\r\n" + "\r\n" + string + "\r\n");
                }
            }
        }
        return sb.toString();
    }
    
    protected String A(final String s, final D d) {
        return s + "\r\n" + "Content-Disposition: form-data; name=\"" + d.E() + "\"; filename=\"" + d.G() + "\"" + "\r\n" + "Content-Type: " + ((d.C() == null) ? "application/unknown" : d.C()) + "\r\n" + "\r\n";
    }
    
    protected String D() {
        final StringBuffer sb = new StringBuffer("------------");
        while (sb.length() < 34) {
            sb.append(jmaster.util.http.B.BOUDARY_VALID_CHARS[(int)(Math.random() * jmaster.util.http.B.BOUDARY_VALID_CHARS.length)]);
        }
        return sb.toString();
    }
    
    static {
        BOUDARY_VALID_CHARS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'w', 'v', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'W', 'V', 'X', 'Y', 'Z' };
    }
}
