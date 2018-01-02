// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.songeditor;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

abstract class ListenerEvent implements DocumentListener
{
    public void removeUpdate(final DocumentEvent e) {
        this.update();
    }
    
    public void insertUpdate(final DocumentEvent e) {
        this.update();
    }
    
    public void changedUpdate(final DocumentEvent e) {
        this.update();
    }
    
    public abstract void update();
}
