// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.InputStream;
import java.io.IOException;
import com.hw.client.util.a;
import java.util.Properties;

public class de
{
    private static final Properties a;
    
    private static Properties b() {
        final Properties properties = new Properties();
        final InputStream resourceAsStream = ((by.a == null) ? (by.a = by.a("VT_6_1_0_11.de")) : by.a).getResourceAsStream("/version.properties");
        try {
            if (resourceAsStream != null) {
                properties.load(resourceAsStream);
            }
            else {
                com.hw.client.util.a.e("Version properties file not found in classpath: " + ((by.a == null) ? (by.a = by.a("VT_6_1_0_11.de")) : by.a).getResource("/").toString());
            }
        }
        catch (IOException ex) {
            com.hw.client.util.a.a("Could not load version information.", ex);
            try {
                if (resourceAsStream != null) {
                    resourceAsStream.close();
                }
            }
            catch (Exception ex2) {}
        }
        finally {
            try {
                if (resourceAsStream != null) {
                    resourceAsStream.close();
                }
            }
            catch (Exception ex3) {}
        }
        return properties;
    }
    
    static Properties a() {
        return de.a;
    }
    
    static {
        a = b();
    }
}
