// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Dialog;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Component;
import java.awt.Window;

public class eb
{
    public static en q;
    
    public eb() {
    }
    
    public static final Window q(Window window, final Component component) {
        Frame frame = null;
        Window q = null;
        if (cV.s()) {
            q = q(component, false);
        }
        else {
            frame = (Frame)q(component, true);
        }
        if (window != null) {
            window.removeAll();
            try {
                window.dispose();
            }
            catch (Exception ex) {}
            window = null;
        }
        if (window == null || !window.equals((frame != null) ? new Window(frame) : new Window(q))) {
            if (frame != null) {
                window = new Window(frame);
            }
            else {
                window = new Window(q);
            }
            window.setLayout(new BorderLayout());
        }
        return window;
    }
    
    public static final Window q(Component parent, final boolean b) {
        Window window = null;
        while (!(parent instanceof Frame) && (parent = parent.getParent()) != null) {
            if (parent instanceof Window) {
                window = (Window)parent;
                if (!b && parent instanceof Dialog) {
                    break;
                }
                continue;
            }
        }
        if (b) {
            return window;
        }
        return (Window)parent;
    }
    
    public static String q(String s) {
        if (eb.q == null) {
            return s;
        }
        s = new String(s);
        final String q;
        if ((q = eb.q.q(s)) == null) {
            return new String(s);
        }
        return q;
    }
    
    public eb(final en q) {
        eb.q = q;
    }
}
