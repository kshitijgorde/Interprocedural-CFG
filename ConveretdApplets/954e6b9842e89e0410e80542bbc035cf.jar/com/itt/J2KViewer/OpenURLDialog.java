// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import com.itt.J2KViewer.gui.RiverLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class OpenURLDialog extends JDialog implements ActionListener
{
    private JPanel m_mainPanel;
    private JPanel m_centerPanel;
    private JPanel m_buttonPanel;
    private JButton m_openButton;
    private JButton m_cancelButton;
    private JTextField m_url_field;
    private boolean m_isCancelled;
    
    public OpenURLDialog(final JFrame locationRelativeTo, final boolean b) {
        super(locationRelativeTo, b);
        this.m_mainPanel = new JPanel();
        this.m_centerPanel = new JPanel();
        this.m_buttonPanel = new JPanel();
        this.m_openButton = new JButton("Open");
        this.m_cancelButton = new JButton("Cancel");
        this.m_url_field = new JTextField(40);
        this.m_isCancelled = false;
        this.m_mainPanel.setLayout(new BorderLayout());
        this.getContentPane().add(this.m_mainPanel);
        this.buildCenterPanel();
        this.m_mainPanel.add(this.m_centerPanel, "Center");
        this.m_openButton.addActionListener(this);
        this.m_cancelButton.addActionListener(this);
        this.m_buttonPanel.add(this.m_openButton);
        this.m_buttonPanel.add(this.m_cancelButton);
        this.m_mainPanel.add(this.m_buttonPanel, "South");
        this.pack();
        this.setLocationRelativeTo(locationRelativeTo);
        this.setTitle("Open URL");
    }
    
    protected void buildCenterPanel() {
        this.m_centerPanel.setLayout(new RiverLayout());
        this.AddKeyListenerToJTextField(this.m_url_field);
        this.m_url_field.setText("jpip://");
        final Font font = new Font("Arial", 1, 12);
        final JLabel label = new JLabel("Open An Image By Entering A URL");
        label.setFont(font);
        this.m_centerPanel.add("center", label);
        this.m_centerPanel.add("br center", new JLabel("Example:  jpip://localhost:8080/JP2Server/myalias"));
        this.m_centerPanel.add("br br left", new JLabel("URL:"));
        this.m_centerPanel.add("", this.m_url_field);
        this.m_centerPanel.add("gr", new JLabel("  "));
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.m_openButton == actionEvent.getSource()) {
            this.doOpenURL();
        }
        if (this.m_cancelButton == actionEvent.getSource()) {
            this.m_isCancelled = true;
            this.setVisible(false);
        }
    }
    
    protected void doOpenURL() {
        this.setVisible(this.m_isCancelled = false);
    }
    
    public String getSelectedURL() {
        return this.m_url_field.getText().trim();
    }
    
    public boolean isCancelled() {
        return this.m_isCancelled;
    }
    
    protected void AddKeyListenerToJTextField(final JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(final KeyEvent keyEvent) {
                OpenURLDialog.this.txtKeyTyped(keyEvent, false, false, false);
            }
        });
    }
    
    protected void txtKeyTyped(final KeyEvent keyEvent, final boolean b, final boolean b2, final boolean b3) {
        if (keyEvent.getKeyChar() == '\n') {
            this.doOpenURL();
        }
    }
}
