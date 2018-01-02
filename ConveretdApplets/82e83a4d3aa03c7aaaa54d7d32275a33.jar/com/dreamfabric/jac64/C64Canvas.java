// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class C64Canvas extends JPanel implements KeyListener, FocusListener
{
    C64Screen scr;
    Keyboard keyboard;
    boolean dscr;
    
    public C64Canvas(final C64Screen scr, final boolean dscr, final Keyboard keyboard) {
        this.dscr = dscr;
        this.scr = scr;
        this.keyboard = keyboard;
        this.setFont(new Font("Monospaced", 0, 11));
        this.setFocusTraversalKeysEnabled(false);
        this.addFocusListener(this);
        this.addKeyListener(this);
    }
    
    public Dimension getPreferredSize() {
        final int n = this.dscr ? 2 : 1;
        return new Dimension(384 * n, 284 * n);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.scr.paint(graphics);
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        this.keyboard.keyPressed(keyEvent);
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        this.keyboard.keyReleased(keyEvent);
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        if (keyEvent.getKeyChar() == 'w' && (keyEvent.getModifiers() & 0x8) != 0x0) {
            this.scr.getMixer().setFullSpeed(!this.scr.getMixer().fullSpeed());
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.keyboard.reset();
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.keyboard.reset();
    }
    
    public boolean isFocusTraversable() {
        return true;
    }
}
