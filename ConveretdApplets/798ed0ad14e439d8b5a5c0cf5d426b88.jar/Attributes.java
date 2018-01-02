import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Hashtable;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class Attributes
{
    public static boolean withMathMLAttributes;
    public int type;
    public Font font;
    public int fontMask;
    public Color color;
    public int estil;
    public int quins_estil;
    public static final String[] textAlign;
    public static final String[] halign;
    public static final String[] mathml_valign;
    public static final String[] css_valign;
    public static final String[] styles;
    public static final String[] visibility;
    public static final String[] display;
    public static final String[] imageVAlign;
    static Class EM;
    static Class XMLTag;
    static Class addElement;
    static Class append;
    
    public Attributes() {
        this.type = -1;
    }
    
    public Attributes(final Attributes attributes) {
        this.type = -1;
        this.join(attributes);
    }
    
    public Attributes(final int n, final boolean b) {
        this.type = -1;
        this.quins_estil = n;
        if (b) {
            this.estil = n;
        }
    }
    
    public Attributes(final Font font, final int fontMask) {
        this.type = -1;
        this.font = font;
        if (font != null) {
            this.fontMask = fontMask;
        }
    }
    
    public Attributes(final Color color) {
        this.type = -1;
        this.color = color;
    }
    
    public Attributes(final int estil, final int quins_estil) {
        this.type = -1;
        this.estil = estil;
        this.quins_estil = quins_estil;
    }
    
    public Attributes(final Font font, final int fontMask, final Color color, final int estil, final int quins_estil) {
        this.type = -1;
        this.font = font;
        this.fontMask = fontMask;
        this.color = color;
        this.estil = estil;
        this.quins_estil = quins_estil;
    }
    
    public final boolean modifiesColor(final int n) {
        return this.type == -1 || this.type == n;
    }
    
    public final void setType(final int type) {
        this.type = type;
    }
    
    public final void setFontName(final String s) {
        this.setFont(s, 0, 0, 4);
    }
    
    public final void setBold(final boolean b) {
        this.setFont(null, b ? 1 : 0, 0, 1);
    }
    
    public final void setItalic(final boolean b) {
        this.setFont(null, b ? 2 : 0, 0, 2);
    }
    
    public final void setSize(final int n) {
        this.setFont(null, 0, n, 8);
    }
    
    public final void setSize(String substring) {
        substring = substring.substring(0, substring.indexOf("px"));
        this.setSize(Integer.parseInt(substring));
    }
    
    public final void setFont(final String s, final int n, final int n2, final int n3) {
        final Font joinFont = joinFont(this.font, this.fontMask, s, n, n2, n3);
        if (this.font == null || !this.font.equals(joinFont)) {
            this.font = joinFont;
        }
        this.fontMask |= n3;
    }
    
    public final void fixFontStyles(final int n) {
        if ((this.fontMask & n) != 0x0) {
            this.fontMask |= n;
        }
    }
    
    public static final Attributes obteAtributs(final BoxComponent boxComponent, final BoxPosition boxPosition) {
        return EM(boxComponent, boxPosition);
    }
    
    private static final Attributes EM(final BoxComponent boxComponent, final BoxPosition boxPosition) {
        final FormulaEditor formulaEditor = (FormulaEditor)boxComponent;
        BoxPosition z = boxPosition;
        if (formulaEditor.B != null && !formulaEditor.B.deldret) {
            z = formulaEditor.Z;
        }
        if (!(z.c instanceof TokensBox)) {
            return obteAtributs(z.c);
        }
        if (z.x < 0 || z.x > z.c.nfills) {
            return obteAtributs(z.c.getParentBox());
        }
        int x = z.x - 1;
        if (x < 0 || z.c.fill[x].areAttributesInterior()) {
            x = z.x;
        }
        if (x >= z.c.nfills || z.c.fill[x].areAttributesInterior()) {
            return obteAtributs(z.c.getParentBox());
        }
        return obteAtributs(z.c.fill[x]);
    }
    
    public final void join(final Attributes attributes) {
        this.type = attributes.type;
        if (attributes.font != null) {
            this.font = joinFont(this.font, this.fontMask, attributes.font, attributes.fontMask);
            this.fontMask |= attributes.fontMask;
        }
        if (attributes.color != null) {
            this.color = attributes.color;
        }
        this.quins_estil |= attributes.quins_estil;
        this.estil = AbstractBox.copiaEstil(this.estil, attributes.estil, attributes.quins_estil);
    }
    
    public static final Attributes join(final Attributes attributes, final Attributes attributes2) {
        final Attributes attributes3 = new Attributes(attributes);
        attributes3.join(attributes2);
        return attributes3;
    }
    
    public static final Attributes restrict(final Attributes attributes, final Attributes attributes2, final boolean b) {
        final Attributes attributes3 = new Attributes(attributes);
        attributes3.restrict(attributes2, b);
        return attributes3;
    }
    
    public final void restrict(final Attributes attributes, final boolean b) {
        if (b) {
            if (this.fontMask != 0) {}
            this.quins_estil &= ~attributes.quins_estil;
            this.fontMask &= ~attributes.fontMask;
        }
        else {
            this.quins_estil &= attributes.quins_estil;
            this.fontMask &= attributes.fontMask;
        }
        this.normalizeMask();
    }
    
    public final void normalizeMask() {
        this.estil &= this.quins_estil;
        if ((this.fontMask & 0xF) == 0x0) {
            this.font = null;
        }
    }
    
    public static final Attributes complement(final Attributes attributes, final Attributes attributes2) {
        final Attributes attributes3 = new Attributes();
        if (attributes2.color == null) {
            attributes3.color = attributes.color;
        }
        else if (!attributes2.color.equals(attributes.color)) {
            attributes3.color = attributes.color;
        }
        attributes3.quins_estil = complement(attributes.estil, attributes.quins_estil, attributes2.estil, attributes2.quins_estil);
        attributes3.estil = (attributes.estil & attributes3.quins_estil);
        if (attributes.font != null) {
            if (attributes2.font != null) {
                attributes3.fontMask = complement(compareFont(attributes.font, attributes2.font), attributes.fontMask, 15, attributes2.fontMask);
                attributes3.font = attributes.font;
            }
            else {
                attributes3.font = attributes.font;
                attributes3.fontMask = attributes.fontMask;
            }
        }
        return attributes3;
    }
    
    private static final int complement(final int n, final int n2, final int n3, final int n4) {
        return n2 & ((n2 ^ n4) | (n ^ n3));
    }
    
    private static final boolean equals(final int n, final int n2, final int n3, final int n4) {
        return n2 == n4 && (n & n2) == (n3 & n4);
    }
    
    private static final int compareFont(final Font font, final Font font2) {
        return ((font.getName() == font2.getName()) ? 4 : 0) | ((font.getSize() == font2.getSize()) ? 8 : 0) | (((font.getStyle() & 0x1) == (font2.getStyle() & 0x1)) ? 1 : 0) | (((font.getStyle() & 0x2) == (font2.getStyle() & 0x2)) ? 2 : 0);
    }
    
    public static final Font joinFont(final Font font, final int n, final String s, final int n2, final int n3, final int n4) {
        String name;
        int size;
        int n5;
        int n6;
        if (font == null) {
            name = "SansSerif";
            size = 16;
            n5 = 0;
            n6 = 0;
        }
        else {
            name = font.getName();
            size = font.getSize();
            n5 = (font.getStyle() & 0x2);
            n6 = (font.getStyle() & 0x1);
        }
        if (chooseSrc(n, n4, 4)) {
            name = s;
        }
        if (chooseSrc(n, n4, 2)) {
            n5 = (n2 & 0x2);
        }
        if (chooseSrc(n, n4, 1)) {
            n6 = (n2 & 0x1);
        }
        if (chooseSrc(n, n4, 8)) {
            size = n3;
        }
        return new Font(name, n5 | n6, size);
    }
    
    public static final boolean chooseSrc(final int n, final int n2, final int n3) {
        return (n2 & n3) != 0x0;
    }
    
    public static final Font joinFont(final Font font, final int n, final Font font2, final int n2) {
        if (font2 == null) {
            return font;
        }
        if (font == null) {
            return font2;
        }
        final Font joinFont = joinFont(font, n, font2.getName(), font2.getStyle(), font2.getSize(), n2);
        if (!font.equals(joinFont)) {
            return joinFont;
        }
        return font;
    }
    
    public static final Attributes obteAtributs(final AbstractBox abstractBox) {
        if (abstractBox == null) {
            return new Attributes(null, 0, null, 0, 0);
        }
        return abstractBox.getAttributes();
    }
    
    public static final boolean equals(final Attributes attributes, final Attributes attributes2) {
        if (!equals(attributes.estil, attributes.quins_estil, attributes2.estil, attributes2.quins_estil)) {
            return false;
        }
        if (attributes.color == null) {
            if (attributes2.color != null) {
                return false;
            }
        }
        else {
            if (attributes2.color == null) {
                return false;
            }
            if (!attributes.color.equals(attributes2.color)) {
                return false;
            }
        }
        return attributes.fontMask == attributes2.fontMask && (attributes.fontMask == 0 || (compareFont(attributes.font, attributes2.font) & attributes.fontMask) == attributes.fontMask);
    }
    
    public static final boolean iguals(final AbstractBox abstractBox, final AbstractBox abstractBox2) {
        return equals(abstractBox.getAttributes(), abstractBox2.getAttributes());
    }
    
    public static final void formatXML1(final Object o, final int n, final int n2, final String s) {
        final XMLOutput xmlOutput = (XMLOutput)o;
        if (!s.equals("mstyle") && !s.equals("math") && (n2 & 0x100) != 0x0) {
            xmlOutput.XMLTag(0, "mstyle");
            xmlOutput.attributeXML("displaystyle", Boolean.toString((n & 0x100) == 0x0));
        }
        if ((n & n2 & 0x1) != 0x0) {
            xmlOutput.XMLTag(0, "maction");
            xmlOutput.attributeXML("actiontype", "comment");
        }
        if ((n & n2 & 0x4) != 0x0) {
            xmlOutput.XMLTag(0, "maction");
            xmlOutput.attributeXML("actiontype", "no-visible");
        }
    }
    
    public static final void formatXML1end(final Object o, final int n, final int n2, final String s) {
        final XMLOutput xmlOutput = (XMLOutput)o;
        if ((n & n2 & 0x4) != 0x0) {
            xmlOutput.XMLTag(1, "maction");
        }
        if ((n & n2 & 0x1) != 0x0) {
            xmlOutput.XMLTag(1, "maction");
        }
        if (!s.equals("mstyle") && !s.equals("math") && (n2 & 0x100) != 0x0) {
            xmlOutput.XMLTag(1, "mstyle");
        }
    }
    
    public static final void formatXML2(final Object o, final Attributes attributes, final Font font, Hashtable hashtable, final String s) {
        Font font2 = attributes.font;
        int fontMask = attributes.fontMask;
        if (fontMask != 0) {
            fontMask = 15;
            font2 = font;
        }
        if (Attributes.withMathMLAttributes) {
            if (attributes.color != null) {
                ((XMLOutput)o).attributeXML("mathcolor", "#" + Integer.toHexString(attributes.color.getRGB() & 0xFFFFFF));
            }
            if (font2 != null) {
                if ((fontMask & 0x8) != 0x0) {
                    ((XMLOutput)o).attributeXML("mathsize", Integer.toString(font2.getSize()) + "px");
                }
                final String fontToMathVariant = fontToMathVariant(font2, fontMask, font);
                if (fontToMathVariant != null) {
                    ((XMLOutput)o).attributeXML("mathvariant", fontToMathVariant);
                }
            }
        }
        else {
            if (attributes.color != null) {
                if (hashtable != null) {
                    hashtable = (Hashtable<String, String>)hashtable.clone();
                }
                else {
                    hashtable = new Hashtable<String, String>();
                }
                hashtable.put("color", GraphicsUtils.colorToString(attributes.color));
            }
            if (font2 != null) {
                if (hashtable != null) {
                    hashtable = (Hashtable<String, String>)hashtable.clone();
                }
                else {
                    hashtable = new Hashtable<String, String>();
                }
                if ((fontMask & 0x4) != 0x0) {
                    hashtable.put("font-family", font2.getName());
                }
                if ((fontMask & 0x8) != 0x0) {
                    hashtable.put("font-size", font2.getSize() + "px");
                }
                if ((fontMask & 0x1) != 0x0 && font2.isBold()) {
                    hashtable.put("font-weight", "bold");
                }
                if ((fontMask & 0x2) != 0x0 && font2.isItalic()) {
                    hashtable.put("font-style", "italic");
                }
            }
        }
        if (s.equals("mstyle") && (attributes.quins_estil & 0x100) != 0x0) {
            if ((attributes.estil & 0x100) != 0x0) {
                ((XMLOutput)o).attributeXML("displaystyle", "false");
            }
            else {
                ((XMLOutput)o).attributeXML("displaystyle", "true");
            }
        }
        if (s.equals("math") && (attributes.quins_estil & 0x100) != 0x0) {
            if ((attributes.estil & 0x100) != 0x0) {
                ((XMLOutput)o).attributeXML("display", "inline");
            }
            else {
                ((XMLOutput)o).attributeXML("display", "block");
            }
        }
        if (hashtable != null && hashtable.size() > 0) {
            ((XMLOutput)o).attributeXML("style", styles2string(hashtable));
        }
    }
    
    public static final void propertiesXML(final Object o, final Hashtable hashtable) {
        if (hashtable != null && !hashtable.isEmpty()) {
            final Enumeration<V> elements = (Enumeration<V>)hashtable.elements();
            final Enumeration<Object> keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                ((XMLOutput)o).attributeXML(keys.nextElement().toString(), elements.nextElement().toString());
            }
        }
    }
    
    private static void exchange(final Hashtable hashtable, final String s, final int n) {
        if (n == 4096) {
            hashtable.remove(s);
        }
    }
    
    public static final void exchangePropertyDialog(final Hashtable hashtable, final String s, final Object o, final Object o2, final boolean b) {
        final Vector<Object> vector = hashtable.get("names");
        final Vector<Object> vector2 = hashtable.get("values");
        final Vector<Object> vector3 = hashtable.get("types");
        final Vector<Object> vector4 = hashtable.get("editables");
        final String s2 = (String)hashtable.get("currentGroup");
        if (s2 != null) {
            for (int i = 0; i < vector.size(); ++i) {
                if (vector3.elementAt(i).equals("label") && vector.elementAt(i).equals(s2)) {
                    int n;
                    for (n = i + 1; n < vector.size() && !vector3.elementAt(n).equals("label"); ++n) {}
                    if (n < vector.size()) {
                        if (o2.equals("label")) {
                            return;
                        }
                        vector.insertElementAt(s, n);
                        vector2.insertElementAt(o, n);
                        vector3.insertElementAt(o2, n);
                        vector4.insertElementAt(b, n);
                        return;
                    }
                }
            }
        }
        vector.addElement(s);
        vector2.addElement(o);
        vector3.addElement(o2);
        vector4.addElement(b);
    }
    
    public static final boolean exchangeBool(final Hashtable hashtable, final String s, final int n, final boolean b, final boolean b2) {
        exchange(hashtable, s, n);
        if (n != 0) {
            if (n == 1) {
                if (b != b2) {
                    hashtable.put(s, "" + b);
                }
            }
            else if (n == 4098) {
                exchangePropertyDialog(hashtable, s, b, (Attributes.EM == null) ? (Attributes.EM = XMLTag("java.lang.Boolean")) : Attributes.EM, true);
            }
            return b;
        }
        final String s2 = hashtable.get(s);
        if (s2 != null) {
            return Boolean.valueOf(s2);
        }
        return b2;
    }
    
    public static final void exchangeTitle(final Hashtable hashtable, final String s, final int n) {
        if (n == 4098) {
            hashtable.put("currentGroup", s);
            exchangePropertyDialog(hashtable, s, "", "label", false);
        }
    }
    
    public static final float exchangeFloat(final Hashtable hashtable, final String s, final int n, final float n2, final float n3) {
        exchange(hashtable, s, n);
        if (n != 0) {
            if (n == 1 && n2 != n3) {
                hashtable.put(s, "" + Math.round(n2 * 100.0f) / 100.0f);
            }
            return n2;
        }
        final String s2 = hashtable.get(s);
        if (s2 != null) {
            return Float.valueOf(s2);
        }
        return n3;
    }
    
    public static final double exchangeDouble(final Hashtable hashtable, final String s, final int n, final double n2, final double n3) {
        exchange(hashtable, s, n);
        if (n != 0) {
            if (n == 1 && n2 != n3) {
                hashtable.put(s, "" + n2);
            }
            return n2;
        }
        final String s2 = hashtable.get(s);
        if (s2 != null) {
            return Double.valueOf(s2);
        }
        return n3;
    }
    
    public static final float[] exchangeFloatV(final int n, final Hashtable hashtable, final String s, final int n2, final float[] array, final float[] array2) {
        exchange(hashtable, s, n2);
        if (n2 != 0) {
            if (n2 == 1) {
                int n3;
                for (n3 = 0; n3 < n && array[n3] == array2[n3]; ++n3) {}
                if (n3 != n) {
                    String s2 = "";
                    for (int i = 0; i < n; ++i) {
                        if (i > 0) {
                            s2 += ",";
                        }
                        s2 += array[i];
                    }
                    hashtable.put(s, s2);
                }
            }
            return array;
        }
        final String s3 = hashtable.get(s);
        if (s3 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s3, ",");
            final float[] array3 = new float[n];
            int n4 = 0;
            while (stringTokenizer.hasMoreTokens()) {
                array3[n4++] = Float.valueOf(stringTokenizer.nextToken());
            }
            return array3;
        }
        return array2;
    }
    
    public static final Color exchangeColor(final Hashtable hashtable, final String s, final int n, final Color color, final Color color2) {
        exchange(hashtable, s, n);
        if (n != 0) {
            if (n == 1) {
                if (color != color2 && !color.equals(color2)) {
                    hashtable.put(s, GraphicsUtils.colorToString(color));
                }
            }
            else if (n == 4098) {
                exchangePropertyDialog(hashtable, s, color, (Attributes.XMLTag == null) ? (Attributes.XMLTag = XMLTag("java.awt.Color")) : Attributes.XMLTag, true);
            }
            return color;
        }
        final String s2 = hashtable.get(s);
        if (s2 != null) {
            return Color.decode(s2);
        }
        return color2;
    }
    
    public static final int exchangeInt(final Hashtable hashtable, final String s, final int n, final int n2, final int n3) {
        exchange(hashtable, s, n);
        if (n == 0) {
            final String s2 = hashtable.get(s);
            try {
                if (s2 != null) {
                    return Integer.valueOf(s2);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            return n3;
        }
        if (n == 1) {
            if (n2 != n3) {
                hashtable.put(s, "" + n2);
            }
        }
        else if (n == 4098) {
            exchangePropertyDialog(hashtable, s, new Integer(n2), (Attributes.addElement == null) ? (Attributes.addElement = XMLTag("java.lang.Integer")) : Attributes.addElement, true);
        }
        return n2;
    }
    
    public static final int exchangeIntTranslate(final Hashtable hashtable, final String s, final int n, final int n2, final int n3, final String[] array) {
        if (n == 4098) {
            exchangePropertyDialog(hashtable, s, array[n2], array, true);
            return n2;
        }
        final String exchangeString = exchangeString(hashtable, s, n, array[n2], array[n3]);
        if (n == 0) {
            try {
                return Integer.parseInt(exchangeString);
            }
            catch (Throwable t) {}
        }
        return Utils.indexOf(array, exchangeString);
    }
    
    public static final String exchangeString(final Hashtable hashtable, final String s, final int n, String s2, final String s3) {
        exchange(hashtable, s, n);
        if (s2 == null) {
            s2 = "";
        }
        if (n != 0 && n != 4097) {
            if (n == 1) {
                if (!s2.equals(s3)) {
                    hashtable.put(s, s2);
                }
            }
            else if (n == 4098) {
                exchangePropertyDialog(hashtable, s, s2, (Attributes.append == null) ? (Attributes.append = XMLTag("java.lang.String")) : Attributes.append, true);
            }
            return s2;
        }
        final String s4 = hashtable.get(s);
        if (s4 != null) {
            return s4;
        }
        return (n == 0) ? s3 : s2;
    }
    
    public static final void exchangeStyles(final AbstractBox abstractBox, final String s) {
        final Hashtable string2styles = string2styles(s);
        string2styles.remove("color");
        string2styles.remove("font-family");
        string2styles.remove("font-size");
        string2styles.remove("font-weight");
        string2styles.remove("font-style");
        if (string2styles.size() > 0) {
            abstractBox.styles = string2styles;
        }
    }
    
    public static final Hashtable string2styles(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        final Hashtable<String, String> hashtable = new Hashtable<String, String>(33);
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final int index = nextToken.indexOf(":");
            hashtable.put(nextToken.substring(0, index).trim(), nextToken.substring(index + 1).trim());
        }
        return hashtable;
    }
    
    public static final String styles2string(final Hashtable hashtable) {
        final StringBuffer sb = new StringBuffer();
        final Enumeration<String> keys = hashtable.keys();
        final int size = hashtable.size();
        final String[] array = new String[size];
        int n = 0;
        while (keys.hasMoreElements()) {
            array[n++] = keys.nextElement();
        }
        Utils.sort(array);
        for (int i = 0; i < size; ++i) {
            if (i > 0) {
                sb.append(";");
            }
            sb.append(array[i]);
            sb.append(":");
            sb.append(hashtable.get(array[i]));
        }
        return sb.toString();
    }
    
    public static final int getAlign(final AbstractBox abstractBox, final int n) {
        Object o = null;
        if (abstractBox.styles != null) {
            o = abstractBox.styles.get("text-align");
        }
        if (o == null) {
            return n;
        }
        final int index = Utils.indexOf(Attributes.textAlign, o);
        return (index < 0) ? 0 : index;
    }
    
    public static final String getStyle(final AbstractBox abstractBox, String s) {
        if (abstractBox.styles == null) {
            return "";
        }
        s = abstractBox.styles.get(s);
        if (s != null) {
            return s;
        }
        return "";
    }
    
    public static final int getStyleDim(final AbstractBox abstractBox, final String s, final int n, final int n2) {
        if (abstractBox.styles == null) {
            return n;
        }
        final String s2 = abstractBox.styles.get(s);
        if (s2 == null || s2.length() == 0) {
            return n;
        }
        return strToDim(abstractBox, s2, n2);
    }
    
    public static final int strToDim(final AbstractBox abstractBox, final String s, final int n) {
        try {
            try {
                return Length.toUserUnit(s, abstractBox.EM, n);
            }
            catch (IllegalArgumentException ex) {
                return abstractBox.em(new Float(s));
            }
        }
        catch (Exception ex2) {
            System.err.println("Invalid value: '" + s + '\'');
            return 0;
        }
    }
    
    public static final String fontToMathVariant(final Font font, final int n, final Font font2) {
        if ((n & 0x7) == 0x0) {
            return null;
        }
        final int n2 = (font.getStyle() & n) | (~n & font2.getStyle());
        final String name = font.getName();
        int n3 = 0;
        if ((n & 0x4) != 0x0) {
            if (name.equalsIgnoreCase("serif")) {
                n3 = 0;
            }
            else if (name.equalsIgnoreCase("sansserif")) {
                n3 = 1;
            }
            else if (name.equalsIgnoreCase("monospace")) {
                n3 = 2;
            }
        }
        String s = null;
        switch (n2 + n3 * 10) {
            case 2: {
                s = "italic";
                break;
            }
            case 1: {
                s = "bold";
                break;
            }
            case 3: {
                s = "bold-italic";
                break;
            }
            case 0: {
                s = "normal";
                break;
            }
            case 12: {
                s = "sans-serif-italic";
                break;
            }
            case 11: {
                s = "bold-sans-serif";
                break;
            }
            case 13: {
                s = "sans-serif-bold-italic";
                break;
            }
            case 10: {
                s = "sans-serif";
                break;
            }
            case 20: {
                s = "monospace";
                break;
            }
            default: {
                s = null;
                break;
            }
        }
        return s;
    }
    
    public final void loadMathvariant(final String s, final boolean b) {
        if (b) {
            this.setFontName("Serif");
        }
        if (s.indexOf("sans-serif") >= 0) {
            this.setFontName("SansSerif");
            this.setBold(false);
            this.setItalic(false);
        }
        if (s.indexOf("monospace") >= 0) {
            this.setFontName("Monospace");
            this.setBold(false);
            this.setItalic(false);
        }
        if (s.indexOf("italic") >= 0) {
            this.setItalic(true);
        }
        if (s.indexOf("bold") >= 0) {
            this.setBold(true);
        }
        if (s.indexOf("normal") >= 0) {
            this.setBold(false);
            this.setItalic(false);
        }
        this.fixFontStyles(3);
    }
    
    private static final Class XMLTag(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Attributes.withMathMLAttributes = false;
        textAlign = new String[] { "left", "center", "right", "justify" };
        halign = new String[] { "left", "center", "right" };
        mathml_valign = new String[] { "axis", "top", "center", "bottom", "baseline" };
        css_valign = new String[] { "", "top", "middle", "bottom", "baseline" };
        styles = new String[] { "font-family", "font-size", "font-weight", "font-style", "min-width", "min-height", "width", "height", "visible", "text-align", "background-color", "display", "visibility" };
        visibility = new String[] { "visible", "hidden" };
        display = new String[] { "inline", "none" };
        imageVAlign = new String[] { "center", "baseline" };
    }
}
