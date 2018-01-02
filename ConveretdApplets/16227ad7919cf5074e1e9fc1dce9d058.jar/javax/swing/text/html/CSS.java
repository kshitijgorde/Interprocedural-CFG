// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import java.io.Serializable;
import java.io.ObjectOutputStream;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import java.util.Enumeration;
import javax.swing.text.MutableAttributeSet;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.Font;
import javax.swing.text.AttributeSet;
import java.awt.Color;
import javax.swing.text.StyleContext;
import javax.swing.text.StyleConstants;
import java.util.Hashtable;

public class CSS
{
    private static final Hashtable attributeMap;
    private static final Hashtable valueMap;
    static int[] sizeMap;
    private static final Hashtable htmlAttrToCssAttrMap;
    private static final Hashtable styleConstantToCssMap;
    private static final Hashtable htmlValueToCssValueMap;
    private static final Hashtable cssValueToInternalValueMap;
    private transient Hashtable valueConvertor;
    private int baseFontSize;
    
    static {
        attributeMap = new Hashtable();
        valueMap = new Hashtable();
        CSS.sizeMap = new int[] { 8, 10, 12, 14, 18, 24, 36 };
        htmlAttrToCssAttrMap = new Hashtable(20);
        styleConstantToCssMap = new Hashtable(17);
        htmlValueToCssValueMap = new Hashtable(8);
        cssValueToInternalValueMap = new Hashtable(8);
        for (int i = 0; i < Attribute.allAttributes.length; ++i) {
            CSS.attributeMap.put(Attribute.allAttributes[i].toString(), Attribute.allAttributes[i]);
        }
        for (int j = 0; j < Value.allValues.length; ++j) {
            CSS.valueMap.put(Value.allValues[j].toString(), Value.allValues[j]);
        }
        CSS.htmlAttrToCssAttrMap.put(HTML.Attribute.COLOR, new Attribute[] { Attribute.COLOR });
        CSS.htmlAttrToCssAttrMap.put(HTML.Attribute.TEXT, new Attribute[] { Attribute.COLOR });
        CSS.htmlAttrToCssAttrMap.put(HTML.Attribute.CLEAR, new Attribute[] { Attribute.CLEAR });
        CSS.htmlAttrToCssAttrMap.put(HTML.Attribute.BACKGROUND, new Attribute[] { Attribute.BACKGROUND_IMAGE });
        CSS.htmlAttrToCssAttrMap.put(HTML.Attribute.BGCOLOR, new Attribute[] { Attribute.BACKGROUND_COLOR });
        CSS.htmlAttrToCssAttrMap.put(HTML.Attribute.WIDTH, new Attribute[] { Attribute.WIDTH });
        CSS.htmlAttrToCssAttrMap.put(HTML.Attribute.HEIGHT, new Attribute[] { Attribute.HEIGHT });
        CSS.htmlAttrToCssAttrMap.put(HTML.Attribute.BORDER, new Attribute[] { Attribute.BORDER_WIDTH });
        CSS.htmlAttrToCssAttrMap.put(HTML.Attribute.CELLPADDING, new Attribute[] { Attribute.PADDING });
        CSS.htmlAttrToCssAttrMap.put(HTML.Attribute.CELLSPACING, new Attribute[] { Attribute.MARGIN });
        CSS.htmlAttrToCssAttrMap.put(HTML.Attribute.MARGINWIDTH, new Attribute[] { Attribute.MARGIN_LEFT, Attribute.MARGIN_RIGHT });
        CSS.htmlAttrToCssAttrMap.put(HTML.Attribute.MARGINHEIGHT, new Attribute[] { Attribute.MARGIN_TOP, Attribute.MARGIN_BOTTOM });
        CSS.htmlAttrToCssAttrMap.put(HTML.Attribute.HSPACE, new Attribute[] { Attribute.PADDING_LEFT, Attribute.PADDING_RIGHT });
        CSS.htmlAttrToCssAttrMap.put(HTML.Attribute.VSPACE, new Attribute[] { Attribute.PADDING_BOTTOM, Attribute.PADDING_TOP });
        CSS.htmlAttrToCssAttrMap.put(HTML.Attribute.FACE, new Attribute[] { Attribute.FONT_FAMILY });
        CSS.htmlAttrToCssAttrMap.put(HTML.Attribute.SIZE, new Attribute[] { Attribute.FONT_SIZE });
        CSS.htmlAttrToCssAttrMap.put(HTML.Attribute.VALIGN, new Attribute[] { Attribute.VERTICAL_ALIGN });
        CSS.htmlAttrToCssAttrMap.put(HTML.Attribute.ALIGN, new Attribute[] { Attribute.VERTICAL_ALIGN, Attribute.TEXT_ALIGN, Attribute.FLOAT });
        CSS.htmlAttrToCssAttrMap.put(HTML.Attribute.TYPE, new Attribute[] { Attribute.LIST_STYLE_TYPE });
        CSS.styleConstantToCssMap.put(StyleConstants.FontFamily, Attribute.FONT_FAMILY);
        CSS.styleConstantToCssMap.put(StyleConstants.FontSize, Attribute.FONT_SIZE);
        CSS.styleConstantToCssMap.put(StyleConstants.Bold, Attribute.FONT_WEIGHT);
        CSS.styleConstantToCssMap.put(StyleConstants.Italic, Attribute.FONT_STYLE);
        CSS.styleConstantToCssMap.put(StyleConstants.Underline, Attribute.TEXT_DECORATION);
        CSS.styleConstantToCssMap.put(StyleConstants.StrikeThrough, Attribute.TEXT_DECORATION);
        CSS.styleConstantToCssMap.put(StyleConstants.Superscript, Attribute.VERTICAL_ALIGN);
        CSS.styleConstantToCssMap.put(StyleConstants.Subscript, Attribute.VERTICAL_ALIGN);
        CSS.styleConstantToCssMap.put(StyleConstants.Foreground, Attribute.COLOR);
        CSS.styleConstantToCssMap.put(StyleConstants.Background, Attribute.BACKGROUND_COLOR);
        CSS.styleConstantToCssMap.put(StyleConstants.FirstLineIndent, Attribute.TEXT_INDENT);
        CSS.styleConstantToCssMap.put(StyleConstants.LeftIndent, Attribute.MARGIN_LEFT);
        CSS.styleConstantToCssMap.put(StyleConstants.RightIndent, Attribute.MARGIN_RIGHT);
        CSS.styleConstantToCssMap.put(StyleConstants.SpaceAbove, Attribute.MARGIN_TOP);
        CSS.styleConstantToCssMap.put(StyleConstants.SpaceBelow, Attribute.MARGIN_BOTTOM);
        CSS.styleConstantToCssMap.put(StyleConstants.Alignment, Attribute.TEXT_ALIGN);
        CSS.htmlValueToCssValueMap.put("disc", Value.DISC);
        CSS.htmlValueToCssValueMap.put("square", Value.SQUARE);
        CSS.htmlValueToCssValueMap.put("circle", Value.CIRCLE);
        CSS.htmlValueToCssValueMap.put("1", Value.DECIMAL);
        CSS.htmlValueToCssValueMap.put("a", Value.LOWER_ALPHA);
        CSS.htmlValueToCssValueMap.put("A", Value.UPPER_ALPHA);
        CSS.htmlValueToCssValueMap.put("i", Value.LOWER_ROMAN);
        CSS.htmlValueToCssValueMap.put("I", Value.UPPER_ROMAN);
        CSS.cssValueToInternalValueMap.put("disc", Value.DISC);
        CSS.cssValueToInternalValueMap.put("square", Value.SQUARE);
        CSS.cssValueToInternalValueMap.put("circle", Value.CIRCLE);
        CSS.cssValueToInternalValueMap.put("decimal", Value.DECIMAL);
        CSS.cssValueToInternalValueMap.put("lower-roman", Value.LOWER_ROMAN);
        CSS.cssValueToInternalValueMap.put("upper-roman", Value.UPPER_ROMAN);
        CSS.cssValueToInternalValueMap.put("lower-alpha", Value.LOWER_ALPHA);
        CSS.cssValueToInternalValueMap.put("upper-alpha", Value.UPPER_ALPHA);
        final Attribute[] allAttributes = Attribute.allAttributes;
        try {
            for (int k = 0; k < allAttributes.length; ++k) {
                StyleContext.registerStaticAttributeKey(allAttributes[k]);
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public CSS() {
        this.baseFontSize = 3;
        (this.valueConvertor = new Hashtable()).put(Attribute.FONT_SIZE, new FontSize());
        this.valueConvertor.put(Attribute.FONT_FAMILY, new FontFamily());
        this.valueConvertor.put(Attribute.FONT_WEIGHT, new FontWeight());
        this.valueConvertor.put(Attribute.BORDER_STYLE, new BorderStyle());
        final ColorValue colorValue = new ColorValue();
        this.valueConvertor.put(Attribute.COLOR, colorValue);
        this.valueConvertor.put(Attribute.BACKGROUND_COLOR, colorValue);
        this.valueConvertor.put(Attribute.BORDER_COLOR, colorValue);
        final LengthValue lengthValue = new LengthValue();
        this.valueConvertor.put(Attribute.MARGIN_TOP, lengthValue);
        this.valueConvertor.put(Attribute.MARGIN_BOTTOM, lengthValue);
        this.valueConvertor.put(Attribute.MARGIN_LEFT, lengthValue);
        this.valueConvertor.put(Attribute.MARGIN_RIGHT, lengthValue);
        this.valueConvertor.put(Attribute.PADDING_TOP, lengthValue);
        this.valueConvertor.put(Attribute.PADDING_BOTTOM, lengthValue);
        this.valueConvertor.put(Attribute.PADDING_LEFT, lengthValue);
        this.valueConvertor.put(Attribute.PADDING_RIGHT, lengthValue);
        this.valueConvertor.put(Attribute.BORDER_WIDTH, lengthValue);
        this.valueConvertor.put(Attribute.BORDER_TOP_WIDTH, lengthValue);
        this.valueConvertor.put(Attribute.BORDER_BOTTOM_WIDTH, lengthValue);
        this.valueConvertor.put(Attribute.BORDER_LEFT_WIDTH, lengthValue);
        this.valueConvertor.put(Attribute.BORDER_RIGHT_WIDTH, lengthValue);
        this.valueConvertor.put(Attribute.TEXT_INDENT, lengthValue);
        this.valueConvertor.put(Attribute.WIDTH, lengthValue);
        this.valueConvertor.put(Attribute.HEIGHT, lengthValue);
        final StringValue stringValue = new StringValue();
        this.valueConvertor.put(Attribute.FONT_STYLE, stringValue);
        this.valueConvertor.put(Attribute.TEXT_DECORATION, stringValue);
        this.valueConvertor.put(Attribute.TEXT_ALIGN, stringValue);
        this.valueConvertor.put(Attribute.VERTICAL_ALIGN, stringValue);
        this.valueConvertor.put(Attribute.LIST_STYLE_TYPE, new ListType());
        final CssValue cssValue = new CssValue();
        for (int length = Attribute.allAttributes.length, i = 0; i < length; ++i) {
            final Attribute attribute = Attribute.allAttributes[i];
            if (this.valueConvertor.get(attribute) == null) {
                this.valueConvertor.put(attribute, cssValue);
            }
        }
    }
    
    static String colorToHex(final Color color) {
        String s = new String("#");
        final String hexString = Integer.toHexString(color.getRed());
        if (hexString.length() > 2) {
            hexString.substring(0, 2);
        }
        else if (hexString.length() < 2) {
            s = String.valueOf(s) + "0" + hexString;
        }
        else {
            s = String.valueOf(s) + hexString;
        }
        final String hexString2 = Integer.toHexString(color.getGreen());
        if (hexString2.length() > 2) {
            hexString2.substring(0, 2);
        }
        else if (hexString2.length() < 2) {
            s = String.valueOf(s) + "0" + hexString2;
        }
        else {
            s = String.valueOf(s) + hexString2;
        }
        final String hexString3 = Integer.toHexString(color.getBlue());
        if (hexString3.length() > 2) {
            hexString3.substring(0, 2);
        }
        else if (hexString3.length() < 2) {
            s = String.valueOf(s) + "0" + hexString3;
        }
        else {
            s = String.valueOf(s) + hexString3;
        }
        return s;
    }
    
    Object cssValueToStyleConstantsValue(final StyleConstants styleConstants, final Object o) {
        if (o instanceof CssValue) {
            return ((CssValue)o).toStyleConstants(styleConstants);
        }
        return null;
    }
    
    public static Attribute[] getAllAttributeKeys() {
        final Attribute[] array = new Attribute[Attribute.allAttributes.length];
        System.arraycopy(Attribute.allAttributes, 0, array, 0, Attribute.allAttributes.length);
        return array;
    }
    
    public static final Attribute getAttribute(final String s) {
        return CSS.attributeMap.get(s);
    }
    
    int getBaseFontSize() {
        return this.baseFontSize;
    }
    
    Color getColor(final AttributeSet set, final Attribute attribute) {
        final ColorValue colorValue = (ColorValue)set.getAttribute(attribute);
        if (colorValue != null) {
            return colorValue.getValue();
        }
        return null;
    }
    
    private Attribute getCssAlignAttribute(final HTML.Tag tag, final AttributeSet set) {
        return Attribute.TEXT_ALIGN;
    }
    
    private Attribute[] getCssAttribute(final HTML.Attribute attribute) {
        return CSS.htmlAttrToCssAttrMap.get(attribute);
    }
    
    Object getCssValue(final Attribute attribute, final String s) {
        return this.valueConvertor.get(attribute).parseHtmlValue(s);
    }
    
    Font getFont(final StyleContext styleContext, final AttributeSet set, final int n) {
        final FontSize fontSize = (FontSize)set.getAttribute(Attribute.FONT_SIZE);
        int n2 = (fontSize != null) ? ((int)fontSize.getValue(set)) : n;
        final StringValue stringValue = (StringValue)set.getAttribute(Attribute.VERTICAL_ALIGN);
        if (stringValue != null) {
            final String string = ((CssValue)stringValue).toString();
            if (string.indexOf("sup") >= 0 || string.indexOf("sub") >= 0) {
                n2 -= 2;
            }
        }
        final FontFamily fontFamily = (FontFamily)set.getAttribute(Attribute.FONT_FAMILY);
        final String s = (fontFamily != null) ? fontFamily.getValue() : "SansSerif";
        int n3 = 0;
        final FontWeight fontWeight = (FontWeight)set.getAttribute(Attribute.FONT_WEIGHT);
        if (fontWeight != null && fontWeight.getValue() > 400) {
            n3 |= 0x1;
        }
        final Object attribute = set.getAttribute(Attribute.FONT_STYLE);
        if (attribute != null && attribute.toString().indexOf("italic") >= 0) {
            n3 |= 0x2;
        }
        return styleContext.getFont(s, n3, n2);
    }
    
    private HTML.Tag getHTMLTag(final AttributeSet set) {
        final Object attribute = set.getAttribute(StyleConstants.NameAttribute);
        if (attribute instanceof HTML.Tag) {
            return (HTML.Tag)attribute;
        }
        return null;
    }
    
    static int getIndexOfSize(final float n) {
        for (int i = 0; i < CSS.sizeMap.length; ++i) {
            if (n <= CSS.sizeMap[i]) {
                return i;
            }
        }
        return CSS.sizeMap.length - 1;
    }
    
    Object getInternalCSSValue(final Attribute attribute, final String s) {
        return this.valueConvertor.get(attribute).parseCssValue(s);
    }
    
    float getLength(final AttributeSet set, final Attribute attribute) {
        final LengthValue lengthValue = (LengthValue)set.getAttribute(attribute);
        return (lengthValue != null) ? lengthValue.getValue() : 0.0f;
    }
    
    float getPointSize(final int n) {
        if (n < 0) {
            return CSS.sizeMap[0];
        }
        if (n > CSS.sizeMap.length - 1) {
            return CSS.sizeMap[CSS.sizeMap.length - 1];
        }
        return CSS.sizeMap[n];
    }
    
    float getPointSize(final String s) {
        if (s == null) {
            return 0.0f;
        }
        if (s.startsWith("+")) {
            return this.getPointSize(this.baseFontSize + Integer.valueOf(s.substring(1)));
        }
        if (s.startsWith("-")) {
            return this.getPointSize(this.baseFontSize + -Integer.valueOf(s.substring(1)));
        }
        return this.getPointSize(Integer.valueOf(s));
    }
    
    static final Value getValue(final String s) {
        return CSS.valueMap.get(s);
    }
    
    static final Color hexToColor(final String s) {
        if (s.startsWith("#")) {
            return Color.decode("0x" + s.substring(1, Math.min(s.length(), 7)));
        }
        return null;
    }
    
    private boolean isFloater(final String s) {
        return s.equals("left") || s.equals("right");
    }
    
    private boolean isHTMLFontTag(final HTML.Tag tag) {
        return tag != null && (tag == HTML.Tag.FONT || tag == HTML.Tag.BASEFONT);
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        int int1 = objectInputStream.readInt();
        this.valueConvertor = new Hashtable(Math.max(1, int1));
        while (int1-- > 0) {
            Object object = objectInputStream.readObject();
            Object object2 = objectInputStream.readObject();
            final Object staticAttribute = StyleContext.getStaticAttribute(object);
            if (staticAttribute != null) {
                object = staticAttribute;
            }
            final Object staticAttribute2 = StyleContext.getStaticAttribute(object2);
            if (staticAttribute2 != null) {
                object2 = staticAttribute2;
            }
            if (object != null && object2 != null) {
                this.valueConvertor.put(object, object2);
            }
        }
    }
    
    void setBaseFontSize(final int baseFontSize) {
        if (baseFontSize < 1) {
            this.baseFontSize = 0;
        }
        else if (baseFontSize > 7) {
            this.baseFontSize = 7;
        }
        else {
            this.baseFontSize = baseFontSize;
        }
    }
    
    void setBaseFontSize(final String s) {
        if (s != null) {
            if (s.startsWith("+")) {
                this.setBaseFontSize(this.baseFontSize + Integer.valueOf(s.substring(1)));
            }
            else if (s.startsWith("-")) {
                this.setBaseFontSize(this.baseFontSize + -Integer.valueOf(s.substring(1)));
            }
            else {
                this.setBaseFontSize(Integer.valueOf(s));
            }
        }
    }
    
    static Color stringToColor(final String s) {
        Color color = null;
        if (s.charAt(0) == '#') {
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
        return color;
    }
    
    Attribute styleConstantsKeyToCSSKey(final StyleConstants styleConstants) {
        return CSS.styleConstantToCssMap.get(styleConstants);
    }
    
    Object styleConstantsValueToCSSValue(final StyleConstants styleConstants, final Object o) {
        final Attribute styleConstantsKeyToCSSKey = this.styleConstantsKeyToCSSKey(styleConstants);
        if (styleConstantsKeyToCSSKey != null) {
            return ((CssValue)this.valueConvertor.get(styleConstantsKeyToCSSKey)).fromStyleConstants(styleConstants, o);
        }
        return null;
    }
    
    private void translateAttribute(final HTML.Attribute attribute, final AttributeSet set, final MutableAttributeSet set2) {
        final Attribute[] cssAttribute = this.getCssAttribute(attribute);
        final String s = (String)set.getAttribute(attribute);
        if (cssAttribute == null || s == null) {
            return;
        }
        for (int i = 0; i < cssAttribute.length; ++i) {
            final Object cssValue = this.getCssValue(cssAttribute[i], s);
            if (cssValue != null) {
                set2.addAttribute(cssAttribute[i], cssValue);
            }
        }
    }
    
    private void translateAttributes(final HTML.Tag tag, final AttributeSet set, final MutableAttributeSet set2) {
        final Enumeration attributeNames = set.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            final HTML.Attribute nextElement = attributeNames.nextElement();
            if (nextElement instanceof HTML.Attribute) {
                final HTML.Attribute attribute = nextElement;
                if (attribute == HTML.Attribute.ALIGN) {
                    final String s = (String)set.getAttribute(HTML.Attribute.ALIGN);
                    if (s == null) {
                        continue;
                    }
                    final Attribute cssAlignAttribute = this.getCssAlignAttribute(tag, set);
                    if (cssAlignAttribute == null) {
                        continue;
                    }
                    final Object cssValue = this.getCssValue(cssAlignAttribute, s);
                    if (cssValue == null) {
                        continue;
                    }
                    set2.addAttribute(cssAlignAttribute, cssValue);
                }
                else {
                    if (attribute == HTML.Attribute.SIZE && !this.isHTMLFontTag(tag)) {
                        continue;
                    }
                    this.translateAttribute(attribute, set, set2);
                }
            }
            else {
                if (!(nextElement instanceof Attribute)) {
                    continue;
                }
                set2.addAttribute(nextElement, set.getAttribute(nextElement));
            }
        }
    }
    
    private void translateEmbeddedAttributes(final AttributeSet set, final MutableAttributeSet set2) {
        final Enumeration attributeNames = set.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            final HTML.Tag nextElement = attributeNames.nextElement();
            if (nextElement instanceof HTML.Tag) {
                final HTML.Tag tag = nextElement;
                final Object attribute = set.getAttribute(tag);
                if (attribute == null || !(attribute instanceof AttributeSet)) {
                    continue;
                }
                this.translateAttributes(tag, (AttributeSet)attribute, set2);
            }
            else {
                if (!(nextElement instanceof Attribute)) {
                    continue;
                }
                set2.addAttribute(nextElement, set.getAttribute(nextElement));
            }
        }
    }
    
    AttributeSet translateHTMLToCSS(final AttributeSet set) {
        final SimpleAttributeSet set2 = new SimpleAttributeSet();
        final Element element = (Element)set;
        final HTML.Tag htmlTag = this.getHTMLTag(set);
        if (htmlTag == HTML.Tag.TD || htmlTag == HTML.Tag.TH) {
            final AttributeSet attributes = element.getParentElement().getParentElement().getAttributes();
            this.translateAttribute(HTML.Attribute.BORDER, attributes, set2);
            final String s = (String)attributes.getAttribute(HTML.Attribute.CELLPADDING);
            if (s != null) {
                final Object internalCSSValue = this.getInternalCSSValue(Attribute.PADDING_TOP, s);
                set2.addAttribute(Attribute.PADDING_TOP, internalCSSValue);
                set2.addAttribute(Attribute.PADDING_BOTTOM, internalCSSValue);
                set2.addAttribute(Attribute.PADDING_LEFT, internalCSSValue);
                set2.addAttribute(Attribute.PADDING_RIGHT, internalCSSValue);
            }
        }
        if (element.isLeaf()) {
            this.translateEmbeddedAttributes(set, set2);
        }
        else {
            this.translateAttributes(htmlTag, set, set2);
        }
        return set2;
    }
    
    private boolean validTextAlignValue(final String s) {
        return this.isFloater(s) || s.equals("center");
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        final Enumeration<Object> keys = this.valueConvertor.keys();
        objectOutputStream.writeInt(this.valueConvertor.size());
        if (keys != null) {
            while (keys.hasMoreElements()) {
                Object o = keys.nextElement();
                Object o2 = this.valueConvertor.get(o);
                if (!(o instanceof Serializable) && (o = StyleContext.getStaticAttributeKey(o)) == null) {
                    o = null;
                    o2 = null;
                }
                else if (!(o2 instanceof Serializable) && (o2 = StyleContext.getStaticAttributeKey(o2)) == null) {
                    o = null;
                    o2 = null;
                }
                objectOutputStream.writeObject(o);
                objectOutputStream.writeObject(o2);
            }
        }
    }
    
    public static final class Attribute
    {
        private String name;
        private String defaultValue;
        private boolean inherited;
        public static final Attribute BACKGROUND;
        public static final Attribute BACKGROUND_ATTACHMENT;
        public static final Attribute BACKGROUND_COLOR;
        public static final Attribute BACKGROUND_IMAGE;
        public static final Attribute BACKGROUND_POSITION;
        public static final Attribute BACKGROUND_REPEAT;
        public static final Attribute BORDER;
        public static final Attribute BORDER_BOTTOM;
        public static final Attribute BORDER_BOTTOM_WIDTH;
        public static final Attribute BORDER_COLOR;
        public static final Attribute BORDER_LEFT;
        public static final Attribute BORDER_LEFT_WIDTH;
        public static final Attribute BORDER_RIGHT;
        public static final Attribute BORDER_RIGHT_WIDTH;
        public static final Attribute BORDER_STYLE;
        public static final Attribute BORDER_TOP;
        public static final Attribute BORDER_TOP_WIDTH;
        public static final Attribute BORDER_WIDTH;
        public static final Attribute CLEAR;
        public static final Attribute COLOR;
        public static final Attribute DISPLAY;
        public static final Attribute FLOAT;
        public static final Attribute FONT;
        public static final Attribute FONT_FAMILY;
        public static final Attribute FONT_SIZE;
        public static final Attribute FONT_STYLE;
        public static final Attribute FONT_VARIANT;
        public static final Attribute FONT_WEIGHT;
        public static final Attribute HEIGHT;
        public static final Attribute LETTER_SPACING;
        public static final Attribute LINE_HEIGHT;
        public static final Attribute LIST_STYLE;
        public static final Attribute LIST_STYLE_IMAGE;
        public static final Attribute LIST_STYLE_POSITION;
        public static final Attribute LIST_STYLE_TYPE;
        public static final Attribute MARGIN;
        public static final Attribute MARGIN_BOTTOM;
        public static final Attribute MARGIN_LEFT;
        public static final Attribute MARGIN_RIGHT;
        public static final Attribute MARGIN_TOP;
        public static final Attribute PADDING;
        public static final Attribute PADDING_BOTTOM;
        public static final Attribute PADDING_LEFT;
        public static final Attribute PADDING_RIGHT;
        public static final Attribute PADDING_TOP;
        public static final Attribute TEXT_ALIGN;
        public static final Attribute TEXT_DECORATION;
        public static final Attribute TEXT_INDENT;
        public static final Attribute TEXT_TRANSFORM;
        public static final Attribute VERTICAL_ALIGN;
        public static final Attribute WORD_SPACING;
        public static final Attribute WHITE_SPACE;
        public static final Attribute WIDTH;
        static final Attribute[] allAttributes;
        
        static {
            BACKGROUND = new Attribute("background", null, false);
            BACKGROUND_ATTACHMENT = new Attribute("background-attachment", "scroll", false);
            BACKGROUND_COLOR = new Attribute("background-color", "transparent", false);
            BACKGROUND_IMAGE = new Attribute("background-image", "none", false);
            BACKGROUND_POSITION = new Attribute("background-position", null, false);
            BACKGROUND_REPEAT = new Attribute("background-repeat", "repeat", false);
            BORDER = new Attribute("border", null, false);
            BORDER_BOTTOM = new Attribute("border-bottom", null, false);
            BORDER_BOTTOM_WIDTH = new Attribute("border-bottom-width", "medium", false);
            BORDER_COLOR = new Attribute("border-color", null, false);
            BORDER_LEFT = new Attribute("border-left", null, false);
            BORDER_LEFT_WIDTH = new Attribute("border-left-width", "medium", false);
            BORDER_RIGHT = new Attribute("border-right", null, false);
            BORDER_RIGHT_WIDTH = new Attribute("border-right-width", "medium", false);
            BORDER_STYLE = new Attribute("border-style", "none", false);
            BORDER_TOP = new Attribute("border-top", null, false);
            BORDER_TOP_WIDTH = new Attribute("border-top-width", "medium", false);
            BORDER_WIDTH = new Attribute("border-width", "medium", false);
            CLEAR = new Attribute("clear", "none", false);
            COLOR = new Attribute("color", null, true);
            DISPLAY = new Attribute("display", "block", false);
            FLOAT = new Attribute("float", "none", false);
            FONT = new Attribute("font", null, true);
            FONT_FAMILY = new Attribute("font-family", null, true);
            FONT_SIZE = new Attribute("font-size", "medium", true);
            FONT_STYLE = new Attribute("font-style", "normal", true);
            FONT_VARIANT = new Attribute("font-variant", "normal", true);
            FONT_WEIGHT = new Attribute("font-weight", "normal", true);
            HEIGHT = new Attribute("height", "auto", false);
            LETTER_SPACING = new Attribute("letter-spacing", "normal", true);
            LINE_HEIGHT = new Attribute("line-height", "normal", true);
            LIST_STYLE = new Attribute("list-style", null, true);
            LIST_STYLE_IMAGE = new Attribute("list-style-image", "none", true);
            LIST_STYLE_POSITION = new Attribute("list-style-position", "outside", true);
            LIST_STYLE_TYPE = new Attribute("list-style-type", "disc", true);
            MARGIN = new Attribute("margin", null, false);
            MARGIN_BOTTOM = new Attribute("margin-bottom", "0", false);
            MARGIN_LEFT = new Attribute("margin-left", "0", false);
            MARGIN_RIGHT = new Attribute("margin-right", "0", false);
            MARGIN_TOP = new Attribute("margin-top", "0", false);
            PADDING = new Attribute("padding", null, false);
            PADDING_BOTTOM = new Attribute("padding-bottom", "0", false);
            PADDING_LEFT = new Attribute("padding-left", "0", false);
            PADDING_RIGHT = new Attribute("padding-right", "0", false);
            PADDING_TOP = new Attribute("padding-top", "0", false);
            TEXT_ALIGN = new Attribute("text-align", null, true);
            TEXT_DECORATION = new Attribute("text-decoration", "none", true);
            TEXT_INDENT = new Attribute("text-indent", "0", true);
            TEXT_TRANSFORM = new Attribute("text-transform", "none", true);
            VERTICAL_ALIGN = new Attribute("vertical-align", "baseline", false);
            WORD_SPACING = new Attribute("word-spacing", "normal", true);
            WHITE_SPACE = new Attribute("whitespace", "normal", true);
            WIDTH = new Attribute("width", "auto", false);
            allAttributes = new Attribute[] { Attribute.BACKGROUND, Attribute.BACKGROUND_ATTACHMENT, Attribute.BACKGROUND_COLOR, Attribute.BACKGROUND_IMAGE, Attribute.BACKGROUND_POSITION, Attribute.BACKGROUND_REPEAT, Attribute.BORDER, Attribute.BORDER_BOTTOM, Attribute.BORDER_BOTTOM_WIDTH, Attribute.BORDER_COLOR, Attribute.BORDER_LEFT, Attribute.BORDER_LEFT_WIDTH, Attribute.BORDER_RIGHT, Attribute.BORDER_RIGHT_WIDTH, Attribute.BORDER_STYLE, Attribute.BORDER_TOP, Attribute.BORDER_TOP_WIDTH, Attribute.BORDER_WIDTH, Attribute.CLEAR, Attribute.COLOR, Attribute.DISPLAY, Attribute.FLOAT, Attribute.FONT, Attribute.FONT_FAMILY, Attribute.FONT_SIZE, Attribute.FONT_STYLE, Attribute.FONT_VARIANT, Attribute.FONT_WEIGHT, Attribute.HEIGHT, Attribute.LETTER_SPACING, Attribute.LINE_HEIGHT, Attribute.LIST_STYLE, Attribute.LIST_STYLE_IMAGE, Attribute.LIST_STYLE_POSITION, Attribute.LIST_STYLE_TYPE, Attribute.MARGIN, Attribute.MARGIN_BOTTOM, Attribute.MARGIN_LEFT, Attribute.MARGIN_RIGHT, Attribute.MARGIN_TOP, Attribute.PADDING, Attribute.PADDING_BOTTOM, Attribute.PADDING_LEFT, Attribute.PADDING_RIGHT, Attribute.PADDING_TOP, Attribute.TEXT_ALIGN, Attribute.TEXT_DECORATION, Attribute.TEXT_INDENT, Attribute.TEXT_TRANSFORM, Attribute.VERTICAL_ALIGN, Attribute.WORD_SPACING, Attribute.WHITE_SPACE, Attribute.WIDTH };
        }
        
        private Attribute(final String name, final String defaultValue, final boolean inherited) {
            this.name = name;
            this.defaultValue = defaultValue;
            this.inherited = inherited;
        }
        
        public String getDefaultValue() {
            return this.defaultValue;
        }
        
        public boolean isInherited() {
            return this.inherited;
        }
        
        public String toString() {
            return this.name;
        }
    }
    
    static final class Value
    {
        static final Value INHERITED;
        static final Value NONE;
        static final Value DOTTED;
        static final Value DASHED;
        static final Value SOLID;
        static final Value DOUBLE;
        static final Value GROOVE;
        static final Value RIDGE;
        static final Value INSET;
        static final Value OUTSET;
        static final Value BLANK_LIST_ITEM;
        static final Value DISC;
        static final Value CIRCLE;
        static final Value SQUARE;
        static final Value DECIMAL;
        static final Value LOWER_ROMAN;
        static final Value UPPER_ROMAN;
        static final Value LOWER_ALPHA;
        static final Value UPPER_ALPHA;
        private String name;
        static final Value[] allValues;
        
        static {
            INHERITED = new Value("inherited");
            NONE = new Value("none");
            DOTTED = new Value("dotted");
            DASHED = new Value("dashed");
            SOLID = new Value("solid");
            DOUBLE = new Value("double");
            GROOVE = new Value("groove");
            RIDGE = new Value("ridge");
            INSET = new Value("inset");
            OUTSET = new Value("outset");
            BLANK_LIST_ITEM = new Value("none");
            DISC = new Value("disc");
            CIRCLE = new Value("circle");
            SQUARE = new Value("square");
            DECIMAL = new Value("decimal");
            LOWER_ROMAN = new Value("lower-roman");
            UPPER_ROMAN = new Value("upper-roman");
            LOWER_ALPHA = new Value("lower-alpha");
            UPPER_ALPHA = new Value("upper-alpha");
            allValues = new Value[] { Value.INHERITED, Value.NONE, Value.DOTTED, Value.DASHED, Value.SOLID, Value.DOUBLE, Value.GROOVE, Value.RIDGE, Value.INSET, Value.OUTSET, Value.DISC, Value.CIRCLE, Value.SQUARE, Value.DECIMAL, Value.LOWER_ROMAN, Value.UPPER_ROMAN, Value.LOWER_ALPHA, Value.UPPER_ALPHA, Value.BLANK_LIST_ITEM };
        }
        
        private Value(final String name) {
            this.name = name;
        }
        
        public String toString() {
            return this.name;
        }
    }
    
    static class CssValue implements Serializable
    {
        String svalue;
        
        Object fromStyleConstants(final StyleConstants styleConstants, final Object o) {
            return null;
        }
        
        Object parseCssValue(final String s) {
            return s;
        }
        
        Object parseHtmlValue(final String s) {
            return this.parseCssValue(s);
        }
        
        public String toString() {
            return this.svalue;
        }
        
        Object toStyleConstants(final StyleConstants styleConstants) {
            return null;
        }
    }
    
    static class StringValue extends CssValue
    {
        Object fromStyleConstants(final StyleConstants styleConstants, final Object o) {
            if (styleConstants == StyleConstants.Italic) {
                if (o.equals(Boolean.TRUE)) {
                    return this.parseCssValue("italic");
                }
                return this.parseCssValue("");
            }
            else if (styleConstants == StyleConstants.Underline) {
                if (o.equals(Boolean.TRUE)) {
                    return this.parseCssValue("underline");
                }
                return this.parseCssValue("");
            }
            else {
                if (styleConstants == StyleConstants.Alignment) {
                    String s = null;
                    switch ((int)o) {
                        case 0: {
                            s = "left";
                            break;
                        }
                        case 2: {
                            s = "right";
                            break;
                        }
                        case 1: {
                            s = "center";
                            break;
                        }
                        case 3: {
                            s = "justify";
                            break;
                        }
                        default: {
                            s = "left";
                            break;
                        }
                    }
                    return this.parseCssValue(s);
                }
                if (styleConstants == StyleConstants.StrikeThrough) {
                    if (o.equals(Boolean.TRUE)) {
                        return this.parseCssValue("line-through");
                    }
                    return this.parseCssValue("");
                }
                else if (styleConstants == StyleConstants.Superscript) {
                    if (o.equals(Boolean.TRUE)) {
                        return this.parseCssValue("super");
                    }
                    return this.parseCssValue("");
                }
                else {
                    if (styleConstants != StyleConstants.Subscript) {
                        return null;
                    }
                    if (o.equals(Boolean.TRUE)) {
                        return this.parseCssValue("sub");
                    }
                    return this.parseCssValue("");
                }
            }
        }
        
        Object parseCssValue(final String svalue) {
            final StringValue stringValue = new StringValue();
            stringValue.svalue = svalue;
            return stringValue;
        }
        
        Object toStyleConstants(final StyleConstants styleConstants) {
            if (styleConstants == StyleConstants.Italic) {
                if (super.svalue.indexOf("italic") >= 0) {
                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            }
            else if (styleConstants == StyleConstants.Underline) {
                if (super.svalue.indexOf("underline") >= 0) {
                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            }
            else if (styleConstants == StyleConstants.Alignment) {
                if (super.svalue.equals("right")) {
                    return new Integer(2);
                }
                if (super.svalue.equals("center")) {
                    return new Integer(1);
                }
                if (super.svalue.equals("justify")) {
                    return new Integer(3);
                }
                return new Integer(0);
            }
            else if (styleConstants == StyleConstants.StrikeThrough) {
                if (super.svalue.indexOf("line-through") >= 0) {
                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            }
            else if (styleConstants == StyleConstants.Superscript) {
                if (super.svalue.indexOf("super") >= 0) {
                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            }
            else {
                if (styleConstants != StyleConstants.Subscript) {
                    return null;
                }
                if (super.svalue.indexOf("sub") >= 0) {
                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            }
        }
    }
    
    class FontSize extends CssValue
    {
        float value;
        boolean relative;
        boolean index;
        boolean percentage;
        
        Object fromStyleConstants(final StyleConstants styleConstants, final Object o) {
            return this.parseCssValue(o.toString());
        }
        
        float getValue(final AttributeSet set) {
            if (this.index) {
                return CSS.this.getPointSize((int)this.value);
            }
            return this.value;
        }
        
        boolean isRelative() {
            return this.relative;
        }
        
        Object parseCssValue(final String svalue) {
            FontSize fontSize = new FontSize();
            fontSize.svalue = svalue;
            try {
                if (svalue.equals("xx-small")) {
                    fontSize.value = 0.0f;
                    fontSize.index = true;
                }
                else if (svalue.equals("x-small")) {
                    fontSize.value = 1.0f;
                    fontSize.index = true;
                }
                else if (svalue.equals("small")) {
                    fontSize.value = 2.0f;
                    fontSize.index = true;
                }
                else if (svalue.equals("medium")) {
                    fontSize.value = 3.0f;
                    fontSize.index = true;
                }
                else if (svalue.equals("large")) {
                    fontSize.value = 4.0f;
                    fontSize.index = true;
                }
                else if (svalue.equals("x-large")) {
                    fontSize.value = 5.0f;
                    fontSize.index = true;
                }
                else if (svalue.equals("xx-large")) {
                    fontSize.value = 6.0f;
                    fontSize.index = true;
                }
                else if (svalue.equals("bigger")) {
                    fontSize.value = 1.0f;
                    fontSize.index = true;
                    fontSize.relative = true;
                }
                else if (svalue.equals("smaller")) {
                    fontSize.value = -1.0f;
                    fontSize.index = true;
                    fontSize.relative = true;
                }
                else if (svalue.endsWith("pt")) {
                    fontSize.value = Float.valueOf(svalue.substring(0, svalue.length() - 2));
                }
                else {
                    fontSize.value = Float.valueOf(svalue);
                }
            }
            catch (NumberFormatException ex) {
                fontSize = null;
            }
            return fontSize;
        }
        
        Object parseHtmlValue(final String svalue) {
            FontSize fontSize = new FontSize();
            fontSize.svalue = svalue;
            try {
                final int baseFontSize = CSS.this.getBaseFontSize();
                if (svalue != null && svalue.charAt(0) == '+') {
                    fontSize.value = baseFontSize + Integer.valueOf(svalue.substring(1));
                    fontSize.index = true;
                }
                else if (svalue != null && svalue.charAt(0) == '-') {
                    fontSize.value = baseFontSize + -Integer.valueOf(svalue.substring(1));
                    fontSize.index = true;
                }
                else {
                    fontSize.value = Integer.parseInt(svalue);
                    if (fontSize.value > 6.0f) {
                        fontSize.value = 6.0f;
                    }
                    else if (fontSize.value < 0.0f) {
                        fontSize.value = 0.0f;
                    }
                    fontSize.index = true;
                }
            }
            catch (NumberFormatException ex) {
                fontSize = null;
            }
            return fontSize;
        }
        
        Object toStyleConstants(final StyleConstants styleConstants) {
            return new Integer((int)this.getValue(null));
        }
    }
    
    static class FontFamily extends CssValue
    {
        String family;
        
        Object fromStyleConstants(final StyleConstants styleConstants, final Object o) {
            return this.parseCssValue(o.toString());
        }
        
        String getValue() {
            return this.family;
        }
        
        Object parseCssValue(final String s) {
            final FontFamily fontFamily = new FontFamily();
            if (s.equals("monospace")) {
                fontFamily.family = "Monospaced";
            }
            else {
                fontFamily.family = s;
            }
            fontFamily.svalue = s;
            return fontFamily;
        }
        
        Object parseHtmlValue(final String s) {
            return this.parseCssValue(s);
        }
        
        Object toStyleConstants(final StyleConstants styleConstants) {
            return this.family;
        }
    }
    
    static class FontWeight extends CssValue
    {
        int weight;
        
        Object fromStyleConstants(final StyleConstants styleConstants, final Object o) {
            if (o.equals(Boolean.TRUE)) {
                return this.parseCssValue("bold");
            }
            return this.parseCssValue("normal");
        }
        
        int getValue() {
            return this.weight;
        }
        
        Object parseCssValue(final String svalue) {
            FontWeight fontWeight = new FontWeight();
            fontWeight.svalue = svalue;
            if (svalue.equals("bold")) {
                fontWeight.weight = 700;
            }
            else if (svalue.equals("normal")) {
                fontWeight.weight = 400;
            }
            else {
                try {
                    fontWeight.weight = Integer.parseInt(svalue);
                }
                catch (NumberFormatException ex) {
                    fontWeight = null;
                }
            }
            return fontWeight;
        }
        
        Object toStyleConstants(final StyleConstants styleConstants) {
            return (this.weight > 500) ? Boolean.TRUE : Boolean.FALSE;
        }
    }
    
    static class ColorValue extends CssValue
    {
        Color c;
        
        Object fromStyleConstants(final StyleConstants styleConstants, final Object o) {
            return this.parseCssValue(CSS.colorToHex((Color)o));
        }
        
        Color getValue() {
            return this.c;
        }
        
        Object parseCssValue(final String svalue) {
            final Color stringToColor = CSS.stringToColor(svalue);
            if (stringToColor != null) {
                final ColorValue colorValue = new ColorValue();
                colorValue.svalue = svalue;
                colorValue.c = stringToColor;
                return colorValue;
            }
            return null;
        }
        
        Object parseHtmlValue(final String s) {
            return this.parseCssValue(s);
        }
        
        Object toStyleConstants(final StyleConstants styleConstants) {
            return this.c;
        }
    }
    
    static class BorderStyle extends CssValue
    {
        private transient Value style;
        
        Value getValue() {
            return this.style;
        }
        
        Object parseCssValue(final String svalue) {
            final Value value = CSS.getValue(svalue);
            if (value != null && (value == Value.INSET || value == Value.OUTSET || value == Value.NONE || value == Value.DOTTED || value == Value.DASHED || value == Value.SOLID || value == Value.DOUBLE || value == Value.GROOVE || value == Value.RIDGE)) {
                final BorderStyle borderStyle = new BorderStyle();
                borderStyle.svalue = svalue;
                borderStyle.style = value;
                return borderStyle;
            }
            return null;
        }
        
        private void readObject(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
            objectInputStream.defaultReadObject();
            final Object object = objectInputStream.readObject();
            if (object != null) {
                this.style = CSS.getValue((String)object);
            }
        }
        
        private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            if (this.style == null) {
                objectOutputStream.writeObject(null);
            }
            else {
                objectOutputStream.writeObject(this.style.toString());
            }
        }
    }
    
    static class LengthValue extends CssValue
    {
        float span;
        
        Object fromStyleConstants(final StyleConstants styleConstants, final Object o) {
            final LengthValue lengthValue = new LengthValue();
            lengthValue.svalue = o.toString();
            lengthValue.span = (float)o;
            return lengthValue;
        }
        
        float getValue() {
            return this.span;
        }
        
        Object parseCssValue(final String svalue) {
            LengthValue lengthValue = new LengthValue();
            lengthValue.svalue = svalue;
            try {
                if (svalue.endsWith("pt")) {
                    lengthValue.span = Float.valueOf(svalue.substring(0, svalue.length() - 2));
                }
                else {
                    lengthValue.span = Float.valueOf(svalue);
                }
            }
            catch (NumberFormatException ex) {
                lengthValue = null;
            }
            return lengthValue;
        }
        
        Object parseHtmlValue(String s) {
            if (s.equals("#DEFAULT")) {
                s = "1";
            }
            return this.parseCssValue(s);
        }
        
        Object toStyleConstants(final StyleConstants styleConstants) {
            return new Float(this.span);
        }
    }
    
    static class ListType extends CssValue
    {
        Object parseCssValue(final String s) {
            Object o = CSS.cssValueToInternalValueMap.get(s);
            if (o == null) {
                o = CSS.cssValueToInternalValueMap.get(s.toLowerCase());
            }
            return o;
        }
        
        Object parseHtmlValue(final String s) {
            Object o = CSS.htmlValueToCssValueMap.get(s);
            if (o == null) {
                o = CSS.htmlValueToCssValueMap.get(s.toLowerCase());
            }
            return o;
        }
    }
}
