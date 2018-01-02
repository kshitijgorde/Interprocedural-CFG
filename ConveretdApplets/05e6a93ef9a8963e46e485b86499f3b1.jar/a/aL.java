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
import java.awt.Font;

public class aL
{
    Font q;
    Font w;
    Font e;
    Font r;
    Font t;
    
    public aL() {
    }
    
    public static final Window q(Window window, final Component component) {
        Frame frame = null;
        Window q = null;
        if (W.e()) {
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
    
    public aL(final Font q) {
        this.q = q;
        this.w = new Font(this.q.getFamily(), 0, this.q.getSize());
        this.e = new Font(this.q.getFamily(), 2, this.q.getSize());
        this.r = new Font(this.q.getFamily(), 1, this.q.getSize());
        this.t = new Font(this.q.getFamily(), 3, this.q.getSize());
    }
    
    public Font q() {
        return this.e;
    }
    
    public Font w() {
        return this.r;
    }
    
    public Font e() {
        return this.t;
    }
    
    public Font r() {
        return this.q;
    }
    
    public Font q(final char c) {
        if (c == 'i') {
            switch (this.q()) {
                case 0: {
                    return this.e;
                }
                case 2: {
                    return this.w;
                }
                case 1: {
                    return this.t;
                }
                case 3: {
                    return this.r;
                }
            }
        }
        if (c == 'b') {
            switch (this.q()) {
                case 0: {
                    return this.r;
                }
                case 2: {
                    return this.t;
                }
                case 1: {
                    return this.w;
                }
                case 3: {
                    return this.e;
                }
            }
        }
        return this.q;
    }
    
    int q() {
        return this.q.getStyle();
    }
    
    public static String q(final char c) {
        return "[" + c + ']';
    }
    
    static String w(final char c) {
        return "[/" + c + ']';
    }
    
    public String q(final String s) {
        return q(s, this.q());
    }
    
    public static String q(final String s, final int n) {
        switch (n) {
            case 0: {
                return s;
            }
            case 2: {
                return q('i') + " " + s + " " + w('i');
            }
            case 1: {
                return q('b') + " " + s + " " + w('b');
            }
            case 3: {
                return q('i') + q('b') + " " + s + " " + w('b') + w('i');
            }
            default: {
                return s;
            }
        }
    }
    
    public static String q(String s, final char c) {
        if (s.indexOf(q(c)) >= 0) {
            final int index = s.indexOf(q(c));
            final int index2;
            if ((index2 = (s = s.substring(0, index) + s.substring(index + q(c).length(), s.length())).indexOf(w(c))) >= 0) {
                s = s.substring(0, index2) + s.substring(index2 + w(c).length(), s.length());
            }
        }
        return s.trim();
    }
}
