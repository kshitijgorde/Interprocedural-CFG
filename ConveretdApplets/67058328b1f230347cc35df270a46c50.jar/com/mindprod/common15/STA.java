// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.common15;

import java.util.ArrayList;
import com.mindprod.common11.ST;

public final class STA extends ST
{
    private static final boolean DEBUGGING = false;
    
    public static String condense(String s) {
        if (s == null) {
            return null;
        }
        s = s.trim();
        if (s.indexOf("  ") < 0) {
            return s;
        }
        final int len = s.length();
        final StringBuilder b = new StringBuilder(len - 1);
        boolean suppressSpaces = false;
        for (int i = 0; i < len; ++i) {
            final char c = s.charAt(i);
            if (c == ' ') {
                if (!suppressSpaces) {
                    b.append(c);
                    suppressSpaces = true;
                }
            }
            else {
                b.append(c);
                suppressSpaces = false;
            }
        }
        return b.toString();
    }
    
    public static String[] pruneExcessBlankLines(final String[] lines, final int minBlankLinesToKeep) {
        int firstNonBlankLine = lines.length;
        for (int i = 0; i < lines.length; ++i) {
            if (lines[i].trim().length() > 0) {
                firstNonBlankLine = i;
                break;
            }
        }
        int lastNonBlankLine = -1;
        for (int j = lines.length - 1; j > 0; --j) {
            if (lines[j].trim().length() > 0) {
                lastNonBlankLine = j;
                break;
            }
        }
        if (firstNonBlankLine > lastNonBlankLine) {
            return new String[0];
        }
        final ArrayList<String> keep = new ArrayList<String>(lastNonBlankLine - firstNonBlankLine + 1);
        int pendingBlankLines = 0;
        for (int k = firstNonBlankLine; k <= lastNonBlankLine; ++k) {
            if (lines[k].trim().length() == 0) {
                ++pendingBlankLines;
            }
            else {
                if (pendingBlankLines >= minBlankLinesToKeep) {
                    keep.add("");
                }
                keep.add(lines[k]);
                pendingBlankLines = 0;
            }
        }
        return keep.toArray(new String[keep.size()]);
    }
    
    public static String quoteSQL(final String sql) {
        final StringBuilder sb = new StringBuilder(sql.length() + 5);
        sb.append('\'');
        for (int i = 0; i < sql.length(); ++i) {
            final char c = sql.charAt(i);
            if (c == '\'') {
                sb.append("''");
            }
            else {
                sb.append(c);
            }
        }
        sb.append('\'');
        return sb.toString();
    }
    
    public static String squish(String s) {
        if (s == null) {
            return null;
        }
        s = s.trim();
        if (s.indexOf(32) < 0) {
            return s;
        }
        final int len = s.length();
        final StringBuilder b = new StringBuilder(len - 1);
        for (int i = 0; i < len; ++i) {
            final char c = s.charAt(i);
            if (c != ' ') {
                b.append(c);
            }
        }
        return b.toString();
    }
    
    public static void main(final String[] args) {
    }
}
