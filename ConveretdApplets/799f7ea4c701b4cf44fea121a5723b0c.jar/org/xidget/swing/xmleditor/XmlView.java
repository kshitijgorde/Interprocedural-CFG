// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.xmleditor;

import javax.swing.text.BadLocationException;
import java.util.regex.Matcher;
import java.util.Iterator;
import javax.swing.text.Segment;
import javax.swing.text.Document;
import javax.swing.text.TabExpander;
import javax.swing.text.Utilities;
import java.util.Map;
import java.util.TreeMap;
import java.awt.Graphics;
import javax.swing.text.Element;
import java.util.LinkedHashMap;
import java.awt.Color;
import java.util.regex.Pattern;
import java.util.HashMap;
import javax.swing.text.PlainView;

public class XmlView extends PlainView
{
    private static HashMap<Pattern, Color> patternColors;
    private static String GENERIC_XML_NAME;
    private static String TAG_PATTERN;
    private static String TAG_END_PATTERN;
    private static String TAG_ATTRIBUTE_PATTERN;
    private static String TAG_ATTRIBUTE_VALUE;
    private static String TAG_COMMENT;
    private static String TAG_CDATA;
    
    static {
        XmlView.GENERIC_XML_NAME = "[A-Za-z]+[A-Za-z0-9\\-_]*(:[A-Za-z]+[A-Za-z0-9\\-_]+)?";
        XmlView.TAG_PATTERN = "(</?" + XmlView.GENERIC_XML_NAME + ")\\s?>?";
        XmlView.TAG_END_PATTERN = "(/>)";
        XmlView.TAG_ATTRIBUTE_PATTERN = "(" + XmlView.GENERIC_XML_NAME + ")\\w*\\=";
        XmlView.TAG_ATTRIBUTE_VALUE = "\\w*\\=\\w*(\"[^\"]*\")";
        XmlView.TAG_COMMENT = "(<\\!--[\\w ]*-->)";
        XmlView.TAG_CDATA = "(<\\!\\[CDATA\\[.*\\]\\]>)";
        (XmlView.patternColors = new LinkedHashMap<Pattern, Color>()).put(Pattern.compile(XmlView.TAG_PATTERN), new Color(63, 127, 127));
        XmlView.patternColors.put(Pattern.compile(XmlView.TAG_CDATA), Color.GRAY);
        XmlView.patternColors.put(Pattern.compile(XmlView.TAG_ATTRIBUTE_PATTERN), new Color(127, 0, 127));
        XmlView.patternColors.put(Pattern.compile(XmlView.TAG_END_PATTERN), new Color(63, 127, 127));
        XmlView.patternColors.put(Pattern.compile(XmlView.TAG_ATTRIBUTE_VALUE), new Color(42, 0, 255));
        XmlView.patternColors.put(Pattern.compile(XmlView.TAG_COMMENT), Color.BLUE);
    }
    
    public XmlView(final Element element) {
        super(element);
        this.getDocument().putProperty("tabSize", 4);
    }
    
    @Override
    protected int drawUnselectedText(final Graphics graphics, int n, final int n2, final int n3, final int n4) throws BadLocationException {
        final Document document = this.getDocument();
        final String text = document.getText(n3, n4 - n3);
        final Segment lineBuffer = this.getLineBuffer();
        final TreeMap<Object, Object> treeMap = new TreeMap<Object, Object>();
        final TreeMap<Object, Color> treeMap2 = new TreeMap<Object, Color>();
        for (final Map.Entry<Pattern, Color> entry : XmlView.patternColors.entrySet()) {
            final Matcher matcher = entry.getKey().matcher(text);
            while (matcher.find()) {
                treeMap.put(matcher.start(1), matcher.end());
                treeMap2.put(matcher.start(1), entry.getValue());
            }
        }
        int n5 = 0;
        int n6 = 0;
        for (final Map.Entry<Object, Object> entry2 : treeMap.entrySet()) {
            int intValue = entry2.getKey();
            final int intValue2 = entry2.getValue();
            if (intValue2 < n6) {
                continue;
            }
            if (intValue < n6) {
                intValue = n6;
            }
            n6 = intValue2;
            if (n5 < intValue) {
                graphics.setColor(Color.black);
                document.getText(n3 + n5, intValue - n5, lineBuffer);
                n = Utilities.drawTabbedText(lineBuffer, n, n2, graphics, this, n5);
            }
            graphics.setColor((Color)treeMap2.get(intValue));
            n5 = intValue2;
            document.getText(n3 + intValue, n5 - intValue, lineBuffer);
            n = Utilities.drawTabbedText(lineBuffer, n, n2, graphics, this, intValue);
        }
        if (n5 < text.length()) {
            graphics.setColor(Color.black);
            document.getText(n3 + n5, text.length() - n5, lineBuffer);
            n = Utilities.drawTabbedText(lineBuffer, n, n2, graphics, this, n5);
        }
        return n;
    }
}
