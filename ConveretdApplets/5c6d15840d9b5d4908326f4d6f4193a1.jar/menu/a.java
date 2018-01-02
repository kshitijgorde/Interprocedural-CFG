// 
// Decompiled by Procyon v0.5.30
// 

package menu;

import java.util.Hashtable;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.Properties;

public class a extends Properties
{
    private String a;
    
    public a(final String a) {
        this.a = "8859_1";
        this.a = a;
    }
    
    public synchronized void load(final InputStream inputStream) throws IOException {
        int n = 0;
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, this.a));
        Label_0170: {
            break Label_0170;
            String line = null;
            do {
                ++n;
                if (line.length() > 0) {
                    final String trim = line.trim();
                    if (!trim.startsWith("#") && !trim.startsWith("!") && !trim.equals("")) {
                        try {
                            final int index = trim.indexOf(61);
                            ((Hashtable<String, String>)this).put(trim.substring(0, index).trim(), trim.substring(index + 1).trim());
                        }
                        catch (Exception ex) {
                            System.err.println("A config. file error at line " + n + ". Cannot parse - <" + trim + ">");
                        }
                    }
                }
                line = bufferedReader.readLine();
            } while (line != null);
        }
    }
}
