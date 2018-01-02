// 
// Decompiled by Procyon v0.5.30
// 

package inknet;

import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class Translator
{
    private Properties props;
    private boolean valid;
    
    public Translator(final URL url, final String file) {
        this.valid = false;
        this.props = new Properties();
        try {
            final InputStream is = new URL(url, file).openStream();
            this.props.load(is);
            is.close();
            this.valid = true;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String lookup(final String key) {
        if (!this.valid) {
            return key;
        }
        final String r = this.props.getProperty(key);
        if (r == null) {
            return key;
        }
        return r;
    }
}
