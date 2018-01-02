// 
// Decompiled by Procyon v0.5.30
// 

package myspeedserver.applet;

import java.lang.reflect.Array;
import java.util.Vector;
import java.awt.Insets;
import java.util.StringTokenizer;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Font;

public class Util
{
    public static final int KT = 0;
    public static final int LT = 1;
    public static final int MT = 2;
    private static int[] IT;
    private static int[] JT;
    
    static {
        Base64.encode(new byte[0]);
        Util.IT = new int[] { 0, 2, 3, 1 };
        Util.JT = new int[] { 10, 18, 11, 12, 17, 10, 13, 16, 15, 14 };
    }
    
    public static Font getFont(final String[] array, final int n, final int n2) {
        final String[] availableFontFamilyNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        String s = null;
        for (int n3 = 0; s == null && n3 < array.length; ++n3) {
            for (int n4 = 0; availableFontFamilyNames != null && n4 < availableFontFamilyNames.length; ++n4) {
                if (array[n3].toLowerCase().equals(availableFontFamilyNames[n4].toLowerCase())) {
                    s = availableFontFamilyNames[n4];
                    break;
                }
            }
        }
        return new Font((s == null) ? "Dialog" : s, n, n2);
    }
    
    public static void gradientFill(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color, final Color color2) {
        for (int i = 0; i < n4; ++i) {
            final float n5 = i / n4;
            graphics.setColor(new Color(mid(color.getRed(), color2.getRed(), n5), mid(color.getGreen(), color2.getGreen(), n5), mid(color.getBlue(), color2.getBlue(), n5)));
            graphics.drawLine(n, i + n2, n + n3 - 1, i + n2);
        }
    }
    
    public static void horzGradientFill(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color, final Color color2) {
        for (int i = 0; i < n3; ++i) {
            final float n5 = i / n3;
            graphics.setColor(new Color(mid(color.getRed(), color2.getRed(), n5), mid(color.getGreen(), color2.getGreen(), n5), mid(color.getBlue(), color2.getBlue(), n5)));
            graphics.drawLine(i + n, n2, i + n, n2 + n4);
        }
    }
    
    public static void setAntialias(final Graphics graphics, final boolean b) {
        ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, b ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
    }
    
    private static int mid(final int n, final int n2, final float n3) {
        return (int)(n + n2 * n3 - n * n3);
    }
    
    public static String oneDP(final double n) {
        return new StringBuffer().append((int)(n * 10.0) / 10.0f).toString();
    }
    
    public static float getMOSScore(final double n, final double n2) {
        if (n < 0.0 || n2 == -1.0) {
            return 0.0f;
        }
        double n3 = 1.0;
        if (n <= 100.0) {
            n3 = -0.028 * n + 4.3;
        }
        else if (n <= 200.0) {
            n3 = -0.005 * n + 2.0;
        }
        double n4 = 1.0;
        if (n2 <= 1.0) {
            n4 = -1.3 * n2 + 4.3;
        }
        else if (n2 < -5.0) {
            n4 = -0.5 * n2 + 3.5;
        }
        return (int)(10.0 * Math.min(n3, n4)) / 10.0f;
    }
    
    public static void gbAdd(final Container container, final Component component, final int n, final int n2, final String s) {
        final GridBagConstraints gbc = makeGBC(n, n2, s);
        container.add(component);
        try {
            ((GridBagLayout)container.getLayout()).setConstraints(component, gbc);
        }
        catch (Exception ex) {
            System.out.println("gbAdd setConstraints failed: " + ex);
        }
    }
    
    private static GridBagConstraints makeGBC(final int gridx, final int gridy, final String s) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.anchor = 17;
        if (s != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "whafxypiltrb", true);
            while (stringTokenizer.hasMoreTokens()) {
                try {
                    final String nextToken = stringTokenizer.nextToken();
                    final int int1 = Integer.parseInt(stringTokenizer.nextToken());
                    if (nextToken.equals("w")) {
                        gridBagConstraints.gridwidth = int1;
                    }
                    else if (nextToken.equals("h")) {
                        gridBagConstraints.gridheight = int1;
                    }
                    else if (nextToken.equals("a")) {
                        gridBagConstraints.anchor = Util.JT[int1];
                    }
                    else if (nextToken.equals("f")) {
                        gridBagConstraints.fill = Util.IT[int1];
                    }
                    else if (nextToken.equals("x")) {
                        gridBagConstraints.weightx = int1;
                    }
                    else if (nextToken.equals("y")) {
                        gridBagConstraints.weighty = int1;
                    }
                    else if (nextToken.equals("p")) {
                        gridBagConstraints.ipadx = int1;
                        gridBagConstraints.ipady = int1;
                    }
                    else if (nextToken.equals("i")) {
                        gridBagConstraints.insets = new Insets(int1, int1, int1, int1);
                    }
                    else if (nextToken.equals("l")) {
                        gridBagConstraints.insets.left = int1;
                    }
                    else if (nextToken.equals("t")) {
                        gridBagConstraints.insets.top = int1;
                    }
                    else if (nextToken.equals("r")) {
                        gridBagConstraints.insets.right = int1;
                    }
                    else if (nextToken.equals("b")) {
                        gridBagConstraints.insets.bottom = int1;
                    }
                    else {
                        System.out.println("?GBC:" + nextToken);
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    break;
                }
            }
        }
        return gridBagConstraints;
    }
    
    public static String httpDecode(final String s) {
        if (s == null) {
            return null;
        }
        final String replace = s.replace('+', ' ');
        int n = 0;
        final StringBuffer sb = new StringBuffer();
        int index;
        while ((index = replace.indexOf(37, n)) >= 0) {
            sb.append(replace.substring(n, index));
            sb.append((char)Integer.parseInt(replace.substring(index + 1, index + 3), 16));
            n = index + 3;
        }
        sb.append((n == 0) ? replace : replace.substring(n, replace.length()));
        return sb.toString();
    }
    
    public static void drawShadow(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final int[][] array = { { 244, 224, 201, 182, 173 }, { 246, 232, 213, 198, 192 }, { 250, 241, 230, 221, 217 }, { 253, 249, 244, 240, 238 } };
        final int n5 = 5;
        final int n6 = 170;
        final int n7 = 249;
        for (int i = 1; i <= n5; ++i) {
            final int n8 = n7 + (n5 - i) * (n6 - n7) / (n5 - 1);
            graphics.setColor(new Color(n8, n8, n8));
            graphics.drawLine(n, n2 + n4 + i, n + n3, n2 + n4 + i);
        }
        graphics.setColor(new Color(14474460));
        graphics.drawLine(n - 1, n2 + 1, n - 1, n2 + n4 + 1);
        graphics.drawLine(n + n3 + 1, n2 + 1, n + n3 + 1, n2 + n4 + 1);
        graphics.setColor(new Color(15921906));
        graphics.drawLine(n - 2, n2 + 3, n - 2, n2 + n4 + 1);
        graphics.drawLine(n + n3 + 2, n2 + 3, n + n3 + 2, n2 + n4 + 1);
        for (int j = 0; j < array.length; ++j) {
            for (int k = 0; k < array[0].length; ++k) {
                graphics.setColor(new Color(array[j][k], array[j][k], array[j][k]));
                graphics.drawLine(n + k - 2, n2 + n4 + j + 1, n + k - 2, n2 + n4 + j + 1);
                graphics.drawLine(n + n3 - k + 2, n2 + n4 + j + 1, n + n3 - k + 2, n2 + n4 + j + 1);
            }
        }
    }
    
    public static void drawToolTipRect(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final Color color = graphics.getColor();
        gradientFill(graphics, n + 1, n2 + 1, n3 - 2, n4 - 1, new Color(16514043), new Color(15856113));
        gradientFill(graphics, n + 2, n2 + 2, n3 - 4, n4 - 3, new Color(16382457), new Color(15263976));
        graphics.setColor(new Color(14342874));
        graphics.drawRoundRect(n, n2, n3, n4, 5, 5);
        graphics.setColor(color);
    }
    
    public static void drawOrangeButton(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        final Color color = graphics.getColor();
        if (n5 != 0) {
            if (n5 == 1) {
                graphics.setColor(new Color(14405273));
                final int n6 = (n5 == 1) ? (n4 / 2) : 5;
                graphics.drawLine(n + 1, n2, n + n3 - 1, n2);
                gradientFill(graphics, n, n2 + 1, n3, n6 - 1, new Color(14405273), new Color(12165236));
                gradientFill(graphics, n + 1, n2 + 1, n3 - 2, n6 - 1, new Color(16777211), new Color(16775651));
                gradientFill(graphics, n + 2, n2 + 2, n3 - 4, n6 - 2, new Color(16776684), new Color(16772537));
                gradientFill(graphics, n, n2 + n6, n3, n4 - n6 - 1, new Color(12099443), new Color(12631211));
                gradientFill(graphics, n + 1, n2 + n6, n3 - 2, n4 - n6 - 1, new Color(16773833), new Color(16777210));
                gradientFill(graphics, n + 2, n2 + n6, n3 - 4, n4 - n6 - 2, new Color(16767069), new Color(16775585));
                graphics.setColor(new Color(12631211));
                graphics.drawLine(n + 1, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
            }
            else if (n5 == 2) {
                final int n7 = n4 / 2;
                gradientFill(graphics, n + 1, n2 + 1, n3 - 2, 3, new Color(11699548), new Color(15904886));
                gradientFill(graphics, n + 1, n2 + 4, n3 - 2, n7 - 3, new Color(15839346), new Color(15832635));
                gradientFill(graphics, n + 1, n2 + n7, n3 - 2, n4 - n7, new Color(15562756), new Color(16756227));
                graphics.setColor(new Color(8087109));
                graphics.drawLine(n + 1, n2, n + n3 - 1, n2);
                graphics.drawLine(n, n2 + 1, n, n2 + n4 - 1);
                graphics.drawLine(n + n3, n2 + 1, n + n3, n2 + n4 - 1);
                graphics.drawLine(n + 1, n2 + 1, n + 1, n2 + 1);
                graphics.drawLine(n + n3 - 1, n2 + 1, n + n3 - 1, n2 + 1);
                graphics.setColor(new Color(12631211));
                graphics.drawLine(n + 1, n2 + n4, n + n3 - 1, n2 + n4);
            }
        }
        graphics.setColor(color);
    }
    
    public static double parseDouble(final String s, final double n) {
        try {
            return Double.valueOf(s);
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public static int parseInt(final String s, final int n) {
        try {
            return Integer.parseInt(s);
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public static long parseLong(final String s, final long n) {
        try {
            return Long.parseLong(s);
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    public static String parseString(final Object o, final String s) {
        return (String)((o instanceof String && o != null) ? o : s);
    }
    
    public static int constrainInt(final int n, final int n2, final int n3) {
        return (n < n2) ? n2 : ((n > n3) ? n3 : n);
    }
    
    public static long constrainLong(final long n, final long n2, final long n3) {
        return (n < n2) ? n2 : ((n > n3) ? n3 : n);
    }
    
    public static int[] toIntArray(final String s, final String s2) {
        if (s == null) {
            return null;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final int[] array = new int[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            try {
                array[i] = Integer.parseInt(stringTokenizer.nextToken());
            }
            catch (Exception ex) {}
        }
        return array;
    }
    
    public static long[] toLongArray(final String s, final String s2) {
        if (s == null) {
            return null;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final long[] array = new long[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            try {
                array[i] = Long.parseLong(stringTokenizer.nextToken());
            }
            catch (Exception ex) {}
        }
        return array;
    }
    
    public static float[] toFloatArray(final String s, final String s2) {
        if (s == null) {
            return null;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final float[] array = new float[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            try {
                array[i] = Float.valueOf(stringTokenizer.nextToken());
            }
            catch (Exception ex) {}
        }
        return array;
    }
    
    public static boolean[] toBooleanArray(final String s, final String s2) {
        if (s == null) {
            return null;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final boolean[] array = new boolean[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = "y".equals(stringTokenizer.nextToken());
        }
        return array;
    }
    
    public static String intArrayToString(final int[] array, final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int n = 0; array != null && n < array.length; ++n) {
            if (sb.length() > 0) {
                sb.append(s);
            }
            sb.append(array[n]);
        }
        return sb.toString();
    }
    
    public static String longArrayToString(final long[] array, final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int n = 0; array != null && n < array.length; ++n) {
            if (sb.length() > 0) {
                sb.append(s);
            }
            sb.append(array[n]);
        }
        return sb.toString();
    }
    
    public static String floatArrayToString(final float[] array, final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int n = 0; array != null && n < array.length; ++n) {
            if (sb.length() > 0) {
                sb.append(s);
            }
            sb.append(array[n]);
        }
        return sb.toString();
    }
    
    public static String booleanArrayToString(final boolean[] array, final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int n = 0; array != null && n < array.length; ++n) {
            if (sb.length() > 0) {
                sb.append(s);
            }
            sb.append(array[n] ? "y" : "n");
        }
        return sb.toString();
    }
    
    public static Object renderVector(final Vector vector, final Class clazz) {
        final int size = vector.size();
        final Object instance = Array.newInstance(clazz, size);
        for (int i = 0; i < size; ++i) {
            Array.set(instance, i, vector.elementAt(i));
        }
        return instance;
    }
    
    public static String replace(String string, final String s, final String s2) {
        final int index = string.indexOf(s);
        if (index < 0) {
            return string;
        }
        string = String.valueOf(string.substring(0, index)) + s2 + string.substring(index + s.length());
        return replace(string, s, s2);
    }
}
