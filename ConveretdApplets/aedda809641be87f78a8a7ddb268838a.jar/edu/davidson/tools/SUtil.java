// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.tools;

import edu.davidson.numerics.Parser;
import edu.davidson.display.Format;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Graphics;

public final class SUtil
{
    public static void drawArrow(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        drawArrow(graphics, n, n2, n3, n4, 5);
    }
    
    public static void drawArrow(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        graphics.drawLine(n, n2, n3, n4);
        final double n6 = n3 - n;
        final double n7 = -(n4 - n2);
        final double sqrt = Math.sqrt(n6 * n6 + n7 * n7);
        double n8;
        if (sqrt > 3 * n5) {
            n8 = n5;
        }
        else {
            n8 = sqrt / 3;
        }
        if (sqrt > 1) {
            final double n9 = n8 * n6 / sqrt;
            final double n10 = -(n8 * n7 / sqrt);
            final double n11 = n3 - 3 * n9;
            final double n12 = n4 - 3 * n10;
            graphics.drawLine((int)(n11 - n10), (int)(n12 + n9), n3, n4);
            graphics.drawLine((int)(n11 + n10), (int)(n12 - n9), n3, n4);
        }
    }
    
    public static void drawThickArrow(final Graphics graphics, final int n, final int n2, final int n3, final int n4, int max, int n5) {
        if (n5 < 2) {
            drawSolidArrow(graphics, n, n2, n3, n4, max);
            return;
        }
        final double n6 = n3 - n;
        final double n7 = -(n4 - n2);
        final double sqrt = Math.sqrt(n6 * n6 + n7 * n7);
        max = Math.max(1, max + n5 / 2);
        if (sqrt < 4 * max) {
            max = (int)(sqrt / 4);
        }
        if (2 * n5 > max) {
            n5 = max / 2;
        }
        final double max2 = Math.max(n5 / 2.0, 2.0);
        final double n8 = max * n6 / sqrt;
        final double n9 = -(max * n7 / sqrt);
        final double n10 = n3 - 3 * n8;
        final double n11 = n4 - 3 * n9;
        final int n12 = -(int)Math.round(max2 * n7 / sqrt);
        final int n13 = -(int)Math.round(max2 * n6 / sqrt);
        graphics.fillPolygon(new int[] { n - n12, (int)n10 - n12, (int)(n10 - n9), n3, (int)(n10 + n9), (int)n10 + n12, n + n12 }, new int[] { n2 - n13, (int)n11 - n13, (int)(n11 + n8), n4, (int)(n11 - n8), (int)n11 + n13, n2 + n13 }, 7);
    }
    
    public static void drawThickLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        final double n6 = n5 / 2.0;
        final double n7 = n3 - n;
        final double n8 = n4 - n2;
        double n9;
        if (n == n3) {
            n9 = 3.141592653589793;
        }
        else {
            n9 = Math.atan(n8 / n7) + 1.5707963267948966;
        }
        final int n10 = (int)(n6 * Math.cos(n9));
        final int n11 = (int)(n6 * Math.sin(n9));
        graphics.fillPolygon(new int[] { n - n10, n3 - n10, n3 + n10, n + n10 }, new int[] { n2 - n11, n4 - n11, n4 + n11, n2 + n11 }, 4);
    }
    
    public static void drawSolidArrow(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        graphics.drawLine(n, n2, n3, n4);
        final double n6 = n3 - n;
        final double n7 = n4 - n2;
        final double sqrt = Math.sqrt(n6 * n6 + n7 * n7);
        final double n8 = n5 * n6 / sqrt;
        final double n9 = n5 * n7 / sqrt;
        final double n10 = n3 - 3 * n8;
        final double n11 = n4 - 3 * n9;
        graphics.fillPolygon(new int[] { n3, (int)(n10 - n9), (int)(n10 + n9) }, new int[] { n4, (int)(n11 + n8), (int)(n11 - n8) }, 3);
    }
    
    public static void drawArrowHead(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        final double n6 = n3 - n;
        final double n7 = -(n4 - n2);
        final double sqrt = Math.sqrt(n6 * n6 + n7 * n7);
        final double n8 = n5 * n6 / sqrt;
        final double n9 = -(n5 * n7 / sqrt);
        final double n10 = n - 3 * n8;
        final double n11 = n2 - 3 * n9;
        graphics.drawLine((int)(n10 - n9), (int)(n11 + n8), n, n2);
        graphics.drawLine((int)(n10 + n9), (int)(n11 - n8), n, n2);
    }
    
    public static void drawSolidArrowHead(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        final double n6 = n3 - n;
        final double n7 = -(n4 - n2);
        final double sqrt = Math.sqrt(n6 * n6 + n7 * n7);
        final double n8 = n5 * n6 / sqrt;
        final double n9 = -(n5 * n7 / sqrt);
        final double n10 = n - 3 * n8;
        final double n11 = n2 - 3 * n9;
        graphics.fillPolygon(new int[] { n, (int)(n10 - n9), (int)(n10 + n9) }, new int[] { n2, (int)(n11 + n8), (int)(n11 - n8) }, 3);
    }
    
    public static Color paleColor(final Color color) {
        return new Color(255 - (255 - color.getRed()) / 2, 255 - (255 - color.getGreen()) / 2, 255 - (255 - color.getBlue()) / 2);
    }
    
    public static Color veryPaleColor(final Color color) {
        return new Color(255 - (255 - color.getRed()) / 4, 255 - (255 - color.getGreen()) / 4, 255 - (255 - color.getBlue()) / 4);
    }
    
    public static Color complementColor(final Color color) {
        return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
    }
    
    public static double chop(final double n, final double n2) {
        if (Math.abs(n) < Math.abs(n2)) {
            return 0.0;
        }
        return n;
    }
    
    public static String removeWhitespace(final String s) {
        final StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < s.length(); ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    
    public static boolean parameterExist(final String s, String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",;");
        for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens; ++i) {
            final String lowerCase2 = stringTokenizer.nextToken().trim().toLowerCase();
            if (lowerCase2 != null && !lowerCase2.equals("") && lowerCase2.startsWith(lowerCase)) {
                return true;
            }
        }
        return false;
    }
    
    private static String convertEscapeCharacter(final String s) {
        if (s == null || s.length() < 1) {
            return s;
        }
        final StringBuffer sb = new StringBuffer(s.length());
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != '\\') {
                sb.append(s.charAt(i));
            }
            else {
                sb.append(s.charAt(i));
                if (i < s.length() - 1 && s.charAt(i + 1) == ',') {
                    sb.append("#");
                }
                if (i < s.length() - 1 && s.charAt(i + 1) == '=') {
                    sb.append("@");
                }
                ++i;
            }
        }
        return sb.toString();
    }
    
    private static String restoreEscapeCharacter(final String s) {
        if (s == null || s.length() < 1) {
            return s;
        }
        final StringBuffer sb = new StringBuffer(s.length());
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != '\\') {
                sb.append(s.charAt(i));
            }
            else {
                if (i < s.length() - 1 && s.charAt(i + 1) == '#') {
                    sb.append(",");
                }
                if (i < s.length() - 1 && s.charAt(i + 1) == '@') {
                    sb.append("=");
                }
                ++i;
            }
        }
        return sb.toString();
    }
    
    public static double getParam(final String s, String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",;");
        final int countTokens = stringTokenizer.countTokens();
        final double n = 0.0;
        for (int i = 0; i < countTokens; ++i) {
            final String lowerCase2 = stringTokenizer.nextToken().trim().toLowerCase();
            if (lowerCase2 != null && !lowerCase2.equals("") && lowerCase2.startsWith(lowerCase)) {
                final String substring = lowerCase2.substring(lowerCase.length());
                try {
                    Double.valueOf(substring);
                }
                catch (Exception ex) {
                    System.out.println(String.valueOf(String.valueOf(new StringBuffer("Parameter in add method is not a number. param ").append(lowerCase).append(" ").append(substring))));
                }
                return Format.atof(substring);
            }
        }
        System.out.println(String.valueOf(String.valueOf(new StringBuffer("Error: parameter not found. paramList:").append(s).append("parameter:").append(lowerCase))));
        return n;
    }
    
    private static String removeWhitespaceToEqual(final String s) {
        boolean b = false;
        final StringBuffer sb = new StringBuffer(s.length());
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '=') {
                b = true;
            }
            if (b || !Character.isWhitespace(s.charAt(i))) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    
    public static String getParamStr(String convertEscapeCharacter, final String s) {
        convertEscapeCharacter = convertEscapeCharacter(convertEscapeCharacter);
        final StringTokenizer stringTokenizer = new StringTokenizer(convertEscapeCharacter, ",;");
        for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens; ++i) {
            final String removeWhitespaceToEqual = removeWhitespaceToEqual(stringTokenizer.nextToken());
            if (removeWhitespaceToEqual != null && !removeWhitespaceToEqual.equals("") && removeWhitespaceToEqual.startsWith(s)) {
                return restoreEscapeCharacter(removeWhitespaceToEqual.substring(s.length()));
            }
        }
        System.out.println(String.valueOf(String.valueOf(new StringBuffer("Error: parameter not found. paramList:").append(convertEscapeCharacter).append("parameter:").append(s))));
        return null;
    }
    
    public static boolean isValidFunction(final String s, final String s2) {
        if (s == null) {
            return false;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s2, ",;");
        final int countTokens = stringTokenizer.countTokens();
        final String[] array = new String[countTokens];
        for (int i = 0; i < countTokens; ++i) {
            array[i] = removeWhitespaceToEqual(stringTokenizer.nextToken());
        }
        final Parser parser = new Parser(array.length);
        for (int j = 0; j < array.length; ++j) {
            parser.defineVariable(j + 1, array[j]);
        }
        parser.define(s);
        parser.parse();
        if (parser.getErrorCode() != 0) {
            System.out.println("Failed to parse function): ".concat(String.valueOf(String.valueOf(s))));
            System.out.println(String.valueOf(String.valueOf(new StringBuffer("Parse error: ").append(parser.getErrorString()).append(" at function 1, position ").append(parser.getErrorPosition()))));
            for (int k = 0; k < array.length; ++k) {
                System.out.println("vars ".concat(String.valueOf(String.valueOf(array[k]))));
            }
            return false;
        }
        return true;
    }
}
