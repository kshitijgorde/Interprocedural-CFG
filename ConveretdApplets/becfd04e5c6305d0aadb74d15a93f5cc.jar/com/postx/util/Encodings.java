// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.util;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import com.postx.util.logging.Logger;

public class Encodings
{
    public static final String Ident = "$Id: Encodings.java,v 1.2 2009/06/12 20:34:24 blm Exp $";
    private static final Logger log;
    private static final Hashtable encodingXlater;
    
    public static String getEncoding(final String s) {
        if (s == null) {
            return null;
        }
        String s2;
        if ((s2 = testEncodings(s)) == null && (s2 = testEncodings(s.replace('-', '_'))) == null) {
            final int length = s.length();
            final StringBuffer sb = new StringBuffer(length);
            for (int i = 0; i < length; ++i) {
                final char char1;
                if ((char1 = s.charAt(i)) != '-' && char1 != '_') {
                    sb.append(char1);
                }
            }
            s2 = testEncodings(sb.toString());
        }
        if (s2 == null) {
            Encodings.log.info("Couldn't find encoding " + quote(s));
        }
        else if (!s.equals(s2)) {
            Encodings.log.info("Translated encoding " + quote(s) + " to " + quote(s2));
        }
        return s2;
    }
    
    private static String testEncodings(final String s) {
        String s2;
        if ((s2 = testEncoding(s)) == null && (s2 = Encodings.encodingXlater.get(s.toLowerCase())) != null) {
            s2 = testEncoding(s2);
        }
        return s2;
    }
    
    static {
        log = Logger.global;
        (encodingXlater = new Hashtable()).put("utf-8", "UTF8");
        Encodings.encodingXlater.put("utf-16", "UnicodeBig");
        Encodings.encodingXlater.put("utf-16be", "UnicodeBig");
        Encodings.encodingXlater.put("utf-16le", "UnicodeLittle");
    }
    
    protected static String quote(final Object o) {
        if (o == null) {
            return "null";
        }
        return "\"" + o + "\"";
    }
    
    private static String testEncoding(String s) {
        try {
            new String(new byte[0], s);
        }
        catch (UnsupportedEncodingException ex) {
            s = null;
        }
        return s;
    }
}
