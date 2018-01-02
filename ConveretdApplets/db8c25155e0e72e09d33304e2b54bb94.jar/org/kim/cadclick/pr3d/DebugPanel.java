// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.pr3d;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Vector;
import java.awt.Label;
import javax.swing.JSlider;
import java.awt.Panel;

public class DebugPanel extends Panel
{
    JSlider slider1;
    JSlider slider2;
    JSlider slider3;
    JSlider slider4;
    private Label l1;
    private Label l2;
    private Label l3;
    private Label l4;
    private Vector changeListeners;
    
    public DebugPanel() {
        this.changeListeners = new Vector();
        this.if();
    }
    
    private void if() {
        (this.slider1 = new JSlider(1)).setMinimum(-100);
        this.slider1.setMaximum(100);
        this.slider1.setValue(0);
        this.slider1.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent changeEvent) {
                DebugPanel.this.a();
            }
        });
        (this.l1 = new Label("0")).setFont(this.l1.getFont().deriveFont(10.0f).deriveFont(0));
        (this.slider2 = new JSlider(1)).setMinimum(-100);
        this.slider2.setMaximum(100);
        this.slider2.setValue(0);
        this.slider2.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent changeEvent) {
                DebugPanel.this.a();
            }
        });
        (this.l2 = new Label("0")).setFont(this.l2.getFont().deriveFont(10.0f).deriveFont(0));
        (this.slider3 = new JSlider(1)).setMinimum(-100);
        this.slider3.setMaximum(100);
        this.slider3.setValue(0);
        this.slider3.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent changeEvent) {
                DebugPanel.this.a();
            }
        });
        (this.l3 = new Label("0")).setFont(this.l3.getFont().deriveFont(10.0f).deriveFont(0));
        (this.slider4 = new JSlider(1)).setMinimum(-100);
        this.slider4.setMaximum(100);
        this.slider4.setValue(0);
        this.slider4.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent changeEvent) {
                DebugPanel.this.a();
            }
        });
        (this.l4 = new Label("0")).setFont(this.l4.getFont().deriveFont(10.0f).deriveFont(0));
        this.setLayout(new GridLayout(2, 4));
        this.add(this.slider1);
        this.add(this.slider2);
        this.add(this.slider3);
        this.add(this.slider4);
        this.add(this.l1);
        this.add(this.l2);
        this.add(this.l3);
        this.add(this.l4);
    }
    
    public void a(final f f) {
        this.changeListeners.add(f);
    }
    
    private void a() {
        try {
            final int value = this.slider1.getValue();
            final int value2 = this.slider2.getValue();
            final int value3 = this.slider3.getValue();
            final int value4 = this.slider4.getValue();
            final float n = value / 25.0f;
            final float n2 = value2 / 25.0f;
            final float n3 = value3 / 25.0f;
            final float n4 = value4 / 25.0f;
            this.l1.setText(new StringBuffer(String.valueOf(n)).toString());
            this.l2.setText(new StringBuffer(String.valueOf(n2)).toString());
            this.l3.setText(new StringBuffer(String.valueOf(n3)).toString());
            this.l4.setText(new StringBuffer(String.valueOf(n4)).toString());
            for (int i = 0; i < this.changeListeners.size(); ++i) {
                ((f)this.changeListeners.get(i)).a(new float[] { n, n2, n3, n4 });
            }
        }
        catch (Exception ex) {}
    }
}
