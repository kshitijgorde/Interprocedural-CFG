// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text.html;

import java.io.StreamTokenizer;
import javax.swing.event.ChangeListener;
import javax.swing.text.Document;
import java.util.NoSuchElementException;
import javax.swing.text.StyledDocument;
import java.awt.FontMetrics;
import java.net.MalformedURLException;
import javax.swing.ImageIcon;
import java.util.StringTokenizer;
import javax.swing.Icon;
import java.awt.Graphics;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import java.awt.Component;
import java.awt.Insets;
import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.Stack;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.text.View;
import java.util.Map;
import java.awt.Font;
import java.awt.Color;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Element;
import java.util.Enumeration;
import javax.swing.text.Style;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.AttributeSet;
import javax.swing.border.EmptyBorder;
import java.net.URL;
import java.util.Vector;
import java.util.Hashtable;
import javax.swing.border.Border;
import javax.swing.text.StyleContext;

public class StyleSheet extends StyleContext
{
    static final Border noBorder;
    static final int DEFAULT_FONT_SIZE = 3;
    private CSS css;
    private Hashtable selectorMapping;
    private Hashtable resolvedStyles;
    private Vector linkedStyleSheets;
    private URL base;
    static final SheetAttribute SPECIFICITY;
    static final SheetAttribute RULE;
    static final SheetAttribute WEIGHT;
    static final SheetAttribute BOX_PAINTER;
    static final SheetAttribute LIST_PAINTER;
    
    static {
        noBorder = new EmptyBorder(0, 0, 0, 0);
        SPECIFICITY = new SheetAttribute("specificity", false);
        RULE = new SheetAttribute("rule", false);
        WEIGHT = new SheetAttribute("rule-weight", false);
        BOX_PAINTER = new SheetAttribute("box-painter", true);
        LIST_PAINTER = new SheetAttribute("list-painter", true);
        StyleContext.registerStaticAttributeKey(StyleSheet.RULE);
        StyleContext.registerStaticAttributeKey(StyleSheet.WEIGHT);
        StyleContext.registerStaticAttributeKey(StyleSheet.BOX_PAINTER);
        StyleContext.registerStaticAttributeKey(StyleSheet.LIST_PAINTER);
    }
    
    public StyleSheet() {
        this.selectorMapping = new Hashtable();
        this.resolvedStyles = new Hashtable();
        if (this.css == null) {
            this.css = new CSS();
        }
    }
    
    private String _cleanSelectorString(final String s) {
        final SearchBuffer obtainSearchBuffer = SearchBuffer.obtainSearchBuffer();
        final StringBuffer stringBuffer = obtainSearchBuffer.getStringBuffer();
        int n = 1;
        int n2 = 0;
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        String string = null;
        try {
            for (int i = 0; i < length; ++i) {
                switch (charArray[i]) {
                    case ' ': {
                        if (n == 0) {
                            n = 1;
                            if (n2 < i) {
                                stringBuffer.append(charArray, n2, 1 + i - n2);
                            }
                        }
                        n2 = i + 1;
                        break;
                    }
                    case '\t':
                    case '\n':
                    case '\r': {
                        if (n == 0) {
                            n = 1;
                            if (n2 < i) {
                                stringBuffer.append(charArray, n2, i - n2);
                                stringBuffer.append(' ');
                            }
                        }
                        n2 = i + 1;
                        break;
                    }
                    default: {
                        n = 0;
                        break;
                    }
                }
            }
            if (n != 0 && stringBuffer.length() > 0) {
                stringBuffer.setLength(stringBuffer.length() - 1);
            }
            else if (n2 < length) {
                stringBuffer.append(charArray, n2, length - n2);
            }
            string = stringBuffer.toString();
        }
        finally {
            SearchBuffer.releaseSearchBuffer(obtainSearchBuffer);
        }
        return string;
    }
    
    public AttributeSet addAttribute(final AttributeSet set, final Object o, final Object o2) {
        if (this.css == null) {
            this.css = new CSS();
        }
        if (o instanceof StyleConstants) {
            final Object styleConstantsValueToCSSValue = this.css.styleConstantsValueToCSSValue((StyleConstants)o, o2);
            if (styleConstantsValueToCSSValue != null) {
                final CSS.Attribute styleConstantsKeyToCSSKey = this.css.styleConstantsKeyToCSSKey((StyleConstants)o);
                if (styleConstantsKeyToCSSKey != null) {
                    return super.addAttribute(set, styleConstantsKeyToCSSKey, styleConstantsValueToCSSValue);
                }
            }
        }
        return super.addAttribute(set, o, o2);
    }
    
    public AttributeSet addAttributes(final AttributeSet set, final AttributeSet set2) {
        return super.addAttributes(set, this.convertAttributeSet(set2));
    }
    
    boolean addCSSAttribute(final MutableAttributeSet set, final CSS.Attribute attribute, final String s) {
        final Object internalCSSValue = this.css.getInternalCSSValue(attribute, s);
        if (internalCSSValue != null) {
            set.addAttribute(attribute, internalCSSValue);
            return true;
        }
        return false;
    }
    
    boolean addCSSAttributeFromHTML(final MutableAttributeSet set, final CSS.Attribute attribute, final String s) {
        final Object cssValue = this.css.getCssValue(attribute, s);
        if (cssValue != null) {
            set.addAttribute(attribute, cssValue);
            return true;
        }
        return false;
    }
    
    public void addRule(final String s) {
        if (s != null) {
            final CssParser cssParser = new CssParser();
            try {
                cssParser.parse(this.getBase(), new StringReader(s), false, false);
            }
            catch (IOException ex) {}
        }
    }
    
    void addRule(final String[] array, final AttributeSet set, final boolean b) {
        final int length = array.length;
        final StringBuffer sb = new StringBuffer();
        sb.append(array[0]);
        for (int i = 1; i < length; ++i) {
            sb.append(' ');
            sb.append(array[i]);
        }
        final String string = sb.toString();
        Style style = this.getStyle(string);
        if (style == null) {
            final Style addStyle = this.addStyle(string, null);
            synchronized (this) {
                Object o = this.getRootSelectorMapping();
                for (int j = length - 1; j >= 0; --j) {
                    o = this.getSelectorMapping(o, array[j], true);
                }
                style = this.getMappingStyle(o);
                if (style == null) {
                    style = this.createStyleForSelector(string, o, addStyle);
                    this.refreshResolvedRules(string, array, style, this.getSpecificity(o));
                }
            }
        }
        if (b) {
            style = this.getLinkedStyle(style);
        }
        style.addAttributes(set);
    }
    
    private void addSortedStyle(final Object o, final Vector vector) {
        final int size = vector.size();
        if (size > 0) {
            final int specificity = this.getSpecificity(o);
            for (int i = 0; i < size; ++i) {
                if (specificity >= this.getSpecificity(vector.elementAt(i))) {
                    vector.insertElementAt(o, i);
                    return;
                }
            }
        }
        vector.addElement(o);
    }
    
    synchronized void addStyleSheet(final StyleSheet styleSheet) {
        if (this.linkedStyleSheets == null) {
            this.linkedStyleSheets = new Vector();
        }
        if (!this.linkedStyleSheets.contains(styleSheet)) {
            this.linkedStyleSheets.insertElementAt(styleSheet, 0);
            this.linkStyleSheetAt(styleSheet, 0);
        }
    }
    
    String cleanSelectorString(final String s) {
        int n = 1;
        for (int i = 0; i < s.length(); ++i) {
            switch (s.charAt(i)) {
                case ' ': {
                    if (n != 0) {
                        return this._cleanSelectorString(s);
                    }
                    n = 1;
                    break;
                }
                case '\t':
                case '\n':
                case '\r': {
                    return this._cleanSelectorString(s);
                }
                default: {
                    n = 0;
                    break;
                }
            }
        }
        if (n != 0) {
            return this._cleanSelectorString(s);
        }
        return s;
    }
    
    AttributeSet convertAttributeSet(final AttributeSet set) {
        if (set instanceof LargeConversionSet || set instanceof SmallConversionSet) {
            return set;
        }
        final Enumeration attributeNames = set.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            if (attributeNames.nextElement() instanceof StyleConstants) {
                final LargeConversionSet set2 = new LargeConversionSet();
                final Enumeration attributeNames2 = set.getAttributeNames();
                while (attributeNames2.hasMoreElements()) {
                    final StyleConstants nextElement = attributeNames2.nextElement();
                    Object styleConstantsValueToCSSValue = null;
                    if (nextElement instanceof StyleConstants) {
                        final CSS.Attribute styleConstantsKeyToCSSKey = this.css.styleConstantsKeyToCSSKey(nextElement);
                        if (styleConstantsKeyToCSSKey != null) {
                            styleConstantsValueToCSSValue = this.css.styleConstantsValueToCSSValue(nextElement, set.getAttribute(nextElement));
                            if (styleConstantsValueToCSSValue != null) {
                                set2.addAttribute(styleConstantsKeyToCSSKey, styleConstantsValueToCSSValue);
                            }
                        }
                    }
                    if (styleConstantsValueToCSSValue == null) {
                        set2.addAttribute(nextElement, set.getAttribute(nextElement));
                    }
                }
                return set2;
            }
        }
        return set;
    }
    
    protected MutableAttributeSet createLargeAttributeSet(final AttributeSet set) {
        return new LargeConversionSet(set);
    }
    
    private Style createResolvedStyle(final String s) {
        final SearchBuffer obtainSearchBuffer = SearchBuffer.obtainSearchBuffer();
        final Vector vector = obtainSearchBuffer.getVector();
        try {
            int index = 0;
            int index2 = 0;
            int index3;
            for (int i = 0, length = s.length(); i < length; i = index3 + 1) {
                if (index == i) {
                    index = s.indexOf(46, i);
                }
                if (index2 == i) {
                    index2 = s.indexOf(35, i);
                }
                index3 = s.indexOf(32, i);
                if (index3 == -1) {
                    index3 = length;
                }
                if (index != -1 && index2 != -1 && index < index3 && index2 < index3) {
                    if (index2 < index) {
                        if (i == index2) {
                            vector.addElement("");
                        }
                        else {
                            vector.addElement(s.substring(i, index2));
                        }
                        if (index + 1 < index3) {
                            vector.addElement(s.substring(index + 1, index3));
                        }
                        else {
                            vector.addElement(null);
                        }
                        if (index2 + 1 == index) {
                            vector.addElement(null);
                        }
                        else {
                            vector.addElement(s.substring(index2 + 1, index));
                        }
                    }
                    else if (index2 < index3) {
                        if (i == index) {
                            vector.addElement("");
                        }
                        else {
                            vector.addElement(s.substring(i, index));
                        }
                        if (index + 1 < index2) {
                            vector.addElement(s.substring(index + 1, index2));
                        }
                        else {
                            vector.addElement(null);
                        }
                        if (index2 + 1 == index3) {
                            vector.addElement(null);
                        }
                        else {
                            vector.addElement(s.substring(index2 + 1, index3));
                        }
                    }
                    index2 = (index = index3 + 1);
                }
                else if (index != -1 && index < index3) {
                    if (index == i) {
                        vector.addElement("");
                    }
                    else {
                        vector.addElement(s.substring(i, index));
                    }
                    if (index + 1 == index3) {
                        vector.addElement(null);
                    }
                    else {
                        vector.addElement(s.substring(index + 1, index3));
                    }
                    vector.addElement(null);
                    index = index3 + 1;
                }
                else if (index2 != -1 && index2 < index3) {
                    if (index2 == i) {
                        vector.addElement("");
                    }
                    else {
                        vector.addElement(s.substring(i, index2));
                    }
                    vector.addElement(null);
                    if (index2 + 1 == index3) {
                        vector.addElement(null);
                    }
                    else {
                        vector.addElement(s.substring(index2 + 1, index3));
                    }
                    index2 = index3 + 1;
                }
                else {
                    vector.addElement(s.substring(i, index3));
                    vector.addElement(null);
                    vector.addElement(null);
                }
            }
            final int size = vector.size();
            final int n = size / 3;
            final String[] array = new String[n];
            final String[] array2 = new String[n];
            final String[] array3 = new String[n];
            for (int j = 0, n2 = size - 3; j < n; ++j, n2 -= 3) {
                array[j] = vector.elementAt(n2);
                array2[j] = vector.elementAt(n2 + 1);
                array3[j] = vector.elementAt(n2 + 2);
            }
            return this.createResolvedStyle(s, array, array2, array3);
        }
        finally {
            SearchBuffer.releaseSearchBuffer(obtainSearchBuffer);
        }
    }
    
    private Style createResolvedStyle(final String s, final Vector vector, HTML.Tag p3) {
        final int size = vector.size();
        if (p3 == HTML.Tag.IMPLIED) {
            p3 = HTML.Tag.P;
        }
        final String[] array = new String[size];
        final String[] array2 = new String[size];
        final String[] array3 = new String[size];
        for (int i = 0; i < size; ++i) {
            final AttributeSet attributes = vector.elementAt(i).getAttributes();
            if (attributes != null) {
                Object p4 = attributes.getAttribute(StyleConstants.NameAttribute);
                if (p4 == HTML.Tag.IMPLIED) {
                    p4 = HTML.Tag.P;
                }
                if (p4 != null) {
                    array[i] = ((HTML.Tag)p4).toString();
                }
                else {
                    array[i] = null;
                }
                if (attributes.isDefined(HTML.Attribute.CLASS)) {
                    array3[i] = attributes.getAttribute(HTML.Attribute.CLASS).toString();
                }
                else {
                    array3[i] = null;
                }
                if (attributes.isDefined(HTML.Attribute.ID)) {
                    array2[i] = attributes.getAttribute(HTML.Attribute.ID).toString();
                }
                else {
                    array2[i] = null;
                }
            }
            else {
                final String[] array4 = array;
                final int n = i;
                final String[] array5 = array2;
                final int n2 = i;
                final String[] array6 = array3;
                final int n3 = i;
                final String s2 = null;
                array6[n3] = s2;
                array4[n] = (array5[n2] = s2);
            }
        }
        array[0] = p3.toString();
        return this.createResolvedStyle(s, array, array2, array3);
    }
    
    private synchronized Style createResolvedStyle(final String s, final String[] array, final String[] array2, final String[] array3) {
        final SearchBuffer obtainSearchBuffer = SearchBuffer.obtainSearchBuffer();
        final Vector vector = obtainSearchBuffer.getVector();
        final Hashtable hashtable = obtainSearchBuffer.getHashtable();
        try {
            final Object rootSelectorMapping = this.getRootSelectorMapping();
            final int length = array.length;
            final String s2 = array[0];
            final Object selectorMapping = this.getSelectorMapping(rootSelectorMapping, s2, false);
            if (selectorMapping != null) {
                this.getStyles(selectorMapping, vector, array, array2, array3, 1, length, hashtable);
            }
            if (array3[0] != null) {
                final String s3 = array3[0];
                final Object selectorMapping2 = this.getSelectorMapping(rootSelectorMapping, String.valueOf(s2) + "." + s3, false);
                if (selectorMapping2 != null) {
                    this.getStyles(selectorMapping2, vector, array, array2, array3, 1, length, hashtable);
                }
                final Object selectorMapping3 = this.getSelectorMapping(rootSelectorMapping, "." + s3, false);
                if (selectorMapping3 != null) {
                    this.getStyles(selectorMapping3, vector, array, array2, array3, 1, length, hashtable);
                }
            }
            if (array2[0] != null) {
                final String s4 = array2[0];
                final Object selectorMapping4 = this.getSelectorMapping(rootSelectorMapping, String.valueOf(s2) + "#" + s4, false);
                if (selectorMapping4 != null) {
                    this.getStyles(selectorMapping4, vector, array, array2, array3, 1, length, hashtable);
                }
                final Object selectorMapping5 = this.getSelectorMapping(rootSelectorMapping, "#" + s4, false);
                if (selectorMapping5 != null) {
                    this.getStyles(selectorMapping5, vector, array, array2, array3, 1, length, hashtable);
                }
            }
            final int n = (this.linkedStyleSheets != null) ? this.linkedStyleSheets.size() : 0;
            final int size = vector.size();
            final AttributeSet[] array4 = new AttributeSet[size + n];
            for (int i = 0; i < size; ++i) {
                array4[i] = this.getMappingStyle(vector.elementAt(i));
            }
            for (int j = 0; j < n; ++j) {
                final Style rule = this.linkedStyleSheets.elementAt(j).getRule(s);
                if (rule == null) {
                    array4[j + size] = SimpleAttributeSet.EMPTY;
                }
                else {
                    array4[j + size] = rule;
                }
            }
            final ResolvedStyle resolvedStyle = new ResolvedStyle(s, array4, size);
            this.resolvedStyles.put(s, resolvedStyle);
            return resolvedStyle;
        }
        finally {
            SearchBuffer.releaseSearchBuffer(obtainSearchBuffer);
        }
    }
    
    protected SmallAttributeSet createSmallAttributeSet(final AttributeSet set) {
        return new SmallConversionSet(set);
    }
    
    private synchronized Style createStyleForSelector(final String s, final Object o, final Style style) {
        Style style2 = ((Hashtable)o).get(StyleSheet.RULE);
        if (style2 == null) {
            style2 = style;
            ((Hashtable)o).put(StyleSheet.RULE, style);
        }
        return style2;
    }
    
    public Color getBackground(final AttributeSet set) {
        return this.css.getColor(set, CSS.Attribute.BACKGROUND_COLOR);
    }
    
    URL getBase() {
        return this.base;
    }
    
    public BoxPainter getBoxPainter(final AttributeSet set) {
        return new BoxPainter(set, this.css, this);
    }
    
    public AttributeSet getDeclaration(final String s) {
        if (s == null) {
            return SimpleAttributeSet.EMPTY;
        }
        return new CssParser().parseDeclaration(s);
    }
    
    public Font getFont(final AttributeSet set) {
        return this.css.getFont(this, set, 12);
    }
    
    public Color getForeground(final AttributeSet set) {
        final Color color = this.css.getColor(set, CSS.Attribute.COLOR);
        if (color == null) {
            return Color.black;
        }
        return color;
    }
    
    public static int getIndexOfSize(final float n) {
        return CSS.getIndexOfSize(n);
    }
    
    private Style getLinkedStyle(final Style style) {
        Style addStyle = (Style)style.getResolveParent();
        if (addStyle == null) {
            addStyle = this.addStyle(null, null);
            style.setResolveParent(addStyle);
        }
        return addStyle;
    }
    
    public ListPainter getListPainter(final AttributeSet set) {
        return new ListPainter(set);
    }
    
    private Style getMappingStyle(final Object o) {
        return ((Hashtable)o).get(StyleSheet.RULE);
    }
    
    public float getPointSize(final int n) {
        return this.css.getPointSize(n);
    }
    
    public float getPointSize(final String s) {
        return this.css.getPointSize(s);
    }
    
    private synchronized Style getResolvedStyle(final String s) {
        Style resolvedStyle = this.resolvedStyles.get(s);
        if (resolvedStyle == null) {
            resolvedStyle = this.createResolvedStyle(s);
        }
        return resolvedStyle;
    }
    
    private synchronized Style getResolvedStyle(final String s, final Vector vector, final HTML.Tag tag) {
        Style resolvedStyle = this.resolvedStyles.get(s);
        if (resolvedStyle == null) {
            resolvedStyle = this.createResolvedStyle(s, vector, tag);
        }
        return resolvedStyle;
    }
    
    private Object getRootSelectorMapping() {
        return this.selectorMapping;
    }
    
    public Style getRule(String cleanSelectorString) {
        cleanSelectorString = this.cleanSelectorString(cleanSelectorString);
        if (cleanSelectorString != null) {
            return this.getResolvedStyle(cleanSelectorString);
        }
        return null;
    }
    
    public Style getRule(HTML.Tag p2, Element element) {
        final SearchBuffer obtainSearchBuffer = SearchBuffer.obtainSearchBuffer();
        try {
            final Vector vector = obtainSearchBuffer.getVector();
            for (Element parentElement = element; parentElement != null; parentElement = parentElement.getParentElement()) {
                vector.addElement(parentElement);
            }
            final int size = vector.size();
            final StringBuffer stringBuffer = obtainSearchBuffer.getStringBuffer();
            for (int i = size - 1; i >= 1; --i) {
                element = vector.elementAt(i);
                final AttributeSet attributes = element.getAttributes();
                final Object attribute = attributes.getAttribute(StyleConstants.NameAttribute);
                String s;
                if (attribute instanceof HTML.Tag) {
                    if (attribute == HTML.Tag.IMPLIED) {
                        s = HTML.Tag.P.toString();
                    }
                    else {
                        s = attribute.toString();
                    }
                }
                else {
                    s = attribute.toString();
                }
                stringBuffer.append(s);
                if (attributes != null) {
                    if (attributes.isDefined(HTML.Attribute.ID)) {
                        stringBuffer.append('#');
                        stringBuffer.append(attributes.getAttribute(HTML.Attribute.ID));
                    }
                    else if (attributes.isDefined(HTML.Attribute.CLASS)) {
                        stringBuffer.append('.');
                        stringBuffer.append(attributes.getAttribute(HTML.Attribute.CLASS));
                    }
                }
                stringBuffer.append(' ');
            }
            if (p2 == HTML.Tag.IMPLIED) {
                p2 = HTML.Tag.P;
            }
            stringBuffer.append(p2.toString());
            element = vector.elementAt(0);
            final AttributeSet attributes2 = element.getAttributes();
            if (attributes2 != null) {
                if (attributes2.isDefined(HTML.Attribute.ID)) {
                    stringBuffer.append('#');
                    stringBuffer.append(attributes2.getAttribute(HTML.Attribute.ID));
                }
                else if (attributes2.isDefined(HTML.Attribute.CLASS)) {
                    stringBuffer.append('.');
                    stringBuffer.append(attributes2.getAttribute(HTML.Attribute.CLASS));
                }
            }
            return this.getResolvedStyle(stringBuffer.toString(), vector, p2);
        }
        finally {
            SearchBuffer.releaseSearchBuffer(obtainSearchBuffer);
        }
    }
    
    private synchronized Object getSelectorMapping(final Object o, final String s, final boolean b) {
        Map<K, V> map = (Map<K, V>)((Hashtable)o).get(s);
        if (map == null && b) {
            map = (Map<K, V>)new Hashtable<SheetAttribute, Integer>(7);
            ((Hashtable)o).put(s, map);
            int intValue = 0;
            if (o != null) {
                final Integer value = ((Hashtable)o).get(StyleSheet.SPECIFICITY);
                if (value != null) {
                    intValue = value;
                }
            }
            final char char1 = s.charAt(0);
            if (char1 == '.') {
                intValue += 100;
            }
            else if (char1 == '#') {
                intValue += 10000;
            }
            else {
                ++intValue;
                if (s.indexOf(46) != -1) {
                    intValue += 100;
                }
                if (s.indexOf(35) != -1) {
                    intValue += 10000;
                }
            }
            ((Hashtable<SheetAttribute, Integer>)map).put(StyleSheet.SPECIFICITY, new Integer(intValue));
        }
        return map;
    }
    
    String[] getSimpleSelectors(String cleanSelectorString) {
        cleanSelectorString = this.cleanSelectorString(cleanSelectorString);
        final SearchBuffer obtainSearchBuffer = SearchBuffer.obtainSearchBuffer();
        final Vector vector = obtainSearchBuffer.getVector();
        int i = 0;
        final int length = cleanSelectorString.length();
        while (i != -1) {
            int index = cleanSelectorString.indexOf(32, i);
            if (index != -1) {
                vector.addElement(cleanSelectorString.substring(i, index));
                if (++index == length) {
                    i = -1;
                }
                else {
                    i = index;
                }
            }
            else {
                vector.addElement(cleanSelectorString.substring(i));
                i = -1;
            }
        }
        final String[] array = new String[vector.size()];
        vector.copyInto(array);
        SearchBuffer.releaseSearchBuffer(obtainSearchBuffer);
        return array;
    }
    
    private int getSpecificity(final Object o) {
        final Integer value = ((Hashtable)o).get(StyleSheet.SPECIFICITY);
        if (value != null) {
            return value;
        }
        return 0;
    }
    
    static int getSpecificity(final String s) {
        int n = 0;
        int n2 = 1;
        for (int i = 0; i < s.length(); ++i) {
            switch (s.charAt(i)) {
                case '.': {
                    n += 100;
                    break;
                }
                case '#': {
                    n += 10000;
                    break;
                }
                case ' ': {
                    n2 = 1;
                    break;
                }
                default: {
                    if (n2 != 0) {
                        n2 = 0;
                        ++n;
                        break;
                    }
                    break;
                }
            }
        }
        return n;
    }
    
    synchronized Enumeration getStyleSheets() {
        if (this.linkedStyleSheets == null) {
            return null;
        }
        return this.linkedStyleSheets.elements();
    }
    
    private synchronized void getStyles(final Object o, final Vector vector, final String[] array, final String[] array2, final String[] array3, final int n, final int n2, final Hashtable hashtable) {
        if (hashtable.contains(o)) {
            return;
        }
        hashtable.put(o, o);
        if (this.getMappingStyle(o) != null) {
            this.addSortedStyle(o, vector);
        }
        for (int i = n; i < n2; ++i) {
            final String s = array[i];
            if (s != null) {
                final Object selectorMapping = this.getSelectorMapping(o, s, false);
                if (selectorMapping != null) {
                    this.getStyles(selectorMapping, vector, array, array2, array3, i + 1, n2, hashtable);
                }
                if (array3[i] != null) {
                    final String s2 = array3[i];
                    final Object selectorMapping2 = this.getSelectorMapping(o, String.valueOf(s) + "." + s2, false);
                    if (selectorMapping2 != null) {
                        this.getStyles(selectorMapping2, vector, array, array2, array3, i + 1, n2, hashtable);
                    }
                    final Object selectorMapping3 = this.getSelectorMapping(o, "." + s2, false);
                    if (selectorMapping3 != null) {
                        this.getStyles(selectorMapping3, vector, array, array2, array3, i + 1, n2, hashtable);
                    }
                }
                if (array2[i] != null) {
                    final String s3 = array2[i];
                    final Object selectorMapping4 = this.getSelectorMapping(o, String.valueOf(s) + "#" + s3, false);
                    if (selectorMapping4 != null) {
                        this.getStyles(selectorMapping4, vector, array, array2, array3, i + 1, n2, hashtable);
                    }
                    final Object selectorMapping5 = this.getSelectorMapping(o, "#" + s3, false);
                    if (selectorMapping5 != null) {
                        this.getStyles(selectorMapping5, vector, array, array2, array3, i + 1, n2, hashtable);
                    }
                }
            }
        }
    }
    
    public AttributeSet getViewAttributes(final View view) {
        return new ViewAttributeSet(view);
    }
    
    void importStyleSheet(final URL url) {
        try {
            final InputStream openStream = url.openStream();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openStream));
            new CssParser().parse(url, bufferedReader, false, true);
            bufferedReader.close();
            openStream.close();
        }
        catch (Throwable t) {}
    }
    
    private synchronized void linkStyleSheetAt(final StyleSheet styleSheet, final int n) {
        if (this.resolvedStyles.size() > 0) {
            final Enumeration<ResolvedStyle> elements = this.resolvedStyles.elements();
            while (elements.hasMoreElements()) {
                final ResolvedStyle resolvedStyle = elements.nextElement();
                resolvedStyle.insertExtendedStyleAt(styleSheet.getRule(resolvedStyle.getName()), n);
            }
        }
    }
    
    public void loadRules(final Reader reader, final URL url) throws IOException {
        new CssParser().parse(url, reader, false, false);
    }
    
    private synchronized void refreshResolvedRules(final String s, final String[] array, final Style style, final int n) {
        if (this.resolvedStyles.size() > 0) {
            final Enumeration<ResolvedStyle> elements = this.resolvedStyles.elements();
            while (elements.hasMoreElements()) {
                final ResolvedStyle resolvedStyle = elements.nextElement();
                if (resolvedStyle.matches(s)) {
                    resolvedStyle.insertStyle(style, n);
                }
            }
        }
    }
    
    public AttributeSet removeAttribute(final AttributeSet set, final Object o) {
        if (o instanceof StyleConstants) {
            final CSS.Attribute styleConstantsKeyToCSSKey = this.css.styleConstantsKeyToCSSKey((StyleConstants)o);
            if (styleConstantsKeyToCSSKey != null) {
                return super.removeAttribute(set, styleConstantsKeyToCSSKey);
            }
        }
        return super.removeAttribute(set, o);
    }
    
    public AttributeSet removeAttributes(final AttributeSet set, final Enumeration enumeration) {
        return super.removeAttributes(set, enumeration);
    }
    
    public AttributeSet removeAttributes(final AttributeSet set, final AttributeSet set2) {
        return super.removeAttributes(set, this.convertAttributeSet(set2));
    }
    
    private void removeMappingStyle(final Object o) {
        ((Hashtable)o).remove(StyleSheet.RULE);
    }
    
    public void removeStyle(final String s) {
        if (this.getStyle(s) != null) {
            final String[] simpleSelectors = this.getSimpleSelectors(this.cleanSelectorString(s));
            synchronized (this) {
                Object o = this.getRootSelectorMapping();
                for (int i = simpleSelectors.length - 1; i >= 0; --i) {
                    o = this.getSelectorMapping(o, simpleSelectors[i], true);
                }
                final Style mappingStyle = this.getMappingStyle(o);
                if (mappingStyle != null) {
                    this.removeMappingStyle(o);
                    if (this.resolvedStyles.size() > 0) {
                        final Enumeration<ResolvedStyle> elements = this.resolvedStyles.elements();
                        while (elements.hasMoreElements()) {
                            elements.nextElement().removeStyle(mappingStyle);
                        }
                    }
                }
            }
        }
        super.removeStyle(s);
    }
    
    synchronized void removeStyleSheet(final StyleSheet styleSheet) {
        if (this.linkedStyleSheets != null) {
            final int index = this.linkedStyleSheets.indexOf(styleSheet);
            if (index != -1) {
                this.linkedStyleSheets.removeElementAt(index);
                this.unlinkStyleSheet(styleSheet, index);
                if (index == 0 && this.linkedStyleSheets.size() == 0) {
                    this.linkedStyleSheets = null;
                }
            }
        }
    }
    
    void setBase(final URL base) {
        this.base = base;
    }
    
    public void setBaseFontSize(final int baseFontSize) {
        this.css.setBaseFontSize(baseFontSize);
    }
    
    public void setBaseFontSize(final String baseFontSize) {
        this.css.setBaseFontSize(baseFontSize);
    }
    
    public Color stringToColor(final String s) {
        return CSS.stringToColor(s);
    }
    
    public AttributeSet translateHTMLToCSS(final AttributeSet set) {
        final AttributeSet translateHTMLToCSS = this.css.translateHTMLToCSS(set);
        final Style addStyle = this.addStyle(null, null);
        addStyle.addAttributes(translateHTMLToCSS);
        return addStyle;
    }
    
    private synchronized void unlinkStyleSheet(final StyleSheet styleSheet, final int n) {
        if (this.resolvedStyles.size() > 0) {
            final Enumeration<ResolvedStyle> elements = this.resolvedStyles.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().removeExtendedStyleAt(n);
            }
        }
    }
    
    class LargeConversionSet extends SimpleAttributeSet
    {
        public LargeConversionSet() {
        }
        
        public LargeConversionSet(final AttributeSet set) {
            super(set);
        }
        
        public Object getAttribute(final Object o) {
            if (o instanceof StyleConstants) {
                final CSS.Attribute styleConstantsKeyToCSSKey = StyleSheet.this.css.styleConstantsKeyToCSSKey((StyleConstants)o);
                if (styleConstantsKeyToCSSKey != null) {
                    final Object attribute = super.getAttribute(styleConstantsKeyToCSSKey);
                    if (attribute != null) {
                        return StyleSheet.this.css.cssValueToStyleConstantsValue((StyleConstants)o, attribute);
                    }
                }
            }
            return super.getAttribute(o);
        }
        
        public boolean isDefined(final Object o) {
            if (o instanceof StyleConstants) {
                final CSS.Attribute styleConstantsKeyToCSSKey = StyleSheet.this.css.styleConstantsKeyToCSSKey((StyleConstants)o);
                if (styleConstantsKeyToCSSKey != null) {
                    return super.isDefined(styleConstantsKeyToCSSKey);
                }
            }
            return super.isDefined(o);
        }
    }
    
    class SmallConversionSet extends SmallAttributeSet
    {
        public SmallConversionSet(final AttributeSet set) {
            super(set);
        }
        
        public Object getAttribute(final Object o) {
            if (o instanceof StyleConstants) {
                final CSS.Attribute styleConstantsKeyToCSSKey = StyleSheet.this.css.styleConstantsKeyToCSSKey((StyleConstants)o);
                if (styleConstantsKeyToCSSKey != null) {
                    final Object attribute = super.getAttribute(styleConstantsKeyToCSSKey);
                    if (attribute != null) {
                        return StyleSheet.this.css.cssValueToStyleConstantsValue((StyleConstants)o, attribute);
                    }
                }
            }
            return super.getAttribute(o);
        }
        
        public boolean isDefined(final Object o) {
            if (o instanceof StyleConstants) {
                final CSS.Attribute styleConstantsKeyToCSSKey = StyleSheet.this.css.styleConstantsKeyToCSSKey((StyleConstants)o);
                if (styleConstantsKeyToCSSKey != null) {
                    return super.isDefined(styleConstantsKeyToCSSKey);
                }
            }
            return super.isDefined(o);
        }
    }
    
    private static class SearchBuffer
    {
        static Stack searchBuffers;
        Vector vector;
        StringBuffer stringBuffer;
        Hashtable hashtable;
        
        static {
            SearchBuffer.searchBuffers = new Stack();
        }
        
        SearchBuffer() {
            this.vector = null;
            this.stringBuffer = null;
            this.hashtable = null;
        }
        
        void empty() {
            if (this.stringBuffer != null) {
                this.stringBuffer.setLength(0);
            }
            if (this.vector != null) {
                this.vector.removeAllElements();
            }
            if (this.hashtable != null) {
                this.hashtable.clear();
            }
        }
        
        Hashtable getHashtable() {
            if (this.hashtable == null) {
                this.hashtable = new Hashtable();
            }
            return this.hashtable;
        }
        
        StringBuffer getStringBuffer() {
            if (this.stringBuffer == null) {
                this.stringBuffer = new StringBuffer();
            }
            return this.stringBuffer;
        }
        
        Vector getVector() {
            if (this.vector == null) {
                this.vector = new Vector();
            }
            return this.vector;
        }
        
        static SearchBuffer obtainSearchBuffer() {
            SearchBuffer searchBuffer;
            try {
                searchBuffer = SearchBuffer.searchBuffers.pop();
            }
            catch (EmptyStackException ex) {
                searchBuffer = new SearchBuffer();
            }
            return searchBuffer;
        }
        
        static void releaseSearchBuffer(final SearchBuffer searchBuffer) {
            searchBuffer.empty();
            SearchBuffer.searchBuffers.push(searchBuffer);
        }
    }
    
    public static class BoxPainter implements Serializable
    {
        Border border;
        Insets binsets;
        CSS css;
        StyleSheet ss;
        
        BoxPainter(final AttributeSet set, final CSS css, final StyleSheet ss) {
            this.ss = ss;
            this.css = css;
            this.border = this.getBorder(set);
            this.binsets = this.border.getBorderInsets(null);
        }
        
        Border getBorder(final AttributeSet set) {
            Border noBorder = StyleSheet.noBorder;
            final Object attribute = set.getAttribute(CSS.Attribute.BORDER_STYLE);
            if (attribute != null) {
                final String string = attribute.toString();
                if ((int)this.getLength(CSS.Attribute.BORDER_WIDTH, set) > 0) {
                    if (string.equals("inset")) {
                        final Color borderColor = this.getBorderColor(set);
                        noBorder = new BevelBorder(1, borderColor.brighter(), borderColor.darker());
                    }
                    else if (string.equals("outset")) {
                        final Color borderColor2 = this.getBorderColor(set);
                        noBorder = new BevelBorder(0, borderColor2.brighter(), borderColor2.darker());
                    }
                    else if (string.equals("solid")) {
                        noBorder = new LineBorder(this.getBorderColor(set));
                    }
                }
            }
            return noBorder;
        }
        
        Color getBorderColor(final AttributeSet set) {
            Color color = this.css.getColor(set, CSS.Attribute.BORDER_COLOR);
            if (color == null) {
                color = this.css.getColor(set, CSS.Attribute.COLOR);
                if (color == null) {
                    return Color.black;
                }
            }
            return color;
        }
        
        public float getInset(final int n, final View view) {
            final AttributeSet attributes = view.getAttributes();
            final float n2 = 0.0f;
            float n3 = 0.0f;
            switch (n) {
                case 2: {
                    n3 = n2 + this.getLength(CSS.Attribute.MARGIN_LEFT, attributes) + this.binsets.left + this.getLength(CSS.Attribute.PADDING_LEFT, attributes);
                    break;
                }
                case 4: {
                    n3 = n2 + this.getLength(CSS.Attribute.MARGIN_RIGHT, attributes) + this.binsets.right + this.getLength(CSS.Attribute.PADDING_RIGHT, attributes);
                    break;
                }
                case 1: {
                    n3 = n2 + this.getLength(CSS.Attribute.MARGIN_TOP, attributes) + this.binsets.top + this.getLength(CSS.Attribute.PADDING_TOP, attributes);
                    break;
                }
                case 3: {
                    n3 = n2 + this.getLength(CSS.Attribute.MARGIN_BOTTOM, attributes) + this.binsets.bottom + this.getLength(CSS.Attribute.PADDING_BOTTOM, attributes);
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Invalid side: " + n);
                }
            }
            return n3;
        }
        
        float getLength(final CSS.Attribute attribute, final AttributeSet set) {
            return this.css.getLength(set, attribute);
        }
        
        public void paint(final Graphics graphics, float n, float n2, float n3, float n4, final View view) {
            final AttributeSet attributes = view.getAttributes();
            final StyleSheet ss = this.ss;
            final float length = this.getLength(CSS.Attribute.MARGIN_TOP, attributes);
            final float length2 = this.getLength(CSS.Attribute.MARGIN_LEFT, attributes);
            n += length2;
            n2 += length;
            n3 -= length2 + this.getLength(CSS.Attribute.MARGIN_RIGHT, attributes);
            n4 -= length + this.getLength(CSS.Attribute.MARGIN_BOTTOM, attributes);
            Color background;
            if (ss != null) {
                background = ss.getBackground(attributes);
            }
            else {
                background = null;
            }
            if (background != null) {
                graphics.setColor(background);
                graphics.fillRect((int)n, (int)n2, (int)n3, (int)n4);
            }
            this.border.paintBorder(null, graphics, (int)n, (int)n2, (int)n3, (int)n4);
        }
    }
    
    public static class ListPainter implements Serializable
    {
        static final char[][] romanChars;
        private int start;
        private CSS.Value type;
        URL imageurl;
        Icon img;
        private int bulletgap;
        
        static {
            romanChars = new char[][] { { 'i', 'v' }, { 'x', 'l' }, { 'c', 'd' }, { 'm', '?' } };
        }
        
        ListPainter(final AttributeSet set) {
            this.img = null;
            this.bulletgap = 5;
            final String s = (String)set.getAttribute(CSS.Attribute.LIST_STYLE_IMAGE);
            this.type = null;
            if (s != null && !s.equals("none")) {
                try {
                    String s2 = null;
                    final StringTokenizer stringTokenizer = new StringTokenizer(s, "()");
                    if (stringTokenizer.hasMoreTokens()) {
                        s2 = stringTokenizer.nextToken();
                    }
                    if (stringTokenizer.hasMoreTokens()) {
                        s2 = stringTokenizer.nextToken();
                    }
                    this.img = new ImageIcon(new URL(s2));
                }
                catch (MalformedURLException ex) {
                    this.type = null;
                    this.img = null;
                }
            }
            if (this.img == null) {
                this.type = (CSS.Value)set.getAttribute(CSS.Attribute.LIST_STYLE_TYPE);
            }
            this.start = 1;
        }
        
        void drawIcon(final Graphics graphics, final int n, final int n2, final int n3, final float n4, final Component component) {
            graphics.setColor(Color.black);
            this.img.paintIcon(component, graphics, n - this.img.getIconWidth() - this.bulletgap, n2 + (int)(n3 * n4) - 3);
        }
        
        void drawLetter(final Graphics graphics, final char c, final int n, final int n2, final int n3, final int n4) {
            graphics.setColor(Color.black);
            final String string = String.valueOf(this.formatItemNum(n4, c)) + ".";
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.drawString(string, n - fontMetrics.stringWidth(string) - this.bulletgap, n2 + fontMetrics.getAscent() + fontMetrics.getLeading());
        }
        
        void drawShape(final Graphics graphics, final CSS.Value value, final int n, final int n2, final int n3, final float n4) {
            graphics.setColor(Color.black);
            final int n5 = n - this.bulletgap - 7;
            final int n6 = n2 + (int)(n3 * n4) - 3;
            if (value == CSS.Value.SQUARE) {
                graphics.drawRect(n5, n6, 7, 7);
            }
            else if (value == CSS.Value.CIRCLE) {
                graphics.drawOval(n5, n6, 7, 7);
            }
            else {
                graphics.fillOval(n5, n6, 7, 7);
            }
        }
        
        String formatAlphaNumerals(final int n) {
            String s;
            if (n > 26) {
                s = String.valueOf(this.formatAlphaNumerals(n / 26)) + this.formatAlphaNumerals(n % 26);
            }
            else {
                s = String.valueOf((char)(97 + n - 1));
            }
            return s;
        }
        
        String formatItemNum(final int n, final char c) {
            boolean b = false;
            String s = null;
            switch (c) {
                default: {
                    s = String.valueOf(n);
                    break;
                }
                case 'A': {
                    b = true;
                }
                case 'a': {
                    s = this.formatAlphaNumerals(n);
                    break;
                }
                case 'I': {
                    b = true;
                }
                case 'i': {
                    s = this.formatRomanNumerals(n);
                    break;
                }
            }
            if (b) {
                s = s.toUpperCase();
            }
            return s;
        }
        
        String formatRomanDigit(final int n, int n2) {
            String s = "";
            if (n2 == 9) {
                return String.valueOf(new StringBuffer(String.valueOf(s)).append(ListPainter.romanChars[n][0]).toString()) + ListPainter.romanChars[n + 1][0];
            }
            if (n2 == 4) {
                return String.valueOf(new StringBuffer(String.valueOf(s)).append(ListPainter.romanChars[n][0]).toString()) + ListPainter.romanChars[n][1];
            }
            if (n2 >= 5) {
                s = String.valueOf(s) + ListPainter.romanChars[n][1];
                n2 -= 5;
            }
            for (int i = 0; i < n2; ++i) {
                s = String.valueOf(s) + ListPainter.romanChars[n][0];
            }
            return s;
        }
        
        String formatRomanNumerals(final int n) {
            return this.formatRomanNumerals(0, n);
        }
        
        String formatRomanNumerals(final int n, final int n2) {
            if (n2 < 10) {
                return this.formatRomanDigit(n, n2);
            }
            return String.valueOf(this.formatRomanNumerals(n + 1, n2 / 10)) + this.formatRomanDigit(n, n2 % 10);
        }
        
        private CSS.Value getChildType(final View view) {
            Object o = view.getAttributes().getAttribute(CSS.Attribute.LIST_STYLE_TYPE);
            if (o == null) {
                if (this.type == null) {
                    final View parent = view.getParent();
                    if (((HTMLDocument)parent.getDocument()).matchNameAttribute(parent.getElement().getAttributes(), HTML.Tag.OL)) {
                        o = CSS.Value.DECIMAL;
                    }
                    else {
                        o = CSS.Value.DISC;
                    }
                }
                else {
                    o = this.type;
                }
            }
            return (CSS.Value)o;
        }
        
        private int getRenderIndex(final View view, final int n) {
            int n2 = n;
            for (int i = n; i >= 0; --i) {
                final AttributeSet attributes = view.getElement().getElement(i).getAttributes();
                if (attributes.getAttribute(StyleConstants.NameAttribute) != HTML.Tag.LI) {
                    --n2;
                }
                else {
                    final Object attribute = attributes.getAttribute(HTML.Attribute.VALUE);
                    if (attribute != null && attribute instanceof String) {
                        try {
                            return n2 - i + Integer.parseInt((String)attribute);
                        }
                        catch (NumberFormatException ex) {}
                    }
                }
            }
            return n2 + this.start;
        }
        
        public void paint(final Graphics graphics, final float n, final float n2, final float n3, final float n4, final View view, final int n5) {
            final View view2 = view.getView(n5);
            final Object attribute = view2.getElement().getAttributes().getAttribute(StyleConstants.NameAttribute);
            if (!(attribute instanceof HTML.Tag) || attribute != HTML.Tag.LI) {
                return;
            }
            final CSS.Value childType = this.getChildType(view2);
            final float alignment = view2.getAlignment(1);
            final Font font = ((StyledDocument)view2.getDocument()).getFont(view2.getAttributes());
            if (font != null) {
                graphics.setFont(font);
            }
            if (childType == CSS.Value.SQUARE || childType == CSS.Value.CIRCLE || childType == CSS.Value.DISC) {
                this.drawShape(graphics, childType, (int)n, (int)n2, (int)n4, alignment);
            }
            else if (childType == CSS.Value.CIRCLE) {
                this.drawShape(graphics, childType, (int)n, (int)n2, (int)n4, alignment);
            }
            else if (childType == CSS.Value.DECIMAL) {
                this.drawLetter(graphics, '1', (int)n, (int)n2, (int)n4, this.getRenderIndex(view, n5));
            }
            else if (childType == CSS.Value.LOWER_ALPHA) {
                this.drawLetter(graphics, 'a', (int)n, (int)n2, (int)n4, this.getRenderIndex(view, n5));
            }
            else if (childType == CSS.Value.UPPER_ALPHA) {
                this.drawLetter(graphics, 'A', (int)n, (int)n2, (int)n4, this.getRenderIndex(view, n5));
            }
            else if (childType == CSS.Value.LOWER_ROMAN) {
                this.drawLetter(graphics, 'i', (int)n, (int)n2, (int)n4, this.getRenderIndex(view, n5));
            }
            else if (childType == CSS.Value.UPPER_ROMAN) {
                this.drawLetter(graphics, 'I', (int)n, (int)n2, (int)n4, this.getRenderIndex(view, n5));
            }
            else if (childType == null && this.img != null) {
                this.drawIcon(graphics, (int)n, (int)n2, (int)n4, alignment, view.getContainer());
            }
        }
    }
    
    static class MuxingAttributeSet implements AttributeSet
    {
        AttributeSet[] attrs;
        
        MuxingAttributeSet() {
        }
        
        MuxingAttributeSet(final AttributeSet[] attrs) {
            this.attrs = attrs;
        }
        
        public boolean containsAttribute(final Object o, final Object o2) {
            return o2.equals(this.getAttribute(o));
        }
        
        public boolean containsAttributes(final AttributeSet set) {
            boolean equals = true;
            Object nextElement;
            for (Enumeration attributeNames = set.getAttributeNames(); equals && attributeNames.hasMoreElements(); equals = set.getAttribute(nextElement).equals(this.getAttribute(nextElement))) {
                nextElement = attributeNames.nextElement();
            }
            return equals;
        }
        
        public AttributeSet copyAttributes() {
            final AttributeSet[] attributes = this.getAttributes();
            final SimpleAttributeSet set = new SimpleAttributeSet();
            for (int i = attributes.length - 1; i >= 0; --i) {
                set.addAttributes(attributes[i]);
            }
            return set;
        }
        
        public Object getAttribute(final Object o) {
            final AttributeSet[] attributes = this.getAttributes();
            for (int length = attributes.length, i = 0; i < length; ++i) {
                final Object attribute = attributes[i].getAttribute(o);
                if (attribute != null) {
                    return attribute;
                }
            }
            return null;
        }
        
        public int getAttributeCount() {
            final AttributeSet[] attributes = this.getAttributes();
            int n = 0;
            for (int i = 0; i < attributes.length; ++i) {
                n += attributes[i].getAttributeCount();
            }
            return n;
        }
        
        public Enumeration getAttributeNames() {
            return new MuxingAttributeNameEnumeration();
        }
        
        protected synchronized AttributeSet[] getAttributes() {
            return this.attrs;
        }
        
        public AttributeSet getResolveParent() {
            return null;
        }
        
        protected synchronized void insertAttributeSetAt(final AttributeSet set, final int n) {
            final int length = this.attrs.length;
            final AttributeSet[] attrs = new AttributeSet[length + 1];
            if (n < length) {
                if (n > 0) {
                    System.arraycopy(this.attrs, 0, attrs, 0, n);
                    System.arraycopy(this.attrs, n, attrs, n + 1, length - n);
                }
                else {
                    System.arraycopy(this.attrs, 0, attrs, 1, length);
                }
            }
            else {
                System.arraycopy(this.attrs, 0, attrs, 0, length);
            }
            attrs[n] = set;
            this.attrs = attrs;
        }
        
        public boolean isDefined(final Object o) {
            final AttributeSet[] attributes = this.getAttributes();
            for (int i = 0; i < attributes.length; ++i) {
                if (attributes[i].isDefined(o)) {
                    return true;
                }
            }
            return false;
        }
        
        public boolean isEqual(final AttributeSet set) {
            return this.getAttributeCount() == set.getAttributeCount() && this.containsAttributes(set);
        }
        
        protected synchronized void removeAttributeSetAt(final int n) {
            final int length = this.attrs.length;
            final AttributeSet[] attrs = new AttributeSet[length - 1];
            if (length > 0) {
                if (n == 0) {
                    System.arraycopy(this.attrs, 1, attrs, 0, length - 1);
                }
                else if (n < length - 1) {
                    System.arraycopy(this.attrs, 0, attrs, 0, n);
                    System.arraycopy(this.attrs, n + 1, attrs, n, length - n - 1);
                }
                else {
                    System.arraycopy(this.attrs, 0, attrs, 0, length - 1);
                }
            }
            this.attrs = attrs;
        }
        
        protected synchronized void setAttributes(final AttributeSet[] attrs) {
            this.attrs = attrs;
        }
        
        class MuxingAttributeNameEnumeration implements Enumeration
        {
            int attrIndex;
            Enumeration currentEnum;
            
            MuxingAttributeNameEnumeration() {
                this.updateEnum();
            }
            
            public boolean hasMoreElements() {
                return this.currentEnum != null && this.currentEnum.hasMoreElements();
            }
            
            public Object nextElement() {
                if (this.currentEnum == null) {
                    throw new NoSuchElementException("No more names");
                }
                final Object nextElement = this.currentEnum.nextElement();
                if (!this.currentEnum.hasMoreElements()) {
                    this.updateEnum();
                }
                return nextElement;
            }
            
            void updateEnum() {
                final AttributeSet[] attributes = MuxingAttributeSet.this.getAttributes();
                this.currentEnum = null;
                while (this.currentEnum == null && this.attrIndex < attributes.length) {
                    this.currentEnum = attributes[this.attrIndex++].getAttributeNames();
                    if (!this.currentEnum.hasMoreElements()) {
                        this.currentEnum = null;
                    }
                }
            }
        }
    }
    
    class ViewAttributeSet extends MuxingAttributeSet
    {
        View host;
        
        ViewAttributeSet(final View host) {
            this.host = host;
            final Document document = host.getDocument();
            final SearchBuffer obtainSearchBuffer = SearchBuffer.obtainSearchBuffer();
            final Vector vector = obtainSearchBuffer.getVector();
            try {
                if (document instanceof HTMLDocument) {
                    final Element element = host.getElement();
                    final AttributeSet attributes = element.getAttributes();
                    final AttributeSet translateHTMLToCSS = StyleSheet.this.translateHTMLToCSS(attributes);
                    if (translateHTMLToCSS.getAttributeCount() != 0) {
                        vector.addElement(translateHTMLToCSS);
                    }
                    if (element.isLeaf()) {
                        final Enumeration attributeNames = attributes.getAttributeNames();
                        while (attributeNames.hasMoreElements()) {
                            final HTML.Tag nextElement = attributeNames.nextElement();
                            if (nextElement instanceof HTML.Tag) {
                                if ((HTML.Tag)nextElement == HTML.Tag.A) {
                                    final Object attribute = attributes.getAttribute(nextElement);
                                    if (attribute != null && attribute instanceof AttributeSet && ((AttributeSet)attribute).getAttribute(HTML.Attribute.HREF) == null) {
                                        continue;
                                    }
                                }
                                final Style rule = StyleSheet.this.getRule(nextElement, element);
                                if (rule == null) {
                                    continue;
                                }
                                vector.addElement(rule);
                            }
                        }
                    }
                    else {
                        Object p2 = attributes.getAttribute(StyleConstants.NameAttribute);
                        if (p2 == HTML.Tag.IMPLIED) {
                            p2 = HTML.Tag.P;
                        }
                        final Style rule2 = StyleSheet.this.getRule((HTML.Tag)p2, element);
                        if (rule2 != null) {
                            vector.addElement(rule2);
                        }
                    }
                }
                vector.copyInto(super.attrs = new AttributeSet[vector.size()]);
            }
            finally {
                SearchBuffer.releaseSearchBuffer(obtainSearchBuffer);
            }
        }
        
        Object doGetAttribute(final Object o) {
            final Object attribute = super.getAttribute(o);
            if (attribute != null) {
                return attribute;
            }
            if (o instanceof CSS.Attribute && ((CSS.Attribute)o).isInherited()) {
                final AttributeSet resolveParent = this.getResolveParent();
                if (resolveParent != null) {
                    return resolveParent.getAttribute(o);
                }
            }
            return null;
        }
        
        public Object getAttribute(final Object o) {
            if (o instanceof StyleConstants) {
                final CSS.Attribute styleConstantsKeyToCSSKey = StyleSheet.this.css.styleConstantsKeyToCSSKey((StyleConstants)o);
                if (styleConstantsKeyToCSSKey != null) {
                    final Object doGetAttribute = this.doGetAttribute(styleConstantsKeyToCSSKey);
                    if (doGetAttribute instanceof CSS.CssValue) {
                        return ((CSS.CssValue)doGetAttribute).toStyleConstants((StyleConstants)o);
                    }
                }
            }
            return this.doGetAttribute(o);
        }
        
        public AttributeSet getResolveParent() {
            if (this.host == null) {
                return null;
            }
            final View parent = this.host.getParent();
            return (parent != null) ? parent.getAttributes() : null;
        }
        
        public boolean isDefined(Object o) {
            if (o instanceof StyleConstants) {
                final Object styleConstantsKeyToCSSKey = StyleSheet.this.css.styleConstantsKeyToCSSKey((StyleConstants)o);
                if (styleConstantsKeyToCSSKey != null) {
                    o = styleConstantsKeyToCSSKey;
                }
            }
            return super.isDefined(o);
        }
    }
    
    static class ResolvedStyle extends MuxingAttributeSet implements Style
    {
        String name;
        private int extendedIndex;
        
        ResolvedStyle(final String name, final AttributeSet[] attrs, final int extendedIndex) {
            super.attrs = attrs;
            this.name = name;
            this.extendedIndex = extendedIndex;
        }
        
        public void addAttribute(final Object o, final Object o2) {
        }
        
        public void addAttributes(final AttributeSet set) {
        }
        
        public void addChangeListener(final ChangeListener changeListener) {
        }
        
        synchronized void addExtendedStyle(final Style style) {
            ((MuxingAttributeSet)this).insertAttributeSetAt(style, super.attrs.length);
        }
        
        int boundedIndexOf(final String s, final char c, final int n, final int n2) {
            final int index = s.indexOf(c, n);
            if (index >= n2) {
                return -1;
            }
            return index;
        }
        
        public String getName() {
            return this.name;
        }
        
        synchronized void insertExtendedStyleAt(final Style style, final int n) {
            ((MuxingAttributeSet)this).insertAttributeSetAt(style, this.extendedIndex + n);
        }
        
        synchronized void insertStyle(final Style style, final int n) {
            final int length = super.attrs.length;
            int n2;
            for (n2 = 0; n2 < this.extendedIndex && n <= StyleSheet.getSpecificity(((Style)super.attrs[n2]).getName()); ++n2) {}
            ((MuxingAttributeSet)this).insertAttributeSetAt(style, n2);
            ++this.extendedIndex;
        }
        
        protected boolean matches(final String s) {
            final int length = s.length();
            if (length == 0) {
                return false;
            }
            final int length2 = this.name.length();
            int i = s.lastIndexOf(32);
            int n = this.name.lastIndexOf(32);
            if (i >= 0) {
                ++i;
            }
            if (n >= 0) {
                ++n;
            }
            if (!this.matches(s, i, length, n, length2)) {
                return false;
            }
            while (i != -1) {
                final int n2 = i - 1;
                i = s.lastIndexOf(32, n2 - 1);
                if (i >= 0) {
                    ++i;
                }
                boolean matches;
                int n3;
                for (matches = false; !matches && n != -1; matches = this.matches(s, i, n2, n, n3)) {
                    n3 = n - 1;
                    n = this.name.lastIndexOf(32, n3 - 1);
                    if (n >= 0) {
                        ++n;
                    }
                }
                if (!matches) {
                    return false;
                }
            }
            return true;
        }
        
        boolean matches(final String s, int max, final int n, int max2, final int n2) {
            max = Math.max(max, 0);
            max2 = Math.max(max2, 0);
            final int boundedIndex = this.boundedIndexOf(this.name, '.', max2, n2);
            final int boundedIndex2 = this.boundedIndexOf(this.name, '#', max2, n2);
            final int boundedIndex3 = this.boundedIndexOf(s, '.', max, n);
            final int boundedIndex4 = this.boundedIndexOf(s, '#', max, n);
            if (boundedIndex3 != -1) {
                if (boundedIndex == -1) {
                    return false;
                }
                if (max == boundedIndex3) {
                    if (n2 - boundedIndex != n - boundedIndex3 || !s.regionMatches(max, this.name, boundedIndex, n2 - boundedIndex)) {
                        return false;
                    }
                }
                else if (n - max != n2 - max2 || !s.regionMatches(max, this.name, max2, n2 - max2)) {
                    return false;
                }
                return true;
            }
            else if (boundedIndex4 != -1) {
                if (boundedIndex2 == -1) {
                    return false;
                }
                if (max == boundedIndex4) {
                    if (n2 - boundedIndex2 != n - boundedIndex4 || !s.regionMatches(max, this.name, boundedIndex2, n2 - boundedIndex2)) {
                        return false;
                    }
                }
                else if (n - max != n2 - max2 || !s.regionMatches(max, this.name, max2, n2 - max2)) {
                    return false;
                }
                return true;
            }
            else {
                if (boundedIndex != -1) {
                    return s.regionMatches(max, this.name, max2, boundedIndex - max2);
                }
                if (boundedIndex2 != -1) {
                    return s.regionMatches(max, this.name, max2, boundedIndex2 - max2);
                }
                return s.regionMatches(max, this.name, max2, n2 - max2);
            }
        }
        
        public void removeAttribute(final Object o) {
        }
        
        public void removeAttributes(final Enumeration enumeration) {
        }
        
        public void removeAttributes(final AttributeSet set) {
        }
        
        public void removeChangeListener(final ChangeListener changeListener) {
        }
        
        synchronized void removeExtendedStyleAt(final int n) {
            ((MuxingAttributeSet)this).removeAttributeSetAt(this.extendedIndex + n);
        }
        
        synchronized void removeStyle(final Style style) {
            int i = super.attrs.length - 1;
            while (i >= 0) {
                if (super.attrs[i] == style) {
                    ((MuxingAttributeSet)this).removeAttributeSetAt(i);
                    if (i < this.extendedIndex) {
                        --this.extendedIndex;
                        break;
                    }
                    break;
                }
                else {
                    --i;
                }
            }
        }
        
        public void setResolveParent(final AttributeSet set) {
        }
    }
    
    static class SheetAttribute
    {
        private boolean cache;
        private String name;
        
        SheetAttribute(final String name, final boolean cache) {
            this.name = name;
            this.cache = cache;
        }
        
        public boolean isCache() {
            return this.cache;
        }
        
        public String toString() {
            return this.name;
        }
    }
    
    class CssParser implements Serializable
    {
        Vector selectors;
        Vector selectorTokens;
        String key;
        Vector ruleTokens;
        MutableAttributeSet declaration;
        boolean parsingDeclaration;
        boolean isLink;
        URL base;
        
        CssParser() {
            this.selectors = new Vector();
            this.selectorTokens = new Vector();
            this.ruleTokens = new Vector();
            this.declaration = new SimpleAttributeSet();
        }
        
        void addImport(final String s) {
            if (s.startsWith("url(") && s.endsWith(")")) {
                try {
                    final URL url = new URL(s.substring(4, s.length() - 1));
                    if (url != null) {
                        StyleSheet.this.importStyleSheet(url);
                    }
                }
                catch (MalformedURLException ex) {}
            }
            else if (this.base != null) {
                try {
                    final URL url2 = new URL(this.base, s);
                    if (url2 != null) {
                        StyleSheet.this.importStyleSheet(url2);
                    }
                }
                catch (MalformedURLException ex2) {}
            }
        }
        
        void addKeyValueToDeclaration() {
            final CSS.Attribute attribute = CSS.getAttribute(this.key);
            if (attribute != null) {
                final Object value = this.getValue(attribute);
                if (value != null) {
                    this.declaration.addAttribute(attribute, value);
                }
            }
            this.ruleTokens.removeAllElements();
        }
        
        void addRule() {
            if (this.parsingDeclaration) {
                throw new RuntimeException("Declaration should not contain rules");
            }
            for (int size = this.selectors.size(), i = 0; i < size; ++i) {
                final String[] array = this.selectors.elementAt(i);
                if (array.length > 0) {
                    StyleSheet.this.addRule(array, this.declaration, this.isLink);
                }
            }
            this.declaration.removeAttributes(this.declaration);
            this.selectors.removeAllElements();
        }
        
        void addSelector() {
            final String[] array = new String[this.selectorTokens.size()];
            this.selectorTokens.copyInto(array);
            this.selectors.addElement(array);
            this.selectorTokens.removeAllElements();
        }
        
        String getDeclarationString() {
            String string = "";
            for (int size = this.ruleTokens.size(), i = 0; i < size; ++i) {
                string = String.valueOf(string) + this.ruleTokens.elementAt(i);
            }
            this.ruleTokens.removeAllElements();
            return string;
        }
        
        Object getValue(final CSS.Attribute attribute) {
            return StyleSheet.this.css.getInternalCSSValue(attribute, this.getDeclarationString());
        }
        
        void parse(final URL base, final Reader reader, final boolean parsingDeclaration, final boolean isLink) throws IOException {
            this.base = base;
            this.isLink = isLink;
            this.declaration.removeAttributes(this.declaration);
            this.selectorTokens.removeAllElements();
            this.selectors.removeAllElements();
            this.ruleTokens.removeAllElements();
            this.parsingDeclaration = parsingDeclaration;
            final StreamTokenizer streamTokenizer = new StreamTokenizer(reader);
            streamTokenizer.ordinaryChar(47);
            streamTokenizer.slashStarComments(true);
            streamTokenizer.wordChars(35, 35);
            int n = parsingDeclaration ? 1 : 0;
            int n2 = 0;
            int n3 = 0;
            int n4 = 0;
            StringBuffer sb = null;
            int n5 = 0;
            final char[] array = { '\0' };
            int i = streamTokenizer.nextToken();
        Label_0806_Outer:
            while (i != -1) {
                while (true) {
                    switch (i) {
                        case -2: {
                            n5 = 0;
                            if (n2 != 0) {
                                if (n3 != 0) {
                                    sb.append(streamTokenizer.nval);
                                }
                                break Label_0806;
                            }
                            else {
                                if (n != 0) {
                                    this.ruleTokens.addElement(new Integer((int)streamTokenizer.nval));
                                    break Label_0806;
                                }
                                if (streamTokenizer.nval == 0.0) {
                                    n5 = 1;
                                    break Label_0806;
                                }
                                this.selectorTokens.setElementAt(String.valueOf(this.selectorTokens.lastElement()) + (int)streamTokenizer.nval, this.selectorTokens.size() - 1);
                                n4 = 1;
                                break Label_0806;
                            }
                            break;
                        }
                        default: {
                            n5 = 0;
                            if (n2 != 0) {
                                if (streamTokenizer.ttype == 59) {
                                    n2 = 0;
                                    if (n3 != 0) {
                                        if (n4 == 0) {
                                            this.addImport(sb.toString());
                                        }
                                        n3 = 0;
                                    }
                                    break Label_0806;
                                }
                                else {
                                    if (n3 == 0) {
                                        break Label_0806;
                                    }
                                    if (streamTokenizer.ttype == 39 || streamTokenizer.ttype == 34) {
                                        sb.append(streamTokenizer.sval);
                                        break Label_0806;
                                    }
                                    sb.append((char)streamTokenizer.ttype);
                                    break Label_0806;
                                }
                            }
                            else {
                                switch (streamTokenizer.ttype) {
                                    case 123: {
                                        n4 = 1;
                                        this.addSelector();
                                        n = 1;
                                        break Label_0806;
                                    }
                                    case 125: {
                                        if (this.ruleTokens.size() > 0) {
                                            this.addKeyValueToDeclaration();
                                        }
                                        this.addRule();
                                        n = 0;
                                        break Label_0806;
                                    }
                                    case 59: {
                                        this.addKeyValueToDeclaration();
                                        break Label_0806;
                                    }
                                    case 58: {
                                        this.key = this.getDeclarationString();
                                        break Label_0806;
                                    }
                                    case 44: {
                                        if (n != 0) {
                                            this.ruleTokens.addElement(new Character(','));
                                            break Label_0806;
                                        }
                                        this.addSelector();
                                        break Label_0806;
                                    }
                                    case 64: {
                                        n2 = 1;
                                        n3 = 0;
                                        break Label_0806;
                                    }
                                    default: {
                                        if (n != 0) {
                                            this.ruleTokens.addElement(new Character(','));
                                            break Label_0806;
                                        }
                                        if (this.selectorTokens.size() == 0) {
                                            array[0] = (char)streamTokenizer.ttype;
                                            this.selectorTokens.addElement(new String(array));
                                            break Label_0806;
                                        }
                                        this.selectorTokens.setElementAt(String.valueOf(this.selectorTokens.lastElement()) + (char)streamTokenizer.ttype, this.selectorTokens.size() - 1);
                                        break Label_0806;
                                    }
                                }
                            }
                            break;
                        }
                        case 10: {
                            i = streamTokenizer.nextToken();
                            continue Label_0806_Outer;
                        }
                        case -3: {
                            if (n2 != 0) {
                                if (n3 != 0) {
                                    sb.append(streamTokenizer.sval);
                                }
                                else if (streamTokenizer.sval.equals("import")) {
                                    n3 = 1;
                                    if (sb == null) {
                                        sb = new StringBuffer();
                                    }
                                    else {
                                        sb.setLength(0);
                                    }
                                }
                            }
                            else if (n != 0) {
                                this.ruleTokens.addElement(streamTokenizer.sval);
                            }
                            else {
                                if (n5 != 0) {
                                    this.selectorTokens.addElement(String.valueOf('.') + streamTokenizer.sval);
                                }
                                else {
                                    this.selectorTokens.addElement(streamTokenizer.sval);
                                }
                                n4 = 1;
                            }
                            n5 = 0;
                            continue;
                        }
                    }
                    break;
                }
            }
            if (this.parsingDeclaration && this.ruleTokens.size() > 0) {
                this.addKeyValueToDeclaration();
            }
        }
        
        AttributeSet parseDeclaration(final Reader reader) throws IOException {
            this.parse(this.base, reader, true, false);
            return this.declaration.copyAttributes();
        }
        
        AttributeSet parseDeclaration(final String s) {
            try {
                return this.parseDeclaration(new StringReader(s));
            }
            catch (IOException ex) {
                return null;
            }
        }
    }
}
