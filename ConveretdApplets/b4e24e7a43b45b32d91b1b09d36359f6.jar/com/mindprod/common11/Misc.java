// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.common11;

import java.util.GregorianCalendar;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Component;

public final class Misc
{
    static final boolean DEBUGGING = false;
    
    public static void beep() {
        System.out.print("\u0007");
        System.out.flush();
    }
    
    public static Frame getParentFrame(final Component child) {
        for (Container c = child.getParent(); c != null; c = c.getParent()) {
            if (c instanceof Frame) {
                return (Frame)c;
            }
        }
        return null;
    }
    
    public static String[][] loadProperties(final InputStream fis) throws IOException {
        final String[] left = new String[1000];
        final String[] right = new String[1000];
        int count = 0;
        final StreamTokenizer s = new StreamTokenizer(new BufferedReader(new InputStreamReader(fis)));
        s.wordChars(32, 95);
        s.commentChar(35);
        s.whitespaceChars(61, 61);
        s.eolIsSignificant(true);
        while (true) {
            s.nextToken();
            if (s.ttype == -1) {
                break;
            }
            if (s.ttype == 10) {
                continue;
            }
            left[count] = s.sval.trim();
            s.nextToken();
            right[count] = s.sval.trim();
            ++count;
        }
        fis.close();
        final String[][] result = new String[2][count];
        System.arraycopy(left, 0, result[0], 0, count);
        System.arraycopy(right, 0, result[1], 0, count);
        return result;
    }
    
    public static String miniURLEncode(final String URL) {
        final StringBuilder sb = new StringBuilder(URL.length() + 20);
        for (int i = 0; i < URL.length(); ++i) {
            final char c = URL.charAt(i);
            switch (c) {
                case ' ':
                case '{':
                case '|':
                case '}': {
                    sb.append('%');
                    sb.append(ST.toLZHexString(c, 2).toUpperCase());
                    break;
                }
                default: {
                    sb.append(c);
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    public static int signum(final long diff) {
        return (int)(diff >>> 32) | ((int)diff | (int)diff << 1) >>> 1;
    }
    
    public static int signum(final double diff) {
        if (diff > 0.0) {
            return 1;
        }
        if (diff < 0.0) {
            return -1;
        }
        return 0;
    }
    
    public static int signum(final float diff) {
        if (diff > 0.0f) {
            return 1;
        }
        if (diff < 0.0f) {
            return -1;
        }
        return 0;
    }
    
    public static int thisDayOfMonth() {
        return new GregorianCalendar().get(5);
    }
    
    public static int thisMonth() {
        return new GregorianCalendar().get(2) + 1;
    }
    
    public static int thisYear() {
        return new GregorianCalendar().get(1);
    }
    
    public static void main(final String[] args) {
    }
}
