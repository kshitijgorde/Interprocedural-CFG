// 
// Decompiled by Procyon v0.5.30
// 

package jclass.util;

import java.applet.AppletContext;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Container;
import java.applet.Applet;
import java.awt.Component;
import java.awt.Image;
import java.util.Hashtable;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Dimension;

public class JCString extends JCVector
{
    public static final Integer UNDERLINE;
    public static final Integer STRIKETHROUGH;
    public static final Integer END_UNDERLINE;
    public static final Integer END_STRIKETHROUGH;
    public static final Integer HORIZ_SPACE;
    public static final Integer VERT_SPACE;
    public static final Integer NEWLINE;
    public static final Integer RESET;
    public static final Integer DEFAULT_FONT;
    public static final Integer DEFAULT_COLOR;
    public static final Integer TOP;
    public static final Integer MIDDLE;
    public static final Integer BOTTOM;
    public static final Integer LEFT;
    public static final Integer CENTER;
    public static final Integer RIGHT;
    public static final Integer HREF;
    public static final Integer END_HREF;
    public static final int STRING_LEFT = 0;
    public static final int STRING_RIGHT = 1;
    public static final int STRING_TOP = 2;
    public static final int STRING_BOTTOM = 3;
    static final int NOTFOUND = -1;
    static final int DEFAULT_SPACE = 10;
    static boolean underline;
    static boolean strikethrough;
    static Dimension size;
    static Rectangle draw_rect;
    private static Vector reg_images;
    public static boolean is_img_caching;
    private static Hashtable img_cache;
    Rectangle url_rect;
    String url;
    JCVector url_rect_list;
    JCVector url_list;
    public static final int ALIGN_LEFT = 0;
    public static final int ALIGN_CENTER = 1;
    public static final int ALIGN_RIGHT = 2;
    int x_offset;
    int y_offset;
    static boolean has_url;
    
    public JCString() {
    }
    
    public JCString(final int n) {
        super(n);
    }
    
    public JCString(final String s) {
        super(1);
        this.addString(s);
    }
    
    public JCString(final String s, final Image image, final int n) {
        this(5);
        if (s == null) {
            this.add(image);
            return;
        }
        if (image == null) {
            this.add(s);
            return;
        }
        switch (n) {
            case 0: {
                this.add(s);
                this.add(JCString.MIDDLE);
                this.add(JCString.HORIZ_SPACE);
                this.add(image);
            }
            case 2: {
                this.add(s);
                this.add(JCString.NEWLINE);
                this.add(JCString.VERT_SPACE);
                this.add(new Integer(5));
                this.add(image);
            }
            case 3: {
                this.add(image);
                this.add(JCString.NEWLINE);
                this.add(JCString.VERT_SPACE);
                this.add(new Integer(5));
                this.add(s);
            }
            default: {
                this.add(image);
                this.add(JCString.MIDDLE);
                this.add(JCString.HORIZ_SPACE);
                this.add(s);
            }
        }
    }
    
    private void addString(final String s) {
        int n;
        int index;
        for (n = 0; (index = s.indexOf("\n", n)) != -1; n = index + 1) {
            this.addElement(s.substring(n, index));
            this.addElement(JCString.NEWLINE);
        }
        this.addElement(s.substring(n));
    }
    
    private boolean isValue(final Object o) {
        return o instanceof Integer && (int)o >= 0;
    }
    
    public void add(final Object o) {
        if (o == null) {
            return;
        }
        if (o instanceof String) {
            this.addString((String)o);
            return;
        }
        this.addElement(o);
    }
    
    public void add(final int n) {
        this.addElement(new Integer(n));
    }
    
    public void add(final int n, final Object o) {
        this.add(n);
        this.add(o);
    }
    
    private void init() {
        JCString.underline = (JCString.strikethrough = false);
    }
    
    private static void parseToken(final Component component, final JCString jcString, final String s) {
        if (s == null) {
            return;
        }
        String substring = null;
        String substring2 = s;
        final int index = s.indexOf(61);
        if (index != -1) {
            substring2 = s.substring(0, index);
            substring = s.substring(index + 1);
        }
        if (substring2.equalsIgnoreCase("DEFAULT_FONT")) {
            jcString.add(JCString.DEFAULT_FONT);
            return;
        }
        if (substring2.equalsIgnoreCase("DEFAULT_COLOR")) {
            jcString.add(JCString.DEFAULT_COLOR);
            return;
        }
        if (substring2.equalsIgnoreCase("UNDERLINE") || substring2.equalsIgnoreCase("UL")) {
            jcString.add(JCString.UNDERLINE);
            return;
        }
        if (substring2.equalsIgnoreCase("/UNDERLINE") || substring2.equalsIgnoreCase("/UL")) {
            jcString.add(JCString.END_UNDERLINE);
            return;
        }
        if (substring2.equalsIgnoreCase("STRIKETHROUGH") || substring2.equalsIgnoreCase("ST")) {
            jcString.add(JCString.STRIKETHROUGH);
            return;
        }
        if (substring2.equalsIgnoreCase("/STRIKETHROUGH") || substring2.equalsIgnoreCase("/ST")) {
            jcString.add(JCString.END_STRIKETHROUGH);
            return;
        }
        if (substring2.equalsIgnoreCase("HREF")) {
            jcString.add(JCString.HREF);
            jcString.add(substring);
            return;
        }
        if (substring2.equalsIgnoreCase("/HREF")) {
            jcString.add(JCString.END_HREF);
            return;
        }
        if (substring2.equalsIgnoreCase("ALIGN")) {
            if (substring.equalsIgnoreCase("TOP")) {
                jcString.add(JCString.TOP);
                return;
            }
            if (substring.equalsIgnoreCase("MIDDLE")) {
                jcString.add(JCString.MIDDLE);
                return;
            }
            if (substring.equalsIgnoreCase("BOTTOM")) {
                jcString.add(JCString.BOTTOM);
            }
        }
        else if (substring2.equalsIgnoreCase("HORIZ_SPACE")) {
            jcString.add(JCString.HORIZ_SPACE);
            if (substring != null) {
                jcString.add(Integer.valueOf(substring));
            }
        }
        else if (substring2.equalsIgnoreCase("VERT_SPACE")) {
            jcString.add(JCString.VERT_SPACE);
            if (substring != null) {
                jcString.add(Integer.valueOf(substring));
            }
        }
        else {
            if (substring2.equalsIgnoreCase("RESET")) {
                jcString.add(JCString.RESET);
                return;
            }
            if (substring2.equalsIgnoreCase("COLOR")) {
                if (substring != null) {
                    if (substring.equalsIgnoreCase("DEFAULT")) {
                        jcString.add(JCString.DEFAULT_COLOR);
                        return;
                    }
                    jcString.add(JCUtilConverter.toColor(substring));
                }
            }
            else if (substring2.equalsIgnoreCase("IMAGE") || substring2.equalsIgnoreCase("IMG")) {
                Image image = null;
                if (substring != null) {
                    try {
                        final int intValue = Integer.valueOf(substring);
                        if (intValue > 0 && intValue <= JCString.reg_images.size()) {
                            image = (Image)JCString.reg_images.elementAt(intValue - 1);
                        }
                    }
                    catch (Exception ex) {
                        final String string = String.valueOf(getApplet(component)) + substring;
                        if ((image = getCachedImage(string)) == null && (image = JCUtilConverter.toImage(component, substring)) != null) {
                            putCachedImage(string, image);
                        }
                    }
                    if (image != null) {
                        jcString.add(new JCImage(image, substring));
                    }
                }
            }
            else if (substring2.equalsIgnoreCase("FONT")) {
                if (substring != null) {
                    if (substring.equalsIgnoreCase("DEFAULT")) {
                        jcString.add(JCString.DEFAULT_FONT);
                        return;
                    }
                    jcString.add(JCUtilConverter.toFont(substring));
                }
            }
            else if (substring2.equalsIgnoreCase("NEWLINE")) {
                jcString.add(JCString.NEWLINE);
            }
        }
    }
    
    public static JCString parse(final Component component, final String s) {
        if (s == null) {
            return null;
        }
        final JCString jcString = new JCString();
        final JCStringTokenizer jcStringTokenizer = new JCStringTokenizer(s);
        while (jcStringTokenizer.hasMoreTokens()) {
            jcString.add(jcStringTokenizer.nextToken('['));
            if (jcStringTokenizer.hasMoreTokens()) {
                final String nextToken = jcStringTokenizer.nextToken(']');
                if (nextToken != null) {
                    nextToken.trim();
                }
                parseToken(component, jcString, nextToken);
            }
        }
        return jcString;
    }
    
    public static Applet getApplet(final Component component) {
        if (component == null) {
            return null;
        }
        Container parent;
        Container parent2;
        for (parent = component.getParent(), parent2 = component.getParent(); parent2 != null && !(parent2 instanceof Applet); parent2 = parent, parent = ((parent == null) ? null : parent.getParent())) {}
        return (Applet)parent2;
    }
    
    public static int registerImage(final Image image) {
        JCString.reg_images.addElement(image);
        return JCString.reg_images.size();
    }
    
    public static void setIsImageCaching(final boolean is_img_caching) {
        JCString.is_img_caching = is_img_caching;
    }
    
    public static boolean getIsImageCaching() {
        return JCString.is_img_caching;
    }
    
    private static Image getCachedImage(final String s) {
        if (!JCString.is_img_caching) {
            return null;
        }
        return JCString.img_cache.get(s);
    }
    
    private static void putCachedImage(final String s, final Image image) {
        if (!JCString.is_img_caching && image != null) {
            return;
        }
        JCString.img_cache.put(s, image);
    }
    
    private boolean addImage(final Applet applet, final URL url, final String s) {
        Image image;
        if ((image = getCachedImage(String.valueOf(url.toString()) + s)) == null) {
            final MediaTracker mediaTracker = new MediaTracker(applet);
            image = applet.getImage(url, s);
            mediaTracker.addImage(image, 0);
            try {
                mediaTracker.waitForAll();
            }
            catch (InterruptedException ex) {
                return false;
            }
            putCachedImage(String.valueOf(url.toString()) + s, image);
        }
        this.add(image);
        return true;
    }
    
    public boolean addImage(final Applet applet, final String s) {
        return this.addImage(applet, applet.getDocumentBase(), s);
    }
    
    public boolean addImage(final Applet applet, final String s, final String s2) {
        try {
            this.addImage(applet, new URL(s), s2);
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public int getHeight(final Component component, final Font font) {
        return this.getSize(component, font).height;
    }
    
    public int getWidth(final Component component, final Font font) {
        return this.getSize(component, font).width;
    }
    
    public Dimension getSize(final Component component, Font font) {
        final Dimension dimension = new Dimension();
        int lineSize = 0;
        int max = 0;
        int height = 0;
        if (font == null) {
            font = component.getFont();
        }
        this.init();
        while ((lineSize = this.getLineSize(component, font, lineSize, dimension)) != -1) {
            max = Math.max(max, dimension.width);
            height += dimension.height;
        }
        dimension.width = max;
        dimension.height = height;
        return dimension;
    }
    
    static int stringWidth(final FontMetrics fontMetrics, final Font font, final String s) {
        return fontMetrics.stringWidth(s) + (font.isItalic() ? (font.getSize() / 3 + 1) : 0);
    }
    
    public int getLineSize(final Component component, final Font font, final int n, final Dimension dimension) {
        if (n >= this.size()) {
            return -1;
        }
        int intValue = 0;
        int n2 = 0;
        int max = 0;
        int n3 = 0;
        int n4 = 0;
        final Graphics graphics = component.getGraphics();
        if (graphics == null) {
            return -1;
        }
        int i;
        for (i = n; i >= 0; --i) {
            if (super.elementData[i] instanceof Font) {
                graphics.setFont((Font)super.elementData[i]);
                break;
            }
            if (super.elementData[i].equals(JCString.DEFAULT_FONT)) {
                graphics.setFont(font);
                break;
            }
        }
        if (i < 0 && font != null) {
            graphics.setFont(font);
        }
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int j;
        for (j = n; j < this.size(); ++j) {
            if (super.elementData[j] instanceof String) {
                n4 = Math.max(n4, fontMetrics.getHeight());
                n3 += stringWidth(fontMetrics, graphics.getFont(), (String)super.elementData[j]);
            }
            else if (super.elementData[j] instanceof Font) {
                graphics.setFont((Font)super.elementData[j]);
                fontMetrics = graphics.getFontMetrics();
            }
            else if (super.elementData[j] instanceof Image) {
                final Image image = (Image)super.elementData[j];
                n4 = Math.max(n4, image.getHeight(null));
                n3 += image.getWidth(null);
            }
            else if (super.elementData[j] instanceof JCImage) {
                final Image image2 = ((JCImage)super.elementData[j]).image;
                n4 = Math.max(n4, image2.getHeight(null));
                n3 += image2.getWidth(null);
            }
            else if (super.elementData[j].equals(JCString.HREF)) {
                ++j;
            }
            else if (super.elementData[j].equals(JCString.DEFAULT_FONT)) {
                graphics.setFont(font);
                fontMetrics = graphics.getFontMetrics();
            }
            else if (super.elementData[j].equals(JCString.HORIZ_SPACE)) {
                if (j < this.size() - 1 && this.isValue(super.elementData[j + 1])) {
                    n3 += (int)super.elementData[++j];
                }
                else {
                    n3 += 10;
                }
            }
            else if (super.elementData[j].equals(JCString.VERT_SPACE)) {
                if (j < this.size() - 1 && this.isValue(super.elementData[j + 1])) {
                    intValue = (int)super.elementData[++j];
                }
                else {
                    intValue = 10;
                }
            }
            else if (super.elementData[j].equals(JCString.NEWLINE)) {
                n2 += n4;
                max = Math.max(max, n3);
                n3 = (n4 = 0);
                break;
            }
        }
        dimension.width = Math.max(max, n3);
        dimension.height = n2 + n4 + intValue;
        return j + 1;
    }
    
    int getVertSpace(final int n, final int n2) {
        int n3 = n;
        while (n3 < n2 && n3 < this.size()) {
            if (super.elementData[n3].equals(JCString.VERT_SPACE)) {
                if (n3 < this.size() - 1 && this.isValue(super.elementData[n3 + 1])) {
                    return (int)super.elementData[++n3];
                }
                return 10;
            }
            else {
                ++n3;
            }
        }
        return 0;
    }
    
    public void draw(final Component component, final Graphics graphics, final Rectangle rectangle, final int n) {
        int n2 = 0;
        int y = rectangle.y;
        this.url = null;
        final JCVector jcVector = null;
        this.url_rect_list = jcVector;
        this.url_list = jcVector;
        final Font font = graphics.getFont();
        this.init();
        int lineSize;
        while ((lineSize = this.getLineSize(component, font, n2, JCString.size)) != -1) {
            final int vertSpace = this.getVertSpace(n2, lineSize);
            final Dimension size = JCString.size;
            size.height -= vertSpace;
            final int n3 = y + vertSpace;
            JCString.draw_rect.reshape(rectangle.x, n3, JCString.size.width, JCString.size.height);
            if (n == 1) {
                final Rectangle draw_rect = JCString.draw_rect;
                draw_rect.x += (rectangle.width - JCString.size.width) / 2;
            }
            else if (n == 2) {
                final Rectangle draw_rect2 = JCString.draw_rect;
                draw_rect2.x += rectangle.width - JCString.size.width;
            }
            this.drawLine(component, graphics, font, JCString.draw_rect, n2, lineSize);
            n2 = lineSize;
            y = n3 + JCString.size.height;
        }
    }
    
    public void setURLoffset(final int x_offset, final int y_offset) {
        this.x_offset = x_offset;
        this.y_offset = y_offset;
    }
    
    private void startURL(final int x, final int y) {
        this.url_rect = new Rectangle();
        this.url_rect.x = x;
        this.url_rect.y = y;
        if (this.url_list == null) {
            this.url_list = new JCVector();
        }
        if (this.url_rect_list == null) {
            this.url_rect_list = new JCVector();
        }
    }
    
    private void endURL(final Graphics graphics, final int n, final int n2) {
        this.url_list.addElement(this.url);
        this.url_rect.width = n - this.url_rect.x;
        this.url_rect.height = n2 - this.url_rect.y;
        this.url_rect.translate(this.x_offset, this.y_offset);
        this.url_rect_list.addElement(this.url_rect);
    }
    
    void drawLine(final Component component, final Graphics graphics, final Font font, final Rectangle rectangle, final int n, final int n2) {
        Integer n3 = JCString.BOTTOM;
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int x = rectangle.x;
        int y = rectangle.y;
        for (int n4 = n; n4 < n2 && n4 < this.size(); ++n4) {
            if (super.elementData[n4] instanceof String) {
                final int height = fontMetrics.getHeight();
                final int n5 = height - fontMetrics.getAscent();
                if (n3.equals(JCString.BOTTOM)) {
                    y = rectangle.y + rectangle.height - n5;
                }
                else if (n3.equals(JCString.MIDDLE)) {
                    y = rectangle.y + (rectangle.height - height) / 2 + height - n5;
                }
                else if (n3.equals(JCString.TOP)) {
                    y = rectangle.y + height - n5;
                }
                graphics.drawString((String)super.elementData[n4], x, y);
                if (this.url != null) {
                    this.startURL(x, y - height + n5);
                }
                final int stringWidth = stringWidth(fontMetrics, graphics.getFont(), (String)super.elementData[n4]);
                if (JCString.underline) {
                    graphics.drawLine(x, y + 1, x + stringWidth, y + 1);
                }
                if (JCString.strikethrough) {
                    graphics.drawLine(x, y - height / 2 + 3, x + stringWidth, y - height / 2 + 3);
                }
                x += stringWidth;
                if (this.url != null) {
                    this.endURL(graphics, x, y + n5);
                }
            }
            else if (super.elementData[n4] instanceof Image || super.elementData[n4] instanceof JCImage) {
                final Object o = super.elementData[n4];
                final Image image = (Image)((o instanceof Image) ? o : ((JCImage)o).image);
                if (this.url != null) {
                    this.startURL(x, rectangle.y);
                }
                graphics.drawImage(image, x, rectangle.y, null);
                x += image.getWidth(null);
                if (this.url != null) {
                    this.endURL(graphics, x, rectangle.y + rectangle.height);
                }
            }
            else if (super.elementData[n4] instanceof Font) {
                graphics.setFont((Font)super.elementData[n4]);
                fontMetrics = graphics.getFontMetrics();
            }
            else if (super.elementData[n4].equals(JCString.DEFAULT_FONT)) {
                graphics.setFont(font);
                fontMetrics = graphics.getFontMetrics();
            }
            else if (super.elementData[n4].equals(JCString.DEFAULT_COLOR)) {
                graphics.setColor(component.getForeground());
            }
            else if (super.elementData[n4].equals(JCString.HREF)) {
                JCString.underline = true;
                this.url = (String)super.elementData[++n4];
            }
            else if (super.elementData[n4].equals(JCString.UNDERLINE)) {
                JCString.underline = true;
            }
            else if (super.elementData[n4].equals(JCString.END_UNDERLINE)) {
                JCString.underline = false;
            }
            else if (super.elementData[n4].equals(JCString.END_HREF)) {
                JCString.underline = false;
                this.url = null;
            }
            else if (super.elementData[n4].equals(JCString.STRIKETHROUGH)) {
                JCString.strikethrough = true;
            }
            else if (super.elementData[n4].equals(JCString.END_STRIKETHROUGH)) {
                JCString.strikethrough = false;
            }
            else if (super.elementData[n4].equals(JCString.TOP)) {
                n3 = JCString.TOP;
            }
            else if (super.elementData[n4].equals(JCString.MIDDLE)) {
                n3 = JCString.MIDDLE;
            }
            else if (super.elementData[n4].equals(JCString.BOTTOM)) {
                n3 = JCString.BOTTOM;
            }
            else if (super.elementData[n4].equals(JCString.HORIZ_SPACE)) {
                if (n4 < this.size() - 1 && this.isValue(super.elementData[n4 + 1])) {
                    x += (int)super.elementData[++n4];
                }
                else {
                    x += 10;
                }
            }
            else if (super.elementData[n4].equals(JCString.RESET)) {
                if (this.url == null) {
                    JCString.underline = false;
                }
                JCString.strikethrough = false;
                n3 = JCString.BOTTOM;
                graphics.setColor(component.getForeground());
                graphics.setFont(font);
                fontMetrics = graphics.getFontMetrics();
            }
            else if (super.elementData[n4] instanceof Color) {
                graphics.setColor((Color)super.elementData[n4]);
            }
            else if (super.elementData[n4].equals(JCString.NEWLINE)) {
                return;
            }
        }
    }
    
    public String getURL(final int n, final int n2) {
        if (this.url_rect_list != null) {
            for (int i = 0; i < this.url_rect_list.size(); ++i) {
                if (((Rectangle)this.url_rect_list.elementAt(i)).inside(n, n2)) {
                    return (String)this.url_list.elementAt(i);
                }
            }
        }
        return null;
    }
    
    public static String getURL(Applet applet, final Object o, final int n, final int n2) {
        String url = null;
        if (applet != null) {
            try {
                if (applet.getAppletContext() == null) {
                    applet = null;
                }
            }
            catch (Exception ex) {
                applet = null;
            }
        }
        if (o instanceof JCString) {
            url = ((JCString)o).getURL(n, n2);
            if (url != null && applet != null) {
                final int max = Math.max(url.indexOf("TARGET="), url.indexOf("target="));
                if (max == -1) {
                    applet.showStatus(url);
                }
                else {
                    applet.showStatus(url.substring(0, max));
                }
            }
            JCString.has_url = true;
        }
        if (applet != null && JCString.has_url && url == null) {
            applet.showStatus(null);
        }
        if (url == null) {
            JCString.has_url = false;
        }
        return url;
    }
    
    public static boolean showURL(final String s, final AppletContext appletContext, final Applet applet) {
        if (s == null || appletContext == null) {
            return false;
        }
        final int index = s.indexOf("TARGET=");
        final String s2 = (index == -1) ? s : s.substring(0, index);
        final String s3 = (index == -1) ? null : s.substring(index + 7);
        try {
            final URL url = new URL(applet.getDocumentBase(), s2);
            if (s3 == null) {
                appletContext.showDocument(url);
            }
            else {
                appletContext.showDocument(url, s3);
            }
        }
        catch (Exception ex) {
            applet.showStatus("Invalid URL");
            return false;
        }
        return true;
    }
    
    public String getString() {
        String s = new String();
        for (int i = 0; i < this.size(); ++i) {
            if (super.elementData[i].equals(JCString.HREF)) {
                ++i;
            }
            else if (super.elementData[i] instanceof String) {
                s = s.concat((String)super.elementData[i]);
            }
            else if (super.elementData[i].equals(JCString.NEWLINE)) {
                s = s.concat("\n");
            }
        }
        return s;
    }
    
    public Image getImage() {
        for (int i = 0; i < this.size(); ++i) {
            if (super.elementData[i] instanceof Image) {
                return (Image)super.elementData[i];
            }
            if (super.elementData[i] instanceof JCImage) {
                return ((JCImage)super.elementData[i]).image;
            }
        }
        return null;
    }
    
    public String toString2() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.size(); ++i) {
            final Object element = this.elementAt(i);
            String s = "";
            if (element instanceof String) {
                s = element.toString();
            }
            else if (element == JCString.DEFAULT_FONT) {
                s = "[DEFAULT_FONT]";
            }
            else if (element == JCString.DEFAULT_COLOR) {
                s = "[DEFAULT_COLOR]";
            }
            else if (element == JCString.UNDERLINE) {
                s = "[UL]";
            }
            else if (element == JCString.END_UNDERLINE) {
                s = "[/UL]";
            }
            else if (element == JCString.STRIKETHROUGH) {
                s = "[ST]";
            }
            else if (element == JCString.END_STRIKETHROUGH) {
                s = "[/ST]";
            }
            else if (element == JCString.HREF) {
                s = "[HREF=" + this.elementAt(++i) + "]";
            }
            else if (element == JCString.END_HREF) {
                s = "[/HREF]";
            }
            else if (element == JCString.TOP) {
                s = "[ALIGN=TOP]";
            }
            else if (element == JCString.MIDDLE) {
                s = "[ALIGN=MIDDLE]";
            }
            else if (element == JCString.BOTTOM) {
                s = "[ALIGN=BOTTOM]";
            }
            else if (element == JCString.HORIZ_SPACE) {
                String string = "[HORIZ_SPACE";
                if (i < this.size() - 1 && this.isValue(super.elementData[i + 1])) {
                    string = String.valueOf(string) + "=" + super.elementData[++i];
                }
                s = String.valueOf(string) + "]";
            }
            else if (element == JCString.VERT_SPACE) {
                String string2 = "[VERT_SPACE";
                if (i < this.size() - 1 && this.isValue(super.elementData[i + 1])) {
                    string2 = String.valueOf(string2) + "=" + super.elementData[++i];
                }
                s = String.valueOf(string2) + "]";
            }
            else if (element == JCString.RESET) {
                s = "[RESET]";
            }
            else if (element == JCString.DEFAULT_COLOR) {
                s = "[COLOR=DEFAULT]";
            }
            else if (element instanceof Color) {
                s = "[COLOR=" + JCUtilConverter.fromColor((Color)element) + "]";
            }
            else if (element instanceof Image) {
                s = "";
            }
            else if (element instanceof JCImage) {
                s = "[IMG=" + ((JCImage)element).file + "]";
            }
            else if (element == JCString.DEFAULT_FONT) {
                s = "[FONT=DEFAULT]";
            }
            else if (element instanceof Font) {
                s = "[FONT=" + JCUtilConverter.fromFont((Font)element) + "]";
            }
            else if (element == JCString.NEWLINE) {
                s = "[NEWLINE]";
            }
            sb.append(s);
        }
        return sb.toString();
    }
    
    static {
        UNDERLINE = new Integer(-2);
        STRIKETHROUGH = new Integer(-3);
        END_UNDERLINE = new Integer(-4);
        END_STRIKETHROUGH = new Integer(-5);
        HORIZ_SPACE = new Integer(-6);
        VERT_SPACE = new Integer(-7);
        NEWLINE = new Integer(-8);
        RESET = new Integer(-9);
        DEFAULT_FONT = new Integer(-10);
        DEFAULT_COLOR = new Integer(-11);
        TOP = new Integer(-17);
        MIDDLE = new Integer(-18);
        BOTTOM = new Integer(-19);
        LEFT = new Integer(-17);
        CENTER = new Integer(-18);
        RIGHT = new Integer(-19);
        HREF = new Integer(-21);
        END_HREF = new Integer(-22);
        JCString.size = new Dimension();
        JCString.draw_rect = new Rectangle();
        JCString.reg_images = new Vector();
        JCString.is_img_caching = true;
        JCString.img_cache = new Hashtable();
    }
}
