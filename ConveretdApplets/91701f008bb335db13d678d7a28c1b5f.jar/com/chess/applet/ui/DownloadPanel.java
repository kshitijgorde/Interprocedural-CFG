// 
// Decompiled by Procyon v0.5.30
// 

package com.chess.applet.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JPanel;

public class DownloadPanel extends JPanel
{
    private JProgressBar downloadProgressBar;
    private JPanel downloadProgressPanel;
    private JLabel loadingLabel;
    
    public DownloadPanel() {
        this.initComponents();
    }
    
    private void initComponents() {
        this.loadingLabel = new JLabel();
        this.downloadProgressPanel = new JPanel();
        this.downloadProgressBar = new JProgressBar();
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.setOpaque(false);
        this.setLayout(new GridLayout(2, 1, 0, 5));
        this.loadingLabel.setHorizontalAlignment(0);
        this.loadingLabel.setText("Downloading resources");
        this.loadingLabel.setVerticalAlignment(3);
        this.add(this.loadingLabel);
        this.downloadProgressPanel.setOpaque(false);
        this.downloadProgressPanel.setLayout(new BorderLayout());
        this.downloadProgressBar.setStringPainted(true);
        this.downloadProgressPanel.add(this.downloadProgressBar, "North");
        this.add(this.downloadProgressPanel);
    }
    
    public void setDownloadSize(final int size) {
        this.downloadProgressBar.setMaximum(size);
    }
    
    public void setProgress(final int progress) {
        this.downloadProgressBar.setValue(progress);
    }
}
