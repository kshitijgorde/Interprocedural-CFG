import java.util.PropertyResourceBundle;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.net.URL;
import au.com.rocketdog.project.awc.applet.Main;
import java.util.ResourceBundle;

// 
// Decompiled by Procyon v0.5.30
// 

public class u
{
    public ResourceBundle a;
    private t b;
    
    public u(final boolean b) {
        this.b = t.a();
        if (!this.a(b)) {
            this.b();
        }
    }
    
    public void a() {
        this.b();
    }
    
    private void b() {
        try {
            boolean b = false;
            BufferedReader bufferedReader;
            File file;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new URL("http://" + Main.b + "/awc/language_" + "4" + "_" + this.b.a("lang.def") + ".properties").openStream())));
                file = new File(Main.i + System.getProperty("file.separator") + "language_" + "4" + "_" + this.b.a("lang.def") + ".properties");
            }
            catch (Exception ex) {
                bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(new URL("http://" + Main.b + "/awc/language_" + "4" + "_EN.properties").openStream())));
                file = new File(Main.i + System.getProperty("file.separator") + "language_" + "4" + "_EN.properties");
                b = true;
                ex.printStackTrace();
            }
            final PrintStream printStream = new PrintStream(new FileOutputStream(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                printStream.println(line);
            }
            printStream.flush();
            printStream.close();
            this.a(b);
        }
        catch (Exception ex2) {
            b.a(ex2, 3);
        }
    }
    
    private boolean a(final boolean b) {
        try {
            File file;
            if (b) {
                file = new File(Main.i + System.getProperty("file.separator") + "language_" + "4" + "_EN.properties");
            }
            else {
                file = new File(Main.i + System.getProperty("file.separator") + "language_" + "4" + "_" + this.b.a("lang.def").toUpperCase() + ".properties");
            }
            this.a = new PropertyResourceBundle(new FileInputStream(file));
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public String a(final String s) {
        try {
            return this.a.getString(s);
        }
        catch (Exception ex) {
            return "";
        }
    }
}
