// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.common11;

import java.awt.Color;
import java.util.Vector;
import java.util.BitSet;

public class ST
{
    private static final boolean DEBUGGING = false;
    private static final String SOMESPACES = "                                                                      ";
    private static final BitSet vt;
    
    public static void beep() {
        System.out.print("\u0007");
        System.out.flush();
    }
    
    public static String canonical(final String s) {
        if (s == null) {
            return "";
        }
        return s.trim();
    }
    
    public static String chopLeadingString(final String text, final String toChop) {
        if (text != null && text.startsWith(toChop)) {
            return text.substring(toChop.length());
        }
        return text;
    }
    
    public static String chopTrailingString(final String text, final String toChop) {
        if (text != null && text.endsWith(toChop)) {
            return text.substring(0, text.length() - toChop.length());
        }
        return text;
    }
    
    public static String condense(String s) {
        if (s == null) {
            return null;
        }
        s = s.trim();
        if (s.indexOf("  ") < 0) {
            return s;
        }
        final int len = s.length();
        final StringBuffer b = new StringBuffer(len - 1);
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
    
    public static int countInstances(final String page, final String lookFor) {
        int count = 0;
        for (int start = 0; (start = page.indexOf(lookFor, start)) >= 0; start += lookFor.length()) {
            ++count;
        }
        return count;
    }
    
    public static int countInstances(final String page, final char lookFor) {
        int count = 0;
        for (int i = 0; i < page.length(); ++i) {
            if (page.charAt(i) == lookFor) {
                ++count;
            }
        }
        return count;
    }
    
    public static int countLeading(final String text, final char c) {
        int count;
        for (count = 0; count < text.length() && text.charAt(count) == c; ++count) {}
        return count;
    }
    
    public static int countLeading(final String text, final String possibleChars) {
        int count;
        for (count = 0; count < text.length() && possibleChars.indexOf(text.charAt(count)) >= 0; ++count) {}
        return count;
    }
    
    public static int countTrailing(final String text, final char c) {
        int length;
        int count;
        for (length = text.length(), count = 0; count < length && text.charAt(length - 1 - count) == c; ++count) {}
        return count;
    }
    
    public static int countTrailing(final String text, final String possibleChars) {
        int length;
        int count;
        for (length = text.length(), count = 0; count < length && possibleChars.indexOf(text.charAt(length - 1 - count)) >= 0; ++count) {}
        return count;
    }
    
    public static String firstWord(String s) {
        s = s.trim();
        final int place = s.indexOf(32);
        return (place < 0) ? s : s.substring(0, place);
    }
    
    public static boolean haveCommonChar(final String a, final String b) {
        for (int i = 0; i < b.length(); ++i) {
            if (a.indexOf(b.charAt(i)) >= 0) {
                return true;
            }
        }
        return false;
    }
    
    public static int indexOfWhiteSpace(final String s) {
        return indexOfWhiteSpace(s, 0);
    }
    
    public static int indexOfWhiteSpace(final String s, final int startOffset) {
        final int length = s.length();
        int i = startOffset;
        while (i < length) {
            switch (s.charAt(i)) {
                case '\t':
                case '\n':
                case '\r':
                case ' ': {
                    return i;
                }
                default: {
                    ++i;
                    continue;
                }
            }
        }
        return -1;
    }
    
    public static boolean isDigit(final char c) {
        return '0' <= c && c <= '9';
    }
    
    public static boolean isEmpty(final String s) {
        return s == null || s.trim().length() == 0;
    }
    
    public static boolean isLegal(final String candidate, final String legalChars) {
        for (int i = 0; i < candidate.length(); ++i) {
            if (legalChars.indexOf(candidate.charAt(i)) < 0) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isLegal(final char candidate, final String legalChars) {
        return legalChars.indexOf(candidate) >= 0;
    }
    
    public static boolean isLetter(final char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
    }
    
    public static boolean isUnaccentedLowerCase(final char c) {
        return 'a' <= c && c <= 'z';
    }
    
    public static boolean isUnaccentedUpperCase(final char c) {
        return 'A' <= c && c <= 'Z';
    }
    
    public static boolean isVowel(final char c) {
        return c < '\u0180' && ST.vt.get(c);
    }
    
    public static String lastWord(String s) {
        s = s.trim();
        return s.substring(s.lastIndexOf(32) + 1);
    }
    
    public static String leftJustified(final int value, final int newLen, final boolean chop) {
        return rightPad(Integer.toString(value), newLen, chop);
    }
    
    public static String leftPad(final String s, final int newLen, final boolean chop) {
        final int grow = newLen - s.length();
        if (grow > 0) {
            return spaces(grow) + s;
        }
        if (chop) {
            return s.substring(0, newLen);
        }
        return s;
    }
    
    public static long parseDirtyLong(String numStr) {
        numStr = numStr.trim();
        final StringBuffer b = new StringBuffer(numStr.length());
        boolean negative = false;
        for (int i = 0, n = numStr.length(); i < n; ++i) {
            final char c = numStr.charAt(i);
            if (c == '-') {
                negative = true;
            }
            else if ('0' <= c && c <= '9') {
                b.append(c);
            }
        }
        numStr = b.toString();
        if (numStr.length() == 0) {
            return 0L;
        }
        long num = Long.parseLong(numStr);
        if (negative) {
            num = -num;
        }
        return num;
    }
    
    public static long parseLongPennies(String numStr) {
        numStr = numStr.trim();
        final StringBuffer b = new StringBuffer(numStr.length());
        boolean negative = false;
        int decpl = -1;
        for (int i = 0, n = numStr.length(); i < n; ++i) {
            final char c = numStr.charAt(i);
            switch (c) {
                case '-': {
                    negative = true;
                    break;
                }
                case '.': {
                    if (decpl == -1) {
                        decpl = 0;
                        break;
                    }
                    throw new NumberFormatException("more than one decimal point");
                }
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9': {
                    if (decpl != -1) {
                        ++decpl;
                    }
                    b.append(c);
                    break;
                }
            }
        }
        if (numStr.length() != b.length()) {
            numStr = b.toString();
        }
        if (numStr.length() == 0) {
            return 0L;
        }
        long num = Long.parseLong(numStr);
        if (decpl == -1 || decpl == 0) {
            num *= 100L;
        }
        else if (decpl != 2) {
            throw new NumberFormatException("wrong number of decimal places.");
        }
        if (negative) {
            num = -num;
        }
        return num;
    }
    
    public static String penniesToString(long pennies) {
        boolean negative;
        if (pennies < 0L) {
            pennies = -pennies;
            negative = true;
        }
        else {
            negative = false;
        }
        String s = Long.toString(pennies);
        final int len = s.length();
        switch (len) {
            case 1: {
                s = "0.0" + s;
                break;
            }
            case 2: {
                s = "0." + s;
                break;
            }
            default: {
                s = s.substring(0, len - 2) + "." + s.substring(len - 2, len);
                break;
            }
        }
        if (negative) {
            s = "-" + s;
        }
        return s;
    }
    
    public static int pluck(final String s) {
        int result = 0;
        try {
            result = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {}
        return result;
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
        final Vector keep = new Vector(lastNonBlankLine - firstNonBlankLine + 1);
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
        final StringBuffer sb = new StringBuffer(sql.length() + 5);
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
    
    public static String rep(final char c, final int count) {
        if (c == ' ' && count <= "                                                                      ".length()) {
            return "                                                                      ".substring(0, count);
        }
        final char[] s = new char[count];
        for (int i = 0; i < count; ++i) {
            s[i] = c;
        }
        return new String(s).intern();
    }
    
    public static String rightJustified(final int value, final int newLen, final boolean chop) {
        return leftPad(Integer.toString(value), newLen, chop);
    }
    
    public static String rightPad(final String s, final int newLen, final boolean chop) {
        final int grow = newLen - s.length();
        if (grow > 0) {
            return s + spaces(grow);
        }
        if (chop) {
            return s.substring(0, newLen);
        }
        return s;
    }
    
    public static String spaces(final int n) {
        if (n > "                                                                      ".length()) {
            return rep(' ', n);
        }
        if (n <= 0) {
            return "";
        }
        return "                                                                      ".substring(0, n);
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
        final StringBuffer b = new StringBuffer(len - 1);
        for (int i = 0; i < len; ++i) {
            final char c = s.charAt(i);
            if (c != ' ') {
                b.append(c);
            }
        }
        return b.toString();
    }
    
    public static String toBookTitleCase(String s) {
        final char[] ca = s.toCharArray();
        boolean changed = false;
        boolean capitalise = true;
        boolean firstCap = true;
        for (int i = 0; i < ca.length; ++i) {
            final char oldLetter = ca[i];
            if (oldLetter == '\'') {
                capitalise = ((i > 1 && ca[i - 1] == 'o') || ca[i - 1] == 'O');
            }
            else if (oldLetter <= '/' || (':' <= oldLetter && oldLetter <= '?') || (']' <= oldLetter && oldLetter <= '`')) {
                capitalise = true;
            }
            else {
                if (capitalise && !firstCap) {
                    capitalise = (!s.substring(i, Math.min(i + 4, s.length())).equalsIgnoreCase("the ") && !s.substring(i, Math.min(i + 3, s.length())).equalsIgnoreCase("of ") && !s.substring(i, Math.min(i + 3, s.length())).equalsIgnoreCase("to "));
                }
                final char newLetter = capitalise ? Character.toUpperCase(oldLetter) : Character.toLowerCase(oldLetter);
                ca[i] = newLetter;
                changed |= (newLetter != oldLetter);
                capitalise = false;
                firstCap = false;
            }
        }
        if (changed) {
            s = new String(ca);
        }
        return s;
    }
    
    public static String toHexString(final int h) {
        String s = Integer.toHexString(h);
        if (s.length() < 8) {
            s = "00000000".substring(0, 8 - s.length()) + s;
        }
        return "0x" + s;
    }
    
    public static String toLZ(final int i, final int len) {
        final String s = Integer.toString(i);
        if (s.length() > len) {
            return s.substring(s.length() - len);
        }
        if (s.length() < len) {
            return "000000000000000000000000000000".substring(0, len - s.length()) + s;
        }
        return s;
    }
    
    public static String toLZ(final long l, final int len) {
        final String s = Long.toString(l);
        if (s.length() > len) {
            return s.substring(s.length() - len);
        }
        if (s.length() < len) {
            return "000000000000000000000000000000".substring(0, len - s.length()) + s;
        }
        return s;
    }
    
    public static String toLZHexString(final int value, final int len) {
        final String s = Integer.toHexString(value);
        if (s.length() > len) {
            return s.substring(s.length() - len);
        }
        if (s.length() < len) {
            return "0000000".substring(0, len - s.length()) + s;
        }
        return s;
    }
    
    public static char toLowerCase(final char c) {
        return ('A' <= c && c <= 'Z') ? ((char)(c + ' ')) : c;
    }
    
    public static String toLowerCase(final String s) {
        final char[] ca = s.toCharArray();
        final int length = ca.length;
        boolean changed = false;
        for (int i = 0; i < length; ++i) {
            final char c = ca[i];
            if ('A' <= c && c <= 'Z') {
                ca[i] = (char)(c + ' ');
                changed = true;
            }
        }
        return changed ? new String(ca) : s;
    }
    
    public static String toString(final Color c) {
        String s = Integer.toHexString(c.getRGB() & 0xFFFFFF);
        if (s.length() < 6) {
            s = "000000".substring(0, 6 - s.length()) + s;
        }
        return '#' + s;
    }
    
    public static char toUpperCase(final char c) {
        return ('a' <= c && c <= 'z') ? ((char)(c - 32)) : c;
    }
    
    public static String toUpperCase(final String s) {
        final char[] ca = s.toCharArray();
        final int length = ca.length;
        boolean changed = false;
        for (int i = 0; i < length; ++i) {
            final char c = ca[i];
            if ('a' <= c && c <= 'z') {
                ca[i] = (char)(c - 32);
                changed = true;
            }
        }
        return changed ? new String(ca) : s;
    }
    
    public static String trimLeading(final String s) {
        if (s == null) {
            return null;
        }
        int len;
        int st;
        for (len = s.length(), st = 0; st < len && s.charAt(st) <= ' '; ++st) {}
        return (st > 0) ? s.substring(st, len) : s;
    }
    
    public static String trimLeading(final String text, final char c) {
        final int count = countLeading(text, c);
        return text.substring(count);
    }
    
    public static String trimLeading(final String text, final String charsToTrim) {
        final int count = countLeading(text, charsToTrim);
        return text.substring(count);
    }
    
    public static String trimTrailing(final String s) {
        if (s == null) {
            return null;
        }
        int origLen;
        int len;
        for (len = (origLen = s.length()); len > 0 && s.charAt(len - 1) <= ' '; --len) {}
        return (len != origLen) ? s.substring(0, len) : s;
    }
    
    public static String trimTrailing(final String text, final char c) {
        final int count = countTrailing(text, c);
        return text.substring(0, text.length() - count);
    }
    
    public static String trimTrailing(final String text, final String charsToTrim) {
        final int count = countTrailing(text, charsToTrim);
        return text.substring(0, text.length() - count);
    }
    
    public static void main(final String[] args) {
    }
    
    static {
        (vt = new BitSet(384)).set(65);
        ST.vt.set(69);
        ST.vt.set(73);
        ST.vt.set(79);
        ST.vt.set(85);
        ST.vt.set(97);
        ST.vt.set(101);
        ST.vt.set(105);
        ST.vt.set(111);
        ST.vt.set(117);
        ST.vt.set(192, 198);
        ST.vt.set(200, 207);
        ST.vt.set(210, 214);
        ST.vt.set(216, 220);
        ST.vt.set(224, 230);
        ST.vt.set(232, 239);
        ST.vt.set(242, 246);
        ST.vt.set(248, 252);
        ST.vt.set(256, 261);
        ST.vt.set(274, 283);
        ST.vt.set(296, 303);
        ST.vt.set(304);
        ST.vt.set(306, 307);
        ST.vt.set(332, 335);
        ST.vt.set(336, 339);
        ST.vt.set(360, 367);
        ST.vt.set(368, 371);
    }
}
