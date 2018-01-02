// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.tabsplitter;

import com.magelang.BorderPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.Panel;

class TabLabelPanel extends Panel implements ActionListener
{
    private Component comp;
    private Button nameButton;
    private int position;
    private String explicitText;
    
    public Component removeComponent() {
        this.remove(this.comp);
        return this.comp;
    }
    
    public int getPosition() {
        return this.position;
    }
    
    public String getTabName() {
        return this.nameButton.getLabel();
    }
    
    public String getExplicitText() {
        return this.explicitText;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        ((SplitterPanel)this.getParent()).separateTabs(this);
    }
    
    public void decrPosition(final int n) {
        if (n < this.position) {
            --this.position;
        }
    }
    
    public TabLabelPanel(final String s, final Component comp, final int position, final String explicitText) {
        this.comp = comp;
        this.explicitText = explicitText;
        this.position = position;
        (this.nameButton = new Button(s)).addActionListener(this);
        this.setLayout(new BorderLayout());
        final BorderPanel borderPanel = new BorderPanel();
        borderPanel.add("Center", this.nameButton);
        this.add("North", borderPanel);
        this.add("Center", comp);
    }
    
    public Component getComponent() {
        return this.comp;
    }
}
