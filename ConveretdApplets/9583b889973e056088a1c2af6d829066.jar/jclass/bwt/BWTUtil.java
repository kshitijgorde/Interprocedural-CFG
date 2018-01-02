// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Event;
import java.awt.Dimension;
import jclass.util.JCUtilConverter;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.FontMetrics;
import jclass.util.JCString;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.util.Vector;
import jclass.base.Border;
import java.awt.Color;
import java.applet.AppletContext;
import java.applet.Applet;
import java.awt.Window;
import java.awt.Frame;
import java.awt.Image;
import jclass.base.BaseComponent;
import java.awt.Point;
import java.awt.Container;
import java.awt.Component;

public class BWTUtil
{
    public static final int LEFT = 0;
    public static final int CENTER = 1;
    public static final int RIGHT = 2;
    
    public static final Object[] copyList(Object[] array, final int n, final Object o) {
        if (array != null && n <= array.length) {
            return array;
        }
        final Object[] array2 = array;
        final int n2 = (array2 != null) ? array2.length : 0;
        array = new Object[n];
        if (array2 != null) {
            System.arraycopy(array2, 0, array, 0, array2.length);
        }
        for (int i = n2; i < array.length; ++i) {
            array[i] = o;
        }
        return array;
    }
    
    public static final int[] copyList(int[] array, final int n, final int n2) {
        if (array != null && n <= array.length) {
            return array;
        }
        final int[] array2 = array;
        final int n3 = (array2 != null) ? array2.length : 0;
        array = new int[n];
        if (array2 != null) {
            System.arraycopy(array2, 0, array, 0, array2.length);
        }
        for (int i = n3; i < array.length; ++i) {
            array[i] = n2;
        }
        return array;
    }
    
    public static final boolean[] copyList(boolean[] array, final int n, final boolean b) {
        if (array != null && n <= array.length) {
            return array;
        }
        final boolean[] array2 = array;
        final int n2 = (array2 != null) ? array2.length : 0;
        array = new boolean[n];
        if (array2 != null) {
            System.arraycopy(array2, 0, array, 0, array2.length);
        }
        for (int i = n2; i < array.length; ++i) {
            array[i] = b;
        }
        return array;
    }
    
    public static final boolean intersects(final Component component, final int n, final int n2, final int n3, final int n4) {
        return component.size().width > n && component.size().height > n2 && n + n3 >= 0 && n2 + n4 >= 0;
    }
    
    public static int countChar(final String s, final char c, int n, final int n2) {
        if (s == null || n >= s.length() || n >= n2) {
            return 0;
        }
        if (n < 0) {
            n = 0;
        }
        int n3;
        for (n3 = 0, n = s.indexOf(c, n); n < n2 && n != -1; ++n, ++n3, n = s.indexOf(c, n)) {}
        return n3;
    }
    
    static final int indexOf(final char[] array, final int n, final int n2, final int n3) {
        for (int i = n3; i < n; ++i) {
            if (array[i] == n2) {
                return i;
            }
        }
        return -1;
    }
    
    public static int countChar(final char[] array, final int n, final char c, int n2, int min) {
        if (array == null || n2 >= n || n2 >= min) {
            return 0;
        }
        min = Math.min(min, n);
        if (n2 < 0) {
            n2 = 0;
        }
        int n3;
        for (n3 = 0, n2 = indexOf(array, n, c, n2); n2 < min && n2 != -1; ++n2, ++n3, n2 = indexOf(array, n, c, n2)) {}
        return n3;
    }
    
    public static Point translateToParent(final Container container, final Component component, final int n, final int n2) {
        return BaseComponent.translateToParent(container, component, n, n2);
    }
    
    public static Point translateFromParent(final Container container, final Component component, final int n, final int n2) {
        return BaseComponent.translateFromParent(container, component, n, n2);
    }
    
    public static Point getVisibleScreenLoc(final Component component, final int n, final int n2, final int n3, final int n4) {
        return BaseComponent.getVisibleScreenLoc(component, n, n2, n3, n4);
    }
    
    static synchronized Image createImage(final Component component, final Image image, final int n, final int n2) {
        return BaseComponent.createImage(component, image, n, n2);
    }
    
    static synchronized Image createImage(final Component component, final int n, final int n2) {
        return BaseComponent.createImage(component, n, n2);
    }
    
    public static Frame getFrame(final Component component) {
        return BaseComponent.getFrame(component);
    }
    
    public static Window getWindow(final Component component) {
        return BaseComponent.getWindow(component);
    }
    
    public static Applet getApplet(final Component component) {
        return BaseComponent.getApplet(component);
    }
    
    public static AppletContext getAppletContext(final Applet applet) {
        return BaseComponent.getAppletContext(applet);
    }
    
    public static boolean inBrowser(final Component component) {
        return getAppletContext(getApplet(component)) != null;
    }
    
    public static Color brighter(final Color color) {
        return Border.brighter(color);
    }
    
    public static Color darker(final Color color) {
        return Border.darker(color);
    }
    
    public static boolean instanceOf(final Object o, final String s) {
        if (o == null) {
            return false;
        }
        Class<?> clazz = o.getClass();
        boolean b;
        while (!(b = (clazz.getName().indexOf(s) != -1)) && (clazz = clazz.getSuperclass()) != null) {}
        return b;
    }
    
    static boolean is_jcstring(final Object o) {
        return instanceOf(o, "JCString");
    }
    
    public static boolean isParent(final Component component, final Component component2) {
        if (component2 == component) {
            return true;
        }
        if (component == null || component2 == null || !(component instanceof Container)) {
            return false;
        }
        for (Container container = component2.getParent(); container != null; container = container.getParent()) {
            if (container == component) {
                return true;
            }
        }
        return false;
    }
    
    public static int getWidth(final Vector vector, final JCMultiColumnInterface jcMultiColumnInterface) {
        if (vector == null) {
            return 0;
        }
        final int min = Math.min(jcMultiColumnInterface.getNumColumns(), vector.size());
        if (min == 0) {
            return 0;
        }
        int n = 0;
        for (int i = 0; i < min; ++i) {
            n += jcMultiColumnInterface.getColumnWidth(i);
        }
        return n;
    }
    
    public static int getWidth(final Object o, final Component component) {
        return getWidth(o, component, component.getFont());
    }
    
    public static int getWidth(final Object o, final Component component, final Font font) {
        if (o == null) {
            return 0;
        }
        if (o instanceof Image) {
            return ((Image)o).getWidth(null);
        }
        if (is_jcstring(o)) {
            return ((JCString)o).getWidth(component, font);
        }
        if (o instanceof Vector && component instanceof JCMultiColumnInterface) {
            return getWidth((Vector)o, (JCMultiColumnInterface)component);
        }
        if (o instanceof Vector) {
            int n = 0;
            for (int i = 0; i < ((Vector)o).size(); ++i) {
                n += getWidth(((Vector)o).elementAt(i), component);
            }
            return n;
        }
        final String string = o.toString();
        if (string == null || string.length() == 0) {
            return 0;
        }
        final FontMetrics fontMetrics = component.getToolkit().getFontMetrics(font);
        if (string.indexOf(10) != -1) {
            int n2 = 0;
            int max = 0;
            int index;
            while ((index = string.indexOf(10, n2)) != -1) {
                max = Math.max(max, stringWidth(fontMetrics, font, string.substring(n2, index)));
                if (font.isItalic()) {
                    max += 5;
                }
                n2 = index + 1;
            }
            return Math.max(max, stringWidth(fontMetrics, font, string.substring(n2, string.length())));
        }
        return stringWidth(fontMetrics, font, string);
    }
    
    static int stringWidth(final FontMetrics fontMetrics, final Font font, final String s) {
        return fontMetrics.stringWidth(s) + (font.isItalic() ? (font.getSize() / 3 + 1) : 0);
    }
    
    public static int getHeight(final Object o, final Component component) {
        return getHeight(o, component, component.getFont());
    }
    
    public static int getHeight(final Object o, final Component component, final Font font) {
        if (o == null) {
            return 0;
        }
        if (o instanceof Image) {
            return ((Image)o).getHeight(null);
        }
        if (is_jcstring(o)) {
            return ((JCString)o).getHeight(component, font);
        }
        if (o instanceof Vector) {
            final Vector vector = (Vector)o;
            int max = 0;
            for (int i = 0; i < vector.size(); ++i) {
                max = Math.max(max, getHeight(vector.elementAt(i), component, font));
            }
            return max;
        }
        return component.getToolkit().getFontMetrics(font).getHeight() * getNumLines(o.toString());
    }
    
    private static int getNumLines(final String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = 1;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '\n') {
                ++n;
            }
        }
        return n;
    }
    
    public static int getNumLines(final Object o) {
        return getNumLines(toString(o));
    }
    
    static String toString(final Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof Image) {
            return null;
        }
        if (is_jcstring(o)) {
            return ((JCString)o).getString();
        }
        if (o instanceof Vector) {
            for (int i = 0; i < ((Vector)o).size(); ++i) {
                final String string;
                if ((string = toString(((Vector)o).elementAt(i))) != null) {
                    return string;
                }
            }
            return null;
        }
        return o.toString();
    }
    
    static boolean startsWith(final Object o, final char c) {
        final String string = toString(o);
        return string != null && string.length() > 0 && Character.toUpperCase(string.charAt(0)) == c;
    }
    
    public static int toHorizAlignment(final int n) {
        if (isCenter(n)) {
            return 1;
        }
        if (isRight(n)) {
            return 2;
        }
        return 0;
    }
    
    static boolean isRight(final int n) {
        return n == 2 || n == 5 || n == 8;
    }
    
    static boolean isCenter(final int n) {
        return n == 1 || n == 4 || n == 7;
    }
    
    static boolean isTop(final int n) {
        return n == 0 || n == 1 || n == 2;
    }
    
    static boolean isMiddle(final int n) {
        return n == 3 || n == 4 || n == 5;
    }
    
    static boolean isBottom(final int n) {
        return n == 6 || n == 7 || n == 8;
    }
    
    public static synchronized void draw(final Component component, final Graphics graphics, final Object o, final int n, final Rectangle rectangle) {
        if (o == null) {
            return;
        }
        final int horizAlignment = toHorizAlignment(n);
        if (is_jcstring(o)) {
            ((JCString)o).draw(component, graphics, rectangle, horizAlignment);
            return;
        }
        if (o instanceof Image) {
            graphics.drawImage((Image)o, rectangle.x, rectangle.y, null);
            return;
        }
        final String string = o.toString();
        if (string == null || string.length() == 0) {
            return;
        }
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final Font font = graphics.getFont();
        final int height = fontMetrics.getHeight();
        int n2 = 0;
        int n3 = rectangle.y + height - (height - fontMetrics.getAscent());
        final int n4 = isTop(n) ? 0 : getHeight(string, component, font);
        if (isBottom(n)) {
            n3 += rectangle.height - n4;
        }
        else if (isMiddle(n)) {
            n3 += (rectangle.height - n4) / 2;
        }
        if (string.indexOf(10) != -1) {
            int n5;
            int index;
            for (n5 = 0; (index = string.indexOf(10, n5)) != -1; n5 = index + 1, n3 += height) {
                final String substring = string.substring(n5, index);
                if (horizAlignment == 1) {
                    n2 = (rectangle.width - stringWidth(fontMetrics, font, substring)) / 2;
                }
                else if (horizAlignment == 2) {
                    n2 = rectangle.width - stringWidth(fontMetrics, font, substring);
                }
                graphics.drawString(substring, rectangle.x + n2, n3);
            }
            final String substring2 = string.substring(n5, string.length());
            if (horizAlignment == 1) {
                n2 = (rectangle.width - stringWidth(fontMetrics, font, substring2)) / 2;
            }
            else if (horizAlignment == 2) {
                n2 = rectangle.width - stringWidth(fontMetrics, font, substring2);
            }
            graphics.drawString(substring2, rectangle.x + n2, n3);
            return;
        }
        if (horizAlignment == 1) {
            n2 = (rectangle.width - stringWidth(fontMetrics, font, string)) / 2;
        }
        else if (horizAlignment == 2) {
            n2 = rectangle.width - stringWidth(fontMetrics, font, string);
        }
        graphics.drawString(string, rectangle.x + n2, n3);
    }
    
    public static void trace() {
        try {
            throw new ArrayIndexOutOfBoundsException("");
        }
        catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void drawDashedLine(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final int min = Math.min(n, n3);
        final int min2 = Math.min(n2, n4);
        final int max = Math.max(n, n3);
        final int max2 = Math.max(n2, n4);
        if (n3 == n) {
            for (int i = min2; i < max2; i = Math.min(i + 3, max2)) {
                graphics.drawLine(n, i, n, i + 1);
            }
            return;
        }
        for (int j = min; j < max; j = Math.min(j + 3, max)) {
            graphics.drawLine(j, n2, j + 1, n2);
        }
    }
    
    public static void drawDashedRect(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        drawDashedLine(graphics, n, n2, n + n3, n2);
        drawDashedLine(graphics, n + n3, n2, n + n3, n2 + n4);
        drawDashedLine(graphics, n, n2, n, n2 + n4);
        drawDashedLine(graphics, n, n2 + n4, n + n3, n2 + n4);
    }
    
    static void fill3DRect(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final boolean b) {
        final Color color = graphics.getColor();
        final Color brighter = brighter(color);
        final Color darker = darker(color);
        if (!b) {
            graphics.setColor(darker);
        }
        graphics.fillRect(n + 1, n2 + 1, n3 - 2, n4 - 2);
        graphics.setColor(b ? brighter : darker);
        graphics.drawLine(n, n2, n, n2 + n4 - 1);
        graphics.drawLine(n + 1, n2, n + n3 - 2, n2);
        graphics.setColor(b ? darker : brighter);
        graphics.drawLine(n + 1, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
        graphics.drawLine(n + n3 - 1, n2, n + n3 - 1, n2 + n4 - 2);
        graphics.setColor(color);
    }
    
    static void fill3DEdgeRect(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final boolean b) {
        final Color color = graphics.getColor();
        fill3DRect(graphics, n + 1, n2 + 1, n3 - 2, n4 - 2, b);
        graphics.setColor(Color.black);
        graphics.drawRect(n, n2, n3 - 1, n4 - 1);
        graphics.setColor(color);
    }
    
    public static void wallPaper(final Component component, final Graphics graphics, final Image image) {
        final Dimension size = component.size();
        if (!JCUtilConverter.waitForImage(component, image)) {
            return;
        }
        final int width = image.getWidth(component);
        final int height = image.getHeight(component);
        for (int i = 0; i < size.width; i += width) {
            for (int j = 0; j < size.height; j += height) {
                graphics.drawImage(image, i, j, component);
            }
        }
    }
    
    public static int getMouseButton(final Event event) {
        if (event.metaDown()) {
            return 3;
        }
        if ((event.modifiers & 0x8) != 0x0) {
            return 2;
        }
        return 1;
    }
    
    public static void setCursor(final Component component, final int n) {
        final Window window = getWindow(component);
        if (window instanceof Dialog && ((Dialog)window).isModal()) {
            return;
        }
        if (window != null) {
            window.setCursor(Cursor.getPredefinedCursor(n));
            component.getToolkit().sync();
        }
    }
}
