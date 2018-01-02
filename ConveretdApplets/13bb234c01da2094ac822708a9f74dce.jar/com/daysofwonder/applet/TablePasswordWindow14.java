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

public class TablePasswordWindow14 extends JDialog implements D, ActionListener, PropertyChangeListener
{
    private Frame a;
    private UIProperties b;
    private String c;
    private JTextField d;
    private JOptionPane e;
    private String f;
    private String g;
    
    public TablePasswordWindow14(final Frame a, final UIProperties b) {
        super(a, true);
        this.c = null;
        this.a = a;
        this.b = b;
    }
    
    public void a() {
        this.e();
        this.pack();
        this.setVisible(true);
    }
    
    private void e() {
        this.setTitle(this.b.b("tablepass.title"));
        this.d = new JTextField(25);
        final Object[] array = { this.b.b("tablepass.expl"), this.d };
        this.f = this.b.b("tablepass.ok");
        this.g = this.b.b("tablepass.cancel");
        final Object[] array2 = { this.f, this.g };
        this.setContentPane(this.e = new JOptionPane(array, 3, 0, null, array2, array2[0]));
        this.setDefaultCloseOperation(0);
        this.setLocationRelativeTo(this.a);
        this.addWindowListener(new Z(this));
        this.addComponentListener(new aa(this));
        this.d.addActionListener(this);
        this.e.addPropertyChangeListener(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.e.setValue(this.f);
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        final String propertyName = propertyChangeEvent.getPropertyName();
        if (this.isVisible() && propertyChangeEvent.getSource() == this.e && ("value".equals(propertyName) || "inputValue".equals(propertyName))) {
            final Object value = this.e.getValue();
            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                return;
            }
            this.e.setValue(JOptionPane.UNINITIALIZED_VALUE);
            if (this.f.equals(value)) {
                this.c = this.d.getText();
                this.d();
            }
            else {
                this.c = null;
                this.d();
            }
        }
    }
    
    public void d() {
        this.d.setText(null);
        this.setVisible(false);
    }
    
    public void dispose() {
    }
    
    public String c() {
        return this.c;
    }
    
    public boolean b() {
        return this.c != null;
    }
}
