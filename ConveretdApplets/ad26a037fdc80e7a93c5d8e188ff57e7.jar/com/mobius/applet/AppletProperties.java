// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.applet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Properties;
import java.io.File;

public class AppletProperties
{
    private static final String USER_HOME_PROPERTY = "user.home";
    
    private static File getPropertiesFile(final String s, final String s2) {
        String property;
        try {
            property = System.getProperty("user.home");
        }
        catch (Exception ex) {
            property = s;
        }
        if (property == null || property.length() == 0) {
            property = s;
        }
        return new File(property + File.separator + s2);
    }
    
    public static Properties getProperties(final String s, final String s2) {
        Properties properties = new Properties();
        final File propertiesFile = getPropertiesFile(s, s2);
        try {
            final FileInputStream fileInputStream = new FileInputStream(propertiesFile);
            properties.load(fileInputStream);
            fileInputStream.close();
        }
        catch (Exception ex) {
            properties = new Properties();
            ex.printStackTrace();
        }
        return properties;
    }
    
    public static void saveProperties(final Properties properties, final String s, final String s2) throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream(getPropertiesFile(s, s2));
        properties.save(fileOutputStream, null);
        fileOutputStream.close();
    }
}
