// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.Rectangle;
import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.Font;
import java.awt.event.TextEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.event.TextListener;
import java.awt.event.ActionListener;
import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.FocusListener;

class ao implements FocusListener, KeyListener, MouseListener, MouseMotionListener
{
    final /* synthetic */ R a;
    
    ao(final R a) {
        this.a = a;
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.a.c(focusEvent);
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.a.d(focusEvent);
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (this.a.t) {
            this.a.c(keyEvent);
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        if (this.a.t) {
            this.a.d(keyEvent);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.a.c.contains(mouseEvent.getPoint())) {
            this.a.f(mouseEvent);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.a.c.contains(mouseEvent.getPoint())) {
            this.a.g(mouseEvent);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
}
