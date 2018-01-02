// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.common.utils;

import java.net.URL;

public class c
{
    public static URL a(final URL url) {
        try {
            String s = new StringBuffer().append(url).toString();
            final String query = url.getQuery();
            if (query != null) {
                s = s.substring(0, s.indexOf(query));
            }
            if (s.lastIndexOf(46) > s.lastIndexOf(47)) {
                s = s.substring(0, s.lastIndexOf(47) + 1);
            }
            return new URL(s);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static String a(final String s, final String s2) {
        String string = s;
        if (s.charAt(s.length() - 1) != '/' && s2.charAt(0) != '/') {
            string = String.valueOf(string) + "/";
        }
        return String.valueOf(string) + s2;
    }
    
    public static boolean int(final String s) {
        return s.trim().toLowerCase().startsWith("http:") || s.trim().toLowerCase().startsWith("https:");
    }
}
