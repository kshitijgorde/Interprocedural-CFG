// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing;

import java.util.StringTokenizer;
import java.awt.Font;
import jmaster.util.property.D;
import java.awt.Color;
import javax.swing.table.AbstractTableModel;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import jmaster.util.C.B;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JApplet;
import javax.swing.JFrame;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Container;
import java.awt.Component;

public class SwingUtil
{
    public static final String METHOD_FIRE_TABLE_DATA_CHANGED = "fireTableDataChanged";
    public static final String METHOD_FIRE_TABLE_ROWS_INSERTED = "fireTableRowsInserted";
    public static final String METHOD_FIRE_TABLE_ROWS_DELETED = "fireTableRowsDeleted";
    public static final String METHOD_FIRE_TABLE_ROWS_UPDATED = "fireTableRowsUpdated";
    
    public static void centerParent(final Component component) {
        final Container parent = component.getParent();
        final Point locationOnScreen = parent.getLocationOnScreen();
        final Dimension size = parent.getSize();
        final Dimension size2 = component.getSize();
        int x;
        if (size.width > size2.width) {
            x = (size.width - size2.width) / 2 + locationOnScreen.x;
        }
        else {
            x = locationOnScreen.x;
        }
        int y;
        if (size.height > size2.height) {
            y = (size.height - size2.height) / 2 + locationOnScreen.y;
        }
        else {
            y = locationOnScreen.y;
        }
        component.setLocation(x, y);
    }
    
    public static Frame getParentFrame(final Component component) {
        final Container parent = component.getParent();
        if (parent == null) {
            return null;
        }
        if (parent instanceof Frame) {
            return (Frame)parent;
        }
        return getParentFrame(parent);
    }
    
    public static JFrame getParentJFrame(final Component component) {
        final Container parent = component.getParent();
        if (parent == null) {
            return null;
        }
        if (parent instanceof JFrame) {
            return (JFrame)parent;
        }
        return getParentJFrame(parent);
    }
    
    public static JApplet getParentJApplet(final Component component) {
        final Container parent = component.getParent();
        if (parent == null) {
            return null;
        }
        if (parent instanceof JApplet) {
            return (JApplet)parent;
        }
        return getParentJApplet(parent);
    }
    
    public static void reloadTreeNode(final DefaultTreeModel defaultTreeModel, final TreeNode treeNode) {
        if (!SwingUtilities.isEventDispatchThread()) {
            try {
                SwingUtilities.invokeAndWait(new Runnable() {
                    public void run() {
                        defaultTreeModel.reload(treeNode);
                    }
                });
                return;
            }
            catch (Exception ex) {
                if (ex instanceof RuntimeException) {
                    throw (RuntimeException)ex;
                }
                throw new RuntimeException(ex);
            }
        }
        defaultTreeModel.reload(treeNode);
    }
    
    public void setNativeLookAndFeel() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        if (!B.A()) {
            B.C(this, "setNativeLookAndFeel");
            return;
        }
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    
    public static void fireTableRowsInserted(final AbstractTableModel abstractTableModel, final int n, final int n2) {
        if (!B.A()) {
            B.C(abstractTableModel, "fireTableRowsInserted", new Class[] { Integer.TYPE, Integer.TYPE }, new Object[] { new Integer(n), new Integer(n2) });
        }
        else {
            abstractTableModel.fireTableRowsInserted(n, n2);
        }
    }
    
    public static void fireTableRowsDeleted(final AbstractTableModel abstractTableModel, final int n, final int n2) {
        if (!B.A()) {
            B.C(abstractTableModel, "fireTableRowsDeleted", new Class[] { Integer.TYPE, Integer.TYPE }, new Object[] { new Integer(n), new Integer(n2) });
        }
        else {
            abstractTableModel.fireTableRowsDeleted(n, n2);
        }
    }
    
    public static void fireTableRowsUpdated(final AbstractTableModel abstractTableModel, final int n, final int n2) {
        if (!B.A()) {
            B.C(abstractTableModel, "fireTableRowsUpdated", new Class[] { Integer.TYPE, Integer.TYPE }, new Object[] { new Integer(n), new Integer(n2) });
        }
        else {
            abstractTableModel.fireTableRowsUpdated(n, n2);
        }
    }
    
    public static void fireTableDataChanged(final AbstractTableModel abstractTableModel) {
        if (!B.A()) {
            B.C(abstractTableModel, "fireTableDataChanged");
        }
        else {
            abstractTableModel.fireTableDataChanged();
        }
    }
    
    public static void setUiDefault(final Object o, final Object o2) {
        UIManager.getLookAndFeelDefaults().put(o, o2);
    }
    
    public static void setUiDefaultString(final String s, Object o) {
        final Object value = UIManager.getLookAndFeelDefaults().get(s);
        if (value != null) {
            if (value instanceof Color) {
                o = new Color(D.R("" + o));
            }
            if (value instanceof Font) {
                final StringTokenizer stringTokenizer = new StringTokenizer("" + o, ",");
                final Font font = (Font)value;
                final String s2 = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : font.getFontName();
                int style = font.getStyle();
                if (stringTokenizer.hasMoreTokens()) {
                    final String nextToken = stringTokenizer.nextToken();
                    style = 0;
                    if (nextToken.indexOf(98) != -1) {
                        style |= 0x1;
                    }
                    if (nextToken.indexOf(105) != -1) {
                        style |= 0x2;
                    }
                }
                int n = font.getSize();
                if (stringTokenizer.hasMoreTokens()) {
                    n = Integer.parseInt(stringTokenizer.nextToken());
                }
                o = new Font(s2, style, n);
            }
        }
        setUiDefault(s, o);
    }
    
    public static Object getUiDefault(final Object o) {
        return UIManager.getLookAndFeelDefaults().get(o);
    }
    
    public static Container getTopContainer(final Component component) {
        return (component.getParent() == null) ? ((component instanceof Container) ? ((Container)component) : null) : getTopContainer(component.getParent());
    }
}
