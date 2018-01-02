// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.components;

import java.io.IOException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.datatransfer.DataFlavor;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import javax.swing.JTextPane;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import javax.swing.JPopupMenu;

public class CopyPaste extends JPopupMenu implements ActionListener
{
    private JComponent parent;
    private JMenuItem copy;
    private JMenuItem paste;
    private JMenuItem cancel;
    private JMenuItem selectall;
    
    public CopyPaste() {
        this.copy = new JMenuItem("Copier");
        this.paste = new JMenuItem("Coller");
        this.cancel = new JMenuItem("Annuler");
        this.selectall = new JMenuItem("Selectionner tout");
        this.add(this.copy);
        this.add(this.paste);
        this.add(this.cancel);
        this.addSeparator();
        this.add(this.selectall);
        this.copy.addActionListener(this);
        this.paste.addActionListener(this);
        this.cancel.addActionListener(this);
        this.selectall.addActionListener(this);
        this.setOpaque(true);
        this.setLightWeightPopupEnabled(true);
    }
    
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.copy)) {
            if (this.parent instanceof JTextPane) {
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(((JTextPane)this.parent).getSelectedText()), null);
            }
            else if (this.parent instanceof TextFieldHistory) {
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(((TextFieldHistory)this.parent).getSelectedText()), null);
            }
            else if (this.parent instanceof TextFieldHistory) {
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(((TextFieldHistory)this.parent).getSelectedText()), null);
            }
            else if (this.parent instanceof JTextField) {
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(((JTextField)this.parent).getText().trim()), null);
            }
            else if (this.parent instanceof JPasswordField) {
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(new String(((JPasswordField)this.parent).getPassword()).trim()), null);
            }
            return;
        }
        if (actionEvent.getSource().equals(this.paste)) {
            if (this.parent instanceof JTextField) {
                if (((JTextField)this.parent).getSelectedText() == null) {
                    ((JTextField)this.parent).setText(((JTextField)this.parent).getText() + this.getPress());
                }
                else {
                    ((JTextField)this.parent).setText(((JTextField)this.parent).getText().substring(0, ((JTextField)this.parent).getSelectionStart()) + this.getPress() + ((JTextField)this.parent).getText().substring(((JTextField)this.parent).getSelectionEnd()));
                }
                return;
            }
            if (this.parent instanceof TextFieldHistory) {
                if (((JTextField)this.parent).getSelectedText() == null) {
                    ((TextFieldHistory)this.parent).setText(((TextFieldHistory)this.parent).getText() + this.getPress());
                }
                else {
                    ((JTextField)this.parent).setText(((JTextField)this.parent).getText().substring(0, ((JTextField)this.parent).getSelectionStart()) + this.getPress() + ((JTextField)this.parent).getText().substring(((JTextField)this.parent).getSelectionEnd()));
                }
                return;
            }
            if (this.parent instanceof JPasswordField) {
                if (((JTextField)this.parent).getSelectedText() == null) {
                    ((JPasswordField)this.parent).setText(((JPasswordField)this.parent).getText() + this.getPress());
                }
                else {
                    ((JTextField)this.parent).setText(((JTextField)this.parent).getText().substring(0, ((JTextField)this.parent).getSelectionStart()) + this.getPress() + ((JTextField)this.parent).getText().substring(((JTextField)this.parent).getSelectionEnd()));
                }
                return;
            }
        }
        if (actionEvent.getSource().equals(this.cancel)) {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(null), null);
            return;
        }
        if (actionEvent.getSource().equals(this.selectall)) {
            if (this.parent instanceof JTextPane) {
                ((JTextPane)this.parent).requestFocus();
                ((JTextPane)this.parent).selectAll();
            }
            else if (this.parent instanceof TextFieldHistory) {
                ((TextFieldHistory)this.parent).selectAll();
            }
            else if (this.parent instanceof JPasswordField) {
                ((JPasswordField)this.parent).selectAll();
            }
            else if (this.parent instanceof JTextField) {
                ((JTextField)this.parent).selectAll();
            }
        }
    }
    
    public String getPress() {
        final Transferable contents = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        String s = null;
        try {
            if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                s = (String)contents.getTransferData(DataFlavor.stringFlavor);
            }
        }
        catch (UnsupportedFlavorException ex2) {}
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return s;
    }
    
    public void refershItemsState() {
        if (this.parent instanceof JTextPane) {
            if (((JTextPane)this.parent).getSelectedText() == null) {
                this.copy.setEnabled(false);
            }
            else {
                this.copy.setEnabled(true);
            }
        }
        else if (this.parent instanceof TextFieldHistory) {
            if (((TextFieldHistory)this.parent).getSelectedText() == null) {
                this.copy.setEnabled(false);
            }
            else {
                this.copy.setEnabled(true);
            }
        }
        else if (this.parent instanceof JPasswordField) {
            if (((JPasswordField)this.parent).getSelectedText() == null) {
                this.copy.setEnabled(false);
            }
            else {
                this.copy.setEnabled(true);
            }
        }
        else if (this.parent instanceof JTextField) {
            if (((JTextField)this.parent).getSelectedText() == null) {
                this.copy.setEnabled(false);
            }
            else {
                this.copy.setEnabled(true);
            }
        }
        if (this.getPress() == null) {
            this.paste.setEnabled(false);
            this.cancel.setEnabled(false);
        }
        else {
            this.paste.setEnabled(true);
            this.cancel.setEnabled(true);
        }
    }
    
    public void setParent(final JComponent parent) {
        this.parent = parent;
    }
}
