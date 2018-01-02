import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URLConnection;
import java.awt.Window;
import java.io.InputStream;
import javax.jnlp.PersistenceService;
import javax.jnlp.ServiceManager;
import javax.jnlp.BasicService;
import java.net.URL;
import java.awt.Component;
import java.awt.Frame;
import java.net.CookieHandler;
import java.util.Hashtable;
import jnlpapp.JNLPEPApp;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_dh extends rp_bZ
{
    private JNLPEPApp a;
    private Hashtable a;
    
    public rp_dh(final JNLPEPApp a) {
        this.a = new Hashtable();
        this.a = a;
        this.a();
        CookieHandler.setDefault(new rp_eR(this, CookieHandler.getDefault()));
    }
    
    public final Frame a() {
        return this.a;
    }
    
    public final Component a() {
        return this.a;
    }
    
    public final URL a() {
        return a().getCodeBase();
    }
    
    public final void c(final String s) {
    }
    
    public final void a(final URL url, final String s) {
        rp_C.a(10, "showDocument(\"" + url.toString() + "\");");
        a().showDocument(url);
    }
    
    private static BasicService a() {
        try {
            return (BasicService)ServiceManager.lookup(BasicService.class.getName());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private static PersistenceService a() {
        try {
            return (PersistenceService)ServiceManager.lookup(PersistenceService.class.getName());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public final URL a(final String s, final String s2) {
        if (s2 == null || s2.length() == 0) {
            rp_C.a(1, "Returning null url for action: " + s + " path: " + s2);
            return null;
        }
        String s3;
        if (s2.indexOf(63) > 0) {
            s3 = s2 + '&';
        }
        else {
            s3 = s2 + '?';
        }
        final String string;
        return (string = s3 + s).startsWith("http") ? new URL(string) : new URL("http", this.a().getHost(), string);
    }
    
    public final InputStream b(String s) {
        rp_C.a(10, "getFileInputStream(" + s + ")");
        try {
            final InputStream resourceAsStream;
            if ((resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(s)) != null) {
                return resourceAsStream;
            }
        }
        catch (Exception ex) {}
        final URL url;
        if (!rp_C.a(url = new URL(rp_C.b(this.a() + s)))) {
            rp_C.a(4, rp_C.a("JNLPToolkit.getFileInputStream: ", new IllegalArgumentException("8873")));
            final rp_fb a = this.a.a().a();
            final JNLPEPApp a2 = (this = this).a;
            final rp_fb rp_fb = a;
            s = "err";
            final String a3 = rp_fb.a(0, s);
            final rp_fb rp_fb2 = a;
            s = "ant1";
            final String a4 = rp_fb2.a(0, s);
            final rp_fb rp_fb3 = a;
            s = "cl";
            rp_bd.a(a2, a3, a4, rp_fb3.a(0, s));
            return null;
        }
        final URLConnection openConnection;
        (openConnection = url.openConnection()).addRequestProperty("Accept-Encoding", "gzip");
        openConnection.setDoOutput(false);
        openConnection.setDoInput(true);
        openConnection.setUseCaches(true);
        rp_C.a(10, "AbstractNetworkToolkit.getFileInputStream URL: " + url);
        return rp_bZ.a(openConnection.getContentEncoding(), openConnection.getInputStream());
    }
    
    public final InputStream c(final String s) {
        return this.b(rp_aJ.b + s);
    }
    
    public final InputStream d(final String s) {
        byte[] byteArray;
        if ((byteArray = this.a.get(s)) == null) {
            final InputStream c = this.c(s);
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int read;
            while (-1 != (read = c.read())) {
                byteArrayOutputStream.write(read);
            }
            byteArray = byteArrayOutputStream.toByteArray();
            this.a.put(s, byteArray);
        }
        return new ByteArrayInputStream(byteArray);
    }
    
    public final void a(final String[] array) {
    }
    
    public final Image a(final String s, final boolean b) {
        return this.a(s);
    }
    
    public final Vector a() {
        try {
            String string = this.a().toString();
            rp_C.a(4, "Applet URL = " + string);
            try {
                if (string.equals("undefined")) {
                    string = null;
                }
            }
            catch (Exception ex2) {
                string = null;
            }
            final int lastIndex;
            if ((lastIndex = string.lastIndexOf(63)) == -1) {
                return null;
            }
            final String substring;
            if ((substring = string.substring(lastIndex + 1)) == null || substring.length() < 2) {
                return null;
            }
            Vector<String> vector = null;
            final StringTokenizer stringTokenizer = new StringTokenizer(substring, "&");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (vector == null) {
                    vector = new Vector<String>();
                }
                vector.addElement(nextToken);
            }
            return vector;
        }
        catch (Exception ex) {
            System.out.println("JNLPToolkit.getCmdlnArgs() " + ex);
            return null;
        }
    }
    
    public final Object a(String s, String s2) {
        rp_C.a(10, "JNLPToolkit.setProperty(" + s + ", " + s2 + ")");
        final rp_dh rp_dh = this;
        final String s3 = s;
        s2 = s2;
        s = s3;
        this = rp_dh;
        final BasicService a = a();
        final PersistenceService a2;
        if ((a2 = a()) != null && a != null) {
            try {
                final URL url = new URL(a.getCodeBase().toString() + s);
                try {
                    a2.delete(url);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                a2.create(url, 1024L);
                final DataOutputStream dataOutputStream;
                (dataOutputStream = new DataOutputStream(a2.get(url).getOutputStream(true))).writeUTF(s2);
                dataOutputStream.flush();
                dataOutputStream.close();
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        return null;
    }
    
    public final String a(final String s) {
        final String b = this.b(s);
        rp_C.a(10, "JNLPToolkit.getProperty(" + s + "):" + b + ")");
        return b;
    }
    
    private String b(final String s) {
        String utf = null;
        try {
            final BasicService a = a();
            final PersistenceService a2;
            if ((a2 = a()) != null && a != null) {
                final URL url = new URL(a.getCodeBase().toString() + s);
                System.out.println("muffinURL:" + url);
                utf = new DataInputStream(a2.get(url).getInputStream()).readUTF();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return utf;
    }
}
