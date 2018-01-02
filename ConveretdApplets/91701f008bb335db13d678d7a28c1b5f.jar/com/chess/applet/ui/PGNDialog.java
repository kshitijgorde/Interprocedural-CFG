// 
// Decompiled by Procyon v0.5.30
// 

package com.chess.applet.ui;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.jnlp.UnavailableServiceException;
import javax.jnlp.ServiceManager;
import java.awt.Frame;
import javax.swing.JFrame;
import java.awt.Dialog;
import java.awt.Window;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.jnlp.ClipboardService;
import javax.swing.JDialog;

public class PGNDialog extends JDialog
{
    private ClipboardService clipboardService;
    private boolean isJNLPServiceAvailable;
    private JPanel buttonsSouthPanel;
    private JButton closeButton;
    private JPanel contentPane;
    private JButton copyToClipboardButton;
    private JScrollPane scrollPane;
    private JPanel southPanel;
    private JTextArea textArea;
    
    public PGNDialog(final Window parent) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.isJNLPServiceAvailable = false;
        this.checkJNLPServiceAvailability();
        this.initComponents();
    }
    
    public PGNDialog() {
        super((Frame)null);
        this.isJNLPServiceAvailable = false;
        this.checkJNLPServiceAvailability();
        this.initComponents();
    }
    
    public final boolean checkJNLPServiceAvailability() {
        try {
            this.clipboardService = (ClipboardService)ServiceManager.lookup("javax.jnlp.ClipboardService");
            this.isJNLPServiceAvailable = true;
        }
        catch (UnavailableServiceException ex) {
            this.isJNLPServiceAvailable = false;
        }
        return this.isJNLPServiceAvailable;
    }
    
    private void initComponents() {
        this.contentPane = new JPanel();
        this.scrollPane = new JScrollPane();
        this.textArea = new JTextArea();
        this.southPanel = new JPanel();
        this.buttonsSouthPanel = new JPanel();
        this.copyToClipboardButton = new JButton();
        this.closeButton = new JButton();
        this.setDefaultCloseOperation(2);
        this.setTitle("PGN");
        this.contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.contentPane.setLayout(new BorderLayout());
        this.textArea.setColumns(20);
        this.textArea.setEditable(false);
        this.textArea.setLineWrap(true);
        this.textArea.setRows(5);
        this.textArea.setCursor(Cursor.getPredefinedCursor(2));
        this.scrollPane.setViewportView(this.textArea);
        this.contentPane.add(this.scrollPane, "Center");
        this.southPanel.setLayout(new BorderLayout());
        this.copyToClipboardButton.setText("Copy to clipboard");
        this.copyToClipboardButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                PGNDialog.this.copyToClipboardButtonActionPerformed(evt);
            }
        });
        this.buttonsSouthPanel.add(this.copyToClipboardButton);
        this.closeButton.setText("Close");
        this.closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                PGNDialog.this.closeButtonActionPerformed(evt);
            }
        });
        this.buttonsSouthPanel.add(this.closeButton);
        this.southPanel.add(this.buttonsSouthPanel, "South");
        this.contentPane.add(this.southPanel, "South");
        this.getContentPane().add(this.contentPane, "Center");
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screenSize.width - 300) / 2, (screenSize.height - 352) / 2, 300, 352);
    }
    
    private void closeButtonActionPerformed(final ActionEvent evt) {
        this.dispose();
    }
    
    private void copyToClipboardButtonActionPerformed(final ActionEvent evt) {
        final StringSelection stringSelection = new StringSelection(this.textArea.getText());
        if (this.isJNLPServiceAvailable) {
            this.clipboardService.setContents((Transferable)stringSelection);
        }
        else {
            final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, stringSelection);
        }
    }
    
    public void setPGN(final String pgn) {
        this.textArea.setText(pgn);
    }
}
