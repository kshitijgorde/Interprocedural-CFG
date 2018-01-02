// 
// Decompiled by Procyon v0.5.30
// 

package com.chess.applet;

import java.io.InputStream;
import java.io.IOException;
import com.chess.util.Util;
import java.util.Properties;

public class Version
{
    public static String getVersion() {
        try {
            final InputStream inputStream = Version.class.getResourceAsStream("/applet_version.properties");
            final Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            final String version = properties.getProperty("version");
            final String build = properties.getProperty("BUILD");
            return version + "." + build;
        }
        catch (IOException ex) {
            Util.getLogger((Class)Version.class).error((Object)"error getting verion", (Throwable)ex);
            return null;
        }
    }
}
