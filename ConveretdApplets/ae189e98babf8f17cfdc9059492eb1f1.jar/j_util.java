import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class j_util
{
    public static byte i;
    public static byte curchar;
    public static byte decompChar;
    public static byte accumulator;
    public static byte aCurbit;
    public static byte wCurbit;
    public static int inLen;
    public static String out;
    public static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final char[] SoundexTable;
    
    public static void centerDialog(final Dialog dialog, final Frame frame) {
        final Dimension screenSize = frame.getToolkit().getScreenSize();
        final int width = dialog.bounds().width;
        final int height = dialog.bounds().height;
        int n = frame.location().x + frame.bounds().width / 2 - width / 2;
        int n2 = frame.location().y + frame.bounds().height / 2 - height / 2;
        if (n < 0) {
            n = 10;
        }
        if (n2 < 0) {
            n2 = 10;
        }
        if (n + width > screenSize.width) {
            n = screenSize.width - width - 10;
        }
        if (n2 + height > screenSize.height) {
            n2 = screenSize.height - height - 10;
        }
        dialog.reshape(n, n2, width, height);
    }
    
    public static String compWord(String upperCase) {
        j_util.out = "";
        j_util.accumulator = 0;
        j_util.aCurbit = 7;
        upperCase = upperCase.toUpperCase();
        j_util.inLen = upperCase.length();
        j_util.i = 0;
        while (j_util.i < j_util.inLen) {
            j_util.curchar = (byte)upperCase.charAt(j_util.i);
            if (j_util.curchar < 65 || j_util.curchar > 90) {
                return "";
            }
            j_util.curchar -= 64;
            j_util.wCurbit = 4;
            while (j_util.wCurbit >= 0) {
                if (j_util.aCurbit < 0) {
                    j_util.out = String.valueOf(j_util.out) + (char)j_util.accumulator;
                    j_util.accumulator = 0;
                    j_util.aCurbit = 7;
                }
                j_util.accumulator += (byte)((int)Math.pow(2.0, j_util.aCurbit) * ((int)Math.pow(2.0, j_util.wCurbit) & j_util.curchar) >>> j_util.wCurbit);
                --j_util.aCurbit;
                --j_util.wCurbit;
            }
            ++j_util.i;
        }
        if (j_util.accumulator != 0) {
            j_util.out = String.valueOf(j_util.out) + (char)j_util.accumulator;
        }
        return j_util.out;
    }
    
    public static String decompWord(final String s) {
        j_util.out = "";
        j_util.accumulator = 0;
        j_util.aCurbit = 4;
        j_util.inLen = s.length();
        j_util.i = 0;
        while (j_util.i < j_util.inLen) {
            j_util.decompChar = (byte)s.charAt(j_util.i);
            j_util.wCurbit = 7;
            while (j_util.wCurbit >= 0) {
                if (j_util.aCurbit < 0 && j_util.accumulator != 0) {
                    j_util.out = String.valueOf(j_util.out) + (char)(j_util.accumulator + 64);
                    j_util.accumulator = 0;
                    j_util.aCurbit = 4;
                }
                j_util.accumulator += (byte)((int)Math.pow(2.0, j_util.aCurbit) * ((int)Math.pow(2.0, j_util.wCurbit) & j_util.decompChar) >>> j_util.wCurbit);
                --j_util.aCurbit;
                --j_util.wCurbit;
            }
            ++j_util.i;
        }
        if (j_util.accumulator != 0) {
            j_util.out = String.valueOf(j_util.out) + (char)(j_util.accumulator + 64);
        }
        return j_util.out;
    }
    
    public static double soundexsim(final String s, final String s2) {
        return eqscore(soundex(s, false), soundex(s2, false), 'E');
    }
    
    public static double eqscore(final String s, final String s2) {
        return eqscore(s, s2, 'E');
    }
    
    public static double eqscore(String trim, String trim2, final char c) {
        double n = 0.0;
        trim = trim.toUpperCase().trim();
        trim2 = trim2.toUpperCase().trim();
        if (trim.equals(trim2)) {
            n = 1.0;
        }
        else if (trim.equals("") || trim2.equals("")) {
            n = 0.0;
        }
        else {
            double n2 = 0.0;
            int n3 = 0;
            int n4 = 0;
            int n5 = 0;
            if (trim.length() > trim2.length()) {
                final String s = new String(trim);
                trim = trim2;
                trim2 = s;
            }
            final StringBuffer sb = new StringBuffer(trim);
            final StringBuffer sb2 = new StringBuffer(trim2);
            int length = sb.length();
            final int length2 = sb2.length();
            final double n6 = (length2 > 1) ? (length2 + addseries(length2 - 1)) : 1.0;
            for (int i = 0; i < length; ++i) {
                if (c == 'F') {
                    n2 = (length2 - i) / n6;
                }
                else if (c == 'E') {
                    n2 = 1.0 / length2;
                }
                if (sb.charAt(i) == sb2.charAt(i)) {
                    ++n3;
                    n += n2;
                }
                else if (length - 1 > i && length != length2 && sb.charAt(i) == sb2.charAt(i + 1) && sb.charAt(i + 1) != sb2.charAt(i + 1)) {
                    ++n4;
                    sb.insert(i, sb2.charAt(i + 1));
                    ++length;
                }
                else if (length - 1 > i && (sb.charAt(i + 1) == sb2.charAt(i) || sb2.charAt(i + 1) == sb.charAt(i))) {
                    if (sb.charAt(i + 1) == sb2.charAt(i) && sb2.charAt(i + 1) == sb.charAt(i)) {
                        n += n2 / 2.0;
                    }
                    ++n5;
                    final char char1 = sb.charAt(i);
                    sb.setCharAt(i, sb.charAt(i + 1));
                    sb.setCharAt(i + 1, char1);
                }
            }
        }
        return n;
    }
    
    public static double factorial(final int n) {
        if (n > 1) {
            return n * factorial(n - 1);
        }
        return 1.0;
    }
    
    public static double addseries(final int n) {
        if (n > 1) {
            return n + addseries(n - 1);
        }
        return 1.0;
    }
    
    public static String soundex(final String s) {
        return soundex(s, true);
    }
    
    public static String soundex(final String s, final boolean b) {
        int i = 0;
        char c = ' ';
        final StringBuffer sb = new StringBuffer("    ");
        final String upperCase = s.toUpperCase();
        for (int n = 0; n < upperCase.length() && i < 4; ++n) {
            final char char1 = upperCase.charAt(n);
            if (!Character.isLetter(char1)) {
                break;
            }
            final char c2 = j_util.SoundexTable["ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(char1)];
            if (i == 0) {
                sb.setCharAt(i, char1);
                ++i;
                c = ' ';
            }
            else {
                if (c2 != c && c2 != '0') {
                    sb.setCharAt(i, c2);
                    ++i;
                }
                c = c2;
            }
        }
        if (b) {
            while (i < 4) {
                sb.setCharAt(i, '0');
                ++i;
            }
        }
        return sb.toString();
    }
    
    public static void main(final String[] array) {
        final int n = (int)(eqscore(array[0], array[1], 'E') * 100.0);
        final int n2 = (int)(eqscore(soundex(array[0], false), soundex(array[1], false), 'E') * 100.0);
        final int n3 = (n + n2) / 2;
        System.out.println("   Plain Similarity: " + n);
        System.out.println("Phonetic Similarity: " + n2);
        System.out.println(" Average Similarity: " + n3);
    }
    
    static {
        SoundexTable = new char[] { '0', '1', '2', '3', '0', '1', '2', '0', '0', '2', '2', '4', '5', '5', '0', '1', '2', '6', '2', '3', '0', '1', '0', '2', '0', '2' };
    }
}
