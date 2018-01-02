// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.gui;

import java.awt.event.ActionEvent;
import java.util.Observable;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.util.Observer;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public abstract class D implements MouseListener, KeyListener, Observer, ActionListener, ItemListener
{
    private B[] A;
    
    public D() {
    }
    
    public D(final B[] a) {
        this.A = a;
    }
    
    public void A(final B[] a) {
        this.A = a;
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        this.A().B(keyEvent.getKeyChar());
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.A().A(itemEvent.getItemSelectable().getSelectedObjects() != null);
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        this.B();
        if (keyEvent.getKeyCode() == 34 || keyEvent.getKeyCode() == 33) {
            final int n = (keyEvent.getKeyCode() == 34) ? 1 : -1;
            for (int i = 0; i < this.A.length; ++i) {
                if (this.A[i] != null && this.A[i].I()) {
                    this.A[i].B(n);
                }
            }
        }
        else {
            this.A().A(keyEvent);
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.B();
    }
    
    protected abstract void B();
    
    public abstract void A(final String p0);
    
    public abstract void A(final String p0, final String p1, final boolean p2, final boolean p3);
    
    protected abstract H A();
    
    public abstract void update(final Observable p0, final Object p1);
    
    public abstract void actionPerformed(final ActionEvent p0);
}
