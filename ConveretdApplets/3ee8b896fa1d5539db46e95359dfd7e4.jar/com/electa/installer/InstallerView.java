// 
// Decompiled by Procyon v0.5.30
// 

package com.electa.installer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InstallerView extends JPanel
{
    private static final long serialVersionUID = 8501789711287512014L;
    JLabel progressText;
    JProgressBar progressBar;
    
    public InstallerView() {
        this.progressText = new JLabel("Launching eLecta Live...");
        final Font f = new Font("Tahoma", 0, 12);
        this.setLayout(new BoxLayout(this, 1));
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(300, 80));
        this.progressText.setAlignmentX(0.0f);
        this.progressText.setFont(f);
        (this.progressBar = new JProgressBar(0)).setStringPainted(true);
        this.progressBar.setBackground(Color.white);
        this.progressBar.setFont(f);
        this.progressBar.setBorderPainted(false);
        this.progressBar.setAlignmentX(0.0f);
        this.add(this.progressBar, this);
    }
    
    public JProgressBar getProgressBar() {
        return this.progressBar;
    }
}
