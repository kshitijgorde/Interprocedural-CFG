// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.a;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.datatransfer.Clipboard;
import java.io.IOException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.datatransfer.DataFlavor;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.ContainerEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Dialog;
import java.awt.Insets;
import java.awt.Label;
import java.awt.GridBagConstraints;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.event.KeyListener;
import java.awt.event.ContainerListener;
import java.awt.event.WindowAdapter;

public final class c extends WindowAdapter implements ContainerListener, KeyListener, ClipboardOwner, Transferable
{
    private String a;
    private static Frame b;
    private static String z;
    
    public static Dimension a() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
    
    public static void a(final Component component, final Component component2) {
        if (component2 == null) {
            return;
        }
        component2.setSize(a(component));
    }
    
    public static Dimension a(final Component component) {
        return a(component, 0.75f);
    }
    
    public static Dimension a(final Component component, final float n) {
        final Dimension dimension = (component == null) ? a() : component.getSize();
        return new Dimension((int)(dimension.width * n), (int)(dimension.height * n));
    }
    
    public static void a(final Component component, final Window window) {
        a(component, (Component)window);
        b(component, window);
    }
    
    public static void b(final Component component, final Window window) {
        Rectangle bounds = null;
        Label_0040: {
            if (component == null) {
                final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                bounds = new Rectangle(0, 0, screenSize.width, screenSize.height);
                if (!h.a) {
                    break Label_0040;
                }
            }
            bounds = component.getBounds();
        }
        window.setLocation(a(bounds, window.getSize()));
    }
    
    public static Window b(final Component component) {
        final boolean a = h.a;
        if (component == null) {
            return null;
        }
        if (component instanceof Window) {
            return (Window)component;
        }
        Container container = component.getParent();
        while (true) {
            Label_0048: {
                if (!a) {
                    break Label_0048;
                }
                if (container instanceof Window) {
                    return (Window)container;
                }
                container = container.getParent();
            }
            if (container == null) {
                return null;
            }
            continue;
        }
    }
    
    public static Container a(final Container container, final String[] array, final Component[] array2) {
        final boolean a = h.a;
        int n = 0;
        int n2 = 0;
        while (true) {
            Label_0069: {
                if (!a) {
                    break Label_0069;
                }
                final Component component = array2[n2];
                String s = array[n2].trim();
                if (s.startsWith("*")) {
                    s = s.substring(1);
                }
                a(container, s, component, a(n, 0), true);
                ++n;
                ++n2;
            }
            if (n2 >= array2.length) {
                return container;
            }
            continue;
        }
    }
    
    public static Component a(final Container container, final String s, final Object o, final GridBagConstraints gridBagConstraints, final boolean b) {
        container.add(new Label(new String(String.valueOf(s) + c.z)), gridBagConstraints);
        Component component = null;
        Label_0080: {
            if (o instanceof Component) {
                component = (Component)o;
                if (!h.a) {
                    break Label_0080;
                }
            }
            component = new Label((o != null) ? o.toString() : "");
        }
        gridBagConstraints.gridx = ++gridBagConstraints.gridx;
        if (b) {
            gridBagConstraints.weightx = 1.0;
        }
        container.add(component, gridBagConstraints);
        return component;
    }
    
    public static GridBagConstraints a(final int gridy, final int gridx) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(3, 5, 0, 1);
        return gridBagConstraints;
    }
    
    public static Dialog c(final Component component) {
        final c c = new c();
        final Dialog dialog = new Dialog(b());
        dialog.addContainerListener(c);
        dialog.addWindowListener(c);
        return dialog;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        final Window b = b(windowEvent.getComponent());
        if (b == null) {
            return;
        }
        b.dispose();
    }
    
    public void componentAdded(final ContainerEvent containerEvent) {
        this.e(containerEvent.getChild());
    }
    
    public void componentRemoved(final ContainerEvent containerEvent) {
        this.d(containerEvent.getChild());
    }
    
    private void d(final Component component) {
        final boolean a = h.a;
        component.removeKeyListener(this);
        if (!(component instanceof Container)) {
            return;
        }
        final Container container = (Container)component;
        container.removeContainerListener(this);
        final Component[] components = container.getComponents();
        int n = 0;
        while (true) {
            Label_0052: {
                if (!a) {
                    break Label_0052;
                }
                this.e(components[n]);
                ++n;
            }
            if (n >= components.length) {
                return;
            }
            continue;
        }
    }
    
    private void e(final Component component) {
        final boolean a = h.a;
        component.addKeyListener(this);
        if (!(component instanceof Container)) {
            return;
        }
        final Container container = (Container)component;
        container.addContainerListener(this);
        final Component[] components = container.getComponents();
        int n = 0;
        while (true) {
            Label_0052: {
                if (!a) {
                    break Label_0052;
                }
                this.e(components[n]);
                ++n;
            }
            if (n >= components.length) {
                return;
            }
            continue;
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 27) {
            return;
        }
        final Window b = b(keyEvent.getComponent());
        if (b == null) {
            return;
        }
        b.dispose();
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public static synchronized Frame b() {
        if (c.b == null) {
            (c.b = new Frame()).setSize(0, 0);
            c.b.setLocation(0, 0);
        }
        return c.b;
    }
    
    public static Point a(final Rectangle rectangle, final Dimension dimension) {
        return new Point((rectangle.x + rectangle.width) / 2 - dimension.width / 2, (rectangle.y + rectangle.height) / 2 - dimension.height / 2);
    }
    
    public Object getTransferData(final DataFlavor dataFlavor) throws UnsupportedFlavorException, IOException {
        return this.a;
    }
    
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[] { DataFlavor.stringFlavor };
    }
    
    public boolean isDataFlavorSupported(final DataFlavor dataFlavor) {
        return dataFlavor == DataFlavor.stringFlavor;
    }
    
    public void lostOwnership(final Clipboard clipboard, final Transferable transferable) {
    }
    
    public static void a(final Graphics graphics, final String s, final int n, final int n2, final int n3) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        if (fontMetrics == null) {
            return;
        }
        graphics.drawString(s, n, n2 + (fontMetrics.getAscent() + (n3 - fontMetrics.getHeight()) / 2));
    }
    
    public static void a(final Color color, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final boolean a = h.a;
        final Color color2 = graphics.getColor();
        graphics.setColor(color);
        Label_0129: {
            if (n2 == n4) {
                int n5 = n;
                while (true) {
                    Label_0046: {
                        if (!a) {
                            break Label_0046;
                        }
                        graphics.drawLine(n5, n2, n5 + 1, n4);
                        n5 += 3;
                    }
                    if (n5 < n3) {
                        continue;
                    }
                    break;
                }
                if (!a) {
                    break Label_0129;
                }
            }
            if (n == n3) {
                int n6 = n2;
                while (true) {
                    Label_0088: {
                        if (!a) {
                            break Label_0088;
                        }
                        graphics.drawLine(n, n6, n3, n6 + 1);
                        n6 += 3;
                    }
                    if (n6 + 3 < n4) {
                        continue;
                    }
                    break;
                }
                graphics.drawLine(n, n6, n3, n6 + (n4 - n6));
                if (!a) {
                    break Label_0129;
                }
            }
            graphics.drawLine(n, n2, n3, n4);
        }
        graphics.setColor(color2);
    }
    
    public static void b(final Color color, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        a(color, graphics, n, n2, n + n3, n2);
        a(color, graphics, n, n2 + n4, n + n3, n2 + n4);
        a(color, graphics, n, n2, n, n2 + n4);
        a(color, graphics, n + n3, n2, n + n3, n2 + n4);
    }
    
    static {
        c.z = z(z("D["));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u000f';
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
                    c2 = '~';
                    break;
                }
                case 1: {
                    c2 = '{';
                    break;
                }
                case 2: {
                    c2 = '\u0005';
                    break;
                }
                case 3: {
                    c2 = '^';
                    break;
                }
                default: {
                    c2 = '\u000f';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
