// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.gui;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JButton;
import com.itt.J2KViewer.imagetools.MainImageStream;
import com.itt.J2KViewer.controller.ViewCentral;
import com.itt.J2KViewer.util.Log;
import javax.swing.JDialog;

public class PropertiesDialog extends JDialog
{
    private static final long serialVersionUID = 1L;
    private static Log log;
    private ViewCentral viewCentral;
    private MainImageStream imageStream;
    private JButton buttonClose;
    private JPanel buttonPanel;
    private JPanel mainPanel;
    private JList msgListBox;
    private JScrollPane textScrollPane;
    private JLabel labelInfo;
    static /* synthetic */ Class class$com$itt$J2KViewer$gui$PropertiesDialog;
    
    public PropertiesDialog(final Frame frame, final boolean b, final ViewCentral viewCentral) {
        super(frame, b);
        this.viewCentral = null;
        this.imageStream = null;
        this.labelInfo = null;
        this.viewCentral = viewCentral;
        this.imageStream = viewCentral.getImageStream();
        this.initComponents();
        this.setSize(475, 400);
        this.getImageProperties();
        this.getRootPane().setDefaultButton(this.buttonClose);
    }
    
    public void getImageProperties() {
        try {
            if (this.imageStream != null && this.viewCentral.getPropertyManager().isOpen()) {
                this.msgListBox.setListData(this.imageStream.getRawImageFileProperties().split("\n"));
                this.msgListBox.setSelectionMode(0);
            }
            else {
                this.msgListBox.setListData(new Object[] { "<Properties Unavailable. Image must be open.>" });
            }
        }
        catch (Exception ex) {
            PropertiesDialog.log.error("Error retrieving image stream properties.", ex);
        }
    }
    
    private void initComponents() {
        this.mainPanel = new JPanel();
        this.textScrollPane = new JScrollPane();
        this.msgListBox = new JList();
        this.buttonPanel = new JPanel();
        this.buttonClose = new JButton();
        this.setDefaultCloseOperation(2);
        this.setTitle("Code-Stream Properties");
        this.mainPanel.setLayout(new BorderLayout());
        this.textScrollPane.setViewportView(this.msgListBox);
        this.mainPanel.add(this.textScrollPane, "Center");
        this.getContentPane().add(this.mainPanel, "Center");
        this.buttonPanel.setLayout(new BorderLayout());
        this.buttonPanel.setBorder(BorderFactory.createEmptyBorder(4, 6, 4, 6));
        this.buttonClose.setMnemonic('C');
        this.buttonClose.setText("Close");
        this.buttonClose.setToolTipText("Close window");
        this.buttonClose.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                PropertiesDialog.this.buttonCloseActionPerformed(actionEvent);
            }
        });
        this.msgListBox.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                PropertiesDialog.this.msgListBoxMouseClicked(mouseEvent);
            }
        });
        this.labelInfo = new JLabel();
        this.buttonPanel.add(this.labelInfo, "West");
        this.buttonPanel.add(this.buttonClose, "East");
        this.getContentPane().add(this.buttonPanel, "South");
        this.pack();
    }
    
    private void msgListBoxMouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {}
    }
    
    private void buttonCloseActionPerformed(final ActionEvent actionEvent) {
        this.dispose();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        PropertiesDialog.log = new Log((PropertiesDialog.class$com$itt$J2KViewer$gui$PropertiesDialog == null) ? (PropertiesDialog.class$com$itt$J2KViewer$gui$PropertiesDialog = class$("com.itt.J2KViewer.gui.PropertiesDialog")) : PropertiesDialog.class$com$itt$J2KViewer$gui$PropertiesDialog);
    }
}
