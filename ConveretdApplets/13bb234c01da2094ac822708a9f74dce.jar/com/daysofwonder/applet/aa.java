// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.beans.PropertyChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.Container;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.daysofwonder.util.UIProperties;
import java.awt.Frame;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

class aa extends ComponentAdapter
{
    final /* synthetic */ TablePasswordWindow14 a;
    
    aa(final TablePasswordWindow14 a) {
        this.a = a;
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
        this.a.d.requestFocusInWindow();
    }
}
