import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class KLHGdf8wighfuidfiH
{
    public static String LKHJdfeiuynj3frg(final String s, final String s2) {
        String string = null;
        final String s3 = "http://";
        String s4 = null;
        if (s.startsWith(s3)) {
            s4 = "JGHDFUYheb";
        }
        if (s4 == "JGHDFUYheb") {
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
