// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.base.config;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.io.BufferedInputStream;
import java.io.InputStream;
import org.jfree.util.Log;

public class PropertyFileConfiguration extends HierarchicalConfiguration
{
    public void load(final String s) {
        final InputStream resourceAsStream = this.getClass().getResourceAsStream(s);
        if (resourceAsStream != null) {
            this.load(resourceAsStream);
        }
        else {
            Log.debug("Report configuration file not found: " + s);
        }
    }
    
    public void load(final InputStream inputStream) {
        if (inputStream == null) {
            throw new NullPointerException();
        }
        try {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            final Properties properties = new Properties();
            properties.load(bufferedInputStream);
            this.getConfiguration().putAll(properties);
            bufferedInputStream.close();
        }
        catch (IOException ex) {
            Log.warn("Unable to read configuration", ex);
        }
    }
}
