import java.applet.Applet;
import netscape.javascript.JSObject;
import java.util.Calendar;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URLConnection;
import java.awt.Window;
import java.io.InputStream;
import java.util.StringTokenizer;
import java.util.Vector;
import java.net.URL;
import javax.swing.JOptionPane;
import java.awt.Frame;
import java.awt.Component;
import java.util.Date;
import java.util.Hashtable;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_dH extends rp_bZ
{
    private JApplet a;
    private String a;
    private Hashtable a;
    private String b;
    private boolean e;
    
    public rp_dH(final JApplet a) {
        this.a = new Hashtable();
        this.b = null;
        this.e = false;
        this.a = a;
        final StringBuffer sb;
        (sb = new StringBuffer()).append("1-");
        sb.append(Long.toString(new Date().getTime()));
        sb.append('-');
        sb.append(System.getProperty("java.version", "?").substring(0, 5));
        this.a = new String(sb);
        rp_C.a(2, "Applet ID: " + this.a);
        this.a();
    }
    
    public final Component a() {
        return this.a;
    }
    
    public final Frame a() {
        return JOptionPane.getFrameForComponent(this.a);
    }
    
    public final void c(final String s) {
        this.a.getAppletContext().showStatus(s);
    }
    
    public final void a(final URL url, final String s) {
        this.a.getAppletContext().showDocument(url, (s == null) ? "ePrevue" : s);
    }
    
    public final Vector a() {
        try {
            String string = this.a.getDocumentBase().toString();
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
            System.out.println("AppletToolkit.getCmdlnArgs() " + ex);
            return null;
        }
    }
    
    public final URL a() {
        return this.a.getCodeBase();
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
            rp_C.a(4, rp_C.a("AppletToolkit.getFileInputStream: ", new IllegalArgumentException("8873")));
            final rp_fb a = this.a.a().a();
            final Frame frameForComponent = JOptionPane.getFrameForComponent((this = this).a);
            final rp_fb rp_fb = a;
            s = "err";
            final String a2 = rp_fb.a(0, s);
            final rp_fb rp_fb2 = a;
            s = "ant1";
            final String a3 = rp_fb2.a(0, s);
            final rp_fb rp_fb3 = a;
            s = "cl";
            rp_bd.a(frameForComponent, a2, a3, rp_fb3.a(0, s));
            return null;
        }
        final URLConnection openConnection;
        (openConnection = url.openConnection()).addRequestProperty("Accept-Encoding", "gzip");
        openConnection.setDoOutput(false);
        openConnection.setDoInput(true);
        openConnection.setUseCaches(true);
        rp_C.a(10, "AppletToolkit.getFileInputStream URL: " + url);
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
    
    public final Image a(final String s, final boolean b) {
        return this.a(s);
    }
    
    public final Object a(String string, final String s) {
        final Calendar instance;
        (instance = Calendar.getInstance()).add(2, 1);
        string = rp_C.e(string) + "=" + rp_C.e(s) + ("; expires=" + instance.getTime().toString());
        ((JSObject)JSObject.getWindow((Applet)this.a).getMember("document")).setMember("cookie", (Object)string);
        this.e = false;
        return null;
    }
    
    private String a() {
        if (!this.e) {
            try {
                final String b;
                if ((b = (String)((JSObject)JSObject.getWindow((Applet)this.a).getMember("document")).getMember("cookie")) != null && b.length() > 0) {
                    this.b = b;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            if (this.b == null) {
                this.b = "?";
            }
            this.e = true;
        }
        return this.b;
    }
    
    public final String a(final String s) {
        final String a = this.a();
        final String string = rp_C.e(s) + "=";
        if (a.length() > 0) {
            final int index;
            if ((index = a.indexOf(string)) != -1) {
                final int n = index + string.length();
                int n2;
                if ((n2 = a.indexOf(";", n)) == -1) {
                    n2 = a.length();
                }
                return rp_C.d(a.substring(n, n2));
            }
            System.out.println("Did not find cookie: " + s);
        }
        return "";
    }
}
