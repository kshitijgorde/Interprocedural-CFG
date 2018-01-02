import java.util.TimeZone;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.awt.MediaTracker;
import java.util.Date;
import java.lang.reflect.InvocationTargetException;
import java.awt.Toolkit;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;
import java.text.ParseException;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.util.Properties;
import java.util.Hashtable;
import java.text.SimpleDateFormat;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class g
{
    public Image a;
    public Image b;
    public int c;
    public d d;
    public SimpleDateFormat e;
    public Hashtable f;
    public Hashtable g;
    public Hashtable h;
    public Hashtable i;
    public boolean j;
    public static /* synthetic */ Class k;
    public static /* synthetic */ Class l;
    
    public void a(final Properties properties) {
        this.a = this.a(properties, "Splash");
        this.b = this.a(properties, "Logo");
    }
    
    private Image a(final Properties properties, final String s) {
        final String property = properties.getProperty(s);
        if (property == null) {
            return null;
        }
        try {
            return this.a(null, property, false);
        }
        catch (ParseException ex) {
            this.d.c().a((Component)this.d.s, 4, new Object[] { property, s, ex });
        }
        catch (IOException ex2) {
            this.d.c().a((Component)this.d.s, 4, new Object[] { property, s, ex2 });
        }
        return null;
    }
    
    public Image a(final ImageObserver imageObserver, final String s, final boolean b) throws ParseException, IOException {
        URL b2;
        try {
            b2 = this.d.d().b(s);
        }
        catch (MalformedURLException ex) {
            throw new ParseException(ex.getMessage(), 0);
        }
        return this.a(imageObserver, b2, b);
    }
    
    public Image a(final ImageObserver imageObserver, final URL url, final boolean b) throws IOException {
        Image image;
        synchronized (this) {
            Hashtable hashtable;
            Hashtable<URL, String> i;
            if (!b) {
                hashtable = this.f;
                i = null;
            }
            else {
                hashtable = this.g;
                i = (Hashtable<URL, String>)this.i;
            }
            if ((image = hashtable.get(url)) == null) {
                if (this.j && b) {
                    image = Toolkit.getDefaultToolkit().createImage(new byte[0]);
                    hashtable.put(url, image);
                    this.b(imageObserver, url, image);
                }
                else {
                    final StringBuffer sb = new StringBuffer();
                    image = this.a(imageObserver, url, sb);
                    hashtable.put(url, image);
                    if (i != null && sb.length() > 0) {
                        i.put(url, sb.toString());
                    }
                }
            }
            else if (b && this.h.get(url) == null) {
                this.b(imageObserver, url, image);
            }
        }
        return image;
    }
    
    private Image a(final ImageObserver imageObserver, final URL url, final StringBuffer sb) throws IOException {
        sb.setLength(0);
        Image image = null;
        Label_0245: {
            if (this.j) {
                try {
                    final Object invoke = Class.forName("com.netfeedtech.image.NTCToolkit").getMethod("GetDefaultToolkit", (Class<?>[])new Class[0]).invoke(null, new Object[0]);
                    image = (Image)invoke.getClass().getMethod("GetImage", (g.k == null) ? (g.k = class$("java.net.URL")) : g.k, (g.l == null) ? (g.l = class$("java.util.Properties")) : g.l, (g.l == null) ? (g.l = class$("java.util.Properties")) : g.l).invoke(invoke, url, null, null);
                    break Label_0245;
                }
                catch (ClassNotFoundException ex) {
                    throw new IOException(ex.getMessage());
                }
                catch (NoSuchMethodException ex2) {
                    throw new IOException(ex2.getMessage());
                }
                catch (IllegalAccessException ex3) {
                    throw new IOException(ex3.getMessage());
                }
                catch (InvocationTargetException ex4) {
                    throw new IOException(ex4.getTargetException().getMessage());
                }
            }
            image = Toolkit.getDefaultToolkit().getImage(url);
            sb.append(this.e.format(new Date()));
        }
        if (image == null) {
            this.d.c().a((Component)this.d.s, 5, new Object[] { url.toExternalForm() });
            throw new IOException(url.toExternalForm());
        }
        if (!this.j || imageObserver == null) {
            this.a(imageObserver, url, image);
        }
        return image;
    }
    
    private void a(final ImageObserver imageObserver, final URL url, final Image image) throws IOException {
        MediaTracker mediaTracker;
        if (imageObserver == null) {
            mediaTracker = new MediaTracker((Component)this.d.s);
        }
        else {
            mediaTracker = new MediaTracker((Component)imageObserver);
        }
        mediaTracker.addImage(image, 0);
        if (imageObserver == null) {
            try {
                mediaTracker.waitForID(0);
            }
            catch (InterruptedException ex) {}
            if (mediaTracker.isErrorID(0)) {
                image.flush();
                this.d.c().a((Component)this.d.s, 5, new Object[] { url.toExternalForm() });
                throw new IOException(url.toExternalForm());
            }
        }
    }
    
    private void b(final ImageObserver imageObserver, final URL url, final Image image) {
        new Thread(this.d, imageObserver, url, image, (String)this.i.get(url)) {
            public ImageObserver a;
            public URL b;
            public Image c;
            public String d;
            
            {
                g.a(g.this, d);
                this.a = a;
                this.b = b;
                this.c = c;
                this.d = d2;
            }
            
            public final void run() {
                try {
                    Image c;
                    if (g.a(g.this)) {
                        c = this.b();
                    }
                    else {
                        c = this.c();
                    }
                    if (c != null) {
                        Toolkit.getDefaultToolkit().prepareImage(c, -1, -1, (this.a == null) ? ((Component)g.b(g.this).s) : this.a);
                        g.this.a(this.a, this.b, c);
                        g.c(g.this).remove(this.b);
                        final Image c2 = this.c;
                        this.c = c;
                        g.c(g.this).put(this.b, this.c);
                        g.d(g.this).put(this.b, this.c);
                        if (this.a instanceof ad) {
                            try {
                                ((ad)this.a).a(c2, this.c);
                            }
                            catch (Throwable t) {}
                        }
                        c2.flush();
                        if (g.a(g.this, (g.e(g.this) + 1) % 6) == 0) {
                            g.b(g.this).d();
                            f.b();
                        }
                    }
                }
                catch (Throwable t2) {}
                finally {
                    this.a();
                }
            }
            
            private Image b() throws IOException {
                final Properties properties = new Properties();
                if (this.d != null) {
                    ((Hashtable<String, String>)properties).put("If-Modified-Since", this.d);
                }
                final Properties properties2 = new Properties();
                Image image;
                try {
                    try {
                        final Object invoke = Class.forName("com.netfeedtech.image.NTCToolkit").getMethod("GetDefaultToolkit", (Class<?>[])new Class[0]).invoke(null, new Object[0]);
                        image = (Image)invoke.getClass().getMethod("GetImage", (g.k == null) ? (g.k = g.class$("java.net.URL")) : g.k, (g.l == null) ? (g.l = g.class$("java.util.Properties")) : g.l, (g.l == null) ? (g.l = g.class$("java.util.Properties")) : g.l).invoke(invoke, this.b, properties, properties2);
                        final String d = ((Hashtable<K, String>)properties2).get("Last-Modified");
                        this.d = d;
                        if (d != null) {
                            g.f(g.this).put(this.b, this.d);
                        }
                    }
                    catch (ClassNotFoundException ex) {
                        throw new IOException(ex.getMessage());
                    }
                    catch (NoSuchMethodException ex2) {
                        throw new IOException(ex2.getMessage());
                    }
                    catch (IllegalAccessException ex3) {
                        throw new IOException(ex3.getMessage());
                    }
                }
                catch (InvocationTargetException ex4) {
                    IOException ex5;
                    if (ex4.getTargetException() instanceof IOException) {
                        ex5 = (IOException)ex4.getTargetException();
                    }
                    else {
                        ex5 = new IOException(ex4.getTargetException().getMessage());
                    }
                    image = null;
                    if (!(ex5 instanceof FileNotFoundException)) {
                        if (((Hashtable<K, String>)properties2).get("X-Status-Code") == null || !((Hashtable<K, String>)properties2).get("X-Status-Code").equals("304")) {
                            throw ex5;
                        }
                    }
                }
                return image;
            }
            
            private Image c() throws IOException {
                Image image = null;
                final URLConnection openConnection = this.b.openConnection();
                openConnection.setAllowUserInteraction(true);
                openConnection.setUseCaches(false);
                if (this.d != null) {
                    openConnection.setRequestProperty("If-Modified-Since", this.d);
                }
                openConnection.connect();
                try {
                    final InputStream inputStream = openConnection.getInputStream();
                    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(new byte[Math.max(1024, openConnection.getContentLength())].length);
                    final byte[] array = new byte[1024];
                    int read;
                    while ((read = inputStream.read(array, 0, 1024)) > 0) {
                        byteArrayOutputStream.write(array, 0, read);
                    }
                    final byte[] byteArray = byteArrayOutputStream.toByteArray();
                    image = Toolkit.getDefaultToolkit().createImage(byteArray, 0, byteArray.length);
                    if (openConnection.getLastModified() > 0L) {
                        g.f(g.this).put(this.b, g.g(g.this).format(new Date(openConnection.getLastModified())));
                    }
                }
                catch (FileNotFoundException ex) {}
                return image;
            }
            
            public final void a() {
                this.a = null;
                this.b = null;
                this.c = null;
                this.d = null;
            }
        }.start();
    }
    
    public final void a() {
        synchronized (this) {
            this.h.clear();
        }
    }
    
    public g(final d d) {
        this.f = new Hashtable(50, 0.75f);
        this.g = new Hashtable(50, 0.75f);
        this.h = new Hashtable(25, 0.75f);
        this.i = new Hashtable(50, 0.75f);
        this.j = false;
        this.d = d;
        (this.e = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'")).setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Class.forName("com.netfeedtech.image.NTCToolkit");
            this.j = true;
        }
        catch (ClassNotFoundException ex) {
            this.j = false;
        }
    }
    
    public final void b() {
        this.b = null;
        this.a = null;
        if (this.h != null) {
            this.h.clear();
            this.h = null;
        }
        if (this.g != null) {
            this.g.clear();
            this.g = null;
        }
        if (this.f != null) {
            this.f.clear();
            this.f = null;
        }
        if (this.i != null) {
            this.i.clear();
            this.i = null;
        }
        this.e = null;
        this.d = null;
    }
    
    public static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public static /* synthetic */ d a(final g g, final d d) {
        return g.d = d;
    }
    
    public static /* synthetic */ boolean a(final g g) {
        return g.j;
    }
    
    public static /* synthetic */ d b(final g g) {
        return g.d;
    }
    
    public static /* synthetic */ Hashtable c(final g g) {
        return g.g;
    }
    
    public static /* synthetic */ Hashtable d(final g g) {
        return g.h;
    }
    
    public static /* synthetic */ int a(final g g, final int c) {
        return g.c = c;
    }
    
    public static /* synthetic */ int e(final g g) {
        return g.c;
    }
    
    public static /* synthetic */ Hashtable f(final g g) {
        return g.i;
    }
    
    public static /* synthetic */ SimpleDateFormat g(final g g) {
        return g.e;
    }
}
