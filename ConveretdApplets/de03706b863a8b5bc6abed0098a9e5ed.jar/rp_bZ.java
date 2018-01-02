import java.util.zip.InflaterInputStream;
import java.util.zip.Inflater;
import java.util.zip.GZIPInputStream;
import javax.imageio.ImageIO;
import java.awt.Toolkit;
import java.awt.MediaTracker;
import java.awt.Image;
import java.net.JarURLConnection;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.net.URLConnection;
import java.awt.Frame;
import java.net.MalformedURLException;
import java.io.InputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.Window;
import java.util.Vector;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class rp_bZ implements rp_ax, rp_fK
{
    protected rp_fx a;
    public rp_dF a;
    private boolean e;
    private int a;
    public boolean a;
    public boolean b;
    public boolean c;
    public boolean d;
    private String[] a;
    private String a;
    private Thread a;
    protected List a;
    
    public rp_bZ() {
        this.e = false;
        this.a = 0;
        this.a = false;
        this.b = false;
        this.c = false;
        this.d = false;
        this.a = null;
        this.a = null;
        this.a = Collections.synchronizedList(new LinkedList<Object>());
    }
    
    public abstract URL a();
    
    public abstract Vector a();
    
    public final void a(final rp_fx a) {
        this.a = a;
    }
    
    public final rp_fx a() {
        return this.a;
    }
    
    public final rp_dF a() {
        return this.a;
    }
    
    public void a(final String[] a) {
        this.a = a;
    }
    
    public final void a(final String a) {
        this.a = a;
    }
    
    public final void a(final boolean e) {
        if (!(this.e = e)) {
            this.a = 0;
        }
    }
    
    public final boolean a() {
        return this.a;
    }
    
    public final boolean a(String s, boolean b) {
        while (true) {
            rp_C.a(4, "checkUserLogin: " + s);
            if (this.e) {
                return true;
            }
            if (b) {
                return false;
            }
            if (1 != rp_bd.c(this.a(), this.a.a().a(), "wrn", "nlg", false)) {
                return false;
            }
            this.a.a.a(this, false, s);
            final rp_bZ rp_bZ = this;
            final String s2 = null;
            b = true;
            s = s2;
            this = rp_bZ;
        }
    }
    
    public final void b(final String s) {
        if (s == null) {
            this.a = 0;
            rp_C.a(10, "UserLevel reset to NONE");
            return;
        }
        if (s.equals("NONE")) {
            this.a = 0;
        }
        if (s.equals("NOVICE")) {
            this.a = 10;
        }
        if (s.equals("EXPERT")) {
            this.a = 15;
        }
        rp_C.a(10, "UserLevel set to " + s);
    }
    
    public final int a() {
        return this.a;
    }
    
    public final boolean b(final String s, final boolean b) {
        rp_C.a(10, "checkUserRights:" + s);
        if (!this.e && (s.equals("cmLoad") || s.equals("cmWL"))) {
            if (!b) {
                final rp_fb a = this.a.a().a();
                rp_bd.a(this.a(), a.a(0, "err"), a.a("nacc"), a.a(0, "cl"));
            }
            return false;
        }
        if (this.a && (s.equals("cmLoad") || s.equals("cmRequestinfo"))) {
            if (!b) {
                final rp_fb a2 = this.a.a().a();
                rp_bd.a(this.a(), a2.a(0, "wrn"), a2.a(0, "ap2"), a2.a(0, "cl"));
            }
            return false;
        }
        return this.b || (!s.equals("cmSaveImage") && !s.equals("cmRequestinfo") && !s.equals("cmEmail") && !s.equals("cmSaveAs"));
    }
    
    public final rp_ap a() {
        final InputStream a = this.a("event=manageAccount&action=account_get_info");
        try {
            final BufferedReader bufferedReader;
            final String line;
            if ((line = (bufferedReader = new BufferedReader(new InputStreamReader(a))).readLine()) != null && line.equalsIgnoreCase("OK")) {
                bufferedReader.readLine();
                if (line.equalsIgnoreCase("version-1")) {
                    System.out.println("account_get_info format with higher version");
                }
                return new rp_ap(bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine(), "", "");
            }
            bufferedReader.close();
        }
        catch (IOException ex) {
            return null;
        }
        catch (Exception ex2) {
            return null;
        }
        return null;
    }
    
    public final boolean b() {
        final String a;
        if ((a = a(this.a("event=manageAccount&action=logout"))) != null) {
            final rp_fb a2 = this.a.a().a();
            rp_bd.a(this.a(), a2.a(0, "err"), a, a2.a(0, "cl"));
            return false;
        }
        this.a(false);
        return true;
    }
    
    public final rp_aV a(final String s) {
        return this.a.a.a().a(s);
    }
    
    public final InputStream a(final int n, final Object o) {
        switch (n) {
            case 100: {
                final String string;
                if ((string = rp_aJ.e + rp_aJ.a + rp_au.a.a("srv_gen_file", "")) != null && string.length() != 0) {
                    return this.c(string);
                }
                return null;
            }
            case 103: {
                return this.a("action=collections2");
            }
            case 5: {
                return this.a("action=catalogs");
            }
            case 6: {
                return this.a("action=brands");
            }
            case 104: {
                return this.a("action=types");
            }
            case 204: {
                return this.a("action=type&stamp=120&typeSubtypeID=" + (String)o);
            }
            case 30: {
                return this.a("action=custom-add" + (String)o);
            }
            case 31: {
                return this.a("action=custom-update" + (String)o);
            }
            case 32: {
                return this.a("action=custom-delete" + (String)o);
            }
            case 10: {
                return this.a("action=plan-delete&name=" + rp_C.e((String)o));
            }
            case 4: {
                return this.a("action=get-user-picked-items");
            }
            case 20: {
                String a = "";
                if (o != null && o instanceof String[]) {
                    a = a((String[])o);
                }
                return this.a("action=get-basic-templates" + a);
            }
            default: {
                return null;
            }
        }
    }
    
    private static String a(final String[] array) {
        String string = "";
        int i = 0;
        int n = 1;
        while (i < array.length) {
            if (array[i] != null && array[i].length() > 0) {
                string = string + "&bt" + n++ + "=" + rp_C.b(array[i]);
            }
            ++i;
        }
        return string;
    }
    
    public final InputStream a(final int n, final String s, final int n2, final int n3) {
        String s2 = null;
        switch (n) {
            case 203: {
                s2 = "action=collection2&typeSubtypeID=" + s;
                break;
            }
            case 204: {
                s2 = "action=type&typeSubtypeID=" + s;
                break;
            }
            case 2: {
                s2 = "action=custom-get";
                break;
            }
            default: {
                return null;
            }
        }
        if (n2 != 0) {
            s2 = s2 + "&from=" + n2;
        }
        if (n3 != -1) {
            s2 = s2 + "&count=" + n3;
        }
        return this.a(s2 + "&stamp=120");
    }
    
    public final InputStream a(rp_k rp_k) {
        final StringBuilder append = new StringBuilder().append("action=search2" + "&stamp=120");
        rp_k = rp_k;
        final StringBuffer sb;
        (sb = new StringBuffer("&name=")).append(rp_C.e(rp_k.a));
        if (rp_k.b != null && rp_k.b.length() > 0) {
            sb.append("&typeSubtypeID=");
            sb.append(rp_C.e(rp_k.b));
        }
        if (rp_k.a > 0 || rp_k.b > 0 || rp_k.c > 0) {
            sb.append("&limit-type=");
            sb.append((rp_k.a == rp_dQ.b) ? "max" : "min");
            if (rp_k.a > 0) {
                sb.append("&width=" + rp_k.a);
            }
            if (rp_k.b > 0) {
                sb.append("&depth=" + rp_k.b);
            }
            if (rp_k.c > 0) {
                sb.append("&height=" + rp_k.c);
            }
        }
        sb.append("&from=" + rp_k.d);
        sb.append("&count=" + rp_k.e);
        return this.a(append.append(sb.toString()).toString());
    }
    
    public final URL a(final String s) {
        try {
            return new URL("http", this.a().getHost(), rp_C.b(s));
        }
        catch (MalformedURLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    protected final void a() {
        (this.a = new Thread(new rp_ev(this))).setName("HTTP Request Worker Thread");
        this.a.start();
    }
    
    public final InputStream a(String s) {
        try {
            final URL a;
            if ((a = this.a(s, rp_aJ.c)) == null) {
                return null;
            }
            rp_C.a(4, "serverRequest: " + a.toString());
            if (!rp_C.a(a)) {
                rp_C.a(4, rp_C.a("sendServerRequest: ", new IllegalArgumentException("8873")));
                final rp_fb a2 = this.a.a().a();
                final Frame a3 = this.a();
                final rp_fb rp_fb = a2;
                s = "err";
                final String a4 = rp_fb.a(0, s);
                final rp_fb rp_fb2 = a2;
                s = "ant1";
                final String a5 = rp_fb2.a(0, s);
                final rp_fb rp_fb3 = a2;
                s = "cl";
                rp_bd.a(a3, a4, a5, rp_fb3.a(0, s));
                return null;
            }
            final URLConnection openConnection;
            (openConnection = a.openConnection()).addRequestProperty("Accept-Encoding", "gzip");
            if (this.a != null) {
                openConnection.setRequestProperty("Cookie", this.a);
            }
            openConnection.setDoOutput(true);
            openConnection.setDoInput(true);
            openConnection.setUseCaches(false);
            openConnection.setRequestProperty("Content-Length", String.valueOf(0));
            openConnection.setRequestProperty("Content-Type", "text/plain");
            return a(openConnection.getContentEncoding(), openConnection.getInputStream());
        }
        catch (IOException ex) {
            System.out.println("AbstractNetworkToolkit:sendServerRequest: IOException");
            System.out.println(ex);
            return null;
        }
    }
    
    public final rp_fq a(final ByteArrayOutputStream a, final String b, final String c, final String d, boolean n) {
        if (!b2) {
            return this.a(a, b, c, d);
        }
        ((rp_cK)(n = (int)new rp_cK(this))).a = true;
        ((rp_cK)n).a = a;
        ((rp_cK)n).b = b;
        ((rp_cK)n).c = c;
        ((rp_cK)n).d = d;
        ((rp_cK)n).a = null;
        this.a.add(n);
        while (!((rp_cK)n).b) {
            try {
                Thread.sleep(10L);
                continue;
            }
            catch (InterruptedException ex) {
                System.err.println("Send server write request interrupted due to: " + ex.getMessage());
                ex.printStackTrace();
            }
            break;
        }
        if (((rp_cK)n).c) {
            if (((rp_cK)n).a != null) {
                System.err.println("Write Request failed due to: " + ((rp_cK)n).a.getMessage());
            }
            else {
                System.err.println("Write Request failed!");
            }
            return new rp_bg("ERROR\n");
        }
        return ((rp_cK)n).a;
    }
    
    public final rp_fq a(final ByteArrayOutputStream byteArrayOutputStream, String s, final String s2, final String s3) {
        try {
            final int size = byteArrayOutputStream.size();
            s = "action=" + s;
            if (s2 != null) {
                s += s2;
            }
            s = s + "&length=" + String.valueOf(size);
            s += "&epPVer=2";
            final URL a = this.a(s, rp_aJ.c);
            System.out.println("writeStreamToServer URL: " + a.toString());
            if (!rp_C.a(a)) {
                rp_C.a(4, rp_C.a("writeStreamToServer: ", new IllegalArgumentException("8873")));
                final rp_fb a2 = this.a.a().a();
                rp_bd.a(this.a(), a2.a(0, "err"), a2.a(0, "ant1"), a2.a(0, "cl"));
                return null;
            }
            final URLConnection openConnection;
            (openConnection = a.openConnection()).addRequestProperty("Accept-Encoding", "gzip");
            if (this.a != null) {
                openConnection.setRequestProperty("Cookie", this.a);
            }
            if (this.a != null) {
                openConnection.setRequestProperty("Cookie", this.a);
            }
            openConnection.setDoOutput(true);
            openConnection.setDoInput(true);
            openConnection.setUseCaches(false);
            openConnection.setRequestProperty("Content-Length", String.valueOf(size));
            openConnection.setRequestProperty("Content-Type", s3);
            final DataOutputStream dataOutputStream = new DataOutputStream(openConnection.getOutputStream());
            byteArrayOutputStream.writeTo(dataOutputStream);
            dataOutputStream.flush();
            dataOutputStream.close();
            byteArrayOutputStream.close();
            return new rp_bg(a(openConnection.getContentEncoding(), openConnection.getInputStream()));
        }
        catch (MalformedURLException ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
        catch (IOException ex2) {
            System.out.println(ex2);
            ex2.printStackTrace();
        }
        catch (Throwable t) {
            System.out.println(t);
            t.printStackTrace();
        }
        return new rp_bg("ERROR\nInternal error.");
    }
    
    public URL a(final String s, String s2) {
        if ((s2 = s2) == null || s2.length() == 0) {
            return null;
        }
        if (s2.indexOf(63) > 0) {
            s2 += '&';
        }
        else {
            s2 += '?';
        }
        if ((s2 += s).startsWith("http")) {
            return new URL(s2);
        }
        return new URL("http", this.a().getHost(), s2);
    }
    
    public final URL a(String s, String line, final int n) {
        try {
            s = "action=stamp&SKU=" + rp_C.e(s);
            if (line != null) {
                s = s + "&Catalog=" + line;
            }
            s = s + "&ImageSize=" + n;
            final BufferedReader bufferedReader;
            line = (bufferedReader = new BufferedReader(new InputStreamReader(this.a(s)))).readLine();
            final String line2 = bufferedReader.readLine();
            URL url = null;
            if (line != null && line2 != null) {
                if (line.equalsIgnoreCase("OK")) {
                    url = new URL("http", this.a().getHost(), rp_C.b(line2));
                }
                if (line.equalsIgnoreCase("ERROR")) {
                    System.out.println("Server response: ERROR " + line2);
                }
            }
            bufferedReader.close();
            return url;
        }
        catch (IOException ex) {
            System.out.println("AppletToolkit.getStampUrl: IOException");
            System.out.println(ex);
            return null;
        }
    }
    
    public final void a(Object o) {
        try {
            if (rp_aJ.c != null) {
                boolean a = false;
                if (this.a != null && this.a.a != null) {
                    a = this.a.a.a;
                }
                final InputStream a2 = this.a("action=keep-alive&dirty=" + (a ? "true" : "false"));
                try {
                    final BufferedReader bufferedReader;
                    final String line = (bufferedReader = new BufferedReader(new InputStreamReader(a2))).readLine();
                    rp_C.a(10, "LINE-1:" + line);
                    boolean b = false;
                    String line2;
                    while (null != (line2 = bufferedReader.readLine())) {
                        if (line2.startsWith("MESS:")) {
                            final rp_fb a3 = this.a.a().a();
                            rp_bd.a(this.a(), a3.a(0, "wrn"), line2.substring(5), a3.a(0, "cl"));
                        }
                        else {
                            if (line2.startsWith("REDIR:")) {
                                rp_C.a(4, "redirecting to: " + line2.substring(6));
                                this.a(new URL(line2.substring(6)), null);
                                return;
                            }
                            if (line2.startsWith("DIRTY:")) {
                                this.a.a.a(!line2.substring(6).equalsIgnoreCase("false"));
                            }
                            else if (line2.startsWith("PROP:")) {
                                final String[] split;
                                if ((split = line2.substring(5).split("=")).length == 2) {
                                    this.a.a().a(split[0], split[1]);
                                    b = true;
                                }
                                else {
                                    System.out.println("Wrong sysntax:" + line2);
                                }
                            }
                            else {
                                System.out.println("Unknown command:" + line2);
                            }
                        }
                    }
                    if (b) {
                        this.a.a().b();
                    }
                    final int a4;
                    if ((a4 = rp_C.a(line)) > 0) {
                        o = new Integer(a4 * 1000);
                    }
                }
                catch (IOException ex) {}
            }
        }
        finally {
            int intValue = 1200000;
            if (o != null) {
                intValue = (int)o;
            }
            rp_C.a(10, "Timer set to " + intValue + "ms");
            new rp_eQ(this, intValue, o).start();
        }
    }
    
    private static String a(final InputStream inputStream) {
        try {
            final BufferedReader bufferedReader;
            final String line = (bufferedReader = new BufferedReader(new InputStreamReader(inputStream))).readLine();
            final String line2 = bufferedReader.readLine();
            if (line != null) {
                if (line.equalsIgnoreCase("OK")) {
                    return null;
                }
                if (line.equalsIgnoreCase("ERROR") && line2 != null) {
                    return line2;
                }
            }
            bufferedReader.close();
        }
        catch (IOException ex) {
            return "IOException";
        }
        return "Unknown error.";
    }
    
    public final String a(String line, String line2) {
        try {
            String s = "action=get-display-sku&SKU=" + rp_C.e(line);
            if (line2 != null) {
                s = s + "&Catalog=" + line2;
            }
            final BufferedReader bufferedReader;
            line = (bufferedReader = new BufferedReader(new InputStreamReader(((rp_dH)rp_au.a.a()).a(s)))).readLine();
            line2 = bufferedReader.readLine();
            bufferedReader.close();
            if (line != null && line2 != null) {
                if (line.equalsIgnoreCase("OK")) {
                    return line2;
                }
                if (line.equalsIgnoreCase("ERROR")) {
                    System.out.println("Server response: ERROR " + line2);
                    return "";
                }
            }
            return null;
        }
        catch (IOException ex) {
            System.out.println("AppletToolkit.getStampUrl: IOException");
            System.out.println(ex);
            return "";
        }
    }
    
    private URL b(final String s, final String s2) {
        try {
            final URL url;
            if (((JarURLConnection)(url = new URL("jar:" + this.a() + s + "!/" + s2)).openConnection()).getJarFile() != null) {
                return url;
            }
        }
        catch (IOException ex) {
            System.out.println("IO: " + ex);
        }
        catch (Exception ex2) {
            System.out.println("Exception:" + ex2);
            ex2.printStackTrace();
        }
        return null;
    }
    
    protected final Image a(final String s) {
        final MediaTracker mediaTracker = new MediaTracker(this.a());
        Image image = null;
        try {
            URL url = null;
            if (this.a != null) {
                for (int i = Math.max(9, this.a.length - 1); i > 0; --i) {
                    if (this.a[i] != null) {
                        url = this.b(this.a[i], s);
                    }
                }
            }
            if (url == null) {
                url = this.getClass().getClassLoader().getResource(s);
            }
            if (!rp_C.a(url)) {
                rp_C.a(4, rp_C.a("getImageNow: ", new IllegalArgumentException("8873")));
                final rp_fb a = this.a.a().a();
                rp_bd.a(this.a(), a.a(0, "err"), a.a(0, "ant1"), a.a(0, "cl"));
                return null;
            }
            if ((image = Toolkit.getDefaultToolkit().getImage(url)) != null) {
                mediaTracker.addImage(image, 0);
                mediaTracker.waitForID(0);
            }
        }
        catch (InterruptedException ex) {}
        if (mediaTracker.isErrorID(0)) {
            System.out.println("ERROR loading image: " + s);
            return null;
        }
        return image;
    }
    
    public final Image a(final URL url) {
        if (!rp_C.a(url)) {
            rp_C.a(4, rp_C.a("getImage: ", new IllegalArgumentException("8873")));
            final rp_fb a = this.a.a().a();
            rp_bd.a(this.a(), a.a(0, "err"), a.a(0, "ant1"), a.a(0, "cl"));
            return null;
        }
        try {
            final URLConnection openConnection;
            (openConnection = url.openConnection()).setUseCaches(false);
            return ImageIO.read(openConnection.getInputStream());
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    protected static InputStream a(final String s, final InputStream inputStream) {
        InputStream inputStream2;
        if (s != null && s.equalsIgnoreCase("gzip")) {
            inputStream2 = new GZIPInputStream(inputStream);
        }
        else if (s != null && s.equalsIgnoreCase("deflate")) {
            inputStream2 = new InflaterInputStream(inputStream, new Inflater(true));
        }
        else {
            inputStream2 = inputStream;
        }
        return inputStream2;
    }
    
    public final String b(final String s, final String s2) {
        final String a;
        if ((a = this.a(s)) == null || a.length() == 0) {
            return s2;
        }
        return a;
    }
    
    public final int a(final String s, final int n) {
        this.a(s, "" + n);
        return 0;
    }
    
    public final int b(final String s, final int n) {
        String a = "";
        try {
            return Integer.parseInt(a = this.a(s));
        }
        catch (Exception ex) {
            rp_C.a(4, "getPropertyInt exception: " + s + "=" + a);
            return n;
        }
    }
    
    public final URL a(final rp_v rp_v, final int n, final int n2) {
        try {
            return this.a("action=file-system&cmd=plan-stamp" + rp_v.a(-1) + "&width=" + 200 + "&height=" + 150, rp_aJ.c);
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public final rp_fq a(final rp_aw rp_aw, final rp_v[] array, final Object o) {
        switch (rp_ex.a[rp_aw.ordinal()]) {
            case 1: {
                return new rp_aS(this.a("action=file-system&cmd=get-tree"));
            }
            case 2: {
                return new rp_aS(this.a("action=file-system&cmd=get-folder-tree"));
            }
            case 3: {
                if (array == null || array.length != 1) {
                    rp_C.a(1, "fsCommand: wrong parameters");
                    return new rp_bg("ERROR\nInternal error.");
                }
                final int a = array[0].a;
                return new rp_aS(this.a("action=file-system&cmd=get-folder-content&parent=" + ((a == -1) ? "null" : a)));
            }
            case 4: {
                if (array == null || array.length != 1) {
                    rp_C.a(1, "fsCommand: wrong parameters");
                    return new rp_bg("ERROR\nInternal error.");
                }
                final int a2 = array[0].a;
                return new rp_aS(this.a("action=file-system&cmd=get-favorites&parent=" + ((a2 == -1) ? "null" : a2) + "&stamp=120"));
            }
            case 5: {
                if (array == null || array.length != 1) {
                    rp_C.a(1, "fsCommand: wrong parameters");
                    return new rp_bg("ERROR\nInternal error.");
                }
                return new rp_aS(this.a("action=file-system&cmd=plan-load" + array[0].a(-1)));
            }
            case 6: {
                if (array == null || array.length != 1 || o == null || !(o instanceof rp_fU)) {
                    rp_C.a(1, "fsCommand: wrong parameters");
                    return new rp_bg("ERROR\nInternal error.");
                }
                final rp_fU rp_fU = (rp_fU)o;
                try {
                    return this.a(rp_fU.a(), "file-system", "&cmd=plan-save" + array[0].a(-1) + "&description=" + rp_C.b(array[0].b), "text/xml");
                }
                catch (IOException ex) {
                    return new rp_bg("ERROR\nInternal error.");
                }
            }
            case 7: {
                if (array == null || array.length != 1 || o == null || !(o instanceof String)) {
                    rp_C.a(1, "fsCommand: wrong parameters");
                    return new rp_bg("ERROR\nInternal error.");
                }
                return new rp_bg(this.a("action=file-system&cmd=folder-new&parent=" + array[0].a() + "&new-name=" + rp_C.b((String)o)));
            }
            case 8: {
                if (array == null || array.length == 0) {
                    rp_C.a(1, "fsCommand: wrong parameters");
                    return new rp_bg("ERROR\nInternal error.");
                }
                String string = "";
                for (int i = 0; i < array.length; ++i) {
                    string += array[i].a(i + 1);
                }
                return new rp_bg(this.a("action=file-system&cmd=delete" + string));
            }
            case 9: {
                if (array == null || array.length == 0 || o == null || !(o instanceof rp_v) || !((rp_v)o).a()) {
                    rp_C.a(1, "fsCommand: wrong parameters");
                    return new rp_bg("ERROR\nInternal error.");
                }
                String string2 = "";
                for (int j = 0; j < array.length; ++j) {
                    string2 += array[j].a(j + 1);
                }
                return new rp_bg(this.a("action=file-system&cmd=move" + (string2 + "&new-location=" + ((rp_v)o).a())));
            }
            case 10: {
                if (array == null || array.length != 1 || o == null || !(o instanceof String)) {
                    rp_C.a(1, "fsCommand: wrong parameters");
                    return new rp_bg("ERROR\nInternal error.");
                }
                return new rp_bg(this.a("action=file-system&cmd=rename" + array[0].a(-1) + "&new-name=" + rp_C.b((String)o)));
            }
            case 11:
            case 12: {
                if (array == null || array.length != 1 || o == null || !(o instanceof rp_fU)) {
                    rp_C.a(1, "fsCommand: wrong parameters");
                    return new rp_bg("ERROR\nInternal error.");
                }
                final rp_fU rp_fU2 = (rp_fU)o;
                final String s = (rp_aw == rp_aw.k) ? "&cmd=mail" : "&cmd=request-info";
                try {
                    return this.a(rp_fU2.a(), "file-system", s + array[0].a(-1), "text/xml");
                }
                catch (IOException ex2) {
                    return new rp_bg("ERROR\nInternal error.");
                }
                break;
            }
        }
        rp_C.a(1, "fsCommand: unknown command " + rp_aw);
        return new rp_bg("ERRROR\nUnknown command");
    }
}
