// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import java.util.Properties;

public class OutputPropertyUtils
{
    public static boolean getBooleanProperty(final String key, final Properties props) {
        final String s = props.getProperty(key);
        return null != s && s.equals("yes");
    }
    
    public static int getIntProperty(final String key, final Properties props) {
        final String s = props.getProperty(key);
        if (null == s) {
            return 0;
        }
        return Integer.parseInt(s);
    }
}
