import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.util.Locale;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.File;
import java.util.PropertyResourceBundle;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.ResourceBundle;

// 
// Decompiled by Procyon v0.5.30
// 

public class t
{
    private static t a;
    private static ResourceBundle b;
    private static Hashtable c;
    
    public static final String a(final String s, final String s2, final String s3) {
        final StringBuffer sb = new StringBuffer("");
        int n = 0;
        while (true) {
            final int index = s3.indexOf(s, n);
            if (index < 0) {
                break;
            }
            sb.append(s3.substring(n, index));
            sb.append(s2);
            n = index + s.length();
        }
        sb.append(s3.substring(n));
        return sb.toString();
    }
    
    private void b(final String s) {
        t.c.put("chat.font.name", "Verdana");
        t.c.put("chat.font.size", "10");
        t.c.put("chat.font.color", "black");
        t.c.put("chat.font.backgroundcolor", "white");
        t.c.put("chat.frame.size", "640x480");
        t.c.put("chat.allowpms", "true");
        t.c.put("chat.pmexitmessage", "I'm out of here");
        t.c.put("chat.showjoins", "true");
        t.c.put("cam.frame.size", "240x180");
        t.c.put("cam.capturepath", a("\\", "\\\\", System.getProperty("user.home") + System.getProperty("file.separator") + "awc_" + n.b().z()));
        t.c.put("cam.gender.male", "true");
        t.c.put("cam.gender.female", "true");
        t.c.put("cam.gender.other", "true");
        t.c.put("cam.gender.group", "true");
        t.c.put("chat.sortcount", "false");
        t.c.put("cam.closewindow", "false");
        t.c.put("lang.def", s);
        t.c.put("cam.disp", "0");
    }
    
    private t(final InputStream inputStream, final String s) {
        try {
            this.b(s);
            t.b = new PropertyResourceBundle(inputStream);
            this.b();
        }
        catch (Exception ex) {
            b.a(ex, 3);
            this.a(t.c);
        }
    }
    
    private t(final String s) {
        this.b(s);
        this.c();
    }
    
    public static void a(final String s, final String s2) {
        try {
            t.a = new t(new FileInputStream(new File(s)), s2);
        }
        catch (FileNotFoundException ex) {
            t.a = new t(s2);
        }
    }
    
    public static t a() {
        return t.a;
    }
    
    public void b() {
        boolean b = false;
        final Enumeration<Object> keys = t.c.keys();
        while (keys.hasMoreElements()) {
            final String string = keys.nextElement().toString();
            try {
                t.b.getObject(string);
                t.c.put(string, t.b.getString(string));
            }
            catch (MissingResourceException ex) {
                b = true;
            }
        }
        if (b) {
            this.a(t.c);
        }
    }
    
    public void a(final Hashtable hashtable) {
        try {
            final Locale default1 = Locale.getDefault();
            final String string = System.getProperty("user.home") + System.getProperty("file.separator") + "awc_" + n.b().z() + System.getProperty("file.separator") + "settings_" + default1.getLanguage() + "_" + default1.getCountry() + ".properties";
            final FileOutputStream fileOutputStream = new FileOutputStream(string);
            final PrintStream printStream = new PrintStream(fileOutputStream);
            final Enumeration<Object> keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                final String string2 = keys.nextElement().toString();
                printStream.println(string2 + '=' + hashtable.get(string2).toString());
            }
            printStream.close();
            fileOutputStream.close();
            t.b = new PropertyResourceBundle(new FileInputStream(new File(string)));
        }
        catch (Exception ex) {
            b.a(ex, 3);
        }
    }
    
    public void c() {
        this.a(t.c);
    }
    
    public String a(final String s) throws MissingResourceException {
        return t.b.getString(s);
    }
    
    static {
        t.a = null;
        t.c = new Hashtable();
    }
}
