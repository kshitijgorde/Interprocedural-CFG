// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.resource;

import java.io.InputStream;
import com.masystem.beergame.debug.Log;
import java.util.HashMap;

public class Properties
{
    private final HashMap<String, String> properties;
    
    public Properties(String string) {
        this.properties = new HashMap<String, String>();
        if (string.charAt(0) != '/') {
            string = "/" + string;
        }
        final InputStream resourceAsStream;
        if ((resourceAsStream = Properties.class.getResourceAsStream(string)) != null) {
            readProperties(resourceAsStream, this.properties);
            return;
        }
        Log.warn("Configuration file '" + string + "' not found.");
    }
    
    public final String getProperty(String s) {
        if ((s = this.properties.get(s.toLowerCase())) != null && s.length() != 0) {
            return s;
        }
        return null;
    }
    
    public final int getIntegerProperty(final String s) {
        return this.getIntegerProperty(s, 0);
    }
    
    private int getIntegerProperty(final String s, final int n) {
        try {
            return Integer.parseInt(this.getProperty(s));
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public final void setProperty(final String s, final String s2) {
        this.properties.put(s.toLowerCase(), s2);
    }
    
    private static void readProperties(final InputStream inputStream, final HashMap<String, String> hashMap) {
        final String[] array = new String[2];
        final StringBuffer sb = new StringBuffer();
        try {
            while (true) {
                final int read;
                if (((read = inputStream.read()) == -1 || read == 10) && sb.length() != 0) {
                    final String string = sb.toString();
                    final String[] array2 = array;
                    String substring;
                    if ((substring = string) != null) {
                        final int index;
                        if ((index = substring.indexOf(35)) != -1) {
                            substring = substring.substring(0, index);
                        }
                        final int index2;
                        if ((index2 = substring.indexOf(61)) != -1) {
                            array2[0] = substring.substring(0, index2).trim();
                            array2[1] = substring.substring(index2 + 1).trim();
                        }
                        else {
                            array2[1] = (array2[0] = "");
                        }
                    }
                    final String s = array[0];
                    final String s2 = array[1];
                    if (s.length() != 0 && s2.length() != 0) {
                        hashMap.put(s.toLowerCase(), s2);
                    }
                    sb.setLength(0);
                    if (read != -1) {
                        continue;
                    }
                    break;
                }
                else {
                    sb.append((char)read);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
