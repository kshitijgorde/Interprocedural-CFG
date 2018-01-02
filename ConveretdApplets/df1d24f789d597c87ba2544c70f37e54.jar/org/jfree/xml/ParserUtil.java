// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml;

import java.awt.geom.Rectangle2D;
import org.xml.sax.Attributes;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Stroke;
import org.xml.sax.SAXException;

public class ParserUtil
{
    static /* synthetic */ Class class$java$awt$Color;
    
    public static int parseInt(final String s, final String message) throws SAXException {
        if (s == null) {
            throw new SAXException(message);
        }
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            throw new SAXException("NumberFormatError: " + message);
        }
    }
    
    public static int parseInt(final String s, final int n) {
        if (s == null) {
            return n;
        }
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static float parseFloat(final String s, final String message) throws SAXException {
        if (s == null) {
            throw new SAXException(message);
        }
        try {
            return Float.parseFloat(s);
        }
        catch (NumberFormatException ex) {
            throw new SAXException("NumberFormatError: " + message);
        }
    }
    
    public static float parseFloat(final String s, final float n) {
        if (s == null) {
            return n;
        }
        try {
            return Float.parseFloat(s);
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static boolean parseBoolean(final String s, final boolean b) {
        if (s == null) {
            return b;
        }
        return s.equalsIgnoreCase("true");
    }
    
    public static String parseString(final String s, final String s2) {
        if (s == null) {
            return s2;
        }
        return s;
    }
    
    public static Stroke parseStroke(final String s) {
        try {
            if (s != null) {
                return new BasicStroke(new Float(s));
            }
        }
        catch (NumberFormatException ex) {}
        return new BasicStroke(1.0f);
    }
    
    public static Color parseColor(final String s) {
        return parseColor(s, Color.black);
    }
    
    public static Color parseColor(final String s, final Color color) {
        if (s == null) {
            return color;
        }
        try {
            return Color.decode(s);
        }
        catch (NumberFormatException ex) {
            try {
                return (Color)((ParserUtil.class$java$awt$Color == null) ? (ParserUtil.class$java$awt$Color = class$("java.awt.Color")) : ParserUtil.class$java$awt$Color).getField(s).get(null);
            }
            catch (Exception ex2) {
                return color;
            }
        }
    }
    
    public static float parseRelativeFloat(final String s, final String message) throws SAXException {
        if (s == null) {
            throw new SAXException(message);
        }
        final String trim = s.trim();
        if (trim.endsWith("%")) {
            return parseFloat(trim.substring(0, trim.indexOf("%")), message) * -1.0f;
        }
        return parseFloat(trim, message);
    }
    
    public static Rectangle2D getElementPosition(final Attributes attributes) throws SAXException {
        return new Rectangle2D.Float(parseRelativeFloat(attributes.getValue("x"), "Element x not specified"), parseRelativeFloat(attributes.getValue("y"), "Element y not specified"), parseRelativeFloat(attributes.getValue("width"), "Element width not specified"), parseRelativeFloat(attributes.getValue("height"), "Element height not specified"));
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
