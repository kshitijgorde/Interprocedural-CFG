import java.awt.Color;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class Convert
{
    private static String[] Vector2StringArray(final Vector v) {
        final String[] strn = new String[v.size()];
        for (int i = 0; i < v.size(); ++i) {
            strn[i] = v.elementAt(i);
        }
        return strn;
    }
    
    public static String[] string2Array(final String strn, final String delimiter) {
        final Vector v = new Vector();
        int beginIndex = 0;
        int endIndex;
        do {
            endIndex = strn.indexOf(delimiter, beginIndex);
            int endPos;
            if (endIndex != -1) {
                endPos = endIndex;
            }
            else {
                endPos = strn.length();
            }
            final String s = strn.substring(beginIndex, endPos).trim();
            if (!s.equals("")) {
                v.addElement(s);
            }
            beginIndex = endIndex + 1;
        } while (endIndex != -1);
        final String[] strnArray = Vector2StringArray(v);
        return strnArray;
    }
    
    public static Color string2Color(String clr) {
        clr = clr.trim().toLowerCase();
        if (clr.equals("black")) {
            return Color.black;
        }
        if (clr.equals("blue")) {
            return Color.blue;
        }
        if (clr.equals("cyan")) {
            return Color.cyan;
        }
        if (clr.equals("darkgray")) {
            return Color.darkGray;
        }
        if (clr.equals("gray")) {
            return Color.gray;
        }
        if (clr.equals("green")) {
            return Color.green;
        }
        if (clr.equals("lightgray")) {
            return Color.lightGray;
        }
        if (clr.equals("magenta")) {
            return Color.magenta;
        }
        if (clr.equals("orange")) {
            return Color.orange;
        }
        if (clr.equals("pink")) {
            return Color.pink;
        }
        if (clr.equals("red")) {
            return Color.red;
        }
        if (clr.equals("white")) {
            return Color.white;
        }
        if (clr.equals("yellow")) {
            return Color.yellow;
        }
        try {
            final int clrInt = Integer.valueOf(string2Int(clr));
            System.out.println(clrInt);
            return new Color(clrInt);
        }
        catch (Exception e) {
            return Color.black;
        }
    }
    
    private static String string2Int(String num) {
        if (num.indexOf("\\x") == 0 || num.indexOf("&h") == 0 || num.indexOf("#") == 0) {
            num = hexa2Int(num);
        }
        else {
            num = string2Number(num, "");
        }
        return num;
    }
    
    private static String hexa2Int(String num) {
        double decimalValue = 0.0;
        if (num.indexOf("\\x") == 0 || num.indexOf("&h") == 0) {
            num = num.substring(2);
        }
        else {
            num = num.substring(1);
        }
        num = string2Number(num, "abcdef");
        for (int strLen = num.length(), i = 0; i < strLen; ++i) {
            final char ch = num.charAt(i);
            final int hex = "0123456789abcdef".indexOf(ch);
            decimalValue += hex * Math.pow(16.0, strLen - 1 - i);
        }
        return (int)decimalValue + "";
    }
    
    private static String string2Number(final String strn, final String otherChars) {
        String decimalValue = "";
        final String validChars = "-+0123456789." + otherChars;
        for (int i = 0; i < strn.length(); ++i) {
            final char ch = strn.charAt(i);
            if (validChars.indexOf(ch) == -1) {
                break;
            }
            decimalValue += ch;
        }
        return decimalValue;
    }
}
