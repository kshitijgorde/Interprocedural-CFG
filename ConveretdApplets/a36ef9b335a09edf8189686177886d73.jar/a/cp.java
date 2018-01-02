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
import java.awt.Image;
import java.util.Hashtable;

public class cp
{
    private Hashtable q;
    
    public cp(final String s) {
        this.q = new Hashtable();
    }
    
    public final void q(final String s, final Image image) {
        this.q.put(s, image);
    }
    
    public final Image q(final String s) {
        return this.q.get(s);
    }
    
    public cp() {
    }
    
    public static Window q(Window window, final Component component) {
        Frame frame = null;
        Window q = null;
        if (bI.a()) {
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
    
    public static Window q(Component parent, final boolean b) {
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
}
