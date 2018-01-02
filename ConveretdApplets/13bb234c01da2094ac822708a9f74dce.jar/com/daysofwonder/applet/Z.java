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
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

class Z extends WindowAdapter
{
    final /* synthetic */ TablePasswordWindow14 a;
    
    Z(final TablePasswordWindow14 a) {
        this.a = a;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.a.e.setValue(-1);
    }
}
