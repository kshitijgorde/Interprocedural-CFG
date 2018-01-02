// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.util;

import java.awt.Container;
import java.awt.Window;
import java.awt.Component;
import java.util.Vector;
import java.awt.Toolkit;

public class Utilities
{
    public static String getSystemFont(final String s) {
        final String[] fontList = Toolkit.getDefaultToolkit().getFontList();
        final Vector vector = new Vector<String>();
        for (int i = 0; i < fontList.length; ++i) {
            vector.addElement(fontList[i]);
        }
        final int index = vector.indexOf(s);
        return (index == -1) ? ((vector.size() == 0) ? null : vector.elementAt(0)) : vector.elementAt(index);
    }
    
    private static Window getWindowAncestor(final Component component) {
        Container container = component.getParent();
        while (component != null) {
            if (container instanceof Window) {
                return (Window)container;
            }
            container = container.getParent();
        }
        return null;
    }
}
