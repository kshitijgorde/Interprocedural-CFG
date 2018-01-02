// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Component;
import javax.swing.RootPaneContainer;
import javax.swing.JPanel;

public final class MouseBlocker
{
    private static final JPanel blocker;
    private static int nbrBlocks;
    
    public static void init(final RootPaneContainer rootPaneContainer) {
        rootPaneContainer.setGlassPane(MouseBlocker.blocker);
        MouseBlocker.blocker.setBackground(null);
        MouseBlocker.blocker.setOpaque(false);
        MouseBlocker.blocker.addMouseListener(new MouseAdapter() {});
        MouseBlocker.blocker.addMouseMotionListener(new MouseMotionAdapter() {});
        MouseBlocker.blocker.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public final void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
            }
        });
        MouseBlocker.blocker.addFocusListener(new FocusListener() {
            @Override
            public final void focusLost(final FocusEvent focusEvent) {
                if (MouseBlocker.blocker.isVisible()) {
                    MouseBlocker.blocker.requestFocusInWindow();
                }
            }
            
            @Override
            public final void focusGained(final FocusEvent focusEvent) {
            }
        });
    }
    
    public static void block() {
        if (++MouseBlocker.nbrBlocks > 0) {
            MouseBlocker.blocker.setVisible(true);
            MouseBlocker.blocker.requestFocusInWindow();
        }
    }
    
    public static void unblock() {
        if (--MouseBlocker.nbrBlocks <= 0) {
            MouseBlocker.blocker.setVisible(false);
        }
    }
    
    static {
        blocker = new JPanel();
    }
}
