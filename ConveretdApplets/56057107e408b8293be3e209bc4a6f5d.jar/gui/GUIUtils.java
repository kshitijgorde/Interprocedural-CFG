// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ComponentListener;
import java.awt.event.MouseMotionListener;
import anon.util.JobQueue;
import anon.util.CountryMapper;
import anon.infoservice.ServiceLocation;
import javax.swing.JFileChooser;
import javax.swing.UIDefaults;
import java.awt.datatransfer.DataFlavor;
import java.awt.IllegalComponentStateException;
import javax.swing.SwingUtilities;
import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.MenuComponent;
import javax.swing.Icon;
import gui.dialog.JAPDialog;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.util.Enumeration;
import javax.swing.plaf.FontUIResource;
import javax.swing.LookAndFeel;
import anon.util.ClassUtil;
import javax.swing.UIManager;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.awt.datatransfer.Clipboard;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Frame;
import javax.swing.ToolTipManager;
import javax.swing.KeyStroke;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import java.awt.Font;
import javax.swing.JOptionPane;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Container;
import java.applet.Applet;
import java.awt.Window;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.URL;
import java.awt.image.ColorModel;
import anon.util.IReturnRunnable;
import anon.util.JAPMessages;
import java.awt.Toolkit;
import anon.util.ResourceLoader;
import java.awt.Image;
import javax.swing.ImageIcon;
import logging.LogHolder;
import logging.LogType;
import java.util.Hashtable;
import java.util.Vector;
import java.awt.Point;

public final class GUIUtils
{
    public static final String aktVersion = "00.00.002";
    public static final String MSG_DEFAULT_IMGAGE_PATH;
    public static final String MSG_DEFAULT_IMGAGE_PATH_LOWCOLOR;
    private static final String MSG_PASTE_FILE;
    private static final String MSG_COPY_FROM_CLIP;
    private static final String MSG_SAVED_TO_CLIP;
    private static final int MAXIMUM_TEXT_LENGTH = 60;
    private static boolean ms_loadImages;
    private static boolean ms_bCapturingAWTEvents;
    private static Point ms_mousePosition;
    private static final Object SYNC_MOUSE_POSITION;
    private static AWTEventListener ms_mouseListener;
    private static final Vector AWT_EVENT_LISTENERS;
    private static final IIconResizer DEFAULT_RESIZER;
    private static IIconResizer ms_resizer;
    private static final NativeGUILibrary DUMMY_GUI_LIBRARY;
    private static NativeGUILibrary ms_nativeGUILibrary;
    private static final IIconResizer RESIZER;
    private static Hashtable ms_iconCache;
    static /* synthetic */ Class class$gui$GUIUtils;
    static /* synthetic */ Class class$java$awt$Window;
    static /* synthetic */ Class class$javax$swing$JComponent;
    static /* synthetic */ Class class$javax$swing$KeyStroke;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$awt$Frame;
    static /* synthetic */ Class class$java$awt$Toolkit;
    static /* synthetic */ Class class$javax$swing$LookAndFeel;
    
    public static final IIconResizer getIconResizer() {
        return GUIUtils.RESIZER;
    }
    
    public static void setLoadImages(final boolean ms_loadImages) {
        if (GUIUtils.ms_loadImages && !ms_loadImages) {
            LogHolder.log(5, LogType.GUI, "Loading of images has been stopped!");
        }
        GUIUtils.ms_loadImages = ms_loadImages;
    }
    
    public static boolean isLoadingImagesStopped() {
        return !GUIUtils.ms_loadImages;
    }
    
    public static final void setIconResizer(final IIconResizer ms_resizer) {
        if (ms_resizer != null) {
            GUIUtils.ms_resizer = ms_resizer;
        }
        else {
            GUIUtils.ms_resizer = GUIUtils.DEFAULT_RESIZER;
        }
    }
    
    public static ImageIcon loadImageIcon(final String s) {
        return loadImageIcon(s, true, true);
    }
    
    public static ImageIcon loadImageIcon(final String s, final boolean b) {
        return loadImageIcon(s, b, true);
    }
    
    public static ImageIcon loadImageIcon(final String s, final boolean b, final boolean b2) {
        ImageIcon imageIcon = null;
        boolean b3 = false;
        String string = null;
        if (s == null) {
            return null;
        }
        if (b2 && GUIUtils.ms_resizer.getResizeFactor() != 1.0) {
            string = (int)(100.0 * GUIUtils.ms_resizer.getResizeFactor()) + "/" + s;
        }
        if (string != null && GUIUtils.ms_iconCache.containsKey(string)) {
            imageIcon = new ImageIcon((Image)GUIUtils.ms_iconCache.get(string));
            if (imageIcon != null) {
                b3 = true;
            }
        }
        else if (GUIUtils.ms_iconCache.containsKey(s)) {
            imageIcon = new ImageIcon(GUIUtils.ms_iconCache.get(s));
        }
        if (imageIcon == null && GUIUtils.ms_loadImages) {
            if (string != null) {
                imageIcon = loadImageIconInternal(ResourceLoader.getResourceURL(string));
                if (imageIcon != null) {
                    b3 = true;
                }
            }
            if (imageIcon == null) {
                imageIcon = loadImageIconInternal(ResourceLoader.getResourceURL(s));
            }
            ColorModel colorModel = null;
            try {
                colorModel = Toolkit.getDefaultToolkit().getColorModel();
            }
            catch (Throwable t) {}
            if (imageIcon == null && (colorModel == null || colorModel.getPixelSize() <= 16)) {
                if (string != null) {
                    imageIcon = loadImageIconInternal(ResourceLoader.getResourceURL(JAPMessages.getString(GUIUtils.MSG_DEFAULT_IMGAGE_PATH_LOWCOLOR) + string));
                    if (imageIcon != null) {
                        b3 = true;
                    }
                }
                if (imageIcon == null) {
                    imageIcon = loadImageIconInternal(ResourceLoader.getResourceURL(JAPMessages.getString(GUIUtils.MSG_DEFAULT_IMGAGE_PATH_LOWCOLOR) + s));
                }
            }
            if (imageIcon == null || imageIcon.getImageLoadStatus() == 4) {
                if (string != null) {
                    imageIcon = loadImageIconInternal(ResourceLoader.getResourceURL(JAPMessages.getString(GUIUtils.MSG_DEFAULT_IMGAGE_PATH) + string));
                    if (imageIcon != null) {
                        b3 = true;
                    }
                }
                if (imageIcon == null) {
                    imageIcon = loadImageIconInternal(ResourceLoader.getResourceURL(JAPMessages.getString(GUIUtils.MSG_DEFAULT_IMGAGE_PATH) + s));
                }
            }
            if (imageIcon != null) {
                if (b) {
                    while ((imageIcon.getImageLoadStatus() & 0xE) == 0x0) {
                        Thread.yield();
                    }
                }
                if (string != null && b3) {
                    GUIUtils.ms_iconCache.put(string, imageIcon.getImage());
                }
                else {
                    GUIUtils.ms_iconCache.put(s, imageIcon.getImage());
                }
            }
            final int n = 6;
            if (imageIcon == null || (imageIcon.getImageLoadStatus() & n) != 0x0) {
                LogHolder.log(6, LogType.GUI, "Could not load requested image '" + s + "'!");
            }
        }
        if (b2 && !b3 && GUIUtils.ms_loadImages && GUIUtils.ms_resizer.getResizeFactor() != 1.0) {
            final IReturnRunnable returnRunnable = new IReturnRunnable() {
                private ImageIcon m_icon;
                
                public void run() {
                    this.m_icon = GUIUtils.createScaledImageIcon(imageIcon, GUIUtils.ms_resizer);
                }
                
                public Object getValue() {
                    return this.m_icon;
                }
            };
            final Thread thread = new Thread(returnRunnable);
            thread.setDaemon(true);
            thread.start();
            try {
                thread.join(1000L);
            }
            catch (InterruptedException ex) {}
            while (thread.isAlive()) {
                thread.interrupt();
                try {
                    thread.join();
                }
                catch (InterruptedException ex2) {}
            }
            if (returnRunnable.getValue() != null) {
                return (ImageIcon)returnRunnable.getValue();
            }
            if (imageIcon != null && returnRunnable.getValue() == null) {
                LogHolder.log(3, LogType.GUI, "Interrupted while scaling image icon!");
            }
        }
        return imageIcon;
    }
    
    private static ImageIcon loadImageIconInternal(final URL url) {
        try {
            return new ImageIcon(url);
        }
        catch (NullPointerException ex) {
            return null;
        }
    }
    
    public static ImageIcon combine(final ImageIcon imageIcon, final ImageIcon imageIcon2) {
        final int n = imageIcon.getIconWidth() + imageIcon2.getIconWidth();
        final int max = Math.max(imageIcon.getIconHeight(), imageIcon2.getIconHeight());
        try {
            final Class<?> forName = Class.forName("java.awt.image.BufferedImage");
            final Image image = (Image)forName.getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(new Integer(n), new Integer(max), new Integer(forName.getField("TYPE_INT_ARGB").getInt(forName)));
            final Graphics graphics = (Graphics)forName.getMethod("createGraphics", (Class[])null).invoke(image, (Object[])null);
            graphics.drawImage(imageIcon.getImage(), 0, 0, null);
            graphics.drawImage(imageIcon2.getImage(), imageIcon.getIconWidth(), 0, null);
            graphics.dispose();
            return new ImageIcon(image);
        }
        catch (Exception ex) {
            return imageIcon;
        }
    }
    
    public static void setLocationRelativeTo(final Component component, final Window window) {
        if (component == null && window == null) {
            return;
        }
        Component component2 = null;
        if (component != null) {
            if (component instanceof Window || component instanceof Applet) {
                component2 = component;
            }
            else {
                for (Container container = component.getParent(); container != null; container = container.getParent()) {
                    if (container instanceof Window || container instanceof Applet) {
                        component2 = container;
                        break;
                    }
                }
            }
        }
        if ((component != null && !component.isShowing()) || component2 == null || !component2.isShowing()) {
            final Dimension size = window.getSize();
            final Dimension screenSize = window.getToolkit().getScreenSize();
            window.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
        }
        else {
            final Dimension size2 = component.getSize();
            Point locationOnScreen;
            if (component2 instanceof Applet) {
                locationOnScreen = component.getLocationOnScreen();
            }
            else {
                locationOnScreen = new Point(0, 0);
                for (Component parent = component; parent != null; parent = parent.getParent()) {
                    final Point location = parent.getLocation();
                    final Point point = locationOnScreen;
                    point.x += location.x;
                    final Point point2 = locationOnScreen;
                    point2.y += location.y;
                    if (parent == component2) {
                        break;
                    }
                }
            }
            final Rectangle bounds = window.getBounds();
            int n = locationOnScreen.x + (size2.width - bounds.width >> 1);
            int n2 = locationOnScreen.y + (size2.height - bounds.height >> 1);
            final Dimension screenSize2 = window.getToolkit().getScreenSize();
            if (n2 + bounds.height > screenSize2.height) {
                n2 = screenSize2.height - bounds.height;
                n = ((locationOnScreen.x < screenSize2.width >> 1) ? (locationOnScreen.x + size2.width) : (locationOnScreen.x - bounds.width));
            }
            if (n + bounds.width > screenSize2.width) {
                n = screenSize2.width - bounds.width;
            }
            if (n < 0) {
                n = 0;
            }
            if (n2 < 0) {
                n2 = 0;
            }
            window.setLocation(n, n2);
        }
    }
    
    public static Window getParentWindow(final Component component) {
        Component component2 = component;
        if (component2 == null) {
            component2 = new JOptionPane().createDialog(component2, "").getParent();
        }
        while (component2 != null && !(component2 instanceof Window)) {
            component2 = component2.getParent();
        }
        return (Window)component2;
    }
    
    public static void positionRightUnderWindow(final Window window, final Window window2) {
        if (window == null || window2 == null) {
            return;
        }
        final Dimension size = window2.getSize();
        final Dimension size2 = window.getSize();
        final Point locationOnScreen = window2.getLocationOnScreen();
        window.setLocation(locationOnScreen.x + size.width / 2 - size2.width / 2, locationOnScreen.y + 40);
    }
    
    public static void moveToUpRightCorner(final Window window) {
        final Screen currentScreen = getCurrentScreen(window);
        window.setLocation(currentScreen.getX() + (currentScreen.getWidth() - window.getSize().width), currentScreen.getY());
    }
    
    public static void setNativeGUILibrary(final NativeGUILibrary ms_nativeGUILibrary) {
        if (ms_nativeGUILibrary != null) {
            GUIUtils.ms_nativeGUILibrary = ms_nativeGUILibrary;
        }
    }
    
    public static boolean isAlwaysOnTop(final Component component) {
        return isAlwaysOnTop(getParentWindow(component));
    }
    
    public static boolean isAlwaysOnTop(final Window window) {
        if (window == null) {
            return false;
        }
        try {
            return (boolean)((GUIUtils.class$java$awt$Window == null) ? (GUIUtils.class$java$awt$Window = class$("java.awt.Window")) : GUIUtils.class$java$awt$Window).getMethod("isAlwaysOnTop", (Class[])new Class[0]).invoke(window, new Object[0]);
        }
        catch (Throwable t) {
            return GUIUtils.ms_nativeGUILibrary.isAlwaysOnTop(window);
        }
    }
    
    public static void setFontStyle(final Component component, final int n) {
        if (component == null) {
            return;
        }
        component.setFont(new Font(component.getFont().getName(), n, component.getFont().getSize()));
    }
    
    public static boolean setAlwaysOnTop(final Component component, final boolean b) {
        return setAlwaysOnTop(getParentWindow(component), b);
    }
    
    public static boolean hasJavaOnTop() {
        try {
            ((GUIUtils.class$java$awt$Window == null) ? (GUIUtils.class$java$awt$Window = class$("java.awt.Window")) : GUIUtils.class$java$awt$Window).getMethod("setAlwaysOnTop", Boolean.TYPE);
        }
        catch (NoSuchMethodException ex) {
            return false;
        }
        return true;
    }
    
    public static boolean setAlwaysOnTop(final Window window, final boolean b) {
        if (window == null) {
            return false;
        }
        try {
            ((GUIUtils.class$java$awt$Window == null) ? (GUIUtils.class$java$awt$Window = class$("java.awt.Window")) : GUIUtils.class$java$awt$Window).getMethod("setAlwaysOnTop", Boolean.TYPE).invoke(window, new Boolean(b));
            return true;
        }
        catch (Throwable t) {
            return GUIUtils.ms_nativeGUILibrary.setAlwaysOnTop(window, b);
        }
    }
    
    public static boolean restoreSize(final Window window, final Dimension size) {
        if (window == null || size == null) {
            return false;
        }
        window.setSize(size);
        final Screen currentScreen = getCurrentScreen(window);
        int n = window.getSize().width;
        int n2 = window.getSize().height;
        if (window.getLocation().x + n > currentScreen.getX() + currentScreen.getWidth()) {
            n = currentScreen.getX() + currentScreen.getWidth() - window.getLocation().x;
        }
        if (window.getLocation().y + n2 > currentScreen.getY() + currentScreen.getHeight()) {
            n2 = currentScreen.getY() + currentScreen.getHeight() - window.getLocation().y;
        }
        if (n == 0) {
            n = window.getSize().width;
        }
        if (n2 == 0) {
            n2 = window.getSize().height;
        }
        window.setSize(n, n2);
        return true;
    }
    
    public static Point getMiddlePoint(final Window window) {
        if (window == null) {
            return new Point(0, 0);
        }
        return new Point(window.getLocation().x + window.getSize().width / 2, window.getLocation().y + window.getSize().height / 2);
    }
    
    public static boolean restoreLocation(final Window window, final Point location) {
        if (window == null || location == null) {
            return false;
        }
        double n = -1.0;
        double n2 = Double.MAX_VALUE;
        Screen screen = null;
        window.setLocation(location);
        final Point middlePoint = getMiddlePoint(window);
        final Screen[] screens = getScreens(window);
        int n3 = location.x;
        final int width = window.getSize().width;
        int n4 = location.y;
        final int height = window.getSize().height;
        if (screens.length == 0) {
            return false;
        }
        for (int i = 0; i < screens.length; ++i) {
            if (middlePoint.x >= screens[i].getX() && middlePoint.y >= screens[i].getY() && middlePoint.x <= screens[i].getX() + screens[i].getWidth() && middlePoint.y <= screens[i].getY() + screens[i].getHeight()) {
                screen = screens[i];
                break;
            }
            final boolean b = n3 >= screens[i].getX() && n3 <= screens[i].getX() + screens[i].getWidth() && n4 >= screens[i].getY() && n4 <= screens[i].getY() + screens[i].getHeight();
            final boolean b2 = n3 + width >= screens[i].getX() && n3 + width <= screens[i].getX() + screens[i].getWidth() && n4 >= screens[i].getY() && n4 <= screens[i].getY() + screens[i].getHeight();
            final boolean b3 = n4 + height >= screens[i].getY() && n4 + height <= screens[i].getY() + screens[i].getHeight() && n3 >= screens[i].getX() && n3 <= screens[i].getX() + screens[i].getWidth();
            final boolean b4 = n4 + height >= screens[i].getY() && n3 + width >= screens[i].getX() && n4 + height <= screens[i].getY() + screens[i].getHeight() && n3 + width <= screens[i].getX() + screens[i].getWidth();
            if (b || b2 || b3 || b4) {
                int x;
                if (b || b3) {
                    x = n3;
                }
                else {
                    x = screens[i].getX();
                }
                int n5;
                if (b2 || b4) {
                    n5 = n3 + width;
                }
                else {
                    n5 = screens[i].getX() + screens[i].getWidth();
                }
                int y;
                if (b || b2) {
                    y = n4;
                }
                else {
                    y = screens[i].getY();
                }
                int n6;
                if (b3 || b4) {
                    n6 = n4 + height;
                }
                else {
                    n6 = screens[i].getY() + screens[i].getHeight();
                }
                final int n7 = (n5 - x) * (n6 - y);
                LogHolder.log(6, LogType.GUI, "Calculated partial overlapping area for restoring window location: " + n7);
                if (n7 >= n) {
                    n = n7;
                    screen = screens[i];
                }
            }
        }
        if (screen == null) {
            for (int j = 0; j < screens.length; ++j) {
                final Point point = new Point(screens[j].getX() + screens[j].getWidth() / 2, screens[j].getY() + screens[j].getHeight() / 2);
                final double sqrt = Math.sqrt(Math.pow(middlePoint.x - point.x, 2.0) + Math.pow(middlePoint.y - point.y, 2.0));
                LogHolder.log(6, LogType.GUI, "Calculated distance vector for restoring window location: " + sqrt);
                if (sqrt < n2) {
                    screen = screens[j];
                    n2 = sqrt;
                }
            }
        }
        LogHolder.log(5, LogType.GUI, "The following screen was chosen for restoring a window location:\n" + screen);
        if (n3 + window.getSize().width > screen.getX() + screen.getWidth()) {
            n3 = screen.getX() + screen.getWidth() - window.getSize().width;
        }
        if (n4 + window.getSize().height > screen.getY() + screen.getHeight()) {
            n4 = screen.getY() + screen.getHeight() - window.getSize().height;
        }
        if (n3 < screen.getX()) {
            n3 = screen.getX();
        }
        if (n4 < screen.getY()) {
            n4 = screen.getY();
        }
        window.setLocation(n3, n4);
        return true;
    }
    
    public static MouseListener addTimedTooltipListener(final JComponent component) {
        try {
            final Class<?> forName = Class.forName("javax.swing.InputMap");
            final Object invoke = ((GUIUtils.class$javax$swing$JComponent == null) ? (GUIUtils.class$javax$swing$JComponent = class$("javax.swing.JComponent")) : GUIUtils.class$javax$swing$JComponent).getMethod("getInputMap", (Class[])new Class[0]).invoke(component, new Object[0]);
            ((GUIUtils.class$javax$swing$JComponent == null) ? (GUIUtils.class$javax$swing$JComponent = class$("javax.swing.JComponent")) : GUIUtils.class$javax$swing$JComponent).getMethod("getActionMap", (Class[])new Class[0]).invoke(component, new Object[0]);
            boolean b = false;
            final KeyStroke[] array = (KeyStroke[])forName.getMethod("keys", (Class[])new Class[0]).invoke(invoke, new Object[0]);
            if (array == null || array.length == 0) {
                forName.getMethod("put", (GUIUtils.class$javax$swing$KeyStroke == null) ? (GUIUtils.class$javax$swing$KeyStroke = class$("javax.swing.KeyStroke")) : GUIUtils.class$javax$swing$KeyStroke, (GUIUtils.class$java$lang$Object == null) ? (GUIUtils.class$java$lang$Object = class$("java.lang.Object")) : GUIUtils.class$java$lang$Object).invoke(invoke, KeyStroke.getKeyStroke(92, 0), "backSlash");
                b = true;
            }
            ToolTipManager.sharedInstance().registerComponent(component);
            if (b) {
                forName.getMethod("remove", (GUIUtils.class$javax$swing$KeyStroke == null) ? (GUIUtils.class$javax$swing$KeyStroke = class$("javax.swing.KeyStroke")) : GUIUtils.class$javax$swing$KeyStroke).invoke(invoke, KeyStroke.getKeyStroke(92, 0));
            }
            final ToolTipMouseListener toolTipMouseListener = new ToolTipMouseListener();
            component.addMouseListener(toolTipMouseListener);
            return toolTipMouseListener;
        }
        catch (Exception ex) {
            LogHolder.log(5, LogType.GUI, "Could not register component for timed tooltip!", ex);
            return null;
        }
    }
    
    public static Screen[] getScreens(final Window window) {
        try {
            final Object invoke = Class.forName("java.awt.GraphicsEnvironment").getMethod("getLocalGraphicsEnvironment", (Class<?>[])null).invoke(null, (Object[])null);
            final Object[] array = (Object[])invoke.getClass().getMethod("getScreenDevices", (Class<?>[])null).invoke(invoke, (Object[])null);
            final Screen[] array2 = new Screen[array.length];
            for (int i = 0; i < array.length; ++i) {
                final Object invoke2 = array[i].getClass().getMethod("getDefaultConfiguration", (Class<?>[])null).invoke(array[i], (Object[])null);
                array2[i] = new Screen(((GUIUtils.class$java$awt$Frame == null) ? (GUIUtils.class$java$awt$Frame = class$("java.awt.Frame")) : GUIUtils.class$java$awt$Frame).getConstructor(Class.forName("java.awt.GraphicsConfiguration")).newInstance(invoke2).getLocation(), (Rectangle)invoke2.getClass().getMethod("getBounds", (Class<?>[])null).invoke(invoke2, (Object[])null));
            }
            return array2;
        }
        catch (Exception ex) {
            return new Screen[] { new Screen(new Point(0, 0), getDefaultScreenBounds(window)) };
        }
    }
    
    public static Screen getCurrentScreen(final Component component) {
        return getCurrentScreen(getParentWindow(component));
    }
    
    public static Screen getCurrentScreen(final Window window) {
        if (window == null) {
            return null;
        }
        try {
            final Object invoke = Class.forName("java.awt.GraphicsEnvironment").getMethod("getLocalGraphicsEnvironment", (Class<?>[])null).invoke(null, (Object[])null);
            final Object[] array = (Object[])invoke.getClass().getMethod("getScreenDevices", (Class<?>[])null).invoke(invoke, (Object[])null);
            final Point middlePoint = getMiddlePoint(window);
            for (int i = 0; i < array.length; ++i) {
                final Object invoke2 = array[i].getClass().getMethod("getDefaultConfiguration", (Class<?>[])null).invoke(array[i], (Object[])null);
                final Point location = ((GUIUtils.class$java$awt$Frame == null) ? (GUIUtils.class$java$awt$Frame = class$("java.awt.Frame")) : GUIUtils.class$java$awt$Frame).getConstructor(Class.forName("java.awt.GraphicsConfiguration")).newInstance(invoke2).getLocation();
                final Rectangle rectangle = (Rectangle)invoke2.getClass().getMethod("getBounds", (Class<?>[])null).invoke(invoke2, (Object[])null);
                if (middlePoint.x >= location.x && middlePoint.x <= location.x + rectangle.width && middlePoint.y >= location.y && middlePoint.y <= location.y + rectangle.height) {
                    return getOverlappingScreen(new Screen(location, rectangle), window);
                }
            }
        }
        catch (Exception ex) {}
        return new Screen(new Point(0, 0), getDefaultScreenBounds(window));
    }
    
    public static void centerOnScreen(final Window window) {
        final Rectangle defaultScreenBounds = getDefaultScreenBounds(window);
        final Dimension size = window.getSize();
        window.setLocation(defaultScreenBounds.x + (defaultScreenBounds.width - size.width) / 2, defaultScreenBounds.y + (defaultScreenBounds.height - size.height) / 2);
    }
    
    public static void centerOnWindow(final Window window, final Window window2) {
        if (window == null || window2 == null) {
            return;
        }
        final Dimension size = window2.getSize();
        final Dimension size2 = window.getSize();
        final Point locationOnScreen = window2.getLocationOnScreen();
        window.setLocation(locationOnScreen.x + size.width / 2 - size2.width / 2, locationOnScreen.y + size.height / 2 - size2.height / 2);
    }
    
    public static JTextPane createSelectableAndResizeableLabel(final Component component) {
        final JTextPane textPane = new JTextPane();
        textPane.setBackground(component.getBackground());
        textPane.setEditable(false);
        textPane.setDisabledTextColor(textPane.getCaretColor());
        final Font font = new JLabel().getFont();
        textPane.setFont(new Font(font.getName(), 1, font.getSize()));
        return textPane;
    }
    
    public static JLabel createMultiLineLabel(final String s, final int preferredWidth) {
        final JAPHtmlMultiLineLabel japHtmlMultiLineLabel = new JAPHtmlMultiLineLabel();
        japHtmlMultiLineLabel.setText(JAPMessages.getString(s));
        japHtmlMultiLineLabel.setPreferredWidth(preferredWidth);
        return japHtmlMultiLineLabel;
    }
    
    public static JLabel createLabel(final String s) {
        return createLabel(new String[] { s });
    }
    
    public static JLabel createLabel(final String s, final String s2) {
        return createLabel(new String[] { s, s2 });
    }
    
    public static JButton createButton(final String s) {
        return new JButton(JAPMessages.getString(s));
    }
    
    public static JLabel createLabel(final String[] array) {
        final StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < array.length; ++i) {
            sb.append(JAPMessages.getString(array[i]) + ((i < array.length - 1) ? "/" : ""));
        }
        return new JLabel(sb.toString());
    }
    
    public static boolean isMouseButton(final MouseEvent mouseEvent, final int n) {
        return (mouseEvent.getModifiers() & n) == n;
    }
    
    public static Clipboard getSystemClipboard() {
        Clipboard systemClipboard = null;
        try {
            systemClipboard = (Clipboard)((GUIUtils.class$java$awt$Toolkit == null) ? (GUIUtils.class$java$awt$Toolkit = class$("java.awt.Toolkit")) : GUIUtils.class$java$awt$Toolkit).getMethod("getSystemSelection", (Class[])new Class[0]).invoke(Toolkit.getDefaultToolkit(), new Object[0]);
        }
        catch (NoSuchMethodException ex) {}
        catch (IllegalAccessException ex2) {}
        catch (InvocationTargetException ex3) {}
        if (systemClipboard == null) {
            systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        }
        return systemClipboard;
    }
    
    public static Vector registerLookAndFeelClasses(final File file) throws IllegalAccessException {
        if (file == null) {
            return new Vector();
        }
        final UIManager.LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
        final Vector vector = new Vector<File>(installedLookAndFeels.length);
        for (int i = 0; i < installedLookAndFeels.length; ++i) {
            final File classDirectory = ClassUtil.getClassDirectory(installedLookAndFeels[i].getClassName());
            if (classDirectory != null) {
                vector.addElement(classDirectory);
            }
        }
        ClassUtil.addFileToClasspath(file);
        ClassUtil.loadClasses(file);
        final Vector subclasses = ClassUtil.findSubclasses((GUIUtils.class$javax$swing$LookAndFeel == null) ? (GUIUtils.class$javax$swing$LookAndFeel = class$("javax.swing.LookAndFeel")) : GUIUtils.class$javax$swing$LookAndFeel);
        for (int j = 0; j < subclasses.size(); ++j) {
            LookAndFeel lookAndFeel;
            try {
                lookAndFeel = subclasses.elementAt(j).newInstance();
            }
            catch (IllegalAccessException ex) {
                continue;
            }
            catch (InstantiationException ex2) {
                continue;
            }
            catch (ClassCastException ex3) {
                continue;
            }
            try {
                if (lookAndFeel.isSupportedLookAndFeel()) {
                    final UIManager.LookAndFeelInfo[] installedLookAndFeels2 = UIManager.getInstalledLookAndFeels();
                    boolean b = false;
                    for (int k = 0; k < installedLookAndFeels2.length; ++k) {
                        if (installedLookAndFeels2[k].getClassName().equals(lookAndFeel.getClass().getName())) {
                            b = true;
                        }
                    }
                    if (!b) {
                        UIManager.installLookAndFeel(lookAndFeel.getName(), lookAndFeel.getClass().getName());
                    }
                }
            }
            catch (Throwable t) {}
        }
        final UIManager.LookAndFeelInfo[] installedLookAndFeels3 = UIManager.getInstalledLookAndFeels();
        Vector<File> vector2;
        if (installedLookAndFeels3.length > installedLookAndFeels.length) {
            vector2 = new Vector<File>(installedLookAndFeels3.length - installedLookAndFeels.length);
            for (int l = 0; l < installedLookAndFeels3.length; ++l) {
                final File classDirectory2 = ClassUtil.getClassDirectory(installedLookAndFeels3[l].getClassName());
                if (!vector.contains(classDirectory2)) {
                    vector2.addElement(classDirectory2);
                }
            }
        }
        else {
            vector2 = new Vector<File>();
        }
        return vector2;
    }
    
    public static void resizeAllFonts(final float n) {
        final Enumeration<Object> keys = ((Hashtable<Object, V>)UIManager.getDefaults()).keys();
        while (keys.hasMoreElements()) {
            final Object nextElement = keys.nextElement();
            if (UIManager.get(nextElement) instanceof FontUIResource) {
                adjustFontSize(nextElement.toString(), n);
            }
        }
    }
    
    public static String getTextFromClipboard(final Component component) {
        return getTextFromClipboard(component, true);
    }
    
    public static void saveTextToClipboard(final String text, final Component component) {
        try {
            getSystemClipboard().setContents(new StringSelection(text), new ClipboardOwner() {
                public void lostOwnership(final Clipboard clipboard, final Transferable transferable) {
                }
            });
            if (text.equals(getTextFromClipboard(component, false))) {
                JAPDialog.showMessageDialog(component, JAPMessages.getString(GUIUtils.MSG_SAVED_TO_CLIP));
                return;
            }
        }
        catch (Exception ex) {
            LogHolder.log(5, LogType.GUI, ex);
        }
        final ClipFrame clipFrame = new ClipFrame(component, JAPMessages.getString(GUIUtils.MSG_COPY_FROM_CLIP), false);
        clipFrame.setText(text);
        clipFrame.setVisible(true, false);
    }
    
    public static ImageIcon createScaledImageIcon(final ImageIcon imageIcon, final IIconResizer iconResizer) {
        if (imageIcon == null) {
            return null;
        }
        if (iconResizer == null) {
            return imageIcon;
        }
        return new ImageIcon(imageIcon.getImage().getScaledInstance((int)(imageIcon.getIconWidth() * iconResizer.getResizeFactor()), -1, 8));
    }
    
    public static Icon createScaledIcon(final Icon icon, final IIconResizer iconResizer) {
        if (icon == null) {
            return icon;
        }
        return new IconScaler(icon, iconResizer.getResizeFactor());
    }
    
    public static String trim(String s, final int n) {
        if (s == null || n < 4) {
            return null;
        }
        s = JAPHtmlMultiLineLabel.removeTagsAndNewLines(s);
        if (s.length() > n) {
            s = s.substring(0, n - 2) + "...";
        }
        return s;
    }
    
    public static String trim(final String s) {
        return trim(s, 60);
    }
    
    public static void addAWTEventListener(final AWTEventListener awtEventListener) {
        synchronized (GUIUtils.AWT_EVENT_LISTENERS) {
            if (!GUIUtils.ms_bCapturingAWTEvents) {
                final Runnable runnable = new Runnable() {
                    public void run() {
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                final EventQueue systemEventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
                                try {
                                    while (!JAPDialog.isConsoleOnly()) {
                                        final AWTEvent nextEvent = systemEventQueue.getNextEvent();
                                        Class<?> forName;
                                        try {
                                            forName = Class.forName("java.awt.ActiveEvent");
                                        }
                                        catch (ClassNotFoundException ex3) {
                                            forName = null;
                                        }
                                        if (forName != null && forName.isInstance(nextEvent)) {
                                            forName.getMethod("dispatch", (Class[])null).invoke(nextEvent, (Object[])null);
                                        }
                                        else if (nextEvent.getSource() instanceof Component) {
                                            try {
                                                ((Component)nextEvent.getSource()).dispatchEvent(nextEvent);
                                            }
                                            catch (IllegalMonitorStateException ex) {
                                                LogHolder.log(5, LogType.GUI, ex);
                                            }
                                        }
                                        else if (nextEvent.getSource() instanceof MenuComponent) {
                                            ((MenuComponent)nextEvent.getSource()).dispatchEvent(nextEvent);
                                        }
                                        synchronized (GUIUtils.AWT_EVENT_LISTENERS) {
                                            for (int i = 0; i < GUIUtils.AWT_EVENT_LISTENERS.size(); ++i) {
                                                ((AWTEventListener)GUIUtils.AWT_EVENT_LISTENERS.elementAt(i)).eventDispatched(nextEvent);
                                            }
                                        }
                                        Thread.yield();
                                    }
                                }
                                catch (Exception ex2) {
                                    LogHolder.log(2, LogType.GUI, ex2);
                                }
                            }
                        });
                    }
                };
                if (SwingUtilities.isEventDispatchThread()) {
                    new Thread(runnable).start();
                }
                else {
                    runnable.run();
                }
                GUIUtils.ms_bCapturingAWTEvents = true;
            }
            if (awtEventListener == null) {
                return;
            }
            if (!GUIUtils.AWT_EVENT_LISTENERS.contains(awtEventListener)) {
                GUIUtils.AWT_EVENT_LISTENERS.addElement(awtEventListener);
            }
        }
    }
    
    public static void removeAWTEventListener(final AWTEventListener awtEventListener) {
        GUIUtils.AWT_EVENT_LISTENERS.removeElement(awtEventListener);
    }
    
    public static Point getMousePosition() {
        synchronized (GUIUtils.SYNC_MOUSE_POSITION) {
            if (GUIUtils.ms_mouseListener == null) {
                addAWTEventListener(GUIUtils.ms_mouseListener = new AWTEventListener() {
                    public void eventDispatched(final AWTEvent awtEvent) {
                        if (awtEvent instanceof MouseEvent) {
                            final MouseEvent mouseEvent = (MouseEvent)awtEvent;
                            if (awtEvent.getSource() != null && awtEvent.getSource() instanceof Component) {
                                final Component component = (Component)awtEvent.getSource();
                                try {
                                    synchronized (GUIUtils.SYNC_MOUSE_POSITION) {
                                        GUIUtils.ms_mousePosition = component.getLocationOnScreen();
                                        final Point access$800 = GUIUtils.ms_mousePosition;
                                        access$800.x += mouseEvent.getX();
                                        final Point access$801 = GUIUtils.ms_mousePosition;
                                        access$801.y += mouseEvent.getY();
                                    }
                                }
                                catch (IllegalComponentStateException ex) {}
                            }
                        }
                    }
                });
            }
        }
        if (GUIUtils.ms_mousePosition == null) {
            return null;
        }
        return new Point(GUIUtils.ms_mousePosition.x, GUIUtils.ms_mousePosition.y);
    }
    
    public static Point getRelativePosition(final Point point, final Component component) {
        if (point == null || component == null) {
            return null;
        }
        if (point == null) {
            return null;
        }
        Point point2;
        try {
            point2 = component.getLocationOnScreen();
        }
        catch (IllegalComponentStateException ex) {
            point2 = component.getLocation();
        }
        if (point.x < point2.x - 1 || point.x > point2.x + component.getSize().width + 1 || point.y < point2.y - 1 || point.y > point2.y + component.getSize().height + 1) {
            return null;
        }
        point.x -= point2.x;
        point.y -= point2.y;
        return point;
    }
    
    public static Point getMousePosition(final Component component) {
        return getRelativePosition(getMousePosition(), component);
    }
    
    private static Screen getOverlappingScreen(Screen screen, final Window window) {
        if (screen == null) {
            return null;
        }
        final Screen screen2 = new Screen(new Point(0, 0), getDefaultScreenBounds(window));
        if (screen2.getX() == screen.getX() && screen2.getY() == screen.getY() && screen2.getWidth() == screen.getWidth() && screen2.getHeight() == screen.getHeight()) {
            return screen;
        }
        int n = screen.getX();
        int n2 = screen.getY();
        int n3 = screen.getWidth();
        int n4 = screen.getHeight();
        boolean b = false;
        if ((screen.getY() < screen2.getY() && screen.getY() + screen.getHeight() > screen2.getY()) || (screen2.getY() < screen.getY() && screen2.getY() + screen2.getHeight() > screen.getY())) {
            b = true;
            LogHolder.log(5, LogType.GUI, "Found overlapping screen.");
            n2 = Math.max(screen.getY(), screen2.getY());
            n4 = Math.min(screen.getY() + screen.getHeight(), screen2.getY() + screen2.getHeight() - Math.abs(screen.getY() - screen2.getY()));
        }
        if ((screen.getX() < screen2.getX() && screen.getX() + screen.getWidth() > screen2.getX()) || (screen2.getX() < screen.getX() && screen2.getX() + screen2.getWidth() > screen.getX())) {
            b = true;
            n = Math.max(screen.getX(), screen2.getX());
            n3 = Math.min(screen.getX() + screen.getWidth(), screen2.getX() + screen2.getWidth() - Math.abs(screen.getX() - screen2.getX()));
        }
        if (b) {
            screen = new Screen(new Point(n, n2), new Rectangle(n3, n4));
        }
        return screen;
    }
    
    private static String getTextFromClipboard(final Component component, final boolean b) {
        final Clipboard systemClipboard = getSystemClipboard();
        String text = null;
        final Transferable contents = systemClipboard.getContents(component);
        if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                text = (String)contents.getTransferData(DataFlavor.stringFlavor);
            }
            catch (Exception ex) {
                LogHolder.log(5, LogType.GUI, ex);
            }
        }
        if (b && text == null) {
            final ClipFrame clipFrame = new ClipFrame(component, JAPMessages.getString(GUIUtils.MSG_PASTE_FILE), true);
            clipFrame.setVisible(true, false);
            text = clipFrame.getText();
        }
        return text;
    }
    
    private static Rectangle getDefaultScreenBounds(final Window window) {
        if (window == null) {
            return null;
        }
        Rectangle rectangle;
        try {
            final Object invoke = Class.forName("java.awt.GraphicsEnvironment").getMethod("getLocalGraphicsEnvironment", (Class<?>[])null).invoke(null, (Object[])null);
            final Object invoke2 = invoke.getClass().getMethod("getDefaultScreenDevice", (Class<?>[])null).invoke(invoke, (Object[])null);
            final Object invoke3 = invoke2.getClass().getMethod("getDefaultConfiguration", (Class<?>[])null).invoke(invoke2, (Object[])null);
            rectangle = (Rectangle)invoke3.getClass().getMethod("getBounds", (Class<?>[])null).invoke(invoke3, (Object[])null);
        }
        catch (Exception ex) {
            rectangle = new Rectangle(new Point(0, 0), window.getToolkit().getScreenSize());
        }
        return rectangle;
    }
    
    private static void adjustFontSize(final Object o, final float n) {
        try {
            final UIDefaults defaults = UIManager.getDefaults();
            final Font font = defaults.getFont(o);
            defaults.put(o, new FontUIResource(font.getName(), font.getStyle(), Math.round(font.getSize() * n)));
        }
        catch (Exception ex) {
            LogHolder.log(3, LogType.GUI, ex);
        }
    }
    
    public static Dimension getMaxSize(final Vector vector) {
        final Dimension dimension = new Dimension(0, 0);
        int max = 0;
        int max2 = 0;
        final Enumeration<JComponent> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final JComponent component = elements.nextElement();
            max = Math.max(dimension.width, component.getPreferredSize().width);
            max2 = Math.max(dimension.height, component.getPreferredSize().height);
            dimension.setSize(max, max2);
        }
        dimension.setSize(max, max2);
        return dimension;
    }
    
    public static Dimension getTotalSize(final Vector vector) {
        int n = 0;
        int n2 = 0;
        final Enumeration<JComponent> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final JComponent component = elements.nextElement();
            n += component.getPreferredSize().width;
            n2 += component.getPreferredSize().height;
        }
        return new Dimension(n, n2);
    }
    
    public static int showMonitoredFileChooser(final JFileChooser fileChooser, final Component component) {
        if (fileChooser == null) {
            throw new NullPointerException("No file chooser given!");
        }
        LogHolder.log(4, LogType.GUI, "Showing monitored file chooser...");
        final GUIUtils$1.class_showMonitoredFileChooser_Runnable class_showMonitoredFileChooser_Runnable = new GUIUtils$1.class_showMonitoredFileChooser_Runnable(fileChooser, false);
        final Thread thread = new Thread(class_showMonitoredFileChooser_Runnable);
        thread.start();
        int showOpenDialog;
        try {
            showOpenDialog = fileChooser.showOpenDialog(component);
        }
        catch (Exception ex) {
            LogHolder.log(1, LogType.GUI, ex);
            showOpenDialog = -1;
        }
        LogHolder.log(4, LogType.GUI, "Finished monitored file chooser. Stopping thread.");
        class_showMonitoredFileChooser_Runnable.m_bFinished = true;
        thread.interrupt();
        LogHolder.log(4, LogType.GUI, "Stopped monitored file chooser thread.");
        return showOpenDialog;
    }
    
    public static void setSizes(final Vector vector, final Dimension dimension) {
        final Enumeration<JComponent> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final JComponent component = elements.nextElement();
            component.setPreferredSize(new Dimension(dimension.width, dimension.height));
            component.setMaximumSize(new Dimension(dimension.width, dimension.height));
        }
    }
    
    public static void setEqualWidths(final Vector vector, final Dimension dimension) {
        final Enumeration<JComponent> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final JComponent component = elements.nextElement();
            final double n = component.getPreferredSize().height;
            component.setPreferredSize(new Dimension(dimension.width, (int)n));
            component.setMaximumSize(new Dimension(dimension.width, (int)n));
        }
    }
    
    public static void exitWithNoMessagesError(final String s) {
        JAPAWTMsgBox.MsgBox(new Frame(), "File not found: " + s + "_en" + ".properties\nYour package of JAP may be corrupted.\n" + "Try again to download or install the package.", "Error");
        System.exit(1);
    }
    
    public static String getCountryFromServiceLocation(final ServiceLocation serviceLocation) {
        if (serviceLocation == null) {
            return "";
        }
        String s = "";
        if (serviceLocation.getCity() != null && serviceLocation.getCity().trim().length() > 0) {
            s = serviceLocation.getCity().trim();
        }
        if (serviceLocation.getState() != null && serviceLocation.getState().trim().length() > 0 && !s.equals(serviceLocation.getState().trim())) {
            if (s.length() > 0) {
                s += ", ";
            }
            s += serviceLocation.getState().trim();
        }
        if (serviceLocation.getCountryCode() != null && serviceLocation.getCountryCode().trim().length() > 0) {
            if (s.length() > 0) {
                s += ", ";
            }
            try {
                s += new CountryMapper(serviceLocation.getCountryCode(), JAPMessages.getLocale()).toString();
            }
            catch (IllegalArgumentException ex) {
                s += serviceLocation.getCountryCode().trim();
            }
        }
        if (s.trim().length() == 0) {
            return "N/A";
        }
        return s;
    }
    
    private static void interruptAWTEventThread() {
        final Thread[] array = new Thread[Thread.activeCount()];
        Thread.enumerate(array);
        for (int i = 0; i < array.length; ++i) {
            if (array[i].getName().startsWith("AWT-EventQueue-")) {
                try {
                    LogHolder.log(0, LogType.GUI, "Interrupting AWT event dispatch thread!");
                    array[i].interrupt();
                }
                catch (Throwable t) {
                    LogHolder.log(0, LogType.GUI, t);
                }
            }
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        MSG_DEFAULT_IMGAGE_PATH = ((GUIUtils.class$gui$GUIUtils == null) ? (GUIUtils.class$gui$GUIUtils = class$("gui.GUIUtils")) : GUIUtils.class$gui$GUIUtils).getName() + "_imagePath";
        MSG_DEFAULT_IMGAGE_PATH_LOWCOLOR = ((GUIUtils.class$gui$GUIUtils == null) ? (GUIUtils.class$gui$GUIUtils = class$("gui.GUIUtils")) : GUIUtils.class$gui$GUIUtils).getName() + "_imagePathLowColor";
        MSG_PASTE_FILE = ((GUIUtils.class$gui$GUIUtils == null) ? (GUIUtils.class$gui$GUIUtils = class$("gui.GUIUtils")) : GUIUtils.class$gui$GUIUtils).getName() + "_pasteFile";
        MSG_COPY_FROM_CLIP = ((GUIUtils.class$gui$GUIUtils == null) ? (GUIUtils.class$gui$GUIUtils = class$("gui.GUIUtils")) : GUIUtils.class$gui$GUIUtils).getName() + "_copyFromClip";
        MSG_SAVED_TO_CLIP = ((GUIUtils.class$gui$GUIUtils == null) ? (GUIUtils.class$gui$GUIUtils = class$("gui.GUIUtils")) : GUIUtils.class$gui$GUIUtils).getName() + "_savedToClip";
        GUIUtils.ms_loadImages = true;
        GUIUtils.ms_bCapturingAWTEvents = false;
        SYNC_MOUSE_POSITION = new Object();
        AWT_EVENT_LISTENERS = new Vector();
        DEFAULT_RESIZER = new IIconResizer() {
            public double getResizeFactor() {
                return 1.0;
            }
            
            final class class_showMonitoredFileChooser_Runnable implements Runnable
            {
                public volatile boolean m_bFinished;
                private final /* synthetic */ JFileChooser val$a_chooser;
                
                class_showMonitoredFileChooser_Runnable(final JFileChooser val$a_chooser, final boolean bFinished) {
                    this.val$a_chooser = val$a_chooser;
                    this.m_bFinished = bFinished;
                }
                
                public void run() {
                    try {
                        Thread.sleep(2000L);
                        LogHolder.log(4, LogType.GUI, "Waiting in timeout thread of monitored file chooser...");
                        while ((!this.val$a_chooser.isVisible() || !this.val$a_chooser.isShowing()) && !this.m_bFinished) {
                            LogHolder.log(1, LogType.GUI, "File chooser dialog blocked and is now interrupted!");
                            interruptAWTEventThread();
                            Thread.sleep(200L);
                        }
                    }
                    catch (InterruptedException ex) {}
                }
            }
        };
        GUIUtils.ms_resizer = GUIUtils.DEFAULT_RESIZER;
        DUMMY_GUI_LIBRARY = new NativeGUILibrary() {
            public boolean setAlwaysOnTop(final Window window, final boolean b) {
                return false;
            }
            
            public boolean isAlwaysOnTop(final Window window) {
                return false;
            }
        };
        GUIUtils.ms_nativeGUILibrary = GUIUtils.DUMMY_GUI_LIBRARY;
        RESIZER = new IIconResizer() {
            public double getResizeFactor() {
                return GUIUtils.ms_resizer.getResizeFactor();
            }
        };
        GUIUtils.ms_iconCache = new Hashtable();
    }
    
    public static class WindowDocker
    {
        private JobQueue m_queue;
        private Component m_component;
        private InternalListener m_listener;
        private Window m_parentWindow;
        
        public WindowDocker(final Component component) {
            this.m_component = component;
            this.m_listener = new InternalListener();
            this.m_component.addMouseListener(this.m_listener);
            this.m_component.addMouseMotionListener(this.m_listener);
            this.m_parentWindow = GUIUtils.getParentWindow(component);
            this.m_queue = new JobQueue("Docking queue for window: " + component.getName());
        }
        
        public void finalize() {
            this.m_queue.stop();
            this.m_queue = null;
            this.m_component.removeMouseListener(this.m_listener);
            this.m_component.removeMouseMotionListener(this.m_listener);
            this.m_component.removeComponentListener(this.m_listener);
            this.m_listener = null;
        }
        
        private class InternalListener extends MouseAdapter implements MouseMotionListener, ComponentListener, IDockInterface
        {
            private boolean m_bIsDragging;
            private Point m_startPoint;
            private final Object SYNC;
            private final /* synthetic */ WindowDocker this$0;
            
            private InternalListener() {
                this.m_bIsDragging = false;
                this.SYNC = new Object();
            }
            
            public void componentHidden(final ComponentEvent componentEvent) {
            }
            
            public void componentResized(final ComponentEvent componentEvent) {
            }
            
            public void componentShown(final ComponentEvent componentEvent) {
            }
            
            public void componentMoved(final ComponentEvent componentEvent) {
                if (!this.m_bIsDragging) {
                    this.move(null);
                }
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                synchronized (this.SYNC) {
                    this.m_bIsDragging = false;
                }
            }
            
            public void mouseMoved(final MouseEvent mouseEvent) {
            }
            
            public void mouseDragged(final MouseEvent mouseEvent) {
                synchronized (this.SYNC) {
                    if (!this.m_bIsDragging) {
                        this.m_bIsDragging = true;
                        this.m_startPoint = mouseEvent.getPoint();
                    }
                    else {
                        final Point point = mouseEvent.getPoint();
                        final Point locationOnScreen = WindowDocker.this.m_parentWindow.getLocationOnScreen();
                        this.move(new Point(locationOnScreen.x + point.x - this.m_startPoint.x, locationOnScreen.y + point.y - this.m_startPoint.y));
                    }
                }
            }
            
            private void move(final Point point) {
                WindowDocker.this.m_queue.addJob(new JobQueue.Job() {
                    private final /* synthetic */ InternalListener this$1 = this$1;
                    
                    public void runJob() {
                        final Screen currentScreen = GUIUtils.getCurrentScreen(this.this$1.this$0.m_parentWindow);
                        boolean b = point != null;
                        Point point = point;
                        if (point == null) {
                            point = this.this$1.this$0.m_parentWindow.getLocationOnScreen();
                        }
                        int n = point.x;
                        int n2 = point.y;
                        final int n3 = currentScreen.getWidth() + currentScreen.getX();
                        final int n4 = currentScreen.getHeight() + currentScreen.getY();
                        if (n != currentScreen.getX() && Math.abs(n - currentScreen.getX()) < 10) {
                            b = true;
                            n = currentScreen.getX();
                        }
                        else if (n + this.this$1.this$0.m_parentWindow.getSize().width > n3 - 10 && n + this.this$1.this$0.m_parentWindow.getSize().width <= n3 + 10) {
                            b = true;
                            n = n3 - this.this$1.this$0.m_parentWindow.getSize().width;
                        }
                        if (n2 != currentScreen.getY() && Math.abs(n2 - currentScreen.getY()) < 10) {
                            b = true;
                            n2 = currentScreen.getY();
                        }
                        else if (n2 + this.this$1.this$0.m_parentWindow.getSize().height > n4 - 10 && n2 + this.this$1.this$0.m_parentWindow.getSize().height <= n4 + 10) {
                            b = true;
                            n2 = n4 - this.this$1.this$0.m_parentWindow.getSize().height;
                        }
                        if (b) {
                            this.this$1.this$0.m_parentWindow.setLocation(n, n2);
                        }
                    }
                });
            }
        }
        
        private interface IDockInterface
        {
            public static final int DOCK_DISTANCE = 10;
        }
    }
    
    public static class Screen
    {
        private Point m_location;
        private Rectangle m_bounds;
        
        public Screen(final Point location, final Rectangle bounds) {
            this.m_location = location;
            this.m_bounds = bounds;
        }
        
        public int getX() {
            return this.m_location.x;
        }
        
        public int getY() {
            return this.m_location.y;
        }
        
        public int getWidth() {
            return this.m_bounds.width;
        }
        
        public int getHeight() {
            return this.m_bounds.height;
        }
        
        public Point getLocation() {
            return this.m_location;
        }
        
        public Rectangle getBounds() {
            return this.m_bounds;
        }
        
        public String toString() {
            return "x=" + this.getX() + " " + "y=" + this.getY() + " " + "width=" + this.getWidth() + " " + "height=" + this.getHeight();
        }
    }
    
    private static class ToolTipMouseListener extends MouseAdapter
    {
        static /* synthetic */ Class class$javax$swing$JComponent;
        static /* synthetic */ Class class$java$lang$Object;
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            if (!(mouseEvent.getComponent() instanceof JComponent)) {
                return;
            }
            final JComponent component = (JComponent)mouseEvent.getComponent();
            ActionListener actionListener = null;
            try {
                actionListener = (Action)Class.forName("javax.swing.ActionMap").getMethod("get", (ToolTipMouseListener.class$java$lang$Object == null) ? (ToolTipMouseListener.class$java$lang$Object = class$("java.lang.Object")) : ToolTipMouseListener.class$java$lang$Object).invoke(((ToolTipMouseListener.class$javax$swing$JComponent == null) ? (ToolTipMouseListener.class$javax$swing$JComponent = class$("javax.swing.JComponent")) : ToolTipMouseListener.class$javax$swing$JComponent).getMethod("getActionMap", (Class[])new Class[0]).invoke(component, new Object[0]), "postTip");
            }
            catch (Exception ex) {
                LogHolder.log(2, LogType.GUI, ex);
            }
            if (actionListener != null) {
                actionListener.actionPerformed(new ActionEvent(component, 1001, "postTip"));
            }
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
    
    private static class IconScaler implements Icon
    {
        private static Class GRAPHICS_2D;
        private Icon m_icon;
        private double m_scaleWidth;
        private double m_scaleHeight;
        
        public IconScaler(final Icon icon, final double n) {
            this(icon, n, n);
        }
        
        public IconScaler(final Icon icon, final double scaleWidth, final double scaleHeight) {
            this.m_icon = icon;
            if (IconScaler.GRAPHICS_2D != null) {
                this.m_scaleWidth = scaleWidth;
                this.m_scaleHeight = scaleHeight;
            }
            else {
                this.m_scaleWidth = 1.0;
                this.m_scaleHeight = 1.0;
            }
        }
        
        public int getIconHeight() {
            return (int)(this.m_icon.getIconHeight() * this.m_scaleHeight);
        }
        
        public int getIconWidth() {
            return (int)(this.m_icon.getIconWidth() * this.m_scaleWidth);
        }
        
        public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
            scale(graphics, this.m_scaleWidth, this.m_scaleHeight);
            this.m_icon.paintIcon(component, graphics, n, n2);
            scale(graphics, 1.0 / this.m_scaleWidth, 1.0 / this.m_scaleHeight);
        }
        
        private static void scale(final Graphics graphics, final double n, final double n2) {
            if (IconScaler.GRAPHICS_2D != null) {
                try {
                    IconScaler.GRAPHICS_2D.getMethod("scale", Double.TYPE, Double.TYPE).invoke(graphics, new Double(n), new Double(n2));
                }
                catch (Exception ex) {
                    LogHolder.log(3, LogType.GUI, ex);
                }
            }
        }
        
        static {
            try {
                IconScaler.GRAPHICS_2D = Class.forName("java.awt.Graphics2D");
            }
            catch (ClassNotFoundException ex) {
                IconScaler.GRAPHICS_2D = null;
            }
        }
    }
    
    public interface AWTEventListener
    {
        void eventDispatched(final AWTEvent p0);
    }
    
    public interface IIconResizer
    {
        double getResizeFactor();
    }
    
    public interface NativeGUILibrary
    {
        boolean setAlwaysOnTop(final Window p0, final boolean p1);
        
        boolean isAlwaysOnTop(final Window p0);
    }
}
