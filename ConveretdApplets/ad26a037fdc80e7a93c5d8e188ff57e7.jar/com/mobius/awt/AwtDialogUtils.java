// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.awt;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Component;

public class AwtDialogUtils
{
    public static void forceTabFocusTransfer(final Component component, final Component component2, final Component component3) {
        component.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 9) {
                    if (keyEvent.isShiftDown()) {
                        component2.requestFocus();
                    }
                    else {
                        component3.requestFocus();
                    }
                }
            }
        });
    }
}
