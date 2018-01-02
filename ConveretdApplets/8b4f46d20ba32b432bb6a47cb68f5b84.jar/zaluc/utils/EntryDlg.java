// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.utils;

import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Button;
import java.awt.TextField;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Dialog;

public class EntryDlg extends Dialog implements WindowListener, ActionListener, KeyListener
{
    private TextField textField;
    private Button okButton;
    private Button cancelButton;
    private Button helpButton;
    private Frame parent;
    private Callback caller;
    private int taskId;
    
    public EntryDlg(final Frame parent, final String s, final String s2, final boolean b, final String s3, final Callback caller, final int taskId) {
        super(parent, s, true);
        this.parent = parent;
        this.caller = caller;
        this.taskId = taskId;
        this.setLayout(new BorderLayout(5, 5));
        this.add("North", new Label(s2));
        this.add("Center", this.textField = new TextField());
        if (b) {
            this.textField.setEchoChar('*');
        }
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(1, 5, 5));
        panel.add(this.okButton = new Button("OK"));
        panel.add(this.cancelButton = new Button("Cancel"));
        this.add("South", panel);
        this.addWindowListener(this);
        this.addKeyListener(this);
        this.textField.addKeyListener(this);
        this.okButton.addActionListener(this);
        this.okButton.addKeyListener(this);
        this.cancelButton.addActionListener(this);
        this.cancelButton.addKeyListener(this);
        final Dimension screenSize = this.getToolkit().getScreenSize();
        this.pack();
        this.setLocation((screenSize.width - this.getSize().width) / 2, (screenSize.height - this.getSize().height) / 2);
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.close(1);
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.okButton) {
            this.close(0);
            return;
        }
        if (source == this.cancelButton) {
            this.close(1);
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 10: {
                this.close(0);
            }
            case 27: {
                this.close(1);
            }
            default: {}
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    private void close(final int n) {
        this.setVisible(false);
        this.dispose();
        if (this.caller != null) {
            if (n == 0) {
                this.caller.callback(this.taskId, n, this.textField.getText(), null);
            }
            else {
                this.caller.callback(this.taskId, n, null, null);
            }
        }
        if (this.parent != null) {
            this.parent.requestFocus();
        }
    }
}
