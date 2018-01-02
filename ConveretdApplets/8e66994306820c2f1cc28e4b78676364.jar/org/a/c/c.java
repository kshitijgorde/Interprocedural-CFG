// 
// Decompiled by Procyon v0.5.30
// 

package org.a.c;

import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
import java.io.IOException;
import org.apache.log4j.PropertyConfigurator;
import java.util.Properties;
import java.io.InputStream;

class c
{
    public boolean a(final InputStream inputStream) {
        boolean b = false;
        if (inputStream != null) {
            try {
                final Properties properties = new Properties();
                properties.load(inputStream);
                PropertyConfigurator.configure(properties);
                b = true;
            }
            catch (IOException ex) {}
        }
        if (!b) {
            BasicConfigurator.configure();
            final Logger rootLogger = Logger.getRootLogger();
            ((Category)rootLogger).removeAllAppenders();
            ((Category)rootLogger).setLevel(Level.OFF);
        }
        return b;
    }
}
