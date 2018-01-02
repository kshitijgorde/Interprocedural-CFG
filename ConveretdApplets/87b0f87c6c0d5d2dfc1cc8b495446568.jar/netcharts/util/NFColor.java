// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.util.Enumeration;
import java.awt.Color;
import java.util.Vector;
import java.util.Hashtable;

public class NFColor
{
    private static Hashtable a;
    private static final int b = 255;
    private static final String c = "_";
    private Vector d;
    protected static final double DARK_FACTOR = 0.3;
    protected static final double BRIGHT_FACTOR = 0.38;
    
    public NFColor() {
        this.d = null;
    }
    
    public static Color get(String substring) {
        int int1 = 255;
        if (substring != null && substring.indexOf("_") != -1) {
            try {
                int1 = Integer.parseInt(substring.substring(substring.indexOf("_") + 1));
            }
            catch (Exception ex) {}
            substring = substring.substring(0, substring.indexOf("_"));
        }
        final Color color = NFColor.a.get(substring.toLowerCase());
        if (color != null) {
            if (NFUtil.getJDKVersion() >= 1.2 && int1 != 255) {
                return NF12Util.getColor(color, int1);
            }
            return color;
        }
        else {
            if (substring.length() != 7 || substring.charAt(0) != 'x') {
                return null;
            }
            int int2;
            try {
                int2 = Integer.parseInt(substring.substring(1), 16);
            }
            catch (Exception ex2) {
                return null;
            }
            if (NFUtil.getJDKVersion() >= 1.2 && int1 != 255) {
                return NF12Util.getColor(int2, int1);
            }
            return new Color(int2);
        }
    }
    
    public static String toString(final Color color) {
        final Enumeration<String> keys = NFColor.a.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            if (((Color)NFColor.a.get(s)).equals(color)) {
                return s;
            }
        }
        String s2 = NFUtil.sprintf("x%06x", new Integer(color.getRGB() & 0xFFFFFF));
        if (NFUtil.getJDKVersion() >= 1.2) {
            final int alpha = NF12Util.getAlpha(color);
            if (alpha != 255) {
                s2 = s2 + "_" + alpha;
            }
        }
        return s2;
    }
    
    public Color getDefaultColor(int n) {
        if (this.d != null && this.d.size() > 0) {
            n %= this.d.size();
            return this.d.elementAt(n);
        }
        n %= 10;
        switch (n) {
            case 0: {
                return Color.blue;
            }
            case 1: {
                return Color.red;
            }
            case 2: {
                return Color.green;
            }
            case 3: {
                return Color.yellow;
            }
            case 4: {
                return Color.cyan;
            }
            case 5: {
                return Color.blue.darker();
            }
            case 6: {
                return Color.red.darker();
            }
            case 7: {
                return Color.green.darker();
            }
            case 8: {
                return Color.yellow.darker();
            }
            case 9: {
                return Color.cyan.darker();
            }
            default: {
                return Color.white;
            }
        }
    }
    
    public void setDefaultTable(final Vector d) {
        this.d = d;
    }
    
    public static Color brighter(final Color color) {
        return brighter(color, 0.38);
    }
    
    public static Color brighter(final Color color, final double n) {
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        final int min = Math.min((int)(red + (255 - red) * n), 255);
        final int min2 = Math.min((int)(green + (255 - green) * n), 255);
        final int min3 = Math.min((int)(blue + (255 - blue) * n), 255);
        if (NFUtil.getJDKVersion() >= 1.2) {
            return NF12Util.getColor(min, min2, min3, NF12Util.getAlpha(color));
        }
        return new Color(min, min2, min3);
    }
    
    public static Color darker(final Color color) {
        return darker(color, 0.3);
    }
    
    public static Color darker(final Color color, final double n) {
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        final int max = Math.max((int)(red - red * n), 0);
        final int max2 = Math.max((int)(green - green * n), 0);
        final int max3 = Math.max((int)(blue - blue * n), 0);
        if (NFUtil.getJDKVersion() >= 1.2) {
            return NF12Util.getColor(max, max2, max3, NF12Util.getAlpha(color));
        }
        return new Color(max, max2, max3);
    }
    
    static {
        (NFColor.a = new Hashtable(200)).put("black", Color.black);
        NFColor.a.put("blue", Color.blue);
        NFColor.a.put("cyan", Color.cyan);
        NFColor.a.put("gray", Color.gray);
        NFColor.a.put("grey", Color.gray);
        NFColor.a.put("green", Color.green);
        NFColor.a.put("magenta", Color.magenta);
        NFColor.a.put("orange", Color.orange);
        NFColor.a.put("pink", Color.pink);
        NFColor.a.put("red", Color.red);
        NFColor.a.put("white", Color.white);
        NFColor.a.put("yellow", Color.yellow);
        NFColor.a.put("lightblue", Color.blue.brighter());
        NFColor.a.put("lightcyan", Color.cyan.brighter());
        NFColor.a.put("lightgray", Color.gray.brighter());
        NFColor.a.put("lightgrey", Color.gray.brighter());
        NFColor.a.put("lightgreen", Color.green.brighter());
        NFColor.a.put("lightmagenta", Color.magenta.brighter());
        NFColor.a.put("lightorange", Color.orange.brighter());
        NFColor.a.put("lightpink", Color.pink.brighter());
        NFColor.a.put("lightred", Color.red.brighter());
        NFColor.a.put("lightyellow", Color.yellow.brighter());
        NFColor.a.put("darkblue", Color.blue.darker());
        NFColor.a.put("darkcyan", Color.cyan.darker());
        NFColor.a.put("darkgray", Color.gray.darker());
        NFColor.a.put("darkgrey", Color.gray.darker());
        NFColor.a.put("darkgreen", Color.green.darker());
        NFColor.a.put("darkmagenta", Color.magenta.darker());
        NFColor.a.put("darkorange", Color.orange.darker());
        NFColor.a.put("darkpink", Color.pink.darker());
        NFColor.a.put("darkred", Color.red.darker());
        NFColor.a.put("darkyellow", Color.yellow.darker());
        NFColor.a.put("aliceblue", new Color(240, 248, 255));
        NFColor.a.put("antiquewhite", new Color(250, 235, 215));
        NFColor.a.put("aqua", new Color(0, 255, 255));
        NFColor.a.put("aquamarine", new Color(127, 255, 212));
        NFColor.a.put("azure", new Color(240, 255, 255));
        NFColor.a.put("beige", new Color(245, 245, 220));
        NFColor.a.put("bisque", new Color(255, 228, 196));
        NFColor.a.put("black", new Color(0, 0, 0));
        NFColor.a.put("blanchedalmond", new Color(255, 235, 205));
        NFColor.a.put("blue", new Color(0, 0, 255));
        NFColor.a.put("blueviolet", new Color(138, 43, 226));
        NFColor.a.put("brown", new Color(165, 42, 42));
        NFColor.a.put("burlywood", new Color(222, 184, 135));
        NFColor.a.put("cadetblue", new Color(95, 158, 160));
        NFColor.a.put("chartreuse", new Color(127, 255, 0));
        NFColor.a.put("chocolate", new Color(210, 105, 30));
        NFColor.a.put("coral", new Color(255, 127, 80));
        NFColor.a.put("cornflowerblue", new Color(100, 149, 237));
        NFColor.a.put("cornsilk", new Color(255, 248, 220));
        NFColor.a.put("crimson", new Color(220, 20, 60));
        NFColor.a.put("cyan", new Color(0, 255, 255));
        NFColor.a.put("darkblue", new Color(0, 0, 139));
        NFColor.a.put("darkcyan", new Color(0, 139, 139));
        NFColor.a.put("darkgoldenrod", new Color(184, 134, 11));
        NFColor.a.put("darkgray", new Color(169, 169, 169));
        NFColor.a.put("darkgrey", new Color(169, 169, 169));
        NFColor.a.put("darkgreen", new Color(0, 100, 0));
        NFColor.a.put("darkkhaki", new Color(189, 183, 107));
        NFColor.a.put("darkmagenta", new Color(139, 0, 139));
        NFColor.a.put("darkolivegreen", new Color(85, 107, 47));
        NFColor.a.put("darkorange", new Color(255, 140, 0));
        NFColor.a.put("darkorchid", new Color(153, 50, 204));
        NFColor.a.put("darkred", new Color(139, 0, 0));
        NFColor.a.put("darksalmon", new Color(233, 150, 122));
        NFColor.a.put("darkseagreen", new Color(143, 188, 143));
        NFColor.a.put("darkslateblue", new Color(72, 61, 139));
        NFColor.a.put("darkslategray", new Color(47, 79, 79));
        NFColor.a.put("darkslategrey", new Color(47, 79, 79));
        NFColor.a.put("darkturquoise", new Color(0, 206, 209));
        NFColor.a.put("darkviolet", new Color(148, 0, 211));
        NFColor.a.put("deeppink", new Color(255, 20, 147));
        NFColor.a.put("deepskyblue", new Color(0, 191, 255));
        NFColor.a.put("dimgray", new Color(105, 105, 105));
        NFColor.a.put("dimgrey", new Color(105, 105, 105));
        NFColor.a.put("dodgerblue", new Color(30, 144, 255));
        NFColor.a.put("firebrick", new Color(178, 34, 34));
        NFColor.a.put("floralwhite", new Color(255, 250, 240));
        NFColor.a.put("forestgreen", new Color(34, 139, 34));
        NFColor.a.put("fuchsia", new Color(255, 0, 255));
        NFColor.a.put("gainsboro", new Color(220, 220, 220));
        NFColor.a.put("ghostwhite", new Color(248, 248, 255));
        NFColor.a.put("gold", new Color(255, 215, 0));
        NFColor.a.put("goldenrod", new Color(218, 165, 32));
        NFColor.a.put("gray", new Color(128, 128, 128));
        NFColor.a.put("grey", new Color(128, 128, 128));
        NFColor.a.put("green", new Color(0, 128, 0));
        NFColor.a.put("greenyellow", new Color(173, 255, 47));
        NFColor.a.put("honeydew", new Color(240, 255, 240));
        NFColor.a.put("hotpink", new Color(255, 105, 180));
        NFColor.a.put("indianred", new Color(205, 92, 92));
        NFColor.a.put("indigo", new Color(75, 0, 130));
        NFColor.a.put("ivory", new Color(255, 255, 240));
        NFColor.a.put("khaki", new Color(240, 230, 140));
        NFColor.a.put("lavender", new Color(230, 230, 250));
        NFColor.a.put("lavenderblush", new Color(255, 240, 245));
        NFColor.a.put("lawngreen", new Color(124, 252, 0));
        NFColor.a.put("lemonchiffon", new Color(255, 250, 205));
        NFColor.a.put("lightblue", new Color(173, 216, 230));
        NFColor.a.put("lightcoral", new Color(240, 128, 128));
        NFColor.a.put("lightcyan", new Color(224, 255, 255));
        NFColor.a.put("lightgoldenrodyellow", new Color(250, 250, 210));
        NFColor.a.put("lightgreen", new Color(144, 238, 144));
        NFColor.a.put("lightgrey", new Color(211, 211, 211));
        NFColor.a.put("lightpink", new Color(255, 182, 193));
        NFColor.a.put("lightsalmon", new Color(255, 160, 122));
        NFColor.a.put("lightseagreen", new Color(32, 178, 170));
        NFColor.a.put("lightskyblue", new Color(135, 206, 250));
        NFColor.a.put("lightslategray", new Color(119, 136, 153));
        NFColor.a.put("lightslategrey", new Color(119, 136, 153));
        NFColor.a.put("lightsteelblue", new Color(176, 196, 222));
        NFColor.a.put("lightyellow", new Color(255, 255, 224));
        NFColor.a.put("lime", new Color(0, 255, 0));
        NFColor.a.put("limegreen", new Color(50, 205, 50));
        NFColor.a.put("linen", new Color(250, 240, 230));
        NFColor.a.put("magenta", new Color(255, 0, 255));
        NFColor.a.put("maroon", new Color(128, 0, 0));
        NFColor.a.put("mediumaquamarine", new Color(102, 205, 170));
        NFColor.a.put("mediumblue", new Color(0, 0, 205));
        NFColor.a.put("mediumorchid", new Color(186, 85, 211));
        NFColor.a.put("mediumpurple", new Color(147, 112, 219));
        NFColor.a.put("mediumseagreen", new Color(60, 179, 113));
        NFColor.a.put("mediumslateblue", new Color(123, 104, 238));
        NFColor.a.put("mediumspringgreen", new Color(0, 250, 154));
        NFColor.a.put("mediumturquoise", new Color(72, 209, 204));
        NFColor.a.put("mediumvioletred", new Color(199, 21, 133));
        NFColor.a.put("midnightblue", new Color(25, 25, 112));
        NFColor.a.put("mintcream", new Color(245, 255, 250));
        NFColor.a.put("mistyrose", new Color(255, 228, 225));
        NFColor.a.put("moccasin", new Color(255, 228, 181));
        NFColor.a.put("navajowhite", new Color(255, 222, 173));
        NFColor.a.put("navy", new Color(0, 0, 128));
        NFColor.a.put("oldlace", new Color(253, 245, 230));
        NFColor.a.put("olive", new Color(128, 128, 0));
        NFColor.a.put("olivedrab", new Color(107, 142, 35));
        NFColor.a.put("orange", new Color(255, 165, 0));
        NFColor.a.put("orangered", new Color(255, 69, 0));
        NFColor.a.put("orchid", new Color(218, 112, 214));
        NFColor.a.put("palegoldenrod", new Color(238, 232, 170));
        NFColor.a.put("palegreen", new Color(152, 251, 152));
        NFColor.a.put("paleturquoise", new Color(175, 238, 238));
        NFColor.a.put("palevioletred", new Color(219, 112, 147));
        NFColor.a.put("papayawhip", new Color(255, 239, 213));
        NFColor.a.put("peachpuff", new Color(255, 218, 185));
        NFColor.a.put("peru", new Color(205, 133, 63));
        NFColor.a.put("pink", new Color(255, 192, 203));
        NFColor.a.put("plum", new Color(221, 160, 221));
        NFColor.a.put("powderblue", new Color(176, 224, 230));
        NFColor.a.put("purple", new Color(128, 0, 128));
        NFColor.a.put("red", new Color(255, 0, 0));
        NFColor.a.put("rosybrown", new Color(188, 143, 143));
        NFColor.a.put("royalblue", new Color(65, 105, 225));
        NFColor.a.put("saddlebrown", new Color(139, 69, 19));
        NFColor.a.put("salmon", new Color(250, 128, 114));
        NFColor.a.put("sandybrown", new Color(244, 164, 96));
        NFColor.a.put("seagreen", new Color(46, 139, 87));
        NFColor.a.put("seashell", new Color(255, 245, 238));
        NFColor.a.put("sienna", new Color(160, 82, 45));
        NFColor.a.put("silver", new Color(192, 192, 192));
        NFColor.a.put("skyblue", new Color(135, 206, 235));
        NFColor.a.put("slateblue", new Color(106, 90, 205));
        NFColor.a.put("slategray", new Color(112, 128, 144));
        NFColor.a.put("slategrey", new Color(112, 128, 144));
        NFColor.a.put("snow", new Color(255, 250, 250));
        NFColor.a.put("springgreen", new Color(0, 255, 127));
        NFColor.a.put("steelblue", new Color(70, 130, 180));
        NFColor.a.put("tan", new Color(210, 180, 140));
        NFColor.a.put("teal", new Color(0, 128, 128));
        NFColor.a.put("thistle", new Color(216, 191, 216));
        NFColor.a.put("tomato", new Color(255, 99, 71));
        NFColor.a.put("turquoise", new Color(64, 224, 208));
        NFColor.a.put("violet", new Color(238, 130, 238));
        NFColor.a.put("wheat", new Color(245, 222, 179));
        NFColor.a.put("white", new Color(255, 255, 255));
        NFColor.a.put("whitesmoke", new Color(245, 245, 245));
        NFColor.a.put("yellow", new Color(255, 255, 0));
        NFColor.a.put("yellowgreen", new Color(154, 205, 50));
    }
}
