// 
// Decompiled by Procyon v0.5.30
// 

package ji.net;

import java.util.StringTokenizer;
import ji.v1event.f0;
import java.io.OutputStream;
import ji.v1event.f2;
import java.io.InputStream;
import ji.net.cookie.bc;
import ji.res.aa;
import ji.document.ad;
import java.net.URLConnection;
import ji.io.h;
import ji.util.d;
import ji.awt.c;
import java.net.URL;

public abstract class jiFeedbackServletCommunication
{
    public static final String OK = "OK";
    public static final String FAILED = "FAILED";
    public static final String COMMAND_SEPARATOR = ":";
    public static final String COMMAND_TERMINATOR = "\n";
    public static final String STATUS = "Status";
    public static final String COMPLETION = "Completion";
    public static final String INIT = "init";
    private wx a;
    private URL b;
    private c c;
    protected boolean d;
    protected String e;
    protected String f;
    
    public jiFeedbackServletCommunication(final URL b, final String f) {
        this.a = new wx();
        this.b = b;
        this.f = f;
        this.c = new c("jiFeedbackServletComms1", 5);
    }
    
    public String getLastError() {
        return this.e;
    }
    
    protected String a(final int n) {
        return ji.util.d.b(n, this.f);
    }
    
    public void cancelRequest() {
        synchronized (this.a) {
            this.a.a = true;
        }
        // monitorexit(this.a)
    }
    
    public final void releaseResources() {
        try {
            this.b = null;
            this.f = null;
            if (this.c != null) {
                this.c.c();
                this.c = null;
            }
        }
        catch (Exception ex) {}
    }
    
    protected abstract boolean d();
    
    protected abstract String b();
    
    protected abstract boolean c();
    
    protected abstract String a();
    
    protected void a(final String s) {
        h.e(this.f, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.b()))).append(": ").append(s))));
    }
    
    protected void a(final URLConnection urlConnection, final ad ad, final boolean b, final String s) throws Exception {
        urlConnection.setRequestProperty("X-Client-Version", aa.e());
        if (b) {
            urlConnection.setRequestProperty("Content-Encoding", "gzip");
        }
        if (!ji.util.d.by(s)) {
            urlConnection.setRequestProperty("Content-Type", s);
        }
        bc.c(urlConnection, this.f);
    }
    
    public boolean sendToServer(final InputStream inputStream, final ad ad, final boolean b, final String s) {
        try {
            this.d = false;
            if (this.d()) {
                this.a(String.valueOf(String.valueOf(new StringBuffer("connecting to ").append(this.b).append("..."))));
            }
            this.a(1, -1, null);
            URLConnection urlConnection;
            if (inputStream != null) {
                urlConnection = ji.util.d.b(this.b, false, this.f);
                this.a(urlConnection, ad, b, s);
            }
            else {
                urlConnection = ji.util.d.a(this.b, false, this.f);
            }
            final byte[] array = new byte[2048];
            this.a(2, -1, null);
            if (this.d()) {
                this.a("connected...");
            }
            if (inputStream != null) {
                final OutputStream outputStream = urlConnection.getOutputStream();
                int read;
                while ((read = inputStream.read(array)) != -1) {
                    if (this.d()) {
                        this.a(String.valueOf(String.valueOf(new StringBuffer("Sending: ").append(b ? "compressed XML data" : new String(array, 0, read)).append("..."))));
                    }
                    outputStream.write(array, 0, read);
                }
                outputStream.flush();
                inputStream.close();
                outputStream.close();
            }
            if (this.c() && ji.util.d.ck(this.f)) {
                return false;
            }
            this.a(3, -1, null);
            if (this.d()) {
                this.a("reading feedback...");
            }
            final String headerField = urlConnection.getHeaderField(0);
            if (this.d()) {
                this.a(headerField);
            }
            final byte[] array2 = new byte[1024];
            final InputStream inputStream2 = urlConnection.getInputStream();
            StringBuffer sb = new StringBuffer();
            do {
                int n;
                if (inputStream2.available() == 0) {
                    final int read2 = inputStream2.read();
                    if (read2 <= -1) {
                        break;
                    }
                    inputStream2.available();
                    sb.append((char)read2);
                    while ((n = inputStream2.read(array2, 0, array2.length)) != -1) {
                        for (int i = 0; i < n; ++i) {
                            sb.append((char)array2[i]);
                            if (array2[i] == 10) {
                                if (this.e()) {
                                    break;
                                }
                                this.b(sb.toString());
                                sb = new StringBuffer();
                            }
                        }
                        if (this.e()) {
                            break;
                        }
                    }
                    if (this.e()) {
                        break;
                    }
                }
                else {
                    while ((n = inputStream2.read(array2, 0, array2.length)) != -1) {
                        for (int j = 0; j < n; ++j) {
                            sb.append((char)array2[j]);
                            if (array2[j] == 10) {
                                if (this.e()) {
                                    break;
                                }
                                this.b(sb.toString());
                                sb = new StringBuffer();
                            }
                        }
                        if (this.e()) {
                            break;
                        }
                    }
                    if (this.e()) {
                        break;
                    }
                }
                if (n > -1) {
                    continue;
                }
                break;
            } while (!this.e());
            try {
                inputStream2.close();
            }
            catch (Exception ex2) {}
            if (this.d && !this.e()) {
                if (ji.util.d.by(this.e)) {
                    this.a(5, -1, null);
                }
            }
            else if (!this.e() && ji.util.d.by(this.e)) {
                if (this.d()) {
                    this.a("connection aborted! (servlet must have ended premeturely)");
                }
                this.e = this.a();
            }
        }
        catch (Exception ex) {
            if (!ji.util.d.ck(this.f)) {
                this.e = ex.toString();
                ex.printStackTrace();
                this.a(new f2(this, 1, 0, ex.getMessage()));
            }
        }
        if (this.d()) {
            this.a("connection success: ".concat(String.valueOf(String.valueOf(this.d))));
        }
        return this.d;
    }
    
    private boolean e() {
        if (this.c() && ji.util.d.ck(this.f)) {
            return true;
        }
        synchronized (this.a) {
            // monitorexit(this.a)
            return this.a.a;
        }
    }
    
    public void addFeedbackListener(final f0 f0) {
        if (!this.c.a(f0)) {
            this.c.c(f0);
        }
    }
    
    public void removeFeedbackListener(final f0 f0) {
        if (this.c.a(f0)) {
            this.c.b(f0);
        }
    }
    
    protected abstract void a(final int p0, final int p1, final String p2);
    
    protected void b(final int n, final int n2, final String s) {
        this.a(new f2(this, n, n2, s));
    }
    
    protected void a(final f2 f2) {
        for (int i = 0; i < this.c.b(); ++i) {
            ((f0)this.c.b(i)).a(f2);
        }
    }
    
    private void b(final String s) {
        this.a(s.getBytes(), s.length());
    }
    
    protected final void a(final byte[] array, final int n) {
        if (n == -1) {
            return;
        }
        final String s = new String(array);
        if (this.d()) {
            this.a("received message: ".concat(String.valueOf(String.valueOf(s))));
            this.a("decoded received message: ".concat(String.valueOf(String.valueOf(ji.util.d.b(ji.util.d.c3(s), "+", " ")))));
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
        String nextToken = null;
        while (stringTokenizer.hasMoreTokens()) {
            nextToken = stringTokenizer.nextToken();
        }
        if (nextToken != null) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(nextToken, ":");
            while (stringTokenizer2.hasMoreTokens()) {
                final String nextToken2 = stringTokenizer2.nextToken();
                if (nextToken2.equalsIgnoreCase("Completion")) {
                    if (!stringTokenizer2.hasMoreTokens()) {
                        continue;
                    }
                    final String nextToken3 = stringTokenizer2.nextToken();
                    if (nextToken3.equalsIgnoreCase("OK")) {
                        this.d = true;
                        String nextToken4 = null;
                        if (stringTokenizer2.hasMoreTokens()) {
                            nextToken4 = stringTokenizer2.nextToken();
                        }
                        this.a(6, 100, nextToken4);
                    }
                    else {
                        if (!nextToken3.equalsIgnoreCase("FAILED") || !stringTokenizer2.hasMoreTokens()) {
                            continue;
                        }
                        final String c3 = ji.util.d.c3(stringTokenizer2.nextToken());
                        this.e = c3;
                        this.d = false;
                        this.a(7, 100, c3);
                    }
                }
                else if (nextToken2.equalsIgnoreCase("Status")) {
                    if (!stringTokenizer2.hasMoreTokens()) {
                        continue;
                    }
                    final String nextToken5 = stringTokenizer2.nextToken();
                    try {
                        final int int1 = Integer.parseInt(nextToken5);
                        if (stringTokenizer2.hasMoreTokens()) {
                            this.a(4, int1, ji.util.d.c3(stringTokenizer2.nextToken()));
                        }
                        else {
                            this.a(4, int1, null);
                        }
                    }
                    catch (NumberFormatException ex) {
                        ex.printStackTrace();
                    }
                }
                else {
                    if (!nextToken2.equalsIgnoreCase("init") || !stringTokenizer2.hasMoreTokens()) {
                        continue;
                    }
                    this.a(8, 0, "".concat(String.valueOf(String.valueOf(Integer.parseInt(stringTokenizer2.nextToken())))));
                }
            }
        }
    }
    
    class wx
    {
        boolean a;
        
        wx(final jiFeedbackServletCommunication jiFeedbackServletCommunication) {
            this.a = false;
        }
    }
}
