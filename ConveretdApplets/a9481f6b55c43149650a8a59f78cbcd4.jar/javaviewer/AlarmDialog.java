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
    private Dialog _$2298;
    private Point _$2299;
    private Label _$2300;
    private Button _$2301;
    
    public AlarmDialog(final Container container) {
        this._$2298 = new Dialog(this.getFrame(container), true);
        this._$2299 = container.getLocation();
        this._$2300 = new Label();
        this._$2301 = new Button("OK");
        this._$2298.setLayout(null);
        this._$2301.addActionListener(this);
    }
    
    public void setMessage(final String text) {
        this._$2300.setText(text);
    }
    
    public void show(final int n, final int n2) {
        this._$2298.setLocation(this._$2299.x + n, this._$2299.y + n2);
        this._$2298.add(this._$2300);
        this._$2298.add(this._$2301);
        this._$2300.setBounds(20, 40, 300, 15);
        this._$2301.setBounds(140, 70, 60, 20);
        this._$2298.setSize(340, 130);
        this._$2298.show();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this._$2298.dispose();
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
