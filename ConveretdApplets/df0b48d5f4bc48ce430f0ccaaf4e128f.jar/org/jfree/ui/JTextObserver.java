// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui;

import java.awt.event.FocusEvent;
import javax.swing.text.JTextComponent;
import java.awt.event.FocusListener;

public final class JTextObserver implements FocusListener
{
    private static JTextObserver singleton;
    
    public static void addTextComponent(final JTextComponent t) {
        if (JTextObserver.singleton == null) {
            JTextObserver.singleton = new JTextObserver();
        }
        t.addFocusListener(JTextObserver.singleton);
    }
    
    public void focusGained(final FocusEvent e) {
        if (e.getSource() instanceof JTextComponent) {
            final JTextComponent tex = (JTextComponent)e.getSource();
            tex.selectAll();
        }
    }
    
    public void focusLost(final FocusEvent e) {
        if (e.getSource() instanceof JTextComponent) {
            final JTextComponent tex = (JTextComponent)e.getSource();
            tex.select(0, 0);
        }
    }
    
    public static JTextObserver getInstance() {
        if (JTextObserver.singleton == null) {
            JTextObserver.singleton = new JTextObserver();
        }
        return JTextObserver.singleton;
    }
    
    public static void removeTextComponent(final JTextComponent t) {
        if (JTextObserver.singleton == null) {
            JTextObserver.singleton = new JTextObserver();
        }
        t.removeFocusListener(JTextObserver.singleton);
    }
}
