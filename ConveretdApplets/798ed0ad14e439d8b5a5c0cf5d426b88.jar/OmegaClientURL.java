import java.util.StringTokenizer;
import java.net.URL;
import java.io.DataOutputStream;
import java.util.Hashtable;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.URLConnection;

// 
// Decompiled by Procyon v0.5.30
// 

public class OmegaClientURL extends OmegaClient
{
    String close;
    URLConnection equalsIgnoreCase;
    InputStream err;
    OutputStream flush;
    private static Hashtable cookies;
    private static boolean first;
    private static boolean redirection_first;
    
    public OmegaClientURL() {
        this(Configuration.url_calculate_exec());
    }
    
    public OmegaClientURL(final String close) {
        this.close = close;
        if (OmegaClientURL.first) {
            System.err.println("calculation url: " + this.close);
            OmegaClientURL.first = false;
        }
    }
    
    public final DataOutputStream iniciaConnexio() {
        (this.equalsIgnoreCase = new URL(OmegaSystem.translateURL(this.close)).openConnection()).setUseCaches(false);
        this.equalsIgnoreCase.setDoOutput(true);
        this.equalsIgnoreCase.setDoInput(true);
        this.equalsIgnoreCase.setAllowUserInteraction(false);
        this.flush = this.equalsIgnoreCase.getOutputStream();
        (super.I = new DataOutputStream(new PostMethodFilter(this.flush))).writeBytes(" :");
        return super.I;
    }
    
    public final void alliberaConnexio() {
        if (this.err != null) {
            this.err.close();
        }
    }
    
    public final InputStream getInputStream() {
        this.flush.write(32);
        this.flush.write(32);
        this.flush.flush();
        this.err = this.equalsIgnoreCase.getInputStream();
        recvCookies(this.equalsIgnoreCase);
        return this.err;
    }
    
    public final void interrumpt() {
        if (!super.Z) {
            throw new OException("En aquest moment no es pot fer pausa.");
        }
    }
    
    public static final void recvCookies(final URLConnection urlConnection) {
        int n = 0;
        String headerFieldKey;
        String headerField;
        do {
            headerFieldKey = urlConnection.getHeaderFieldKey(n);
            headerField = urlConnection.getHeaderField(n);
            if (headerFieldKey != null && headerField != null && headerFieldKey.equalsIgnoreCase("set-cookie")) {
                final StringTokenizer stringTokenizer = new StringTokenizer(headerField, ";");
                while (stringTokenizer.hasMoreElements()) {
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken(), "=");
                    if (stringTokenizer2.hasMoreElements()) {
                        final String nextToken = stringTokenizer2.nextToken();
                        if (!stringTokenizer2.hasMoreElements()) {
                            continue;
                        }
                        OmegaClientURL.cookies.put(nextToken, stringTokenizer2.nextToken());
                    }
                }
            }
            ++n;
        } while (headerFieldKey != null || headerField != null);
    }
    
    static {
        OmegaClientURL.cookies = new Hashtable();
        OmegaClientURL.first = true;
        OmegaClientURL.redirection_first = true;
    }
}
