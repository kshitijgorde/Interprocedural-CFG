// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.ListCellRenderer;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class StrokeChooserPanel extends JPanel
{
    private JComboBox selector;
    
    public StrokeChooserPanel(final StrokeSample selectedItem, final StrokeSample[] array) {
        this.setLayout(new BorderLayout());
        (this.selector = new JComboBox((E[])array)).setSelectedItem(selectedItem);
        this.selector.setRenderer(new StrokeSample(new BasicStroke(1.0f)));
        this.add(this.selector);
        this.selector.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                StrokeChooserPanel.this.selector.transferFocus();
            }
        });
    }
    
    public Stroke getSelectedStroke() {
        return ((StrokeSample)this.selector.getSelectedItem()).getStroke();
    }
}
