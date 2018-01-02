// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JDialog;

public class LicenseDialog extends JDialog
{
    private JPanel licensePanel;
    private JTextArea textArea;
    private JButton closeButton;
    
    public LicenseDialog(final Frame frame, final boolean b, final String s) {
        super(frame, b);
        this.setDefaultCloseOperation(2);
        this.setTitle("License");
        this.initComponents(s);
        this.getRootPane().setDefaultButton(this.closeButton);
    }
    
    private void initComponents(final String s) {
        (this.licensePanel = new JPanel()).setLayout(new BoxLayout(this.licensePanel, 3));
        (this.textArea = new JTextArea(30, 100)).setLineWrap(true);
        this.textArea.setEditable(false);
        this.textArea.append(s);
        this.textArea.setCaretPosition(0);
        final JScrollPane scrollPane = new JScrollPane(this.textArea);
        scrollPane.setLocation(0, 0);
        (this.closeButton = new JButton("Close")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                LicenseDialog.this.closeButtonActionPerformed(actionEvent);
            }
        });
        this.licensePanel.add(scrollPane);
        this.closeButton.setAlignmentX(0.5f);
        this.licensePanel.add(this.closeButton);
        this.getContentPane().add(this.licensePanel);
        this.pack();
    }
    
    private void closeButtonActionPerformed(final ActionEvent actionEvent) {
        this.dispose();
    }
}
