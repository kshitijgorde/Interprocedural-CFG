// 
// Decompiled by Procyon v0.5.30
// 

package javaviewer;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import java.awt.Button;
import java.awt.Label;
import java.awt.Point;
import java.awt.Dialog;
import java.awt.event.ActionListener;

public class AlarmDialog implements ActionListener
{
    private Dialog _$167581;
    private Point _$167587;
    private Label _$167595;
    private Button _$167603;
    
    public AlarmDialog(final Container container) {
        this._$167581 = new Dialog(this.getFrame(container), true);
        this._$167587 = container.getLocation();
        this._$167595 = new Label();
        this._$167603 = new Button("OK");
        this._$167581.setLayout(null);
        this._$167603.addActionListener(this);
    }
    
    public void setMessage(final String text) {
        this._$167595.setText(text);
    }
    
    public void show(final int n, final int n2) {
        this._$167581.setLocation(this._$167587.x + n, this._$167587.y + n2);
        this._$167581.add(this._$167595);
        this._$167581.add(this._$167603);
        this._$167595.setBounds(20, 40, 300, 15);
        this._$167603.setBounds(140, 70, 60, 20);
        this._$167581.setSize(340, 130);
        this._$167581.show();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this._$167581.dispose();
    }
    
    public Frame getFrame(final Container container) {
        Container parent = container.getParent();
        if (parent == null) {
            parent = null;
        }
        else {
            Container parent2;
            while ((parent2 = parent.getParent()) != null) {
                parent = parent2;
            }
        }
        return (Frame)parent;
    }
}
