// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.e;

import java.awt.Font;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.util.Date;
import java.text.DateFormat;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.PopupMenu;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.Dimension;
import java.util.Vector;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import flaxchat.i.d;
import java.awt.Image;
import java.awt.Component;

public class g
{
    public static boolean a;
    private static String z;
    
    public static Image a(final Component component, final Image image, final String s, final int n) {
        final int[] a = a(s, ',');
        if (a == null) {
            return null;
        }
        if (a.length == 0) {
            if (s == null) {
                return null;
            }
            return d.b(s);
        }
        else if (a.length < 4) {
            if (s == null) {
                return null;
            }
            final Image a2 = d.a(d.c(s));
            if (a2 == null) {
                return null;
            }
            if (n == -1) {
                return a2;
            }
            return a2.getScaledInstance(n, n, 2);
        }
        else {
            if (n == -1) {
                return a(component, image, a[0], a[1], a[2], a[3]);
            }
            return a(component, image, a[0], a[1], a[2], a[3]).getScaledInstance(n, n, 2);
        }
    }
    
    public static Image a(final Component component, Image image, final int n, final int n2, final int n3, final int n4) {
        image = component.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(n, n2, n3, n4)));
        d.a(image);
        return image;
    }
    
    public static int[] a(final String s, final char c) {
        final boolean a = g.a;
        final Vector a2 = p.a(s, c);
        final int[] array = new int[a2.size()];
        int n = 0;
        while (true) {
            Label_0062: {
                if (!a) {
                    break Label_0062;
                }
                final String s2 = a2.elementAt(n);
                try {
                    array[n] = Integer.parseInt(s2);
                }
                catch (NumberFormatException ex) {
                    array[n] = 0;
                }
                ++n;
            }
            if (n >= a2.size()) {
                return array;
            }
            continue;
        }
    }
    
    public static Point a(final Component component, final Dimension dimension) {
        Point locationOnScreen = null;
        Dimension dimension2 = null;
        Label_0037: {
            if (component == null) {
                locationOnScreen = new Point(0, 0);
                dimension2 = Toolkit.getDefaultToolkit().getScreenSize();
                if (!g.a) {
                    break Label_0037;
                }
            }
            locationOnScreen = component.getLocationOnScreen();
            dimension2 = component.getSize();
        }
        locationOnScreen.x += dimension2.width / 2 - dimension.width / 2;
        locationOnScreen.y += dimension2.height / 2 - dimension.height / 2;
        return locationOnScreen;
    }
    
    public static Component a(Component parent) {
        final boolean a = g.a;
        if (parent == null) {
            return null;
        }
        while (parent.getParent() != null) {
            parent = parent.getParent();
            if (a) {
                break;
            }
        }
        return parent;
    }
    
    public static int a(final String s, final Graphics graphics) {
        if (s == null) {
            return 0;
        }
        return graphics.getFontMetrics(graphics.getFont()).charsWidth(s.toCharArray(), 0, s.length());
    }
    
    public static int a(final Container container) {
        final boolean a = g.a;
        final int componentCount = container.getComponentCount();
        int max = 0;
        int n = 0;
        while (true) {
            Label_0059: {
                if (!a) {
                    break Label_0059;
                }
                final Component component = container.getComponent(n);
                max = Math.max(max, component.getLocation().x + component.getSize().width);
                ++n;
            }
            if (n >= componentCount) {
                return max;
            }
            continue;
        }
    }
    
    public static void a(final PopupMenu popupMenu, final Component component) {
        component.add(popupMenu);
        popupMenu.show(component, 0, component.getSize().height);
    }
    
    public static boolean a(final MouseEvent mouseEvent) {
        return (mouseEvent.getModifiers() & 0x4) == 0x4;
    }
    
    public static boolean b(final MouseEvent mouseEvent) {
        return (mouseEvent.getModifiers() & 0x10) == 0x10;
    }
    
    public static Color a(final Color color) {
        return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
    }
    
    public static void a(final Component component, final Component component2) {
        component.addKeyListener(new a(component, component2));
    }
    
    public static void a() {
        Toolkit.getDefaultToolkit().beep();
    }
    
    public static String a(final String s) {
        return DateFormat.getDateInstance().format(new Date(Long.parseLong(s)));
    }
    
    public static void b(final String s) {
        try {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(s), null);
        }
        catch (RuntimeException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static void a(final Graphics graphics, final Component component) {
        final Dimension size = component.getSize();
        graphics.setColor(component.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
    }
    
    public static PopupMenu b() {
        final PopupMenu popupMenu = new PopupMenu();
        popupMenu.setFont(new Font(g.z, 0, 12));
        return popupMenu;
    }
    
    public static int a(final String s, final Component component) {
        return component.getFontMetrics(component.getFont()).charsWidth(s.toCharArray(), 0, s.length());
    }
    
    static {
        g.z = z(z("K'_mPC6"));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '%';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '/';
                    break;
                }
                case 1: {
                    c2 = 'B';
                    break;
                }
                case 2: {
                    c2 = '9';
                    break;
                }
                case 3: {
                    c2 = '\f';
                    break;
                }
                default: {
                    c2 = '%';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
