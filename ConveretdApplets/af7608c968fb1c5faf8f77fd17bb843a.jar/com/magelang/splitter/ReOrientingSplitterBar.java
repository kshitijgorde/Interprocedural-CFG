// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.splitter;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReOrientingSplitterBar extends SplitterBar implements ActionListener
{
    private boolean needsToBeSetup;
    
    public void setOrientation(final int orientation) {
        super.setOrientation(orientation);
        if (this.needsToBeSetup) {
            this.setupBar();
            this.needsToBeSetup = false;
        }
    }
    
    public ReOrientingSplitterBar() {
        this.needsToBeSetup = true;
        this.setupBar();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.getParent() != null && this.getParent().getLayout() instanceof SplitterLayout) {
            ((SplitterLayout)this.getParent().getLayout()).swapOrientation(this.getParent());
        }
    }
    
    protected void setupBar() {
        this.removeAll();
        final SwapButton swapButton = new SwapButton();
        this.setLayout(new BorderLayout());
        this.add("Center", new SplitterSpace());
        if (this.getOrientation() == 0) {
            this.add("West", swapButton);
        }
        else {
            this.add("North", swapButton);
        }
        swapButton.addActionListener(this);
    }
    
    public void swapOrientation() {
        super.swapOrientation();
        this.setupBar();
    }
}
