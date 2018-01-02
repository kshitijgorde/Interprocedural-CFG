// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.awt.FontMetrics;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.util.Hashtable;

public class StringLib
{
    private static final String SUFFIX = "suffix";
    private static final String PREFIX = "prefix";
    private static Hashtable prefixSuffixT;
    
    public static final String getArrayString(final Object o) {
        final StringBuffer sb = new StringBuffer();
        sb.append('[');
        for (int length = Array.getLength(o), i = 0; i < length; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(Array.get(o, i));
        }
        sb.append(']');
        return sb.toString();
    }
    
    public static String formatNumber(final double n, final int n2) {
        final String value = String.valueOf(n);
        final int index = value.indexOf(46);
        if (index == -1) {
            return value;
        }
        final int index2 = value.indexOf(69);
        String s = value.substring(0, Math.min(index + (n2 + ((index2 < 0) ? 1 : 0)), value.length()));
        if (index2 >= 0) {
            s += value.substring(index2);
        }
        return s;
    }
    
    public static String capitalizeFirstOnly(final String s) {
        if (s == null) {
            return null;
        }
        if (s.length() == 0) {
            return s;
        }
        final StringBuffer sb = new StringBuffer();
        final char char1 = s.charAt(0);
        sb.append(Character.toUpperCase(char1));
        int whitespace = Character.isWhitespace(char1) ? 1 : 0;
        for (int i = 1; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (Character.isWhitespace(c)) {
                whitespace = 1;
            }
            else if (whitespace != 0) {
                c = Character.toUpperCase(c);
                whitespace = 0;
            }
            else {
                c = Character.toLowerCase(c);
            }
            sb.append(c);
        }
        return sb.toString();
    }
    
    public static String getStackTrace(final Throwable t) {
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(stringWriter);
        t.printStackTrace(printWriter);
        printWriter.close();
        return stringWriter.toString();
    }
    
    public static String abbreviate(final String s, final FontMetrics fontMetrics, final int n) {
        int n2 = 0;
        int n3 = 0;
        for (int n4 = 0; n4 < n && n3 < s.length(); n4 += fontMetrics.charWidth(s.charAt(n3)), ++n3) {
            if (Character.isWhitespace(s.charAt(n3))) {
                n2 = n3;
            }
        }
        if (n3 < s.length() && n2 > 0) {
            n3 = n2;
        }
        return (n3 > 0) ? s.substring(0, n3) : s;
    }
    
    public static String abbreviateName(String s, final FontMetrics fontMetrics, final int n) {
        if (fontMetrics.stringWidth(s) > n) {
            s = abbreviateName(s, false);
        }
        if (fontMetrics.stringWidth(s) > n) {
            s = abbreviateName(s, true);
        }
        return s;
    }
    
    private static String abbreviateName(final String s, final boolean b) {
        final StreamTokenizer streamTokenizer = new StreamTokenizer(new StringReader(s));
        streamTokenizer.wordChars(38, 38);
        streamTokenizer.wordChars(64, 64);
        streamTokenizer.wordChars(58, 58);
        streamTokenizer.ordinaryChar(44);
        streamTokenizer.ordinaryChar(45);
        String sval = null;
        String string = null;
        final StringBuffer sb = new StringBuffer();
        try {
        Label_0265:
            while (true) {
                switch (streamTokenizer.nextToken()) {
                    case -1: {
                        break Label_0265;
                    }
                    case 10: {
                        System.err.println("warning: unexpected EOL token");
                        continue;
                    }
                    case -2: {
                        continue;
                    }
                    case 44: {
                        break Label_0265;
                    }
                    case -3: {
                        if (streamTokenizer.sval.endsWith(":")) {
                            sb.append(streamTokenizer.sval + " ");
                            continue;
                        }
                        if (StringLib.prefixSuffixT.get(streamTokenizer.sval.toLowerCase()) == null) {
                            if (!b) {
                                if (string != null) {
                                    sb.append(string);
                                }
                                string = streamTokenizer.sval.substring(0, 1) + ". ";
                            }
                            sval = streamTokenizer.sval;
                            continue;
                        }
                        continue;
                    }
                    default: {
                        continue;
                    }
                }
            }
            sb.append(sval);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }
    
    static {
        (StringLib.prefixSuffixT = new Hashtable()).put("mr", "prefix");
        StringLib.prefixSuffixT.put("mr.", "prefix");
        StringLib.prefixSuffixT.put("dr", "prefix");
        StringLib.prefixSuffixT.put("dr.", "prefix");
        StringLib.prefixSuffixT.put("lt", "prefix");
        StringLib.prefixSuffixT.put("lt.", "prefix");
        StringLib.prefixSuffixT.put("gen", "prefix");
        StringLib.prefixSuffixT.put("gen.", "prefix");
        StringLib.prefixSuffixT.put("sgt", "prefix");
        StringLib.prefixSuffixT.put("sgt.", "prefix");
        StringLib.prefixSuffixT.put("cmdr", "prefix");
        StringLib.prefixSuffixT.put("cmdr.", "prefix");
        StringLib.prefixSuffixT.put("cpt", "prefix");
        StringLib.prefixSuffixT.put("cpt.", "prefix");
        StringLib.prefixSuffixT.put("ii", "suffix");
        StringLib.prefixSuffixT.put("iii", "suffix");
        StringLib.prefixSuffixT.put("iv", "suffix");
        StringLib.prefixSuffixT.put("jr", "suffix");
        StringLib.prefixSuffixT.put("jr.", "suffix");
        StringLib.prefixSuffixT.put("sr", "suffix");
        StringLib.prefixSuffixT.put("sr.", "suffix");
    }
}
