import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import ru.zhuk.graphics.codec.i;
import java.applet.AppletContext;
import java.net.URLConnection;
import java.util.Hashtable;
import java.awt.Cursor;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.StringTokenizer;
import ru.zhuk.graphics.codec.g;
import ru.zhuk.graphics.codec.a;
import java.io.OutputStream;
import ru.zhuk.graphics.codec.e;
import java.io.ByteArrayOutputStream;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.io.IOException;
import ru.zhuk.gui.d;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.Vector;
import ru.zhuk.gui.b;
import ru.zhuk.graphics.codec.c;
import java.net.URL;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DrawCanvas extends Applet implements Runnable
{
    private static Image p;
    private static String d;
    private static DrawCanvas o;
    private URL h;
    private final int j = 0;
    private final int l = 1;
    private final int q = 2;
    private int m;
    private int f;
    private c b;
    public b a;
    private static Vector k;
    long r;
    private static char[] g;
    private String n;
    private int e;
    private boolean i;
    private Component c;
    
    public DrawCanvas() {
        this.f = 75;
        this.b = new c();
        this.r = a();
    }
    
    public void init() {
        Color white = Color.white;
        Image image = null;
        this.h = this.getDocumentBase();
        final String parameter = this.getParameter("bgload");
        if (parameter != null) {
            try {
                final byte[] a = ru.zhuk.util.b.a(new URL(this.h, parameter).openConnection().getInputStream());
                this.m = (c(a) ? 0 : (b(a) ? 2 : this.b.a(a)));
                image = a(a, this);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            final String parameter2 = this.getParameter("bgimage");
            if (parameter2 != null) {
                try {
                    final byte[] a2 = ru.zhuk.util.c.a(parameter2);
                    this.m = (c(a2) ? 0 : (b(a2) ? 2 : this.b.a(a2)));
                    image = a(a2, this);
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
        final String parameter3 = this.getParameter("bgcolor");
        if (parameter3 != null) {
            try {
                white = new Color(Integer.decode(parameter3));
            }
            catch (NumberFormatException ex3) {}
        }
        int int1 = 5;
        final String parameter4 = this.getParameter("undo_buffer");
        if (parameter4 != null) {
            try {
                int1 = Integer.parseInt(parameter4);
            }
            catch (NumberFormatException ex4) {}
        }
        this.a = new b(white, image, int1);
        this.setLayout(new BorderLayout());
        final ru.zhuk.gui.c c = new ru.zhuk.gui.c(new BorderLayout());
        c.setBackground(white);
        c.add(this.a, "Center");
        this.add(c, "Center");
        DrawCanvas.o = this;
        try {
            final String parameter5 = this.getParameter("jpeg_quality");
            if (parameter5 != null) {
                this.f = Integer.parseInt(parameter5);
                if (this.f < 1) {
                    this.f = 1;
                }
                else if (this.f > 100) {
                    this.f = 100;
                }
            }
            final String parameter6 = this.getParameter("gripsize");
            if (parameter6 != null) {
                final int int2 = Integer.parseInt(parameter6);
                if (int2 > 0) {
                    ru.zhuk.gui.d.d = int2;
                }
            }
            final String parameter7 = this.getParameter("snap");
            if (parameter7 != null) {
                this.a.d = Integer.parseInt(parameter7);
            }
        }
        catch (NumberFormatException ex5) {}
    }
    
    public static DrawCanvas d() throws InterruptedException {
        return DrawCanvas.o;
    }
    
    public void start() {
        final String parameter = this.getParameter("pageid");
        if (parameter == null || DrawCanvas.d == null || parameter.compareTo(DrawCanvas.d) != 0) {
            String s = this.getParameter("image");
            final boolean b = s != null;
            if (!b) {
                s = this.getParameter("load");
            }
            try {
                if (s != null && s.length() > 0) {
                    if (b) {
                        this.SetImage(s);
                    }
                    else {
                        this.LoadImage(s);
                    }
                }
                else {
                    this.a.c();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (DrawCanvas.p != null) {
            this.a.a(DrawCanvas.p, 0, 0);
        }
    }
    
    public void SetImage(final String s) {
        final byte[] a = ru.zhuk.util.c.a(s);
        if (a != null) {
            this.d(a);
        }
    }
    
    public void LoadImage(final String s) throws IOException, MalformedURLException {
        this.d(ru.zhuk.util.b.a(new URL(this.h, s).openConnection().getInputStream()));
    }
    
    public void destroy() {
        DrawCanvas.o = null;
        if (this.getParameter("pageid") != null) {
            DrawCanvas.p = this.a.a(-1, -1);
            DrawCanvas.d = this.getParameter("pageid");
        }
    }
    
    private void d(final byte[] array) {
        try {
            int intValue = 0;
            int intValue2 = 0;
            try {
                final String parameter;
                if ((parameter = this.getParameter("posx")) != null) {
                    intValue = Integer.decode(parameter);
                }
                final String parameter2;
                if ((parameter2 = this.getParameter("posy")) != null) {
                    intValue2 = Integer.decode(parameter2);
                }
            }
            catch (NumberFormatException ex2) {}
            this.m = (c(array) ? 0 : (b(array) ? 2 : (this.b.a(array) ? 1 : 0)));
            final Image a = a(array, this);
            if (a != null) {
                this.a.a(a, intValue, intValue2);
            }
            else {
                this.getAppletContext().showStatus("Error while creating image");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private int a(final String s) {
        if ("JPEG".equalsIgnoreCase(s) || "JPG".equalsIgnoreCase(s)) {
            return 1;
        }
        if ("GIF".equalsIgnoreCase(s)) {
            return 2;
        }
        if ("PNG".equalsIgnoreCase(s)) {
            return 0;
        }
        return this.m;
    }
    
    public String GetImageType() {
        return (new String[] { "PNG", "JPEG", "GIF" })[this.m];
    }
    
    public String GetImage(final int n, final String s) {
        final Dimension size = this.a.getSize();
        int n2 = size.width;
        int n3 = size.height;
        if (n > 0) {
            n2 = Math.max(n2 / n, 1);
            n3 = Math.max(n3 / n, 1);
        }
        return this.a(n2, n3, s, this.f);
    }
    
    public String GetImage(final String s) {
        return this.a(-1, -1, s, this.f);
    }
    
    public String GetImage(final int n, final int n2, final String s) {
        return this.a(n, n2, s, this.f);
    }
    
    private String a(final int n, final int n2, final String s, final int f) {
        try {
            this.m = this.a(s);
            if (this.m == 1) {
                this.f = f;
            }
            final byte[] a = this.a(n, n2);
            if (a != null) {
                return ru.zhuk.util.c.a(a);
            }
            final String parameter = this.getParameter("error_page");
            if (parameter != null) {
                this.getAppletContext().showDocument(new URL(this.getCodeBase(), parameter));
            }
            return "";
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public boolean IsChanged() {
        return this.a.d();
    }
    
    private byte[] a(final Image image, final Component component) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        switch (this.m) {
            case 1: {
                final e e = new e(component, image, this.f, byteArrayOutputStream);
                e.a(this.b.b(), this.b.c(), this.b.a());
                e.a();
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
            case 2: {
                if (ru.zhuk.graphics.codec.a.a(byteArrayOutputStream, image, 5, null, this) != 0) {
                    throw new IOException("error creating GIF file");
                }
                return byteArrayOutputStream.toByteArray();
            }
            default: {
                return new g(component, image).d();
            }
        }
    }
    
    private byte[] a(final int n, final int n2) {
        try {
            this.b();
        }
        catch (Exception ex2) {
            return null;
        }
        if (DrawCanvas.k != null) {
            for (int i = 0; i < DrawCanvas.k.size(); ++i) {
                try {
                    final StringTokenizer stringTokenizer = new StringTokenizer(new String(this.a(ru.zhuk.util.c.a(DrawCanvas.k.elementAt(i)))), " \t");
                    stringTokenizer.nextToken();
                    final String nextToken = stringTokenizer.nextToken();
                    final String a = ru.zhuk.util.b.a(this.h.toExternalForm());
                    if (Long.parseLong(stringTokenizer.nextToken()) > this.r && (a.length() == 0 || nextToken.equals(a) || (nextToken.startsWith(".") && a.endsWith(nextToken.substring(1))))) {
                        return this.a(this.a.a(n, n2), this);
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }
    
    private Vector b() throws Exception {
        if (DrawCanvas.k == null) {
            if (DrawCanvas.g[0] == 'k') {
                for (int i = 0; i < DrawCanvas.g.length; ++i) {
                    final char[] g = DrawCanvas.g;
                    final int n = i;
                    ++g[n];
                }
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(new String(ru.zhuk.util.b.a(new URL(this.getCodeBase(), new String(DrawCanvas.g)).openConnection().getInputStream())).trim(), "\n\r");
            DrawCanvas.k = new Vector();
            while (stringTokenizer.hasMoreTokens()) {
                DrawCanvas.k.addElement(stringTokenizer.nextToken());
            }
        }
        return DrawCanvas.k;
    }
    
    private void a(final byte[] array, final byte[] array2) {
        final int[] array3 = { -147217920, 54998033, -285199294, 25 };
        int n = (array[0] & 0xFF) << 24 | (array[1] & 0xFF) << 16 | (array[2] & 0xFF) << 8 | (array[3] & 0xFF);
        int n2 = (array[4] & 0xFF) << 24 | (array[5] & 0xFF) << 16 | (array[6] & 0xFF) << 8 | (array[7] & 0xFF);
        int n3 = 32;
        int n4 = -957401312;
        while (n3-- > 0) {
            n2 -= (n << 4 ^ n >>> 5) + (n ^ n4) + array3[n4 >>> 11 & 0x3];
            n4 += 1640531527;
            n -= (n2 << 4 ^ n2 >>> 5) + (n2 ^ n4) + array3[n4 & 0x3];
        }
        array2[0] = (byte)(n >>> 24);
        array2[1] = (byte)(n >>> 16 & 0xFF);
        array2[2] = (byte)(n >>> 8 & 0xFF);
        array2[3] = (byte)(n & 0xFF);
        array2[4] = (byte)(n2 >>> 24);
        array2[5] = (byte)(n2 >>> 16 & 0xFF);
        array2[6] = (byte)(n2 >>> 8 & 0xFF);
        array2[7] = (byte)(n2 & 0xFF);
    }
    
    private void a(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final byte[] array = new byte[8];
        final byte[] array2 = new byte[8];
        int read;
        while ((read = inputStream.read(array)) == 8) {
            this.a(array, array2);
            outputStream.write(array2);
        }
        if (read > 0) {
            throw new IOException();
        }
    }
    
    private byte[] a(final byte[] array) throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.a(byteArrayInputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    
    public void run() {
        if (this.c != null) {
            this.c.setCursor(new Cursor(3));
        }
        this.m = this.a(this.getParameter("save_format"));
        try {
            final byte[] a = this.a(-1, -1);
            byte[] a2 = null;
            final String parameter = this.getParameter("thumbnail");
            if (parameter != null) {
                try {
                    final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",x");
                    a2 = this.a(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()));
                }
                catch (Exception ex2) {
                    System.err.println("invalid thumbnail specifier: ".concat(String.valueOf(String.valueOf(parameter))));
                }
            }
            if (a != null) {
                final String[] array = { "image.png", "image.jpg", "image.gif" };
                final String[] array2 = { "image/png", "image/jpeg", "image/gif" };
                final URL url = new URL(this.h, this.n);
                final URLConnection openConnection = url.openConnection();
                InputStream inputStream;
                if (this.e == 1) {
                    final Hashtable<String, String> hashtable = new Hashtable<String, String>(6);
                    hashtable.put("image", ru.zhuk.util.c.a(a));
                    hashtable.put("image_type", array2[this.m]);
                    hashtable.put("image_name", array[this.m]);
                    if (a2 != null) {
                        hashtable.put("thumbnail", ru.zhuk.util.c.a(a2));
                        hashtable.put("thumbnail_type", array2[this.m]);
                        hashtable.put("thumbnail_name", "th_".concat(String.valueOf(String.valueOf(array[this.m]))));
                    }
                    inputStream = ru.zhuk.util.b.a(openConnection, hashtable);
                }
                else {
                    final Hashtable<String, ru.zhuk.util.a> hashtable2 = new Hashtable<String, ru.zhuk.util.a>(2);
                    final ru.zhuk.util.a a3 = new ru.zhuk.util.a();
                    a3.a = a;
                    a3.b = array[this.m];
                    a3.c = array2[this.m];
                    hashtable2.put("image", a3);
                    if (a2 != null) {
                        final ru.zhuk.util.a a4 = new ru.zhuk.util.a();
                        a4.a = a2;
                        a4.b = "th_".concat(String.valueOf(String.valueOf(array[this.m])));
                        a4.c = array2[this.m];
                        hashtable2.put("thumbnail", a4);
                    }
                    inputStream = ru.zhuk.util.b.b(openConnection, hashtable2);
                }
                this.a(new String(ru.zhuk.util.b.a(inputStream)), url);
            }
            else {
                final String parameter2 = this.getParameter("error_page");
                if (parameter2 != null) {
                    this.getAppletContext().showDocument(new URL(this.getCodeBase(), parameter2));
                }
            }
        }
        catch (Exception ex) {
            this.getAppletContext().showStatus(ex.getMessage());
            ex.printStackTrace();
        }
        this.i = false;
        this.a.a((String)null);
        if (this.c != null) {
            this.c.setCursor(new Cursor(0));
        }
    }
    
    public void a(final String n, final int e, final Component c, final String s) {
        final String parameter = this.getParameter("blockunchanged");
        if (parameter != null && !this.IsChanged()) {
            this.getAppletContext().showStatus(parameter);
            return;
        }
        if (this.i) {
            return;
        }
        this.i = true;
        if (n != null) {
            try {
                this.n = n;
                this.e = e;
                this.c = c;
                this.a.a(s);
                new Thread(this).start();
            }
            catch (Exception ex) {
                this.getAppletContext().showStatus(ex.getMessage());
            }
        }
    }
    
    private void a(final String s, final URL url) {
        if (s == null) {
            return;
        }
        final AppletContext appletContext = this.getAppletContext();
        String s2 = null;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\r\n");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final int index = nextToken.indexOf(61);
            if (index >= 0) {
                final String substring = nextToken.substring(0, index);
                final String substring2 = nextToken.substring(index + 1);
                if (substring.equalsIgnoreCase("#SHOWSTATUS")) {
                    appletContext.showStatus(substring2);
                }
                else if (substring.equalsIgnoreCase("#SETDOCTAG")) {
                    s2 = substring2;
                }
                else if (substring.equalsIgnoreCase("#SHOWDOCUMENT")) {
                    try {
                        if (s2 != null) {
                            appletContext.showDocument(new URL(url, substring2), s2);
                        }
                        else {
                            appletContext.showDocument(new URL(url, substring2));
                        }
                    }
                    catch (Exception ex) {
                        appletContext.showStatus(ex.toString());
                    }
                }
                else {
                    System.out.println(nextToken);
                }
            }
            else {
                System.out.println(nextToken);
            }
        }
    }
    
    private static Image a(final byte[] array, final Component component) throws InterruptedException {
        Image image;
        if (c(array) && c() < 258) {
            image = component.createImage(new i(new ByteArrayInputStream(array)));
        }
        else {
            image = component.getToolkit().createImage(array);
        }
        final MediaTracker mediaTracker = new MediaTracker(component);
        mediaTracker.addImage(image, 0);
        if (mediaTracker.waitForID(0, 30000L)) {
            return image;
        }
        return null;
    }
    
    private static int c() {
        int n = 0;
        try {
            final String property = System.getProperty("java.version");
            if (property != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(property, ".");
                n = ((Integer.parseInt(stringTokenizer.nextToken()) & 0xFF) << 8 | (Integer.parseInt(stringTokenizer.nextToken()) & 0xFF));
            }
        }
        catch (Exception ex) {}
        return n;
    }
    
    private static boolean c(final byte[] array) {
        if (array.length < 8) {
            return false;
        }
        final byte[] array2 = { -119, 80, 78, 71, 13, 10, 26, 10 };
        for (int i = 0; i < array2.length; ++i) {
            if (array[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean b(final byte[] array) {
        return array.length > 3 && array[0] == 71 && array[1] == 73 && array[2] == 70;
    }
    
    private static long a() {
        final String s = "kbwb/vujm/Ebuf";
        final String s2 = "hfuUjnf";
        try {
            final Class<?> forName = Class.forName(a(s, -1));
            return (long)forName.getMethod(a(s2, -1), (Class<?>[])null).invoke(forName.newInstance(), (Object[])null);
        }
        catch (Exception ex) {
            return Long.MAX_VALUE;
        }
    }
    
    private static String a(final String s, final int n) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            final char[] array = charArray;
            final int n2 = i;
            array[n2] += (char)n;
        }
        return new String(charArray);
    }
    
    public String getAppletInfo() {
        return "J-Painter 5.4 Copyright 2001-2005 Igor Zhukovsky. http://izhuk.com";
    }
    
    static {
        DrawCanvas.g = new char[] { 'k', 'h', 'b', 'd', 'm', 'r', 'd', '-', 's', 'w', 's' };
    }
}
