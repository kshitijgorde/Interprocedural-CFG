// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graphApi.io;

import java.awt.Color;

public class CSSColourParser
{
    public static String colorToHex(final Color color) {
        String s = new String("#");
        final String hexString = Integer.toHexString(color.getRed());
        if (hexString.length() > 2) {
            hexString.substring(0, 2);
        }
        else if (hexString.length() < 2) {
            s = s + "0" + hexString;
        }
        else {
            s += hexString;
        }
        final String hexString2 = Integer.toHexString(color.getGreen());
        if (hexString2.length() > 2) {
            hexString2.substring(0, 2);
        }
        else if (hexString2.length() < 2) {
            s = s + "0" + hexString2;
        }
        else {
            s += hexString2;
        }
        final String hexString3 = Integer.toHexString(color.getBlue());
        if (hexString3.length() > 2) {
            hexString3.substring(0, 2);
        }
        else if (hexString3.length() < 2) {
            s = s + "0" + hexString3;
        }
        else {
            s += hexString3;
        }
        return s;
    }
    
    static final Color hexToColor(final String s) {
        s.length();
        String substring;
        if (s.startsWith("#")) {
            substring = s.substring(1, Math.min(s.length(), 7));
        }
        else {
            substring = s;
        }
        final String string = "0x" + substring;
        Color decode;
        try {
            decode = Color.decode(string);
        }
        catch (NumberFormatException ex) {
            decode = null;
        }
        return decode;
    }
    
    public static Color stringToColor(final String s) {
        Color color;
        if (s == null || s.length() == 0) {
            color = Color.black;
        }
        else if (s.startsWith("rgb(")) {
            color = parseRGB(s);
        }
        else if (s.charAt(0) == '#') {
            color = hexToColor(s);
        }
        else if (s.equalsIgnoreCase("Black")) {
            color = hexToColor("#000000");
        }
        else if (s.equalsIgnoreCase("Silver")) {
            color = hexToColor("#C0C0C0");
        }
        else if (s.equalsIgnoreCase("Gray")) {
            color = hexToColor("#808080");
        }
        else if (s.equalsIgnoreCase("White")) {
            color = hexToColor("#FFFFFF");
        }
        else if (s.equalsIgnoreCase("Maroon")) {
            color = hexToColor("#800000");
        }
        else if (s.equalsIgnoreCase("Red")) {
            color = hexToColor("#FF0000");
        }
        else if (s.equalsIgnoreCase("Purple")) {
            color = hexToColor("#800080");
        }
        else if (s.equalsIgnoreCase("Fuchsia")) {
            color = hexToColor("#FF00FF");
        }
        else if (s.equalsIgnoreCase("Green")) {
            color = hexToColor("#008000");
        }
        else if (s.equalsIgnoreCase("Lime")) {
            color = hexToColor("#00FF00");
        }
        else if (s.equalsIgnoreCase("Olive")) {
            color = hexToColor("#808000");
        }
        else if (s.equalsIgnoreCase("Yellow")) {
            color = hexToColor("#FFFF00");
        }
        else if (s.equalsIgnoreCase("Navy")) {
            color = hexToColor("#000080");
        }
        else if (s.equalsIgnoreCase("Blue")) {
            color = hexToColor("#0000FF");
        }
        else if (s.equalsIgnoreCase("Teal")) {
            color = hexToColor("#008080");
        }
        else if (s.equalsIgnoreCase("Aqua")) {
            color = hexToColor("#00FFFF");
        }
        else {
            color = hexToColor(s);
        }
        return color;
    }
    
    private static Color parseRGB(final String s) {
        final int[] array = { 4 };
        return new Color(getColorComponent(s, array), getColorComponent(s, array), getColorComponent(s, array));
    }
    
    private static int getColorComponent(final String s, final int[] array) {
        final int length = s.length();
        char char1;
        while (array[0] < length && (char1 = s.charAt(array[0])) != '-' && !Character.isDigit(char1) && char1 != '.') {
            final int n = 0;
            ++array[n];
        }
        final int n2 = array[0];
        if (n2 < length && s.charAt(array[0]) == '-') {
            final int n3 = 0;
            ++array[n3];
        }
        while (array[0] < length && Character.isDigit(s.charAt(array[0]))) {
            final int n4 = 0;
            ++array[n4];
        }
        if (array[0] < length && s.charAt(array[0]) == '.') {
            final int n5 = 0;
            ++array[n5];
            while (array[0] < length && Character.isDigit(s.charAt(array[0]))) {
                final int n6 = 0;
                ++array[n6];
            }
        }
        if (n2 != array[0]) {
            try {
                float float1 = Float.parseFloat(s.substring(n2, array[0]));
                if (array[0] < length && s.charAt(array[0]) == '%') {
                    final int n7 = 0;
                    ++array[n7];
                    float1 = float1 * 255.0f / 100.0f;
                }
                return Math.min(255, Math.max(0, (int)float1));
            }
            catch (NumberFormatException ex) {}
        }
        return 0;
    }
}
