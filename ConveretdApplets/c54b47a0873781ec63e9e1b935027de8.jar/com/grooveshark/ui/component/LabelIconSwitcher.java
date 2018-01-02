// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.component;

import javax.swing.Icon;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.util.List;
import javax.swing.JLabel;
import java.awt.event.ActionListener;

public class LabelIconSwitcher implements ActionListener
{
    private JLabel label;
    private List<ImageIcon> icons;
    private List<String> labels;
    
    public LabelIconSwitcher(final JLabel label, final List<ImageIcon> icons, final List<String> labels) {
        this.label = label;
        this.icons = icons;
        this.labels = labels;
    }
    
    public void actionPerformed(final ActionEvent e) {
        int index = this.icons.indexOf(this.label.getIcon()) + 1;
        if (index == this.labels.size()) {
            index = 0;
        }
        this.label.setIcon(this.icons.get(index));
        this.label.setText(this.labels.get(index));
    }
}
