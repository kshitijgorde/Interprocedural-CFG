import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.awt.Color;
import java.io.Serializable;
import java.awt.Toolkit;
import java.awt.FontMetrics;
import java.awt.Font;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class UBBTextTools
{
    private String name;
    private UBBErrorHandler error;
    private static Hashtable fonts;
    private char openTagChar;
    private char closeTagChar;
    private int defaultWidth;
    public static final int LEFT = 0;
    public static final int CENTER = 1;
    public static final int RIGHT = 2;
    public static final int TOP = 0;
    public static final int MIDDLE = 1;
    public static final int BOTTOM = 2;
    
    private Font addFont(final Vector vector, String s, int n, int n2) {
        Font font = null;
        final String string = s + n + n2;
        if (UBBTextTools.fonts == null) {
            UBBTextTools.fonts = new Hashtable(23, 0.5f);
        }
        else {
            font = (Font)UBBTextTools.fonts.get(string);
        }
        if (font == null) {
            font = new Font(s, n2, n);
            if (font != null) {
                UBBTextTools.fonts.put(string, font);
            }
            else {
                this.error.notify(this.name, 1, "Invalid Font " + s, null);
                s = "TimesRoman";
                n = 10;
                n2 = 0;
                final String string2 = s + n + n2;
                font = (Font)UBBTextTools.fonts.get(string2);
                if (font == null) {
                    UBBTextTools.fonts.put(string2, new Font(s, n2, n));
                }
            }
        }
        vector.addElement(font);
        return font;
    }
    
    public UBBTextTools(final String s, final String s2, final int n, final UBBErrorHandler error) {
        this.name = ((s != null) ? s : this.getClass().getName());
        this.defaultWidth = ((n <= 0) ? 100000 : n);
        this.error = error;
        try {
            this.openTagChar = s2.charAt(0);
            this.closeTagChar = s2.charAt(1);
        }
        catch (Exception ex) {
            this.openTagChar = '<';
            this.closeTagChar = '>';
        }
    }
    
    private LineMetrics addText(final Vector vector, final Vector vector2, final String s, final FontMetrics fontMetrics, final int n, final int ascent, final int descent, final LineMetrics lineMetrics) {
        int i = 0;
        final int length = s.length();
        while (i < length) {
            int n2;
            for (n2 = i; n2 < length && s.charAt(n2) == ' '; ++n2) {}
            int index;
            if (n2 == length) {
                index = -1;
            }
            else {
                index = s.indexOf(32, n2);
            }
            String s2;
            if (index > -1) {
                while (index < length && s.charAt(index) == ' ') {
                    ++index;
                }
                s2 = ((index >= length) ? s.substring(i) : s.substring(i, index));
                i = index;
            }
            else {
                s2 = s.substring(i);
                i = length;
            }
            int width = fontMetrics.stringWidth(s2);
            if (width + lineMetrics.width >= n && lineMetrics.width != 0) {
                if (s2.charAt(0) == ' ') {
                    int n3;
                    for (n3 = 0; n3 < s2.length() && s2.charAt(n3) == ' '; ++n3) {}
                    if (n3 < s2.length()) {
                        s2 = s2.substring(n3);
                        width = fontMetrics.stringWidth(s2);
                    }
                    else {
                        s2 = "";
                        width = 0;
                    }
                }
                int j = vector.size() - 1;
                if (j > 0) {
                    Serializable element = null;
                    boolean b = false;
                    while (j >= 0) {
                        element = vector.elementAt(j);
                        if (element == null || element instanceof String) {
                            break;
                        }
                        if (element instanceof Font) {
                            b = true;
                        }
                        --j;
                    }
                    if (j >= 0 && element != null) {
                        final int n4 = j;
                        String s3;
                        int n5;
                        for (s3 = (String)element, n5 = s3.length() - 1; n5 >= 0 && s3.charAt(n5) == ' '; --n5) {}
                        if (n5 != s3.length() - 1) {
                            FontMetrics fontMetrics2 = fontMetrics;
                            if (b) {
                                while (j >= 0) {
                                    final Font element2 = vector.elementAt(j);
                                    if (element2 instanceof Font) {
                                        fontMetrics2 = Toolkit.getDefaultToolkit().getFontMetrics(element2);
                                        break;
                                    }
                                    --j;
                                }
                            }
                            final int stringWidth = fontMetrics2.stringWidth(s3);
                            String substring;
                            if (n5 >= 0) {
                                substring = s3.substring(0, n5 + 1);
                                lineMetrics.width -= stringWidth - fontMetrics2.stringWidth(substring);
                            }
                            else {
                                substring = "";
                                lineMetrics.width -= stringWidth;
                            }
                            vector.setElementAt(substring, n4);
                        }
                    }
                }
                vector.addElement(null);
                vector2.addElement(new LineMetrics(lineMetrics));
                lineMetrics.ascent = ascent;
                lineMetrics.descent = descent;
                lineMetrics.width = width;
            }
            else {
                lineMetrics.width += width;
            }
            vector.addElement(s2);
        }
        return lineMetrics;
    }
    
    public void releaseFontTable() {
        if (UBBTextTools.fonts != null) {
            UBBTextTools.fonts.clear();
            UBBTextTools.fonts = null;
        }
    }
    
    public FormattedText formatText(final String s, final int n, final int n2) {
        return this.formatText(s, n, n2, null, 0, 0, null);
    }
    
    public FormattedText formatText(final String s, final int n, int defaultWidth, final String s2, final int n2, final int n3, Color black) {
        FormattedText formattedText = null;
        if (defaultWidth <= 0) {
            defaultWidth = this.defaultWidth;
        }
        if (s != null) {
            int i = 0;
            final int length = s.length();
            LineMetrics lineMetrics = new LineMetrics();
            int ascent = 0;
            int descent = 0;
            int n4 = 0;
            final Vector vector = new Vector<Color>(10);
            final Vector<LineMetrics> vector2 = new Vector<LineMetrics>(10);
            String trim = (s2 != null) ? s2 : "TimesRoman";
            int int1 = (n2 != 0) ? n2 : 10;
            int n5 = n3;
            int n6 = 1;
            FontMetrics fontMetrics = null;
            if (black == null) {
                black = Color.black;
            }
            vector.addElement(black);
            while (i < length) {
                final int index = s.indexOf(this.openTagChar, i);
                if (index > -1) {
                    if (index > i) {
                        if (n6 != 0) {
                            fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(this.addFont(vector, trim, int1, n5));
                            ascent = n4 + fontMetrics.getAscent() + fontMetrics.getLeading();
                            descent = fontMetrics.getDescent();
                            if (lineMetrics.ascent < ascent) {
                                lineMetrics.ascent = ascent;
                            }
                            if (lineMetrics.descent < descent) {
                                lineMetrics.descent = descent;
                            }
                            n6 = 0;
                        }
                        lineMetrics = this.addText(vector, vector2, s.substring(i, index), fontMetrics, defaultWidth, ascent, descent, lineMetrics);
                    }
                    i = s.indexOf(this.closeTagChar, index);
                    if (i > -1) {
                        final String trim2 = s.substring(index + 1, i++).trim();
                        switch (trim2.charAt(0)) {
                            case 'F':
                            case 'f': {
                                if (trim2.length() > 1) {
                                    trim = trim2.substring(1).trim();
                                    n6 = 1;
                                    continue;
                                }
                                this.error.notify(this.name, 1, "Bad font " + trim2, null);
                                continue;
                            }
                            case 'S':
                            case 's': {
                                if (trim2.length() > 1) {
                                    try {
                                        int1 = Integer.parseInt(trim2.substring(1).trim());
                                        n6 = 1;
                                    }
                                    catch (Exception ex) {
                                        this.error.notify(this.name, 1, "Bad font size", ex);
                                    }
                                    continue;
                                }
                                continue;
                            }
                            case 'C':
                            case 'c': {
                                if (trim2.length() > 1) {
                                    try {
                                        final Color color = UBBImageFactory.parseColor(trim2.substring(1).trim());
                                        if (vector.size() == 1) {
                                            vector.setElementAt(color, 0);
                                        }
                                        else {
                                            vector.addElement(color);
                                        }
                                    }
                                    catch (Exception ex2) {
                                        this.error.notify(this.name, 1, "Bad color", ex2);
                                    }
                                    continue;
                                }
                                continue;
                            }
                            case 'B':
                            case 'b': {
                                if (trim2.length() > 1 && (trim2.charAt(1) == 'r' || trim2.charAt(1) == 'R')) {
                                    vector.addElement(null);
                                    vector2.addElement(new LineMetrics(lineMetrics));
                                    if (trim2.length() > 2) {
                                        final String trim3 = trim2.substring(2).trim();
                                        if (trim3.length() > 0) {
                                            try {
                                                final int int2 = Integer.parseInt(trim3);
                                                n4 += int2;
                                                final LineMetrics lineMetrics2 = lineMetrics;
                                                lineMetrics2.ascent += int2;
                                                ascent += int2;
                                            }
                                            catch (Exception ex3) {
                                                this.error.notify(this.name, 1, "Bad height adjust", ex3);
                                            }
                                        }
                                    }
                                    lineMetrics.ascent = ascent;
                                    lineMetrics.descent = descent;
                                    lineMetrics.width = 0;
                                    continue;
                                }
                                n5 |= 0x1;
                                n6 = 1;
                                continue;
                            }
                            case 'I':
                            case 'i': {
                                n5 |= 0x2;
                                n6 = 1;
                                continue;
                            }
                            case 'P':
                            case 'p': {
                                n5 = 0;
                                n6 = 1;
                                continue;
                            }
                            default: {
                                this.error.notify(this.name, 1, "Unknown text tag " + trim2, null);
                                continue;
                            }
                        }
                    }
                    else {
                        this.error.notify(this.name, 1, "Tag end not found: look for missing quote, '" + this.openTagChar + "', or '" + this.closeTagChar + "' in \"" + s + "\"", null);
                        i = length;
                    }
                }
                else {
                    if (n6 != 0) {
                        fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(this.addFont(vector, trim, int1, n5));
                    }
                    ascent = n4 + fontMetrics.getAscent() + fontMetrics.getLeading();
                    descent = fontMetrics.getDescent();
                    if (lineMetrics.ascent < ascent) {
                        lineMetrics.ascent = ascent;
                    }
                    if (lineMetrics.descent < descent) {
                        lineMetrics.descent = descent;
                    }
                    lineMetrics = this.addText(vector, vector2, s.substring(i), fontMetrics, defaultWidth, ascent, descent, lineMetrics);
                    i = length;
                }
            }
            if (lineMetrics.width > 0) {
                vector2.addElement(new LineMetrics(lineMetrics));
            }
            String string = null;
            FontMetrics fontMetrics2 = null;
            final Vector vector3 = new Vector<StringData>(vector.size());
            final Enumeration<Color> elements = vector.elements();
            while (elements.hasMoreElements()) {
                final Color nextElement = elements.nextElement();
                if (nextElement == null || !(nextElement instanceof String)) {
                    if (string != null) {
                        vector3.addElement(new StringData(string, fontMetrics2.stringWidth(string)));
                        string = null;
                    }
                    vector3.addElement((StringData)nextElement);
                    if (!(nextElement instanceof Font)) {
                        continue;
                    }
                    fontMetrics2 = Toolkit.getDefaultToolkit().getFontMetrics((Font)nextElement);
                }
                else if (string == null) {
                    string = (String)nextElement;
                }
                else {
                    string += (String)nextElement;
                }
            }
            if (string != null) {
                vector3.addElement(new StringData(string, fontMetrics2.stringWidth(string)));
            }
            final Object[] array = new Object[vector3.size()];
            vector3.copyInto(array);
            vector3.removeAllElements();
            final LineMetrics[] array2 = new LineMetrics[vector2.size()];
            vector2.copyInto(array2);
            vector2.removeAllElements();
            formattedText = new FormattedText(array2, array, n, defaultWidth);
        }
        return formattedText;
    }
    
    private class LineMetrics
    {
        int width;
        int ascent;
        int descent;
        
        public LineMetrics() {
            this.width = 0;
            this.ascent = 0;
            this.descent = 0;
        }
        
        public LineMetrics(final LineMetrics lineMetrics) {
            this.width = lineMetrics.width;
            this.ascent = lineMetrics.ascent;
            this.descent = lineMetrics.descent;
        }
    }
    
    public class FormattedText
    {
        private LineMetrics[] lineMetrics;
        private Object[] formattedText;
        private int locationX;
        private int locationY;
        private int formattedWidth;
        private int justification;
        private int justifyAdjust;
        private int alignment;
        private int alignAdjust;
        private Rectangle area;
        private int maxWidth;
        private int maxHeight;
        private int drawingAreaHeight;
        
        public FormattedText(final LineMetrics[] lineMetrics, final Object[] formattedText, final int justification, final int formattedWidth) {
            this.maxWidth = -1;
            this.maxHeight = -1;
            this.lineMetrics = lineMetrics;
            this.formattedText = formattedText;
            this.formattedWidth = formattedWidth;
            this.justification = justification;
        }
        
        public void paint(final Image image, final int n, final int n2, final Rectangle rectangle) {
            if (image != null) {
                if (rectangle != null) {
                    this.drawingAreaHeight = rectangle.height;
                }
                final Graphics graphics = image.getGraphics();
                if (graphics != null) {
                    this.paint(graphics, n, n2, rectangle);
                    graphics.dispose();
                }
            }
        }
        
        public void paint(final Graphics graphics, final int locationX, final int locationY, final Rectangle area) {
            this.locationX = locationX;
            this.locationY = locationY;
            this.area = area;
            this.paint(graphics);
        }
        
        public void paint(final Image image) {
            if (image != null) {
                final Graphics graphics = image.getGraphics();
                this.drawingAreaHeight = image.getHeight(null);
                if (graphics != null) {
                    this.paint(graphics);
                    graphics.dispose();
                }
            }
        }
        
        public void paint(final Graphics graphics) {
            int locationX = this.locationX;
            int locationY = this.locationY;
            if (graphics != null && this.formattedText != null) {
                int height = -1;
                Rectangle clipBounds = null;
                if (this.area != null) {
                    clipBounds = graphics.getClipBounds();
                    if (clipBounds == null || !clipBounds.equals(this.area)) {
                        locationX += this.area.x;
                        locationY += this.area.y;
                        graphics.setClip(this.area.x, this.area.y, this.area.width, this.area.height);
                        height = this.area.height;
                    }
                    else {
                        clipBounds = null;
                    }
                }
                int n = 0;
                if (this.alignment != 0 && this.drawingAreaHeight != 0) {
                    n = this.drawingAreaHeight - this.getHeight() - locationY;
                    if (n > 0 && this.alignment == 1) {
                        n /= 2;
                    }
                }
                int n2 = locationY + (this.lineMetrics[0].ascent + n + this.alignAdjust);
                final int n3 = locationX;
                int n4 = 0;
                if (this.justification == 1) {
                    if (this.lineMetrics[0].width != this.formattedWidth) {
                        locationX = n3 + (this.formattedWidth - this.lineMetrics[0].width) / 2;
                    }
                }
                else if (this.justification == 2) {
                    locationX = n3 + (this.formattedWidth - this.lineMetrics[0].width);
                }
                int n5 = locationX + this.justifyAdjust;
                final int n6 = this.lineMetrics.length - 1;
                for (int i = 0; i < this.formattedText.length; ++i) {
                    final Object o = this.formattedText[i];
                    if (o == null) {
                        if (this.justification == 0) {
                            n5 = n3;
                        }
                        else if (this.justification == 1) {
                            if (this.lineMetrics[n4 + 1].width != this.formattedWidth) {
                                n5 = n3 + (this.formattedWidth - this.lineMetrics[n4 + 1].width) / 2;
                            }
                        }
                        else {
                            n5 = n3 + (this.formattedWidth - this.lineMetrics[n4 + 1].width);
                        }
                        n5 += this.justifyAdjust;
                        final int n7 = (n4 < n6) ? this.lineMetrics[n4 + 1].ascent : 0;
                        n2 = n2 + this.lineMetrics[n4].descent + n7;
                        if (height >= 0 && n2 - n7 >= height) {
                            break;
                        }
                        ++n4;
                    }
                    else if (o instanceof StringData) {
                        graphics.drawString(((StringData)o).string, n5, n2);
                        n5 += ((StringData)o).width;
                    }
                    else if (o instanceof Font) {
                        graphics.setFont((Font)o);
                    }
                    else if (o instanceof Color) {
                        graphics.setColor((Color)o);
                    }
                }
                if (this.area != null) {
                    if (clipBounds != null) {
                        graphics.setClip(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
                        return;
                    }
                    graphics.setClip(null);
                }
            }
        }
        
        public void setJustification(final int n) {
            this.setJustification(n, 0);
        }
        
        public void setJustification(final int justification, final int justifyAdjust) {
            this.justification = justification;
            this.justifyAdjust = justifyAdjust;
        }
        
        public int getHeight() {
            if (this.maxHeight == -1) {
                this.maxHeight = 0;
                for (int i = 0; i < this.lineMetrics.length; ++i) {
                    this.maxHeight += this.lineMetrics[i].ascent + this.lineMetrics[i].descent;
                }
            }
            return this.maxHeight;
        }
        
        public void setAlignment(final int n) {
            this.setAlignment(n, 0);
        }
        
        public void setAlignment(final int alignment, final int alignAdjust) {
            this.alignment = alignment;
            this.alignAdjust = alignAdjust;
        }
        
        public int getWidth() {
            if (this.maxWidth == -1) {
                this.maxWidth = 0;
                for (int i = 0; i < this.lineMetrics.length; ++i) {
                    if (this.lineMetrics[i].width > this.maxWidth) {
                        this.maxWidth = this.lineMetrics[i].width;
                    }
                }
            }
            return this.maxWidth;
        }
        
        public void setLocation(final int locationX, final int locationY, final Rectangle area) {
            this.locationX = locationX;
            this.locationY = locationY;
            this.area = area;
        }
    }
    
    private class StringData
    {
        int width;
        String string;
        
        public StringData(final String string, final int width) {
            this.string = string;
            this.width = width;
        }
    }
}
