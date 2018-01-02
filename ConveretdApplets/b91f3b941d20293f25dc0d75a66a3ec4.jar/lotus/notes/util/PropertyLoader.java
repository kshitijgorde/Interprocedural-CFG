// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class PropertyLoader
{
    public Properties getProperties(final String s, final String s2, final Properties properties, final URL url) {
        int n = 0;
        int n2 = 1;
        String substring = ".properties";
        final String s3 = "_";
        Properties properties2;
        if (properties != null) {
            properties2 = new Properties(properties);
        }
        else {
            properties2 = new Properties();
        }
        if (s2 != null) {
            final String replace = s2.toLowerCase().replace('-', '_');
            if (url != null) {
                final String s4 = new String(url.getProtocol());
            }
            int n3 = 0;
            int n4 = 1;
            while (n4 != 0 && n == 0) {
                int index = replace.indexOf(",", n3);
                String s5;
                if (index != -1) {
                    s5 = replace.substring(n3, index);
                    n3 = ++index;
                }
                else {
                    s5 = replace.substring(n3);
                    n4 = 0;
                }
                final int index2;
                if ((index2 = s5.indexOf(".")) == -1) {
                    s5 += substring;
                }
                else {
                    substring = s5.substring(index2);
                }
                int index3;
                for (int n5 = 0; (index3 = s5.indexOf(s3, n5)) != -1; n5 = ++index3) {
                    ++n2;
                }
                if (s != null) {
                    s5 = s + s3 + s5;
                }
                int i = 0;
                while (i == 0) {
                    try {
                        properties2.load(new URL(url, s5).openStream());
                        i = 1;
                        n = 1;
                        continue;
                    }
                    catch (MalformedURLException ex) {}
                    catch (SecurityException ex2) {}
                    catch (IOException ex3) {
                        final int lastIndex;
                        if (--n2 > 0 && (lastIndex = s5.lastIndexOf("-")) != -1) {
                            s5 = s5.substring(0, lastIndex) + substring;
                        }
                        else if (n4 == 0 && !s5.equals(s + substring)) {
                            s5 = s + substring;
                        }
                        else {
                            i = 1;
                        }
                        continue;
                    }
                    break;
                }
            }
        }
        return properties2;
    }
}
