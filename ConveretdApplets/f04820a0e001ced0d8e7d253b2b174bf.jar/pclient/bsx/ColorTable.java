// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsx;

import java.awt.Color;
import java.util.Hashtable;

public class ColorTable
{
    private static Hashtable colors;
    
    public static Hashtable getColors() {
        if (ColorTable.colors == null) {
            getColor("black");
        }
        return ColorTable.colors;
    }
    
    public static Color getColor(final String s) {
        if (s == null) {
            return null;
        }
        if (ColorTable.colors == null) {
            (ColorTable.colors = new Hashtable(650)).put("aliceblue", "f0f8ff");
            ColorTable.colors.put("antiquewhite", "faebd7");
            ColorTable.colors.put("aquamarine", "7fffd4");
            ColorTable.colors.put("azure", "f0ffff");
            ColorTable.colors.put("beige", "f5f5dc");
            ColorTable.colors.put("bisque", "ffe4c4");
            ColorTable.colors.put("black", "000000");
            ColorTable.colors.put("blanchedalmond", "ffebcd");
            ColorTable.colors.put("blue", "0000ff");
            ColorTable.colors.put("blueviolet", "8a2be2");
            ColorTable.colors.put("brown", "a52a2a");
            ColorTable.colors.put("burlywood", "deb887");
            ColorTable.colors.put("cadetblue", "5f9ea0");
            ColorTable.colors.put("chartreuse", "7fff00");
            ColorTable.colors.put("chocolate", "d2691e");
            ColorTable.colors.put("coral", "ff7f50");
            ColorTable.colors.put("cornflowerblue", "6495ed");
            ColorTable.colors.put("cornsilk", "fff8dc");
            ColorTable.colors.put("cyan", "00ffff");
            ColorTable.colors.put("darkgoldenrod", "b8860b");
            ColorTable.colors.put("darkgreen", "006400");
            ColorTable.colors.put("darkkhaki", "bdb76b");
            ColorTable.colors.put("darkolivegreen", "556b2f");
            ColorTable.colors.put("darkorange", "ff8c00");
            ColorTable.colors.put("darkorchid", "9932cc");
            ColorTable.colors.put("darksalmon", "e9967a");
            ColorTable.colors.put("darkseagreen", "8fbc8f");
            ColorTable.colors.put("darkslateblue", "483d8b");
            ColorTable.colors.put("darkslategray", "2f4f4f");
            ColorTable.colors.put("darkslategrey", "2f4f4f");
            ColorTable.colors.put("darkturquoise", "00ced1");
            ColorTable.colors.put("darkviolet", "9400d3");
            ColorTable.colors.put("deeppink", "ff1493");
            ColorTable.colors.put("deepskyblue", "00bfff");
            ColorTable.colors.put("dimgray", "696969");
            ColorTable.colors.put("dimgrey", "696969");
            ColorTable.colors.put("dodgerblue", "1e90ff");
            ColorTable.colors.put("firebrick", "b22222");
            ColorTable.colors.put("floralwhite", "fffaf0");
            ColorTable.colors.put("forestgreen", "228b22");
            ColorTable.colors.put("green", "00ff00");
            ColorTable.colors.put("gainsboro", "dcdcdc");
            ColorTable.colors.put("ghostwhite", "f8f8ff");
            ColorTable.colors.put("gold", "ffd700");
            ColorTable.colors.put("goldenrod", "daa520");
            ColorTable.colors.put("gray", "bebebe");
            ColorTable.colors.put("honeydew", "f0fff0");
            ColorTable.colors.put("hotpink", "ff69b4");
            ColorTable.colors.put("indianred", "cd5c5c");
            ColorTable.colors.put("ivory", "fffff0");
            ColorTable.colors.put("khaki", "f0e68c");
            ColorTable.colors.put("lavender", "e6e6fa");
            ColorTable.colors.put("lavenderblush", "fff0f5");
            ColorTable.colors.put("lawngreen", "7cfc00");
            ColorTable.colors.put("lemonchiffon", "fffacd");
            ColorTable.colors.put("lightblue", "add8e6");
            ColorTable.colors.put("lightcoral", "f08080");
            ColorTable.colors.put("lightcyan", "e0ffff");
            ColorTable.colors.put("lightgoldenrod", "eedd82");
            ColorTable.colors.put("lightgoldenrodyellow", "fafad2");
            ColorTable.colors.put("lightgray", "d3d3d3");
            ColorTable.colors.put("lightgrey", "d3d3d3");
            ColorTable.colors.put("lightpink", "ffb6c1");
            ColorTable.colors.put("lightsalmon", "ffa07a");
            ColorTable.colors.put("lightseagreen", "20b2aa");
            ColorTable.colors.put("lightskyblue", "87cefa");
            ColorTable.colors.put("lightslateblue", "8470ff");
            ColorTable.colors.put("lightslategray", "778899");
            ColorTable.colors.put("lightslategrey", "778899");
            ColorTable.colors.put("lightsteelblue", "b0c4de");
            ColorTable.colors.put("lightyellow", "ffffe0");
            ColorTable.colors.put("limegreen", "32cd32");
            ColorTable.colors.put("linen", "faf0e6");
            ColorTable.colors.put("magenta", "ff00ff");
            ColorTable.colors.put("maroon", "b03060");
            ColorTable.colors.put("mediumaquamarine", "66cdaa");
            ColorTable.colors.put("mediumblue", "0000cd");
            ColorTable.colors.put("mediumorchid", "ba55d3");
            ColorTable.colors.put("mediumpurple", "9370db");
            ColorTable.colors.put("mediumseagreen", "3cb371");
            ColorTable.colors.put("mediumslateblue", "7b68ee");
            ColorTable.colors.put("mediumspringgreen", "00fa9a");
            ColorTable.colors.put("mediumturquoise", "48d1cc");
            ColorTable.colors.put("mediumvioletred", "c71585");
            ColorTable.colors.put("midnightblue", "191970");
            ColorTable.colors.put("mintcream", "f5fffa");
            ColorTable.colors.put("mistyrose", "ffe4e1");
            ColorTable.colors.put("moccasin", "ffe4b5");
            ColorTable.colors.put("navajowhite", "ffdead");
            ColorTable.colors.put("navy", "000080");
            ColorTable.colors.put("navyblue", "000080");
            ColorTable.colors.put("oldlace", "fdf5e6");
            ColorTable.colors.put("olivedrab", "6b8e23");
            ColorTable.colors.put("orange", "ffa500");
            ColorTable.colors.put("orangered", "ff4500");
            ColorTable.colors.put("orchid", "da70d6");
            ColorTable.colors.put("palegoldenrod", "eee8aa");
            ColorTable.colors.put("palegreen", "98fb98");
            ColorTable.colors.put("paleturquoise", "afeeee");
            ColorTable.colors.put("palevioletred", "db7093");
            ColorTable.colors.put("papayawhip", "ffefd5");
            ColorTable.colors.put("peachpuff", "ffdab9");
            ColorTable.colors.put("peru", "cd853f");
            ColorTable.colors.put("pink", "ffc0cb");
            ColorTable.colors.put("plum", "dda0dd");
            ColorTable.colors.put("powderblue", "b0e0e6");
            ColorTable.colors.put("purple", "a020f0");
            ColorTable.colors.put("red", "ff0000");
            ColorTable.colors.put("rosybrown", "bc8f8f");
            ColorTable.colors.put("royalblue", "4169e1");
            ColorTable.colors.put("saddlebrown", "8b4513");
            ColorTable.colors.put("salmon", "fa8072");
            ColorTable.colors.put("sandybrown", "f4a460");
            ColorTable.colors.put("seagreen", "2e8b57");
            ColorTable.colors.put("seashell", "fff5ee");
            ColorTable.colors.put("sienna", "a0522d");
            ColorTable.colors.put("skyblue", "87ceeb");
            ColorTable.colors.put("slateblue", "6a5acd");
            ColorTable.colors.put("slategray", "708090");
            ColorTable.colors.put("slategrey", "708090");
            ColorTable.colors.put("snow", "fffafa");
            ColorTable.colors.put("springgreen", "00ff7f");
            ColorTable.colors.put("steelblue", "4682b4");
            ColorTable.colors.put("tan", "d2b48c");
            ColorTable.colors.put("thistle", "d8bfd8");
            ColorTable.colors.put("tomato", "ff6347");
            ColorTable.colors.put("turquoise", "40e0d0");
            ColorTable.colors.put("violet", "ee82ee");
            ColorTable.colors.put("violetred", "d02090");
            ColorTable.colors.put("wheat", "f5deb3");
            ColorTable.colors.put("white", "ffffff");
            ColorTable.colors.put("whitesmoke", "f5f5f5");
            ColorTable.colors.put("yellow", "ffff00");
            ColorTable.colors.put("yellowgreen", "9acd32");
        }
        try {
            return new Color(Integer.parseInt(s, 16));
        }
        catch (NumberFormatException ex) {
            final String s2 = ColorTable.colors.get(s);
            if (s2 != null) {
                return new Color(Integer.parseInt(s2, 16));
            }
            System.out.println("unknown color: " + s);
            return null;
        }
    }
    
    static {
        ColorTable.colors = null;
    }
}
